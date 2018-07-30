package com.test.netty.server.handlers;

import com.test.netty.common.BaseRequest;
import com.test.netty.common.BaseResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/30_11:14 PM
 */
public class ServerInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BaseRequest request=(BaseRequest)msg;
        System.out.println("server received data:"+request.getData()+ ",request id is:"+request.getRequestId());
        BaseResponse response=new BaseResponse();
        response.setRequestId(request.getRequestId());
        response.setResult("success");
        response.setError("no error");
        ctx.write(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
        ctx.close();
    }
}
