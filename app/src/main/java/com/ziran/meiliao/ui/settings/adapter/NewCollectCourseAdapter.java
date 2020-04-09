package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.common.irecyclerview.widget.LoadMoreFooterView;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.widget.SimpleTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/15 16:57
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/15$
 * @updateDes ${TODO}
 */

public class NewCollectCourseAdapter extends MultiItemRecycleViewAdapter<Object> {
    private static final int TYPE_TITLE = 999;
    private static final int TYPE_CHILD = 998;
    private static final int TYPE_FOOTER = 997;
    private IRecyclerView mIRecyclerView;

    public NewCollectCourseAdapter(Context context) {
        super(context, new MyMultiItemTypeSupport1());
        mapData = new TreeMap<>();
        mapFooter = new TreeMap<>();
    }

    public NewCollectCourseAdapter(Context context, IRecyclerView recyclerView) {
        super(context, new MyMultiItemTypeSupport1());
        mapData = new TreeMap<>();
        mapFooter = new TreeMap<>();
        mIRecyclerView = recyclerView;
    }

    @Override
    public void convert(ViewHolderHelper helper, Object o, int position) {
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                SimpleTextView view = helper.getView(R.id.simpleTextView);
                HeadData data = EmptyUtils.parseObject(o);
                view.setTitle(parseTitle(data.getTitle()));
                break;
            case TYPE_CHILD:
                SearchItemBean searchItemBean = EmptyUtils.parseObject(o);
                helper.setText(R.id.tv_item_course_library_teacher_name, searchItemBean.getName());
                helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_pic, searchItemBean.getPicture(), R.mipmap.ic_loading_square);

                break;
            case TYPE_FOOTER:
                final FooterBean footerBean = EmptyUtils.parseObject(o);
                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        footerBean.setFlag(!footerBean.isFlag());
                        updateList(footerBean.getKey());
                    }
                });
                helper.setText(R.id.tv_footer_text, footerBean.getTitle());
                break;
        }
    }

    private String parseTitle(String title) {
        if (EmptyUtils.isEmpty(title)) return "";
        switch (title) {
            case "crowd":
                return "众筹课程";
            case "team":
                return "课程库";
            case "teacher":
                return "老师库";
            case "activity":
                return "工作坊";
        }
        return "";
    }

    private String flagKey;

    private void updateList(String key) {
        Set<String> keySet = mapFooter.keySet();
        List newResult = new ArrayList();
        boolean flag = false;
        for (String s : keySet) {
            FooterBean footerBean = mapFooter.get(s);
            if (key.equals(s)) {
                flag = footerBean.isFlag();
            } else {
                footerBean.setFlag(false);
            }
        }
        LogUtils.logd("flag:" + flag + " key:" + key + " keySet:" + keySet.toString());
        if (flag) {
            flagKey = key;
            if (mIRecyclerView != null) {
                mIRecyclerView.setLoadMoreEnabled(true);
            }
            List list = mapData.get(key);
            newResult.add(new HeadData(0, key, false));
            FooterBean footerBean = mapFooter.get(key);
            newResult.addAll(list);
            newResult.add(footerBean);
            replaceAll(newResult);
        } else {
            flagKey = null;
            if (mIRecyclerView != null) {
                mIRecyclerView.setLoadMoreEnabled(false);
            }
            refresh();
        }
        LogUtils.logd("mIRecyclerView:" + mIRecyclerView + "  " + mIRecyclerView.getLoadMoreEnabled());
    }

    public void onRefresh() {
        mapData.clear();
        mapFooter.clear();
        flagKey = null;
    }

    private static class MyMultiItemTypeSupport1 implements MultiItemTypeSupport<Object> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_TITLE:
                    return R.layout.item_team_detail_title;
                case TYPE_CHILD:
                    return R.layout.item_course_library_teacher;
                case TYPE_FOOTER:
                    return R.layout.item_footer_title;
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position, Object o) {
            return o instanceof HeadData ? TYPE_TITLE : o instanceof FooterBean ? TYPE_FOOTER : TYPE_CHILD;
        }
    }


    private Map<String, List> mapData;
    private Map<String, FooterBean> mapFooter;

    public void addData(String key, List data) {
        if (EmptyUtils.isEmpty(data)) return;
        if (mapData.containsKey(key)) {
            List list = mapData.get(key);
            list.addAll(data);
        } else {
            mapData.put(key, data);
            FooterBean footerBean = new FooterBean(key, false);
            mapFooter.put(key, footerBean);
        }
        refresh();
    }

    private void refresh() {
        List newResult = new ArrayList();
        Set<String> keySet = mapData.keySet();
        for (String key1 : keySet) {
            List list = mapData.get(key1);
            newResult.add(new HeadData(0, key1, false));
            if (EmptyUtils.isNotEmpty(list)) {
                FooterBean footerBean = mapFooter.get(key1);
                if (list.size() > 3) {
                    for (int i = 0; i < 3; i++) {
                        newResult.add(list.get(i));
                    }
                } else {
                    newResult.addAll(list);
                }
                newResult.add(footerBean);
            }
        }
        replaceAll(newResult);
    }

    public void updateData(String key, List data) {
        if (EmptyUtils.isEmpty(data)) return;
        List newResult = new ArrayList();
        List list = null;
        if (mapData.containsKey(key)) {
            list = mapData.get(key);
            list.addAll(data);
        } else {
            mapData.put(key, data);
            FooterBean footerBean = new FooterBean(key, false);
            mapFooter.put(key, footerBean);
        }
        newResult.add(new HeadData(0, key, false));
        FooterBean footerBean = mapFooter.get(key);
        newResult.addAll(list);
        newResult.add(footerBean);

        replaceAll(newResult);

        mIRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
    }


    public List getDataFormKey(String key) {
        return mapData.get(key);
    }

    public String getFlagKey() {
        return flagKey;
    }

    public class FooterBean {

        public FooterBean(String key, boolean flag) {
            this.key = key;
            this.flag = flag;
        }

        private String key;

        private boolean flag; // true收起 false 展开

        public String getKey() {
            return key;
        }

        public String getTitle() {
            return flag ? "收起" : "展开";
        }


        public void setKey(String key) {
            this.key = key;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
