package com.kiwi.timer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * 时间转换工具，注意该类不是线程安全的
 *
 * @author wangjunfeng
 * @newDate 2011-3-23
 */
public final class DateTimeUtil {
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final String DATE_FORMAT_1 = "yyyy-MM-dd";
    private static final String DATE_FORMAT_2 = "yyyyMM";
    private static final String DATE_FORMAT_3 = "yyyy_MM";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_TIME_FORMAT_1 = "yyyyMMddHHmmss";
    private static final String TIME_ONLY_FORMAT = "HH:mm:ss";
    private static final String YEAR_ONLY_FORMAT = "yyyy";
    private static final SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private DateTimeUtil() {

    }

    /**
     * 获取系统当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }


    /**
     * 获取当天的零点时间
     *
     * @author wangjunfeng
     */
    public static Date getCurrentZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String getDayBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取某一天的零点时间
     *
     * @param date
     * @return
     * @author wangjunfeng
     * @date 2017年3月14日
     */
    public static Date getZeroDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 返回日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date 日志
     * @return
     */
    public static String toDateTimeStr(Date date) {
        if (date == null) {
            return "";
        }
        return DateTimeUtil.toDateStr(date, DATE_TIME_FORMAT);
    }

    /**
     * 返回日期格式(yyyyMMddHHmmss)
     *
     * @param date 日期
     * @return
     */
    public static String toDateTimeStr2(Date date) {
        if (date == null) {
            return "";
        }
        return DateTimeUtil.toDateStr(date, DATE_TIME_FORMAT_1);
    }

    /**
     * 返回日期格式(yyyy-MM-dd)
     *
     * @param date 日期
     * @return
     */
    public static String toDateStr(Date date) {
        if (date == null) {
            return "";
        }
        return DateTimeUtil.toDateStr(date, DATE_FORMAT_1);
    }

    /**
     * 返回时间格式(HH:mm:ss)
     *
     * @param date 日期
     * @return
     */
    public static String toTimeStr(Date date) {
        if (date == null) {
            return "";
        }
        return DateTimeUtil.toDateStr(date, TIME_ONLY_FORMAT);
    }

    /**
     * 返回日期格式(yyyyMMdd)
     *
     * @param date 日期
     * @return
     */
    public static String toDateStr2(Date date) {
        if (date == null) {
            return "";
        }
        return DateTimeUtil.toDateStr(date, DATE_FORMAT);
    }

    /**
     * 返回日期格式(yyyy_MM)
     *
     * @param date 日期
     * @return
     */
    public static String toDateStr3(Date date) {
        if (date == null) {
            return "";
        }
        return DateTimeUtil.toDateStr(date, DATE_FORMAT_3);
    }

    /**
     * 返回日期格式(yyyy_MM)
     *
     * @param date 日期
     * @return
     */
    public static String toDateStr4(Date date) {
        if (date == null) {
            return "";
        }
        return DateTimeUtil.toDateStr(date, DATE_FORMAT_2);
    }

    /**
     * <p>
     * 得到xxxx年xx月xx日 日期格式。
     * </p>
     *
     * @param date 日期
     * @return
     */
    public static String toChinaDateStr(Date date) {
        if (date == null) {
            return "";
        }
        // 得到yyyy-mm-dd格式日期格式
        String dateStr = toDateStr(date);
        StringBuffer sb = new StringBuffer();
        if (dateStr != null && dateStr.length() > 0) {
            String[] newStr = dateStr.split("-");
            // 得到月
            int month = Integer.valueOf(newStr[1]);
            // 得到日
            int day = Integer.valueOf(newStr[2]);
            sb.append(newStr[0]).append("年");
            sb.append(month).append("月").append(day).append("日");
        }
        return sb.toString();
    }

