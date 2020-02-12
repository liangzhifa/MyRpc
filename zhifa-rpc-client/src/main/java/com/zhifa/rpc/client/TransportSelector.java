package com.zhifa.rpc.client;

import com.zhifa.rpc.transport.TransportClient;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 17:28
 */
public interface TransportSelector {

    /**
     *  选择一个transport与server做交互
     * @return  wangl网络连接
     */
    TransportClient select();

    void release(TransportClient client);

}
