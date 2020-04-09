package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.utils.StringUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */


public class SJKZhuanLanSubscribeAdapter extends CommonRecycleViewAdapter<RecordListBean.DataBean> {
    private int freeOrSubscribe;

    public SJKZhuanLanSubscribeAdapter(Context context, int layoutId, int freeOrSubscribe) {
        super(context, layoutId);
        this.freeOrSubscribe = freeOrSubscribe;
    }

    @Override
    public void convert(ViewHolderHelper holder, RecordListBean.DataBean bean, int position) {
        ImageView iv = holder.getView(R.id.iv_item_sjk_zhuanlan_subscribe_pic);
        ImageLoaderUtils.displayTager(mContext,iv,bean.getPic(), R.mipmap.ic_loading_square_big);
        holder.setVisible(R.id.tv_item_sjk_zhuanlan_subscribe_tag, freeOrSubscribe == 0);
        holder.setImageResource(R.id.iv_item_sjk_zhuanlan_subscribe_flag, getRes(2 == bean.getType()));
        String buyTimes = bean.getBuyTimes() == 0 ? "" : "订阅" + bean.getBuyCount();
        holder.setText(R.id.tv_item_sjk_zhuanlan_subscribe_bottom, StringUtils.format("阅览%d %s %s", bean.getSeeCount(), buyTimes, bean
                .getCreateTime()));
        holder.setText(R.id.tv_item_sjk_zhuanlan_subscribe_title,bean.getTitle());
        if (EmptyUtils.isNotEmpty(bean.getDetail())){
            holder.setText(R.id.tv_item_sjk_zhuanlan_subscribe_subTitle, Html.fromHtml(bean.getDetail()));
        }
    }

    private int getRes(boolean b) {
        return b ? R.mipmap.course_ic_audio : R.mipmap.course_ic_video;
    }

}
