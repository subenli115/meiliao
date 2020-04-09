package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.NewDecompressionmuseum.adapter.PracticeQuestionAdapter;
import com.ziran.meiliao.ui.NewDecompressionmuseum.asrfinishjson.AsrFinishJsonData;
import com.ziran.meiliao.ui.NewDecompressionmuseum.asrpartialjson.AsrPartialJsonData;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeFourContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeFourPresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeFourCheckBean;
import com.ziran.meiliao.ui.bean.PracticeFourDetailBean;
import com.ziran.meiliao.ui.bean.PracticeSaveBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BottomMbsrView;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_DetailFOURCheck;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_DetailFOURSAVE;


public class WeekQuestionActivity extends CommonHttpActivity<PracticeFourPresenter, CommonModel> implements PracticeFourContract.View  , PracticeQuestionAdapter.SaveEditListener , EventListener {
    private static Boolean mIsFinish;
    private static int mpracticeStatus;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv_big_title)
    TextView tv_big_title;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.tv_right)
    TextView tvRight;

    private String itemId;
    private String id;
    private PracticeQuestionAdapter mAdapter;
    Map<Integer, String> map = new HashMap<>();
    private EventManager asr;
    private List<PracticeFourCheckBean.DataBean.AnswerListBean> answerList;
    private String final_result;
    private ArrayList<Boolean> list;
    private int mPosition;
    private  JdxShareBean.DataBean mBean;
    @Bind(R.id.bottom_exercise_view)
    BottomMbsrView mBottomExerciseView;
    private int REQUESTCODE=999;
    private boolean mRefresh;
    private Map<String, String> statusMap;
    private InputMethodManager imm;
    private boolean isEdit=true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_happy_calendar;
    }

    public static void startAction(Context context, String id, String itemId, JdxShareBean.DataBean result, Boolean isFinish, int practiceStatus) {
        Intent intent = new Intent(context, WeekQuestionActivity.class);
        intent.putExtra("id",id);
        Bundle bundle = new Bundle();
        bundle.putParcelable("JdxShareBean", result);
        intent.putExtras(bundle);
        intent.setExtrasClassLoader(JdxShareBean.class.getClassLoader());
        mpracticeStatus=practiceStatus;
        mIsFinish=isFinish;
        intent.putExtra("itemId",itemId);
        context.startActivity(intent);
    }



    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }
    @Override
    public void initView() {
        if(getIntent()!=null){
            Intent intent = getIntent();
            id = intent.getStringExtra("id");
            itemId = intent.getStringExtra("itemId");
            mBean = intent.getExtras().getParcelable("JdxShareBean");
        }

        initPermission();
        asr = EventManagerFactory.create(this, "asr");
        asr.registerListener(this); //  EventListener 中 onEvent方法
        map.clear();
        final Map<String, String> saveMap = MapUtils.getDefMap(true);
        final Map<String, String> fourMap = MapUtils.getDefMap(true);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setTvLeftVisiable(true, true);
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setOnivBackImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRefresh){
                    setResult(2, new Intent());
                }
                finish();
            }
        });
        if(mIsFinish){
            tvRight.setVisibility(View.VISIBLE);
            isEdit=false;
            tvSave.setText("分享");
        }
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tvSave.getText().toString().equals("保存")){
                    tvRight.setVisibility(View.VISIBLE);
                    //遍历处理map存储的内容
                    saveMap.put("id",id);
                    saveMap.put("itemId",itemId);
                    saveMap.put("answerJson",getJSONArrayByList(answerList).toString());
                    mPresenter.getPracticeFourSaveData(PRACTIEACTIVITY_DetailFOURSAVE,saveMap);
                    mAdapter = new PracticeQuestionAdapter(answerList,list, mContext,false,false);
                    recyclerView.setAdapter(mAdapter);
                    tvSave.setText("分享 ");
                }else {
                    isFinish();

                }
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.updateDataForEdit(mContext,true,true);
                tvSave.setText("保存");
                tvRight.setVisibility(View.INVISIBLE);
            }
        });

        fourMap.put("id",id);
        fourMap.put("itemId",itemId);
        statusMap = MapUtils.getDefMap(true);
        statusMap.put("id",id);
        statusMap.put("itemId",itemId+"");
        statusMap.put("status","1");
        mPresenter.getPracticeFourCheckData(PRACTIEACTIVITY_DetailFOURCheck,fourMap);
    }




    @Override
    public void SaveEdit(int position, String string) {
        //回调处理edittext内容，使用map的好处在于：position确定的情况下，string改变，只会动态改变string内容
        map.put(position,string);
        answerList.get(position).setAnswerDetail(string);
    }

    @Override
    public void showFourData(PracticeFourDetailBean.DataBean result) {

    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {

    }

    @Override
    public void showFourSaveData(PracticeSaveBean result) {
            isFinish();
    }

    @Override
    public void showFourCheckData(PracticeFourCheckBean.DataBean result) {
        ntbTitle.setTitleText(result.getItemTitle());
        tv_big_title.setText("对一个"+result.getItemTitle()+"进行觉察");
        Glide.with(mContext).load(result.getBgPic()).error(R.mipmap.jdx_bg_happy).into(ivBg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         list = new ArrayList<>();
        for(int i=0;i<result.getAnswerList().size();i++){
            list.add(false);
        }
        answerList = result.getAnswerList();
        mAdapter = new PracticeQuestionAdapter(answerList,list, mContext,isEdit,true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new PracticeQuestionAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, EditText v) {
                  mPosition = position;
                if(mAdapter.getList().get(position)){
                    stop();
                }else {
                    start();
                }

            }

        });
    }

    /**
     * 根据List获取到对应的JSONArray
     * @param list
     * @return
     */

    public static JSONArray getJSONArrayByList(List<PracticeFourCheckBean.DataBean.AnswerListBean> list){
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        return array;
    }


    private void parseAsrPartialJsonData(String data) {
        Gson gson = new Gson();
        AsrPartialJsonData jsonData = gson.fromJson(data, AsrPartialJsonData.class);
        String resultType = jsonData.getResult_type();
        if(resultType != null && resultType.equals("final_result")){
            final_result = jsonData.getBest_result();

            if(final_result!=null&&final_result.length()>0){
//                mv.setText(final_result);
                map.put(mPosition,final_result);
                answerList.get(mPosition).setAnswerDetail(final_result);
                mAdapter.notifyItemChanged(mPosition);
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
            recyclerView.setEnabled(true);
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
        recyclerView.setEnabled(false);
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
    protected void onDestroy() {
        super.onDestroy();
        if(asr!=null){
            asr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
        }
    }


    private void isFinish() {
        if (EmptyUtils.isNotEmpty(mBean)) {
            mBottomExerciseView.setId(id+"");
            mBottomExerciseView.setItemId(itemId);
            mBottomExerciseView.setUserHead(mBean.getUserPic(),mBean.getPicture());
            mBottomExerciseView.setTitleText(mBean.getDuration() ,mBean.getWords(),mBean.getTitle());
            mBottomExerciseView.setHisId(mBean.getHisId()+"");
            mBottomExerciseView.setTimeShow();
            mBottomExerciseView.setShow(true);
            mBottomExerciseView.setOnCloseListener(new BottomMbsrView.OnCloseListener() {
                @Override
                public void onClose() {
                    if(mIsFinish){
                        finish();
                    }else {
                        mBottomExerciseView.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

}
