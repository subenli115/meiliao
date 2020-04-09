package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.NewCourseBean;
import com.ziran.meiliao.ui.priavteclasses.activity.NewGongZuoFangActivity;
import com.ziran.meiliao.widget.GlideRoundTransform;


public class CourseTwoAdapter extends MultiItemRecycleViewAdapter<NewCourseBean.DataBean> {


    public static final int TYPE_TOP = 11;
    public CourseTwoAdapter(Context context, MultiItemTypeSupport<NewCourseBean.DataBean> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);

    }

    @Override
    public void convert(final ViewHolderHelper helper, final NewCourseBean.DataBean bean, final int position) {

        Glide.with(mContext).load(bean.getPicture()).transform(new GlideRoundTransform(mContext)).into(helper.getImageView(R.id.iv_item_sjk_act_img));
        if(!bean.getTagName().equals("")){

            (helper.getView(R.id.iv_item_sjk_act_label)).setVisibility(View.VISIBLE);
            ((TextView)helper.getView(R.id.iv_item_sjk_act_label)).setText(bean.getTagName());
        }
        ((TextView)helper.getView(R.id.tv_title)).setText(bean.getActivityName());
        ((TextView)helper.getView(R.id.tv_teacher_name)).setText("导师:"+bean.getTeacher());
        ((TextView)helper.getView(R.id.tv_address)).setText(bean.getAddress());
        ((TextView)helper.getView(R.id.tv_time)).setText(bean.getTime());
        ((TextView)helper.getView(R.id.tv_remain)).setText("仅限"+bean.getRemain()+"位");
        ((TextView)helper.getView(R.id.tv_price)).setText(bean.getPrice()+"");

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActisData actisData = new ActisData();
                actisData.setUrl(bean.getUrl() );
                actisData.setActivityId(bean.getActivityId()+"");
//                GongZuoFangActivity.startAction(mContext,actisData);  //工作坊活动详情页面
                NewGongZuoFangActivity.startAction(mContext,bean.getActivityId()+"",bean.getActivityName());
            }
        });
    }

    public static class ActivityMultiItemType implements MultiItemTypeSupport<NewCourseBean.DataBean> {

        public ActivityMultiItemType() {
        }

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_TOP:
                    return R.layout.item_main_sjk_act_left_new_more;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, NewCourseBean.DataBean newZhuanLanData) {
            return TYPE_TOP;
        }

    }
}