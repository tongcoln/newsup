package com.tt.newsup.utils;

import java.util.UUID;

/**
 * @author ：tt
 * @date ：Created in 2020/4/28 3:38 下午
 * @description：UUID
 * @modified By：
 * @version:
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
