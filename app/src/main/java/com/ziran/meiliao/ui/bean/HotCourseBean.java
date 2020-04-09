package com.ziran.meiliao.ui.bean;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/21 18:47
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/21$
 * @updateDes ${TODO}
 */

public class HotCourseBean {
    private String title;
    private  List<SJKSingeLiveData> data ;

    public HotCourseBean(String title, List<SJKSingeLiveData> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SJKSingeLiveData> getData() {
        return data;
    }

    public void setData(List<SJKSingeLiveData> data) {
        this.data = data;
    }
}
