package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.citypicker.citylist.widget.ClearEditText;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.MyFollowBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_USER_SEARCH;


/**
 * Created by Administrator on 2019/1/31.
 */
public class SearchFragment extends CommonHttpFragment<CommonPresenter, CommonModel> implements CommonContract.View<UserBean> {
    @Bind(R.id.et_seach_content)
    ClearEditText etSeachContent;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;
    @Bind(R.id.tv_sign)
    TextView tvSign;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.iv_real_name)
    ImageView ivReal;
    @Bind(R.id.arl)
    AutoRelativeLayout arl;
    @Bind(R.id.bg)
    AutoLinearLayout bg;
    private UserBean.DataBean mResult;
    private String mPosition;
    private View contentView1;
    private boolean isCheck;


    @Override
    protected void initView() {
//        loadData();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadData(){
        Map<String, String> map = MapUtils.getDefMap(true);
        map.put("id", MyAPP.getUserId());
        map.put("nickname",""+etSeachContent.getText().toString());
        mPresenter.getData(ADMIN_USER_SEARCH, map, UserBean.class);
    }

    //点击监听
    @OnClick({R.id.tv_seach,R.id.iv_back,R.id.tv_right,R.id.arl})
    public void onClick(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_seach:
                KeyBordUtil.hideSoftKeyboard(etSeachContent);
                loadData();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                if(!mPosition.equals("1")){
                    showPopAgainWindow();
                }
                break;
            case R.id.arl:
                UserHomeActivity.startAction(mResult.getId());

                break;
        }
    }
    private void put( ) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        if(mPosition.equals("0")){
            defMap.put("userId", MyAPP.getUserId());
            defMap.put("followUserId",mResult.getId());
            defMap.put("isBlacklist", "0");
        }else if(mPosition.equals("2")){
            defMap.put("userId",mResult.getId());
            defMap.put("followUserId",MyAPP.getUserId());
            if(isCheck){
                defMap.put("isBlacklist", "1");
            }else {
                defMap.put("isBlacklist", "0");
            }
        }
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USERFOLLOW_DEL, defMap, new NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
            @Override
            protected void onSuccess(StringDataV2Bean result) {
                ToastUitl.show(result.getResultMsg(),0);
                arl.setVisibility(View.GONE);
            }

            @Override
            public void onError(String msg, int code) {
                super.onError(msg, code);
                ToastUitl.showShort(msg);
            }
        });
    }


    @Override
    public void returnData(UserBean result) {
        mResult=result.getData();
        if(mResult==null){
            arl.setVisibility(View.GONE);
            loadedTip.setEmptyMsg("什么都没有~",R.mipmap.load_empty_bg);
        }else {
            loadedTip.setVisibility(View.GONE);
            initData();
        }
    }
    private void showPopAgainWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView1 = LayoutInflater.from(getContext()).inflate(R.layout.pop_again_apply, null);
        contentView1.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView1,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        //设置成不被键盘挡住
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(bg, Gravity.CENTER, 0, 0);
        TextView tvqd = contentView1.findViewById(R.id.tv_qd1);
        TextView tvqx = contentView1.findViewById(R.id.tv_qx1);
        TextView tvTitle = contentView1.findViewById(R.id.tv_title);
        TextView etReason = contentView1.findViewById(R.id.et_reason);
        CheckBox checkBox = contentView1.findViewById(R.id.checkBox);
        LinearLayout linearLayout = contentView1.findViewById(R.id.ll_check);
        if(mPosition.equals("0")){
            tvTitle.setText("取消关注");
            etReason.setText("确定取消对"+mResult.getNickname()+"的关注吗");
        }else if(mPosition.equals("2")){
            tvTitle.setText("移除粉丝");
            etReason.setText("确定要移除"+mResult.getNickname()+"吗");
            linearLayout.setVisibility(View.VISIBLE);
            checkBox.setOnCheckedChangeListener((compoundButton, b) -> isCheck=b);
        }
        tvqd.setOnClickListener(view -> {
            put();
            popupWindow.dismiss();
        });
        tvqx.setOnClickListener(view -> popupWindow.dismiss());
    }

    private void initData() {
        arl.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(mResult.getAvatar()).transform(new GlideRoundTransform(getContext())).into(ivHead);
        tvSign.setText(mResult.getIntroduce());
        tvName.setText(mResult.getNickname());
         mPosition = mResult.getInvitationCode();

        if(mPosition.equals("0")){
            tvRight.setText("取消关注");
        }else if(mPosition.equals("1")){
            tvRight.setBackgroundResource(R.drawable.normal_bg_bule_tran);
            tvRight.setText("互相关注");
            tvRight.setTextColor(getResources().getColor(R.color.textColor_main_bule));
        }else {
            tvRight.setText("移除粉丝");
        }


    }
}