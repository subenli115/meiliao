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
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.NewQRCodeUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.util.Calendar;

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
public class BottomPlayerView extends CustomRelativeLayout implements MoreImageView.OnMoreImageViewClickListener {
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
    private String id;
    private String itemId;
    private TextView mTvNum;
    private TextView mTvWord;
    private ImageView Ivbg;
    private TextView tvTitle;
    private AutoLinearLayout allTime;
    private TextView mTvNumDay;
    private TextView tvName;
    private TextView tvShare;
    private ImageView ivClose;
    private AutoRelativeLayout arl_first;
    private View iv_share_exercise_close;
    private TextView tv_qx;


    public BottomPlayerView(Context context) {
        this(context, null);
    }

    public BottomPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BottomPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected int getResourseLayoutId() {
        return R.layout.custom_view_player;
    }

    //初始化控件
    private void initViews() {
        ButterKnife.bind(this, rootView);
        tvShareExerciseTitle = ViewUtil.getView(rootView, R.id.tv_share_exercise_title);
        lLayoutShareExercise = ViewUtil.getView(rootView, R.id.ll_share_exercise_layout);
////        ivExerciseShareBG = ViewUtil.getView(rootView, R.id.iv_share_exercise_bg);
        rLayoutShot = ViewUtil.getView(rootView, R.id.rl_bottom_myshot);
        tvTitle = ViewUtil.getView(rootView, R.id.tv_title);
        tvName = ViewUtil.getView(rootView, R.id.tv_name);
////        tvDay = ViewUtil.getView(rootView, R.id.tv_share_exercise_day);
        Ivbg = ViewUtil.getView(rootView, R.id.iv_bg);
////        tvYearAndMonth = ViewUtil.getView(rootView, R.id.tv_exercise_yearAndMonth);
////        tvDayCount = ViewUtil.getView(rootView, R.id.tv_exercise_day_count);
////        tvTotalTimeCount = ViewUtil.getView(rootView, R.id.tv_exercise_total_time_count);
////        tvTotalAlbumCount = ViewUtil.getView(rootView, R.id.tv_exercise_total_lbum_count);
//        mCustomNumbersView = ViewUtil.getView(rootView, R.id.customNumbersView);
        mTvNum = ViewUtil.getView(rootView, R.id.tv_num);
////        mCustomNumbersView.setTextColor(Color.WHITE);
        ivExerciseShareUserHead = ViewUtil.getView(rootView, R.id.iv_exercise_user_head);
        ivExerciseShareQrCode = ViewUtil.getView(rootView, R.id.tv_exercise_qrcode);
        mTvWord = ViewUtil.getView(rootView, R.id.tv_word);
        mMoreImageView = ViewUtil.getView(rootView, R.id.moreImageView);
        allTime = ViewUtil.getView(rootView, R.id.all_time);
        mMoreImageView.setOnMoreImageViewClickListener(this);
        mTvNumDay = ViewUtil.getView(rootView, R.id.tv_num_days);
        tvShare = ViewUtil.getView(rootView, R.id.tv_share);
        ivClose = ViewUtil.getView(rootView, R.id.iv_close);
        arl_first = ViewUtil.getView(rootView, R.id.arl_first);
        tv_qx = ViewUtil.getView(rootView, R.id.tv_qx);
        tv_qx.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tvShare.setVisibility(View.VISIBLE);
                arl_first.setVisibility(View.GONE);

            }
        });
        iv_share_exercise_close = ViewUtil.getView(rootView, R.id.iv_share_exercise_close);
        tvShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tvShare.setVisibility(GONE);
                arl_first.setVisibility(View.VISIBLE);

            }
        });
        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (onCloseListener != null) {
                    onCloseListener.onClose();
                    setVisibility(GONE);
//                }
            }
        });

//        setUserHead(StringUtils.headImg());

//        setDate(System.currentTimeMillis());
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
    public void setTimeShow(){
        allTime.setVisibility(View.INVISIBLE);
    }
    //设置当前日期
    public void setDate(long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        tvDay.setText(String.valueOf(cal.get(Calendar.DATE)));
        tvYearAndMonth.setText(String.format(getContext().getString(R.string.yyyy_mm), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) +
                1));
    }

    public void setTitleText(String minute,String day,String title) {
//        minute = minute == 0 ? 1 : minute;
//        tvShareExerciseTitle.setText(String.format(getContext().getString(R.string.exercise_finish), MyAPP.getUserInfo().getNickName(),
//                minute));
//        tvShareExerciseTitle.setText(String.format("", MyAPP.getUserInfo().getNickName(),
//                ""));
        tvName.setText("");
        tvName.setText(title);
        mTvNum.setText(minute+"");
        mTvNumDay.setText(day);
        tvShareExerciseTitle.setText(MyAPP.getUserInfo().getNickName());
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
    public void setUserHead(String url, final String picture) {
        ImageLoaderUtils.displayCircle(getContext(), ivExerciseShareUserHead, StringUtils.headImg(), R.mipmap.ic_user_pic);
        Glide.with(getContext()).load(picture).error(R.mipmap.jdx_bg_live).into(Ivbg);


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
//        if (onCloseListener != null) {
//            onCloseListener.onClose();
//        }
    }
    /**
     * 进行分享请求
     *
     * @param shareMedia 分享到的应用
     */
    private void share(final SHARE_MEDIA shareMedia) {
        ivExerciseShareQrCode.setVisibility(View.VISIBLE);
        shotFilePath = ViewUtil.myShot(rLayoutShot);
        //上传图片到服务器
        LoadingDialog.cancelDialogForLoading();
        ShareUtil.shareImage(getContext(),shareMedia,"",new File(shotFilePath),null,shotFilePath);
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                if (onCloseListener != null) {
                    onCloseListener.onClose();
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

        Glide.with(getContext()).load(R.mipmap.bg_jdx_exercise).transform(new GlideRoundTransform(getContext())).into(ivExerciseShareBG);
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
        }
    }

    //取消按钮监听
    public interface OnCloseListener {
        void onClose();
    }

}
