package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.ActisData;
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

public class WorkshopsMainWorkshopsAdapter extends CommonRecycleViewAdapter<ActisData> {

    public WorkshopsMainWorkshopsAdapter(Context context) {
        super(context, R.layout.item_workshops_main_workshops);
    }

    @Override
    protected float getWidthRatio() {
        return 0.93f;
    }

    @Override
    public void convert(ViewHolderHelper helper, ActisData bean, int position) {
        helper.setImageUrl(R.id.iv_item_workshops_main_workshops_cover, bean.getPicture(), R.mipmap.ic_loading_rectangle);
        helper.setText(R.id.tv_item_workshops_main_workshops_title, bean.getTitle());
        helper.setText(R.id.tv_item_workshops_main_workshops_address, bean.getAddress());
        helper.setText(R.id.tv_item_workshops_main_workshops_time, HtmlUtil.format("%s 至 %s", bean.getBeginTime(), bean.getEndTime()));
    }
}
