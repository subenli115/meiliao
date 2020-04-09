package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.MediaAndTextBean;

public class ZLAudioOneAdapter extends CommonRecycleViewAdapter<MediaAndTextBean.DataBean> {
    public ZLAudioOneAdapter(Context context ) {
        super(context, R.layout.item_zl_audio_one);
    }

    @Override
    public void convert(ViewHolderHelper helper, MediaAndTextBean.DataBean bean, int position) {
        helper.setImageUrl(R.id.iv_item_img,bean.getRoundPic());
        helper.setText(R.id.tv_item_title,bean.getTitle());

    }
}
