package com.hecc.lib.api;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author xuhoujun
 * @description: 接口请求封装
 * @date: Created In 下午2:09 on 2018/3/31.
 */
public abstract class HeccRequest<T extends HeccResponse> {

    // TODO: 2018/3/31 将URL信息放入配置文件当中 
    private static final String HECC_URL = "";
    private static final Logger logger = LoggerFactory.getLogger(HeccRequest.class);
    protected T response;

    protected abstract Class<T> getResponseClass();

    protected abstract String getApiName();

    protected abstract Map<String, String> getTextParams();

    public T getResponse() {
        String rsp = this.getResponseString();
        Class<T> clazz = getResponseClass();

        this.response = JSON.parseObject(rsp, clazz);

        Map<String, String> params = getTextParams();
        this.response.setParams(params);
        this.response.setBody(rsp);
        return this.response;
    }

    private String getResponseString() {
        return "";
      /*  Map<String, String> params = getTextParams();
        String apiName = getApiName();
        try {
            return WebUtils.doGet(HECC_URL + apiName, params);
        //    return WebUtil.doPost(HECC_URL + apiName, params);
        } catch (Exception ex) {
            logger.error("hecc api invoke error:", ex);
            return "{\n" +
                    "  \"errCode\": 500,\n" +
                    "  \"description\": \"hecc api invoke error\",\n" +
                    "  \"description_cn\": \"请求失败,请稍等重试\"\n" +
                    "}";
        }*/
    }
}
