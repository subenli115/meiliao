package com.ziran.meiliao.im.activity;

import android.Manifest;
import android.content.Intent;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.wgd.gdcp.gdcplibrary.GDCompressA;
import com.wgd.gdcp.gdcplibrary.GDCompressC;
import com.wgd.gdcp.gdcplibrary.GDCompressImageListener;
import com.wgd.gdcp.gdcplibrary.GDCompressImageListenerA;
import com.wgd.gdcp.gdcplibrary.GDConfig;
import com.wgd.gdcp.gdcplibrary.GDImageBean;
import com.ziran.meiliao.R;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.ThreadUtil;
import com.ziran.meiliao.ui.bean.FileListBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.CustomClickListener;
import com.ziran.meiliao.widget.CustomEditText;
import com.ziran.meiliao.widget.ItemGroupView;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;
import com.ziran.meiliao.widget.pupop.PrivatePopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by ${chenyn} on 2017/5/7.
 */

public class ReleaseWechatActivity extends BaseActivity implements BGASortableNinePhotoLayout.Delegate {
    private static final int RC_PHOTO_PREVIEW = 2;
    private static final int REQUEST_CODE_A = 3;
    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int PRC_PHOTO_PICKER = 1;

    @Bind(R.id.et_content)
    CustomEditText et_content;
    @Bind(R.id.igv_private)
    ItemGroupView igvPrivate;
    @Bind(R.id.igv_address)
    ItemGroupView igvAddress;
    @Bind(R.id.tv_fb)
    TextView tvFb;
    @Bind(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;
    private static final int REQUEST_IMAGE = 2;
    List<String> mSelect;
    private int status;
    private String longitude;
    private String latitude;
    private String address;
    private ArrayList<String> paths;
    private ArrayList<String> GDpaths;
    private boolean mSingleChoiceCb = false;
    private LocationClient mLocationClient;
//    private SelectNineGridAdapter adapter;

    public static void startAction(int code) {
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent = new Intent(activity, ReleaseWechatActivity.class);
        activity.startActivityForResult(intent, code);
    }


    @Override
    public int getLayoutId() {
        return R.layout.ac_release_wechat;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mSelect = new ArrayList<>();
        // 设置拖拽排序控件的代理
        mPhotosSnpl.setDelegate(this);
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
        tvFb.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                //发布
                if (paths != null) {
                    uploadFiles();
                } else {
                    spaceAdd(null);
                }
            }

            @Override
            protected void onFastClick() {
                //连续点击
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO&&data!=null) {
            mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
            paths = BGAPhotoPickerPreviewActivity.getSelectedPhotos(data);
        } else if (requestCode == RC_PHOTO_PREVIEW&&data!=null) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
            paths = BGAPhotoPickerPreviewActivity.getSelectedPhotos(data);
        }
        if (requestCode == REQUEST_CODE_A) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    address = data.getStringExtra("address");
                    longitude = data.getStringExtra("longitude");
                    latitude = data.getStringExtra("latitude");
                    igvAddress.setRigthText(address);
                }
            }

        }
    }


    //点击监听
    @OnClick({R.id.tv_back, R.id.igv_address, R.id.igv_private})
    public void onClick(View view) {
//        if (!MyAPP.isLogin(mContext)) { //如果没有登录则跳转到登录界面
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.igv_private:
                PrivatePopupWindow pop = new PrivatePopupWindow(mContext, igvPrivate);
                PopupWindowUtil.show(this, pop);
                break;
            case R.id.igv_address:
                Intent intent = new Intent(this, BaiduMapActivity.class);
                startActivityForResult(intent, REQUEST_CODE_A);

                break;
        }
    }

    private void uploadFiles() {
        Looper.prepare();
        startProgressDialog("正在发布...");
        Looper.loop();
        OkHttpClientManager.upLoadFiles(ApiKey.ADMIN_SYS_FILE_UPLOADS, MyAPP.getAccessToken(),paths, new
                NewRequestCallBack<FileListBean>(FileListBean.class) {

                    @Override
                    public void onSuccess(FileListBean listData) {
                        ArrayList<String> imgs = new ArrayList<>();
                        for (int i = 0; i < listData.getData().size(); i++) {
                            imgs.add(listData.getData().get(i).getUrl());
                        }
                        spaceAdd(imgs);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                        stopProgressDialog();
                    }
                });
    }

    private void spaceAdd(ArrayList<String> list) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", MyAPP.getUserId());
        if (list != null) {
            String str = String.join(",", list);
            defMap.put("images", str);
        }else {
            if(et_content.getContent().equals("")){
                ToastUitl.showShort("请输入内容");
                return;
            }
        }
        if (igvPrivate.getRightText().equals("仅自己可见")) {
            defMap.put("status", "0");
        } else {
            defMap.put("status", "1");
        }
        defMap.put("enclosureType", "0");
        if (!igvAddress.getRightText().equals("不显示位置")) {
            defMap.put("address", address);
        }
        defMap.put("latitude", latitude);
        defMap.put("content",et_content.getContent());
        defMap.put("longitude", longitude);
        OkHttpClientManager.postAsyncAddHead(ApiKey.ADMIN_SPACE_ADD, defMap, "", new
                NewRequestCallBack<Result>(Result.class) {

                    @Override
                    public void onSuccess(Result listBean) {
                        Intent intent = new Intent();
                        mRxManager.post(AppConstant.RXTag.UPDATE_USER, "");
                        setResult(Activity.RESULT_OK, intent);
                        // RESULT_OK就是一个默认值，=-1，它说OK就OK吧
                        finish();
                        stopProgressDialog();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                        stopProgressDialog();
                    }
                });


    }
    /**
     * 压缩本地图片
     * @param srcPath
     * @return
     */
    private Bitmap compressImageFromFile(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//      return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        //其实是无效的,大家尽管尝试
        return bitmap;
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
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
        paths=mPhotosSnpl.getData();
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

    /**
     * 定位SDK监听函数
     *
     * @author
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            longitude = location.getLongitude() + "";
            latitude = location.getLatitude() + "";


        }
    }

    public BDLocationListener myListener = new MyLocationListener();

}
