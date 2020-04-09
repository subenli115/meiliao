package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.WrapperAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.adapter.helper.ListTitleHelper;
import com.ziran.meiliao.ui.adapter.helper.SubscriptionHelper;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.SpecColumnData;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */

public class AlbumTwoMoreAdapter extends MultiItemRecycleViewAdapter<Object> {

  public AlbumTwoMoreAdapter(Context context) {

      super(context, new SJKZhuanLanMultiItemType());
  }

                @Override
                public void convert(ViewHolderHelper holder, Object o, int position) {
                    switch (getItemViewType(position)) {
                        case WrapperAdapter.TITLE:
                            ListTitleHelper.convert(holder,o,AppConstant.RXTag.SJKZHUANLAN_MORE_CLICK);
                            break;
                        case TYPE_DATA:
                            SubscriptionHelper.convert(holder,  o);
                            break;
                    }
    }

    private static class SJKZhuanLanMultiItemType implements MultiItemTypeSupport<Object> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DATA:
                    return R.layout.item_home_common_more_list;
                case WrapperAdapter.TITLE:
                    return R.layout.item_single_title;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object bean) {
            if (bean instanceof HeadData) {
                return WrapperAdapter.TITLE;
            } else if (bean instanceof SpecColumnData) {
                return TYPE_DATA;
            }
            return -1;
        }
    }

}
