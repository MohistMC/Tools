package com.mohistmc.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 一些关于农历的库
 *
 * @author Mgazul by MohistMC
 * @date 2023/10/7 0:32:48
 */
public class NongLi {


    private static int year;
    private int month;
    private int day;
    private boolean leap;
    static String[] chineseNumber;
    static SimpleDateFormat chineseDateFormat;
    static long[] lunarInfo;

    static {
        chineseNumber = new String[] { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
        NongLi.chineseDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        lunarInfo = new long[] { 19416L, 19168L, 42352L, 21717L, 53856L, 55632L, 91476L, 22176L, 39632L, 21970L, 19168L, 42422L, 42192L, 53840L, 119381L, 46400L, 54944L, 44450L, 38320L, 84343L, 18800L, 42160L, 46261L, 27216L, 27968L, 109396L, 11104L, 38256L, 21234L, 18800L, 25958L, 54432L, 59984L, 28309L, 23248L, 11104L, 100067L, 37600L, 116951L, 51536L, 54432L, 120998L, 46416L, 22176L, 107956L, 9680L, 37584L, 53938L, 43344L, 46423L, 27808L, 46416L, 86869L, 19872L, 42448L, 83315L, 21200L, 43432L, 59728L, 27296L, 44710L, 43856L, 19296L, 43748L, 42352L, 21088L, 62051L, 55632L, 23383L, 22176L, 38608L, 19925L, 19152L, 42192L, 54484L, 53840L, 54616L, 46400L, 46496L, 103846L, 38320L, 18864L, 43380L, 42160L, 45690L, 27216L, 27968L, 44870L, 43872L, 38256L, 19189L, 18800L, 25776L, 29859L, 59984L, 27480L, 21952L, 43872L, 38613L, 37600L, 51552L, 55636L, 54432L, 55888L, 30034L, 22176L, 43959L, 9680L, 37584L, 51893L, 43344L, 46240L, 47780L, 44368L, 21977L, 19360L, 42416L, 86390L, 21168L, 43312L, 31060L, 27296L, 44368L, 23378L, 19296L, 42726L, 42208L, 53856L, 60005L, 54576L, 23200L, 30371L, 38608L, 19415L, 19152L, 42192L, 118966L, 53840L, 54560L, 56645L, 46496L, 22224L, 21938L, 18864L, 42359L, 42160L, 43600L, 111189L, 27936L, 44448L };
    }

    private static int yearDays(int y) {
        int sum = 348;
        for (int i = 32768; i > 8; i >>= 1) {
            if ((NongLi.lunarInfo[y - 1900] & (long)i) != 0x0L) {
                ++sum;
            }
        }
        return sum + leapDays(y);
    }

    private static int leapDays(int y) {
        if (leapMonth(y) == 0) {
            return 0;
        }
        if ((NongLi.lunarInfo[y - 1900] & 0x10000L) != 0x0L) {
            return 30;
        }
        return 29;
    }

    private static int leapMonth(int y) {
        return (int)(NongLi.lunarInfo[y - 1900] & 0xFL);
    }

    private static int monthDays(int y, int m) {
        if ((NongLi.lunarInfo[y - 1900] & (long)(65536 >> m)) == 0x0L) {
            return 29;
        }
        return 30;
    }

    public static String animalsYear() {
        String[] Animals = { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
        return Animals[(NongLi.year - 4) % 12];
    }

    private static String cyclicalm(int num) {
        String[] Gan = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
        String[] Zhi = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
        return Gan[num % 10] + Zhi[num % 12];
    }

    public String cyclical() {
        int num = NongLi.year - 1900 + 36;
        return cyclicalm(num);
    }

    public NongLi(Calendar cal) {
        int leapMonth = 0;
        Date baseDate = null;
        try {
            baseDate = NongLi.chineseDateFormat.parse("1900年1月31日");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        int offset;
        int daysOfYear;
        int iYear;
        for (offset = (int)((cal.getTime().getTime() - baseDate.getTime()) / 86400000L), daysOfYear = 0, iYear = 1900; iYear < 2050 && offset > 0; offset -= daysOfYear, ++iYear) {
            daysOfYear = yearDays(iYear);
        }
        if (offset < 0) {
            offset += daysOfYear;
            --iYear;
        }
        NongLi.year = iYear;
        leapMonth = leapMonth(iYear);
        this.leap = false;
        int daysOfMonth = 0;
        int iMonth;
        for (iMonth = 1; iMonth < 13 && offset > 0; ++iMonth) {
            if (leapMonth > 0 && iMonth == leapMonth + 1 && !this.leap) {
                --iMonth;
                this.leap = true;
                daysOfMonth = leapDays(NongLi.year);
            }
            else {
                daysOfMonth = monthDays(NongLi.year, iMonth);
            }
            offset -= daysOfMonth;
            if (this.leap && iMonth == leapMonth + 1) {
                this.leap = false;
            }
            if (!this.leap) {}
        }
        if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
            if (this.leap) {
                this.leap = false;
            }
            else {
                this.leap = true;
                --iMonth;
            }
        }
        if (offset < 0) {
            offset += daysOfMonth;
            --iMonth;
        }
        this.month = iMonth;
        this.day = offset + 1;
    }

    public static String getChinaDayString(int day) {
        String[] chineseTen = { "初", "十", "廿", "卅" };
        int n = (day % 10 == 0) ? 9 : (day % 10 - 1);
        if (day > 30) {
            return "";
        }
        if (day == 10) {
            return "初十";
        }
        return chineseTen[day / 10] + NongLi.chineseNumber[n];
    }

    @Override
    public String toString() {
        return NongLi.year + "年" + (this.leap ? "闰" : "") + NongLi.chineseNumber[this.month - 1] + "月" + getChinaDayString(this.day);
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
        NongLi lunar = new NongLi(cal);
        System.out.println("农历" + lunar + " " + animalsYear());
    }
}
