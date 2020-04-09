package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class HasTickPopupWindow extends BasePopupWindow {

    private TextView tvName;
    private ImageView ivCover;
    private TextView tvSingle;

    public HasTickPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_has_tick;
    }

    @Override
    protected void initViews(View contentView) {
        tvName = getView(R.id.tv_popuw_has_tick_name);
        tvSingle = getView(R.id.tv_popuw_has_tick_single);
        ivCover = getView(R.id.iv_popuw_has_tick_cover);
        setOnClickListener(R.id.tv_popuw_has_tick_used);
        touchDismiss(R.id.background);
    }

    public void setParmes(String name, String cover, String single) {
        ViewUtil.setText(tvName, name);
        ViewUtil.setText(tvSingle, single);
        ImageLoaderUtils.displayTager(mContext, ivCover, cover, R.mipmap.ic_loading_square);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_popuw_has_tick_used:
                SJKLiveDetailProfileBean.DataBean.UserTickBean userTickBean = new SJKLiveDetailProfileBean.DataBean.UserTickBean();
                RxManagerUtil.post(AppConstant.RXTag.USER_TICK, userTickBean);
                dismiss();
        }
    }
}