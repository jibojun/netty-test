package com.test.netty.serialization;

import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

/**
 * @Author: Bojun Ji
 * @Description: byte[]->object
 * @Date: 2018/6/30_6:30 PM
 */
public class ProtoStuffObjectInput {
    private final Schema schema;
    private final byte[] data;
    private static Objenesis objenesis = new ObjenesisStd(true);

    public ProtoStuffObjectInput(Class cls, byte[] data) {
        this.schema = RuntimeSchema.getSchema(cls);
        this.data = data;
    }

    public Object readObject(Object object) {
        try {
            ProtostuffIOUtil.mergeFrom(this.data, object, this.schema);
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public <T> T readObject(Class<T> cls) {
        T object = null;
        try {
            //create instance, but need to avoid situation that no constructor method without any parameter
            object = objenesis.newInstance(cls);
            ProtostuffIOUtil.mergeFrom(this.data, object, this.schema);
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }
}
