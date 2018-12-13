package com.learn.makeTestData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MakeDataUtils {

    private static final String[] lastNameDefault = {"赵","钱","孙","李","周","吴","郑","王"};
    private static final String[] firstNameDefault = {"一","二","三","四","五","六","七","八","红","蓝","黄","皇"};
    private static final String[] sexsDefault = {"男","女"};
    private static final String[] provinceDefault = {"广东省","湖南省","四川省","甘肃省","青海省","北京市","天津市","上海市","重庆市"};
    private static final String[] cityDefault = {"长沙市","茂名市","临海市","临滨市","临江市","成都市","浮云市"};
    private static final String[] townDefault = {"神仙镇","妖怪镇","仙女镇"};
    private static final String[] streetDefault = {"人民街","解放街","改革街","开放街"};
    private static final String[] emailSuffixsDefault = {"qq.com","sina.com","163.com"};
    private static final char[] alphabets = "abcdefghijklstrpqostuywxyz".toCharArray();
    private static final char[] numbers = "0123456789".toCharArray();
    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd mm:hh:ss";
    private static final String DATE_START_DEFAULT= "1900-01-01 00:00:00";

    /**
     * 造名字
     * @param lastName 姓氏列表(可为null)
     * @param firstName 名字列表(可为null)
     * @param count 拼接lastName 和 firsstName的次数 例如设置为3 则 lastName + firstName + firstName,
     *              如果所传值为0则采用默认策略,默认会随机为 2 或 3
     * @return 随机生成的名字
     */
    public static String makeName(String[] lastName, String[] firstName, int count){
        if (lastName == null || lastName.length == 0){
            lastName = lastNameDefault;
        }

        if (firstName == null || firstName.length == 0){
            firstName = firstNameDefault;
        }

        StringBuilder name = new StringBuilder();
        Random random = new Random();
        if (count == 0){
            if (random.nextInt() % 2 == 0){
                count = 2;
            }
            else {
                count = 3;
            }
        }
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                name.append(lastName[random.nextInt(lastName.length)]);
            }
            else {
                name.append(firstName[random.nextInt(firstName.length)]);
            }
        }

        return name.toString();
    }

    /**
     * 随机生成年龄数据
     * @param maxAge 最大年龄
     * @return 年龄
     */
    public static int makeAge(int maxAge){
        if (maxAge <= 0){
            maxAge = 100;
        }
        Random random = new Random();
        return random.nextInt(maxAge);
    }

    /**
     * 生成性别,从sexs列表中生成
     * @param sexs 性别列表 如[男,女,不详...]
     * @return
     */
    public static String makeSex(String [] sexs){
        if (sexs == null || sexs.length <= 0){
            sexs = sexsDefault;
        }
        Random random = new Random();
        return sexs[random.nextInt(sexs.length)];
    }

    /**
     * 造地址数据
     * @param province 所有省的列表
     * @param city 所有市的列表
     * @param town 所有镇的列表
     * @param street 所有街道的列表
     * @return 返回一个 province + city + town + street的地址数据
     */
    public static String makeAddress(String[] province, String[] city, String[] town, String[] street){
        StringBuilder address = new StringBuilder();
        Random random = new Random();
        if (province == null || province.length <= 0){
            province = provinceDefault;
            address.append(province[random.nextInt(province.length)]);
            address.append(" ");
        }
        if (city == null){
            city = cityDefault;
            address.append(city[random.nextInt(city.length)]);
            address.append(" ");
        }
        if (town == null || town.length <= 0){
            town = townDefault;
            address.append(town[random.nextInt(town.length)]);
            address.append(" ");
        }
        if (street == null || street.length <= 0){
            street = streetDefault;
            address.append(street[random.nextInt(street.length)]);
        }
        return address.toString();
    }

    /**
     * 随机生成一个邮件地址
     * @param emailSuffixs 邮件后缀列表(qq.com,163.com,xxx.cn...),即 '@' 后的内容
     * @return 邮件数据 格式为 ${随机 5-10 位数字} @ ${emailSuffixs中的随机一个值}
     */
    public static String makeEmail(String[] emailSuffixs){
        if (emailSuffixs == null || emailSuffixs.length <= 0){
            emailSuffixs = emailSuffixsDefault;
        }
        Random random = new Random();
        int charCount = random.nextInt(5)+5;
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < charCount; i++) {
                email.append(random.nextInt(10));
        }
        email.append("@"+emailSuffixs[random.nextInt(emailSuffixs.length)]);
        return email.toString();
    }


    /**
     * @param dateFormat 要返回的日期的格式
     * @param startDateStr 日期的开始范围,要跟dateFormat匹配
     * @param endDateStr 日期的结束范围,要跟dateFormat匹配
     * @return 范围内的随机日期
     * @throws ParseException 1.传入的startDateStr/endDateStr与dataFormat不匹配
     *                        2.当传入的dataFormat的值不为:[yyyy-MM-dd hh:mm:ss],且未传入对应格式的startDateStr/endDateStr
     */
    public static String makeDate(String dateFormat, String startDateStr, String endDateStr) throws ParseException {
        Date startDate;
        Date endDate;
        long startDateTimeStamp;
        long endDateTimeStamp;
        SimpleDateFormat simpleDateFormat;
        if (dateFormat == null || "".equals(dateFormat)) {
            dateFormat = DATE_FORMAT_DEFAULT;
        }
        simpleDateFormat = new SimpleDateFormat(dateFormat);
        if (startDateStr == null || "".equals(startDateStr)) {
            startDateStr = DATE_START_DEFAULT;
        }
        startDate = simpleDateFormat.parse(startDateStr);
        startDateTimeStamp = startDate.getTime();
        if (endDateStr != null && "".equals(endDateStr)) {
            endDate = simpleDateFormat.parse(endDateStr);
            endDateTimeStamp = endDate.getTime();
        } else {
            endDateTimeStamp = System.currentTimeMillis();
        }
        long realIntever = endDateTimeStamp - startDateTimeStamp;
        long randomIntever = (long) (Math.random()*realIntever);
        long finalTimeStamp = startDateTimeStamp + randomIntever;
        Date finalDate = new Date(finalTimeStamp);
        return simpleDateFormat.format(finalDate);
    }

    public static void main(String[] args) throws ParseException {
        String[] emailSuffixs = {"qq.com"};
        for (int i = 0; i < 1000; i++) {
            System.out.println(String.format("%s - %s - %s - %s - %s - %s", makeName(null,null ,0 ),makeAge(0),makeSex(null),makeAddress(null,null,null, null ),makeEmail(emailSuffixs),makeDate(null, null, null)));
        }
    }
}
