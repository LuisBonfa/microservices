package com.senior.challenge.user.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public class VerifyDtoFields {

    public static void verifyNullAndAddToObject(Object dto, Object entity) {
        Method[] dtoMethods = dto.getClass().getDeclaredMethods();
        Method[] entityMethods = entity.getClass().getDeclaredMethods();
        for (Method method : dtoMethods) {
            String type = method.getName().substring(0, 3);
            String main = method.getName().substring(3);
            String setMethod = "set" + main;
            if (type.equals("get")) {
                try {
                    if (Optional.ofNullable(method.invoke(dto)).isPresent()) {

                        Method entityMethod = entity.getClass().getDeclaredMethod(setMethod, method.getReturnType());
                        entityMethod.invoke(entity, method.invoke(dto));
                    }
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
