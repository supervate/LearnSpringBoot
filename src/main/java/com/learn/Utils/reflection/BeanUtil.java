package com.learn.Utils.reflection;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author vate
 * @date 2018
 * 该工具类可以针对所有实体对象进行一些动态操作
 *      如获取该对象中的所有字段
 *      如对两个对象(同类或不同类的对象)进行复制操作等
 * 其核心原理基于Java的反射机制
 */
public class BeanUtil {

    public static Field[] getAllFields(Object src){
       Class clazz = src.getClass();
       Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
        }
        return fields;
    }

    public static Field getFieldByName(Object src,String fieldName) throws NoSuchFieldException {
        Class clazz = src.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    public static boolean setFieldValue(Object src, String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = src.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        Class fieldClass = field.getType();
        field.setAccessible(true);
        if (fieldValue.getClass().isAssignableFrom(fieldClass)){
            field.set(src, fieldValue);
            return true;
        }
        else {
            return false;
        }
    }

    public static Object getFieldValue(Object src,String fieldName) throws NoSuchFieldException, IllegalAccessException {
       Field field = getFieldByName(src, fieldName);
       return field.get(src);
    }

    public static void copyBean(Object src,Object target,String... ignoreFieldNames) throws IllegalAccessException, NoSuchFieldException {
        HashSet ignoreFields = new HashSet();
        ignoreFields.addAll(Arrays.asList(ignoreFieldNames));
        if (src.getClass().getName().equals(target.getClass().getName())){
            doCopy_sameClass(src, target,ignoreFields);
        }
        else {
            doCopy_diffClass(src, target, ignoreFields);
        }
    }

    private static void doCopy_sameClass(Object src, Object target, Set ignoreFields) throws IllegalAccessException, NoSuchFieldException {
        Field[] fields = getAllFields(src);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (!ignoreFields.contains(fieldName)){
                if (!setFieldValue(target,field.getName(), field.get(src))){
                    throw new RuntimeException(String.format("Set fieldValue Error!The Src's field type is diffrent of target's field type!The FieldName is : %s",fieldName));
                }
            }
        }
    }

    private static void doCopy_diffClass(Object src, Object target, Set ignoreFields) throws IllegalAccessException, NoSuchFieldException {
        Field[] srcFields = getAllFields(src);
        Field[] tarFields = getAllFields(target);
        HashMap<String,Field> tarFieldMap = new HashMap<>();
        for (int i = 0; i < tarFields.length; i++) {
            Field tarField = tarFields[i];
            tarFieldMap.put(tarField.getName(), tarField);
        }
        for (int i = 0; i < srcFields.length; i++) {
            Field srcField = srcFields[i];
            String fieldName = srcField.getName();
            if (tarFieldMap.containsKey(fieldName) && !ignoreFields.contains(fieldName)){
                if (!setFieldValue(target, fieldName, srcField.get(src))){
                    throw new RuntimeException(String.format("Set fieldValue Error!The Src's field type is diffrent of target's field type!The FieldName is : %s",fieldName));
                }
            }
        }
    }

    public static String[] getNullValueFields(Object src) throws IllegalAccessException {
        Field[] fields = getAllFields(src);
        List<String> nullFieldNames = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.get(src) == null){
                nullFieldNames.add(field.getName());
            }
        }
        return nullFieldNames.toArray(new String[nullFieldNames.size()]);
    }

}
