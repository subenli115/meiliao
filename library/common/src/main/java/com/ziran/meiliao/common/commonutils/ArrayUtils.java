package com.ziran.meiliao.common.commonutils;

import android.content.Context;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class ArrayUtils {

    public static List<String> getList(Context context, int arrayId) {
        String[] array = context.getResources().getStringArray(arrayId);
        return Arrays.asList(array);
    }

    public static String[] getArray(Context context, int arrayId) {
        return context.getResources().getStringArray(arrayId);
    }

    public static String[] toArray(List<String> list ,String baseUrl) {
        if (EmptyUtils.isNotEmpty(list)) {
            String[] result = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = baseUrl + list.get(i);
            }
            return result;
        }
        return null;
    }

}
