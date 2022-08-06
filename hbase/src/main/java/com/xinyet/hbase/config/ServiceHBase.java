package com.xinyet.hbase.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@org.springframework.context.annotation.Configuration
public class ServiceHBase {
    @Value("${hbase.zookeeper.quorum}")
    private String zookeeperQuorum;

    @Value("${hbase.zookeeper.property.clientPort}")
    private String clientPort;

    @Bean("myConnection")
    public Connection getCon() {
        Configuration entries = HBaseConfiguration.create();
        entries.set("hbase.zookeeper.quorum", zookeeperQuorum);
        entries.set("hbase.zookeeper.property.clientPort", clientPort);
        try {
            return ConnectionFactory.createConnection(entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
