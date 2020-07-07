package com.okhttplib.annotation;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 请求方法
 *
 * @author zhousf
 */
@IntDef({RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Retention(RetentionPolicy.SOURCE)
public @interface RequestMethod {
    int POST = 1;
    int GET = 2;
    int PUT = 3;
    int DELETE = 4;
}