package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.utils.ImageAnimation;

/**
 *  播放界面音乐列表适配器
 */

public class AlbumPlayerListAdapter extends MultiItemRecycleViewAdapter<MusicEntry> {

    public AlbumPlayerListAdapter(Context context, MultiItemTypeSupport<MusicEntry> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    @Override
    public void convert(ViewHolderHelper helper, final MusicEntry bean, int position) {
        if (bean.isHead()) {
            helper.setText(R.id.tv_item_jyg_audio_title, bean.getCatalogName());
        } else {
            helper.setText(R.id.tv_item_jyg_audio_player_name, bean.getName());
            ImageView imageView = helper.getView(R.id.iv_item_jyg_audio_player_playing);
            if (MyAPP.mServiceManager.getCurMusicId() == bean.getMusicId() ) {
                helper.setTextColor(R.id.tv_item_jyg_audio_player_name, R.color.textColor_teshe);
                helper.setVisible(R.id.iv_item_jyg_audio_player_playing, true);
                ImageAnimation.get().setTager(imageView, true);
                helper.setVisible(R.id.tv_item_jyg_audio_player_time, false);
            } else {
                helper.setTextColor(R.id.tv_item_jyg_audio_player_name, R.color.textColor_333);
                helper.setVisible(R.id.iv_item_jyg_audio_player_playing, false);
                helper.setVisible(R.id.tv_item_jyg_audio_player_time, true);
                helper.setText(R.id.tv_item_jyg_audio_player_time, bean.getDuration());
            }
        }
    }

    public static class MultiItemType implements MultiItemTypeSupport<MusicEntry> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DATA:
                    return R.layout.item_jyg_album_player_list;
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
