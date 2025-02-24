package com.render.render.service;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class JsonModel {

    Map resultData=new HashMap();

    /** 애플리케이션에서 JsonObject 형태로 주고 받을 상태코드 */
    String resultMessage = "empty message";
    String resultCode = "";

    public Map getResultData() {
        return resultData;
    }
    public void setResultData(Map data) {
        this.resultData = data;
    }
    public void putResultData(String key, Object data){
        this.resultData.put(key, data);
    }
    public void putResultData(String key, Collection<Object> data){
        this.resultData.put(key, data);
    }
    public String getResultMessage() {
        return resultMessage;
    }
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

}
