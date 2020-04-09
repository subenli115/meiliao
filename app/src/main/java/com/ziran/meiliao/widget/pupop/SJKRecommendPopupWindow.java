package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.AlbumGainBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.decompressionmuseum.activity.ShareAlbumActivity;
import com.ziran.meiliao.utils.MapUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class SJKRecommendPopupWindow extends BasePopupWindow {

    private ImageView ivImg;
    private TextView tvContent;
    private TextView tvYaoQing;

    public SJKRecommendPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_sjk_recommend;
    }

    @Override
    protected void initViews(View contentView) {
        initTips(contentView);


    }

    private void initTips(View contentView) {
        ivImg = ViewUtil.getView(contentView, R.id.iv_popuw_sjk_recommend_img);
        tvContent = ViewUtil.getView(contentView, R.id.tv_popuw_sjk_recommend_content);
        tvYaoQing = ViewUtil.getView(contentView, R.id.tv_popuw_sjk_recommend_get);
        setOnClickListener(R.id.iv_popuw_sjk_recommend_close);
        setOnClickListener(R.id.tv_popuw_sjk_recommend_get);
    }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.tv_popuw_sjk_recommend_get:
                    if ("马上去听".equals(tvYaoQing.getText().toString())) {
                        AlbumDetailActivity.startAction(mContext, albumId);
                    } else
                        OkHttpClientManager.postAsync(ApiKey.ALBUM_GAIN, MapUtils.getOnlyCan(AppConstant.SPKey.ALBUM_ID, albumId), new
                                NewRequestCallBack<AlbumGainBean>(AlbumGainBean.class) {
                            @Override
                            public void onSuccess(AlbumGainBean result) {
                                Bundle bundle = new Bundle();
                                bundle.putString(AppConstant.ExtraKey.ALBUM_TITLE, name);
                                bundle.putString(AppConstant.SPKey.ALBUM_ID, albumId);
                                bundle.putParcelable(AppConstant.ExtraKey.ALBUM_GAIN, result.getData());
                                ShareAlbumActivity.startAction(mContext, bundle);
                            }
                        });
                    break;
            }
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setTvContent(String content) {
        ViewUtil.setText(tvContent, content);
    }

    public void setYaoQingText(String content) {
        ViewUtil.setText(tvYaoQing, content);
    }

    public void setIvImg(String url) {
        ImageLoaderUtils.display(mContext, ivImg, url);
    }

    private String albumId;
    private String name;

    public void setPoster(String albumId, String name) {
        this.albumId = albumId;
        this.name = name;
    }

    public void setType(int type,String name) {
        switch (type){
            case 1:
                setTvContent(String .format("送您“%s”专辑,请帮忙分享5P医学至朋友圈哦^_^",name));
                setYaoQingText("点击领取");
                break;
            case 2:
                setTvContent(String .format("您已获得“%s”专辑",name));
                setYaoQingText("马上去听");
                break;
        }
    }
}