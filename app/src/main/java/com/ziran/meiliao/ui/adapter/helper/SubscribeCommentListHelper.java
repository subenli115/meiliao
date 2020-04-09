package com.ziran.meiliao.ui.adapter.helper;

import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.security.EncodeUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.SubscribeCommentListBean;
import com.ziran.meiliao.utils.HandlerUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/27 18:51
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/27$
 * @updateDes ${TODO}
 */

public class SubscribeCommentListHelper {

    public static void convert(final ViewHolderHelper helper, final SubscribeCommentListBean.DataBean bean, int position) {

        helper.setText(R.id.tv_item_sjk_subscribe_comment_nickname, bean.getNickName());
        helper.setText(R.id.tv_item_sjk_subscribe_comment_time, bean.getCreateTime());
        helper.setText(R.id.tv_item_sjk_subscribe_comment_content, EncodeUtil.decodeUTF(bean.getContent()));
        helper.setText(R.id.tv_item_sjk_subscribe_comment_likes, String.valueOf(bean.getPraiseCount()));
        helper.setImageCircle(R.id.iv_item_sjk_subscribe_comment_avatar, bean.getHeadImg());
        final TextView tvLikes = helper.getView(R.id.tv_item_sjk_subscribe_comment_likes);
        helper.setOnClickListener(R.id.tv_item_sjk_subscribe_comment_likes, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxManagerUtil.post(AppConstant.RXTag.SUBSCRIBE_AUDIO_TAG, HandlerUtil.obj(AppConstant.RXTag
                        .SUBSCRIBE_AUDIO_TAG_POST_PRAISE_COMMENT, bean));
                bean.setPraiseCount(bean.isPraise() ? bean.getPraiseCount() - 1 : bean.getPraiseCount() + 1);
                setLikes(!bean.isPraise(), tvLikes, bean.getPraiseCount());

            }
        });

        setLikes(bean.isPraise(), tvLikes, bean.getPraiseCount());
    }

    private static void setLikes(boolean isPraise, TextView tvLikes, int praiseCount) {
        tvLikes.setCompoundDrawablesWithIntrinsicBounds(isPraise ? R.mipmap.course_btn_like_pre : R.mipmap.course_btn_like, 0, 0, 0);
        tvLikes.setText(praiseCount == 0 ? "" : String.valueOf(praiseCount));
    }
}
