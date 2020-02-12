package com.zhifa.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 13:05
 *
 * 表示一个具体服务
 */
@Data
@AllArgsConstructor
public class ServiceInstance {

    private Object target;
    private Method method;

}
