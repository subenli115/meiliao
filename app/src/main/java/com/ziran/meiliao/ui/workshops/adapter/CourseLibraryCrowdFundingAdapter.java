package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.CrownFundListBean;
import com.ziran.meiliao.ui.workshops.widget.ProgressTipsView;
import com.ziran.meiliao.utils.HtmlUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class CourseLibraryCrowdFundingAdapter extends CommonRecycleViewAdapter<CrownFundListBean> {

    public CourseLibraryCrowdFundingAdapter(Context context) {
        super(context, R.layout.item_course_library_crowd_funding);
    }

    @Override
    public void convert(ViewHolderHelper helper, CrownFundListBean bean, int position) {
        helper.setImageUrlTarget(R.id.iv_item_workshops_main_crowd_funding_pic, bean.getPicture(),R.mipmap.ic_loading_square_big);
//        helper.setImageUrlTarget(R.id.iv_item_workshops_main_crowd_funding_pic, AppConstant.URL,R.mipmap.ic_loading_square_big);
        helper.setText(R.id.tv_item_workshops_main_crowd_funding_title,bean.getTitle());
        helper.setText(R.id.tv_item_workshops_main_crowd_funding_price, HtmlUtil.format("¥%d",bean.getIntAvgPrice()));
        helper.setText(R.id.tv_item_workshops_main_crowd_funding_people,String.valueOf(bean.getTargetMembers()));
        helper.setText(R.id.tv_item_workshops_main_crowd_funding_time,bean.getTotalTime());
        ProgressTipsView progressTipsView = helper.getView(R.id.progressTipsView);
        progressTipsView.setParms(bean.getSupportMembers(), bean.getLeftTime(),bean.getTargetMembers());
    }
}
