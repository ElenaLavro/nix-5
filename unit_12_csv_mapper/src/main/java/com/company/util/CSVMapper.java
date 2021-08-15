package com.company.util;

import com.company.annotation.Determinant;
import com.company.mapper.CSVTable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CSVMapper {
    public static <T> List<T> mapper(Class<T> tClass) {
        CSVTable table = CSVParser.readFromFile();
        List<T> result = new ArrayList<>();
        for (int i = 1; i < table.getSize(); i++) {
            T obj;
            try {
                Constructor<T> constructor = tClass.getConstructor();
                obj = constructor.newInstance();

                Field[] fields = result.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Determinant.class)) {
                        field.setAccessible(true);
                        Class<?> type = field.getType();
                        String cell = table.getValue(i, field.getAnnotation(Determinant.class).value());
                        setFieldType(type, field, cell, obj);
                    }
                }
                result.add(obj);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static <T> void setFieldType(Class<?> fieldType, Field field, String cell, T instance) throws IllegalAccessException {
        try {
            if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                field.set(instance, Integer.parseInt(cell));
            } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                field.set(instance, Double.parseDouble(cell));
            } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                field.set(instance, Float.parseFloat(cell));
            } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                field.set(instance, Long.parseLong(cell));
            } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                field.set(instance, Double.parseDouble(cell));
            } else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
                field.set(instance, Byte.parseByte(cell));
            } else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
                field.set(instance, Short.parseShort(cell));
            } else if (fieldType.equals(String.class)) {
                field.set(instance, cell);
            }
        } catch (IllegalAccessException e) {
            throw new IllegalAccessException(e.getMessage());
        }
    }
}
