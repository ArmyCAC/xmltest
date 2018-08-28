package com.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;

/**
 * @program: ParseService
 * @description:
 * @author: Adrian Liu
 * @create: 2018-08-13 09:38
 **/

public class ConfigeUtils {
    //读取配置文件
    public static JsonObject getConfig(String name) throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        String classpath= PathUtils.getClassPath();
        //        String classpath=this.getClass().getResource("/").getPath();
        System.out.println("classpath:"+classpath);
        JsonElement parse = parser.parse(new FileReader(classpath+"/conf.json"));
        JsonObject jsonObject = parse.getAsJsonObject().getAsJsonObject(name);
        System.out.println(jsonObject);
        return jsonObject;
    }

    public static Properties getProperties(String sourceName) throws FileNotFoundException {
        JsonObject config = getConfig(sourceName);
        Properties properties = new Properties();

        for (Map.Entry<String, JsonElement> entry : config.entrySet()) {
            properties.put(entry.getKey(), entry.getValue().getAsString());
//            properties.put(entry.getKey(), entry.getValue());
        }
        return properties;
    }
}
