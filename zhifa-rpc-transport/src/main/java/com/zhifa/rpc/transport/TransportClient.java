package com.zhifa.rpc.transport;

import com.zhifa.rpc.Peer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 11:01
 * <p>
 * 1.创建连接
 * 2.发送数据，等待响应
 * 3.关闭连接
 */
public interface TransportClient {

    void connet(Peer peer);

    InputStream write(InputStream data) throws IOException;

    void close();

}
