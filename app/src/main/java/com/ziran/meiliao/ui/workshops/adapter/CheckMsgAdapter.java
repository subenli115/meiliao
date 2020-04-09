package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.ziran.meiliao.ui.bean.CheckMsgBean;
import com.ziran.meiliao.widget.SmoothCheckBox;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/28 19:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/28$
 * @updateDes ${TODO}
 */

public class CheckMsgAdapter extends CommonAblistViewAdapter<CheckMsgBean> {
    public CheckMsgAdapter(Context context) {
        super(context, R.layout.item_crowd_funding_proejct_check_msg);
    }

    @Override
    public void convert(ViewHolderHelper helper, final CheckMsgBean bean, int position) {
        final SmoothCheckBox smoothCheckBox = helper.getView(R.id.smoothCheckBox);
        smoothCheckBox.setChecked(bean.isCheck());
        helper.setTextColor(R.id.tv_item_title, bean.isMustNeed() ? R.color.textColor_999 : R.color.textColor_333);
        helper.setText(R.id.tv_item_title, bean.getTitle());
        smoothCheckBox.setEnabled(!bean.isMustNeed());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bean.isMustNeed()) {
                    smoothCheckBox.setChecked(!smoothCheckBox.isChecked());
                }
            }
        });
        smoothCheckBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                bean.setCheck(isChecked);
            }
        });
    }
}
