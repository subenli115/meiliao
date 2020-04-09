package com.ziran.meiliao.widget.calendar.entity;

import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * Created by Administrator on 2016/7/30.
 */
public class CalendarInfo implements Comparable<CalendarInfo> {
    /**
     * 年
     */
    public int year;
    /**
     * 月
     */
    public int month;
    /**
     * 日
     */
    public int day;
    /**
     * 描述词
     */
    public String des;
    /**
     * 是否为休、班。。1为休，2为班，默认为普通日期
     */
    public int rest;

    /**
     * 构造函数
     *
     * @param year  事务年份
     * @param month 事务月份
     * @param day   事务日期号
     * @param des   事务描述
     */
    public CalendarInfo(int year, int month, int day, String des) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.des = des;
    }

    /**
     * 构造函数
     *
     * @param year  事务年份
     * @param month 事务月份
     * @param day   事务日期号
     * @param des   事务描述
     * @param rest  是否为休、班。。1为休，2为班，默认为普通日期
     */
    public CalendarInfo(int year, int month, int day, String des, int rest) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.des = des;
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "CalendarInfo{" + "year=" + year + ", month=" + month + ", day=" + day + ", des='" + des + '\'' + ", rest=" + rest + '}';
    }


    @Override
    public int compareTo(@NonNull CalendarInfo o) {
        return checkData(this, o);
    }

    public static boolean check(CalendarInfo date1, CalendarInfo date2) {
        //          2007        2008
        if (date1.year < date2.year) {
            return true;
        } else if (date1.year > date2.year) {
            return false;
        } else {
            //      8                       9
            if (date1.month < date2.month) {
                return true;
            } else if (date1.month > date2.month) {
                return false;
            } else {
                return date1.day <= date2.day;
            }
        }
    }

    public static int checkData(CalendarInfo date1, CalendarInfo date2) {
        //          2007        2008
        if (date1.year < date2.year) {
            return -1;
        } else if (date1.year > date2.year) {
            return 1;
        } else {
            //      8                       9
            if (date1.month < date2.month) {
                return -1;
            } else if (date1.month > date2.month) {
                return 1;
            } else {
                if (date1.day < date2.day) {
                    return -1;
                } else if (date1.day > date2.day) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    // -1 过期   1 之后
    public int checkBig(int year, int month, int day) {
        if (this.year < year) {
            return -1;
        } else if (this.year > year) {
            return 1;
        } else {
            //      8                       9
            if (this.month < month) {
                return -1;
            } else if (this.month > month) {
                return 1;
            } else {
                if (this.day < day) {
                    return -1;
                } else if (this.day > day) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public String getString() {
        return String.format(Locale.getDefault(), "%d-%02d-%02d", year, month, day);
    }
}
