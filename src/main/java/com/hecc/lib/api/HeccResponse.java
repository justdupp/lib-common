package com.hecc.lib.api;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Map;

/**
 * @Auther xuhoujun
 * @Description: 接口响应封装
 * @Date: Created In 下午2:17 on 2018/3/31.
 */
public class HeccResponse implements Serializable {

    @JSONField(name = "errCode")
    private String errorCode;

    @JSONField(name = "description")
    private String description;

    private String body;

    private Map<String, String> params;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public boolean isSuccess() {
        return "0".equals(this.errorCode);
    }
}
