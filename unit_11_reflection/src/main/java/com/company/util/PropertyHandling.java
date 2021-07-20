package com.company.util;

import com.company.annotation.PropertyKey;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PropertyHandling {
    public static <T> T ManagingProperties(Properties properties, Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getConstructor();
            T instance = constructor.newInstance();
            for (Field field : tClass.getDeclaredFields()) {
                field.setAccessible(true);
                PropertyKey key = field.getAnnotation(PropertyKey.class);
                if (key == null) continue;

                String propertiesValue = properties.getProperty(key.value());

                Class<?> typeOfFile = field.getType();

                if (typeOfFile.equals(String.class)) {
                    field.set(instance, propertiesValue);
                } else if (typeOfFile.equals(Integer.class) || typeOfFile.equals(int.class)) {
                    field.setInt(instance, Integer.parseInt(propertiesValue));
                } else if (typeOfFile.equals(Double.class) || typeOfFile.equals(double.class)) {
                    field.setDouble(instance, Double.parseDouble(propertiesValue));
                } else if (typeOfFile.equals(Boolean.class) || typeOfFile.equals(boolean.class)) {
                    field.setBoolean(instance, Boolean.parseBoolean(propertiesValue));
                } else if (typeOfFile.equals(Float.class) || typeOfFile.equals(float.class)) {
                    field.setFloat(instance, Float.parseFloat(propertiesValue));
                } else if (typeOfFile.equals(Byte.class) || typeOfFile.equals(byte.class)) {
                    field.setByte(instance, Byte.parseByte(propertiesValue));
                } else if (typeOfFile.equals(Long.class) || typeOfFile.equals(long.class)) {
                    field.setLong(instance, Long.parseLong(propertiesValue));
                }
            }
            return instance;
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
