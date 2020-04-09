package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.AlbumEntry;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
import com.ziran.meiliao.ui.adapter.OneSlideViewHolder;
import com.ziran.meiliao.utils.DownloadUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/1/14.
 */

public class DownloadAlbumAdapter extends OneSlideAdapter<AlbumEntry> {

    private final Context context;

    public DownloadAlbumAdapter(Context context, int layoutId) {
        super(context, layoutId);
        from_type = FROM_ALBUM;
        this.context=context;
    }

    @Override
    public int onBindSlideViewId() {

        return R.id.item_content_rl;

    }

    @Override
    public void convertData(OneSlideViewHolder helper, AlbumEntry bean, int position) {
        ImageView iv = helper.getView(R.id.iv_jyg_recommend_img);
        TextView tv_title = helper.getView(R.id.tv_jyg_recommend_title);
        TextView tv_anchor = helper.getView(R.id.tv_jyg_recommend_anchor);
        TextView tv_lable = helper.getView(R.id.tv_jyg_recommend_lable);
        if (tv_lable != null && tv_lable.isShown()) {
            tv_lable.setVisibility(View.GONE);
        }
        TextView tv_profile = helper.getView(R.id.tv_jyg_recommend_profile);
        ImageLoaderUtils.display(mContext, iv, bean.getPicture(),R.mipmap.ic_loading_square);
        tv_anchor.setText(bean.getName());
        tv_title.setText(bean.getTitle());
        tv_profile.setText(bean.getDetail());
    }

    @Override
    protected void delete(AlbumEntry albumEntry) {
        if (EmptyUtils.isNotEmpty(albumEntry)) {
            List<MusicEntry> musicEntries = DbCore.getDaoSession().getMusicEntryDao().queryRaw("where ALBUM_ID = ?", new
                    String[]{albumEntry.getAlbumId()});
            if (EmptyUtils.isNotEmpty(musicEntries)) {
                for (int i = 0; i < musicEntries.size(); i++) {
                    FileUtil.delete(musicEntries.get(i).getPath());
                    DownloadUtil.getInstance().cancel(musicEntries.get(i).getAESUrl());
//                    DownloadService.getBinder(MyAPP.getContext()).cancelDownload(musicEntries.get(i).getAESUrl());
                }
                DbCore.getDaoSession().getMusicEntryDao().deleteInTx(musicEntries);
            }
            DbCore.getDaoSession().getAlbumEntryDao().delete(albumEntry);
        }
    }
}
