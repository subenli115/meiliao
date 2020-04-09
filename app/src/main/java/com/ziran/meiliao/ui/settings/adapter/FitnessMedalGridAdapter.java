package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.FitnessMedalBean;

import java.util.List;
//健身功法适配器




public class FitnessMedalGridAdapter extends BaseAdapter {
    private final Context mContext;
    private List<FitnessMedalBean.DataBean.MedalBean> beanList;
    private LayoutInflater layoutInflater;

    public FitnessMedalGridAdapter(Context context, List<FitnessMedalBean.DataBean.MedalBean> List) {
        layoutInflater = LayoutInflater.from(context);
                beanList =  List;
        mContext=context;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_medal_view, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.iv_image);
            holder.text = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FitnessMedalBean.DataBean.MedalBean typeBean = beanList.get(position);
        if (typeBean != null) {
            holder.text.setText("练习者勋章");
            Glide.with(mContext).load(typeBean.getPicture()).into(holder.image);
        }
        return convertView;
    }

    class ViewHolder {
        TextView text;
        ImageView image;

    }

}