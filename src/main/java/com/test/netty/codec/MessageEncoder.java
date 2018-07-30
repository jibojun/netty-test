package com.test.netty.codec;

import com.test.netty.serialization.ProtoStuffObjectOutput;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/31_12:08 AM
 */
public class MessageEncoder extends MessageToByteEncoder {
    private Class<?> genericClass;

    public MessageEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        //serialization
        if (genericClass.isInstance(msg)) {
            ProtoStuffObjectOutput output = new ProtoStuffObjectOutput(msg);
            byte[] data = output.writeObjectAndReturn();
            out.writeBytes(data);
            out.writeInt(data.length);
        }
    }
}
