package com.test.netty.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/30_11:14 PM
 */
public class ServerOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("server is sending response back to client side");
        System.out.println(msg.toString());
        super.write(ctx, msg, promise);
    }
}
