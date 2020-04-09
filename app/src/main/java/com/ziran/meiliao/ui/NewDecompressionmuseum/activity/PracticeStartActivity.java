package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cncoderx.wheelview.OnWheelChangedListener;
import com.cncoderx.wheelview.Wheel3DView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.SelectNumAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.SelectNumMoreAdapter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.DailyMindBean;
import com.ziran.meiliao.ui.bean.ExerciseUploadBean;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticePageBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.RecordActivity;
import com.ziran.meiliao.ui.decompressionmuseum.activity.TakeNotesActivity;
import com.ziran.meiliao.ui.decompressionmuseum.contract.MindfulnessHallCountTimeContract;
import com.ziran.meiliao.ui.decompressionmuseum.presenter.MindfulnessHallCountTimePresenter;
import com.ziran.meiliao.ui.decompressionmuseum.util.MusicDingPlayerUtil;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.GlideLoadUtils;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.PracticeDataUtil;
import com.ziran.meiliao.utils.ProgressUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.BottomExerciseView;
import com.ziran.meiliao.widget.BottomMbsrView;
import com.ziran.meiliao.widget.CustomNumbers1View;
import com.ziran.meiliao.widget.RippleBackground;
import com.ziran.meiliao.widget.SwitchButton;
import com.ziran.meiliao.widget.Timeutils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 开始练习界面
 */

