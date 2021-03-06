package com.taylor.api.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;



public class Resful2Utils {
	
	private static final Logger LOG = Logger.getLogger(Resful2Utils.class);
	
	private Resful2Utils(){
		
	}

	//-- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	private static ObjectMapper mapper = new ObjectMapper();

	//-- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数.

	 * eg.
	 * render("text/plain", "hello", "encoding:GBK");
	 * render("text/plain", "hello", "no-cache:false");
	 * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(HttpServletResponse response, final String contentType, final String content, final String... headers) {
		response = initResponseHeader(response, contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			LOG.error("render error", e);
		}
	}

	public static <T> void renderXML(HttpServletResponse response, final T object, Class<T> cls, final String... headers) {
		response = initResponseHeader(response, ServletUtils.XML_TYPE, headers);
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(cls);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(object, response.getWriter());
			response.getWriter().flush();
		} catch (JAXBException e) {
			LOG.error("renderXML JAXBException", e);
		} catch (IOException e) {
			LOG.error("renderXML IOException", e);
		}

	}

	/**
	 * 直接输出文本.
	 * @see #render(String, String, String...)
	 */
	public static void renderText(HttpServletResponse response, final String text, final String... headers) {
		render(response, ServletUtils.TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(HttpServletResponse response, final String html, final String... headers) {
		render(response, ServletUtils.HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(HttpServletResponse response, final String xml, final String... headers) {
		render(response, ServletUtils.XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse response, final String jsonString, final String... headers) {
		render(response, ServletUtils.JSON_TYPE, jsonString, headers);
	}

	/**
	 * 直接输出JSON,使用Jackson转换Java对象.
	 * 
	 * @param data 可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(HttpServletResponse response, final Object data, final String... headers) {
		response = initResponseHeader(response, ServletUtils.JSON_TYPE, headers);
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			LOG.error("renderJson IOException", e);
		}
	}

	/**
	 * 直接输出支持跨域Mashup的JSONP.
	 * 
	 * @param callbackName callback函数名.
	 * @param object Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 */
	public static void renderJsonp(HttpServletResponse response, final String callbackName, final Object object, final String... headers) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			LOG.error("renderJsonp IOException", e);
			throw new IllegalArgumentException(e);
		}

		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();

		render(response, ServletUtils.JS_TYPE, result, headers);
	}

	public static void renderJavascript(HttpServletResponse response, final String jsValue, String... heads) {
		render(response, ServletUtils.JS_TYPE, jsValue, heads);

	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(HttpServletResponse response, final String contentType, final String... headers) {
		//分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				LOG.error("不是一个合法的header类型");
				throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}
		}

		//设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setNoCacheHeader(response);
		}
		return response;
	}

	public static boolean isValidateURL(String url) {
		return StringUtils.startsWithIgnoreCase(url, "http://");
	}

	public static String getRealIP(HttpServletRequest request) {
		String realIp=request.getHeader("cdn-src-ip");
		if(StringUtils.isBlank(realIp)){
			realIp= request.getHeader("x-forwarded-for");
			if(StringUtils.isNotBlank(realIp)){
				for(String IP:realIp.split(",")){
					if(IP.startsWith("192.168")||IP.startsWith("10")||IP.startsWith("172.16")){
						continue;
					}else{
						realIp=IP;
						break;
					}
				}
			}
			if(StringUtils.isBlank(realIp)){
				realIp=request.getHeader("x-real-ip");
			}
			if(StringUtils.isBlank(realIp)){
				realIp=request.getRemoteAddr();
			}
		}
		return realIp;
	}

	public static int getRealPort() {
		return 80;
	}

}
