package com.ziran.meiliao.ui.priavteclasses.util;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/15 14:32
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/15$
 * @updateDes ${TODO}
 */

public class ResultUtil {

    public static List<Result> getData(int count){
        List<Result>list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Result());
        }
        return list;
    }
}
