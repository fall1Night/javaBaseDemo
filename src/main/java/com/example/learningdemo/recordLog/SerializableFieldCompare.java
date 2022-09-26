package com.example.learningdemo.recordLog;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializableFieldCompare {
    public static <T> List<Map<String, Object>> compare(Class<T> type, T newObject, T oldObject) throws Exception {
        List<Map<String, Object>> logList = new ArrayList<>();
        Class<?> newObj = newObject.getClass();
        Class<?> oldObj = oldObject.getClass();
        // 获取Class中属性数组
        Field[] newFields = type.getDeclaredFields();
        // 属性数组遍历比较,记录属性不同的字段
        for (int i = 0; i < newFields.length; i++) {
            // 通过注解获取对象中属性的值,只有没添加上@FieldCompare注解的属性才不会返回null
            FieldCompare newAnnotation = newFields[i].getAnnotation(FieldCompare.class);
            Map<String, Object> map = new HashMap<>();
            if (null != newAnnotation) {
                //获取Bean对象对应属性的值
                PropertyDescriptor newPd = new PropertyDescriptor(newFields[i].getName(), newObj);
                Method getMethodNew = newPd.getReadMethod();
                Object oldVal = getMethodNew.invoke(oldObject);
                Object newVal = getMethodNew.invoke(newObject);
                if (oldVal == null && newVal == null) {
                    break;
                }
                boolean eq = false;
                if (oldVal != null) {
                    if (oldVal instanceof String) {
                        String s1 = String.valueOf(oldVal).trim();
                        String s2 = String.valueOf(newVal).trim();
                        eq = !s1.equals(s2);
                    } else if (oldVal instanceof Integer) {
                        int i1 = (Integer) oldVal;
                        int i2 = (Integer) newVal;
                        eq = i1 != i2;
                    } else if (oldVal instanceof Double) {
                        double d1 = (Double) oldVal;
                        double d2 = (Double) newVal;
                        eq = d1 != d2;
                    } else if (oldVal instanceof BigDecimal) {
                        BigDecimal b1 = (BigDecimal) oldVal;
                        BigDecimal b2 = (BigDecimal) newVal;
                        eq = b1.compareTo(b2) != 0;
                    } else if (oldVal instanceof Long) {
                        long l1 = (Long) oldVal;
                        long l2 = (Long) newVal;
                        eq = l1 != l2;
                    }
                } else {
                    if (newVal instanceof String) {
                        String s1 = String.valueOf(newVal).trim();
                        String s2 = String.valueOf(oldVal).trim();
                        eq = !s1.equals(s2);
                    } else if (newVal instanceof Integer) {
                        int i1 = (Integer) newVal;
                        int i2 = (Integer) oldVal;
                        eq = i1 != i2;
                    } else if (newVal instanceof Double) {
                        double d1 = (Double) newVal;
                        double d2 = (Double) oldVal;
                        eq = d1 != d2;
                    } else if (newVal instanceof BigDecimal) {
                        BigDecimal b1 = (BigDecimal) newVal;
                        BigDecimal b2 = (BigDecimal) oldVal;
                        if (oldVal == null) {
                            eq = true;

                        } else {
                            eq = b1.compareTo(b2) != 0;
                        }
                    } else if (newVal instanceof Long) {
                        long l1 = (Long) newVal;
                        long l2 = (Long) oldVal;
                        eq = l1 != l2;
                    }
                }

                String s1 = oldVal == null ? "" : oldVal.toString().trim();
                String s2 = newVal == null ? "" : newVal.toString().trim();
                if (eq) {

                    map.put("ParamName", newFields[i].getName());
                    map.put("ParamChineseName", newAnnotation.chineseName());
                    map.put("oldParam", oldVal);
                    map.put("newParam", newVal);
                }
                logList.add(map);


/*
                if (eq) {
                    Map<String, String> map = codeToNameMap(newAnnotation);
                    if (map.size() > 0) {
                        logList.add(newAnnotation.chineseName() + "由：[" + map.get(s1) + "]更改为了：[" + map.get(s2) + "]");
                    }else {
                        logList.add(newAnnotation.chineseName() + "由：[" + s1 + "]更改为了：[" + s2 + "]");
                    }
                }
*/
            }
        }
        return logList;
    }

    private static Map<String, String> codeToNameMap(FieldCompare newAnnotation) {
        Map<String, String> map = new HashMap<>();
        String properties = newAnnotation.properties();
        if (StringUtils.isNotBlank(properties)) {
            String[] propertiesArr = properties.split(",");
            for (String propertie : propertiesArr) {
                String[] split = propertie.split(":");
                map.put(split[0], split[1]);
            }
        }
        return map;
    }
}