    /**
     * <p>
     * 获取当前系统时间的小时数
     * </p>
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * <p>
     * 获取当前系统时间的分钟数
     * </p>
     *
     * @return
     */
    public static int getCurrentMinutes() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * <p>
     * 获取本月第一天日期（格式如YYYYMMDD）,如果当前日为当月1日,则返回上月第一日
     * </p>
     *
     * @return
     */
    public static String getMonthFirstDay() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = 0;
        if (day == 1) {
            calendar.add(Calendar.MONTH, -1);
        }
        month = calendar.get(Calendar.MONTH);
        if (month < 10) {
            return "" + calendar.get(Calendar.YEAR) + "0" + (month + 1) + "01";
        } else {
            return "" + calendar.get(Calendar.YEAR) + month + "01";
        }
    }

    /**
     * 取得本月的第一天
     *
     * @return
     * @author wangjunfeng
     * @date 2013-3-20
     */
    public static Date getFristDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    /**
     * 根据传入的Date取得某个月的第一天
     *
     * @param date
     * @return
     * @author wangjunfeng
     * @date 2014年9月29日
     */
    public static Date getFristDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得一个月的最后一天
     *
     * @return
     * @author wangjunfeng
     * @date 2013-3-20
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得一个月的最后一天
     *
     * @return
     * @author wangjunfeng
     * @date 2013-3-20
     */
    public static Date getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前时间前几天或后几天的日期
     *
     * @param days 天数
     * @return
     * @author wangjunfeng
     * @date 2013-3-20
     */
    public static Date getAddDays(int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static Date getAddDays(Date date, int days) {
        return new Date(date.getTime() + 1000L * 60 * 60 * 24 * days);
    }

    public static Date getAddMonths(Date date, int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months); // 设置时间加一个月
        return c.getTime();
    }

    public static Date getAddYears(Date date, int years) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, years); // 设置时间加一年
        return c.getTime();
    }

    public static Date getAddMinutes(Date date, int minutes) {
        return new Date(date.getTime() + 1000L * 60 * minutes);
    }

    /**
     * 获取某个月后的日期格式（yyyyMMdd）
     *
     * @param monthNum 月数
     * @return
     * @author wangjunfeng
     * @date 2013-3-20
     */
    public static String getAfterMonth(int monthNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, monthNum);
        return DateTimeUtil.toDateStr(calendar.getTime(), DATE_FORMAT);
    }

    /**
     * <p>
     * 返回日期（格式yyyyMMdd）
     * </p>
     *
     * @param timeMillis 时间
     * @return
     */
    public static String getFormatDate(long timeMillis) {
        return DateTimeUtil.toDateStr(new Date(timeMillis), DATE_TIME_FORMAT_1);
    }

    public static long getCurrentYear() {
        return Long.parseLong(DateTimeUtil.toDateStr(new Date(), YEAR_ONLY_FORMAT));
    }

    /**
     * 获取当前年的第一天
     *
     * @return
     * @author wangjunfeng
     * @date 2017年3月20日
     */
    public static Date getFirstDayofCurrentYear() {
        return new Date(getCurrentYear());
    }

    /**
     * 判断一个日期字符串是否合法
     *
     * @param date   日期
     * @param format 格式
     * @return
     * @author wangjunfeng
     */
    public static boolean isDateStringCorrect(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * <p/>
     * 将字符串类型的日期格式 转换为 符合要求的日期格式
     * </P>
     *
     * @param date   日期
     * @param format 格式
     * @return
     */
    public static String getStrDate4String(String date, String format) {
        if (date == null || "".equals(date.trim())) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                Date d = df.parse(date);
                return df.format(d);
            } catch (ParseException e) {
                return "";
            }
        }
    }

    /**
     * <p/>
     * 将Date类型的日期格式 转换为 符合要求的 String日期格式
     * </P>
     *
     * @param date   日期
     * @param format 格式
     * @return
     */
    public static String toDateStr(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            try {
                SimpleDateFormat df = new SimpleDateFormat(format);
                return df.format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * <p/>
     * 将字符串类型的日期格式 转换为 符合要求的 Date类型的日期格式
     * </P>
     *
     * @param date   日期
     * @param format 格式
     * @return
     */
    public static Date strToDate(String date, String format) {
        if (date == null || "".equals(date.trim())) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                return df.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * <p/>
     * 将字符串类型的日期格式 "yyyy-MM-dd"
     * </P>
     *
     * @param date 日期
     * @param
     * @return
     */
    public static Date strToDate(String date) {
        if (date == null || "".equals(date.trim())) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_1);
            try {
                return df.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * <p/>
     * 得到起始日期和结束日期之间的天数
     * </P>
     *
     * @param beginStr 起始日期
     * @param endStr   结束日期
     * @param format   根据 日期参数的格式，传对应的SimpleDateFormat格式
     * @return 天数
     */
    public static int getDaysInterval(String beginStr, String endStr, SimpleDateFormat format) {

        try {
            Date beginDate = format.parse(beginStr);
            Date endDate = format.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减得到日期差X(单位:毫秒)
            return (int) (millisecond / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p/>
     * 得到起始日期和结束日期之间的天数
     * </P>
     *
     * @param begin 起始日期
     * @param end   结束日期
     * @return 天数
     */
    public static int getDaysInterval(Date begin, Date end) {
        long millisecond = end.getTime() - begin.getTime(); // 日期相减得到日期差X(单位:毫秒)
        return (int) (millisecond / (1000 * 60 * 60 * 24));
    }

    /**
     * 取得当前的年
     *
     * @return
     * @author wangjunfeng
     * @date 2012-3-30
     */
    public static String getYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return Integer.toString(c.get(Calendar.YEAR));
    }

    /**
     * 取得当前的月
     *
     * @return
     * @author wangjunfeng
     * @date 2012-3-30
     */
    public static String getMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return Integer.toString(c.get(Calendar.MONTH) + 1);
    }

    public static String getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return Integer.toString(c.get(Calendar.MONTH) + 1);
    }

    /**
     * 取得当前的日
     *
     * @return
     * @author wangjunfeng
     * @date 2012-3-30
     */
    public static String getDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return Integer.toString(c.get(Calendar.DATE));
    }

    /**
     * 将日期格式化为yyyyMMddTHHmmssZ
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @return String
     * @author wangjunfeng
     */
    public static String str2Str(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String d = date.substring(8, 10);
        String h = date.substring(11, 13);
        String m = date.substring(14, 16);
        String s = date.substring(17, 19);
        return year + month + d + "T" + h + m + s + "Z";
    }

    /**
     * 取当天零点零分零秒
     */
    public static String getTodayStart() {
        Calendar calendar = Calendar.getInstance();
        // 如果没有这种设定的话回去系统的当期的时间
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = new Date(calendar.getTimeInMillis());
        return longDateFormat.format(date);
    }

    /**
     * 取当天23点59分59秒
     */
    public static String getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date date = new Date(calendar.getTimeInMillis());
        return longDateFormat.format(date);
    }


    /**
     * 根据传来的日期获取最后时间
     */
    public static Date getEndTimeByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    @SuppressWarnings("deprecation")
    public static int getMonthOfDayNum(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, date.getYear());
        cal.set(Calendar.MONTH, date.getMonth() - 1);// 7月
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 取特定日期的零点零分零秒
     */
    public static String getDateStart(String strDate) {
        if (null == strDate || "".equals(strDate.trim())) {
            return "";
        }
        Date date = null;
        try {
            date = shortDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longDateFormat.format(date);
    }

    /**
     * 取特定日期的23点59分59秒
     */
    public static String getDateEnd(String strDate) {
        if (null == strDate || "".equals(strDate.trim())) {
            return "";
        }
        Date date = null;
        try {
            date = shortDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 这样能够取到59分59秒
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return longDateFormat.format(calendar.getTime());
    }

    public static Date getStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 根据传入的Date对象获取下月的当日
     *
     * @param date
     * @return
     * @author wangjunfeng
     * @date 2014年9月29日
     */
    public static Date getNextMonthCurrentDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DAY_OF_YEAR, lastDay);
        Date currentDay = calendar.getTime();
        return currentDay;
    }

    /**
     * 根据传入的Date对象获取下月的第一天
     *
     * @param date
     * @return
     * @author wangjunfeng
     * @date 2014年9月29日
     */
    public static Date getNextMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 根据传入的年份的第一天
     *
     * @param year
     * @return
     * @author wangjunfeng
     * @date 2014年9月29日
     */
    public static Date getYearFirstDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static boolean timePeriodCoincide(Date aStartDate, Date aEndDate, Date bStartDate, Date bEndDate) {
        return aEndDate.after(bStartDate) && bEndDate.after(aStartDate);
    }

    public static boolean isOverlap(Map<String, Date> dateMap1, Map<String, Date> dateMap2) {
        if (null == dateMap1 || dateMap1.size() == 0) {
            return false;
        }
        if (null == dateMap2 || dateMap2.size() == 0) {
            return false;
        }
        Date d1b = dateMap1.get("date1Begin");
        Date d1e = dateMap1.get("date1End");
        Date d2b = dateMap2.get("date2Begin");
        Date d2e = dateMap2.get("date2End");
        return d1e.compareTo(d2b) >= 0 && d2e.compareTo(d1b) >= 0;
    }

    /**
     * 某天是周几
     *
     * @param date
     * @return
     * @author wangjunfeng
     * @date 2016年12月13日
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int x = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return x;
    }

    public static int getMonthSpace(Date begin, Date end) {
        int result = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(begin);
        c2.setTime(end);
        result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12
                + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
        int dayMonths = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
        if (dayMonths < 0) {
            result--;
        } else if (dayMonths > 0) {
            result++;
        } else {// 日期相同,时间不同情况
            int hourDays = c2.get(Calendar.HOUR_OF_DAY) - c1.get(Calendar.HOUR_OF_DAY);
            if (hourDays > 0) {// 判断小时
                result++;
            } else if (hourDays == 0) {// 判断分钟
                int minHours = c2.get(Calendar.MINUTE) - c1.get(Calendar.MINUTE);
                if (minHours > 0) {
                    result++;
                } else if (minHours == 0) {// 判断秒
                    int secMins = c2.get(Calendar.SECOND) - c1.get(Calendar.SECOND);
                    if (secMins > 0) {
                        result++;
                    } else if (secMins == 0) {// 判断毫秒
                        int millSecs = c2.get(Calendar.MILLISECOND) - c1.get(Calendar.MILLISECOND);
                        if (millSecs > 0) {
                            result++;
                        }
                    }
                }
            }
        }
        return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 某天是周几
     *
     * @param date
     * @return
     * @author ljy
     * @date 2017年5月22日
     */
    public static String getDateOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int x = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String weekName = "";
        switch (x) {
            case 1:
                weekName = "周一";
                break;
            case 2:
                weekName = "周二";
                break;
            case 3:
                weekName = "周三";
                break;
            case 4:
                weekName = "周四";
                break;
            case 5:
                weekName = "周五";
                break;
            case 6:
                weekName = "周六";
                break;
            case 0:
                weekName = "周日";
                break;
            default:
                weekName = "未知";
                break;
        }
        return weekName;
    }

    public static Date getFristDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static void main(String[] args) throws Exception {

        System.out.println(toDateStr(new Date(),"yyyyMM"));
    }
}