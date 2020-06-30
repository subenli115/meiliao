package com.ziran.meiliao.im.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.common.viewpager.CardPagerAdapter;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.model.AppBean;
import com.ziran.meiliao.im.utils.event.ImageEvent;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.bean.RechargeBean;
import com.ziran.meiliao.utils.DownloadUtil;
import com.ziran.meiliao.widget.GlideCircleTransform;

public class AppsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private int selectPos = -1;
    private List<RechargeBean.DataBean.RecordsBean> mDdata = new ArrayList<>();

    public AppsAdapter(Context context, List<RechargeBean.DataBean.RecordsBean> data) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        if (data != null) {
            this.mDdata = data;
        }
    }
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener<T> {
        void onItemClick(ViewGroup container, T item, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public  void  update(List<RechargeBean.DataBean.RecordsBean> data){
        this.mDdata = data;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mDdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mDdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_app, null);
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_gold = (TextView) convertView.findViewById(R.id.tv_gold_num);
            viewHolder.iv_status = (ImageView) convertView.findViewById(R.id.iv_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mDdata.get(position).isSelect()) {
            selectPos = position;
            convertView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.normal_bg_orange_gift));
        } else {
            convertView.setBackgroundDrawable(null);
        }
        RechargeBean.DataBean.RecordsBean recordsBean = mDdata.get(position);
        if (recordsBean != null) {

            if(recordsBean.getReserve1().equals("2")){
                viewHolder.iv_status.setBackgroundResource(R.mipmap.icon_gift_new);
            }else if(recordsBean.getReserve1().equals("1")){
                viewHolder.iv_status.setBackgroundResource(R.mipmap.icon_gift_hot);
            }
            Glide.with(mContext).load(recordsBean.getImages()).into(viewHolder.iv_icon);
            viewHolder.tv_name.setText(recordsBean.getName());
            viewHolder.tv_gold.setText((int)recordsBean.getPrice()+"");
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(parent, mDdata.get(position), position);
                    }

                }
            });
        }
        return convertView;
    }
    public void changeCheck(int position) {
        if (selectPos == position) return;
        if (selectPos != -1) {
            mDdata.get(selectPos).setSelect(false);
        }
        mDdata.get(position).setSelect(true);
        notifyDataSetChanged();
    }

    public RechargeBean.DataBean.RecordsBean getSelect() {
        if (selectPos == -1) return null;
        return mDdata.get(selectPos);
    }
    class ViewHolder {
        public ImageView iv_icon,iv_status;
        public TextView tv_name,tv_gold;
    }
}