package com.ziran.meiliao.ui.priavteclasses.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.beecolud.BillUtils;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.CourseJoinDeatilBean;
import com.ziran.meiliao.ui.bean.CoursePayResult;
import com.ziran.meiliao.ui.priavteclasses.adapter.CouresSelectAdapter;
import com.ziran.meiliao.utils.MapUtils;
import com.zhy.autolayout.AutoLinearLayout;


import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.ACTIVITY_APPJOIN;
import static com.ziran.meiliao.constant.ApiKey.ACTIVITY_TOJOIN;


public class CourseEnrollmentActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result>, TextWatcher {

    @Bind(R.id.minus)
    TextView minus;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.add)
    TextView add;
    @Bind(R.id.et_result)
    EditText etResult;
    @Bind(R.id.all_main)
    AutoLinearLayout all_main;
    @Bind(R.id.tv_points)
    TextView tvPoints;
    @Bind(R.id.tv_activity_city)
    TextView tvCity;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    @Bind(R.id.box)
    CheckBox box;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_edit_userinfo_nick)
    EditText etName;
    private int nowText;
    private String courseId;
    private View contentView;
    private int score;
    private String name;
    private Double money;
    private Double moneyInt;
    private boolean mIsCheck=false;
    private int detailId;
    private int mScore;
    private String allPrice;
    private String billNo;

    @Override
    public int getLayoutId() {
        return R.layout.ac_activity_sign_up;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }
    @Override
    public void returnData(Result result) {
        if (result instanceof CoursePayResult) {
            CoursePayActivity.startAction(mContext,detailId+"",billNo,
                    allPrice,tvSave.getText().toString().substring(3));
            finish();
        }else {
            CourseJoinDeatilBean data=(CourseJoinDeatilBean)result;
            CourseJoinDeatilBean.DataBean  dataBean= data.getData();
            score = dataBean.getScore();
            tvPoints.setText("可用积分"+score+"");
            detailId=dataBean.getDetailList().get(0).getId();
            CouresSelectAdapter couresSelectAdapter = new CouresSelectAdapter(mContext,dataBean.getDetailList());
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            couresSelectAdapter.setItemClickListener(new CouresSelectAdapter.ItemClickListener() {
                @Override
                public void itemClick(int isSelect, int id) {
                    detailId =id;
                }
            });
            recyclerView.setAdapter(couresSelectAdapter);
        }
    }

    @Override
    public void returnAction(Result result) {

    }

    public static void startAction(Context context, String courseId, String name, double money) {
        Intent intent = new Intent();
        intent.putExtra("courseId",courseId+"");
        intent.putExtra("name",name+"");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("money",money);
        intent.setClass(context, CourseEnrollmentActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void initView() {
        super.initView();
        if(getIntent()!=null){
            courseId=getIntent().getStringExtra("courseId");
            name=getIntent().getStringExtra("name");
            money=getIntent().getDoubleExtra("money",0);
        }
         moneyInt =money;
        ntbTitle.setTitleWeizhi();
        ntbTitle.setTitleText("课程报名");
        tvPay.setText("去支付"+moneyInt+"");
        tvCity.setText(name);
        ntbTitle.setBackGroundColor1();
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        etResult.addTextChangedListener(this);
        nowText = Integer.parseInt(etResult.getText().toString());
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("courseId",courseId);
        mPresenter.postData(ACTIVITY_TOJOIN,defMap, CourseJoinDeatilBean.class);
        box.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                mIsCheck=isCheck;
                Settlement();
            }
        });
    }

    @OnClick({R.id.minus, R.id.add ,R.id.iv_rule,R.id.tv_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.minus:
                if(nowText>1){
                    nowText--;
                    etResult.setText(nowText+"");
                    Settlement();
                }
                break;
            case R.id.add:
                nowText++;
                etResult.setText(nowText+"");
                Settlement();
                break;
            case R.id.iv_rule:
                showPopWindow();
                break;
            case  R.id.tv_pay:
                String userPhone = etPhone.getText().toString();
                if(etPhone.getText().toString().length()<11){
                    ToastUitl.showShort("请输入正确手机号");
                    return;
                }
                String userName = etName.getText().toString();
                if(userName.length()==0){
                    ToastUitl.showShort("请输入正确姓名");
                    return;
                }
                    if(mIsCheck){
                        mScore=score;
                }else {
                        mScore=0;
                }
                 allPrice = tvPay.getText().toString().substring(3);
              billNo=   "android"+BillUtils.genBillNum();
                Map<String, String> defMap = MapUtils.getDefMap(true);
                defMap.put("courseId", courseId);
                defMap.put("detailId", detailId + "");
                defMap.put("billNo", billNo);
                defMap.put("name", userName);
                defMap.put("phone", userPhone);
                defMap.put("members", nowText+"");
                defMap.put("score", mScore + "");
                defMap.put("price", allPrice);
                tvPay.setEnabled(false);
                mPresenter.postData(ACTIVITY_APPJOIN, defMap, CoursePayResult.class);
                break;
        }
    }

    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(mContext).inflate(R.layout.item_course_point_rule, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(all_main, Gravity.CENTER, 0, 0);
        TextView quit = contentView.findViewById(R.id.tv_ok);
        setBackgroundAlpha(0.5f);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }

    private void Settlement() {
        if(mIsCheck){
            tvPoints.setText("已抵扣"+score+"");
            tvSave.setText("已省￥"+score+"");
            if(nowText>1){
                tvPay.setText("去支付"+(moneyInt*nowText-score-  200*nowText)+"");
                tvSave.setText("已省￥"+(score+200*nowText)+"");
            }else{
                tvPay.setText("去支付"+(moneyInt*nowText-score)+"");
                tvSave.setText("已省￥"+score+"");
            }
        }else {
            tvPoints.setText("可使用积分"+score);
            if(nowText>1){
                tvPay.setText("去支付"+(moneyInt*nowText-200*nowText)+"");
                tvSave.setText("已省￥"+(200*nowText)+"");
            }else{
                tvPay.setText("去支付"+moneyInt+"");
                tvSave.setText("已省￥"+0+"");
            }

        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        all_main.setAlpha(bgAlpha);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(etResult.getText().toString().equals("")||nowText==0){
            etResult.setText("1");
        }
        nowText =Integer.parseInt(etResult.getText().toString());
        Settlement();
    }
}
