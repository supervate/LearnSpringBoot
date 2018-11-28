//package com.learn.ElasticSearch;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.sun.org.apache.xerces.internal.xs.StringList;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * 用来造es数据的
// */
//public class ma {
//    public static void main(String[] args) {
//
//        String jsonindex = "{" +
//                "\"index\":{\"_id\":\"1\"}\n" +
//                "}";
//        System.out.println(makeJsonINdex(jsonindex,1000 ));
//    }
//
//    public static String makeJsonINdex(String jsonindex,int times){
//        JSONObject jsonObject = JSONObject.parseObject(jsonindex);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i=0;i<times;i++){
//            Map index = jsonObject.getJSONObject("index");
//            index.put("_id", i+1);
//            stringBuilder.append(jsonObject.toJSONString());
//            stringBuilder.append("\r\n");
//            String Jsonobj = "{" +
//                    "\"age\": 29," +
//                    "\"name\": \"F\"," +
//                    "\"birthday\": \"Euron\"," +
//                    "\"email\": \"bradshawmckenzie@euron.com\"" +
//                    "}";
//            stringBuilder.append(makeJsonObj(Jsonobj));
//            stringBuilder.append("\r\n");
//        }
//        return stringBuilder.toString();
//    }
//    public static String makeJsonObj(String jsonstr){
//
//        JSONObject jsonObject = JSONObject.parseObject(jsonstr);
//
//        jsonObject.put("age",(int)(Math.random()*100));
//        jsonObject.put("complex_age",Math.random()*100);
//        jsonObject.put("name",randomStringList().get((int)Math.random()*10));
//        jsonObject.put("email", randomStringList().get((int)Math.random()*10)+"@qq.com");
//        jsonObject.put("birthday",randomDate());
//        return jsonObject.toString();
//    }
//
//
//    public static List<String> randomStringList() {
//        List<String> stringList = new ArrayList<String>();
//
//        char[] charAndnum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
//        Random random = new Random(); //用于产生随机数
//        for (int j = 0; j < 10; j++) {
//            String string = new String();
//            do{
//                int sLength = random.nextInt(10);
//
//                for(int i = 0;i<((sLength==0)?1:sLength);i++){
//                    string+= charAndnum[random.nextInt(25)];
//                }
//            }while(stringList.contains(string));
//            stringList.add(string);
//        }
//        return stringList;
//    }
//
//    public static String randomDate(){
//        Random rand = new Random();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        cal.set(1950, 0, 1);
//        long start = cal.getTimeInMillis();
//        cal.set(2008, 0, 1);
//        long end = cal.getTimeInMillis();
//        Date d = new Date(start + (long)(rand.nextDouble() * (end - start)));
//        return format.format(d);
//    }
//
//}
