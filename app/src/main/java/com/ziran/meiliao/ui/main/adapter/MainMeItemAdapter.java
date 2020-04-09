package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.widget.MainMeItemView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${我的界面Item的适配器}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */


public class MainMeItemAdapter extends CommonRecycleViewAdapter<MainMeItemView.Items> {

    private final int displayWidth;

    public MainMeItemAdapter(Context context, int layoutId) {
        super(context, layoutId);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        displayWidth = dm.widthPixels;
    }

    @Override
    public void convert(ViewHolderHelper holder, MainMeItemView.Items item, int position) {
        TextView tv = holder.getView(R.id.tv_main_me_new_item);
        tv.setText(item.getName());
        if(position==1){
            tv.setPadding(displayWidth/12, 7, displayWidth/12, 12);
        }else{
            tv.setPadding(displayWidth/12, 12, displayWidth/12, 12);
        }
        tv.setCompoundDrawablesWithIntrinsicBounds(0, item.getIcon(), 0, 0);
    }
}
