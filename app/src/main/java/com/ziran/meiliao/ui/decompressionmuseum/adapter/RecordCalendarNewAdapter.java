package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.UserNoteBean;
import com.ziran.meiliao.utils.SlidingMenuUtil;
import com.ziran.meiliao.widget.SlidingMenu;

import java.util.List;

/**
 * 正念练习界面练习记录的适配器
 */

public class RecordCalendarNewAdapter extends CommonRecycleViewAdapter<UserNoteBean.DataBean> {

    private SlidingMenuUtil mSlidingMenuUtil;

    public RecordCalendarNewAdapter(Context context, int layoutId) {
        super(context, layoutId);
        mSlidingMenuUtil = new SlidingMenuUtil();
        setHeadCount(0);
    }

    @Override
    public void convert(final ViewHolderHelper helper, final UserNoteBean.DataBean dataBean, final int position) {
        SlidingMenu slidingMenu = helper.getView(R.id.slidingMenu);
        slidingMenu.setSlidingMenuListener(mSlidingMenuUtil);
        slidingMenu.setCustomOnClickListener(new SlidingMenu.CustomOnClickListener() {
            @Override
            public void onContentClick() {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(null, helper.getConvertView(), dataBean, position);
                }
            }
            @Override
            public void onMenuClick() {
                LogUtils.logd("执行删除笔记请求");
                dataBean.setPosition(position);
                mSlidingMenuUtil.closeOpenMenu();
                RxManagerUtil.post(AppConstant.RXTag.DELETE_NOTES, dataBean);
            }
        });

        //默认显示内容的第一条
        List<UserNoteBean.DataBean.QuesListBean> quesList = dataBean.getQuesList();
        if (EmptyUtils.isNotEmpty(quesList)) {
            helper.setText(R.id.tv_item_record_calendar_content, quesList.get(0).getContent());
        }
        //默认显示第一张图片,如果没有图片则隐藏
        helper.setText(R.id.tv_item_record_calendar_time, dataBean.getCreateTime());
        ImageView iv = helper.getView(R.id.iv_item_record_calendar_pic);
        if (EmptyUtils.isNotEmpty(dataBean.getPics())) {
            helper.setImageUrl(R.id.iv_item_record_calendar_pic, dataBean.getPics().get(0), R.mipmap.ic_loading_rectangle);
            iv.setVisibility(View.VISIBLE);
        } else {
            iv.setVisibility(View.INVISIBLE);
        }
    }

}
