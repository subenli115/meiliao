package cn.rongcloud.live.controller;

import android.net.Uri;
import android.os.Parcel;

/**
 * Created by Administrator on 2017/2/16.
 */

public class MyUserBean extends io.rong.imlib.model.UserInfo {

    private String headImg ;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public MyUserBean(String id, String name, Uri portraitUri) {
        super(id, name, portraitUri);
    }
    public MyUserBean(String id, String name,String  headImg) {
        super(id, name, null);
    }

    public MyUserBean(Parcel in, String headImg) {
        super(in);
    }
    @Override
    public String toString() {
        return "MyUserBean{" +
                "name='" + getName() + '\'' + "headImg = " + getHeadImg() +
                "userId='" + getUserId() + '\''  +
                '}';
    }
}
