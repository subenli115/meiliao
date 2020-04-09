package com.ziran.meiliao.ui.settings.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.slide.ISlideHelper;
import com.ziran.meiliao.common.irecyclerview.slide.SlideHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.common.irecyclerview.widget.LoadMoreFooterView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.ui.adapter.helper.CourseLibraryTeamHelper;
import com.ziran.meiliao.ui.adapter.helper.WordshopsHelper;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.workshops.widget.ProgressTipsView;
import com.ziran.meiliao.utils.HtmlUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.SimpleTextView;
import com.ziran.meiliao.widget.SmoothCheckBox;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.SimpleDialogClickListener;

import java.util.ArrayList;
import java.util.HashSet;
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

public class NewCollectCourse2Adapter extends MultiItemRecycleViewAdapter<Object> implements ISlideHelper, AppConstant.CollectCourse {
    private static final int TYPE_TITLE = 999;
    private static final int TYPE_CHILD = 998;
    private static final int TYPE_FOOTER = 997;
    private IRecyclerView mIRecyclerView;
    /**
     * 选中的下标
     */
    protected HashSet<Integer> positionSet;

    /**
     * 滑动偏移工具类
     */
    private SlideHelper mISlideHelper;
    /**
     * 是否选中所有item
     */
    private boolean isSelectAll;


    private static final int CLILD_ACTIVITY = 5551;
    private static final int CLILD_CROWD = 5552;
    private static final int CLILD_TEAM = 5553;
    private static final int CLILD_TEACHER = 5554;

