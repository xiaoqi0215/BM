package com.qixiao.bm.Utils;

import android.util.Log;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Date;



/**

 * <b>描述</b>: 日历转换工具类：阴历和阳历日期互换(阴历日期范围19000101~20491229)<br>


 */

public class CalendarUtil {

    //	private static final Logger logger = LoggerFactory.getLogger(CalendarUtil.class);

    // 计算阴历日期参照1900年到2049年

    private final static int[] lunar200y = {

            0x04AE53,0x0A5748,0x5526BD,0x0D2650,0x0D9544,0x46AAB9,0x056A4D,0x09AD42,0x24AEB6,0x04AE4A,/*1901-1910*/

            0x6A4DBE,0x0A4D52,0x0D2546,0x5D52BA,0x0B544E,0x0D6A43,0x296D37,0x095B4B,0x749BC1,0x049754,/*1911-1920*/

            0x0A4B48,0x5B25BC,0x06A550,0x06D445,0x4ADAB8,0x02B64D,0x095742,0x2497B7,0x04974A,0x664B3E,/*1921-1930*/

            0x0D4A51,0x0EA546,0x56D4BA,0x05AD4E,0x02B644,0x393738,0x092E4B,0x7C96BF,0x0C9553,0x0D4A48,/*1931-1940*/

            0x6DA53B,0x0B554F,0x056A45,0x4AADB9,0x025D4D,0x092D42,0x2C95B6,0x0A954A,0x7B4ABD,0x06CA51,/*1941-1950*/

            0x0B5546,0x555ABB,0x04DA4E,0x0A5B43,0x352BB8,0x052B4C,0x8A953F,0x0E9552,0x06AA48,0x6AD53C,/*1951-1960*/

            0x0AB54F,0x04B645,0x4A5739,0x0A574D,0x052642,0x3E9335,0x0D9549,0x75AABE,0x056A51,0x096D46,/*1961-1970*/

            0x54AEBB,0x04AD4F,0x0A4D43,0x4D26B7,0x0D254B,0x8D52BF,0x0B5452,0x0B6A47,0x696D3C,0x095B50,/*1971-1980*/

            0x049B45,0x4A4BB9,0x0A4B4D,0xAB25C2,0x06A554,0x06D449,0x6ADA3D,0x0AB651,0x093746,0x5497BB,/*1981-1990*/

            0x04974F,0x064B44,0x36A537,0x0EA54A,0x86B2BF,0x05AC53,0x0AB647,0x5936BC,0x092E50,0x0C9645,/*1991-2000*/

            0x4D4AB8,0x0D4A4C,0x0DA541,0x25AAB6,0x056A49,0x7AADBD,0x025D52,0x092D47,0x5C95BA,0x0A954E,/*2001-2010*/

            0x0B4A43,0x4B5537,0x0AD54A,0x955ABF,0x04BA53,0x0A5B48,0x652BBC,0x052B50,0x0A9345,0x474AB9,/*2011-2020*/

            0x06AA4C,0x0AD541,0x24DAB6,0x04B64A,0x69573D,0x0A4E51,0x0D2646,0x5E933A,0x0D534D,0x05AA43,/*2021-2030*/

            0x36B537,0x096D4B,0xB4AEBF,0x04AD53,0x0A4D48,0x6D25BC,0x0D254F,0x0D5244,0x5DAA38,0x0B5A4C,/*2031-2040*/

            0x056D41,0x24ADB6,0x049B4A,0x7A4BBE,0x0A4B51,0x0AA546,0x5B52BA,0x06D24E,0x0ADA42,0x355B37,/*2041-2050*/

            0x09374B,0x8497C1,0x049753,0x064B48,0x66A53C,0x0EA54F,0x06B244,0x4AB638,0x0AAE4C,0x092E42,/*2051-2060*/

            0x3C9735,0x0C9649,0x7D4ABD,0x0D4A51,0x0DA545,0x55AABA,0x056A4E,0x0A6D43,0x452EB7,0x052D4B,/*2061-2070*/

            0x8A95BF,0x0A9553,0x0B4A47,0x6B553B,0x0AD54F,0x055A45,0x4A5D38,0x0A5B4C,0x052B42,0x3A93B6,/*2071-2080*/

            0x069349,0x7729BD,0x06AA51,0x0AD546,0x54DABA,0x04B64E,0x0A5743,0x452738,0x0D264A,0x8E933E,/*2081-2090*/

            0x0D5252,0x0DAA47,0x66B53B,0x056D4F,0x04AE45,0x4A4EB9,0x0A4D4C,0x0D1541,0x2D92B5          /*2091-2099*/

    };



    // 允许输入的最小年份

    private final static int MIN_YEAR = 1901;

