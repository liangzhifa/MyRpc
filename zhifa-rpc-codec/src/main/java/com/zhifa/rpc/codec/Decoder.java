package com.zhifa.rpc.codec;

/**
 * 反序列化
 * @author lzf
 * @version 1.0
 * @date 2020-02-11 16:10
 */
public interface Decoder {

    <T> T decode(byte[] bytes, Class<T> clazz);

}


