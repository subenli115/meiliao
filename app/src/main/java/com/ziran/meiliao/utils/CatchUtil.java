package com.ziran.meiliao.utils;

import com.ziran.meiliao.envet.MyCallBack;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/11 10:30
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/11$
 * @updateDes ${TODO}
 */

public class CatchUtil {
    public static void execute(MyCallBack callback){
        try {
            callback.call();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
