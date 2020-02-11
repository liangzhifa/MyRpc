package com.zhifa.rpc.codec;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-11 15:33
 * 序列化
 */
public interface Encoder {

    byte[] encode(Object object);

}
