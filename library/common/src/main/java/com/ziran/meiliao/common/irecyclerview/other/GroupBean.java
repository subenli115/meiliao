/*
 * Copyright (c) 2017. Xi'an iRain IOT Technology service CO., Ltd (ShenZhen). All Rights Reserved.
 */

package com.ziran.meiliao.common.irecyclerview.other;

import java.util.List;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @since 2017-09-07
 */
public class GroupBean<T> {
    public final String title;
    public final List<T> members;

    public GroupBean(String title, List<T> members) {
        this.title = title;
        this.members = members;
    }
}
