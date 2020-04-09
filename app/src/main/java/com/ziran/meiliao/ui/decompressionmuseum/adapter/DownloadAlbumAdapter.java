package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.R;
import com.ziran.meiliao.utils.WpyxDownloadUtil;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.widget.SmoothCheckBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  专辑音乐下载的适配器
 */

public class DownloadAlbumAdapter extends CommonRecycleViewAdapter<MusicEntry> {
    //记录选中的音乐
    private Map<Integer,MusicEntry> positionSet;
    //选择发生变化监听
    private MyOnCheckedChangeListener mListener;
    //是否全选
    private boolean isSelectAll;
    //当前音乐的专辑ID
    private String albumId;
    public String getAlbumId() {
        return albumId+"";
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
    public DownloadAlbumAdapter(Context context, int layoutId) {
        super(context, layoutId);
        mContext=context;
        positionSet = new HashMap<>();
    }

    @Override
    public void convert(ViewHolderHelper holder, final MusicEntry audioListBean, final int position) {
        final View checkBoxView = holder.getView(R.id.include_smooth_checkbox);
        if (checkBoxView instanceof SmoothCheckBox) {
            SmoothCheckBox checkBox = (SmoothCheckBox) checkBoxView;
            checkBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                    addOrRemove(position, isChecked, 0f);
                }
            });
            checkBox.setChecked(positionSet.containsKey(position));
        }
        holder.setText(R.id.tv_item_jyg_audio_name, FileUtil.newString(audioListBean.getName()));
        holder.setText(R.id.tv_item_jyg_audio_time, audioListBean.getSize());
    }

    //设置监听
    public void setListener(MyOnCheckedChangeListener listener) {
        this.mListener = listener;
    }

    /**
     *  选择发生变化的监听器
     */
    public interface MyOnCheckedChangeListener {
        void downSize(float downSize);
    }

    /**
     * 操作Item记录集合
     */
    public void addOrRemove(int position, boolean isCheck, float fileSize) {
        if (isCheck) {
            // 如果包含，则撤销选择
            positionSet.put(position, get(position));
        } else {
            // 如果不包含，则添加
            positionSet.remove(position);
        }
        if (mListener != null) {
            mListener.downSize(getFileTotalSize());
        }
    }



    //开始下载文件
    public void downloadFile() {
        if (EmptyUtils.isEmpty(positionSet)) return;
        Set<Integer> entrySet = positionSet.keySet();
        for (Integer key : entrySet) {
            MusicEntry dataBean = positionSet.get(key);
            Log.e("positionSet",dataBean.getUrl()+"    "+dataBean.getAESUrl());
            WpyxDownloadUtil.get().downMusic(mContext,null,albumId,dataBean,false,false);
        }
    }
    //获取选中的文件大小
    private float getFileTotalSize() {
        if (EmptyUtils.isEmpty(positionSet)) return 0;
        float fileTotalSize = 0;
        Set<Integer> entrySet = positionSet.keySet();
        for (Integer key : entrySet) {
            String size = positionSet.get(key).getSize();
            if (size.length()>1){
                fileTotalSize +=   Float.parseFloat(size.replace("M",""));
            }
        }
        return fileTotalSize;
    }
    //全选或取消全选
    public void selectAll() {
        if (!isSelectAll) {
            positionSet.clear();
            isSelectAll = true;
            for (int i = 0; i < mDatas.size(); i++) {
                positionSet.put(i, mDatas.get(i));
            }
            notifyDataSetChanged();
        } else {
            isSelectAll = false;
            positionSet.clear();
            notifyDataSetChanged();
        }
    }
    //清除选中状态
    public void clearSelect() {
        positionSet.clear();
        mDatas.clear();
    }

}
