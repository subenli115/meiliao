package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.ui.bean.SJKCommentBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * Created by Administrator on 2017/1/14.
 */

public class SJKLiveDetailCommentAdapter extends CommonRecycleViewAdapter<SJKCommentBean.DataBean> {

    public SJKLiveDetailCommentAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, SJKCommentBean.DataBean bean, int position) {
        ImageView iv_avatar = helper.getView(R.id.iv_item_sjk_live_comment_avatar);
        helper.setText(R.id.tv_item_sjk_live_comment_name, bean.getNickName());
        if (bean.IsMe()) {
            ImageLoaderUtils.displayCircle(mContext, iv_avatar, StringUtils.headImg(), R.mipmap.ic_member_pic);
        } else {
            ImageLoaderUtils.displayCircle(mContext, iv_avatar, bean.getHeadImg(), R.mipmap.ic_member_pic);
        }
        TextView msgText = helper.getView(R.id.tv_item_sjk_live_comment_content);
        msgText.setText(EncodeUtil.decodeUTF(bean.getContent()));
        helper.setText(R.id.tv_item_sjk_live_comment_time, bean.getCreateTime());
    }
}
