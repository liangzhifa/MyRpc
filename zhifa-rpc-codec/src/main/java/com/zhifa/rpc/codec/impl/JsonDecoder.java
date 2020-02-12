package com.zhifa.rpc.codec.impl;

import com.alibaba.fastjson.JSON;
import com.zhifa.rpc.codec.Decoder;

/**
 * 基于json的反序列化
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 10:49
 */
public class JsonDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
