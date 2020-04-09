package com.yuyh.library.imgsel;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.FileProviderUriUtil;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.yuyh.library.imgsel.common.Callback;
import com.yuyh.library.imgsel.common.Constant;
import com.yuyh.library.imgsel.utils.MyFileUtils;
import com.yuyh.library.imgsel.utils.StatusBarCompat;

import java.io.File;
import java.util.ArrayList;

public class ImgSelActivity extends FragmentActivity implements View.OnClickListener, Callback {

    public static final String INTENT_RESULT = "result";
    public static final int IMAGE_CROP_CODE = 1;
    public static final int STORAGE_REQUEST_CODE = 1;

    private ImgSelConfig config;

    private RelativeLayout rlTitleBar;
    private TextView tvTitle;
    private Button btnConfirm;
    private ImageView ivBack;
    private String cropImagePath;

    private ArrayList<String> result = new ArrayList<>();

    public static void startActivity(Activity activity, ImgSelConfig config, int RequestCode) {
        Intent intent = new Intent(activity, ImgSelActivity.class);
        Constant.config = config;
        activity.startActivityForResult(intent, RequestCode);
    }
    public static void startActivity(Activity activity, ImgSelConfig config, int RequestCode,int fromType) {
        config.fromType = fromType;
        Intent intent = new Intent(activity, ImgSelActivity.class);
        Constant.config = config;
        activity.startActivityForResult(intent, RequestCode);
    }

    public static void startActivity(Fragment fragment, ImgSelConfig config, int RequestCode,int fromType) {
        config.fromType = fromType;
        Intent intent = new Intent(fragment.getActivity(), ImgSelActivity.class);
        Constant.config = config;
        fragment.startActivityForResult(intent, RequestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_sel);
        Constant.imageList.clear();
        config = Constant.config;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    STORAGE_REQUEST_CODE);
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fmImageList, ImgSelFragment.instance(config), null)
                    .commit();
        }

        initView();
        if (!MyFileUtils.isSdCardAvailable()) {
            Toast.makeText(this, "SD卡不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);

        if (config != null) {
            if (config.backResId != -1) {
                ivBack.setImageResource(config.backResId);
            }

            if (config.statusBarColor != -1) {
                StatusBarCompat.compat(this, config.statusBarColor);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                        && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    //透明状态栏
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
            rlTitleBar.setBackgroundColor(config.titleBgColor);
            tvTitle.setTextColor(config.titleColor);
            tvTitle.setText(config.title);
            btnConfirm.setBackgroundColor(config.btnBgColor);
            btnConfirm.setTextColor(config.btnTextColor);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnConfirm) {
            if (Constant.imageList != null && !Constant.imageList.isEmpty()) {
                exit();
            }
        } else if (id == R.id.ivBack) {
            finish();
        }
    }

    @Override
    public void onSingleImageSelected(String path) {
        if (config.needCrop) {
            crop(path);
        } else {
            Constant.imageList.add(path);
            exit();
        }
    }

    @Override
    public void onImageSelected(String path) {
        btnConfirm.setText("确定(" + Constant.imageList.size() + "/" + config.maxNum + ")");

    }

    @Override
    public void onImageUnselected(String path) {
        btnConfirm.setText("确定(" + Constant.imageList.size() + "/" + config.maxNum + ")");
    }

    @Override
    public void onCameraShot(File imageFile) {
        if (imageFile != null) {
            if (config.needCrop) {
                crop(imageFile.getAbsolutePath());
            } else {
                Constant.imageList.add(imageFile.getAbsolutePath());
                exit();
            }
        }
    }

    private void crop(String imagePath) {
        if (TextUtils.isEmpty(config.cropImagePath)) {
            cropImagePath = MyFileUtils.createRootPath(this) + "/" + System.currentTimeMillis() + ".jpg";
        } else {
            cropImagePath = config.cropImagePath;
        }
        Uri imageUri = FileProviderUriUtil.getImageContentUri(this, new File(imagePath));
        File file = new File(cropImagePath);
        FileUtil.delete(file.getPath());
        MyFileUtils.createFile(file);
        Uri outputUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", config.aspectX);
        intent.putExtra("aspectY", config.aspectY);
        intent.putExtra("outputX", config.outputX);
        intent.putExtra("outputY", config.outputY);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, IMAGE_CROP_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_CROP_CODE && resultCode == RESULT_OK) {
            Constant.imageList.add(cropImagePath);
            exit();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void exit() {
        Intent intent = new Intent();
        result.clear();
        result.addAll(Constant.imageList);
        if (config.fromType!=0){
            Message message = new Message();
            message.what = config.fromType;
            message.obj = new ArrayList<>(result);
            RxManagerUtil.post("image",message);
        }
        intent.putStringArrayListExtra(INTENT_RESULT, result);
        setResult(RESULT_OK, intent);
        Constant.imageList.clear();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_REQUEST_CODE:
                if (grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fmImageList, ImgSelFragment.instance(config), null)
                            .commitAllowingStateLoss();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
