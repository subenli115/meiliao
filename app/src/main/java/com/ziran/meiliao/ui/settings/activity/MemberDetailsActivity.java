package com.ziran.meiliao.ui.settings.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ArrayUtils;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.ExchangeBean;
import com.ziran.meiliao.ui.bean.IntegralDetailBean;
import com.ziran.meiliao.ui.bean.MemberCenterBean;
import com.ziran.meiliao.ui.bean.MemberExchangeBean;
import com.ziran.meiliao.ui.priavteclasses.activity.PrivateCourseActivity;
import com.ziran.meiliao.ui.settings.adapter.PointsExchangeAdapter;
import com.ziran.meiliao.ui.settings.adapter.PointsGetAdapter;
import com.ziran.meiliao.ui.settings.contract.MemberDetailsContract;
import com.ziran.meiliao.ui.settings.model.MemberModel;
import com.ziran.meiliao.ui.settings.presenter.MemberDetailsPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoFrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.ui.settings.activity.UserInfoActivity.FROM_MAIN_ME;

/**
 * 积分系统
 */

public class MemberDetailsActivity extends BaseActivity <MemberDetailsPresenter, MemberModel> implements MemberDetailsContract.View{


            @Bind(R.id.ntb)
            public NormalTitleBar ntb;
            @Bind(R.id.iv_me_main_avatar)
            public ImageView ivMe_main_avatar;
            @Bind(R.id.tv_dhjf)
            public TextView tvDhjf;
            @Bind(R.id.tv_zqjf)
            public TextView tvZqjf;
            @Bind(R.id.iv_points)
            public ImageView ivPoints;
            @Bind(R.id.iv_exchange)
            public ImageView ivExchange;
            @Bind(R.id.tv_hyqy)
            public TextView tvHyqy;
            @Bind(R.id.tv_progress_first)
            public TextView tvFirst;
            @Bind(R.id.tv_member_level)
            public TextView tvMemberLevel;
            @Bind(R.id.recyclerView)
            public RecyclerView recyclerView;
            @Bind(R.id.tv_jfmx)
            public TextView tvJfmx;
            @Bind(R.id.tv_progress_second)
            public TextView tvProgressSecond;

            @Bind(R.id.tv_integral)
            public TextView tvIntegral;
            @Bind(R.id.progressBarHorizontal)
            public ProgressBar progressBarHorizontal;

            @Bind(R.id.tv_user_name)
            TextView tvMain_me_new_username;//
            @Bind(R.id.iv_level_bg)
            public ImageView ivLevelBg;
            @Bind(R.id.iv_small_bg)
            public ImageView ivSmallBg;
            private MemberCenterBean.DataBean mResult;
            private int type;
             private String[] mTitles;
             private PointsGetAdapter mAdapter;
            private String[] mDetails;
            private String[] mButtons;
            private int[] mipList={R.mipmap.member_buy_course,R.mipmap.member_invite_partners,R.mipmap.member_join,R.mipmap.member_recharge,R.mipmap.member_improving};
             private List<MemberExchangeBean.DataBean.GoodsListBean> mGoodsList;
            private PointsExchangeAdapter mAdapterTwo;
           private int mPostion;
            private ArrayList<Boolean> booleans;
            private AutoFrameLayout all;

    @Override
            public int getLayoutId() {
                return R.layout.ac_member_integral;
            }

            @Override
            public void initPresenter() {
                mPresenter.setVM(this, mModel);
            }

            @Override
            public void initView() {
                ntb.setVerLineVisiable(false);
                ntb.setNewTitleText("会员中心");
                mTitles = ArrayUtils.getArray(this, R.array.get_points);
                mDetails = ArrayUtils.getArray(this, R.array.points_detail);
                mButtons = ArrayUtils.getArray(this, R.array.points_button);
                ImageLoaderUtils.displayCircle(getBaseContext(), ivMe_main_avatar, StringUtils.headImg(), R.mipmap.ic_user_pic);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                 booleans = new ArrayList<>();
                for(int i=0;i<mTitles.length;i++){
                    booleans.add(false);
                }
                mAdapter = new PointsGetAdapter(mTitles,mDetails,mipList,mButtons,mContext,booleans);
                mAdapter.setItemClickListener(new PointsGetAdapter.OnItemClickListener() {
                    @Override
                    public void itemClick(int position) {
                        switch (position){
                            case 0:
                                //购买课程
                                PrivateCourseActivity.startAction(mContext,"2");
                                break;
                            case 1:

                                //金币充值
                                RechargeActivity.startAction(mContext, "");
                                break;
                            case 2:
                                //邀请新伙伴
                                InviteFriendsActivity.startAction(mContext);
                                break;
                            case 3:
                                //邀请购课
                                PrivateCourseActivity.startAction(mContext,"0");
                                break;
                            case 4:
                                //完善个人资料
                                Intent intent = new Intent();
                                intent.setClass(mContext, NewUserInfoActivity.class);
                                intent.putExtra(AppConstant.KEY_EDIT_USERINFO_FROM, FROM_MAIN_ME);
                                startActivityForResult(intent,10);
                                break;
                        }
                    }
                });
                recyclerView.setAdapter(mAdapter);
            }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10&&resultCode==10){
            booleans.set(booleans.size()-1,true);
        mAdapter.updateData(booleans,mContext);
        }
    }
    private void updateData() {
        // 1.首先先看本地有没有缓存
        // 2.有缓存,直接加载缓存
        mPresenter.getMemberCenterData(MapUtils.getDefMap(true), getBaseContext());
        mPresenter.getMemberGoodsData(MapUtils.getDefMap(true), getBaseContext());

//        if (!TextUtils.isEmpty(cache)&&) {
//            // 有缓存
//            System.out.println("发现缓存....");
//            uiUpdate(new Gson().fromJson(cache, MemberDetails.class).getData());
//
//        }else{
////            mPresenter.getMemberData(MapUtils.getDefMap(true), getBaseContext());
//
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
        if (tvMain_me_new_username != null && MyAPP.getUserInfo() != null) {
            tvMain_me_new_username.setText(MyAPP.getUserInfo().getNickName());
        }
    }

    @OnClick({R.id.tv_hyqy,R.id.all_integral_record,R.id.all_get_points,R.id.all_exchange_points})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hyqy:
            MemberRuleActivity.startAction(getBaseContext(),mResult.getMemberLink());
                break;

            case  R.id.all_integral_record:
                //积分明细
                 IntegralDetailsActivity.startAction(mContext);
                break;
            case  R.id.all_get_points:
                ivPoints.setImageResource(R.mipmap.member_points_select);
                tvZqjf.setTextColor(Color.parseColor("#DCC38D"));
                ivExchange.setImageResource(R.mipmap.member_exchage);
                tvDhjf.setTextColor(Color.parseColor("#AAAAAA"));
                type=0;
                recyclerView.setAdapter(mAdapter);
                break;

            case  R.id.all_exchange_points:
                ivExchange.setImageResource(R.mipmap.member_exchage_select);
                tvDhjf.setTextColor(Color.parseColor("#DCC38D"));
                ivPoints.setImageResource(R.mipmap.member_points);
                tvZqjf.setTextColor(Color.parseColor("#AAAAAA"));
                type=1;
                recyclerView.setAdapter(mAdapterTwo);
                all = (AutoFrameLayout) LayoutInflater.from(this).inflate(R.layout.item_member_exchange_head, null);
                TextView tvExchange = all.findViewById(R.id.tv_exchange);
                tvExchange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showCoverDialog(1,"",-1,-1);
                    }
                });
