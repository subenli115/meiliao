package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.FitnessExeActivity;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.MbsrWorkbookMainActivity;
import com.ziran.meiliao.ui.NewDecompressionmuseum.activity.PracticeTaskActivity;
import com.ziran.meiliao.ui.bean.BootCampV2Bean;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.List;

/**
 * Created by Administrator on 2019/1/31.
 */

public class BootCampAdapter extends MultiItemRecycleViewAdapter<Object> {


    public static final int TYPE_LEFT = 10;
    public static final int TYPE_TOP = 11;
    public BootCampAdapter(Context context, MultiItemTypeSupport<Object> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);

    }

    @Override
    public void convert(final ViewHolderHelper helper, final Object object, final int position) {

            if(object instanceof BootCampV2Bean.DataBean.PracticeBooksBean){
              final   BootCampV2Bean.DataBean.PracticeBooksBean bean=(BootCampV2Bean.DataBean.PracticeBooksBean)object;
                Glide.with(mContext).load(bean.getPicture()).transform(new GlideRoundTransform(mContext)).into(helper.getImageView(R.id.iv_item_sjk_act_img));
                ((TextView)helper.getView(R.id.tv_title)).setText(bean.getName());
                ((TextView)helper.getView(R.id.tv_time)).setText(bean.getStartTime());

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.getName().equals("MBSR八周练习"))
                            if(bean.getStatus()==0){
                                MbsrWorkbookMainActivity.startAction(mContext,bean.getBooksId()+"");
                            }else {
                                PracticeTaskActivity.startAction(mContext,bean.getBooksId()+"",0+"");
                            }
                    }
                });
            }else{
                final   BootCampV2Bean.DataBean.JsCoursesBean bean=(BootCampV2Bean.DataBean.JsCoursesBean)object;
                Glide.with(mContext).load(bean.getRecPic()).transform(new GlideRoundTransform(mContext)).into(helper.getImageView(R.id.iv_item_sjk_act_img));
                ((TextView)helper.getView(R.id.tv_title)).setText(bean.getName());
                ((TextView)helper.getView(R.id.tv_time)).setText(bean.getDescription());
                if(bean.getName().equals("易经筋")){
                    ((TextView)helper.getView(R.id.tv_free)).setVisibility(View.VISIBLE);
                }
                List<VideoSectionEntry> courseEntries = VideoSectionEntry.loadWhereCourseId("");
                if(courseEntries!=null&&courseEntries.size()>0){
                    for(int i=0;i<courseEntries.size();i++){
                        if(courseEntries.get(i).getTitle().equals(bean.getName())){
                        ((TextView)helper.getView(R.id.tv_download)).setText("已下载");

                        }
                    }
                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FitnessExeActivity.startAction(mContext,bean.getId());
                    }
                });


            }
    }

    public static class ActivityMultiItemType implements MultiItemTypeSupport<Object> {

        public ActivityMultiItemType() {
        }

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_TOP:
                    return R.layout.item_main_sjk_act_long_new_second;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object newZhuanLanData) {
            return TYPE_TOP;
        }

    }
}