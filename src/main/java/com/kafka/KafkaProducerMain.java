package com.kafka;

/**
 * @program: ParseService
 * @description:
 * @author: Adrian Liu
 * @create: 2018-07-26 09:56
 **/
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.utils.PathUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * 消息提供者
 */
public class KafkaProducerMain {
    /**
     * 消息发送
     */
    public void sendMessage(Map<String, String> topicMsg) throws FileNotFoundException {
        for (Map.Entry<String, String> entry : topicMsg.entrySet()) {
            String topic = entry.getKey();
            String message = entry.getValue();
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
            // 发送
            KafkaProducerSingleton.getInstance().send(record, new CallBackFuntion(topic, message));
//            producer.send(record, (recordMetadata, e) -> {
//                if (e != null) {
//                    System.err.println(topic + ": " + message + "--消息发送失败");
//                }else {
//                    System.err.println(topic + ": " + message + "--消息发送成功");
//                }
//            });
        }
        KafkaProducerSingleton.getInstance().flush();
//        producer.close();  暂时不关闭
    }


//    public void closeKfk(){
//        producer.close();
//    }
}
