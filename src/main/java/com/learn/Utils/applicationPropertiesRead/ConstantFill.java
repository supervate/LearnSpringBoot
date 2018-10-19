package com.learn.Utils.applicationPropertiesRead;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author vate
 *  从application-xxx.properties文件中读取到的属性信息(在项目启动时通过调用RqConstantFill类的方法注入)
 */
@Slf4j
public class ConstantFill {

    //手动注入application.properties和application-xxx.properties的属性值到常量类中
    public static void injectApplicationProperties(){
        Map propertiesMap = getApplicationProperties();
        Constant.applicationProperties = propertiesMap;
    }

    /**
     * 读取application.properties的属性值到常量类中
     * 如果配置了spring.profiles.active 属性的值,则同时也会读取:
     *    application-{spring.profiles.active}.properties中的值
     * @return
     */
    public static Map getApplicationProperties() {
        try {
            Map propertiesMap = new HashMap();
            ClassLoader classloader = ConstantFill.class.getClassLoader();
            InputStream appstream = classloader.getResourceAsStream("application.properties");
            Properties apppro = new Properties();
            apppro.load(appstream);
            //把application.properties的属性存入map
            propertiesMap.putAll(apppro);
            Object profileActiveObj = apppro.get("spring.profiles.active");
            if (!Objects.isNull(profileActiveObj)) {

                //如果设置了profile属性,则读取对应的属性文件
                String profileActive = (String) profileActiveObj;
                if (StringUtils.isNotBlank(profileActive)) {
                    switch (profileActive) {
                        case "dev": {
                            InputStream appstream_dev = classloader.getResourceAsStream("application-" + profileActive + ".properties");
                            //如果配置文件不存在,则不加载
                            if (appstream_dev != null){
                                Properties apppro_dev = new Properties();
                                apppro_dev.load(appstream_dev);
                                //把application-dev.properties的属性存入map
                                propertiesMap.putAll(apppro_dev);
                                return propertiesMap;
                            }
                        }
                        case "pro": {
                            InputStream appstream_pro = classloader.getResourceAsStream("application-" + profileActive + ".properties");
                            //如果配置文件不存在,则不加载
                            if (appstream_pro != null){
                                Properties apppro_pro = new Properties();
                                apppro_pro.load(appstream_pro);
                                //把application-pro.properties的属性存入map
                                propertiesMap.putAll(apppro_pro);
                                return propertiesMap;
                            }
                        }
                    }
                }

            }
            //未指定profile属性，则直接返回application.properties文件的属性信息
            return propertiesMap;
        } catch (IOException e) {
            log.error("系统属性文件加载失败!请确认application-xxx.properties类属性文件是否正确！", e);
            throw new RuntimeException("系统属性文件加载失败!");
        }
    }
}
