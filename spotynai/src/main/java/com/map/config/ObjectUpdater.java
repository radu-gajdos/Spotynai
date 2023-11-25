package com.map.config;

import java.lang.reflect.Field;

public class ObjectUpdater {

    public static <T, U> void updateFields(T target, U source) {
        Field[] sourceFields = source.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            try {
                Object value = sourceField.get(source);
                if (value != null) {
                    Field targetField = target.getClass().getDeclaredField(sourceField.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                //TO_DO
            }
        }
    }
}
