package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.NewDecompressionmuseum.asrfinishjson.AsrFinishJsonData;
import com.ziran.meiliao.ui.NewDecompressionmuseum.asrpartialjson.AsrPartialJsonData;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeThreeDataContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeThreeDataPresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.ui.bean.PracticeThreeBean;
import com.ziran.meiliao.ui.bean.PracticeThreeDetailCheckBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.CustomEditText;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_DetailThreeSave;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_STATUSUPDATE;

/**
 * 保存飲食記錄 2018/9/21.
 */

public class WeekDietSaveActivity  extends CommonHttpActivity<PracticeThreeDataPresenter, CommonModel> implements PracticeThreeDataContract.View , EventListener {

    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.tv_times)
    TextView tvTimes;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.nice_spinner_first)
    AppCompatSpinner niceSpinnerFirst;
    @Bind(R.id.et_content)
    CustomEditText etContent;
    @Bind(R.id.iv_bg)
    ImageView ivbg;

    @Bind(R.id.tv_voice)
    TextView tvVoice;
    private String itemId;
    private String id;
    private String timeSlot;
    private String amounts;
    private final static int REQUESTCODE = 1; // 返回的结果码
    private String bgpic;
    private EventManager asr;
    private String final_result;
    private boolean isStart=false;
    private String mpracticeStatus;
    private Map<String, String> statusMap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_diet_fill_cotent;
    }
    public static void startAction(Context context,String id,String itemId,String amounts) {
        Intent intent = new Intent(context, WeekDietSaveActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("itemId",itemId);
        intent.putExtra("amounts",amounts);
        context.startActivity(intent);
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    private void setBG(TextView tv_voice,Integer r) {
        Drawable nav_up=mContext.getResources().getDrawable(r);
        nav_up.setBounds(0,0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        tv_voice.setCompoundDrawables(nav_up,null,null,null);
    }
    @Override
    public void initView() {
        if(getIntent()!=null){
            Intent intent = getIntent();
             id = intent.getStringExtra("id");
            itemId = intent.getStringExtra("itemId");
            amounts = intent.getStringExtra("amounts");
             bgpic = intent.getStringExtra("bgpic");
            mpracticeStatus = intent.getStringExtra("mpracticeStatus");
        }
        etContent.setFocusable(true);
        etContent.setEnabled(true);
        etContent.setFocusableInTouchMode(true);
        etContent.setLongClickable(true);
        initPermission();
        asr = EventManagerFactory.create(this, "asr");
        asr.registerListener(this); //  EventListener 中 onEvent方法

        tvVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(isStart){
                        isStart=false;
                        stop();
                        setBG(tvVoice,R.mipmap.jdx_no_speak);
                        tvVoice.setText("点击使用语音输入");
                    }else{
                        isStart=true;
                        tvVoice.setText("输入中....说完了");
                        start();
                        setBG(tvVoice,R.mipmap.jdx_speak);
                    }
            }
        });
        Glide.with(mContext).load(bgpic).error(R.mipmap.jdx_bg_diet).into(ivbg);
        tvTimes.setText(amounts);
        final String s[]={"早餐","午餐","晚餐"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,android.R.id.text1,s);
        niceSpinnerFirst.setAdapter(adapter);
        niceSpinnerFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeSlot =(String) niceSpinnerFirst.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ntbTitle.setTitleWeizhi();
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setTvLeftVisiable(true, true);
        ntbTitle.setTitleText("正念饮食");
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, String> threeMap = MapUtils.getDefMap(true);
                threeMap.put("id",id);
                threeMap.put("itemId",itemId);
                threeMap.put("timeSlot", timeSlot);
                threeMap.put("detail", etContent.getText().toString());
                mPresenter.getPracticeThreeSaveData(PRACTIEACTIVITY_DetailThreeSave,threeMap);
            }
        });
    }

    private void parseAsrPartialJsonData(String data) {
        Gson gson = new Gson();
        AsrPartialJsonData jsonData = gson.fromJson(data, AsrPartialJsonData.class);
        String resultType = jsonData.getResult_type();
        if(resultType != null && resultType.equals("final_result")){
            final_result = jsonData.getBest_result();

            if(final_result!=null&&final_result.length()>0){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        etContent.setText(final_result);
                    }
                });
            }
        }
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm :permissions){
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                //进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()){
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                    AskForPermission();
                }
            }
        }

    }
    private void AskForPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Need Permission!");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().show();
    }
    /*
       * EventListener  回调方法
       * name:回调事件
       * params: JSON数据，其格式如下：
       *
       * */
    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        String result = "";

        if (length > 0 && data.length > 0) {
            result += ", 语义解析结果：" + new String(data, offset, length);
        }

        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
            // 引擎准备就绪，可以开始说话
            result += "引擎准备就绪，可以开始说话";

        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_BEGIN)) {
            // 检测到用户的已经开始说话
            result += "检测到用户的已经开始说话";

        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_END)) {
            // 检测到用户的已经停止说话
            result += "检测到用户的已经停止说话";
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            // 临时识别结果, 长语音模式需要从此消息中取出结果
            result += "识别临时识别结果";
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
            parseAsrPartialJsonData(params);
        } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)) {
            // 识别结束， 最终识别结果或可能的错误
            result += "识别结束";
            asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
            if (params != null && !params.isEmpty()) {
                result += "params :" + params + "\n";
            }
            parseAsrFinishJsonData(params);
        }
    }

    private void parseAsrFinishJsonData(String data) {
        Gson gson = new Gson();
        AsrFinishJsonData jsonData = gson.fromJson(data, AsrFinishJsonData.class);
        String desc = jsonData.getDesc();
        if(desc !=null && desc.equals("Speech Recognize success.")){
        }else{
            String errorCode = "\n错误码:" + jsonData.getError();
            String errorSubCode = "\n错误子码:"+ jsonData.getSub_error();
            String errorResult = errorCode + errorSubCode;
        }
    }
    private void start() {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        String event = null;
        event = SpeechConstant.ASR_START;
        params.put(SpeechConstant.DISABLE_PUNCTUATION, true);
        params.put(SpeechConstant.PID, 15362); // 默认1536
        params.put(SpeechConstant.DECODER, 0); // 纯在线(默认)
        params.put(SpeechConstant.VAD, SpeechConstant.VAD_TOUCH); // 语音活动检测
        params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 3000); // 不开启长语音。开启VAD尾点检测，即静音判断的毫秒数。建议设置800ms-3000ms
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);// 是否需要语音音量数据回调
        String json = null; //可以替换成自己的json
        json = new JSONObject(params).toString(); // 这里可以替换成你需要测试的json

        asr.send(event, json, null, 0, 0);
    }

    private void stop() {
        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0);
    }

    @Override
    public void showThreeData(PracticeThreeBean.DataBean result) {

    }

    @Override
    public void showThreeCheckData(PracticeThreeDetailCheckBean.DataBean result) {

    }

    @Override
    public void showThreeSaveData(PracticeSaveBean.DataBean result) {
        if(mpracticeStatus.equals("0")){
            statusMap = MapUtils.getDefMap(true);
            statusMap.put("id",id);
            statusMap.put("itemId",itemId+"");
            statusMap.put("status","1");
            mPresenter.changePracticeStatus(PRACTIEACTIVITY_STATUSUPDATE,statusMap);
        }
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("itemId", itemId);
        setResult(2, intent);
        finish();

    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {

    }
}