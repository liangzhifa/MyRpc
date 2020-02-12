package com.zhifa.rpc.client.config;

import com.zhifa.rpc.Peer;
import com.zhifa.rpc.client.TransportSelector;
import com.zhifa.rpc.client.impl.RandomTransportSelector;
import com.zhifa.rpc.codec.Decoder;
import com.zhifa.rpc.codec.Encoder;
import com.zhifa.rpc.codec.impl.JsonDecoder;
import com.zhifa.rpc.codec.impl.JsonEncoder;
import com.zhifa.rpc.transport.TransportClient;
import com.zhifa.rpc.transport.impl.HttpTransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private Class<? extends TransportSelector> transportSelectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));




}
