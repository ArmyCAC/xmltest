package com.kafka;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.utils.PathUtils;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @program: ParseService
 * @description:
 * @author: Adrian Liu
 * @create: 2018-08-07 10:01
 **/

public class KafkaProducerSingleton {

    private static KafkaProducer<String, String> producer;


    private KafkaProducerSingleton() throws FileNotFoundException {
        //建kafka生产者
        Properties properties = getProperties("producer");
        this.producer = new KafkaProducer<String, String>(properties);
    }

    public static KafkaProducer getInstance() throws FileNotFoundException {
        if(producer==null){
            KafkaProducerSingleton kafkaProducerSingleton=new KafkaProducerSingleton();
            producer=kafkaProducerSingleton.getProducer();
        }
        return producer;
    }

    public KafkaProducer<String, String> getProducer() {
        return producer;
    }

    public void flush() throws FileNotFoundException {
        producer.flush();
    }


    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, String> map = new HashMap();
        KafkaProducerMain main=new KafkaProducerMain();
        map.put("base-station-info", "send message to kafka from producer");
        for (int i = 0; i < 30; i++ ) {
            main.sendMessage(map);
        }
    }

    //读取配置文件
    public JsonObject getConfig(String name) throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        String classpath= PathUtils.getClassPath();
        JsonElement parse = parser.parse(new FileReader(classpath+"/kafka.json"));
        JsonObject jsonObject = parse.getAsJsonObject().getAsJsonObject(name);
        System.out.println(jsonObject);
        return jsonObject;
    }

    public Properties getProperties(String sourceName) throws FileNotFoundException {
        JsonObject config = getConfig(sourceName);
        Properties properties = new Properties();

        for (Map.Entry<String, JsonElement> entry : config.entrySet()) {
            properties.put(entry.getKey(), entry.getValue().getAsString());
        }
        return properties;
    }

}
