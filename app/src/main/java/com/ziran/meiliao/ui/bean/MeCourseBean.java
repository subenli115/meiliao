package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.CourseEntry;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class MeCourseBean extends Result {

    private List<CourseEntry> data;

    public List<CourseEntry> getData() {
        return data;
    }

    public void setData(List<CourseEntry> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "MeCourseBean{" +
                "data=" + data + " resultCode = " + resultCode +
                '}';
    }
}
