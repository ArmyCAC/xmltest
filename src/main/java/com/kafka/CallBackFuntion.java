package com.kafka;

/**
 * @program: ParseService
 * @description:
 * @author: Adrian Liu
 * @create: 2018-07-26 10:40
 **/

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * 回掉函数
 */
public class CallBackFuntion implements Callback {

    private String topic;
    private String message;

    public CallBackFuntion(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }

//    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
            System.out.println(topic + ": " + message + "--消息发送失败");
        }else {
            System.out.println(topic + ": " + message + "--消息发送成功");
        }
    }
}