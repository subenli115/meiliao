package com.ziran.meiliao.widget;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.irecyclerview.SimpleAnimatorListener;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.DownloadAlbumAdapter;
import com.ziran.meiliao.utils.StringUtils;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.SimpleDialogClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     专辑下载控件
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class BottomDownView extends CustomRelativeLayout implements View.OnClickListener {
    /**
     * 列表控件
     */
    private RecyclerView recyclerView;
    /**
     * 适配器
     */
    private DownloadAlbumAdapter mAdapter;
    /**
     * 下载按钮
     */
    private TextView tvDownload;
    /**
     * 全选按钮
     */
    private TextView tvSelectAll;
    
    public BottomDownView (Context context) {
        this(context, null);
    }
    
    public BottomDownView (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public BottomDownView (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }
    
    @Override
    protected int getResourseLayoutId () {
        return R.layout.custom_bottom_download_view;
    }
    
    //初始化控件
    private void initViews () {
        initMore(rootView, R.id.layout_custom_container);
        recyclerView = ViewUtil.getView(rootView, R.id.recyclerView);
        tvDownload = ViewUtil.getView(rootView, R.id.tv_download_select_download);
        tvSelectAll = ViewUtil.getView(rootView, R.id.tv_download_select_all);
        //设置点击事件
        ViewUtil.getView(rootView, R.id.tv_download_select_cancel).setOnClickListener(this);
        ViewUtil.getView(rootView, R.id.tv_download_select_all).setOnClickListener(this);
        ViewUtil.getView(rootView, R.id.tv_download_select_download).setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    
    //关联适配器
    public void setAdapter (List<MusicEntry> lists) {
        if (mAdapter == null) {
            mAdapter = new DownloadAlbumAdapter(getContext(), R.layout.item_jyg_album_download);
            mAdapter.setHeadCount(0);
            //设置点击checkBox的监听
            mAdapter.setListener(new DownloadAlbumAdapter.MyOnCheckedChangeListener() {
                @Override
                public void downSize (float downSize) {
                    if (downSize == 0f) {
                        tvDownload.setText(R.string.download);
                    } else {
                        tvDownload.setText(String.format(getContext().getString(R.string.download_size), (int) downSize));
                    }
                }
            });
            //设置item的点击监听
            mAdapter.setOnItemClickListener(new OnItemClickListener<MusicEntry>() {
                @Override
                public void onItemClick (ViewGroup parent, View view, MusicEntry o, int position) {
                    View view1 = ViewUtil.getView(view, R.id.include_smooth_checkbox);
                    if (view1 != null && view1 instanceof SmoothCheckBox) {
                        ((SmoothCheckBox) view1).setChecked(!((SmoothCheckBox) view1).isChecked());
                    }
                }
                
                @Override
                public boolean onItemLongClick (ViewGroup parent, View view, MusicEntry o, int position) {
                    return false;
                }
            });
            recyclerView.setAdapter(mAdapter);
        }
        //替换数据源
        mAdapter.replaceAll(changeList(lists));
    }
    
    //去掉无效的数据
    public List<MusicEntry> changeList (List<MusicEntry> listBeanList) {
        List<MusicEntry> list = new ArrayList<>();
        if (EmptyUtils.isEmpty(listBeanList))
            return list;
        for (int i = 0; i < listBeanList.size(); i++) {
            MusicEntry musicEntry = listBeanList.get(i);
            if (EmptyUtils.isNotEmpty(musicEntry) && !musicEntry.isHead() && !FileUtil.fileIsExists(musicEntry.getFilePath())) {
                list.add(musicEntry);
            }
        }
        return list;
    }
    
    //显示或隐藏控件，并开启动画效果
    public void setShowDownload (boolean show) {
        showOrHide = show;
        if (show) {
            setVisibility(VISIBLE);
            com.ziran.meiliao.utils.AnimationUtil.startAnimationVer(true, ll_more_more, false, 300, new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd (Animator animation) {
                    
                }
            });
        } else {
            com.ziran.meiliao.utils.AnimationUtil.startAnimationVer(false, ll_more_more, false, 300, new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd (Animator animation) {
                    setVisibility(GONE);
                    mAdapter.clearSelect();
                }
            });
        }
    }
    
    //点击事件监听
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.tv_download_select_all:
                if (StringUtils.getText(R.string.delete_act_sellect).equals(tvSelectAll.getText().toString())) {
                    tvSelectAll.setText(R.string.cancel_selectall);
                } else {
                    tvSelectAll.setText(R.string.delete_act_sellect);
                }
                mAdapter.selectAll();
                break;
            case R.id.tv_download_select_download:
                //判断网络环境，如果是3G,4G网络需要弹出对话框提示用户
                if (NetUtil.getNetWorkState(getContext()) == NetUtil.NETWORK_MOBILE) {
                    MDAlertDialog.createDialog(getContext(), getResources().getString(R.string.mobile_toast), new SimpleDialogClickListener() {
                        @Override
                        public void clickRightButton (Dialog dialog, View view) {
                            dialog.dismiss();
                            mAdapter.downloadFile();
                            setShowDownload(false);
                        }
                    });
                } else {
                    mAdapter.downloadFile();
                    setShowDownload(false);
                }
                break;
            case R.id.tv_download_select_cancel:
                mAdapter.clearSelect();
                setShowDownload(false);
                break;
        }
    }
    
    /**
     *  隐藏控件
     * @param isShow  true 显示， false 隐藏
     */
    @Override
    protected void setShow (boolean isShow) {
        setShowDownload(false);
    }
    
    //设置专辑id，下载需要用到
    public void setAlbumId (String albumId) {
        if (mAdapter != null)
            mAdapter.setAlbumId(albumId);
    }
}
