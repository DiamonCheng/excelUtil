package com.dc.excel.util;

import java.lang.annotation.*;

/**
 * 在Java Bean 中字段，类名上注解
 * 类名：指定Sheet名称
 * 字段：指定字段对应的表头名称
 *
 * @author DC
 * @date 2018/4/19.
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExcelHead {
    String value();
}
