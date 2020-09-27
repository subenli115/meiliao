package com.ziran.meiliao.ui.main.fragment;

import android.content.Context;
import android.view.View;

import com.atech.staggedrv.GridItemDecoration;
import com.atech.staggedrv.StaggerdRecyclerView;
import com.atech.staggedrv.callbacks.LoadMoreAndRefresh;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.AliVideoInfoBean;
import com.ziran.meiliao.ui.bean.SpaceRecommendBean;
import com.ziran.meiliao.ui.main.adapter.DynamicStaggedAdapter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


/**
 *
 * 社区关注的动态
 *
 */
public class RecommendFollowDynamicFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<SpaceRecommendBean>  {
    private List<SpaceRecommendBean.DataBean> datas = new ArrayList<>();
    @Bind(R.id.str)
    StaggerdRecyclerView str;
    private DynamicStaggedAdapter<SpaceRecommendBean.DataBean> staggedAdapter;
    private int page=1;
    private boolean refresh=true;
    private List<SpaceRecommendBean.DataBean> data;
    private Context context;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_dynamic_recommend;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
         context = getContext();
        staggedAdapter = new DynamicStaggedAdapter<>(getContext());

        str.link(staggedAdapter,2);
        //动画效果
        //间距
        str.addDecoration(new GridItemDecoration(getContext(), 12));

        str.addCallbackListener(new LoadMoreAndRefresh() {
            @Override
            public void onLoadMore() {
                refresh=false;
                page++;
                loadData(page);
            }

            @Override
            public void onRefresh() {
                refresh=true;
                page=1;
                loadData(page);
            }
        });
        loadData(page);
        //订阅更新用户头像
    }

    private void loadData(int page) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("current",page+"");
        defMap.put("size","50");
        defMap.put("userId", MyAPP.getUserId());
        mPresenter.getData(ApiKey.ADMIN_SPACE_FOLLOWSPACE,defMap, SpaceRecommendBean.class);
    }




    @OnClick({})
    public void onViewClicked(View view) {
    }

    @Override
    public void returnData(SpaceRecommendBean result) {
        data = result.getData();
        if(result.getData().size()!=0){
            for( int i=0;i<data.size();i++){
                SpaceRecommendBean.DataBean bean = data.get(i);
                    setImg(i, bean);
            }
            if (refresh){
                if(datas.size()==0){
                    datas.addAll(data);
                }
                staggedAdapter.refresh(data);
            }else{
                //模拟加载更多
                datas.addAll(data);
                staggedAdapter.loadMore(datas);

            }
        }
    }

    private void setImg(int i, SpaceRecommendBean.DataBean bean) {
        if(bean.getHeight()==0){
            bean.setWidth(ScreenUtils.getScreenWidth(getContext()) / 2);
            bean.setHeight(ScreenUtils.getScreenWidth(getContext()) / 2+(int)(Math.random()*100));
        }
    }

}
