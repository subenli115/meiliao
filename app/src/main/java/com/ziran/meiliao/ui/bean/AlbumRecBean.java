package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/22 14:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/22$
 * @updateDes ${TODO}
 */

public class AlbumRecBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 静心
         * albumList : [{"picture":"http://www.dgli.net:8888/resource/images/album/zf/14.1.png","id":14,"vip":"vip",
         * "author":{"name":"张庆祥"},"title":"黄庭禅坐","detail":"清净本性","listenCount":120},{"picture":"http://www.dgli
         * .net:8888/resource/images/album/zf/15.1.png","id":15,"vip":"vip","author":{"name":"张德芬"},"title":"黄庭禅坐",
         * "detail":"问题和困扰不是来找麻烦，而是助你内在成长","listenCount":170},{"picture":"http://www.dgli.net:8888/resource/images/album/zf/18.1.png",
         * "id":18,"vip":"限免","author":{"name":"杨建铭"},"title":"完全减压放松法","detail":null,"listenCount":285},{"picture":"http://www.dgli
         * .net:8888/resource/images/album/zf/19.png","id":19,"vip":"vip","author":{"name":"陈梦怡"},"title":"呼吸的自愈力","detail":null,
         * "listenCount":138},{"picture":"http://www.dgli.net:8888/resource/images/album/zf/20.1.png","id":20,"vip":"限免",
         * "author":{"name":"张芝华"},"title":"找回内心的真爱","detail":null,"listenCount":325},{"picture":"http://www.dgli
         * .net:8888/resource/images/album/zf/21.1.png","id":21,"vip":"vip","author":{"name":"修·蓝"},"title":"回到内在的平静","detail":"",
         * "listenCount":211},{"picture":"http://www.dgli.net:8888/resource/images/album/zf/22.1.png","id":22,"vip":"vip",
         * "author":{"name":"kr女士"},"title":"真正的自己","detail":null,"listenCount":162}]
         */

        private String name;
        private List<AlbumListBean> albumList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AlbumListBean> getAlbumList() {
            return albumList;
        }

        public void setAlbumList(List<AlbumListBean> albumList) {
            this.albumList = albumList;
        }

        @Override
        public String toString() {
            return "DataBean{" + "name='" + name + '\'' + ", albumList=" + albumList + '}';
        }
    }
}
