package com.taylor.api.common.enums;

/**
 * @notes:异常类code定义
 * @author henry
 * 2015年11月13日		下午4:44:23
 */
public enum InsureCode {
	
	

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
	 * 请求已重定向  = 28001
	 */
	CONTROLLER_FRONT_REQUEST_HAS_BEEN_REDIRECTED("28001"),
	
	/**
	 * 客户端错误  = 28002
	 */
	CONTROLLER_FRONT_CLIENT_ERROR("28002"),
	
	/**
	 * 服务器异常  = 28003
	 */
	CONTROLLER_FRONT_SERVER_EXCEPTION("28003"),
	
	/**
	 * 请求参数错误 = 28004
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_ERROR("28004"),
	
	/**
	 * 请求参数类型错误 = 28005
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_TYPE_ERROR("28005"),
	
	/**
	 * 请求参数长度错误 = 28006
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_LENGTH_ERROR("28006"),
	
	/**
	 * 请求参数值错误 = 28007
	 */
	CONTROLLER_FRONT_REQUEST_PARAMETER_VALUE_ERROR("28007"),
	
/*****************************************************控制层后台**********************************************/
	
	/**
	 * 请求已重定向  = 28201
	 */
	CONTROLLER_ADMIN_REQUEST_HAS_BEEN_REDIRECTED("28201"),
	
	/**
	 * 客户端错误  = 28202
	 */
	CONTROLLER_ADMIN_CLIENT_ERROR("28202"),
	
	/**
	 * 服务器异常  = 28203
	 */
	CONTROLLER_ADMIN_SERVER_EXCEPTION("28203"),
	
	/**
	 * 请求参数错误 = 28204
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_ERROR("28204"),
	
	/**
	 * 请求参数类型错误 = 28005
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_TYPE_ERROR("28205"),
	
	/**
	 * 请求参数长度错误 = 28206
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_LENGTH_ERROR("28206"),
	
	/**
	 * 请求参数值错误 = 28207
	 */
	CONTROLLER_ADMIN_REQUEST_PARAMETER_VALUE_ERROR("28207"),
	
	/*****************************************************服务层*****************************************/
	
	/**
	 * 服务器异常  = 28403
	 */
	SERVICE_SERVER_EXCEPTION("28401"),
	
	/**
	 * 请求参数错误 = 28402
	 */
	SERVICE_REQUEST_PARAMETER_ERROR("28402"),
	
	/**
	 * 请求参数类型错误 = 28403
	 */
	SERVICE_REQUEST_PARAMETER_TYPE_ERROR("28403"),
	
	/**
	 * 请求参数长度错误 = 28404
	 */
	SERVICE_REQUEST_PARAMETER_LENGTH_ERROR("28404"),
	
	/**
	 * 请求参数值错误  = 28405
	 */
	SERVICE_REQUEST_PARAMETER_VALUE_ERROR("28405");
    
    /**
     * 枚举值
     */
    private String enumValue;
    
    /**
     * 构造函数，枚举类型只能为私有
     * @param enumValue
     * @author: henry
     * 2015年11月13日		下午4:28:41
     */
    private InsureCode(String enumValue) {
        this.enumValue = enumValue;
    }

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}
}
