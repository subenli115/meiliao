package com.ziran.meiliao.ui.me.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.FileListBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.CustomEditText;
import com.ziran.meiliao.widget.ItemGroupView;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_TREPORT_ADD;

/**
 * 意见反馈
 * Created by Administrator on 2017/1/4.
 */

public class FeedbackActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result>, BGASortableNinePhotoLayout.Delegate {
    private static final int PRC_PHOTO_PICKER = 1;
    private static final int RC_PHOTO_PREVIEW = 2;
    private static final int RC_CHOOSE_PHOTO = 1;
    @Bind(R.id.et_content)
    CustomEditText et_content;
    private String title;
    @Bind(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;


    public static void startAction() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, FeedbackActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_feed_back;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }


    @Override
    public void initView() {
        mPhotosSnpl.setDelegate(this);
    }

    private void treport(ArrayList<String> list) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        if (list != null&&list.size()>0) {
            if(list.size()>1){
                String str = String.join(",", list);
                defMap.put("img", str);
            }else {
                defMap.put("img", list.get(0));
            }
        }
        defMap.put("dataType","2");
        defMap.put("reportUserId", MyAPP.getUserId());
        defMap.put("remarks",et_content.getContent());
        mPresenter.postData(ADMIN_TREPORT_ADD,defMap, StringDataV2Bean.class);
    }
    private void uploadFiles() {
        startProgressDialog("正在上传...");
        OkHttpClientManager.upLoadFiles(ApiKey.ADMIN_SYS_FILE_UPLOADS, MyAPP.getAccessToken(),mPhotosSnpl.getData(), new
                NewRequestCallBack<FileListBean>(FileListBean.class) {

                    @Override
                    public void onSuccess(FileListBean listData) {
                        ArrayList<String> imgs = new ArrayList<>();
                        for (int i = 0; i < listData.getData().size(); i++) {
                            imgs.add(listData.getData().get(i).getUrl());
                        }
                        treport(imgs);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                        stopProgressDialog();
                    }
                });
    }

    //点击监听
    @OnClick({R.id.tv_qd})
    public void onClick(View view) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_qd:
                if(et_content.getContent().equals("")){
                    ToastUitl.showShort("请输入内容");
                    return;
                }
                if (mPhotosSnpl.getData() != null&&mPhotosSnpl.getData().size()>0) {
                    uploadFiles();
                } else {
                    treport(null);
                }
            break;
        }
    }

    @Override
    public void returnData(Result result) {
        stopProgressDialog();
        if(result instanceof StringDataV2Bean){
            ToastUitl.showShort("反馈成功");
            finish();
        }

    }

    @Override
    public void returnAction(Result result) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO&&data!=null) {
            mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
        } else if (requestCode == RC_PHOTO_PREVIEW&&data!=null) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
        }

    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
    }
    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mPhotosSnpl.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);

    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {

    }
}