    public NewCollectCourse2Adapter(Context context, IRecyclerView recyclerView) {
        super(context, new MyMultiItemTypeSupport1());
        mapData = new TreeMap<>();
        mapFooter = new TreeMap<>();
        mIRecyclerView = recyclerView;

        mISlideHelper = new SlideHelper();
        positionSet = new HashSet<>();
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
            case TYPE_FOOTER:
                return super.onCreateViewHolder(parent, viewType);
        }
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        OneSlideViewHolder viewHolder = OneSlideViewHolder.get(mContext, null, parent, layoutId, -1);
        mISlideHelper.add(viewHolder);
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    /**
     * 绑定  OneSlideViewHolder
     *
     * @param holder   OneSlideViewHolder对象
     * @param position 下标
     */
    @Override
    public void onBindViewHolder(ViewHolderHelper holder, final int position) {
        if (holder instanceof OneSlideViewHolder) {
            OneSlideViewHolder slideViewHolder = (OneSlideViewHolder) holder;
            slideViewHolder.onBindSlide(holder.getView(onBindSlideViewId()));
            holder.updatePosition(position);
            //绑定数据
            convertData(slideViewHolder, mDatas.get(position), position);

            SmoothCheckBox smoothCheckBox = holder.getView(R.id.include_smooth_checkbox);
            slideViewHolder.setCheckBox(smoothCheckBox);
            smoothCheckBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                    addOrRemove(position, isChecked);
                }
            });
            if (isCheck() && !smoothCheckBox.isShown()) {
                smoothCheckBox.setVisibility(View.VISIBLE);
            }
            smoothCheckBox.setChecked(positionSet.contains(position));
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    /**
     * 关闭偏移
     */
    public void slideClose() {
        mISlideHelper.slideClose();
    }

    /**
     * 操作Item记录集合
     */
    public void addOrRemove(int position, boolean isCheck) {
        if (isCheck) {
            // 如果包含，则撤销选择
            positionSet.add(position);
        } else {
            // 如果不包含，则添加
            positionSet.remove(position);
        }
        OneSlideAdapter.DeleteItem.setAllSize(getAllSize());
        OneSlideAdapter.DeleteItem.setSelectSize(getSelectSize());
        isSelectAll = OneSlideAdapter.DeleteItem.isSelectAll();
        RxManagerUtil.post(AppConstant.RXTag.UPDATE_SEL, getSelectSize());
    }

    public int getAllSize() {
        List<Object> all = getAll();
        int size = 0;
        for (Object o : all) {
            if (o instanceof HeadData || o instanceof FooterBean) continue;
            size++;
        }
        return size;
    }

    /**
     * 删除选中的数据
     *
     * @param msg 对话框提示的内容
     */
    public void delete(String msg) {
        MDAlertDialog.createDialog(mContext, msg, new SimpleDialogClickListener() {
            @Override
            public void clickRightButton(Dialog dialog, View view) {
                dialog.dismiss();

                HashSet valueSet = new HashSet<>();
                for (Integer integer : positionSet) {
                    valueSet.add(get(integer));
                }
                for (Object t : valueSet) {
                    delete(t);
                }
                deleteSlef(valueSet);
                removeAll(valueSet);
                clearSelect();
                RxManagerUtil.post(AppConstant.RXTag.CLOSE, "1");
//                RxManagerUtil.post(AppConstant.RXTag.DELETE, from_type);
            }
        });
    }

    /**
     * 删除单个item
     *
     * @param t item数据
     */
    protected void delete(Object t) {

    }

    /**
     * 删除单个item
     *
     * @param valueSet item数据集
     */
    protected void deleteSlef(HashSet valueSet) {
        if (EmptyUtils.isEmpty(valueSet)) return;
        String api= "";
        Map<String, String> collect = MapUtils.getOnlyCan("collect", "0");


        for (Object bean : valueSet) {
            if (bean instanceof SearchItemBean){
                SearchItemBean itemBean = (SearchItemBean) bean;
                switch (itemBean.getType()){
                    case ITEM_TYPE_CROWD_FUND:
                        api = ApiKey.CROWN_FUND_COLLECT_CROWN_FUND;
                        collect.put("crownFundId",itemBean.getId());
                        delete(ITEM_TYPE_CROWD_FUND,itemBean);
                         break;
                    case ITEM_TYPE_TEAM:
                        api = ApiKey.MISSION_BUILT_COLLECT_MISSION_BUILT;
                        collect.put("missionBuiltId",itemBean.getId());
                        delete(ITEM_TYPE_TEAM,itemBean);
                        break;
                    case ITEM_TYPE_ACTIVITY:
                        api = ApiKey.COLLECT_ACT;
                        collect.put("activityIds",itemBean.getId());
                        delete(ITEM_TYPE_ACTIVITY,itemBean);
                        break;
                    case ITEM_TYPE_TEACHER:
                        api = ApiKey.FAMOUS_TEACHERS_COLLECT_FAMOUS_TEACHER;
                        collect.put("famousTeacherId",itemBean.getId());
                        delete(ITEM_TYPE_TEACHER,itemBean);
                        break;
                }
                OkHttpClientManager.postAsync(api,collect, new NewRequestCallBack<Result>(Result.class) {
                    @Override
                    public void onSuccess(Result result) {

                    }
                });
            }
        }
       refresh();
    }

    /**
     * 获取选中的数量
     *
     * @return 选中的数量
     */
    public int getSelectSize() {
        return positionSet.size();
    }

    /**
     * @return 绑定偏移的控件id
     */
    public int onBindSlideViewId() {
        return R.id.item_content_rl;
    }


    /**
     * 清楚选中的数据
     */
    public void clearSelect() {
        positionSet.clear();
    }

    /**
     * 全选或取消全选
     */
    public void selectAll() {
        if (!isSelectAll) {
            positionSet.clear();
            isSelectAll = true;
            for (int i = 0; i < mDatas.size(); i++) {
                if (mDatas.get(i) instanceof SearchItemBean) {
                    positionSet.add(i);
                }
            }
            LogUtils.logd("positionSet:select" + positionSet.toString());
            notifyDataSetChanged();
        } else {
            positionSet.clear();
            isSelectAll = false;
            LogUtils.logd("positionSet:unSelect" + positionSet.toString());
            notifyDataSetChanged();
        }
    }

    /**
     * 绑定数据
     *
     * @param helper   OneSlideViewHolder对象对象
     * @param itemData item数据
     * @param position 下标
     */
    public void convertData(OneSlideViewHolder helper, Object itemData, int position) {
        convert(helper, itemData, position);
    }


    @Override
    public void convert(ViewHolderHelper helper, Object o, int position) {
        SearchItemBean searchItemBean;
        switch (getItemViewType(position)) {
            case TYPE_TITLE:
                SimpleTextView view = helper.getView(R.id.simpleTextView);
                HeadData data = EmptyUtils.parseObject(o);
                view.setTitle(parseTitle(data.getTitle()));
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
            case CLILD_CROWD:
                 searchItemBean = (SearchItemBean) o;
                helper.setImageUrlTarget(R.id.iv_item_workshops_main_crowd_funding_pic,searchItemBean.getPicture(),R.mipmap.ic_loading_square_big);
                helper.setText(R.id.tv_item_workshops_main_crowd_funding_title,searchItemBean.getTitle());
                helper.setText(R.id.tv_item_workshops_main_crowd_funding_price, HtmlUtil.format("¥%d",searchItemBean.getAvgPrice()));
                helper.setText(R.id.tv_item_workshops_main_crowd_funding_people,String.valueOf(searchItemBean.getTargetMembers()));
                helper.setText(R.id.tv_item_workshops_main_crowd_funding_date,searchItemBean.getTotalTime());
                ProgressTipsView progressTipsView = helper.getView(R.id.progressTipsView);
                progressTipsView.setParms(searchItemBean.getSupportMembers(), searchItemBean.getLeftTime(),searchItemBean.getTargetMembers());
                break;
            case CLILD_TEAM:
                CourseLibraryTeamHelper.setTeam(helper,o,position,false);
                break;
            case CLILD_TEACHER:
                 searchItemBean = (SearchItemBean) o;
                helper.setImageUrlTarget(R.id.iv_item_course_library_teacher_pic, searchItemBean.getPicture(), R.mipmap.ic_loading_square_small);
                helper.setText(R.id.tv_item_course_library_teacher_name, searchItemBean.getName());
                helper.setText(R.id.tv_item_course_library_teacher_des, searchItemBean.getIntro());
                helper.setText(R.id.tv_item_course_library_teacher_count, HtmlUtil.format("共%d个课程", searchItemBean.getCourseNumbers()));
                helper.setVisible(R.id.view_line, false);
                break;
            case CLILD_ACTIVITY:
                WordshopsHelper.convert(helper,  o, position, false);
                break;
        }
    }

    private String parseTitle(String title) {
        if (EmptyUtils.isEmpty(title)) return "";
        switch (title) {
            case ITEM_TYPE_CROWD_FUND:
                return "众筹课程";
            case ITEM_TYPE_TEAM:
                return "课程库";
            case ITEM_TYPE_TEACHER:
                return "老师库";
            case ITEM_TYPE_ACTIVITY:
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

    private static class MyMultiItemTypeSupport1 implements MultiItemTypeSupport<Object>, AppConstant.CollectCourse {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_TITLE:
                    return R.layout.item_team_detail_title;
                case CLILD_CROWD:
                    return R.layout.item_course_collect_crowd;
                case CLILD_TEAM:
                    return R.layout.item_course_collect_team;
                case CLILD_ACTIVITY:
                    return R.layout.item_course_collect_activity;
                case CLILD_TEACHER:
                    return R.layout.item_course_collect_teacher;
                case TYPE_FOOTER:
                    return R.layout.item_footer_title;
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position, Object o) {
            if (o instanceof SearchItemBean) {
                switch (((SearchItemBean) o).getType()) {
                    case ITEM_TYPE_CROWD_FUND:
                        return CLILD_CROWD;
                    case ITEM_TYPE_TEAM:
                        return CLILD_TEAM;
                    case ITEM_TYPE_ACTIVITY:
                        return CLILD_ACTIVITY;
                    case ITEM_TYPE_TEACHER:
                        return CLILD_TEACHER;
                }
            }
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
            mapFooter.put(key, new FooterBean(key, false));
        }
        refresh();
    }

    private void refresh() {
        List newResult = new ArrayList();
        Set<String> keySet = mapData.keySet();
        for (String key1 : keySet) {
            List list = mapData.get(key1);
            if (EmptyUtils.isNotEmpty(list)) {
                newResult.add(new HeadData(0, key1, false));
                FooterBean footerBean = mapFooter.get(key1);
                if (list.size() >= 3) {
                    for (int i = 0; i < 3; i++) {
                        newResult.add(list.get(i));
                    }
                    newResult.add(footerBean);
                } else {
                    newResult.addAll(list);
                }
            }
        }
        replaceAll(newResult);
    }

    public boolean delete(String key,Object object){
        if (mapData.containsKey(key)){
            List list = mapData.get(key);
            if (EmptyUtils.isNotEmpty(list)){
                list.remove(object);
            }
        }
        return true;
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
            mapFooter.put(key, new FooterBean(key, false));
        }
        newResult.add(new HeadData(0, key, false));
        FooterBean footerBean = mapFooter.get(key);
        newResult.addAll(list);
        if (list.size() >= 3) {
            newResult.add(footerBean);
        }
        replaceAll(newResult);
        mIRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
    }

    public List getDataFormKey(String key) {
        return mapData.get(key);
    }

    public String getFlagKey() {
        return flagKey;
    }

    /**
     * 打开偏移
     */
    public void slideOpen() {
        mISlideHelper.slideOpen();
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
