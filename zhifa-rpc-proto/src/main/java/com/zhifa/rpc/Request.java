package com.zhifa.rpc;

import lombok.Data;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-11 14:48
 *表示rpc的一个请求
 *
 */
@Data
public class Request {

    private ServiceDescriptor service;
    private Object[] parameters;
}
