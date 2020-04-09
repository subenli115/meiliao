package com.ziran.meiliao.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.ShareBean;
import com.ziran.meiliao.ui.bean.ShareCouponBean;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewQRCodeUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

import java.io.File;
import java.util.Calendar;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     练习海报生成组合控件
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class BottomExerciseView extends CustomRelativeLayout implements MoreImageView.OnMoreImageViewClickListener {
    /**
     * 剪切控件内的标题
     */
    private TextView tvShareExerciseTitle;
    /**
     * 剪切控件内的当前的日期（DAY）
     */
    private TextView tvDay;
    /**
     * 剪切控件内的当前的年月（格式 Year.Month）
     */
    private TextView tvYearAndMonth;
    /**
     * 剪切控件内的分别是 连续天数 ，总冥想时间 总冥想专辑
     */
//    private TextView tvDayCount, tvTotalTimeCount, tvTotalAlbumCount;
    private CustomNumbers1View mCustomNumbersView;
    /**
     * 剪切控件内的用户头像
     */
    private ImageView ivExerciseShareUserHead;
    /**
     * 剪切控件的背景
     */
    private ImageView ivExerciseShareBG;
//    /**
//     * 剪切控件的二维码
//     */
//    private ImageView ivExerciseShareQrCode;
    /**
     * 分享控件
     */
    private MoreImageView mMoreImageView;
    /**
     * 需要剪切的控件
     */
    private View rLayoutShot;
    /**
     * 显示的内容控件
     */
    private View lLayoutShareExercise;
    /**
     * 截取控件的图片路径
     */
    private String shotFilePath;
    /**
     * 点击取消监听
     */
    private OnCloseListener onCloseListener;

    /**
     * 图片上传服务器需要的参数
     */
    private String hisId;
    private ImageView ivExerciseShareQrCode;

    public BottomExerciseView(Context context) {
        this(context, null);
    }

    public BottomExerciseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BottomExerciseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected int getResourseLayoutId() {
        return R.layout.custom_exercise_view;
    }

    //初始化控件
    private void initViews() {
        ButterKnife.bind(this, rootView);
        tvShareExerciseTitle = ViewUtil.getView(rootView, R.id.tv_share_exercise_title);
        lLayoutShareExercise = ViewUtil.getView(rootView, R.id.ll_share_exercise_layout);
        ivExerciseShareBG = ViewUtil.getView(rootView, R.id.iv_share_exercise_bg);
        rLayoutShot = ViewUtil.getView(rootView, R.id.rl_bottom_myshot);
        tvDay = ViewUtil.getView(rootView, R.id.tv_share_exercise_day);
        tvYearAndMonth = ViewUtil.getView(rootView, R.id.tv_exercise_yearAndMonth);
//        tvDayCount = ViewUtil.getView(rootView, R.id.tv_exercise_day_count);
//        tvTotalTimeCount = ViewUtil.getView(rootView, R.id.tv_exercise_total_time_count);
//        tvTotalAlbumCount = ViewUtil.getView(rootView, R.id.tv_exercise_total_lbum_count);
        mCustomNumbersView = ViewUtil.getView(rootView, R.id.customNumbersView);
//        mCustomNumbersView.setTextColor(Color.WHITE);
        ivExerciseShareUserHead = ViewUtil.getView(rootView, R.id.iv_exercise_user_head);
        ivExerciseShareQrCode = ViewUtil.getView(rootView, R.id.tv_exercise_qrcode);

        mMoreImageView = ViewUtil.getView(rootView, R.id.moreImageView);
        mMoreImageView.setOnMoreImageViewClickListener(this);
        setUserHead(StringUtils.headImg());

        setDate(System.currentTimeMillis());
        //截屏
    }

    /**
     * 显示控件
     *
     * @param isFull 是否全屏显示
     */
    public void setShow(boolean isFull) {
        int left, top;

        left = DisplayUtil.dip2px(30);
        top = DisplayUtil.dip2px(16);
        setVisibility(VISIBLE);
        //设置内边距
        lLayoutShareExercise.setPadding(left, top, left, top/2);
    }

    //设置当前日期
    public void setDate(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        tvDay.setText(String.valueOf(cal.get(Calendar.DATE)));
        tvYearAndMonth.setText(String.format(getContext().getString(R.string.yyyy_mm), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) +
                1));
    }

    public void setTitleText(int minute) {
        minute = minute == 0 ? 1 : minute;
//        tvShareExerciseTitle.setText(String.format(getContext().getString(R.string.exercise_finish), MyAPP.getUserInfo().getNickName(),
//                minute));
//        tvShareExerciseTitle.setText(String.format("", MyAPP.getUserInfo().getNickName(),
//                ""));
        tvShareExerciseTitle.setText(MyAPP.getUserInfo().getNickName());
    }

    public void setCountTextView(int leftCount, int midCount, int rightCount) {
        midCount = midCount == 0 ? 1 : midCount;
        mCustomNumbersView.setDatas(leftCount, midCount, rightCount, false);
        setUserHead(StringUtils.headImg());
    }

    public void setCountTextView(int leftCount, String midCount, int rightCount) {
        mCustomNumbersView.setDatas(leftCount, midCount, rightCount, true);
        setUserHead(MyAPP.getUserInfo().getHeadImg());
    }

    /**
     * 在二维码中间添加Logo图案
     */
    private Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        // 获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        // logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }
    public void setUserHead(String url) {
        ImageLoaderUtils.displayCircle(getContext(), ivExerciseShareUserHead, url);
    }
    //加载二维码
    public void setQRCode(String url) {
        Bitmap qrImage = NewQRCodeUtils.createQRImage(url, ivExerciseShareQrCode);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.login_logo);
        Bitmap bitmap1 = addLogo(qrImage, bitmap);

        if (qrImage != null) {

            ivExerciseShareQrCode.setImageBitmap(bitmap1);
        }
        ImageLoaderUtils.display(getContext(),ivExerciseShareQrCode,url);
    }

    //点击事件
    @OnClick(R.id.iv_share_exercise_close)
    public void onClick(View view) {
        setVisibility(GONE);
        if (onCloseListener != null) {
            onCloseListener.onClose();
        }
    }

    /**
     * 进行分享请求
     *
     * @param shareMedia 分享到的应用
     */
    private void share(final SHARE_MEDIA shareMedia) {
        ivExerciseShareQrCode.setVisibility(View.VISIBLE);
        shotFilePath = ViewUtil.myShot(rLayoutShot);
        Map<String, String> number = MapUtils.getOnlyCan("hisId", hisId);
        //上传图片到服务器
        OkHttpClientManager.postContentAndFiles(ApiKey.PRACTICE_UPLOADPOSTER, number, FileUtil.str2File(shotFilePath), new
                NewRequestCallBack<ShareCouponBean>(ShareCouponBean.class) {
            @Override
            public void onSuccess(ShareCouponBean result) {
                if (EmptyUtils.isNotEmpty(result.getData())) {
                    ShareBean data = result.getData();
                    //启动分享
                    ShareUtil.shareImage(getContext(),shareMedia,data.getShareDescript(),new File(shotFilePath),null,shotFilePath);
//                    ShareUtil.shareWeb(getContext(), shareMedia, data.getShareDescript(), shotFilePath, data.getSharePagePath() +
//                            "&isShare=1", data.getShareTitle(), data.getShareDescript());
                    HandlerUtil.runMain(new Runnable() {
                        @Override
                        public void run() {
                            if (onCloseListener != null) {
                                onCloseListener.onClose();
                            }
                            setVisibility(GONE);
                        }
                    });

                }
            }
        });
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.onCloseListener = onCloseListener;
    }

    /**
     * 加载背景图片
     *
     * @param filePath 图片背景的路径
     */
    public void setBG(String filePath) {
//        Glide.with(getContext()).load(filePath).transform(new GlideRoundTransform(getContext())).placeholder(R.mipmap.ic_loading_square_small).thumbnail(0.2f).into(ivExerciseShareBG);
        Glide.with(getContext()).load(filePath).placeholder(R.mipmap.ic_loading_square_small).thumbnail(0.2f).into(ivExerciseShareBG);
//        Glide.with(getContext()).load(R.mipmap.bg_jdx_exercise).transform(new GlideRoundTransform(getContext())).into(ivExerciseShareBG);
    }


    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    @Override
    public void onClick(int index) {
        switch (index) {
            case 0:
                share(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform);
                break;
            case 1:
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform);
                break;
            case 2:
                share(SHARE_MEDIA.SINA.toSnsPlatform().mPlatform);
                break;
            case 3:
                share(SHARE_MEDIA.QQ.toSnsPlatform().mPlatform);
                break;
        }
    }

    //取消按钮监听
    public interface OnCloseListener {
        void onClose();
    }

}
