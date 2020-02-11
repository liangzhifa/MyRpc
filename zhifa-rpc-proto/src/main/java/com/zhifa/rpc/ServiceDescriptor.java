package com.zhifa.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-11 14:43
 *
 * 表示服务
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {

    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

}
