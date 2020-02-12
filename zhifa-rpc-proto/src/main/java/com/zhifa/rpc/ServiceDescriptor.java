package com.zhifa.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

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

    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());

        Class<?>[] parameterClass = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClass.length];
        for (int i = 0; i < parameterClass.length; i++) {
            parameterTypes[i] = parameterClass[i].getName();
        }
        serviceDescriptor.setParameterTypes(parameterTypes);

        return serviceDescriptor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceDescriptor that = (ServiceDescriptor) o;

        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (returnType != null ? !returnType.equals(that.returnType) : that.returnType != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (returnType != null ? returnType.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }
}
