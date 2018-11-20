package com.hecc.lib.exception;

/**
 * @author xuhoujun
 * @description: HECC自定义异常
 * @date: Created In 5:31 PM on 2018/11/8.
 */
public class HeccException extends RuntimeException{
    private Integer resCode;
    private String msg;
    private Object data;

    public HeccException(Integer redCodse,String msg) {
        super();
        this.resCode = redCodse;
        this.msg = msg;
        this.data = new Object();
    }

    public HeccException(Integer resCode,String msg,Object object){
        super();
        this.resCode = resCode;
        this.msg = msg;
        this.data = object;
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
