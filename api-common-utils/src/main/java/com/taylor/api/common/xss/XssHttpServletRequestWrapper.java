package com.taylor.api.common.xss;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	HttpServletRequest orgRequest = null;

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
	}
	
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		// 若开启特殊字符替换，对特殊字符进行替换
		if(XssSecurityConfig.isReplace()){
			value = XssSecurityManager.securityReplace(value);
		}
		return value;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		// 若开启特殊字符替换，对特殊字符进行替换
		if(XssSecurityConfig.isReplace()){
			value = XssSecurityManager.securityReplace(value);
		}
		return value;
	}

	/**
	 * 没有违规的数据，就返回false;
	 * 
	 */
	@SuppressWarnings("unchecked")
	private boolean checkHeader(){
		Enumeration<String> headerParams = this.getHeaderNames();
		while(headerParams.hasMoreElements()){
			String headerName = headerParams.nextElement();
			String headerValue = this.getHeader(headerName);
			if(XssSecurityManager.matches(headerValue)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 没有违规的数据，就返回false;
	 * 
	 */
	@SuppressWarnings("unchecked")
	private boolean checkParameter(){
		Map<String,Object> submitParams = this.getParameterMap();
		Set<String> submitNames = submitParams.keySet();
		for(String submitName : submitNames){
			Object submitValues = submitParams.get(submitName);
			if(submitValues instanceof String){
				String tempV = (String)submitValues;
				if(StringUtils.isNotBlank(tempV) && XssSecurityManager.matches(tempV)){
					return true;
				}
			}else if(submitValues instanceof String[]){
				for(String submitValue : (String[])submitValues){
					String tempV = submitValue;
					if(StringUtils.isNotBlank(tempV) && XssSecurityManager.matches(tempV)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
   
    /**
     * 没有违规的数据，就返回false;
     * 若存在违规数据，根据配置信息判断是否跳转到错误页面
     * @param response
     * @throws IOException 
     * @throws ServletException 
     */
    public boolean validateParameter(HttpServletResponse response) {
    	// 开始header校验，对header信息进行校验
    	if(XssSecurityConfig.isIs_check_header()){
	    	if(this.checkHeader()){
	    		return true;
	    	}
    	}
    	// 开始parameter校验，对parameter信息进行校验
    	if(XssSecurityConfig.isIs_check_parameter()){
	    	if(this.checkParameter()){
	    		return true;
	    	}
    	}
    	return false;
    }
}
