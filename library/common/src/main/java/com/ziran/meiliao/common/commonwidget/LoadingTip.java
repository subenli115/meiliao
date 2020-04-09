package com.ziran.meiliao.common.commonwidget;

import android.content.Context;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

/**
 * des:加载页面内嵌提示
 * Created by xsf
 * on 2016.07.17:22
 */
public class LoadingTip extends LinearLayout {

    private ImageView img_tip_logo;
    private ProgressBar progress;
    private TextView tv_tips;
    private Button bt_operate;
    private String errorMsg;
    private onReloadListener onReloadListener;
    private String emptyMsg;
    private Spanned emptySpanned;

    public LoadingTip(Context context) {
        super(context);
        initView(context);
    }

    public LoadingTip(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadingTip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    //分为服务器失败，网络加载失败、数据为空、加载中、完成四种状态
    public static enum LoadStatus {
        sereverError, error, empty, loading, finish, noWifi
    }

    private LoadStatus mLoadStatus = LoadStatus.finish;

    private void initView(Context context) {

        View.inflate(context, R.layout.dialog_loading_tip, this);
        img_tip_logo = (ImageView) findViewById(R.id.img_tip_logo);
        progress = (ProgressBar) findViewById(R.id.progress);
        tv_tips = (TextView) findViewById(R.id.tv_tips);
        bt_operate = (Button) findViewById(R.id.bt_operate);
        //重新尝试
        bt_operate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onReloadListener != null) {
                    onReloadListener.reload();
                }
            }
        });
        setVisibility(View.GONE);
    }

    public void setTips(String tips) {
        if (tv_tips != null) {
            tv_tips.setText(tips);
        }
    }

    private int logeEmpty = R.drawable.no_content_tip;

    /**
     * 根据状态显示不同的提示
     *
     * @param loadStatus
     */
    public void setLoadingTip(LoadStatus loadStatus) {
        if (mLoadStatus == loadStatus) return;
        mLoadStatus = loadStatus;
        switch (loadStatus) {
            case empty:
                setVisibility(View.VISIBLE);
                img_tip_logo.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                if (EmptyUtils.isNotEmpty(emptySpanned)) {
                    tv_tips.setText(emptySpanned);
                } else {
                    tv_tips.setText(emptyMsg);
                }
                setOperateShown();
                img_tip_logo.setImageResource(logeEmpty);
                break;
            case sereverError:
                setVisibility(View.VISIBLE);
                img_tip_logo.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                if (TextUtils.isEmpty(errorMsg)) {
                    tv_tips.setText(getContext().getText(R.string.net_error).toString());
                } else {
                    tv_tips.setText(errorMsg);
                }
                setOperateShown();
                img_tip_logo.setImageResource(R.drawable.ic_empty_wifi);
                break;
            case error:
                setVisibility(View.VISIBLE);
                img_tip_logo.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                if (TextUtils.isEmpty(errorMsg)) {
                    tv_tips.setText(getContext().getText(R.string.net_error).toString());
                } else {
                    tv_tips.setText(errorMsg);
                }
                img_tip_logo.setImageResource(R.drawable.ic_empty_wifi);
                break;
            case noWifi:
                setVisibility(View.VISIBLE);
                img_tip_logo.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                if (TextUtils.isEmpty(errorMsg)) {
                    tv_tips.setText(getContext().getText(R.string.no_net).toString());
                } else {
                    tv_tips.setText(errorMsg);
                }
                bt_operate.setText("去设置");
                bt_operate.setVisibility(VISIBLE);
                img_tip_logo.setImageResource(R.drawable.ic_empty_wifi);
                break;
            case loading:
                setVisibility(View.VISIBLE);
                img_tip_logo.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                setOperateShown();
                tv_tips.setText(getContext().getText(R.string.loading).toString());
                break;
            case finish:
                setOperateShown();
                setVisibility(View.GONE);
                break;
        }
    }

   public void setOperate(){
       bt_operate.setText("重新加载");

   }

    public void setOperateShown() {
        if (bt_operate.isShown()) bt_operate.setVisibility(GONE);
    }

    public void setEmptyMsg(String emptyMsg) {
        this.emptyMsg = emptyMsg;
    }

    private String operateText;

    public void setEmptyMsg(String emptyMsg, int iconId, String operateText) {
        this.emptyMsg = emptyMsg;
        this.logeEmpty = iconId;
        if (EmptyUtils.isEmpty(operateText)) {
            bt_operate.setVisibility(GONE);
        } else {
            this.operateText = operateText;
            bt_operate.setText(operateText);
        }
    }

    public void setEmptyMsg(String emptyMsg, int iconId) {
        this.emptyMsg = emptyMsg;
        this.logeEmpty = iconId;
        img_tip_logo.setImageResource(iconId);
    }


    public void setImg_tip_logo(int iconId) {
        img_tip_logo.setImageResource(iconId);
    }

    public void setOnReloadListener(onReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    /**
     * 重新尝试接口
     */
    public interface onReloadListener {
        void reload();
    }

    public void setEmptySpanned(Spanned spanned) {
        this.emptySpanned = spanned;
    }

}

