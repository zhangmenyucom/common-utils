package com.taylor.api.common.vo;

import java.io.Serializable;

/**
 * @notes:  Vo基础类
 * @author: kuta.li
 * @date: 2015年9月21日-下午1:39:42
 */
public class BaseVo implements Serializable{
    
    private static final long serialVersionUID = -8148336982612104798L;

    private Integer resultCode;
    
    private String resultMessage;
    
    public BaseVo(){
        super();
    };
    
    public BaseVo(Integer resultCode) {
        super();
        this.resultCode = resultCode;
    }
    
    public BaseVo(Integer resultCode, String resultMessage) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

}
