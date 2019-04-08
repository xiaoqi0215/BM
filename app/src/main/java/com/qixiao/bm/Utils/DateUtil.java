package com.qixiao.bm.Utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.format.DateFormat;

import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String format(String longStr) {
        String rst;
        try {
            rst = (String) DateFormat.format("yyyy-MM-dd HH:mm:ss", Long.parseLong(longStr));
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format(Long time) {
        String rst;
        try {
            rst = DateFormat.format("yyyy-MM-dd HH:mm:ss", time).toString();
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String formatChinese(Long time) {
        String rst;
        try {
            rst = DateFormat.format("yyyy年MM月dd日", time).toString();
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String formatYM(Long time) {
        String rst;
        try {
            rst = DateFormat.format("yyyy-MM", time).toString();
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String formatYYDD(String longStr) {
        String rst;
        try {
            rst = (String) DateFormat.format("MM-dd", Long.parseLong(longStr));
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format2(Long longStr) {
        if (longStr != null) {
            java.text.DateFormat sdfDateTime = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
            return sdfDateTime.format(longStr);
        }
        return "";
    }

    public static String format3(String longStr) {
        String rst;
        try {
            rst = (String) DateFormat.format("yyyy-MM-dd", Long.parseLong(longStr));
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format3(long longStr) {
        String rst;
        try {
            rst = (String) DateFormat.format("yyyy-MM-dd", longStr);
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format4(String time) {
        String rst;
        try {
            rst = (String) DateFormat.format("yyyy-MM-dd HH:mm", Long.parseLong(time));
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format5(String time) {
        String rst;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(time);
            rst = (String) DateFormat.format("MM月dd日 HH:mm", date.getTime());
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format6(String time) {
        String rst;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(time);
            rst = (String) DateFormat.format("yyyy年MM月dd日 HH:mm", date.getTime());
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format7(String time) {
        String rst;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(time);
            rst = (String) DateFormat.format("MM/dd", date.getTime());
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format8(String time) {
        String rst;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(time);
            rst = (String) DateFormat.format("MM/dd", date.getTime());
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format9(String time) {
        String rst;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(time);
            rst = (String) DateFormat.format("yy/MM/dd", date.getTime());
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String format10(String time) {
        String rst;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(time);
            rst = (String) DateFormat.format("yy/MM/dd", date.getTime());
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    public static String formatCommon(String pattern, String newPattern, String time) {
        String rst;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(time);
            rst = (String) DateFormat.format(newPattern, date.getTime());
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

    /**
     * 判断两个日期是否同一年
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isSameYear(String time1, String time2) {
        boolean rst = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(sdf.parse(time1));
            calendar2.setTime(sdf.parse(time2));
            if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
                rst = true;
            } else {
                rst = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }

    //无时分秒的字符串
    public static boolean isSameYear2(String time1, String time2) {
        boolean rst = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(sdf.parse(time1));
            calendar2.setTime(sdf.parse(time2));
            if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
                rst = true;
            } else {
                rst = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }

    // 判断是否为同一天
    public static boolean isSameDay(long time1, long time2) {
        String strDay1 = DateFormat.format("yyyy-MM-dd", time1).toString();
        String strDay2 = DateFormat.format("yyyy-MM-dd", time2).toString();
        return strDay1.equals(strDay2);
    }

    public static Calendar formatToCalendar(long time) {
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String process(Long l) {
        if (l != null) {
            long now = Calendar.getInstance(Locale.US).getTimeInMillis();
            long difference = (now - l) / 1000;
            if (difference >= 172800) {// 大于两天
                return format2(l);
            } else if (difference >= 86400) {// 大于一天
                return "一天前 ";
            } else if (difference >= 3600) {// 大于一小时
                int hours = (int) (difference / 3600);
                return hours + "小时前";
            } else if (difference >= 60) {// 大于十分钟
                int min = (int) (difference / 60);
                return min + "分钟前";
            } else {
                return "刚刚";
            }
        }
        return "";
    }

    /**
     * Time conversion.
     *
     * @param duration ms.
     * @return such as: {@code 00:00:00}, {@code 00:00}.
     */
    @NonNull
    public static String convertDuration(long duration) {
        duration /= 1000;
        int hour = (int) (duration / 3600);
        int minute = (int) ((duration - hour * 3600) / 60);
        int second = (int) (duration - hour * 3600 - minute * 60);

        String hourValue = "";
        String minuteValue;
        String secondValue;
        if (hour > 0) {
            if (hour >= 10) {
                hourValue = Integer.toString(hour);
            } else {
                hourValue = "0" + hour;
            }
            hourValue += ":";
        }
        if (minute > 0) {
            if (minute >= 10) {
                minuteValue = Integer.toString(minute);
            } else {
                minuteValue = "0" + minute;
            }
        } else {
            minuteValue = "00";
        }
        minuteValue += ":";
        if (second > 0) {
            if (second >= 10) {
                secondValue = Integer.toString(second);
            } else {
                secondValue = "0" + second;
            }
        } else {
            secondValue = "00";
        }
        return hourValue + minuteValue + secondValue;
    }

    /**
     * 计算两个日期的时间差
     *
     * @param before
     * @param after
     * @return
     */
    public static int getDuration(Long before, Long after) {
        long time = Math.abs(after - before);

        int day = getDay(time);
        return day;
    }

    private static int getDay(long time) {
        int day = (int) (time / 1000 / 60 / 60 / 24);
        return day;
    }

    //与当前时间比较
    public static boolean compareTime(String t1) {
        if (TextUtils.isEmpty(t1)) {
            return false;
        }
        Date d1 = null;
        long between;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d1 = sdf.parse(t1);
            between = (d1.getTime() - new Date().getTime()) / 1000;//单位为秒
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            between = 0;
            return false;
        }
        // if (between<1800)
        if (Math.abs(between) < 180) {
            return true;
        } else {
            return false;
        }
    }

    public static String format11(String time) {
        String rst;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            date = sdf.parse(time);
            rst = (String) DateFormat.format("yyyy-MM-dd", date.getTime());
        } catch (Exception e) {
            Logger.e(e, e.getMessage());
            rst = "";
        }
        return rst;
    }

}