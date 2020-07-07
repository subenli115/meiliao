package com.ziran.meiliao.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cy.rollpagerview.CYColorPointHintView;
import com.cy.rollpagerview.CYLoopPagerAdapter;
import com.cy.rollpagerview.CYRollPagerView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.BootCampBean;
import com.ziran.meiliao.ui.bean.ChartData;
import com.ziran.meiliao.ui.bean.CourseGZLBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.HotAlbumBean;
import com.ziran.meiliao.ui.bean.RecActivityBean;
import com.ziran.meiliao.ui.bean.ZhiBoData;
import com.ziran.meiliao.ui.bean.ZhuanLanBean;
import com.ziran.meiliao.ui.main.listener.OnClickMoreListener;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.priavteclasses.util.CountDownUtil;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import static com.ziran.meiliao.ui.bean.NewHomeDataBean.DataBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${首页适配器}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */


public class NewMainHomeAdapter extends MultiItemRecycleViewAdapter<Object> {
    private final RecyclerView.RecycledViewPool viewPool;
    private final Activity mActivity;
    private final RxManager mRxManager;
    protected OnItemClickListener mOnItemClickListener;
    private RecyclerView recyclerView;
    private CYLoopPagerAdapter<DataBean.RecActivityBean> cyLoopPagerAdapter;

    public NewMainHomeAdapter(Context context, Activity activity, MultiItemTypeSupport<Object> multiItemTypeSupport, RxManager mRxManager) {
        super(context, multiItemTypeSupport);
        mActivity=activity;
        this.mRxManager=mRxManager;
        viewPool = new RecyclerView.RecycledViewPool();
    }
    public interface ItemClickListener{
        void itemClick(int id);
    }


