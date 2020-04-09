package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.bean.TempActivityBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.RecordVideoPlayerActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.DefWebActivity;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class ZndhPopupWindow extends BasePopupWindow {
    private RoundImageView ivCover;
    private ImageView ivJump;
    private Context mContext;
    private TempActivityBean.DataBean mData;

    public ZndhPopupWindow(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_zndh;
    }

    @Override
    protected void initViews(View contentView) {
        ivCover = ViewUtil.getView(contentView, R.id.iv_zndh_cover);
        ivJump = ViewUtil.getView(contentView, R.id.iv_zndh_jump);
        setOnClickListener(R.id.iv_zndh_close);
        setOnClickListener(R.id.iv_zndh_jump);
    }


    @Override
    public void dismiss() {
        super.dismiss();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_zndh_close:
                dismiss();
                break;
            case R.id.iv_zndh_jump:
                if (EmptyUtils.isNotEmpty(mData)){
                    if(!mData.getUrl().contains("http")) {
                        String url = mData.getUrl();
                        int index=url.lastIndexOf("=");
                        int length = url.length();
                        url=url.substring(index+1,length);
                        RecordVideoPlayerActivity.startAction(mContext,url);
                        return;
                    }
                    if (EmptyUtils.isNotEmpty(mData.getShareUrl())){
                        DefWebActivity.startAction(mContext,mData.getUrl(), mData.getTitle(),mData);
                    }else{
                        DefWebActivity.startAction(mContext,mData.getUrl(), mData.getTitle(),mData);
                    }
                }
                dismiss();
                break;
        }
    }


    public void setData(TempActivityBean.DataBean data) {
        mData = data;
        ImageLoaderUtils.displayRatioTarger(mContext,ivCover,data.getPicture(),R.mipmap.ic_loading_square_small);
    }
}