    // 允许输入的最大年份

    private final static int MAX_YEAR = 2099;

    // 当年是否有闰月

    private static boolean isLeapYear;

    // 阳历日期计算起点

    private final static String START_DATE = "19000130";

    static int[] monthTotal = new int[]{0,31,59,90,120,151,181,212,243,273,304,334,365};


   public static class lunar_t{
        int year;

        int month;

        int day;

        int is_leap;

       public lunar_t() {
       }

       public lunar_t(int year, int month, int day) {
           this.year = year;
           this.month = month;
           this.day = day;
       }

       public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getIs_leap() {
            return is_leap;
        }

        public void setIs_leap(int is_leap) {
            this.is_leap = is_leap;
        }
    }

    static int lunarLeapMonth(int y) //是否有闰月
     {
     return ((lunar200y[y-MIN_YEAR] & 0xf00000) >>20);
    }

    public static  int dayToDay(int sYear,int sMonth,int sDay,int eYear,int eMonth,int eDay){
        Calendar calenda = Calendar.getInstance();
        calenda.set(sYear, sMonth, sDay, 0, 0, 0);

        Calendar calenda1 = Calendar.getInstance();
        calenda1.set(eYear, eMonth, eDay, 0, 0, 0);

        int day = (int) ((calenda1.getTimeInMillis() / 1000 / 60 / 60 / 24)
                - (calenda.getTimeInMillis() / 1000 / 60 / 60 / 24));

        return day;

    }

    static int lunarMonthDays(int y, int m)

    {
        int temp;
             temp =(lunar200y[y-MIN_YEAR] & (0x080000>>(m-1)))!= 0 ? 30: 29;

        return   temp;

    }



    static int lunarLeapMonthDays(int y) {
        int temp = lunarLeapMonth(y);
        if(temp!=0) {
            return( (lunar200y[y-MIN_YEAR] & (0x80000>>(temp-1)))!=0? 30: 29 );
        }
        else {
            return 0;
        }
    }

    int lunarDateCheck(lunar_t lunar)
    {
        int leap = 0;
        if ((lunar.year < MIN_YEAR) || (lunar.year > MAX_YEAR)) { //年限定范围
            return -1;
        }
        if ((lunar.month < 1) || (lunar.month > 12)) {//月限定范围
            return -1;
        }
        if ((lunar.day < 1) || (lunar.day > 30)) {//中国的月最多30天
            return -1;
        }

        if(lunarMonthDays(lunar.year,lunar.month)<lunar.day) {
            return -1;
        }
        if (lunar.is_leap == 1 ) {
            leap = lunarLeapMonth(lunar.year);
            if(leap==0)
                return -1;
            else if(lunar.month != leap)
                return -1;
            else if(lunarLeapMonthDays(lunar.year)<lunar.day) {
                return -1; }
        }
        return 0;
    }

    static int[] solarMonth = new int[]{0,31,28,31,30,31,30,31,30,31,31,30,31};

   static boolean toYear(int year){
       if (year%4==0&&year%100!=0||year%400==0){

           solarMonth[2]=29;
           return true;
       }else {
           solarMonth[2]=28;
           return false;
       }
   }