public class PracticeStartActivity extends CommonHttpActivity<MindfulnessHallCountTimePresenter, CommonModel> implements
        MindfulnessHallCountTimeContract.View,View.OnClickListener {
    private static final String TEST = "demo";
    @Bind(R.id.tv_back)
    TextView tvBack;
        @Bind(R.id.tv_menu)
    TextView tvMenu;
    @Bind(R.id.wheel)
    Wheel3DView wheel;
    @Bind(R.id.all_time)
    LinearLayout allTime;
    @Bind(R.id.tv_min)
    TextView tvMin;
    @Bind(R.id.afl_time_picker)
    FrameLayout aflTimePicker;
    @Bind(R.id.tv_start)
    TextView tvStart;
    @Bind(R.id.arl_main)
    AutoRelativeLayout arlMain;
    @Bind(R.id.iv_count_time)
    ImageView ivCountTime;
    @Bind(R.id.tv_set_time)
    TextView tvSetTime;
    @Bind(R.id.ll_playing)
    AutoLinearLayout ll_playing;
    @Bind(R.id.rippleBackground)
    RippleBackground rippleBackground;
    @Bind(R.id.tv_mindfulness_hall_cown_time_total_title)
    TextView tvMindfulnessHallCountTimeTotalTitle;
    @Bind(R.id.customNumbersView)
    CustomNumbers1View mCustomNumbersView;
    @Bind(R.id.iv_mindfulness_hall_finish_avatar)
    ImageView ivMindfulnessHallFinishAvatar;
    @Bind(R.id.tv_share_day_tips)
    View tvShareDayTips;
    @Bind(R.id.fl_mindfulness_hall_finish)
    FrameLayout flMindfulnessHallFinish;
    @Bind(R.id.bottom_exercise_view1)
    BottomExerciseView mBottomExerciseView;
    @Bind(R.id.bottom_exercise_view)
    BottomMbsrView mBottomMbsrView;



    private View contentView;
    private RecyclerView recyclerViewFirst;
    private SelectNumAdapter mAdapter;
    private AutoRelativeLayout relativelayoutSecond;
    private RecyclerView recyclerViewSecond;
    private TextView tvHintNum;
    private TextView tvExeNum;
    private String setNum="1";
    private SelectNumMoreAdapter mMoreAdapter;
    private Drawable intoDrawable;
    private Drawable topDrawable;
    private SwitchButton switchButton;
    private TextView tvQd;
    //本次练习的总时间
    private int practiceDuration;
    //默认的练习时间(单位:秒)
    private static int countTime = 0;
    //是否间隔时间提醒
    private boolean isSpanDing = false;
    private MusicDingPlayerUtil mMusicDingPlayerUtil;
    private ProgressUtil progressUtil;
    private Timeutils timeutils;
    private boolean able=false;
    public int currentItem;
    private String newStrMin;
    private Map<String, String> stringMap;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int Index=5;
    private ExerciseUploadBean.DataBean mDataBean;
    private int isFirstPractice;
    private AutoRelativeLayout relativelayoutJg;
    private TextView mTextView;
    private String hintTime="1";
    private JdxShareBean.DataBean mShareBean;
    private String itemId;
    private String id;
    private Timer timer;
    private PowerManager.WakeLock wl;
    private TextView tvInto;
    private PowerManager.WakeLock wakeLock;
    private AlarmManager mAlarmManager;
    private long allOffTime;
    private ArrayList<Integer> moreArray;
    private String timePoint="";
    @Bind(R.id.iv_cover)
    ImageView ivCover;
    private String hintNum="";
    private ArrayList<Integer> array;


    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_exercise;
    }

    @Override
    protected void onResume() {
        super.onResume();
        String videoUrl = getVideoUrl(0);
            GlideLoadUtils.getInstance().glideLoad(this,videoUrl,ivCover,R.mipmap.ic_loading_square_small);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void onBackPressed() {
        if (MyAPP.isLogout) {
            NewLoginActivity.startAction(this);
        } else {
            super.onBackPressed();
        }
    }
        class ScreenStatusReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
         allOffTime=0;
    }
}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }
    final Handler myHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            //如果该消息是本程序所发送的
            if (msg.what == 0x1222)
            {
                if(wheel!=null){

                    if(wheel.getVisibility()==View.VISIBLE){
                        wheel.setVisibility(View.INVISIBLE);
                        allTime.setVisibility(View.VISIBLE);
                        tvMin.setVisibility(View.INVISIBLE);
                        tvSetTime.setText(setNum+":00");

                    }
                }
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (wl != null) {
            try {
                wl.release();
            } catch (Throwable th) {

            }
        } else {

        }
    }
    @Override
    public void initView() {
        if(getIntent()!=null){
            int time = getIntent().getIntExtra("time", 1);
            mShareBean = getIntent().getExtras().getParcelable("JdxShareBean");
            if(mShareBean!=null){
                itemId = getIntent().getStringExtra("itemId");
                tvMenu.setVisibility(View.INVISIBLE);
                id = getIntent().getStringExtra("id");
            }
                tvSetTime.setText(time+":00");
        }
        timer = new Timer();
        stringMap = MapUtils.getDefMap(true);
         topDrawable= getResources().getDrawable(R.mipmap.jdx_menu_top);
         intoDrawable= getResources().getDrawable(R.mipmap.jdx_menu_into);

         wheel.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View view, MotionEvent motionEvent) {
                 if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                     autoSetTime();
                 }

                 return false;
             }
         });
        wheel.setOnWheelChangedListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(com.cncoderx.wheelview.WheelView view, int oldIndex, int newIndex) {
                     setNum = view.getItem(newIndex).toString();
                Index=newIndex;
            }

        });
        mMusicDingPlayerUtil = new MusicDingPlayerUtil();
        mMusicDingPlayerUtil.setOnDingFinishListener(new MusicDingPlayerUtil.OnDingFinishListener() {
            @Override
            public void onFinish() {
                if (isPracticeRunning()) {
                    if (isSpanDing) {
                        progressUtil.startUpdate();
                        MyAPP.mServiceManager.rePlay();
                    }
                }
            }
        });
        mBottomExerciseView.setOnCloseListener(
                new BottomExerciseView.OnCloseListener() {
            @Override
            public void onClose() {
                flMindfulnessHallFinish.setVisibility(View.VISIBLE);
            }
        });
        progressUtil = ProgressUtil.get();
        progressUtil.setPlayMode(ProgressUtil.PLAY_MODE_EXERCISE);
        mPresenter.getDailyMind(ApiKey.PRACTICE_GET_DAILY_MIND, MapUtils.getDefMap(true));
    }

    private void autoSetTime() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                  Message msg = new Message();
                  msg.what = 0x1222;
                  //发送消息
                  myHandler.sendMessage(msg);
                             }
                },1000);//延时1s执行


    }

    @OnClick({ R.id.tv_mindfulness_hall_finish_restart, R.id.tv_mindfulness_hall_finish_share, R.id.tv_mindfulness_hall_finish_make_notes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mindfulness_hall_finish_restart:  //继续练习
                flMindfulnessHallFinish.setVisibility(View.INVISIBLE);
                tvStart.setVisibility(View.VISIBLE);
                tvMenu.setVisibility(View.VISIBLE);
                tvBack.setVisibility(View.VISIBLE);
                aflTimePicker.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_mindfulness_hall_finish_make_notes: //跳转到做笔记界面
                Bundle bundle = new Bundle();
                bundle.putString("hisId", mDataBean.getHisId());
                bundle.putString("type", "add");
                startActivity(TakeNotesActivity.class, bundle);
                break;
            case R.id.tv_mindfulness_hall_finish_share: //分享
                flMindfulnessHallFinish.setVisibility(View.GONE);
                mBottomExerciseView.setBG(getBg());
                if (EmptyUtils.isNotEmpty(mDataBean)) {
                    mBottomExerciseView.setCountTextView(mDataBean.getDays(), mDataBean.getTimesFormat(), mDataBean.getAmounts());
                    mBottomExerciseView.setTitleText(practiceDuration / 60);
                    mBottomExerciseView.setHisId(mDataBean.getHisId());
                    mBottomExerciseView.setShow(true);

                }
                break;
        }
    }



