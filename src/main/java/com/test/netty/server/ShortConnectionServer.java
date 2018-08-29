package com.test.netty.server;

import com.test.netty.codec.MessageDecoder;
import com.test.netty.codec.MessageEncoder;
import com.test.netty.common.BaseRequest;
import com.test.netty.common.BaseResponse;
import com.test.netty.server.handlers.ServerInboundHandler;
import com.test.netty.server.handlers.ServerOutboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetAddress;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/30_11:13 PM
 */
public class ShortConnectionServer {

    private static final int PORT = 8085;

    public static void main(String[] args) {
        new ShortConnectionServer().startServer();
    }

    private void startServer() {
        //boss thread pool and working thread pool
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                //nio,non blocking
                .channel(NioServerSocketChannel.class)
                //connection number
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // add handlers to pipeline for inbound and outbound
                        ChannelPipeline pipeline = sc.pipeline();
                        pipeline.addLast("1", new MessageDecoder(BaseRequest.class));
                        pipeline.addLast("2", new ServerInboundHandler());//inbound
                        pipeline.addLast("3", new MessageEncoder(BaseResponse.class));
                        pipeline.addLast("4", new ServerOutboundHandler());//outbound
                    }
                });

        try {
            //bind
            ChannelFuture f = b.bind(InetAddress.getLocalHost().getHostAddress(), PORT).sync();
            //wait for listened port to close
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            //release resource from thread pool
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
