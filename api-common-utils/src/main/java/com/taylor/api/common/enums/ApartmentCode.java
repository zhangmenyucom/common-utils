package com.taylor.api.common.enums;

/**
 * @notes:异常类code定义
 * @author henry
 * 2015年11月13日		下午4:44:23
 */
public enum ApartmentCode {
	
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
	 * 请求已重定向  = 22001
	 */
	CONTROLLER_FRONT_REQUEST_HAS_BEEN_REDIRECTED("22001"),
	
	/**
	 * 客户端错误  = 22002
	 */
	CONTROLLER_FRONT_CLIENT_ERROR("22002"),
	
	/**
	 * 服务器异常  = 22003
	 */
	CONTROLLER_FRONT_SERVER_EXCEPTION("22003"),
	
	/**
	 * 请求参数错误 = 22004
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_ERROR("22004"),
	
	/**
	 * 请求参数类型错误 = 22005
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_TYPE_ERROR("22005"),
	
	/**
	 * 请求参数长度错误 = 22006
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_LENGTH_ERROR("22006"),
	
	/**
	 * 请求参数值错误 = 22007
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_VALUE_ERROR("22007"),
	
/*****************************************************控制层后台**********************************************/
	
	/**
	 * 请求已重定向  = 22201
	 */
	CONTROLLER_ADMIN_REQUEST_HAS_BEEN_REDIRECTED("22201"),
	
	/**
	 * 客户端错误  = 22202
	 */
	CONTROLLER_ADMIN_CLIENT_ERROR("22202"),
	
	/**
	 * 服务器异常  = 22203
	 */
	CONTROLLER_ADMIN_SERVER_EXCEPTION("22203"),
	
	/**
	 * 请求参数错误 = 22204
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_ERROR("22204"),
	
	/**
	 * 请求参数类型错误 = 22005
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_TYPE_ERROR("22205"),
	
	/**
	 * 请求参数长度错误 = 22206
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_LENGTH_ERROR("22206"),
	
	/**
	 * 请求参数值错误 = 22207
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_VALUE_ERROR("22207"),
	
	/*****************************************************服务层*****************************************/
	
	/**
	 * 服务器异常  = 22403
	 */
	SERVICE_SERVER_EXCEPTION("22401"),
	
	/**
	 * 请求参数错误 = 22402
	 */
	SERVICE_REQUEST_PARAMETER_ERROR("22402"),
	
	/**
	 * 请求参数类型错误 = 22403
	 */
	SERVICE_REQUEST_PARAMETER_TYPE_ERROR("22403"),
	
	/**
	 * 请求参数长度错误 = 22404
	 */
	SERVICE_REQUEST_PARAMETER_LENGTH_ERROR("22404"),
	
	/**
	 * 请求参数值错误  = 22405
	 */
	SERVICE_REQUEST_PARAMETER_VALUE_ERROR("22405");
    
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
    private ApartmentCode(String enumValue) {
        this.enumValue = enumValue;
    }

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}
}
