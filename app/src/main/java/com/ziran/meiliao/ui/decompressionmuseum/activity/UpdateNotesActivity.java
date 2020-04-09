package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.NoteResultBean;
import com.ziran.meiliao.ui.bean.NotesQuesBean;
import com.ziran.meiliao.ui.bean.UserNoteBean;
import com.ziran.meiliao.ui.decompressionmuseum.contract.TakeNotesContract;
import com.ziran.meiliao.ui.decompressionmuseum.presenter.TakeNotesPresenter;
import com.ziran.meiliao.ui.decompressionmuseum.util.NotesUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.CustomNotesView;
import com.ziran.meiliao.widget.ninegridimageview.NineGridImageView;
import com.ziran.meiliao.widget.ninegridimageview.NineGridImageViewAdapter;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

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

public class UpdateNotesActivity extends CommonHttpActivity<TakeNotesPresenter, CommonModel> implements TakeNotesContract.View {

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_take_notes;
    }

    private String hisId;
    private UserNoteBean.DataBean dataBean;

    @Override
    public void initView() {
        try {
            Bundle extras = getIntent().getExtras();
            hisId = extras.getString("hisId");
            dataBean = extras.getParcelable(AppConstant.ExtraKey.BEAN);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText("愉悦体验日历详情");
        ViewUtil.setText(tvTakeNotesNow, TimeUtil.getStringByFormat(System.currentTimeMillis(), "yyyy年MM月dd日 HH:mm"));
        mNineGridImageView.setAdapter(mAdapter);
        ivTakeNotesPickPhoto.setVisibility(View.GONE);
        mPresenter.getNoteQues(ApiKey.PRACTICE_GET_NOTE_QUES, MapUtils.getDefMap(false));
    }

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
                    if (EmptyUtils.isEmpty(contents) ){
                        showShortToast("笔记内容不能为空");
                        return;
                    }
                    String toJson = JsonUtils.toJson(arr);
                    final Map<String, String> json = MapUtils.getOnlyCan("json", toJson);
                    json.put("noteId", hisId);
                    startProgressDialog("正在修改笔记");
                    mPresenter.postPractice(ApiKey.PRACTICE_UPDATE_NOTE, json);
                } else {
                    canEdit(true);
                }
                break;
        }
    }


    boolean isUpdatePic;
    private List<CustomNotesView> mCustomNotesViews;

    @Override
    public void practiceResult(NoteResultBean.DataBean result) {
        stopProgressDialog();
        isUpdatePic = false;
        showShortToast("笔记修改成功");
        WpyxConfig.setUpdateNotes(true);
        canEdit(false);
    }

    @Override
    public void practiceUploadResult(Result result) {

    }

    public void canEdit(boolean editable) {
        for (int i = 0; i < mCustomNotesViews.size(); i++) {
            mCustomNotesViews.get(i).setEnabled(editable);
            mCustomNotesViews.get(i).getEtContent().setEnabled(editable);
        }
        tvTakeNotesFinish.setText(editable?"完成":"编辑");

    }

    @Override
    public void showNoteQues(NotesQuesBean registerBean) {
        mCustomNotesViews = new ArrayList<>();
        List<NotesQuesBean.DataBean> beanList = registerBean.getData();
        for (NotesQuesBean.DataBean dataBean : beanList) {
            CustomNotesView customNotesView1 = new CustomNotesView(this);
            customNotesView1.setTitle(dataBean.getTitle());
            if (setContent(customNotesView1, dataBean)) {
                customNotesView1.showContentView(View.VISIBLE);
            }
            customNotesView1.setOnClickListener(mNotesViewClickListener);
            customNotesView1.setTag(dataBean.getId());
            llTakeNotesQues.addView(customNotesView1);
            mCustomNotesViews.add(customNotesView1);
        }
        if (EmptyUtils.isNotEmpty(dataBean)) {
            canEdit(false);
            imgPaths = (ArrayList<String>) dataBean.getPics();
            mNineGridImageView.setImagesData(dataBean.getPics());
        }
    }

    @Override
    public void showError(String result) {
        stopProgressDialog();
        showShortToast(result);
    }

    private Map<Integer, String> ques;

    private boolean setContent(CustomNotesView customNotesView1, NotesQuesBean.DataBean bean) {
        if (EmptyUtils.isNotEmpty(dataBean)) {
            if (EmptyUtils.isEmpty(ques)) {
                List<UserNoteBean.DataBean.QuesListBean> quesList = dataBean.getQuesList();
                ques = new HashMap<>();
                for (UserNoteBean.DataBean.QuesListBean quesListBean : quesList) {
                    ques.put(quesListBean.getQuesId(), quesListBean.getContent());
                }
            }
            String s = ques.get(bean.getId());
            if (EmptyUtils.isEmpty(s)) {
                return false;
            } else {
                customNotesView1.setContent(s);
                return true;
            }
        } else {
            return false;
        }
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
    ArrayList<String> imgPaths;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImgSelConfig.RequestCode && data != null) {
            imgPaths = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (imgPaths != null && imgPaths.size() > 0) {
                isUpdatePic = true;
                mNineGridImageView.setImagesData(imgPaths);
            }
        }
    }

    private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>(false) {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s) {
            ImageLoaderUtils.display(context, imageView, s, R.mipmap.ic_loading_rectangle);
        }
        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
            NotesUtil.startPhotoActivity(UpdateNotesActivity.this, imageView, index, mNineGridImageView.getLocations(), imgPaths);
        }
    };


}
