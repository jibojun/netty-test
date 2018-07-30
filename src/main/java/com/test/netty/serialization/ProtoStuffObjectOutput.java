package com.test.netty.serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @Author: Bojun Ji
 * @Description: object->byte[]
 * @Date: 2018/6/30_6:30 PM
 */
public class ProtoStuffObjectOutput {
    private final Schema schema;
    private final Object object;
    private final LinkedBuffer buffer = LinkedBuffer.allocate(1024);

    public ProtoStuffObjectOutput(Object object) {
        this.object = object;
        this.schema = RuntimeSchema.getSchema(object.getClass());
    }

    public void writeObject() {
        try {
            LinkedBuffer buffer = LinkedBuffer.allocate(1024);
            ProtobufIOUtil.writeTo(this.buffer, this.object, this.schema);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            buffer.clear();
        }
    }

    public byte[] writeObjectAndReturn() {
        try {
            LinkedBuffer buffer = LinkedBuffer.allocate(1024);
            return ProtostuffIOUtil.toByteArray(this.object, this.schema, this.buffer);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            buffer.clear();
        }
    }
}
