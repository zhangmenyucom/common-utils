package com.taylor.api.common.xss;

/**
 * @notes:安全过滤配置信息类
 *
 * @author taylor
 *
 * 2014-11-14	下午4:04:10
 */
public class XssSecurityConfig {
	
	/**
	 * CHECK_HEADER：是否开启header校验
	 */
	private static boolean is_check_header; 
	
	/**
	 * CHECK_PARAMETER：是否开启parameter校验
	 */
	private static boolean is_check_parameter;
	
	/**
	 * IS_LOG：是否记录日志
	 */
	private static boolean is_log;
	
	/**
	 * IS_LOG：是否中断操作
	 */
	private static boolean is_chain;
	
	/**
	 * REPLACE：是否开启替换
	 */
	private static boolean replace;
	
	private XssSecurityConfig() {
		
	}

	public static boolean isIs_check_header() {
		return is_check_header;
	}

	public static void setIs_check_header(boolean is_check_header) {
		XssSecurityConfig.is_check_header = is_check_header;
	}

	public static boolean isIs_check_parameter() {
		return is_check_parameter;
	}

	public static void setIs_check_parameter(boolean is_check_parameter) {
		XssSecurityConfig.is_check_parameter = is_check_parameter;
	}

	public static boolean isIs_log() {
		return is_log;
	}

	public static void setIs_log(boolean is_log) {
		XssSecurityConfig.is_log = is_log;
	}

	public static boolean isIs_chain() {
		return is_chain;
	}

	public static void setIs_chain(boolean is_chain) {
		XssSecurityConfig.is_chain = is_chain;
	}

	public static boolean isReplace() {
		return replace;
	}

	public static void setReplace(boolean replace) {
		XssSecurityConfig.replace = replace;
	}
	
}
