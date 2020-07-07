package com.ziran.meiliao.im.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.model.AppBean;
import com.ziran.meiliao.ui.bean.GiftsReceivedBean;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

public class MeGiftAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private List<GiftsReceivedBean.DataBean.RecordsBean> mDdata ;

    public MeGiftAdapter(Context context, List<GiftsReceivedBean.DataBean.RecordsBean> data) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
            mDdata = data;
    }
    private OnItemClickListener mOnItemClickListener;

    public void update(Context context,List<GiftsReceivedBean.DataBean.RecordsBean> data) {
        mDdata=null;
        mDdata = data;
        Log.e("","");
//        mDdata.addAll(data);
        this.inflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener<T> {
        void onItemClick(ViewGroup container, T item, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
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
            convertView = inflater.inflate(R.layout.item_me_gift, null);
            viewHolder.iv_gift = (ImageView) convertView.findViewById(R.id.iv_gift);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final GiftsReceivedBean.DataBean.RecordsBean bean = mDdata.get(position);
            Glide.with(mContext).load(bean.getGiftImages()).into(viewHolder.iv_gift);
            viewHolder.tv_name.setText(bean.getGiftName()+"×"+bean.getNumber());
        return convertView;
    }

    class ViewHolder {
        public ImageView iv_gift;
        public TextView tv_name;
    }
}