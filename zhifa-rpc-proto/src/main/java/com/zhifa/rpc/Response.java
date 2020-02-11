package com.zhifa.rpc;

import lombok.Data;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-11 14:50
 * <p>
 * 表示rpc的一个fanhui返回
 */
@Data
public class Response {

    /**
     * fuwu 服务返回编码 0-success 非 0 就是失败
     */
    private int code = 0;
    /**
     * 返回的具体提示信息
     */
    private String message = "ok";
    /**
     * 返回的数据
     */
    private Object data;
}
