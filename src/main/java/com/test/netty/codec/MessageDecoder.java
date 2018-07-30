package com.test.netty.codec;

import com.test.netty.serialization.ProtoStuffObjectInput;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/31_12:08 AM
 */
public class MessageDecoder extends ByteToMessageDecoder {
    private Class<?> genericClass;

    public MessageDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //mark index
        in.markReaderIndex();
        int dataSize = in.readInt();
        //check data size
        if (dataSize <= 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataSize) {
            //reposition to marked index
            in.resetReaderIndex();
        }
        //deserialization
        byte[] data = new byte[dataSize];
        in.readBytes(data);
        ProtoStuffObjectInput input = new ProtoStuffObjectInput(genericClass, data);
        Object obj = input.readObject(genericClass);
        out.add(obj);
    }
}
