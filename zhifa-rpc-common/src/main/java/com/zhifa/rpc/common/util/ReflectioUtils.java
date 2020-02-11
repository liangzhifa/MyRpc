package com.zhifa.rpc.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-11 14:54
 * 反射工具类
 */
public class ReflectioUtils {

    /**
     * fanzhe反射创建对象
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    /**
     * 获取公有方法
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pMethods = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                pMethods.add(method);
            }
        }
        return pMethods.toArray(new Method[0]);
    }

    /**
     * dia调用指定对象的指定方法
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object object,
                                Method method,
                                Object... args) {
        try {
            return method.invoke(object, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
