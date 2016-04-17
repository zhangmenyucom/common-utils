/**
 * 
 */
package com.taylor.api.common.xss;

import java.util.Iterator;
import java.util.regex.Pattern;

import javax.servlet.FilterConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @notes:安全过滤配置管理类，由XSSSecurityManger修改
 *
 * @author taylor
 *
 * 2014-11-14	下午4:03:39
 */
public class XssSecurityManager {
	
	private static final Logger LOG = Logger.getLogger(XssSecurityManager.class);
	
	/**
	 * REGEX：校验正则表达式
	 */
	private static String REGEX;
	
	 /**
     * 特殊字符匹配
     */
    private static Pattern XSS_PATTERN ;
	
    
    private XssSecurityManager(){
        //不可被实例化
    }
    
    public static void init(FilterConfig config){
    	LOG.info("XSSSecurityManager init(FilterConfig config) begin");
    	//初始化过滤配置文件
        String xssPath = config.getServletContext().getRealPath("/") + config.getInitParameter("securityconfig");
        
        // 初始化安全过滤配置
        try {
			if(initConfig(xssPath)){
				// 生成匹配器
				XSS_PATTERN = Pattern.compile(REGEX);
			}
		} catch (DocumentException e) {
			LOG.error("安全过滤配置文件xss_security_config.xml加载异常",e);
		}
		LOG.info("XSSSecurityManager init(FilterConfig config) end");
    }
    
    /**
     * 读取安全审核配置文件xss_security_config.xml
     * 设置XSSSecurityConfig配置信息
     * @param path 配置文件地址 eg C:/apache-tomcat-6.0.33/webapps/security_filter/WebRoot/config/xss/xss_security_config.xml
     * @throws DocumentException
     */
	@SuppressWarnings("unchecked")
	public static boolean initConfig(String path) throws DocumentException {
		LOG.info(path);
		LOG.info("XSSSecurityManager.initConfig(String path) begin");
		Element superElement = new SAXReader().read(path).getRootElement();
		XssSecurityConfig.setIs_check_header(new Boolean(getEleValue(superElement,XssSecurityCon.IS_CHECK_HEADER)));
		XssSecurityConfig.setIs_check_parameter(new Boolean(getEleValue(superElement,XssSecurityCon.IS_CHECK_PARAMETER)));
		XssSecurityConfig.setIs_log(new Boolean(getEleValue(superElement,XssSecurityCon.IS_LOG)));
		XssSecurityConfig.setIs_chain(new Boolean(getEleValue(superElement,XssSecurityCon.IS_CHAIN)));
		XssSecurityConfig.setReplace(new Boolean(getEleValue(superElement,XssSecurityCon.REPLACE)));

		Element regexEle = superElement.element(XssSecurityCon.REGEX_LIST);
		
		if(regexEle != null){
			Iterator<Element> regexIt = regexEle.elementIterator();
			StringBuilder tempStr = new StringBuilder("^");
			//xml的cdata标签传输数据时，会默认在\前加\，需要将\\替换为\
			while(regexIt.hasNext()){
				Element regex = regexIt.next();
				String tmp = regex.getText();
				tmp = tmp.replaceAll("\\\\\\\\", "\\\\");
	        	tempStr.append(tmp);
	        	tempStr.append("|");
			}
	        if(tempStr.charAt(tempStr.length()-1)=='|'){
	        	REGEX= tempStr.substring(0, tempStr.length()-1)+"$";
	        	LOG.info("安全匹配规则"+REGEX);
	        }else{
	        	LOG.error("安全过滤配置文件加载失败:正则表达式异常 "+tempStr.toString());
	        	return false;
	        }
		}else{
			LOG.error("安全过滤配置文件中没有 "+XssSecurityCon.REGEX_LIST+" 属性");
			return false;
		}
		LOG.info("XSSSecurityManager.initConfig(String path) end");
		return true;

	}
    
	/**
	 * 从目标element中获取指定标签信息，若找不到该标签，记录错误日志
	 * @param element 目标节点
	 * @param tagName 制定标签
	 */
	private static String getEleValue(Element element, String tagName){
		if (StringUtils.isBlank(element.elementText(tagName))){
			LOG.error("安全过滤配置文件中没有 "+XssSecurityCon.REGEX_LIST+" 属性");
		}
		return element.elementText(tagName);
	}
	
    /**
     * 对非法字符进行替换
     * @param text
     */
    public static String securityReplace(String text){
    	if(StringUtils.isBlank(text)){
    		return text;
    	}else{
    		return text.replaceAll(REGEX, XssSecurityCon.REPLACEMENT);
    	}
    }
    
    /**
     * 匹配字符是否含特殊字符
     * @param text
     */
    public static boolean matches(String text){
    	if(text==null){
    		return false;
    	}
    	return XSS_PATTERN.matcher(text).matches();
    }
    
    /**
     * 释放关键信息
     */
    public static void destroy(){
    	LOG.info("XSSSecurityManager.destroy() begin");
        XSS_PATTERN = null;
        REGEX = null;
        LOG.info("XSSSecurityManager.destroy() end");
    }
}