//    /**
//     * 初始化View
//     */
//    private void initViewPager() {
//        List<PracticePageBean.DataBean.ListBean> list = PracticeDataUtil.getPracticePageBean().getData().getList();
//        for (int i = 0; i < list.size(); i++) {
//            PracticeBgVideoFragment fragment = PracticeBgVideoFragment.newInstance(i, getVideoUrl(i), null, new
//                    PracticeBgVideoFragment.OnPracticeFinishListener() {
//                        @Override
//                        public boolean onPracticeFinish(int index) {
//                            return !true;
//                        }
//                    });
//            fragmentList.add(fragment);
//        }
//        mViewPagerFixed.setOffscreenPageLimit(fragmentList.size());
//        mViewPagerFixed.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList));
//        mViewPagerFixed.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                currentItem = position;
//            }
//        });
//        mRxManager.on(AppConstant.RXTag.PRACTICE_CAN_SCROLL, new Action1<Boolean>() {
//            @Override
//            public void call(Boolean scroll) {
//                mViewPagerFixed.setScanScroll(scroll);
//            }
//        });
//    }




    public static void startAction(Context context, int time, JdxShareBean.DataBean result, String bookId, String itemId) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, PracticeStartActivity.class);
            intent.putExtra("time",time);
            intent.putExtra("isjdx",result);
            if(result!=null){
                Bundle bundle = new Bundle();
                bundle.putParcelable("JdxShareBean", result);
                intent.putExtras(bundle);
                intent.putExtra("id",bookId);
                intent.putExtra("itemId",itemId);
            }
            context.startActivity(intent);
        }
    }
    private  void setAlarmTime() {
/**刷新时间widget闹钟定时器（3秒一次）**/
        mAlarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(TEST);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
        {
            mAlarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,30000,pendingIntent);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            mAlarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,30000,pendingIntent);
        }else{
            mAlarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,30000,pendingIntent);
        }
    }

    public class AlarmReceover extends BroadcastReceiver {
        public static  final String TEST= PracticeStartActivity.TEST;
        private static final String TAG = "AlarmReceover_LOG";
        AlarmManager mAlarmManager;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action == TEST){
                setAlarmTime(context);
            }
        }

        private  void setAlarmTime(Context context) {
            /**刷新时间widget闹钟定时器（3秒一次）**/
            mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(TEST);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if(Build.VERSION.SDK_INT >Build.VERSION_CODES.KITKAT){ //api大于19的时候
                //和setRepeating的最大的区别是这个动作只执行一次
                mAlarmManager.setWindow(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(),50 * 1000, pendingIntent);
            }
            upDateClockView();
        }

        public void upDateClockView() {
            Calendar c = Calendar.getInstance();
            int hour =c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);

        }
    }


    @OnClick({R.id.tv_back, R.id.tv_menu, R.id.tv_start,R.id.iv_count_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                if (MyAPP.isLogout) {
                    NewLoginActivity.startAction(this);
                } else {
                    finish();
                }
                break;
            case R.id.tv_menu:
                showPopWindow();
                break;
            case R.id.tv_start:
                TextView tv1 = (TextView) ll_playing.getChildAt(0);
                TextView tv2 = (TextView) ll_playing.getChildAt(1);
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(wheel.getVisibility()==View.VISIBLE){
                            ToastUitl.showShort("请先选择时间");
                        }else {

                            rippleBackground.startRippleAnimation();
                            tvStart.setVisibility(View.VISIBLE);
                            ll_playing.setVisibility(View.INVISIBLE);
                            tvStart.setText("暂停");
                            ivCountTime.setEnabled(false);
                            setStartStatus(false);
                            ivCountTime.setClickable(false);
                            mMusicDingPlayerUtil.startPlayDing();
                            progressUtil.startUpdate(); //开始计时
                            timeutils.puseTimer();
                        }
                    }
                });
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        allOffTime=0;
                        practiceDuration=Integer.parseInt(newStrMin)-Integer.parseInt(getCurrenMin());
                        if(Integer.parseInt(newStrMin)-Integer.parseInt(getCurrenMin())<2){
                            ToastUitl.showShort("练习不足一分钟，不做记录!");
                        }else{
                            //自己结束
                            if(mShareBean==null){
                                upload();
                            }else{
                               isFinish();
                            }
                        }

                        tvSetTime.setText("00:00");
                            rippleBackground.stopRippleAnimation();
                            tvStart.setVisibility(View.VISIBLE);
                            ll_playing.setVisibility(View.INVISIBLE);
                            tvStart.setText("开始正念");
                        progressUtil.pauseUpdate();
                            setStartStatus(true);
                            mMusicDingPlayerUtil.startPlayDing();
                            ivCountTime.setEnabled(true);
                            ivCountTime.setClickable(true);
                            progressUtil.resUpdate();
                            timeutils.stopTimer();
                    }
                });
                //继续
                if(rippleBackground.isRippleAnimationRunning()){
                    //播放状态  暂停textview
                    mMusicDingPlayerUtil.startPlayDing();
                    setStartStatus(false);
                    progressUtil.resUpdate();
                    ll_playing.setVisibility(View.VISIBLE);
                    ivCountTime.setEnabled(false);
                    ivCountTime.setClickable(false);
                    tvStart.setVisibility(View.INVISIBLE);
                    rippleBackground.stopRippleAnimation();
                     timeutils.puseTimer();
                }else {
                   // 开始正念textview
                    //停止状态
                     newStrMin = getCurrenMin();
                    if(newStrMin.equals("")||wheel.getVisibility()==View.VISIBLE||tvSetTime.getText().toString().equals("00:00")){
                        ToastUitl.showShort("请选择时间");
                    }else{
                        if(hintTime!=""&&hintTime!="null"){
                            timeutils=new Timeutils(tvSetTime,(long)Integer.parseInt(newStrMin)*60,Integer.parseInt(hintTime)*60);
                        }
                        timeutils.setOnHintFinishListener(new Timeutils.OnHintFinishListener() {
                            @Override
                            public void onHint() {
                                mMusicDingPlayerUtil.startPlayDing();
                            }
                        });
                        timeutils.setOnDingFinishListener(new Timeutils.OnDingFinishListener(){
                            @Override
                            public void onFinish() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        setStartStatus(true);
                                        ivCountTime.setEnabled(true);
                                        ivCountTime.setClickable(true);
                                        mMusicDingPlayerUtil.startPlayDing();
                                        timeutils.stopTimer();
                                        rippleBackground.stopRippleAnimation();
                                        tvStart.setText("开始正念");
                                        //到时间结束
                                        allOffTime=0;
                                        if(mShareBean==null){
                                            upload();
                                        }else{
                                           isFinish();
                                        }
                                    }
                                });
                            }
                        });
                        mMusicDingPlayerUtil.startPlayDing();
                        progressUtil.startUpdate(); //开始计时
                        progressUtil.setMax(Integer.parseInt(newStrMin)*60);
                        if(tvStart.getVisibility()==View.VISIBLE){
                            timeutils.startTimer();
                            tvStart.setText("暂停");
                            setStartStatus(false);
                            ivCountTime.setEnabled(false);
                            ivCountTime.setClickable(false);
                            rippleBackground.startRippleAnimation();
                            ll_playing.setVisibility(View.INVISIBLE);
                        }
                    }
                }
                break;
            case R.id.iv_count_time:
                if(wheel.getVisibility()==View.VISIBLE){
                    wheel.setVisibility(View.INVISIBLE);
                    allTime.setVisibility(View.VISIBLE);
                    tvMin.setVisibility(View.INVISIBLE);
                    tvSetTime.setText(setNum+":00");

                }else{
                    wheel.setVisibility(View.VISIBLE);
                    allTime.setVisibility(View.INVISIBLE);
                    tvMin.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void setStartStatus(boolean isopen) {
        tvBack.setEnabled(isopen);
        if(isopen){
            tvBack.setVisibility(View.VISIBLE);
            tvMenu.setVisibility(View.VISIBLE);
        }else{
            tvMenu.setVisibility(View.INVISIBLE);
            tvBack.setVisibility(View.INVISIBLE);
        }
        tvBack.setClickable(isopen);

        tvMenu.setEnabled(isopen);
        tvMenu.setClickable(isopen);
    }

    private void upload() {
        long duration = (long) Integer.parseInt(newStrMin) * 60 - (long) Integer.parseInt(getCurrenMin()) * 60;
        stringMap.put("duration", String.valueOf(duration));
        stringMap.put("praMusicId",getPreMusicId());
        mPresenter.postPractice(ApiKey.PRACTICE_UPLOAD, stringMap);
    }

    /**
     * 获取当前背景图片的背景音乐
     *
     * @return 背景音乐路径
     */
    public String getVideoUrl(int index) {
        String mp4FileName = FileUtil.getExerciseBj2Mp4FileName(index);
        if (new File(mp4FileName).exists()) {
            return mp4FileName;
        } else {
            PracticePageBean.DataBean.ListBean listBean = PracticeDataUtil.get(index);
            if (listBean!=null&&EmptyUtils.isNotEmpty(listBean.getVedioPathUrl())) {
                PracticeDataUtil.downFile(listBean.getVedioPathUrl(), mp4FileName);
            }
            if(listBean!=null){
                return listBean.getVedioPathUrl();
            }
            return "";
        }
    }

    /**
     * @return 当前背景图片路径
     */
    public String getBg() {
        return getVideoUrl(0);
    }

    /**
     * 获取当前背景的id
     *
     * @return id
     */
    public String getPreMusicId() {
        return "1";
    }


    private String getCurrenMin() {
        String newStr="";
        if(tvSetTime.getText().toString().length()==4){
             newStr = tvSetTime.getText().toString().substring(0, 1).replaceFirst("^0*", "");
        }else{
                newStr = tvSetTime.getText().toString().substring(0, 2).replaceFirst("^0*", "");
        }
        if(newStr.equals("")){
          newStr="0";
        }
        return newStr;
    }
    /**
     * 上传练习请求返回的数据
     *
     * @param result 练习请求返回的数据
     */
    @Override
    public void practiceUploadResult(ExerciseUploadBean result) {
        if (EmptyUtils.isNotEmpty(result.getData())) {
            mDataBean = result.getData();
            int minute = practiceDuration ;
            ViewUtil.setText(tvMindfulnessHallCountTimeTotalTitle, StringUtils.format("%s完成%d分钟的冥想", MyAPP.getUserInfo().getNickName(),
                    minute > 0 ? minute : 1));
            mCustomNumbersView.setDatas(mDataBean.getDays(), mDataBean.getTimesFormat(), mDataBean.getAmounts(), true);
            ImageLoaderUtils.displayCircle(mContext, ivMindfulnessHallFinishAvatar, MyAPP.getUserInfo().getHeadImg());
        }
        isFirstPractice = mDataBean.getIsFirstPractice();
        flMindfulnessHallFinish.setVisibility(View.VISIBLE);
        tvStart.setVisibility(View.INVISIBLE);
        aflTimePicker.setVisibility(View.INVISIBLE);
        tvMenu.setVisibility(View.INVISIBLE);
        tvBack.setVisibility(View.INVISIBLE);
        tvShareDayTips.setVisibility(isFirstPractice == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showUpdateDailyMind(Result result) {
            ToastUitl.showShort(result.getResultMsg());
    }

    @Override
    public void showGainByDay(Result result) {

    }

    /**
     * 显示从服务器返回的每日正念时间
     *
     * @param result 服务器返回的每日正念时间
     */
    @Override
    public void showDailyMind(DailyMindBean.DataBean result) {
        setTimeCownNotify(result);

    }

    private void setTimeCownNotify(DailyMindBean.DataBean result) {
        if (result.isAble()) {
             able = result.isAble();
        }
        timePoint = result.getTimePoint()+"";
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressUtil.onDestroy();
        mMusicDingPlayerUtil.onDestroy();
        if(wakeLock!=null){
            try {
                wakeLock.release();
            } catch (Throwable th) {

            }
        }
        if(timeutils!=null){
            timeutils.stopTimer();
        }
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
    }
    /**
     * 初始化数据
     */
    private void initDatas(final RecyclerView view) {

        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        array = new ArrayList<>();
        if(view.getId()==R.id.recyclerView_first){

                for (int i=0;i<=Integer.parseInt(setNum);i++){
                    if(i!=0){
                        array.add(i);
                    }
                }
            Log.e("mAdapter",""+hintNum);
            mAdapter=new SelectNumAdapter(this,array,hintNum);
            mAdapter.setItemClickListener(new SelectNumAdapter.ItemClickListener() {
                @Override
                public void itemClick(int str, TextView v) {
                           mTextView =v;
                     hintNum = v.getText().toString();
                    tvHintNum.setText(hintNum);
                    }
            });
            view.setLayoutManager(manager);
            view.setAdapter(mAdapter);
            }else {
            moreArray = new ArrayList<>();
            for (int i=0;i<=24;i++){
                if(i>=7&&i<20){
                    moreArray.add(i);
                }
            }
            Log.e("mMoreAdapter",""+timePoint);
            mMoreAdapter=new SelectNumMoreAdapter(this,moreArray,timePoint);
            mMoreAdapter.setItemClickListener( new SelectNumMoreAdapter.ItemClickListener() {
                @Override
                public void itemClick(int position, TextView v) {
                    intoDrawable.setBounds(0, 0, intoDrawable.getMinimumWidth(), intoDrawable.getMinimumHeight());
                    tvExeNum.setText(v.getText().toString());

                }
            });
            view.setLayoutManager(manager);
            view.setAdapter(mMoreAdapter);
            }
    }


    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location=new int[2];
        contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_top_exercise_menu, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlMain, Gravity.TOP, 0, 330);
        final TextView tvIntoEvery=(TextView)contentView.findViewById(R.id.tv_into_every);
        recyclerViewFirst=(RecyclerView)contentView.findViewById(R.id.recyclerView_first);
        tvInto=(TextView)contentView.findViewById(R.id.tv_into);
        recyclerViewSecond=(RecyclerView)contentView.findViewById(R.id.recyclerView_second);
        relativelayoutJg=(AutoRelativeLayout)contentView.findViewById(R.id.arl_jg);
        final TextView tvIntoJg=(TextView)contentView.findViewById(R.id.tv_jg);

        AutoRelativeLayout arlThird = contentView.findViewById(R.id.arl_third);
        AutoRelativeLayout arlSecond = contentView.findViewById(R.id.arl_second);
        AutoRelativeLayout arlFirst = contentView.findViewById(R.id.arl_first);
        relativelayoutSecond=(AutoRelativeLayout)contentView.findViewById(R.id.relativelayout_second_view);
        tvHintNum=(TextView)contentView.findViewById(R.id.tv_hint_num);
        tvExeNum=(TextView)contentView.findViewById(R.id.tv_exe_num);
        if(hintNum!=null&&!hintNum.equals("")){
            tvHintNum.setText(hintNum);
        }
        if(!timePoint.equals("")){
            tvExeNum.setText(timePoint+"时");
        }
        switchButton=(SwitchButton)contentView.findViewById(R.id.sb_set_time_switch);
        switchButton.setChecked(able);
        tvQd=(TextView)contentView.findViewById(R.id.tv_qd);

        tvIntoJg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTextView==null){
                    ToastUitl.showShort("请选择一个时间");
                    return;
                }
                relativelayoutJg.setVisibility(View.GONE);

            }
        });
        tvQd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSecondLayout(tvIntoEvery);
                String str = tvExeNum.getText().toString();
                Map<String, String> stringMap = MapUtils.getDefMap(true);
                timePoint=str.substring(0, str.length()-1);
                able=switchButton.isChecked()? true : false;
                stringMap.put("able",  switchButton.isChecked()? "true" : "false");
                stringMap.put("timePoint",timePoint);
                mPresenter.updateDailyMind(ApiKey.PRACTICE_UPDATE_DAILY_MINDV2, stringMap);
            }

        });
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    //选择
                    isSpanDing=true;
                }else{
                    isSpanDing=false;

                }
            }
        });
        initDatas(recyclerViewFirst);
        initDatas(recyclerViewSecond);
        arlSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSecondLayout(tvIntoEvery);
            }


        });
        arlFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(relativelayoutJg.getVisibility()==View.VISIBLE){
                    relativelayoutJg.setVisibility(View.GONE);
                    intoDrawable.setBounds(0, 0, intoDrawable.getMinimumWidth(), intoDrawable.getMinimumHeight());
                    tvInto.setCompoundDrawables(intoDrawable,null,null,null);
                }else{
                    relativelayoutJg.setVisibility(View.VISIBLE);
                    relativelayoutSecond.setVisibility(View.GONE);
                    intoDrawable.setBounds(0, 0, intoDrawable.getMinimumWidth(), intoDrawable.getMinimumHeight());
                    tvIntoEvery.setCompoundDrawables(intoDrawable,null,null,null);
                    topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
                    tvInto.setCompoundDrawables(topDrawable,null,null,null);
                }
            }

        });
        arlThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(RecordActivity.class);
            }
        });
    }

    private void changeSecondLayout(TextView tvIntoEvery) {
        if(relativelayoutSecond.getVisibility()== View.VISIBLE){
            relativelayoutSecond.setVisibility(View.GONE);
            intoDrawable.setBounds(0, 0, intoDrawable.getMinimumWidth(), intoDrawable.getMinimumHeight());
            tvIntoEvery.setCompoundDrawables(intoDrawable,null,null,null);
        }else{
            relativelayoutJg.setVisibility(View.GONE);
            relativelayoutSecond.setVisibility(View.VISIBLE);
            intoDrawable.setBounds(0, 0, intoDrawable.getMinimumWidth(), intoDrawable.getMinimumHeight());
            tvInto.setCompoundDrawables(intoDrawable,null,null,null);
            topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
            tvIntoEvery.setCompoundDrawables(topDrawable,null,null,null);
        }
    }

    /**
     * 是否正在练习
     *
     * @return true 正在练习
     */
    public boolean isPracticeRunning() {

        return rippleBackground.isRippleAnimationRunning();
    }

    private void isFinish() {
        if (EmptyUtils.isNotEmpty(mShareBean)) {
            mBottomMbsrView.setId(id+"");
            mBottomMbsrView.setItemId(itemId);
            mBottomMbsrView.setUserHead(mShareBean.getUserPic(),mShareBean.getPicture());
            mBottomMbsrView.setTitleText(mShareBean.getDuration() ,mShareBean.getWords(),mShareBean.getTitle());
            mBottomMbsrView.setHisId(mShareBean.getHisId()+"");
            mBottomMbsrView.setTimeShow();
            mBottomMbsrView.setShow(true);
            mBottomMbsrView.setOnCloseListener(new BottomMbsrView.OnCloseListener() {
                @Override
                public void onClose() {
                    mBottomMbsrView.setVisibility(View.GONE);
                }
            });
        }
    }
}
