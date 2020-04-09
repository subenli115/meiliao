package com.ziran.meiliao.ui.me.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.CrowdFundingDetailBuyBean;
import com.ziran.meiliao.utils.HtmlUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 14:21
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class MyCrowdFundingDetailAdapter extends CommonRecycleViewAdapter<CrowdFundingDetailBuyBean.DataBean.DetailListBean> {
    private int fromType;

    public MyCrowdFundingDetailAdapter(Context context, int fromType) {
        super(context, R.layout.item_crowd_funding_detail);
        this.fromType = fromType;
    }

    @Override
    public void convert(ViewHolderHelper helper, CrowdFundingDetailBuyBean.DataBean.DetailListBean bean, int position) {
        helper.setImageCircle(R.id.iv_avatar, bean.getPicture());
        helper.setText(R.id.tv_name, bean.getName());
        helper.setText(R.id.tv_content,getContent("关注了该众筹", bean.getTotalPrice()));
        helper.setText(R.id.tv_time, TimeUtil.getStringByFormat(bean.getTime()));

    }

    private CharSequence getContent(String s, int price) {
        switch (fromType) {
            case 0:
                return s;
            case 1:
                return HtmlUtil.crowdFundingContent("支持了: ",price);
            case 2:
                return HtmlUtil.crowdFundingContent("发起了退款: ",price);
        }
        return "";
    }


}
