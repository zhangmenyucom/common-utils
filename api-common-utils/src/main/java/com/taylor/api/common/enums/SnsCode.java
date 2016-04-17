package com.taylor.api.common.enums;

/**
 * @notes:异常类code定义
 * @author henry
 * 2015年11月13日		下午4:44:23
 */
public enum SnsCode {
	

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
	 * 请求已重定向  = 29001
	 */
	CONTROLLER_FRONT_REQUEST_HAS_BEEN_REDIRECTED("29001"),
	
	/**
	 * 客户端错误  = 29002
	 */
	CONTROLLER_FRONT_CLIENT_ERROR("29002"),
	
	/**
	 * 服务器异常  = 29003
	 */
	CONTROLLER_FRONT_SERVER_EXCEPTION("29003"),
	
	/**
	 * 请求参数错误 = 29004
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_ERROR("29004"),
	
	/**
	 * 请求参数类型错误 = 29005
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_TYPE_ERROR("29005"),
	
	/**
	 * 请求参数长度错误 = 29006
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_LENGTH_ERROR("29006"),
	
	/**
	 * 请求参数值错误 = 29007
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_VALUE_ERROR("29007"),
	
/*****************************************************控制层后台**********************************************/
	
	/**
	 * 请求已重定向  = 29201
	 */
	CONTROLLER_ADMIN_REQUEST_HAS_BEEN_REDIRECTED("29201"),
	
	/**
	 * 客户端错误  = 29202
	 */
	CONTROLLER_ADMIN_CLIENT_ERROR("29202"),
	
	/**
	 * 服务器异常  = 29203
	 */
	CONTROLLER_ADMIN_SERVER_EXCEPTION("29203"),
	
	/**
	 * 请求参数错误 = 29204
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_ERROR("29204"),
	
	/**
	 * 请求参数类型错误 = 29005
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_TYPE_ERROR("29205"),
	
	/**
	 * 请求参数长度错误 = 29206
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_LENGTH_ERROR("29206"),
	
	/**
	 * 请求参数值错误 = 29207
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_VALUE_ERROR("29207"),
	
	/*****************************************************服务层*****************************************/
	
	/**
	 * 服务器异常  = 29403
	 */
	SERVICE_SERVER_EXCEPTION("29401"),
	
	/**
	 * 请求参数错误 = 29402
	 */
	SERVICE_REQUEST_PARAMETER_ERROR("29402"),
	
	/**
	 * 请求参数类型错误 = 29403
	 */
	SERVICE_REQUEST_PARAMETER_TYPE_ERROR("29403"),
	
	/**
	 * 请求参数长度错误 = 29404
	 */
	SERVICE_REQUEST_PARAMETER_LENGTH_ERROR("29404"),
	
	/**
	 * 请求参数值错误  = 29405
	 */
	SERVICE_REQUEST_PARAMETER_VALUE_ERROR("29405");
    
    /**
     * 枚举值
     */
    private String enumValue;
    
    /**
     * 构造函数，枚举类型只能为私有
     * @param enumValue
     * @author: henry
     * 2015年11月13日		下午4:29:41
     */
    private SnsCode(String enumValue) {
        this.enumValue = enumValue;
    }

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}
}
