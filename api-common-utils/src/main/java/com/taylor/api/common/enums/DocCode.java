package com.taylor.api.common.enums;

/**
 * @notes:异常类code定义
 * @author henry
 * 2015年11月13日		下午4:44:23
 */
public enum DocCode {
	
	/*****************************************************通用代码**********************************************/
	
	/**
	 * 请求成功
	 */
	COMMON_RESULT_TYPE_200("200"),
	
	/**
	 * 请求参数异常
	 */
	COMMON_RESULT_TYPE_400("20400"),
	
	/**
	 * 未知 URL 路径
	 */
	COMMON_RESULT_TYPE_404("20404"),
	
	/**
	 * 服务器异常
	 */
	COMMON_RESULT_TYPE_500("20500"),
	
	/*****************************************************控制层前台**********************************************/
	
	/**
	 * 请求已重定向  = 21001
	 */
	CONTROLLER_FRONT_REQUEST_HAS_BEEN_REDIRECTED("21001"),
	
	/**
	 * 客户端错误  = 21002
	 */
	CONTROLLER_FRONT_CLIENT_ERROR("21002"),
	
	/**
	 * 服务器异常  = 21003
	 */
	CONTROLLER_FRONT_SERVER_EXCEPTION("21003"),
	
	/**
	 * 请求参数错误 = 21004
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_ERROR("21004"),
	
	/**
	 * 请求参数类型错误 = 21005
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_TYPE_ERROR("21005"),
	
	/**
	 * 请求参数长度错误 = 21006
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_LENGTH_ERROR("21006"),
	
	/**
	 * 请求参数值错误 = 21007
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_VALUE_ERROR("21007"),
	
/*****************************************************控制层后台**********************************************/
	
	/**
	 * 请求已重定向  = 21201
	 */
	CONTROLLER_ADMIN_REQUEST_HAS_BEEN_REDIRECTED("21201"),
	
	/**
	 * 客户端错误  = 21202
	 */
	CONTROLLER_ADMIN_CLIENT_ERROR("21202"),
	
	/**
	 * 服务器异常  = 21203
	 */
	CONTROLLER_ADMIN_SERVER_EXCEPTION("21203"),
	
	/**
	 * 请求参数错误 = 21204
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_ERROR("21204"),
	
	/**
	 * 请求参数类型错误 = 21005
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_TYPE_ERROR("21205"),
	
	/**
	 * 请求参数长度错误 = 21206
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_LENGTH_ERROR("21206"),
	
	/**
	 * 请求参数值错误 = 21207
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_VALUE_ERROR("21207"),
	
	/*****************************************************服务层*****************************************/
	
	/**
	 * 服务器异常  = 21403
	 */
	SERVICE_SERVER_EXCEPTION("21401"),
	
	/**
	 * 请求参数错误 = 21402
	 */
	SERVICE_REQUEST_PARAMETER_ERROR("21402"),
	
	/**
	 * 请求参数类型错误 = 21403
	 */
	SERVICE_REQUEST_PARAMETER_TYPE_ERROR("21403"),
	
	/**
	 * 请求参数长度错误 = 21404
	 */
	SERVICE_REQUEST_PARAMETER_LENGTH_ERROR("21404"),
	
	/**
	 * 请求参数值错误  = 21405
	 */
	SERVICE_REQUEST_PARAMETER_VALUE_ERROR("21405");
    
    /**
     * 枚举值
     */
    private String enumValue;
    
    /**
     * 构造函数，枚举类型只能为私有
     * @param enumValue
     * @author: henry
     * 2015年11月13日		下午4:22:41
     */
    private DocCode(String enumValue) {
        this.enumValue = enumValue;
    }

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}
}
