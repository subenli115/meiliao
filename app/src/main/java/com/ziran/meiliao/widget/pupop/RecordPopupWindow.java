package com.ziran.meiliao.widget.pupop;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.widget.SoundRecorder;
import com.ziran.meiliao.widget.VoiceLineView;
import com.ziran.meiliao.widget.VoiceManager;


/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/11 15:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/11$
 * @updateDes ${TODO}
 */

public class RecordPopupWindow extends BasePopupWindow {


    VoiceLineView lwvv;
    ImageView ivRecord,ivCancel,ivSave;
    TextView tvRecord;
    private SoundRecorder mSoundRecorder;
    private EnRecordVoiceListener enRecordVoiceListener;
    private int count;
    private boolean isAlive;
    private VoiceManager voiceManager;
    private TextView tvTime;
    private AutoLinearLayout allCancle,allSave;
    private String mFilePath;
    private String mStrLength;


    public RecordPopupWindow(Context context) {
        super(context);
        mSoundRecorder = new SoundRecorder();
    }
        public  void stop(){
            voiceManager.stopRecordAndPlay();
            tvRecord.setText("点击录音");
            tvTime.setText("00:00");
            tvTime.setTextColor(Color.parseColor("#B4C8E0"));
            ivRecord.setImageResource(R.mipmap.icon_record_start);
        }

    @Override
    protected void initViews(View contentView) {
        super.initViews(contentView);
        touchDismiss(R.id.touch_outside);
        lwvv = getView(R.id.voicLine);
        ivRecord = getView(R.id.iv_record);
        tvRecord = getView(R.id.tv_record);
        ivCancel = getView(R.id.iv_cancel);
        allCancle = getView(R.id.all_cancle);
        allSave = getView(R.id.all_save);
        ivSave = getView(R.id.iv_save);
        tvTime = getView(R.id.tv_time);
        voiceManager =VoiceManager.getInstance(mContext);
        setOnClickListener(R.id.iv_record);
        setOnClickListener(R.id.iv_save);
        setOnClickListener(R.id.iv_cancel);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.popup_record_pay;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_record:
                if (tvRecord.getText().equals("点击录音")) {
                    openMicrophone();
                } else if(tvRecord.getText().equals("点击停止")){
                    stopMicrophone();
                }else{
                    ImageView iv = (ImageView) v;
                    tvTime.setTextColor(Color.parseColor("#474766"));
                    voiceManager.setVoicePlayListener(new VoiceManager.VoicePlayCallBack() {
                        @Override
                        public void voiceTotalLength(long time, String strTime) {
                        }

                        @Override
                        public void playDoing(long time, String strTime) {
                            tvTime.setText(strTime);
                        }

                        @Override
                        public void playPause() {
                            iv.setImageResource(R.mipmap.icon_record_play_two);
                            tvRecord.getText().equals("播放");
                        }

                        @Override
                        public void playStart() {
                            iv.setImageResource(R.mipmap.icon_record_pause_two);
                            tvRecord.getText().equals("暂停");
                        }

                        @Override
                        public void playFinish() {
                            tvTime.setText("00:00");
                            iv.setImageResource(R.mipmap.icon_record_play_two);
                        }
                    });
                    voiceManager.continueOrPausePlay(mFilePath);
                }
                break;
            case R.id.iv_save:
                if (enRecordVoiceListener != null) {
                    enRecordVoiceListener.onFinishRecord( mStrLength, mFilePath);
                }
                dismiss();
                break;
            case R.id.iv_cancel:
                dismiss();
                break;

        }
    }


    private void openMicrophone() {
        voiceManager.setVoiceRecordListener(new VoiceManager.VoiceRecordCallBack() {
            @Override
            public void recDoing(long time, String strTime) {
                tvTime.setText(strTime);
                tvTime.setTextColor(Color.parseColor("#474766"));
            }

            @Override
            public void recVoiceGrade(int grade) {
                lwvv.setVolume(grade);
            }

            @Override
            public void recStart(boolean init) {
                tvRecord.setText("点击停止");
                ivRecord.setImageResource(R.mipmap.icon_record_stop);
                lwvv.setContinue();
            }

            @Override
            public void recPause(String str) {
            }


            @Override
            public void recFinish(long length, String strLength, String path) {
                tvTime.setText("00:00");
                mFilePath=path;
                mStrLength=strLength;
                allCancle.setVisibility(View.VISIBLE);
                allSave.setVisibility(View.VISIBLE);
                tvTime.setTextColor(Color.parseColor("#B4C8E0"));
                ivRecord.setImageResource(R.mipmap.icon_record_play_two);
                tvRecord.setText("播放");
            }
        });
        voiceManager.startVoiceRecord(Environment.getExternalStorageDirectory().getPath()+"/VoiceManager/audio");
    }

    private void stopMicrophone() {
        if(voiceManager!=null){
            voiceManager.stopVoiceRecord();
        }
        lwvv.setVisibility(View.GONE);
    }



    /**
     * 结束回调监听
     */
    public interface EnRecordVoiceListener {
        void onFinishRecord( String strLength, String filePath);
    }

    public void setOnFinish(EnRecordVoiceListener enRecordVoiceListener) {
        this.enRecordVoiceListener=enRecordVoiceListener;

    }

}