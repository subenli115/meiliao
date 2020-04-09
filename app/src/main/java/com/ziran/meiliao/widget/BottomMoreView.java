package com.ziran.meiliao.widget;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.irecyclerview.SimpleAnimatorListener;
import com.ziran.meiliao.utils.AnimationUtil;


/**
 * 下载,分享,收藏控件
 * Created by Administrator on 2017/2/20.
 */

public class BottomMoreView extends CustomRelativeLayout implements View.OnClickListener {
    /**
     * 收藏控件
     */
    private TextView tvCollect;
    private ImageView ivCollect;
    private ImageView ivDown;
    private TextView tvDown;

    public BottomMoreView(Context context) {
        this(context, null);
    }

    public BottomMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected int getResourseLayoutId() {
        return R.layout.custom_bottom_more_view;
    }

    private void initViews() {
        tvCollect = ViewUtil.getView(rootView, R.id.tv_more_collect);
        tvDown = ViewUtil.getView(rootView, R.id.tv_down);
        initMore(rootView);
        ViewUtil.getView(rootView, R.id.iv_more_share).setOnClickListener(this);
        ivCollect = ViewUtil.getView(rootView, R.id.iv_more_collect);
        ivDown = ViewUtil.getView(rootView, R.id.iv_more_download);
        ViewUtil.getView(rootView, R.id.iv_more_download).setOnClickListener(this);
        ViewUtil.getView(rootView, R.id.iv_more_collect).setOnClickListener(this);
        ViewUtil.getView(rootView, R.id.btn_mode_cancel).setOnClickListener(this);
    }


    public void setShowMore(boolean show) {
        showOrHide = show;
        if (show) {
            setVisibility(VISIBLE);
            ll_more_more.setVisibility(VISIBLE);
            AnimationUtil.startAnimationVer(true, ll_more_more, false, 300, new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {

                }
            });
        } else {
            com.ziran.meiliao.utils.AnimationUtil.startAnimationVer(false, ll_more_more, false, 300, new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    setVisibility(GONE);
                    ll_more_more.setVisibility(GONE);
                    switch (checkId) {
                        case R.id.iv_more_download:
                            onMoreListener.onDownload();

                            break;
                        case R.id.iv_more_collect:
                            onMoreListener.onCollect();
                            break;
                        case R.id.iv_more_share:
                            onMoreListener.onShare();
                            break;
                    }
                }
            });
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_cancel) {
            checkId = 0;
            setShowMore(false);
            if (onMoreListener != null) onMoreListener.onCancel();
            return;
        }
        if (onMoreListener == null) return;
        checkId = view.getId();
        setShowMore(false);
    }

    private OnMoreCallBack onMoreListener;

    @Override
    protected void setShow(boolean isShow) {
        checkId = 0;
        setShowMore(isShow);
    }

    public void setOnMoreListener(OnMoreCallBack onMoreListener) {
        this.onMoreListener = onMoreListener;
    }


    public void setCollectText(boolean collect) {
        if (tvCollect != null) {
            tvCollect.setText(collect ? R.string.collected : R.string.collect);
        }
        if (ivCollect!=null){
            ivCollect.setSelected(collect);
        }
    }
        public void setDownText(boolean down) {
            if (tvDown != null) {
                tvDown.setText(down ? R.string.download_history : R.string.download);
            }
            if (ivDown != null) {
                if(down){
                    ivDown.setImageResource(R.mipmap.downed_zl);
                }else {
                    ivDown.setImageResource(R.mipmap.ic_more_download_unpre);
                }
            }
        }

        public void setDowning(){
            if (tvDown != null) {
                tvDown.setText(R.string.downloading2);
            }
            if (ivDown != null) {
                ivDown.setImageResource(R.mipmap.ic_more_download);
            }
        }

    public interface OnMoreCallBack {
        void onDownload();

        void onCollect();

        void onShare();

        void onCancel();
    }
}
