package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.AlbumTagBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.priavteclasses.activity.AlbumMoreTwoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */


public class AlbumClassIfyAdapter extends MultiItemRecycleViewAdapter<Object> {

    private RecyclerView recyclerView;
    private HeadData dataBean;
    private AlbumTwoClassAdapter mAdapter;
    private GridLayoutManager gridLayoutManager;

    public AlbumClassIfyAdapter(Context context) {
                    super(context, new SJKZhuanLanMultiItemType());
                }

                @Override
                public void convert(ViewHolderHelper holder, Object o, int position) {
                    switch (getItemViewType(position)) {
                        case HeadData.Type.TITLE:
                             dataBean = EmptyUtils.parseObject(o);
                            holder.setText(R.id.tv_item_main_home_title, dataBean.getTitle());
                            holder.setVisible(R.id.view_top, dataBean.isShowDivler());
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

    private void setBootCampAdapter(ViewHolderHelper holder, Object o) {
        recyclerView = setCommonManager(holder);

        final RecyclerView rv = holder.itemView.findViewById(R.id.rv_bottom);

        final AlbumTagBean data = EmptyUtils.parseObject(o);
         gridLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        final List<Boolean> booleans = new ArrayList<>();
        for(int i=0;i<data.getNextTagList().size();i++){
            booleans.add(true);
        }

        mAdapter = new AlbumTwoClassAdapter(mContext, data.getNextTagList());

        mAdapter.setOnItemClickListener(new AlbumTwoClassAdapter.OnItemClickListener() {

            @Override
            public void itemClick(int position, int TagId, String nextTagName, View view, String name, boolean isSave) {
                HeadData headData = HeadData.create(HeadData.Type.TITLE, "", false);
//                    if(name.equals("导师")){
//                        rv.setLayoutManager(new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false));
//                        List<String> list = new ArrayList<>();
//                        list.add("123");
//                        list.add("321");
//                        list.add("213");
//                        list.add("333");
//                        AlbumTwoTeacherAdapter  adapter = new AlbumTwoTeacherAdapter(mContext,list);
//                        if(isSave){
//                            rv.setVisibility(View.VISIBLE);
//                        }else {
//                            rv.setVisibility(View.GONE);
//                        }
//                        rv.setAdapter(adapter);
//                    }else {
                        headData.setId(TagId);
                        headData.setTitle(nextTagName);
                        rv.setVisibility(View.GONE);
                        AlbumMoreTwoActivity.startAction(mContext,headData);
//                    }
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    public static class SJKZhuanLanMultiItemType implements MultiItemTypeSupport<Object> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DATA:
                    return R.layout.item_home_common_more_list;
                case HeadData.Type.TITLE:
                    return R.layout.item_classify_title;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object bean) {
            if (bean instanceof HeadData) {
                return HeadData.Type.TITLE;
            } else if (bean instanceof AlbumTagBean) {
                return TYPE_DATA;
            }
            return -1;
        }
    }

}
