package com.taylor.api.common.xss;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class XssFilter implements Filter {

	public static final Logger LOG = Logger.getLogger(XssFilter.class);
	
	/**
	 * 销毁操作
	 */
	@Override
	public void destroy() {
		LOG.info("XssFilter destroy() begin");
		XssSecurityManager.destroy();
		LOG.info("XssFilter destroy() end");
	}

	/**
	 * 安全审核
	 * 读取配置信息
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 判断是否使用HTTP
        checkRequestResponse(request, response);
        // 转型
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        /*****
         * 上传请求直接跳过
         */
        String uri = httpRequest.getRequestURI();
        if (uri.indexOf("/file/upload") > -1) {
        	MultipartResolver resolver = new CommonsMultipartResolver(httpRequest.getSession().getServletContext());
        	MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(httpRequest);
        	chain.doFilter(multipartRequest, response);
        	return;
		}
        
        // http信息封装类
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(httpRequest);
        
        // 对request信息进行封装并进行校验工作，若校验失败（含非法字符），根据配置信息进行日志记录和请求中断处理
        if(xssRequest.validateParameter(httpResponse)){
        	if(XssSecurityConfig.isIs_log()){
        		// 记录攻击访问日志
        		// 可使用数据库、日志、文件等方式
        		LOG.error("<<<<<<<<<<<<<<<<<<<<====== 非法请求访问 ======>>>>>>>>>>>>>>>>>>>>>>>");
        	}
        	if(XssSecurityConfig.isIs_chain()){
//        		httpRequest.getRequestDispatcher(XssSecurityCon.FILTER_ERROR_PAGE).forward( httpRequest, httpResponse);
    		}
//        	httpResponse.sendRedirect(httpRequest.getContextPath());
        	return;
        }
        chain.doFilter(xssRequest, response);
	}

	/**
	 * 初始化操作
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		XssSecurityManager.init(filterConfig);
	}

	/**
     * 判断Request ,Response 类型
     * @param request		ServletRequest
     * @param response		ServletResponse
     * @throws ServletException 
     */
    private void checkRequestResponse(ServletRequest request,
            ServletResponse response) throws ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Can only process HttpServletRequest");

        }
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }
    }

}