//                mAdapterTwo.addHeaderView(all);
                break;
        }
    }

    private void buyByPoints(int goodsId, int type) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("goodsId",goodsId+"");
        defMap.put("type",type+"");
        mPresenter.postBuyByScore(defMap, getBaseContext());
    }


    @Override
    public void showGoodsData(MemberExchangeBean.DataBean result) {
         mGoodsList = result.getGoodsList();
         if(mAdapterTwo!=null){
             mAdapterTwo.update(mGoodsList,mContext);
         }else{
             mAdapterTwo = new PointsExchangeAdapter(mGoodsList,mContext);
             mAdapterTwo.notifyDataSetChanged();
             mAdapterTwo.setItemClickListener(new PointsExchangeAdapter.OnItemClickListener() {
                 @Override
                 public void itemClick(int id, int type, int postion, int point, String title) {
                     showCoverDialog(point,title,id,type);
                     mPostion=postion;
                 }
             });
         }
    }
    private void showCoverDialog(int point, String title, final int id,final int type1){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        if(mPostion==0){
            builder.setMessage("是否要用“30积分”兑换“300金币”");
        }else {
            builder.setMessage("是否要用“"+point+"积分”"+"兑换"+"“"+title+"”");
        }
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(type1!=-1){
                    buyByPoints(id, type1);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }


    @Override
    public void showMemberCenterData(MemberCenterBean.DataBean result) {
                 if(result!=null){
                     mResult=result;
                     Glide.with(mContext).load(result.getLevelPicture()).transform(new GlideRoundTransform(mContext,5)).into(ivLevelBg);
                     ImageLoaderUtils.display(getBaseContext(), ivSmallBg, result.getSmallPic(), R.mipmap.member_star);
                     switch(result.getLevel()){
                         case 1:
                             tvMemberLevel.setText("普通会员");
                             break;
                         case 2:
                             tvMemberLevel.setText("白银会员");
                             break;
                         case 3:
                             tvMemberLevel.setText("黄金会员");
                             break;
                         case 4:
                             tvMemberLevel.setText("钻石会员");
                             break;
                         case 5:
                             tvMemberLevel.setText("至尊会员");
                             tvMain_me_new_username.setTextColor(Color.parseColor("#745536"));
                             tvIntegral.setTextColor(Color.parseColor("#9D8066"));
                             tvJfmx.setTextColor(Color.parseColor("#A78F79"));
                             tvProgressSecond.setTextColor(Color.parseColor("#9A734C"));
                             tvFirst.setTextColor(Color.parseColor("#9A734C"));
                             break;
                     }
                         if(result.getCurrentScore()==0||result.getMaxScore().equals("")){
                             progressBarHorizontal.setProgress(0);
                         }else{
                             int progress= result.getCurrentScore() / Integer.parseInt(result.getMaxScore());
                             progressBarHorizontal.setProgress(progress*100);
                         }

                         tvFirst.setText(result.getCurrentScore()+"");
                         tvIntegral.setText(result.getCurrentScore()+"");
                         booleans.set(booleans.size()-1,result.isInfoComplete());
                         mAdapter.updateData(booleans,mContext);

                         tvProgressSecond.setText(result.getMaxScore());
                 }
    }

    @Override
    public void showResult(ExchangeBean.DataBean result) {
        ToastUitl.showShort("兑换成功");
        mPresenter.getMemberCenterData(MapUtils.getDefMap(true), getBaseContext());
        mPresenter.getMemberGoodsData(MapUtils.getDefMap(true), getBaseContext());
    }

    @Override
    public void showScore(IntegralDetailBean.DataBean result) {

    }

}
