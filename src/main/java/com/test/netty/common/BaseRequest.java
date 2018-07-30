package com.test.netty.common;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/30_11:16 PM
 */
public class BaseRequest {
    private String requestId;
    private Object data;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
