package com.kafka;

import java.util.Properties;

public class SendMsg {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("metadata.broker.list", "localhost:9092");

    }
}