    public void update(){
    }

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    @Override
    public void convert(ViewHolderHelper holder, Object bean, final int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case HeadData.Type.TOP_OTHER:
               RecActivityBean  rabean = EmptyUtils.parseObject(bean);
                final RelativeLayout aflb = (RelativeLayout) holder.getView(R.id.afl_banner);
                if(rabean.getBeans()==null){
                    aflb.setVisibility(View.GONE);
                   return;
               }
                final AutoLinearLayout all_zztj = (AutoLinearLayout)holder.getView(R.id.all_zztj);
                final AutoLinearLayout all_yxs =(AutoLinearLayout) holder.getView(R.id.all_yxs);
                 final   ImageView ivbc = (ImageView) holder.getView(R.id.iv_banner_close);
                final   View view_line = (View) holder.getView(R.id.view_line);
                   CYRollPagerView cyRollPagerView = (CYRollPagerView) holder.getView(R.id.vp);
                ivbc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aflb.setVisibility(View.GONE);
//                        view_line.setVisibility(View.GONE);
                    }
                });
                all_yxs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (itemClickListener != null) {
                            itemClickListener.itemClick(all_yxs.getId());
                        }
                    }
                });
                all_zztj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (itemClickListener != null) {
                            itemClickListener.itemClick(all_zztj.getId());
                        }
                    }
                });
                setLBLayout(cyRollPagerView,rabean);
                break;
            case HeadData.Type.TITLE:
                HeadData dataBean = EmptyUtils.parseObject(bean);
                holder.setText(R.id.tv_item_main_home_title, dataBean.getTitle());
                holder.setVisible(R.id.view_top, dataBean.isShowDivler());
                holder.setOnClickListener(R.id.tv_item_main_home_more, new OnClickMoreListener(dataBean, AppConstant.RXTag
                        .MAIN_HOME_MORE_CLICK));
                if(dataBean.getTitle().equals("")){
                    View view = holder.getView(R.id.view1);
                    if(view!=null){
                       view .setVisibility(View.GONE);
                    }
                }
                break;
            case HeadData.Type.GONGZUOFANG_LEFT:
                break;
            case HeadData.Type.GONGZUOFANG_TOP:
                break;
            case HeadData.Type.ZHIBO:
                ZhiBoData zhiBoData = EmptyUtils.parseObject(bean);
                holder.setText(R.id.tv_item_main_home_zhibo_title, zhiBoData.getTitle());
                CountDownUtil.get().execute(holder, R.id.tv_item_main_home_zhibo_cown_time, zhiBoData.getCountDown(), null);
                break;
            case HeadData.Type.ZHUANLAN:
                setZuanLanAdapter(holder,bean);

                break;
            case HeadData.Type.BOOTCAMP:
                setBootCampAdapter(holder,bean);
                break;
            case HeadData.Type.COURSE:
                setCourseAdapter(holder,bean);
                break;
            case HeadData.Type.ALBUM:
                setHotAlbumAdapter(holder,bean);
                break;
        }
    }

    private void setLBLayout(CYRollPagerView cyRollPagerView, RecActivityBean rabean) {
        List<DataBean.RecActivityBean> beans = rabean.getBeans();
        cyLoopPagerAdapter=new CYLoopPagerAdapter<DataBean.RecActivityBean>(cyRollPagerView,beans) {


            @Override
            public void bindDataToView(ViewHolder holder, int position, DataBean.RecActivityBean bean) {
                ImageView view = holder.getView(R.id.iv);
                Glide.with(mContext).load(bean.getPicture()).transform(new GlideRoundTransform(mContext,5)).into(view);
            }


            @Override
            public int getItemLayoutID(int position, DataBean.RecActivityBean bean) {
                return R.layout.item_layout_pager;
            }
            @Override
            public void onItemClick(int position, DataBean.RecActivityBean bean) {
                String url = bean.getUrl();
            }
        };
        cyRollPagerView.setAdapter(cyLoopPagerAdapter,3000,new CYColorPointHintView(mContext));
    }

    private void setCourseAdapter(ViewHolderHelper holder, Object bean) {
         recyclerView = setCommonManager(holder);
        final CourseGZLBean data = EmptyUtils.parseObject(bean);
        CourseAdapter adapter = new CourseAdapter(data.getActivity(),mContext);
        recyclerView.setAdapter(adapter);
    }
    private void setHotAlbumAdapter(ViewHolderHelper holder, Object bean) {
        recyclerView = setCommonManager(holder);
        final HotAlbumBean data = EmptyUtils.parseObject(bean);
        HotAlbumAdapter hotAlbumAdapter = new HotAlbumAdapter(data.getHotAlbum(),mContext);
        hotAlbumAdapter.setItemClickListener(new HotAlbumAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, int itemId) {
            }
        });
        recyclerView.setAdapter(hotAlbumAdapter);
    }

    private void setBootCampAdapter(ViewHolderHelper holder, Object bean) {
         recyclerView = setCommonManager(holder);
        BootCampBean data = EmptyUtils.parseObject(bean);
    }


    private void setZuanLanAdapter(ViewHolderHelper holder, Object bean) {
        recyclerView = setCommonManager(holder);
        ZhuanLanBean data = EmptyUtils.parseObject(bean);

    }

    @NonNull
    private RecyclerView setCommonManager(ViewHolderHelper holder) {
         recyclerView = holder.getView(R.id.rv_item);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) ;
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        return recyclerView;
    }

    public static class NewMainHomeMultiItemType implements MultiItemTypeSupport<Object> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case HeadData.Type.TOP_OTHER:
                    return R.layout.headerview_main_home_new_other;
                case HeadData.Type.TOP:
                    return R.layout.headerview_main_home_new;
                case HeadData.Type.TITLE:
                    return R.layout.item_main_home_title;
                case HeadData.Type.BOOTCAMP:
                case HeadData.Type.ALBUM:
                case HeadData.Type.ZHUANLAN:
                case HeadData.Type.COURSE:
                    return R.layout.item_home_common_more_list;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object dataBean) {
            if (dataBean instanceof HeadData) {
            return HeadData.Type.TITLE;
        } else if (dataBean instanceof BootCampBean) {
            return HeadData.Type.BOOTCAMP;
        } else if (dataBean instanceof ZhuanLanBean) {
            return HeadData.Type.ZHUANLAN;
        } else if (dataBean instanceof HotAlbumBean) {
            return  HeadData.Type.ALBUM ;
        } else if(dataBean instanceof CourseGZLBean){
                return  HeadData.Type.COURSE ;
            }else if(dataBean instanceof ChartData){
                return HeadData.Type.TOP;
            }else if(dataBean instanceof RecActivityBean){
                return HeadData.Type.TOP_OTHER;
            }
            return 0;
    }
    }


}
