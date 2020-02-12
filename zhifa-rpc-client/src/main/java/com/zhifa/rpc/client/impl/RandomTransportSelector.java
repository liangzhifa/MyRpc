package com.zhifa.rpc.client.impl;

import com.zhifa.rpc.Peer;
import com.zhifa.rpc.client.TransportSelector;
import com.zhifa.rpc.common.util.ReflectioUtils;
import com.zhifa.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector {

    private List<TransportClient> clients;

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {

        count = Math.max(count, 1);
        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectioUtils.newInstance(clazz);
                client.connet(peer);
                clients.add(client);
            }
            log.info("connect server : {} ",peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        clients.forEach(TransportClient::close);
        clients.clear();
    }

    public RandomTransportSelector() {
        this.clients = new ArrayList<>();
    }
}
