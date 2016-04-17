package com.taylor.api.common.xss;

/**
 * @notes:
 *
 * @author fei
 *
 * 2014-11-14	下午4:04:28
 */
public class XssSecurityCon {

	/**
	 * 配置文件标签 isCheckHeader
	 */
	public final static String IS_CHECK_HEADER = "isCheckHeader";

	/**
	 * 配置文件标签 isCheckParameter
	 */
	public final static String IS_CHECK_PARAMETER = "isCheckParameter";

	/**
	 * 配置文件标签 isLog
	 */
	public final static String IS_LOG = "isLog";

	/**
	 * 配置文件标签 isChain
	 */
	public final static String IS_CHAIN = "isChain";

	/**
	 * 配置文件标签 replace
	 */
	public final static String REPLACE = "replace";

	/**
	 * 配置文件标签 regexList
	 */
	public final static String REGEX_LIST = "regexList";

	/**
	 * 替换非法字符的字符串
	 */
	public final static String REPLACEMENT = "";

	/**
	 * FILTER_ERROR_PAGE:过滤后错误页面
	 */
	public final static String FILTER_ERROR_PAGE = "/common/filtererror.jsp";
	
	private XssSecurityCon() {}
}
