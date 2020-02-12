package com.zhifa.rpc.server;

import com.zhifa.rpc.codec.Decoder;
import com.zhifa.rpc.codec.Encoder;
import com.zhifa.rpc.codec.impl.JsonDecoder;
import com.zhifa.rpc.codec.impl.JsonEncoder;
import com.zhifa.rpc.transport.TransportServer;
import com.zhifa.rpc.transport.impl.HttpTransportServer;
import lombok.Data;

/**
 * @author lzf
 * @version 1.0
 * @date 2020-02-12 12:47
 *
 * rpc fuw服务配置类
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;

    private Class<? extends Encoder> encoderClass = JsonEncoder.class;

    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private int port = 3000;

}
