package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.WrapperAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.priavteclasses.adapter.MainSJKLiveAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class SJKLiveBean extends Result {

    /**
     * data : {"history":[{"picture":null,"id":1,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":2,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":3,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":4,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":5,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":6,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":7,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":8,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":9,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":10,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":11,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":12,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":13,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":14,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":15,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":16,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":17,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":18,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":19,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":20,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"}],"trailer":[{"picture":null,"id":1,"author":{"name":"测试"},
     * "title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":2,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,
     * "id":3,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":4,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":5,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":6,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":7,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":8,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":9,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":10,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":11,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":12,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":13,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":14,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":15,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":16,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":17,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":18,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":19,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":20,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"}],"pics":[{"link":"这是一个路径",
     * "type":"h5","url":"https://www.psytap.com/wpyx_manager/static/images/coursePics/1.jpg"},{"link":"这是一个Id","type":"native",
     * "url":"https://www.psytap.com/wpyx_manager/static/images/coursePics/2.jpg"}],"list":[{"picture":null,"id":1,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":2,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":3,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":4,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":5,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":6,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":7,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":8,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":9,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":10,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":11,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":12,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":13,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":14,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":15,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":16,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":17,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"},{"picture":null,"id":18,"author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":19,
     * "author":{"name":"测试"},"title":"七天睡眠治疗","price":"0.01"},{"picture":null,"id":20,"author":{"name":"测试"},"title":"七天睡眠治疗",
     * "price":"0.01"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<SJKSingeLiveData> history;
        //预告
        private List<SJKSingeLiveData> trailer;
        //轮播预告图
        private List<PicsBean> pics;
        //直播数据
        private List<SJKSingeLiveData> zhibo;

        public List<SJKSingeLiveData> getHistory() {
            return history;
        }

        public void setHistory(List<SJKSingeLiveData> history) {
            this.history = history;
        }

        public List<SJKSingeLiveData> getTrailer() {
            return trailer;
        }

        public void setTrailer(List<SJKSingeLiveData> trailer) {
            this.trailer = trailer;
        }

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public List<SJKSingeLiveData> getZhibo() {
            return zhibo;
        }

        public void setList(List<SJKSingeLiveData> list) {
            this.zhibo = list;
        }
        @Override
        public String toString() {
            return "DataBean{" + "history=" + history + ", trailer=" + trailer + ", pics=" + pics + ", zhibo=" + zhibo + '}';
        }
    }

    @Override
    public String toString() {
        return "SJKLiveBean{" + "data=" + data + '}';
    }

    public static List<SJKSingeLiveData> getData(SJKLiveBean bean) {
        if (EmptyUtils.isEmpty(bean.getData())) return null;
        List<SJKSingeLiveData> data = new ArrayList<>();
        DataBean dataBean = bean.getData();
        if (EmptyUtils.isNotEmpty(dataBean.getZhibo())) {
            data.add(getTitle(WrapperAdapter.TITLE, "精彩直播", true));
            data.addAll(mapData(dataBean.getZhibo(), WrapperAdapter.TYPE_NOW));
        }
        if (EmptyUtils.isNotEmpty(dataBean.getTrailer())) {
            data.add(getTitle(WrapperAdapter.TITLE, "精彩预告", true));
            data.addAll(mapData(dataBean.getTrailer(), WrapperAdapter.TYPE_Trailer));
        }
        if (EmptyUtils.isNotEmpty(dataBean.getHistory())) {
            SJKSingeLiveData sjkSingeLiveData = new SJKSingeLiveData();
            sjkSingeLiveData.setHistoryData(dataBean.getHistory());
            sjkSingeLiveData.setItemType(MainSJKLiveAdapter.TYPE_HISTORY);
            data.add(sjkSingeLiveData);
        }
        return data;
    }



    public static List<SJKSingeLiveData> mapData(List<SJKSingeLiveData> datas, int itemType) {
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setItemType(itemType);
        }
        return datas;
    }

    public static SJKSingeLiveData getTitle(int itemType, String title, boolean hasMore) {
        SJKSingeLiveData tempData = new SJKSingeLiveData();
        tempData.setItemType(itemType);
        tempData.setTitle(title);
        tempData.setHasMore(hasMore);
        return tempData;
    }


}
