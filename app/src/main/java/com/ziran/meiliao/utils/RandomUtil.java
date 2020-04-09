package com.ziran.meiliao.utils;

import java.util.Random;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 14:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class RandomUtil {
    private static Random sRandom = new Random();

    public static Random getRandom(){
        return sRandom;
    }
}
