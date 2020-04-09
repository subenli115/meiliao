package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.CommAlbumMoreBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.main.listener.OnClickMoreListener;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */


            public class SJKHomeZhuanLanAdapter1 extends MultiItemRecycleViewAdapter<Object> {

    private RecyclerView recyclerView;

    public SJKHomeZhuanLanAdapter1(Context context) {
                    super(context, new SJKZhuanLanMultiItemType());
                }

                @Override
                public void convert(ViewHolderHelper holder, Object o, int position) {
                    switch (getItemViewType(position)) {
                        case HeadData.Type.TITLE:
                            HeadData dataBean = EmptyUtils.parseObject(o);
                            holder.setText(R.id.tv_item_main_home_title, dataBean.getTitle());
                            View view = holder.getView(R.id.view1);
                            view.setVisibility(View.INVISIBLE);
                            holder.setVisible(R.id.view_top, dataBean.isShowDivler());
                            holder.setOnClickListener(R.id.tv_item_main_home_more, new OnClickMoreListener(dataBean, AppConstant.RXTag
                                    .MAIN_HOME_MORE_CLICK));
                            break;
                        case TYPE_DATA:
                            setBootCampAdapter(holder,o);
                            break;
                    }
    }
    @NonNull
    private RecyclerView setCommonManager(ViewHolderHelper holder) {
        recyclerView = holder.getView(R.id.rv_item);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) ;
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    private void setBootCampAdapter(ViewHolderHelper holder, Object bean) {
        recyclerView = setCommonManager(holder);
        CommAlbumMoreBean data = EmptyUtils.parseObject(bean);
        CommMoreMusicAdapter adapter = new CommMoreMusicAdapter(data.getDetailList(),mContext);
        recyclerView.setAdapter(adapter);
    }


    private static class SJKZhuanLanMultiItemType implements MultiItemTypeSupport<Object> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DATA:
                    return R.layout.item_home_common_more_list;
                case HeadData.Type.TITLE:
                    return R.layout.item_main_home_title;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object bean) {
            if (bean instanceof HeadData) {
                return HeadData.Type.TITLE;
            } else if (bean instanceof CommAlbumMoreBean) {
                return TYPE_DATA;
            }
            return -1;
        }
    }

}
