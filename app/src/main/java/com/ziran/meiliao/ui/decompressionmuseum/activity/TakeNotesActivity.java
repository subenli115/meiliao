package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.Luban;
import com.ziran.meiliao.common.compressorutils.OnCompressListener;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.NoteResultBean;
import com.ziran.meiliao.ui.bean.NotesQuesBean;
import com.ziran.meiliao.ui.decompressionmuseum.contract.TakeNotesContract;
import com.ziran.meiliao.ui.decompressionmuseum.presenter.TakeNotesPresenter;
import com.ziran.meiliao.ui.decompressionmuseum.util.NotesUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.CustomNotesView;
import com.ziran.meiliao.widget.ninegridimageview.NineGridImageView;
import com.ziran.meiliao.widget.ninegridimageview.NineGridImageViewAdapter;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 做笔记页面
 * Created by Administrator on 2017/3/4.
 */

public class TakeNotesActivity extends CommonHttpActivity<TakeNotesPresenter, CommonModel> implements TakeNotesContract.View {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.tv_take_notes_now)
    TextView tvTakeNotesNow;
    @Bind(R.id.iv_take_notes_pick_photo)
    ImageView ivTakeNotesPickPhoto;
    @Bind(R.id.tv_take_notes_finish)
    TextView tvTakeNotesFinish;
    @Bind(R.id.ll_take_notes_ques)
    LinearLayout llTakeNotesQues;
    @Bind(R.id.ngl_images)
    NineGridImageView<String> mNineGridImageView;
    private Map<String, String> json;
    private ArrayList<String> imgPaths;
    private List<String> newImgPaths;
    private int count = 0;
    private List<CustomNotesView> mCustomNotesViews;
    private String hisId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_take_notes;
    }

    @Override
    public void initView() {
        try {
            Bundle extras = getIntent().getExtras();
            hisId = extras.getString("hisId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        newImgPaths = new ArrayList<>();
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText("愉悦体验日历");
        ViewUtil.setText(tvTakeNotesNow, TimeUtil.getStringByFormat(System.currentTimeMillis(), "yyyy年MM月dd日 HH:mm"));
        mNineGridImageView.setAdapter(mAdapter);
        mPresenter.getNoteQues(ApiKey.PRACTICE_GET_NOTE_QUES, MapUtils.getDefMap(false));
    }

    public NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>(true) {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s) {
            ImageLoaderUtils.display(context, imageView, s, R.mipmap.ic_loading_rectangle);
        }

        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
            startPhotoActivity(context, imageView, index);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (imgPaths != null && imgPaths.size() > 0) {
                mNineGridImageView.setImagesData(imgPaths);
            }
        }
    }


    public void startPhotoActivity(Context context, ImageView imageView, int index) {
        NotesUtil.startPhotoActivity(TakeNotesActivity.this, imageView, index, mNineGridImageView.getLocations(), imgPaths);
    }

    public View.OnClickListener mNotesViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (CustomNotesView notesView : mCustomNotesViews) {
                if (EmptyUtils.isNotEmpty(notesView.getContent())) {
                    notesView.showContentView(View.VISIBLE);
                } else {
                    if (notesView == v) {
                        notesView.showContentView(View.VISIBLE);
                    } else {
                        notesView.showContentView(View.GONE);
                    }
                }
                KeyBordUtil.showSoftKeyboard(((CustomNotesView) v).getEtContent());
            }
        }
    };


    @OnClick({R.id.iv_take_notes_pick_photo, R.id.tv_take_notes_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_take_notes_pick_photo:
                ImgSelActivity.startActivity(this, ImgSelConfig.DEFAULT_NULTI_NICE, ImgSelConfig.RequestCode);
                break;
            case R.id.tv_take_notes_finish:
                String finishText = tvTakeNotesFinish.getText().toString();
                if ("完成".equals(finishText)) {
                    List arr = new ArrayList();
                    Map<String, String> contents = null;
                    for (CustomNotesView notesView : mCustomNotesViews) {
                        if (EmptyUtils.isNotEmpty(notesView.getContent())) {
                            contents = new HashMap<>();
                            contents.put("quesId", notesView.getTag().toString());
                            contents.put("content", notesView.getContent());
                            arr.add(contents);
                        }
                    }
                    if (EmptyUtils.isEmpty(contents) && EmptyUtils.isEmpty(imgPaths)){
                        showShortToast("笔记内容不能为空");
                        return;
                    }
                    String toJson = JsonUtils.toJson(arr);
                    json = MapUtils.getOnlyCan("json", toJson);
                    json.put("hisId", hisId);
                    mPresenter.postPractice(ApiKey.PRACTICE_WRITE_NOTE, json);
                    startProgressDialog("正在上传笔记");

                } else {
                    canEdit(true);
                }
                break;
        }
    }

    private void compress(ArrayList<String> imgPaths) {
        if (EmptyUtils.isEmpty(imgPaths)) return;
        newImgPaths.clear();
        count = 0;
        for (int i = 0; i < imgPaths.size(); i++) {
            String imgPath = imgPaths.get(i);
            Luban.get(TakeNotesActivity.this).load(new File(imgPath)).putGear(Luban.THIRD_GEAR).setCompressListener(mOnCompressListener)
                    .launch();
        }
    }

    private OnCompressListener mOnCompressListener = new OnCompressListener() {
        @Override
        public void onStart() {
        }
        @Override
        public void onSuccess(File file) {
            newImgPaths.add(file.getAbsolutePath());
            count++;
            if (count == imgPaths.size()) {
                mPresenter.postPracticeImg(ApiKey.PRACTICE_UPLOAD_NOTE_IMGS, json, newImgPaths);
            }
        }
        @Override
        public void onError(Throwable e) {
        }
    };

    @Override
    public void practiceResult(NoteResultBean.DataBean result) {
        if (EmptyUtils.isNotEmpty(imgPaths)){
            json.put("noteId",result.getNoteId());
            compress(imgPaths);
        }else{
            practiceUploadResult( null);
        }
    }

    @Override
    public void practiceUploadResult(Result result) {
        stopProgressDialog();
        showShortToast("笔记上传成功");
        Luban.get(this).cleanCache();
        canEdit(false);
        finish();
    }

    public void canEdit(boolean editable) {
        for (int i = 0; i < mCustomNotesViews.size(); i++) {
            mCustomNotesViews.get(i).setEnabled(editable);
            mCustomNotesViews.get(i).getEtContent().setEnabled(editable);
        }
        if (editable) {
            tvTakeNotesFinish.setText("完成");
            ivTakeNotesPickPhoto.setVisibility(View.VISIBLE);
        } else {
            tvTakeNotesFinish.setText("编辑");
            ivTakeNotesPickPhoto.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNoteQues(NotesQuesBean registerBean) {
        mCustomNotesViews = new ArrayList<>();
        List<NotesQuesBean.DataBean> beanList = registerBean.getData();
        for (NotesQuesBean.DataBean dataBean : beanList) {
            CustomNotesView customNotesView1 = new CustomNotesView(this);
            customNotesView1.setTitle(dataBean.getTitle());
            customNotesView1.setOnClickListener(mNotesViewClickListener);
            customNotesView1.setTag(dataBean.getId());
            llTakeNotesQues.addView(customNotesView1);
            mCustomNotesViews.add(customNotesView1);
        }
    }

    @Override
    public void showError(String result) {
        stopProgressDialog();
        showShortToast(result);
    }
}
