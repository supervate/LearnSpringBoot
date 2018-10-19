package com.learn.Utils.applicationPropertiesRead;

import java.util.Map;

/**
 * 包含项目中的常量配置信息
 *  既包含有手动添加,
 *  也包含从application-xxx.properties文件中读取到的属性信息(在项目启动时通过调用RqConstantFill类的方法注入)
 */
public class Constant {
    /**
     *  包含application.properties
     *    以及 application-{spring.profiles.active}.properties中配置的属性值
     *  (spring.profiles.active在application.properties中配置)
     *  暂时只包含:
     *    application.properties + 一个指定的application-{spring.profiles.active}.properties 文件中的属性
     */
    public static Map applicationProperties;
}
