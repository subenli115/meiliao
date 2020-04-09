package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.settings.fragment.GetGoalFragment;

/**
 * Created by Administrator on 2017/2/8.
 */

public class GetGoalAdapter extends CommonRecycleViewAdapter<GetGoalFragment.Item> {

    public GetGoalAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper holder, GetGoalFragment.Item bean, int position) {
        holder.setText(R.id.tv_get_goal_title, bean.getTitle());
        holder.setText(R.id.tv_get_goal_amount, String .format("+%d",bean.getAmount()));
        boolean flag = bean.isReceived();
        int rightIcon = flag ? R.mipmap.ic_goal_check : R.mipmap.ic_goal_arrow_right;
        int amountTextColor = flag ? R.color.white : R.color.textColor_teshe5;
        int amountTextBackGroup = flag ? R.mipmap.course_icon_lesson_dot : 0;
        holder.setBackgroundRes(R.id.iv_get_goal_icon, rightIcon);
        holder.setTextColor(R.id.tv_get_goal_amount, amountTextColor);
        holder.setBackgroundRes(R.id.tv_get_goal_amount, amountTextBackGroup);
    }
}
