package com.zhifa.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 11:09
 */
public interface RequestHandler {

    void onRequest(InputStream recive, OutputStream toResp);
}
