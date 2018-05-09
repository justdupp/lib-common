package com.hecc.lib.common;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xuhoujun
 * @description: 日期工具类
 * @date: Created In 下午9:36 on 2018/2/26.
 */
public class DateUtils {

    public static final int DAY_ADD_INITIAL = 1;
    public static final int DAY_ADD_IF_FRIDAY = 3;
    public static final int DAY_ADD_IF_SUNDAY = 2;

    public static final String FORMATTER_PATTERN_WITH_MIDDLE_LINE = "yyyy-MM-dd";
    public static final String FORMATTER_PATTERN_WITH_BACK_SLANT = "yyyy/MM/dd";
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        //useDateFormatter();
        //      System.out.println(getNextWorkingDay(2018,03,02));
        //      System.out.println(getNextWorkingDay("2018-02-26",FORMATTER_PATTERN_WITH_MIDDLE_LINE));
        //   System.out.println(getNextWorkingDay("2018/02/26",FORMATTER_PATTERN_WITH_BACK_SLANT));
        // System.out.println(getNowDate());
        //  System.out.println(getWeekNumDate(-1,LocalDate.now()));
        //System.out.println(getWeekNumDate(1,"2018-01-30"));
        //System.out.println(getMonthNumDate(-1,LocalDate.now()));
        //   System.out.println(getMonthNumDate(1,"2018-01-30"));
        // System.out.println(getDayNumDate(1,LocalDate.now()));
        //  System.out.println(getDayNumDate(1,"2018-02-21"));
        System.out.println(collectLocalDates("2018-01-01", "2018-02-28"));
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static LocalDate getNowDate() {
        LocalDate localDate = LocalDate.now();
        return localDate;
    }

    public static LocalDate getDayNumDate(int dayNum, String dateStr) {
        return getDayNumDate(dayNum, LocalDate.parse(dateStr));
    }

    /**
     * 获取第n天后的日期 n可为负数
     *
     * @param localDate 当前日期
     * @return n天后的日期
     */
    public static LocalDate getDayNumDate(int dayNum, LocalDate localDate) {
        return localDate.plus(dayNum, ChronoUnit.DAYS);
    }

    public static LocalDate getWeekNumDate(int weekNum, String dateStr) {
        return getWeekNumDate(weekNum, LocalDate.parse(dateStr));
    }

    /**
     * 获取第n周后的日期 n可为负数
     *
     * @param localDate 当前日期
     * @return n周后的日期
     */
    public static LocalDate getWeekNumDate(int weekNum, LocalDate localDate) {
        return localDate.plus(weekNum, ChronoUnit.WEEKS);
    }

    public static LocalDate getMonthNumDate(int monthNum, String dateStr) {
        return getMonthNumDate(monthNum, LocalDate.parse(dateStr));
    }

    /**
     * 获取第n月后的日期 n可为负数
     *
     * @param localDate 当前日期
     * @return n月后的日期
     */
    public static LocalDate getMonthNumDate(int monthNum, LocalDate localDate) {
        return localDate.plus(monthNum, ChronoUnit.MONTHS);
    }


    /**
     * 获取下一个工作日
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return LocalDate toString 形式
     */
    private static LocalDate getNextWorkingDay(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        return date.with(new NextWorkingDay());
    }

    /**
     * @param dateStr       日期字符串 2018-02-26
     * @param formatPattern 日期格式
     * @return LocalDate toString 形式
     */
    private static LocalDate getNextWorkingDay(String dateStr, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        formatter = formatter.withLocale(Locale.CHINA);
        LocalDate date = LocalDate.parse(dateStr, formatter);
        return date.with(new NextWorkingDay());
    }

    private static class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = DAY_ADD_INITIAL;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = DAY_ADD_IF_FRIDAY;
            }
            if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = DAY_ADD_IF_SUNDAY;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

    /**
     * 收集起始日期到结束日期之间所有的日期并以字符串集合方式返回
     *
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public static List<String> collectLocalDates(String timeStart, String timeEnd) {
        return collectLocalDates(LocalDate.parse(timeStart), LocalDate.parse(timeEnd));
    }

    /**
     * 收集起始日期到结束日期之间所有的日期并以字符串集合方式返回
     *
     * @param start
     * @param end
     * @return List<String>  年月日格式的集合
     */
    public static List<String> collectLocalDates(LocalDate start, LocalDate end) {
        return Stream.iterate(start, localDate -> localDate.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                .map(LocalDate::toString)
                .collect(Collectors.toList());
    }


    /**
     * LocalDateTime转自定义String
     *
     * @param localDateTime
     * @param format        比如 yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * string 转LocalDateTime
     *
     * @param dateStr 源字符串  比如： "2018-02-28 01:00:00"
     * @param format  转换模式  比如   "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String dateStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateStr, formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据时间获取当前月份的天数
     *
     * @param date
     * @return
     */
    public static int getActualMaximum(Date date) {
        return dateToLocalDateTime(date).getMonth().length(dateToLocalDate(date).isLeapYear());
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return 1:星期一；2:星期二；3:星期三；4:星期四；5:星期五；6:星期六；7:星期日；
     */
    public static int getWeekOfDate(Date date) {
        return dateToLocalDateTime(date).getDayOfWeek().getValue();
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * 计算两个日期LocalDate相差的天数，不考虑日期前后，返回结果>=0
     *
     * @param before
     * @param after
     * @return
     */
    public static int getAbsDateDiffDay(LocalDate before, LocalDate after) {
        return Math.abs(Period.between(before, after).getDays());
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        return dateToLocalDateTime(date).toLocalDate();
    }

    /**
     * 获取指定日期的当月的月份数
     *
     * @param date
     * @return
     */
    public static int getLastMonth(Date date) {
        return dateToLocalDateTime(date).getMonth().getValue();

    }

    /**
     * 特定日期的当月第一天
     *
     * @param date
     * @return
     */
    public static LocalDate newThisMonth(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
    }

    /**
     * 特定日期的当月最后一天
     *
     * @param date
     * @return
     */
    public static LocalDate lastThisMonth(Date date) {
        int lastDay = getActualMaximum(date);
        LocalDate localDate = dateToLocalDate(date);
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), lastDay);
    }

    /**
     * date 转转自定义String
     *
     * @param date    new Date()
     * @param pattern 例 yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        return localDateTimeToString(dateToLocalDateTime(date), pattern);
    }

}
