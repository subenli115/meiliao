package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class HotCourseAdapter extends CommonRecycleViewAdapter<SJKSingeLiveData> {

    public HotCourseAdapter(Context context) {
        super(context, R.layout.item_main_sjk_live_history);
    }

    @Override
    public void convert(ViewHolderHelper helper, SJKSingeLiveData bean, int position) {
        helper.setText(R.id.tv_item_sjk_live_course_listerCount, String.valueOf(bean.getWatchCount()));
        helper.setText(R.id.tv_item_sjk_live_course_name, bean.getTitle());
        helper.setImageUrl(R.id.iv_item_sjk_live_course_img, bean.getPicture());
        helper.setText(R.id.tv_item_sjk_live_course_time, bean.getDuration());
        helper.setText(R.id.tv_item_sjk_live_course_lable, bean.getVip());
    }
}
