package com.ziran.meiliao.ui.main.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.bumptech.glide.Glide;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.google.gson.Gson;
import com.sj.emoji.EmojiBean;
import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ACache;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.CommonActivity;
import com.ziran.meiliao.im.model.Constants;
import com.ziran.meiliao.im.utils.CommonUtils;
import com.ziran.meiliao.im.utils.SimpleCommonUtils;
import com.ziran.meiliao.im.utils.keyboard.XhsEmoticonsKeyBoard2;
import com.ziran.meiliao.im.utils.keyboard.data.EmoticonEntity;
import com.ziran.meiliao.im.utils.keyboard.interfaces.EmoticonClickListener;
import com.ziran.meiliao.im.utils.keyboard.widget.FuncLayout;
import com.ziran.meiliao.im.view.FunctionGridView;
import com.ziran.meiliao.im.view.SimpleAppsGridView;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CommentListBean;
import com.ziran.meiliao.ui.bean.CommonListBean;
import com.ziran.meiliao.ui.bean.MediaAndTextBean;
import com.ziran.meiliao.ui.bean.SpaceDetailBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.adapter.ZLAudioOneAdapter;
import com.ziran.meiliao.ui.main.util.ZhuanLanSubscribeProfitUtil;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewCacheUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.pupop.UserPopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_COMMENT_ADD;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_COMMENT_PAGE;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_SPACE_GETBYID;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_TOOL_TESTSECURITY;
import static com.ziran.meiliao.constant.AppConstant.RXTag.SUBSCRIBE_UPDATE;


/**
 * 看图文
 */

public class DynamicAllAudioOneFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<Result>, FuncLayout.OnFuncKeyBoardListener {


