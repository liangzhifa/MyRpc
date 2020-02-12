package com.zhifa.rpc.client;

import com.zhifa.rpc.client.config.RpcClientConfig;
import com.zhifa.rpc.codec.Decoder;
import com.zhifa.rpc.codec.Encoder;
import com.zhifa.rpc.common.util.ReflectioUtils;

import java.lang.reflect.Proxy;

public class RpcClient {

    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectioUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectioUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectioUtils.newInstance(config.getTransportSelectorClass());
        this.selector.init(this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass());
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{clazz},new RemoteInvoker(clazz,encoder,decoder,selector));
    }
}
