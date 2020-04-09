package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.ImageAnimation;

/**
 * 专辑详情界面音乐列表适配器
 */

public class AlbumDetailListAdapter extends MultiItemRecycleViewAdapter<MusicEntry> {


    public AlbumDetailListAdapter(Context context, MultiItemTypeSupport<MusicEntry> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    private AlbumBean mAuthorBean;

    public AlbumDetailListAdapter(Context context, MultiItemTypeSupport<MusicEntry> multiItemTypeSupport, AlbumBean authorBean) {
        super(context, multiItemTypeSupport);
        this.mAuthorBean = authorBean;
    }

    @Override
    public void convert(ViewHolderHelper helper, final MusicEntry bean, int position) {
        if (bean.isHead()) { //如果是标题
            helper.setText(R.id.tv_item_jyg_audio_title, bean.getCatalogName());
        } else {  //内容
            helper.setVisible(R.id.tv_item_jyg_audio_detail_audition, bean.isSt());
            helper.setText(R.id.tv_item_jyg_audio_detail_name, bean.getName());
            ImageView imageView = helper.getView(R.id.iv_item_jyg_audio_detail_playing);
            if (MyAPP.mServiceManager.getCurMusicId() == bean.getMusicId() && MyAPP.mServiceManager.getPlayState() == IConstants
                    .MPS_PLAYING) { // 如果正在播放,并且播放的是当前音乐则显示动画
                helper.setTextColor(R.id.tv_item_jyg_audio_detail_name, R.color.textColor_teshe);
                imageView.setVisibility(View.VISIBLE);
                ImageAnimation.get().setTager(imageView, true);
            } else {
                helper.setTextColor(R.id.tv_item_jyg_audio_detail_name, R.color.textColor_333);
                imageView.setVisibility(View.GONE);
            }
            helper.setVisible(R.id.iv_item_jyg_audio_detail_more, (mAuthorBean != null && mAuthorBean.isBuy())||bean.isSt());
            //点击更多监听
            helper.setOnClickListener(R.id.iv_item_jyg_audio_detail_more, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!CheckUtil.check(mContext, v)) return;
                    ((AlbumDetailActivity) mContext).doMusicMore(bean);
                }
            });
        }
    }

    public static class MultiItemType implements MultiItemTypeSupport<MusicEntry> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DATA:
                    return R.layout.item_jyg_album_detail_list;
                case TYPE_HEAD:
                    return R.layout.item_single_title;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, MusicEntry musicEntry) {
            return musicEntry.isHead() ? TYPE_HEAD : TYPE_DATA;
        }
    }

}
