package com.utils;

import java.util.regex.Pattern;

/**
 * @program: ParseService
 * @description:
 * @author: Adrian Liu
 * @create: 2018-08-01 10:56
 **/

public  class StringUtils {
    //判断字符是否是数字
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
