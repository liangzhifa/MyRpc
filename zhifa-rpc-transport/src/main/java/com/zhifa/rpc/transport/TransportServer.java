package com.zhifa.rpc.transport;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 11:04
 *
 * 1.启动、监听
 * 2.接受请求
 * 3.关闭监听
 */
public interface TransportServer {

    void init(int port, RequestHandler requestHandler);

    void start();

    void stop();

}
