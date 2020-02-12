package com.zhifa.rpc.client;

import com.zhifa.rpc.Request;
import com.zhifa.rpc.Response;
import com.zhifa.rpc.ServiceDescriptor;
import com.zhifa.rpc.codec.Decoder;
import com.zhifa.rpc.codec.Encoder;
import com.zhifa.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker() {
    }

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: "+response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {

        TransportClient client = null;
        Response response = null;

        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(revice, revice.available());
            response = decoder.decode(inBytes, Response.class);

        } catch (Exception e) {
            log.warn(e.getMessage(),e);
            response = new Response();
            response.setCode(1);
            response.setMessage(" RpcClient got error :"+e.getClass()+" :"+e.getMessage());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }

        return response;
    }
}
