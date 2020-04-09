package com.ziran.meiliao.ui.adapter.helper;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.envet.MyCallBack;
import com.ziran.meiliao.ui.bean.SpecColumnData;
import com.ziran.meiliao.utils.CatchUtil;
import com.ziran.meiliao.widget.TagImageView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/27 18:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/27$
 * @updateDes ${TODO}
 */

public class SubscriptionHelper {
    public static void convert(final ViewHolderHelper holder, final Object obj) {
        final SpecColumnData specColumnData = EmptyUtils.parseObject(obj);
        holder.setImageUrlTarget(R.id.iv_item_sjk_recommend_pic, specColumnData.getPic(), R.mipmap.ic_loading_square_small);

        holder.setText(R.id.tv_item_sjk_recommend_title, specColumnData.getTitle());
        holder.setText(R.id.tv_item_sjk_recommend_des, specColumnData.getDescript());
        if (EmptyUtils.isNotEmpty(specColumnData.getTag())) {
            holder.setVisible(R.id.tv_item_sjk_recommend_tag, true);
            holder.setText(R.id.tv_item_sjk_recommend_tag, specColumnData.getTag());
        } else {
            holder.setVisible(R.id.tv_item_sjk_recommend_tag, false);
        }
        CatchUtil.execute(new MyCallBack() {
            @Override
            public void call() {
                TagImageView tagImageView =holder.getView(R.id.iv_item_sjk_recommend_pic);
                tagImageView.setTagByType(specColumnData.getType());
            }
        });
    }
}
