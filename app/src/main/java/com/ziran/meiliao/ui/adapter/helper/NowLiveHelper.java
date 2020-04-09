package com.ziran.meiliao.ui.adapter.helper;

import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.ui.priavteclasses.util.CountDownUtil;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/27 19:00
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/27$
 * @updateDes ${TODO}
 */

public class NowLiveHelper {


    public static void convert(final ViewHolderHelper helper, SJKSingeLiveData bean, int position, Map<Integer, Boolean> needCountTime) {
        boolean showVip = EmptyUtils.isNotEmpty(bean.getVip());
        boolean usedNeedCountTime = EmptyUtils.isNotEmpty(needCountTime);

        helper.setText(R.id.tv_item_sjk_history_course_listerCount, String.format("%s人观看", bean.getWatchCount()));
        helper.setText(R.id.tv_item_sjk_history_course_name, bean.getTitle());
        helper.setImageUrlTarget(R.id.iv_item_sjk_history_course_img, bean.getPicture(), R.mipmap.ic_loading_square_big);
        if (showVip) {
            helper.setText(R.id.tv_item_sjk_live_course_lable, bean.getVip());
        }
        helper.setVisible(R.id.tv_item_sjk_live_course_lable, showVip);
        TextView limitView = helper.getView(R.id.tv_item_sjk_history_course_limit_time);
        boolean showLabel = bean.getCountDown() > 0;
        helper.setVisible(R.id.iv_item_sjk_history_course_lable, !showLabel);
        limitView.setVisibility(showLabel ? View.VISIBLE : View.GONE);

        if (showLabel && usedNeedCountTime && !needCountTime.containsKey(position)) {
            needCountTime.put(position, true);
        }

        if (showLabel) {
            CountDownUtil.get().execute(helper, R.id.tv_item_sjk_history_course_limit_time, bean.getCountDown(), new CountDownUtil
                    .CallBack() {
                @Override
                public void onFinish() {
                    helper.setVisible(R.id.iv_item_sjk_history_course_lable, true);
                }
            });
        } else {
            if (usedNeedCountTime && needCountTime.containsKey(position)) {
                needCountTime.put(position, false);
            }
            CountDownUtil.get().cancel(helper);
        }
    }
}
