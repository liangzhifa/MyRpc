package com.zhifa.rpc.demo;

import com.zhifa.rpc.demo.impl.CalcServiceImpl;
import com.zhifa.rpc.server.RpcServer;

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();

    }
}
