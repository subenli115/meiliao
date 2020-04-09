package com.ziran.meiliao.ui.settings.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ziran.meiliao.common.irecyclerview.baseadapter.BaseAblistViewAdapter;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewHolderUtil;
import com.ziran.meiliao.common.imagePager.BigImagePagerActivity;
import com.ziran.meiliao.R;

import java.util.List;


/**
 * des:9宫图适配器
 * Created by xsf
 * on 2016.09.16:33
 */
public class NinePicturesAdapter extends BaseAblistViewAdapter<String> {
    private boolean showAdd = true;
    private int picturnNum = 0;
    private boolean isDelete = false;//当前是否显示删除按钮

    public NinePicturesAdapter(Context context, int picturnNum) {
        super(context);
        this.picturnNum = picturnNum;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_photo, parent, false);
        }
        final ImageView imageView = ViewHolderUtil.get(convertView, R.id.img_photo);
        ImageView imgDelete = ViewHolderUtil.get(convertView, R.id.img_delete);
        final String url = getData().get(position);

        imgDelete.setVisibility(View.VISIBLE);
        ImageLoaderUtils.display(mContext, imageView, url);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //放大查看图片
                BigImagePagerActivity.startImagePagerActivity((Activity) mContext, getData(), position);
            }
        });
        //删除按钮
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getCount()>0){
                    remove(position);
                }
            }
        });
        return convertView;
    }

    @Override
    public void setData(List<String> d) {
        super.setData(d);
    }

    /**
     * 获取图片张数
     *
     * @return
     */
    public int getPhotoCount() {
        return getCount();
    }


}