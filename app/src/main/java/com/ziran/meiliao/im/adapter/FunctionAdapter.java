package com.ziran.meiliao.im.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.model.AppBean;
import com.ziran.meiliao.im.utils.event.ImageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class FunctionAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<AppBean> mDdata = new ArrayList<AppBean>();

    public FunctionAdapter(Context context, ArrayList<AppBean> data) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        if (data != null) {
            this.mDdata = data;
        }
    }
    public void update(Context context, ArrayList<AppBean> data){
        this.mContext = context;
        if (data != null) {
            this.mDdata = data;
        }
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
            convertView = inflater.inflate(R.layout.item_function, null);
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final AppBean appBean = mDdata.get(position);
        if (appBean != null) {
            viewHolder.iv_icon.setImageResource(appBean.getIcon());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appBean.getFuncName().equals("图片")) {
                        if(appBean.isSelect()){
                            EventBus.getDefault().post(new ImageEvent(JGApplication.IMAGE_MESSAGE));
                        }else {
                            ToastUitl.showShort("亲密度达到4级时解锁");
                        }
                    }else if (appBean.getFuncName().equals("位置")) {
                        if(appBean.isSelect()){
                            EventBus.getDefault().post(new ImageEvent(JGApplication.TAKE_LOCATION));
                        }else {
                            ToastUitl.showShort("亲密度达到2级时解锁");
                        }
                    }else if (appBean.getFuncName().equals("文件")) {
                        if(appBean.isSelect()){
                            EventBus.getDefault().post(new ImageEvent(JGApplication.FILE_MESSAGE));
                        }else {
                            ToastUitl.showShort("亲密度达到5级时解锁");
                        }
                    }else if (appBean.getFuncName().equals("视频")) {
                        if(appBean.isSelect()){
                            EventBus.getDefault().post(new ImageEvent(JGApplication.TACK_VIDEO));
                        }else {
                            ToastUitl.showShort("亲密度达到9级时解锁");
                        }
                    }else if (appBean.getFuncName().equals("语音")) {
                        if(appBean.isSelect()){
                            EventBus.getDefault().post(new ImageEvent(JGApplication.TACK_VOICE));
                        }else {
                            ToastUitl.showShort("亲密度达到6级时解锁");
                        }
                    }
                }
            });
        }
        return convertView;
    }


    class ViewHolder {
        public ImageView iv_icon;
    }
}