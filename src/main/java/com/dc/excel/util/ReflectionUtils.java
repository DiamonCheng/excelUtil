/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dc.excel.util;


import java.lang.reflect.Field;

public class ReflectionUtils {
    
    public static void setValueByName(Object target,Object value,String fieldName){
        Class cls=target.getClass();
        while(!Object.class.equals(cls)){
            Field[] fields=cls.getDeclaredFields();
            for (Field field:fields){
                if (field.getName().equals(fieldName)){
                    field.setAccessible(true);
                    try {
                        field.set(target,value);
                        return;
                    } catch (IllegalAccessException e) {
                         throw new IllegalArgumentException(e);
                    }
                }
            }
            
            cls=cls.getSuperclass();
        }
        throw new IllegalArgumentException("field not found: "+target.getClass()+"."+fieldName);
    }
    
    public static <T> T getValueByName(Object target, String fieldName){
        Class cls=target.getClass();
        while(!Object.class.equals(cls)){
            Field[] fields=cls.getDeclaredFields();
            for (Field field:fields){
                if (field.getName().equals(fieldName)){
                    field.setAccessible(true);
                    try {
                        return (T) field.get(target);
                    } catch (IllegalAccessException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
            
            cls=cls.getSuperclass();
        }
        throw new IllegalArgumentException("field not found: "+target.getClass()+"."+fieldName);
    }
    
}
