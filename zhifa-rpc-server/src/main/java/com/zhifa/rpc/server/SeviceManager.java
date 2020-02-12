package com.zhifa.rpc.server;

import com.zhifa.rpc.Request;
import com.zhifa.rpc.ServiceDescriptor;
import com.zhifa.rpc.common.util.ReflectioUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 13:06
 * <p>
 * guanli管理rpc暴露的服务  注册服务 查找服务
 */
@Slf4j
public class SeviceManager {

    private Map<ServiceDescriptor, ServiceInstance> service;

    public SeviceManager() {
        this.service = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectioUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance instance = new ServiceInstance(bean, method);
            ServiceDescriptor descriptor = ServiceDescriptor.from(interfaceClass, method);
            service.put(descriptor, instance);

            log.info("register service......{}",service);
        }
    }

    public ServiceInstance lookup(Request request) {
        ServiceDescriptor serviceDescriptor = request.getService();
        return service.get(serviceDescriptor);
    }
}
