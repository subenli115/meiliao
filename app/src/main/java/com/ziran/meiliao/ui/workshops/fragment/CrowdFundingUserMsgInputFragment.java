package com.ziran.meiliao.ui.workshops.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.imagePager.Constant;
import com.ziran.meiliao.common.imagePager.ImagePreviewActivity;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.MyCallBack;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.workshops.adapter.ImageAdapter;
import com.ziran.meiliao.ui.workshops.contract.CrowdFundingUserMsgInputContract;
import com.ziran.meiliao.ui.workshops.presenter.CrowdFundingUserMsgInputPresenter;
import com.ziran.meiliao.ui.workshops.widget.TitleGridView;
import com.ziran.meiliao.utils.LuBanUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BaseItemView;
import com.ziran.meiliao.widget.SmoothCheckBox;
import com.ziran.meiliao.widget.pupop.ValidatePhoneDialog;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我要众筹个人信息填写界面
 * Created by Administrator on 2017/1/7.
 */

public class CrowdFundingUserMsgInputFragment extends CommonHttpFragment<CrowdFundingUserMsgInputPresenter, CommonModel> implements
        CrowdFundingUserMsgInputContract.View {

    @Bind(R.id.et_crowd_funding_user_msg_input_profile)
    EditText etCrowdFundingUserMsgInputProfile;
    @Bind(R.id.checkbox)
    SmoothCheckBox checkbox;
    @Bind(R.id.tv_crowd_funding_user_msg_input_submit)
    FilterTextView tvCrowdFundingUserMsgInputSubmit;
    @Bind(R.id.biv_name)
    BaseItemView bivName;
    @Bind(R.id.biv_id)
    BaseItemView bivId;
    @Bind(R.id.titleGridView)
    TitleGridView titleGridView;
    private ImageAdapter mImageAdapter;


    @Override
    protected void initOther() {
        mImageAdapter = new ImageAdapter(getContext());
        titleGridView.setAdapter(mImageAdapter);
        titleGridView.setGridViewPadding(12);
        titleGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mImageAdapter.isAdd(position)) {
                    List<String> selectAll = mImageAdapter.getAll();
                    ImgSelConfig.SELECT_DATA = new ArrayList<>(selectAll);
                    ImgSelActivity.startActivity(getActivity(), ImgSelConfig.MULTI_SELECT(6 - selectAll.size()), ImgSelConfig.RequestCode);
                } else {
                    ArrayList<String> strings = new ArrayList<>(mImageAdapter.getAll());
                    ImagePreviewActivity.startImagePagerActivity(getActivity(), strings, position);
                }
            }
        });
        checkbox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                tvCrowdFundingUserMsgInputSubmit.setEnabled(isChecked);
            }
        });
        checkbox.setChecked(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mImageAdapter != null) {
            mImageAdapter.refreshData(Constant.deleteUrl, titleGridView);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_crowd_funding_user_msg_input;
    }


    @OnClick(R.id.tv_crowd_funding_user_msg_input_submit)
    public void onViewClicked(View view) {
        if (!bivName.checkNull("姓名不能为空")) return;
        if (!bivId.checkNull("身份证不能为空") )return;
        if (!RegexUtils.isIDCard(bivId.getContent())){
            showShortToast("身份证格式不对,请重新输入");
            return;
        }
        final ValidatePhoneDialog validatePhoneDialog = new ValidatePhoneDialog(getContext());
        validatePhoneDialog.setCallBack(new ViewUtil.BaseCallBack() {
            @Override
            public void call() {
                Map<String, String> stringMap = MapUtils.getDefMap(true);
                stringMap.put("name", bivName.getContent());
                stringMap.put("idcard", bivId.getContent());
                stringMap.put("intro", etCrowdFundingUserMsgInputProfile.getText().toString());
                stringMap.put("phone", validatePhoneDialog.getPhone());
                stringMap.put("picture", "");
                mPresenter.postData(ApiKey.CROWN_FUND_ADD_CROWD_FUND_USER, stringMap);
            }
        });
        validatePhoneDialog.show();
//        if (EmptyUtils.isNotEmpty(mImageAdapter.getAll())) {
//
//        } else {
//            showShortToast("请上传您的资格证书");
//        }

    }


    public void setImageResult(ArrayList<String> imgPaths) {
        if (mImageAdapter != null && EmptyUtils.isNotEmpty(imgPaths)) {
            mImageAdapter.addAllNew(imgPaths, true);
            titleGridView.setAdapter(mImageAdapter);
        }
    }

    private LuBanUtil mLuBanUtil;

    @Override
    public void showData(Result result) {
        startProgressDialog("正在上传资料");
        if (EmptyUtils.isNotEmpty(mImageAdapter.getAll())){
            mLuBanUtil = new LuBanUtil(getContext(), mImageAdapter.getAll(), new MyCallBack() {
                @Override
                public void call() {
                    Map<String, String> onlyCan = MapUtils.getOnlyCan("tagId", "1");
                    mPresenter.postImg(ApiKey.UPLOAD_IMGS_UPLOAD_IMGS, onlyCan, mLuBanUtil.getResult());
                }
            });
            mLuBanUtil.startCompress();
        }else{
            stopProgressDialog();
            mRxManager.post(AppConstant.RXTag.SUBMIT_USER_MSG, true);
        }

    }

    @Override
    public void showPostImgResult(Result result) {
        stopProgressDialog();
        mRxManager.post(AppConstant.RXTag.SUBMIT_USER_MSG, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Constant.deleteUrl.clear();
    }
}
