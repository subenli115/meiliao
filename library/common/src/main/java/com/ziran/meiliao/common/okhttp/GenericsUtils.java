package com.ziran.meiliao.common.okhttp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/6 18:47
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/6$
 * @updateDes ${TODO}
 */

public class GenericsUtils {

    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}
