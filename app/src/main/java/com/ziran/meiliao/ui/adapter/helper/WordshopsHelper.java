package com.ziran.meiliao.ui.adapter.helper;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.priavteclasses.util.CountDownUtil;
import com.ziran.meiliao.utils.StringUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/27 18:25
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/27$
 * @updateDes ${TODO}
 */

public class WordshopsHelper {

    public static void convert(final ViewHolderHelper helper, final ActisData bean, final int position ,boolean isTypeTop) {
        final Context context = helper.getContext();
        ImageView iv_img = helper.getView(R.id.iv_item_sjk_act_img);
        helper.setText(R.id.tv_item_sjk_act_title, bean.getTitle());
        helper.setText(R.id.tv_item_sjk_act_date, StringUtils.format(R.string.starttime_to_end, bean.getBeginTime(), bean.getEndTime()));
        helper.setText(R.id.tv_item_sjk_act_place, bean.getAddress());
        ImageLoaderUtils.displayTager(context, iv_img, bean.getPicture());
        if (isTypeTop) {
            boolean isJoin = bean.getTag() == 1;
            boolean showLabel = bean.getCountDown() > 0;
            helper.setVisible(R.id.iv_item_sjk_act_label, isJoin);
            helper.setVisible(R.id.tv_item_sjk_act_count_down, isJoin && showLabel);
            helper.setVisible(R.id.tv_item_sjk_act_join, isJoin);
            if (showLabel) {
                CountDownUtil.get().execute(helper, R.id.tv_item_sjk_act_count_down, bean.getCountDown(), null);
            } else {
                CountDownUtil.get().cancel(helper);
            }
            helper.setOnClickListener(R.id.tv_item_sjk_act_join, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //网页跳转
                    bean.setPosition(position);
                    GongZuoFangActivity.startAction(context, bean, "填写报名信息");
                }
            });
        }
    }

    public static void convert(final ViewHolderHelper helper, final Object bean, final int position ,boolean isTypeTop) {
        if (bean instanceof ActisData){
            convert(helper, (ActisData) bean, position, isTypeTop);
            return;
        }
        final SearchItemBean searchItemBean = (SearchItemBean) bean;
        final Context context = helper.getContext();
        ImageView iv_img = helper.getView(R.id.iv_item_sjk_act_img);
        helper.setText(R.id.tv_item_sjk_act_title, searchItemBean.getTitle());
        helper.setText(R.id.tv_item_sjk_act_date, StringUtils.format(R.string.starttime_to_end, searchItemBean.getBeginTime(), searchItemBean.getEndTime()));
        helper.setText(R.id.tv_item_sjk_act_place, searchItemBean.getAddress());
        ImageLoaderUtils.displayTager(context, iv_img, searchItemBean.getPicture());
        if (isTypeTop) {
            boolean isJoin = "1".equals(searchItemBean.getTag());
            boolean showLabel = searchItemBean.getCountDown() > 0;
            helper.setVisible(R.id.iv_item_sjk_act_label, isJoin);
            helper.setVisible(R.id.tv_item_sjk_act_count_down, isJoin && showLabel);
            helper.setVisible(R.id.tv_item_sjk_act_join, isJoin);
            if (showLabel) {
                CountDownUtil.get().execute(helper, R.id.tv_item_sjk_act_count_down, searchItemBean.getCountDown(), null);
            } else {
                CountDownUtil.get().cancel(helper);
            }
            helper.setOnClickListener(R.id.tv_item_sjk_act_join, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //网页跳转
                    searchItemBean.setPosition(position);
                    GongZuoFangActivity.startAction(context, ActisData.change((searchItemBean)));
                }
            });
        }
    }
}
