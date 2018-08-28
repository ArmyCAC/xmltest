package com.utils;

import java.net.URLDecoder;

/**
 * @program: ParseService
 * @description:
 * @author: Adrian Liu
 * @create: 2018-08-01 15:30
 **/

public class PathUtils {

    public static String getClassPath(){
        PathUtils p=new PathUtils();
        String classpath=p.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            classpath = URLDecoder.decode(classpath, "utf-8");// 转化为utf-8编码
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (classpath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
            // 截取路径中的jar包名
            classpath = classpath.substring(0, classpath.lastIndexOf("/") + 1);
        }
        return classpath;
    }
}