    private Map<String, String> apiKeyMap;
    private List<MediaAndTextBean.DataBean> data;
    private Map<String, String> stringMap;
    private ZhuanLanSubscribeProfitUtil mZhuanLanSubscribeProfitUtil;
    private SubscribeAudioDataBean.DataBean mDataBean;
    private String spaceId;
    private boolean isSelf;
    @Bind(R.id.tv_other)
    public TextView tvOther;
    @Bind(R.id.tv_distance)
    public TextView tvDistance;
    @Bind(R.id.ib_right)
    public ImageButton ibRight;
    @Bind(R.id.tv_name)
    public TextView tvName;
    @Bind(R.id.iv_real_name)
    public ImageView ivRealName;
    @Bind(R.id.iv_top_head)
    public ImageView ivHead;
    @Bind(R.id.ek_bar)
    public XhsEmoticonsKeyBoard2 ekBar;
    @Bind(R.id.recyclerView)
    public IRecyclerView recyclerView;
    @Bind(R.id.all)
    public AutoLinearLayout all;
    @Bind(R.id.headView)
    public AutoLinearLayout headView;
    @Bind(R.id.tv_empty)
    public TextView tvEmpty;
    private String userId;
    private FunctionGridView functionGridView;
    private SimpleAppsGridView gridView;
    private UserPopupWindow pop;
    private SpaceDetailBean result1;
    private String commentId;
    private int commentType;
    private AliPlayer aliyunVodPlayer;
    private ArrayList list;
    private ZLAudioOneAdapter zlAudioOneAdapter;
    private List<CommentListBean.DataBean.RecordsBean> records;
    private String gold="0";
    private SpaceDetailBean.DataBean dataBean;
    private NewCacheUtil newCacheUtil;
    private SpaceDetailBean.DataBean bean;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview_tab;
    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
         zlAudioOneAdapter = new ZLAudioOneAdapter(getContext(), this);
        return  zlAudioOneAdapter;
    }


    @Override
    protected void initView() {
        spaceId = getIntentExtra(getActivity().getIntent(), "spaceId");
         newCacheUtil = new NewCacheUtil(getContext());
        getCommentList();
        super.initView();
        list = new ArrayList();
        getUserMoney();
        initEmoticonsKeyBoardBar();
        aliyunVodPlayer = AliPlayerFactory.createAliPlayer(getActivity().getApplicationContext());
         bean = (SpaceDetailBean.DataBean) newCacheUtil.getDataBean("spacedeatil" + spaceId, SpaceDetailBean.DataBean.class);
        if(bean!=null){
            dataBean=  bean;
            setData();
        }
        mRxManager.on(SUBSCRIBE_UPDATE, new Action1<Boolean>() {
            @Override
            public void call(Boolean balance)
            {
                refresh();
            }
        });
    }
    private void getUserMoney() {
        UserBean.DataBean dataBean = MyAPP.getmUserBean();
        if (dataBean != null&&dataBean.getUserAccount()!=null) {
            UserAccountBean.DataBean data = dataBean.getUserAccount();
            gold = (int)(data.getRecharge() + data.getCurrency())+"";
        }else {
            OkHttpClientManager.getDataOneHead(ApiKey.ACCOUNT_ACCOUNT_INFO, MyAPP.getUserId(),MyAPP.getAccessToken(), new
                    NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {

                        @Override
                        public void onSuccess(UserAccountBean result) {
                            gold = MyAPP.saveMoney(result)+"";
                        }

                        @Override
                        public void onError(String msg, int code) {

                        }
                    });
        }
    }


    private void initData(SpaceDetailBean.DataBean dataBean) {
        if(dataBean!=null){
         if(dataBean.getUserId().equals(MyAPP.getUserId())) {
            isSelf = true;
        }
        tvName.setText(dataBean.getNickname());
        Glide.with(getContext()).load(dataBean.getAvatar()).error(R.drawable.jmui_head_icon).into(ivHead);
            ivHead.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    UserHomeActivity.startAction(userId);
                }
            });
        if(isSelf){
            tvDistance.setVisibility(View.GONE);
            ibRight.setVisibility(View.GONE);
        }else {
            if(dataBean.getDistance()!=null){
                if(dataBean.getDistance().equals("0.0")){
                    tvDistance.setText("距离 "+0+"m");
                }else {
                    if(Double.parseDouble(dataBean.getDistance())>1){
                        tvDistance.setText("距离 "+dataBean.getDistance()+"km");
                    }else {
                        tvDistance.setText("距离 "+(int)(Double.parseDouble(dataBean.getDistance())*1000)+"m");
                    }
                }
            }
            if(dataBean.getIsFollow().equals("1")){
                tvOther.setVisibility(View.GONE);
            }else {
                tvOther.setVisibility(View.VISIBLE);
            }
        }
        }
    }

    //button点击监听
    @OnClick({ R.id.ib_left,R.id.ib_right,R.id.tv_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_right:
                pop = new UserPopupWindow(getContext(),userId);
                pop.show();
                CommonUtils.hideKeyboard(getActivity());
                ekBar.reset();
                break;
            case R.id.ib_left:
                finish();
                break;
            case R.id.tv_other:
                follow();
                break;
        }
    }
        public void  ekbar(View view){
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                CommonUtils.hideKeyboard(getActivity());
                ekBar.reset();
        }
        private void follow(){
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("followUserId", userId);
        defMap.put("userId", MyAPP.getUserId());
        mPresenter.postData(ApiKey.ADMIN_USERFOLLOW_ADD,defMap, StringDataV2Bean.class);
    }

    @Override
    protected void loadData() {
        if(bean==null){
            refresh();
        }
    }

    private void refresh() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id",spaceId);
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("latitude",MeiliaoConfig.getLatitude());
        defMap.put("longitude", MeiliaoConfig.getLongitude());
        mPresenter.getData(ADMIN_SPACE_GETBYID,defMap, SpaceDetailBean.class);
    }


    private void initEmoticonsKeyBoardBar() {
        SimpleCommonUtils.initEmoticonsEditText(ekBar.getEtChat());
        ekBar.setAdapter(SimpleCommonUtils.getCommonAdapter(getContext(), emoticonClickListener));
        ekBar.addOnFuncKeyBoardListener(this);
        functionGridView = new FunctionGridView(getContext(),1);
        gridView = new SimpleAppsGridView(getContext());
        ekBar.addFuncView(gridView);
//        ekBar.getEtChat().setOnSizeChangedListener((w, h, oldw, oldh) -> scrollToBottom());
        //发送按钮
        //发送文本消息
        ekBar.getBtnSend().setOnClickListener(v -> {
                    if (Utils.isOtherFastDoubleClick()) {
                        return;
                    }else {
                        String content = ekBar.getEtChat().getText().toString();
                        if(content.trim().equals("")){
                            ToastUitl.showShort("请输入内容");
                            return;
                        }
                        checkSecurity(content);
                    }


        });

    }

    private void checkSecurity(String content) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("content",content);
        OkHttpClientManager.getAsyncMore(ADMIN_TOOL_TESTSECURITY,defMap, new
                NewRequestCallBack<Result>(Result.class) {
                    @Override
                    public void onSuccess(Result result) {
                        if(result.getResultCode()==0){
                            addComment(content);
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);

                    }
                });
    }

    private void addComment(String content) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("commentUserId", MyAPP.getUserId());
        defMap.put("receiveUserId",commentId);
        defMap.put("content",content);
        defMap.put("spaceId",spaceId);
        if(content.substring(0,1).equals("@")){
            defMap.put("type","1");
        }else {
            defMap.put("type","0");
        }
        mPresenter.postData(ADMIN_COMMENT_ADD,defMap, StringDataV2Bean.class);
    }

    EmoticonClickListener emoticonClickListener = new EmoticonClickListener() {
        @Override
        public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {

            if (isDelBtn) {
                SimpleCommonUtils.delClick(ekBar.getEtChat());
            } else {
                if (o == null) {
                    return;
                }
                if (actionType == Constants.EMOTICON_CLICK_BIGIMAGE) {
                    if (o instanceof EmoticonEntity) {
//                        OnSendImage(((EmoticonEntity) o).getIconUri());
                    }
                } else {
                    String content = null;
                    if (o instanceof EmojiBean) {
                        content = ((EmojiBean) o).emoji;
                    } else if (o instanceof EmoticonEntity) {
                        content = ((EmoticonEntity) o).getContent();
                    }

                    if (TextUtils.isEmpty(content)) {
                        return;
                    }
                    int index = ekBar.getEtChat().getSelectionStart();
                    Editable editable = ekBar.getEtChat().getText();
                    editable.insert(index, content);
                }
            }
        }
    };

    @Override
    public void onRefreshBy(boolean refresh) {
        list.clear();
        super.onRefreshBy(refresh);
    }

   public void  updateView(List<CommentListBean.DataBean.RecordsBean> mDatas, int position){
       if (EmptyUtils.isEmpty(mDatas)) {
           tvEmpty.setVisibility(View.VISIBLE);
       }else {
           tvEmpty.setVisibility(View.GONE);
       }
       ekBar.setNum(mDatas.size());
       records.remove(position);
       ekBar.setNum(records.size());
       if(iRecyclerView.mFooterViewContainer.getVisibility()==View.VISIBLE){
               if (records.size() > 3) {
                   mAdapter.replaceAll(records.subList(0, 3));
               } else {
                   mAdapter.replaceAll(records);
                   iRecyclerView.setFooterViewState(false);
               }

       }


   }

    @Override
    public void returnData(Result result) {
        if(result instanceof CommentListBean){
             records =  ((CommentListBean) result).getData().getRecords();
            ekBar.setNum(records.size());
            if(list.size()>0){
                tvEmpty.setVisibility(View.GONE);
                mAdapter.addAt(0,records.get(0));
                if (records.size() > 3&&iRecyclerView.mFooterViewContainer.getVisibility()==View.VISIBLE) {
                    mAdapter.replaceAll(records.subList(0, 3));
                    mZhuanLanSubscribeProfitUtil.bindFooterView(iRecyclerView, "查看全部",records,  mAdapter);
                } else {
                    mAdapter.replaceAll(records);
                }
                return;
            }
            if (EmptyUtils.isNotEmpty(result)) {
                if (records.size() > 3) {
                    list.addAll(records.subList(0, 3));
                } else {
                    list.addAll(records);
                }
            }
            if (EmptyUtils.isEmpty(list)) {
                tvEmpty.setVisibility(View.VISIBLE);
            }else {
                tvEmpty.setVisibility(View.GONE);
            }
                updateData(list);
            if (records.size() > 3) {
                mZhuanLanSubscribeProfitUtil.bindFooterView(iRecyclerView, "查看全部",records,  mAdapter);
            }
        }else if(result instanceof StringDataV2Bean){
            if(result.getResultMsg().equals("评论成功")){
                CommonUtils.hideKeyboard(getActivity());
                ekBar.reset();
                ekBar.getEtChat().setText("");
                Map<String, String> defMap = MapUtils.getDefMap(true);
                defMap.put("spaceId",spaceId);
                mPresenter.getData(ADMIN_COMMENT_PAGE,defMap, CommentListBean.class);
            }else {
                tvOther.setVisibility(View.GONE);
            }
        }else if(result instanceof SpaceDetailBean){
            dataBean =  ((SpaceDetailBean) result).getData();
            setData();
            newCacheUtil.saveSpaceDeatilBean(dataBean,spaceId);
        }
    }

    private void setData() {
        if(dataBean!=null){
            userId = dataBean.getUserId();
            gridView.setBalance(gold,userId,null);
            gridView.setSpaceId(spaceId);
            mZhuanLanSubscribeProfitUtil = new ZhuanLanSubscribeProfitUtil(getContext(),getActivity(),aliyunVodPlayer,headView);
            mZhuanLanSubscribeProfitUtil.setData(dataBean);
            commentId=userId;
            initData(dataBean);
            ekBar.initData(dataBean,mRxManager);
        }
    }

    private void getCommentList() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("spaceId",spaceId);
        mPresenter.getData(ADMIN_COMMENT_PAGE,defMap, CommentListBean.class);
    }

    public void setEkBar(String userName,String userId){
        ekBar.getEtChat().setText("@"+userName+" ");
        commentId=userId;
    }

    @Override
    public void OnFuncPop(int height) {
    }

    @Override
    public void OnFuncClose() {

    }

    @Override
    public void onDestroy() {
        if(aliyunVodPlayer!=null) {
            aliyunVodPlayer.release();
            aliyunVodPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        if(aliyunVodPlayer!=null){
            aliyunVodPlayer.stop();
        }
        super.onPause();
    }
}
