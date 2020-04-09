package com.ziran.meiliao.ui.adapter.helper;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.ui.bean.HeadData;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/27 18:25
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/27$
 * @updateDes ${TODO}
 */

public class ListTitleHelper {

    public static void convert(final ViewHolderHelper holder, final Object o , final String rxTag) {
        final HeadData bean = EmptyUtils.parseObject(o);
        holder.setText(R.id.tv_item_jyg_audio_title, bean.getTitle());
        View view = holder.getView(R.id.tv_item_jyg_more);
        view.setVisibility(bean.isShowMoreTitle() ? View.VISIBLE : View.GONE);


        holder.setOnClickListener(R.id.tv_item_jyg_more, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxManagerUtil.post(rxTag, bean.getId());
            }
        });
    }
}
