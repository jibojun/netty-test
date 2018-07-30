package com.test.netty.client;

import com.test.netty.client.handlers.ClientInboundHandler;
import com.test.netty.common.BaseRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;
import java.util.UUID;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/30_11:14 PM
 */
public class ShortConnectionClient {
    private BaseRequest request;

    public static void main(String[] args) {
        ShortConnectionClient client = new ShortConnectionClient();
        client.buildRequest("client data");
        client.connectAndSendData();
    }

    private void buildRequest(String data) {
        this.request=new BaseRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setData(data);
    }

    private void connectAndSendData() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel)
                                throws Exception {
                            channel.pipeline()
                                    .addLast(new ClientInboundHandler());
                        }
                    }).option(ChannelOption.TCP_NODELAY, true);
            // connect and send request to server
            ChannelFuture future = bootstrap.connect(InetAddress.getLocalHost().getHostAddress(), 8085).sync();
            future.channel().writeAndFlush(request).sync();
            //wait client to close
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            //release thread pool resource
            group.shutdownGracefully();
        }
    }

}
