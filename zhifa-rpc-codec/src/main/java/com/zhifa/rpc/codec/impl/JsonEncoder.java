package com.zhifa.rpc.codec.impl;

import com.alibaba.fastjson.JSON;
import com.zhifa.rpc.codec.Encoder;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 10:47
 */
public class JsonEncoder implements Encoder {
    @Override
    public byte[] encode(Object object) {
        byte[] bytes = JSON.toJSONBytes(object);
        return bytes;
    }
}
