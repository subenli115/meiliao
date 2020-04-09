package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.TrailerDetailBean;

import java.util.List;

/**
 * 减压馆-消息
 * Created by Administrator on 2017/1/14.
 */

public class TrailerAdapter extends CommonRecycleViewAdapter<TrailerDetailBean.DataBean.DirBean> {

    private int[] tvIds = {R.id.tv_step1, R.id.tv_step2, R.id.tv_step3, R.id.tv_step4};
    private int[] rlIds = {R.id.rl_step1, R.id.rl_step2, R.id.rl_step3, R.id.rl_step4};

    public TrailerAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, final TrailerDetailBean.DataBean.DirBean bean, int position) {
        List<TrailerDetailBean.DataBean.DirBean.StepBean> step = bean.getStep();
        int count = step.size();
        helper.setText(R.id.tv_title, bean.getTitle());
        helper.setText(R.id.tv_position, String.valueOf(position + 1));
        for (int i = 0; i < rlIds.length; i++) {
            helper.setVisible(rlIds[i], i < count);
            if (i < count) {
                TextView tv = helper.getView(tvIds[i]);
                tv.setText(Html.fromHtml(step.get(i).getStepName()));
            }
        }
        helper.setVisible(R.id.ll_content,bean.isOpen());
        helper.setOnClickListener(R.id.ll_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setOpen(!bean.isOpen());
                helper.setVisible(R.id.ll_content,bean.isOpen());
            }
        });
    }
}
