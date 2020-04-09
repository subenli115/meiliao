package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class CrowdFundingChooseTeacherAdapter extends CommonRecycleViewAdapter<String> {

    public CrowdFundingChooseTeacherAdapter(Context context) {
        super(context, R.layout.item_crowd_funding_choose_topic);
    }

    @Override
    public void convert(ViewHolderHelper helper, String s, int position) {
        helper.setText(R.id.tv_item_crowd_funding_choose_topic_title,"10天深度睡眠");
        helper.setText(R.id.tv_item_crowd_funding_choose_topic_profile,"简介简介简介简介简介简介简介简介简介简");
        helper.setImageUrlTarget(R.id.iv_item_crowd_funding_choose_topic_cover, AppConstant.URL,R.mipmap.ic_loading_square);
    }
}
