package com.zhifa.rpc.server;

import com.zhifa.rpc.Request;
import com.zhifa.rpc.common.util.ReflectioUtils;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 16:43
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance service, Request request) {

        return ReflectioUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }

}
