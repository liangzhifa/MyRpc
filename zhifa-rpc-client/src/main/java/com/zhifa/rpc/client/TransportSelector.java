package com.zhifa.rpc.client;

import com.zhifa.rpc.Peer;
import com.zhifa.rpc.transport.TransportClient;

import java.util.List;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 17:28
 */
public interface TransportSelector {

    /**
     * 初始化selector
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers,int count,Class<? extends TransportClient> clazz);

    /**
     *  选择一个transport与server做交互
     * @return  wangl网络连接
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    void close();

}
