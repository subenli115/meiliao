package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.RecListMainBean;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CatalogRecordAdapter extends CommonRecycleViewAdapter<RecListMainBean.DataBean.RecListBean> {


    public CatalogRecordAdapter(Context context, int layoutId) {
            super(context, layoutId);
            }

    @Override
    public void convert(final ViewHolderHelper helper, final RecListMainBean.DataBean.RecListBean bean, int position) {
            TextView tvTitle = helper.getView(R.id.tv_title);
            TextView tvTime= helper.getView(R.id.tv_time);
        AutoRelativeLayout arlBg= helper.getView(R.id.arl_bg);
        AutoLinearLayout    allBg= helper.getView(R.id.all_bg);
            tvTitle.setText(bean.getTitle());
            tvTime.setText(longToDate(bean.getUploadTime())+"更新");
            if(bean.isLock()){
                allBg.setVisibility(View.VISIBLE);
                arlBg.setBackgroundResource(R.drawable.normal_bg_white);
                tvTime.setTextColor(Color.parseColor("#000000"));
            }else{
                if(bean.isIsclick()){
                    arlBg.setBackgroundResource(R.drawable.normal_bg_tran_orange6b);
                }else{
                    arlBg.setBackgroundResource(R.drawable.normal_bg_white);
                }
                allBg.setVisibility(View.GONE);
                tvTime.setTextColor(Color.parseColor("#FBAF6B"));
            }

    }

    @Override
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    /**
         * @Description: long类型转换成日期
         *
         * @param lo 毫秒数
         * @return String yyyy-MM-dd HH:mm:ss
         */
        public static String longToDate(long lo){
            Date date = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("MM-dd");
            return sd.format(date);
        }


private String prefix;

public void setPrefix(String prefix) {
        this.prefix = prefix;
}
        }