    public  static lunar_t toSolar(lunar_t lunar)
    {

        int year = lunar.year, month = lunar.month, day = lunar.day;

        int byNow, xMonth, i;

        int spring_solar_d;

        int spring_solar_m;


        spring_solar_m =(lunar200y[lunar.getYear()-MIN_YEAR] & 0x000060) >> 5;

        spring_solar_d = lunar200y[lunar.getYear()-MIN_YEAR]&0x1f;
      //  Log.e("TAG","春节的月和日:月"+spring_solar_m+"-"+spring_solar_d);

        byNow = spring_solar_d - 1;


        toYear(year);

        int yuanToSpring =0;

        //元旦到春节
        if(spring_solar_m<2){
            yuanToSpring = spring_solar_d;
        }else if (spring_solar_m ==2){
            yuanToSpring= spring_solar_d+31;
        }else {
            for (int j = 1;j<spring_solar_m;j++){
                yuanToSpring += solarMonth[j];
            }
            yuanToSpring +=spring_solar_d;
        }
       // Log.e("TAG","元旦到春节的天数:"+yuanToSpring);

        if( spring_solar_m == 2)

            byNow += 31;

        for(i = 1; i < month; i ++){

            byNow += lunarMonthDays(year,i);

        }

        //春节到指定日期
        //是否有闰月
        xMonth =lunarLeapMonth(year);
     //   Log.e("TAG","是否有闰月:"+xMonth);
        int springToDate =0;
        if(xMonth==0){
            for (int j=1;j<month;j++ ){
                springToDate +=lunarMonthDays(year,j);
          //      Log.e("TAG","1"+j+"月的天数:"+lunarMonthDays(year,j));
            }
            springToDate+=day;
        }else {
            if(xMonth<month){
                for (int j= 1;j<month+1;j++){
                    springToDate+=lunarMonthDays(year,j);
             //       Log.e("TAG","2"+j+"月的天数:"+lunarMonthDays(year,j));
                }
            }else {
                for (int j =1;j<month;j++){
                    springToDate+=lunarMonthDays(year,j);
             //       Log.e("TAG","3"+j+"月的天数:"+lunarMonthDays(year,j));
                }

            }
            springToDate+=day;
        }
       // Log.e("TAG","春节到指定日期的天数:"+springToDate);
        int yuanToData = yuanToSpring+springToDate-1;
      //  Log.e("TAG","元旦到指定日期的天数:"+yuanToData);

        //将总天数转成日期

        int tempTotal = yuanToData;
        int monthNum = 1;
        boolean flag = false;
        while (tempTotal>31){
            if (monthNum>=12){
                flag = true;
                break;
            }
            tempTotal = tempTotal-solarMonth[monthNum++];
        }
        if (flag){
            //表示阳历和阴历不处于同一年，计算下一年是否是闰年
            toYear(year+1);
            lunar.setYear(year+1);
            monthNum=1;
            tempTotal-=31;
            while (tempTotal>31){
                tempTotal = tempTotal-solarMonth[monthNum++];
            }
            if (tempTotal<=0){
                lunar.setMonth(monthNum-1);
                lunar.setDay(solarMonth[monthNum-1]);
            }
            lunar.setMonth(monthNum);
            lunar.setDay(tempTotal);
        }else {
            lunar.setYear(year);
            if (tempTotal<=0){
                lunar.setMonth(monthNum-1);
                lunar.setDay(solarMonth[monthNum-1]);
            }
            lunar.setMonth(monthNum);
            lunar.setDay(tempTotal);
        }
        return lunar;


    }

    int solarDateCheck(int year, int month, int day) {

        if ((year < MIN_YEAR) || (year > MAX_YEAR)) {

            return -1;

        }
        return 0;
    }

    int toLunar(int year, int month, int day , lunar_t  lunar)

    {

        int bySpring,bySolar,daysPerMonth;

        int index;
        boolean flag;

        int spring_solar_d;

        int spring_solar_m;


        if(solarDateCheck(year,month,day) ==-1)

            return -1;



        //bySpring 记录春节离当年元旦的天数。

        //bySolar 记录阳历日离当年元旦的天数。


        spring_solar_d =(lunar200y[year-MIN_YEAR] & 0x0060) >> 5;

        spring_solar_m = lunar200y[year-MIN_YEAR]&0x1f;

        if( spring_solar_m == 1)

            bySpring = spring_solar_d - 1;

        else

            bySpring = spring_solar_d - 1 + 31;



        bySolar = monthTotal[month-1] + day - 1;

        if( ((year % 4)==0) && (month > 2))

            bySolar++;



        //daysPerMonth记录大小月的天数 29 或30

        //index 记录从哪个月开始来计算。

        //flag 是用来对闰月的特殊处理。



        //判断阳历日在春节前还是春节后

        if (bySolar >= bySpring) /*/阳历日在春节后（含春节那天）*/

        {

            bySolar -= bySpring;

            month = 1;

            flag = false;

            daysPerMonth =lunarMonthDays(year,month);



            while(bySolar >= daysPerMonth)

            {

                bySolar -= daysPerMonth;

                if(month == lunarLeapMonth(year) )

                {

                    flag = !flag;

                    if(!flag)

                    {

                        month++;

                    }

                }

                else

                {

                    month++;

                }

                daysPerMonth =lunarMonthDays(year,month);

            }

            day = bySolar + 1;

        }

        else

        {                                                      //阳历日在春节前

            bySpring -= bySolar;

            year--;

            month = 12;

            if ( lunarLeapMonth(year) == 0)

                index = 12;

            else

                index = 13;

            flag = false;



            daysPerMonth = lunarMonthDays(year, index );



            while(bySpring > daysPerMonth)

            {

                bySpring -= daysPerMonth;

                index--;



                if(!flag)

                    month--;

                if(month == lunarLeapMonth(year))

                {

                    flag =!flag;

                }

                daysPerMonth = lunarMonthDays(year, index );

            }

            day = daysPerMonth - bySpring + 1;

        }

        lunar.day = day;

        lunar.month = month;

        lunar.year = year;

        if(month == lunarLeapMonth(year))

            lunar.is_leap = 1;

        else

            lunar.is_leap = 0;

        return 0;

    }
}
