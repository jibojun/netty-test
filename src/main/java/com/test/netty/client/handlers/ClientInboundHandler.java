package com.test.netty.client.handlers;

import com.test.netty.common.BaseResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/30_11:15 PM
 */
public class ClientInboundHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        BaseResponse response=(BaseResponse)msg;
        System.out.println("client received server side's response!");
        System.out.println(response.toString());
    }
}
