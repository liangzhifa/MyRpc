package com.zhifa.rpc.common.util;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-11 15:19
 */
public class ReflectioUtilsTest {

    @Test
    public void newInstance() {
        TestClass testClass = ReflectioUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void getPublicMethods() {
        Method[] publicMethods = ReflectioUtils.getPublicMethods(TestClass.class);
        assertEquals(1, publicMethods.length);

        String name = publicMethods[0].getName();
        assertEquals("c",name);
    }

    @Test
    public void invoke() {
        Method[] publicMethods = ReflectioUtils.getPublicMethods(TestClass.class);
        Method c = publicMethods[0];
        TestClass testClass = new TestClass();
        Object r = ReflectioUtils.invoke(testClass, c);
        assertEquals("c",r);
    }
}