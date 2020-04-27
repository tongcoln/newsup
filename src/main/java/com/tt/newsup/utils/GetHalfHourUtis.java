package com.tt.newsup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：tt
 * @date ：Created in 2020/4/24 10:06 下午
 * @description：获取半个小时前的时间
 * @modified By：
 * @version:
 */
public class GetHalfHourUtis {
    public static Date getHour(){
        long currentTime = System.currentTimeMillis() ;
        currentTime -=30*60*1000;
        Date date=new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        return date;
    }
}
