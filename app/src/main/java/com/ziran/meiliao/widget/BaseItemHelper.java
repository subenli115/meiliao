package com.ziran.meiliao.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/10 15:21
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/10$
 * @updateDes ${TODO}
 */

public class BaseItemHelper {
    private MyCheckBox mMyCheckBoxMan, mMyCheckBoxNv;
    private Context mContext;

    public BaseItemHelper(Context context) {
        mContext = context;
    }

    public void createContent(ViewGroup viewGroup) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        View viewContainer = LayoutInflater.from(mContext).inflate(R.layout.include_sex, null);
        viewGroup.addView(viewContainer,params);
        mMyCheckBoxMan = ViewUtil.getView(viewContainer, R.id.checkMan);
        mMyCheckBoxNv = ViewUtil.getView(viewContainer, R.id.checkNv);
        mMyCheckBoxMan.setChecked(true);

        mMyCheckBoxMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mMyCheckBoxMan.isChecked()){
                    mMyCheckBoxMan.toggle();
                    mMyCheckBoxNv.toggle();
                }
            }
        });
        mMyCheckBoxNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mMyCheckBoxNv.isChecked()){
                    mMyCheckBoxMan.toggle();
                    mMyCheckBoxNv.toggle();
                }
            }
        });
    }

    public String getSex() {
        if (EmptyUtils.isEmpty(mMyCheckBoxMan)) return "";
        return mMyCheckBoxMan.isChecked() ? "男" : "女";
    }
}
