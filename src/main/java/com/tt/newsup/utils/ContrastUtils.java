package com.tt.newsup.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：tt
 * @date ：Created in 2020/5/14 9:50 下午
 * @description：对比工具类,对比站厅的时间的
 * @modified By：
 * @version:
 */
public class ContrastUtils {

    public  static Boolean contrastDateUtil(String storeDate){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date2 = sdf.format(new Date());
        if (storeDate.equals(date2)){
            return true;
        }else{
            return false;
        }

    }

    public static Boolean contrastTimeUtil(String storeTime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 设置日期格式
        Date now = null;
        Date beginTime = null;
        Date endTime = null;
        now = df.parse(df.format(new Date()));
        if ("上午".equals(storeTime)){
            beginTime = df.parse("00:00");
            endTime = df.parse("12:00");
        }else{
            beginTime = df.parse("12:00");
            endTime = df.parse("24:00");
        }

        Calendar date = Calendar.getInstance();
        date.setTime(now);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }

    }

    public static String retrunTimeString(String storeDate,String storeTime) throws ParseException {

//        StringBuffer sb= new StringBuffer();
//        String s= "2020-05-14";
//        String shangwu = "00:00:00";
//        String xiawu ="12:00:00";
//
//        sb = sb.append(s).append(" ").append(shangwu);
//        String sb1 = sb+"";
//        System.out.println(sb1);
//        SimpleDateFormat simdate1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date date1 =  simdate1.parse(sb1);

        StringBuffer sb = new StringBuffer();
        String storeStemptime = null;
        if ("上午".equals(storeTime)){
            storeStemptime = "00:00:00";
        }else{
            storeStemptime = "12:00:00";
        }

        sb = sb.append(storeDate).append(" ").append(storeStemptime);
        String sb1 = sb+"";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endtime = sdf.parse(sb1);
        Date now = sdf.parse(sdf.format(new Date()));
        long day = 0;

        long hour = 0;

        long min = 0;

        long sec = 0;

        long diff = 0;

        long nowtime = now.getTime();
        long longendtime = endtime.getTime();
        diff = longendtime - nowtime;

        day = diff/(24*60*60*1000);

        hour = diff/(60*60*1000) - day*24;

        min = diff/(60*1000) - day*24*60 - hour*60;

        sec = diff/1000-day*24*60*60-hour*60*60-min*60;

        long retrunHour = day*24 +hour;

        System.out.println(retrunHour+"*"+min+"*"+sec) ;

        return retrunHour+"*60*60*1000+"+min+"*60*1000+"+sec+"*1000";

    }


}
