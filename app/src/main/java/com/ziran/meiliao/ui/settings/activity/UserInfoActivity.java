package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
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
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.widget.ItemGroupView;
import com.wevey.selector.dialog.DialogOnItemClickListener;
import com.wevey.selector.dialog.MDSelectionDialog;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


public class UserInfoActivity extends BaseActivity<UpdateUserInfoPresenter, UpdateUserInfoModel> implements UpdateUserInfoContract.View,
        View.OnClickListener {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    //头像Item
    @Bind(R.id.itemView_avatar)
    ItemGroupView mItemGroupViewAvatar;
    //昵称Item
    @Bind(R.id.itemView_name)
    ItemGroupView mItemGroupViewName;
    //账号Item
    @Bind(R.id.itemView_acc)
    ItemGroupView mItemGroupViewAcc;
    //性别Item
    @Bind(R.id.itemView_gender)
    ItemGroupView mItemGroupViewGender;
    //邮箱Item
    @Bind(R.id.itemView_email)
    ItemGroupView mItemGroupViewEmail;
    private boolean hasChange;
    private String[] genderList;
    //从哪个界面跳转过来
    private int from;
    public static final int FROM_REGISTER = 2;
    public static final int FROM_MAIN_ME = 1;

    /**
     * 入口
     */
    public static void startAction(Context mContext) {
        startAction(mContext, 1);
    }

    public static void startAction(Context mContext, int from) {
        Intent intent = new Intent(mContext, UserInfoActivity.class);
        intent.putExtra(AppConstant.KEY_EDIT_USERINFO_FROM, from);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    //设置View与Model
    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    //初始化控件内容
    @Override
    public void initView() {
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
        ntb.setTitleText(getString(R.string.user_info));

        //设置用户数据
        MyAPP.loadUserInfo(new OnLoadDataListener<UserInfo>() {
            @Override
            public void loadSuccess(UserInfo userInfo) {
                if (FileUtil.fileIsExists(StringUtils.headImg())) {
                    mItemGroupViewAvatar.setImagePath(StringUtils.headImg(), R.mipmap.ic_user_pic);
                } else {
                    mItemGroupViewAvatar.setImagePath(userInfo.getHeadImg(), R.mipmap.ic_user_pic);
                }
                mItemGroupViewName.setRigthText(userInfo.getNickName());
                mItemGroupViewEmail.setRigthText(userInfo.getEmail());
                mItemGroupViewGender.setRigthText(userInfo.getSex());
                if(userInfo.getSex().equals("")){
                    mItemGroupViewGender.setRigthText("女");
                }
                mItemGroupViewAcc.setRigthText(userInfo.getPhone());
            }
        });
        //订阅从修改用户资料返回的数据
        mRxManager.on(AppConstant.RXTag.EDIT_USER_INFO, new Action1<Integer>() {
            @Override
            public void call(Integer type) {
                if (type == EditUserInfoActivity.TYPE_TEXT) {  //返回的是用户名
                    mItemGroupViewName.setRigthText(MyAPP.getUserInfo().getNickName());
                } else if (type == EditUserInfoActivity.TYPE_EMAIL) {  //返回的是邮箱
                    mItemGroupViewEmail.setRigthText(MyAPP.getUserInfo().getEmail());
                }
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

    @OnClick({R.id.itemView_avatar, R.id.itemView_name, R.id.itemView_gender, R.id.itemView_email})
    public void onClickAll(View view) {
        //网络判断
        if (!CheckUtil.check(UserInfoActivity.this, view)) {
            return;
        }
        switch (view.getId()) {
            case R.id.itemView_avatar:
                //跳转到选择用户头像界面
                ImgSelActivity.startActivity(this, ImgSelConfig.DEFAULT_SIGN_HEAD(StringUtils.newHeadImgPath(MyAPP.getUserInfo()
                        .getHeadImgVersion() + 1)), ImgSelConfig.RequestCode);
                break;
            case R.id.itemView_name:
                //跳转到编辑用户资料界面更新昵称
                EditUserInfoActivity.startAction(this, EditUserInfoActivity.TYPE_TEXT, mItemGroupViewName.getRightText());
                break;
            case R.id.itemView_gender:
                //性别更新
                editGender();
                break;
            case R.id.itemView_email:
                //编辑用户信息更新邮箱
                EditUserInfoActivity.startAction(this, EditUserInfoActivity.TYPE_EMAIL, mItemGroupViewEmail.getRightText());
                break;
        }
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
                if (!mItemGroupViewGender.getRightText().equals(sex)) {
                    mItemGroupViewGender.setRigthText(sex);
//                    Map<String, String> maps = MapUtils.getUpdateUserInfo(null, sex, null);
//                    mPresenter.updateUserInfo(maps);
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
        mItemGroupViewAvatar.setImagePath(StringUtils.headImg());
        mRxManager.post(AppConstant.RXTag.UPDATE_USER, "head");
        hasChange = true;
    }

    @Override
    public void returnUserInfo(Result result) {
        hasChange = true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (hasChange) {
            DbCore.getDaoSession().getUserInfoDao().update(MyAPP.getUserInfo());
        }
    }

}
