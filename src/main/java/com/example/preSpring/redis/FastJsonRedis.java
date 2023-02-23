package com.example.preSpring.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * redis 使用fastjson 序列化
 *
 *
 * @author 小叶子
 */
public class FastJsonRedis<T> implements RedisSerializer<T> {
    private ObjectMapper objectMapper = new ObjectMapper();

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastJsonRedis(Class<T> clazz){
        this.clazz = clazz;
    }

    static{
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(t==null){
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null|| bytes.length<=0){
            return null;
        }
        String str= new String(bytes,DEFAULT_CHARSET);
        return  JSON.parseObject(str,clazz);
    }

    public void setObjectMapper(ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
    }
}

