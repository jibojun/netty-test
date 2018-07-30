package com.test.netty.common;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/30_11:16 PM
 */
public class BaseResponse {
    private String requestId;
    private Object result;
    private Object error;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "request id:"+requestId+",result:"+result+",error:"+error;
    }
}
