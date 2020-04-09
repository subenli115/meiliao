package com.ziran.meiliao.ui.priavteclasses.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.priavteclasses.activity.ZhuanLanCommentActivity;
import com.ziran.meiliao.utils.StringUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/8 18:15
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/8$
 * @updateDes ${TODO}
 */

public class ZhuanLanSubscribeProfitUtil {
    private Context mContext;
    private View headView;
    private TextView tvHeadDetail;
    private TextView tvHeadProfit;
    private TextView tvHeadTitle;

    private View footerView;
    private View comment;

    public ZhuanLanSubscribeProfitUtil(Context context) {
        mContext = context;
    }
    private boolean ableComment;

    public void setAbleComment(boolean ableComment) {
        this.ableComment = ableComment;
    }

    public void init() {
        if (headView == null) {
            headView = View.inflate(mContext, R.layout.headerview_sjk_subscribe_profit, null);
            tvHeadTitle = ViewUtil.getView(headView, R.id.tv_head_sjk_subscribe_title);
            tvHeadDetail = ViewUtil.getView(headView, R.id.tv_head_sjk_subscribe_detail);
            tvHeadProfit = ViewUtil.getView(headView, R.id.tv_head_sjk_subscribe_profit);
            comment= ViewUtil.getView(headView, R.id.tv_head_sjk_subscribe_edit_comment);
            ViewUtil.getView(headView, R.id.tv_head_sjk_subscribe_edit_comment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ableComment){
                        RxManagerUtil.post(AppConstant.RXTag.SUBSCRIBE_COMMENT_FRAGMENT_SHOW_OR_HIDE, true);
                    }else{
                        ToastUitl.showShort("单个课程只能评论3次");
                    }
                }
            });
        }
    }

    public void setData(SubscribeAudioDataBean.DataBean bean) {
        ableComment = bean.isAbleComment();
        ViewUtil.setText(tvHeadTitle, bean.getTitle());

        ViewUtil.setHtmlText(tvHeadProfit, bean.getDetail());
        String buyCount = bean.getBuyCount() == 0 ? "" : "订阅 " + bean.getBuyCount();
        if (EmptyUtils.isNotEmpty(bean.getSeeCount())) {
            ViewUtil.setText(tvHeadDetail, StringUtils.format("%s  阅览%s  %s", bean.getCreateTime(), bean.getSeeCount(), buyCount));
        } else {
            ViewUtil.setText(tvHeadDetail, StringUtils.format("%s  %s", bean.getCreateTime(), buyCount));
        }
    }

    public void bindTarget(IRecyclerView iRecyclerView) {
        if (iRecyclerView != null && headView != null) {
            iRecyclerView.setHeadView(headView);
        }
    }

    private TextView tvFooter;
    public void bindFooterView(IRecyclerView iRecyclerView, CharSequence text, final String targetId, final String targetKey){
        if (footerView==null){
            footerView =  LayoutInflater.from(mContext).inflate(R.layout.footerview_text,null);
            tvFooter = ViewUtil.getView(footerView,R.id.tv_title);
            footerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ZhuanLanCommentActivity.startAction(mContext,targetKey,targetId);
                }
            });
        }
        tvFooter.setText(text);
        iRecyclerView.setFooterView(footerView);
    }

    public void setVisit() {
        comment.setVisibility(View.INVISIBLE);
    }
}
