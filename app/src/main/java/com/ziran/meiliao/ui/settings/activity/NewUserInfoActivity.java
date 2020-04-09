package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.FileSizeUtil;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.envet.OnLoadDataListener;
import com.ziran.meiliao.ui.bean.UpdateUserHeadBean;
import com.ziran.meiliao.ui.main.activity.LabelActivity;
import com.ziran.meiliao.ui.main.activity.MainActivity;
import com.ziran.meiliao.ui.settings.contract.UpdateUserInfoContract;
import com.ziran.meiliao.ui.settings.model.UpdateUserInfoModel;
import com.ziran.meiliao.ui.settings.presenter.UpdateUserInfoPresenter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.wevey.selector.dialog.DialogOnItemClickListener;
import com.wevey.selector.dialog.MDSelectionDialog;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.ui.settings.activity.UserInfoActivity.FROM_REGISTER;

public class NewUserInfoActivity extends BaseActivity<UpdateUserInfoPresenter, UpdateUserInfoModel> implements UpdateUserInfoContract.View,
        View.OnClickListener {


    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    private TabLayout tabLayout;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.et_NcName)
    EditText etNcName;
    @Bind(R.id.tv_permanentCity)
    EditText tvPermanentCity;
    @Bind(R.id.iv_user_avatar)
    ImageView iv_user_avatar;
    @Bind(R.id.tv_profession_right)
    EditText tvProfessionRight;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;

    private boolean hasChange;
    private String[] genderList;
    private int from;
    private TimeSelector timeSelector;

    /**
     * 入口
     */
    public static void startAction(Context mContext) {
        startAction(mContext, 1);
    }

    public static void startAction(Context mContext, int from) {
        Intent intent = new Intent(mContext, NewUserInfoActivity.class);
        intent.putExtra(AppConstant.KEY_EDIT_USERINFO_FROM, from);
        mContext.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.ac_member_improving_data;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    @Override
    public void initView() {
        ntb.setBackGroundColor(R.color.transparent);
        from = getIntentExtra(getIntent(), AppConstant.KEY_EDIT_USERINFO_FROM, FROM_REGISTER);
        if (from == FROM_REGISTER) {
            ntb.setRightTitle(getString(R.string.skip));
            ntb.setTvLeft(getString(R.string.save_title));
            //设置右侧点击监听
            ntb.setOnRightTextListener(this);
            //设置左侧点击监听
            ntb.setOnBackListener(this);
        } else {
            ntb.setTvLeftVisiable(true, true);
        }

        //设置用户数据
        MyAPP.loadUserInfo(new OnLoadDataListener<UserInfo>() {
            @Override
            public void loadSuccess(UserInfo userInfo) {
                if (FileUtil.fileIsExists(StringUtils.headImg())) {
                    ImageLoaderUtils.displayCircle(mContext, iv_user_avatar, StringUtils.headImg(),R.mipmap.ic_user_pic);
                } else {
                    ImageLoaderUtils.displayCircle(mContext, iv_user_avatar, userInfo.getHeadImg(),R.mipmap.ic_user_pic);
                }
                tvBirthday.setText(userInfo.getAge());
                editName.setText(userInfo.getRealName());
                etNcName.setText(userInfo.getNickName());
                tvPermanentCity.setText(userInfo.getCity());
                tvSex.setText(userInfo.getSex());
                if(userInfo.getSex().equals("")){
                    tvSex.setText("女");
                }
                tvProfessionRight.setText(userInfo.getCareer());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
            case R.id.tv_right:
                //调用更新个人资料
                if(from == FROM_REGISTER){
                    LabelActivity.startAction(this);
                    finish();
                }else{
                    MainActivity.startAction(this);
                    finish();
                }
                break;
        }
    }


    @OnClick({R.id.iv_user_avatar, R.id.tv_sex,R.id.tv_update,R.id.tv_birthday})
    public void onClickAll(View view) {
        //网络判断
        if (!CheckUtil.check(NewUserInfoActivity.this, view)) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_user_avatar:
                //跳转到选择用户头像界面
                ImgSelActivity.startActivity(this, ImgSelConfig.DEFAULT_SIGN_HEAD(StringUtils.newHeadImgPath(MyAPP.getUserInfo()
                        .getHeadImgVersion() + 1)), ImgSelConfig.RequestCode);
                break;
            case R.id.tv_sex:
                //性别更新
                editGender();
                break;
            case R.id.tv_birthday:
                //生日
                // 更新
                showPop();
                break;
            case R.id.tv_update:
                String ncName = etNcName.getText().toString();
                String city = tvPermanentCity.getText().toString();
                String job = tvProfessionRight.getText().toString();
                String name = editName.getText().toString();
                String birth = tvBirthday.getText().toString();

                if(ncName.equals("")){
                    ToastUitl.showShort("昵称不能为空");
                    return;
                }else if(city.equals("")){
                     ToastUitl.showShort("城市不能为空");
                    return;
                }else if(job.equals("")){
                    ToastUitl.showShort("职业不能为空");
                    return;
                }else if(name.equals("")){
                    ToastUitl.showShort("姓名不能为空");
                    return;
                }else if(birth.equals("")){
                    ToastUitl.showShort("生日不能为空");
                    return;
                }
                Map<String, String> maps = MapUtils.getUpdateUserInfo(ncName,tvSex.getText().toString(),name,job,birth,city
                        );
                mPresenter.updateUserInfo(maps);
                break;

        }
    }

    private void showPop() {
         timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
              time = time.replaceAll("-","\\.");
               tvBirthday.setText(time.substring(0,10));
            }
        }, "1960-01-01 00:00", "2005-12-31 23:59");
        timeSelector.setMode(TimeSelector.MODE.YMD);//只显示 年月日
        timeSelector.show();


    }

    /**
     * 性别选择对话框
     */
    private void editGender() {
        if (genderList == null) genderList = getResources().getStringArray(R.array.gender_list);
        MDSelectionDialog dialog = new MDSelectionDialog.Builder(this).setOnItemListener(new DialogOnItemClickListener() {
            @Override
            public void onItemClick(Button button, int position) {
                String sex = genderList[position];
                if (!tvSex.getText().toString().equals(sex)) {
                    tvSex.setText(sex);
                    MyAPP.getUserInfo().setSex(sex);
                }
            }
        }).build();
        dialog.setDataList(Arrays.asList(genderList));
        dialog.show();
    }

    /**
     * 头像选择返回结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            final ArrayList<String> imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (imgPaths != null && imgPaths.size() > 0) {
                startProgressDialog("正在上传头像...");
                updateUserHead(imgPaths);
            }
        }
    }
    private void updateUserHead(ArrayList<String> imgPaths) {
        final String path = imgPaths.get(0);
        HandlerUtil.runTask(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String autoFileOrFilesSize = FileSizeUtil.getAutoFileOrFilesSize(path);
                        if ("0.00 B".equals(autoFileOrFilesSize)) {
                            Thread.sleep(60);
                        } else {
                            mPresenter.updateUserHead(FileUtil.str2File(path), MapUtils.getDefMap(true));
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void returnUserHead(UpdateUserHeadBean result) {
        SPUtils.setInt(StringUtils.getHeadVersionKey(), result.getData().getHeadImgVersion());
        MyAPP.getUserInfo().setHeadImgVersion(result.getData().getHeadImgVersion());
        ToastUitl.showShort(StringUtils.format(getString(R.string.image_toast), result.getResultMsg()));
        if (TextUtils.isEmpty(MyAPP.getUserInfo().getHeadImg())) {
            //将第一次剪切的图片复制到/头像目录下
            MyAPP.getUserInfo().setHeadImg(result.getData().getPath());

        }
        ImageLoaderUtils.displayCircle(mContext, iv_user_avatar, StringUtils.headImg(),R.mipmap.ic_user_pic);
        mRxManager.post(AppConstant.RXTag.UPDATE_USER, "head");
        hasChange = true;
    }

    @Override
    public void returnUserInfo(Result result) {
        hasChange = true;
        MyAPP.getUserInfo().setNickName(etNcName.getText().toString());
        MyAPP.getUserInfo().setCity( tvPermanentCity.getText().toString());
        MyAPP.getUserInfo().setCareer(tvProfessionRight.getText().toString());
        MyAPP.getUserInfo().setAge(tvBirthday.getText().toString());
        MyAPP.getUserInfo().setSex(tvSex.getText().toString());
        Intent intent = getIntent();
        setResult(10,intent);
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (hasChange) {
            DbCore.getDaoSession().getUserInfoDao().update(MyAPP.getUserInfo());
        }
    }
}
