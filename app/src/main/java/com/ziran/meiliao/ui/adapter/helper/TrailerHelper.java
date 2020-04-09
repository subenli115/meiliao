package com.ziran.meiliao.ui.adapter.helper;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/27 19:11
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/27$
 * @updateDes ${TODO}
 */

public class TrailerHelper {

    public  static  void convert(final ViewHolderHelper helper, SJKSingeLiveData bean,   boolean showVip) {

        helper.setText(R.id.tv_sjk_tralier_title, bean.getTitle());
        helper.setText(R.id.tv_sjk_tralier_intro, bean.getIntro());
        if (showVip) {
            helper.setText(R.id.tv_sjk_tralier_lable, bean.getVip());
        }
        helper.setVisible(R.id.tv_sjk_tralier_lable, showVip);
        helper.setImageUrlTarget(R.id.iv_sjk_tralier_img,bean.getPicture(),R.mipmap.ic_loading_square);
        if (EmptyUtils.isNotEmpty(bean.getAuthor())) {
            helper.setText(R.id.tv_sjk_tralier_name, bean.getAuthor().getName());
            helper.setText(R.id.tv_sjk_tralier_descript, bean.getAuthor().getDescript());
        }

    }
}
