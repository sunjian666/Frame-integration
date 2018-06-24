package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    public static String dateToDateStr(){

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=format.format(date);

        return str;

    }

    public static String dateToDateStr(Date date){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=format.format(date);

        return str;

    }

    public static Map<String, String> getDayByDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> map = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
        map.put("endTime", sdf.format(cal.getTime()) + " 23:59:59");
        return map;
    }

    public static Map<String, String> getWeekByDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> map = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        map.put("date", sdf.format(cal.getTime()));
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        map.put("week", cal.get(Calendar.WEEK_OF_YEAR) + "");
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        map.put("startTime", sdf.format(cal.getTime()) + " 00:00:00");
        cal.add(Calendar.DATE, 6);
        map.put("endTime", sdf.format(cal.getTime()) + " 23:59:59");
        return map;
    }

    public static Map<String, String> getMonthByDate(){
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> map = new HashMap<String, String>();

        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        map.put("startTime", format.format(cale.getTime()) + " 00:00:00");

        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        map.put("endTime", format.format(cale.getTime()) + " 23:59:59");

        return map;
    }

    //字符串日期转毫秒数
    public static String getMsec(String date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 0;
        try {
            time = simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(time);

    }

}
