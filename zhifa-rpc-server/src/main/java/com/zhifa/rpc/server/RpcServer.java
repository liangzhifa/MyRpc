package com.zhifa.rpc.server;

import com.zhifa.rpc.Request;
import com.zhifa.rpc.Response;
import com.zhifa.rpc.codec.Decoder;
import com.zhifa.rpc.codec.Encoder;
import com.zhifa.rpc.common.util.ReflectioUtils;
import com.zhifa.rpc.transport.RequestHandler;
import com.zhifa.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 16:48
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private SeviceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer() {

        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflectioUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        this.encoder = ReflectioUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectioUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new SeviceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(recive, recive.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request: {}", request);
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(serviceInstance, request);
                response.setData(ret);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("RPCservcer got  error : " + e.getClass().getName() + e.getMessage());
            }finally {
                byte[] outByte = encoder.encode(response);
                log.info("respone clent....");
                try {
                    toResp.write(outByte);
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }

        }
    };
}
