package com.siokagami.application.mytomato.utils;

import android.databinding.BindingConversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParseUtil {

    public static final long minute = 1000 * 60;
    public static final long hour = minute * 60;
    public static final long day = hour * 24;
    public static final long month = day * 30;
    public static final long year = month * 12;
    private static final String DAY_DESC = "天";
    private static final String HOUR_DESC = "时";
    private static final String MINUTE_DESC = "分";
    private static final String SECOUND_DESC = "秒";

    public static final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static final String[] weekDaysForShort = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    public static final String[] weekDaysAbb = {"日", "一", "二", "三", "四", "五", "六"};

    public static final String[] monthAbb = {"Jan.", "Feb.", "Mar.", "Apr.", "May.", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec."};

    public static final String[] monthWords = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十月", "十二月",};

    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
    private final static String[] constellationArr = new String[]{"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};


    public static String parse2mean(Date date) {

        return parse2mean(date, "yyyy-MM-dd");
    }

    public static String parseCourseTime(Date date) {
        Calendar nowCalender = Calendar.getInstance();
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(date);

        long now = System.currentTimeMillis();
        long diffValue = date.getTime() - now;

        StringBuilder result = new StringBuilder();
        if (diffValue > 0) {
            if (startTime.get(Calendar.YEAR) != nowCalender.get(Calendar.YEAR)) {
                return dateFormate(date, "yyyy-MM-dd HH:mm");
            } else if (startTime.get(Calendar.DAY_OF_MONTH) == nowCalender.get(Calendar.DAY_OF_MONTH)) {
                result.append("今天 ");
            } else if (startTime.get(Calendar.DAY_OF_MONTH) - nowCalender.get(Calendar.DAY_OF_MONTH) == 1) {
                result.append("明天 ");
            } else {
                result.append(dateFormate(date, "yyyy-MM-dd "));
            }
            result.append(dateFormate(date, "HH:mm"));
            return result.toString();
        } else {
            return parse2mean(date, "yyyy-MM-dd HH:mm");
        }
    }

    public static String parse2mean(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }

        String result = "刚刚";
        long now = System.currentTimeMillis();
        long diffValue = now - date.getTime();

        long yearC = diffValue / year;
        long monthC = diffValue / month;
        long weekC = diffValue / (7 * day);
        long dayC = diffValue / day;
        long hourC = diffValue / hour;
        long minC = diffValue / minute;

        if (yearC < 1 && dayC > 2) {
            result = dateFormate(date, "MM-dd");
        } else if (dayC > 2) {
            result = dateFormate(date, "yyyy-MM-dd");
        } else if (dayC >= 1 && dayC <= 2) {
            result = dayC + "天前";
        } else if (hourC >= 1) {
            result = hourC + "小时前";
        } else if (minC >= 1) {
            result = minC + "分钟前";
        }
//        if (monthC >= 1) {
//            result = monthC + "个月前";
//        } else if (weekC >= 1) {
//            result = weekC + "周前";
//        }
        return result;
    }

    /**
     * time的单位是秒
     *
     * @param time
     * @return
     */
    public static String parse60(long time) {
        long hour = time / 3600;
        long min = (time - hour * 3600) / 60;
        long second = time - hour * 3600 - min * 60;
        StringBuilder result = new StringBuilder();
        if (hour > 0) {
            if (hour >= 10)
                result.append(hour);
            else
                result.append("0" + hour);

            result.append(":");
        }

        if (min < 10)
            result.append("0" + min);
        else
            result.append(min);

        result.append(":");

        if (second < 10)
            result.append("0" + second);
        else
            result.append(second);


        return result.toString();
    }

    public static String dateFormate(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formate.format(date);
    }

    public static String dateFormateYmdHm(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formate.format(date);
    }

    public static String dateFormateYmdHm2(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return formate.format(date);
    }

    public static String dateFormatYmdHm(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm");
        return formate.format(date);
    }
    @BindingConversion
    public static String dateFormatStringmd(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("MM月dd日");
        return formate.format(date);
    }


    public static String dateFormatStringYmd(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("yyyy年MM月dd日");
        return formate.format(date);
    }

    public static String dateFormatMdHm(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("MM-dd HH:mm");
        return formate.format(date);
    }

    public static String dateFormatHm(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("HH:mm");
        return formate.format(date);
    }

    public static String dateFormatStringMdHm(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat("MM月dd日 HH:mm");
        return formate.format(date);
    }


    public static String dateFormate(Date date, String format) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        java.text.DateFormat formate = new java.text.SimpleDateFormat(format);
        return formate.format(date);
    }

    public static Date stringToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String stringToDateString(String date, String oldFormat, String currentFormat) {
        Date dateTime = stringToDate(date, oldFormat);
        if (dateTime != null) {
            return dateFormate(dateTime, currentFormat);
        } else {
            return StringUtils.EMPTY;
        }


    }

    public static String dateFormatTransform(String data, String inputFormat, String outputForamt) {
        Date date = stringToDate(data, inputFormat);
        return dateFormate(date, outputForamt);
    }

    /*
    **将 ms 秒转换为
    * 天时分 或
    * 时分秒
     */
    public static String millSecFormat(long millSec) {
        if ((millSec / day) < 1) {
            return millSec2HourMinSec(millSec);
        } else {
            return millSec2DayHourMin(millSec);
        }
    }

    /*
     *将 ms转换为 hh:mm:ss 或者 dd:hh:mm 格式
     */
    public static String millis2DDHHMMSS(long sec) {
        if ((sec / day) < 1) {
            return sec2HHMMSS(sec);
        } else {
            return sec2DDHHMMSS(sec);
        }
    }

    /**
     * 将 毫秒 转为 天时分
     *
     * @param millSecond
     * @return
     */
    public static String millSec2DayHourMin(long millSecond) {
        StringBuilder builder = new StringBuilder();
        long dd = millSecond / day;
        long hr = (millSecond - dd * day) / hour;
        long min = (millSecond - dd * day - hr * hour) / minute;
        return builder.append(numFormatDate(dd)).append(DAY_DESC).append(numFormatDate(hr)).append(HOUR_DESC)
                .append(numFormatDate(min)).append(MINUTE_DESC).toString();
    }

    /**
     * 将毫秒转化为DD:HH:MM:SS
     */
    public static String sec2DDHHMMSS(long sec) {
        StringBuilder builder = new StringBuilder();
        long dd = sec / day;
        long hr = (sec - dd * day) / hour;
        long min = (sec - dd * day - hr * hour) / minute;
        long second = (sec - dd * day - hr * hour - min * minute) / 1000;
        return builder.append(numFormatDate(dd)).append(":").append(numFormatDate(hr)).append(":")
                .append(numFormatDate(min)).append(":").append(numFormatDate(second)).toString();
    }

    /**
     * 将毫秒转为 时分秒
     *
     * @param millSecond
     * @return
     */
    public static String millSec2HourMinSec(long millSecond) {
        StringBuilder builder = new StringBuilder();
        long hr = millSecond / hour;
        long min = (millSecond - hr * hour) / minute;
        long second = (millSecond - hr * hour - min * minute) / 1000;
        return builder.append(numFormatDate(hr)).append(HOUR_DESC).append(numFormatDate(min)).append(MINUTE_DESC)
                .append(numFormatDate(second)).append(SECOUND_DESC).toString();
    }

    public static String millSec2HourMin(long millSecond) {
        StringBuilder builder = new StringBuilder();
        long hr = millSecond / hour;
        long min = (millSecond - hr * hour) / minute;
        long second = (millSecond - hr * hour - min * minute) / 1000;
        return builder.append(numFormatDate(hr)).append(HOUR_DESC).append(numFormatDate(min)).append(MINUTE_DESC)
                .append(numFormatDate(second)).append(SECOUND_DESC).toString();
    }

    public static String millSec2Min(long millSecond) {
        StringBuilder builder = new StringBuilder();
        long min = millSecond / minute;
        return builder.append(numFormatDate(min)).append(MINUTE_DESC)
                .toString();
    }

    /**
     * 将毫秒转换为 hh:mm:ss -> 是毫秒！！！！
     */
    public static String sec2HHMMSS(long sec) {
        StringBuilder builder = new StringBuilder();
        long hr = sec / hour;
        long min = (sec - hr * hour) / minute;
        long second = (sec - hr * hour - min * minute) / 1000;
        return builder.append(numFormatDate(hr)).append(":").append(numFormatDate(min)).append(":")
                .append(numFormatDate(second)).toString();
    }


    /**
     * 数字转换为 00
     *
     * @param num
     * @return
     */
    private static String numFormatDate(long num) {
        if (num / 10 > 0) {
            return String.valueOf(num);
        } else {
            return "0" + num;
        }

    }

    /**
     * 毫秒转换成分钟:秒
     */
    public static String millSec2MinSec(long millSecond) {
        StringBuilder builder = new StringBuilder();
        long min = millSecond / minute;
        long second = millSecond % minute / 1000;
        String minStr = min > 9 ? String.valueOf(min) : "0" + min;
        String secStr = second > 9 ? String.valueOf(second) : "0" + second;
        return builder.append(minStr).append(":").append(secStr).toString();
    }

    public static Date time2Date(long millSeconds) {
        Date date = new Date();
        date.setTime(millSeconds);
        return date;
    }

    /**
     * 根据用户生日计算年龄
     */
    public static String getAgeByBirthday(String strBirth, boolean needConstellation) {
        if (StringUtils.isEmpty(strBirth)) return "";
        Date birthday = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birthday = sdf.parse(strBirth);

            Calendar cal = Calendar.getInstance();

            if (cal.before(birthday)) {
                throw new IllegalArgumentException(
                        "The birthDay is before Now.It's unbelievable!");
            }

            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(birthday);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    // monthNow==monthBirth
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    // monthNow>monthBirth
                    age--;
                }
            }
            if (needConstellation) {
                return age + "岁," + getConstellation(monthBirth, dayOfMonthBirth);
            } else {
                return age + "岁";
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return "";

    }

    //星座
    public static String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];
    }

    //判断是这个月的几号,不是这个月就屏蔽
    public static int getDayOfCurrentMonthFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        int month = Calendar.getInstance().get(Calendar.MONTH);
        calendar.setTime(date);
        if (calendar.get(Calendar.MONTH) == month)
            return calendar.get(Calendar.DAY_OF_MONTH);
        return 0;
    }

    public static boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) == day;
    }

    public static boolean isSameDay(Date oneDay, Date otherDay) {
        if (oneDay == null || otherDay == null) {
            return false;
        }
        return dateFormate(oneDay, "yyyy-MM-dd").equals(dateFormate(otherDay, "yyyy-MM-dd"));
    }

    public static boolean beforeToday(Date date) {
        return compareDateByDay(date, Calendar.getInstance().getTime()) < 0;
    }

    public static int compareDateByDay(Date oneDate, Date twoDate) {
        return dateFormate(oneDate, "yyyy-MM-dd").compareTo(dateFormate(twoDate, "yyyy-MM-dd"));
    }

    public static int getTheDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}

