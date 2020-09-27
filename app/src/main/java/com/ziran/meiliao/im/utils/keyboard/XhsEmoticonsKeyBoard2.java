package com.ziran.meiliao.im.utils.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.utils.keyboard.adpater.PageSetAdapter;
import com.ziran.meiliao.im.utils.keyboard.data.PageSetEntity;
import com.ziran.meiliao.im.utils.keyboard.utils.EmoticonsKeyboardUtils;
import com.ziran.meiliao.im.utils.keyboard.widget.AutoHeightLayout;
import com.ziran.meiliao.im.utils.keyboard.widget.EmoticonsEditText;
import com.ziran.meiliao.im.utils.keyboard.widget.EmoticonsFuncView;
import com.ziran.meiliao.im.utils.keyboard.widget.EmoticonsIndicatorView;
import com.ziran.meiliao.im.utils.keyboard.widget.EmoticonsToolBarView;
import com.ziran.meiliao.im.utils.keyboard.widget.FuncLayout;
import com.ziran.meiliao.im.view.RecordVoiceButton;
import com.ziran.meiliao.ui.bean.CommentListBean;
import com.ziran.meiliao.ui.bean.SpaceDetailBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewCacheUtil;

import java.util.ArrayList;
import java.util.Map;

import static com.ziran.meiliao.constant.AppConstant.RXTag.SUBSCRIBE_UPDATE;

public class XhsEmoticonsKeyBoard2 extends AutoHeightLayout implements View.OnClickListener, EmoticonsFuncView.OnEmoticonsPageViewListener,
        EmoticonsToolBarView.OnToolBarItemClickListener, EmoticonsEditText.OnBackKeyClickListener, FuncLayout.OnFuncChangeListener {

    public static final int FUNC_TYPE_EMOTION = -1;
    public static final int FUNC_TYPE_APPPS = -2;

    protected LayoutInflater mInflater;

    protected ImageView mBtnVoiceOrText;
    protected EmoticonsEditText mEtChat;
    protected ImageView mBtnFace;
    protected RelativeLayout mRlInput;
    protected ImageView mBtnMultimedia;
    protected Button mBtnSend;
    protected FuncLayout mLyKvml;

    protected EmoticonsFuncView mEmoticonsFuncView;
    protected EmoticonsIndicatorView mEmoticonsIndicatorView;
    protected EmoticonsToolBarView mEmoticonsToolBarView;

    protected boolean mDispatchKeyEventPreImeLock = false;
    private boolean flag=true;
    private RelativeLayout rlFunction;
    private int mLevel;
    private TextView tvLike,tvComment;
    private AutoLinearLayout allLike,allComment;
    private ImageView ivLike;
    private SpaceDetailBean.DataBean mData;
    private RxManager mRxManager;
    private boolean isClick;
    private int clickNum;

    public XhsEmoticonsKeyBoard2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflateKeyboardBar();
        initView();
        initFuncView();
    }

    protected void inflateKeyboardBar() {
        mInflater.inflate(R.layout.item_comment_bottom, this);
    }

    protected View inflateFunc() {
        return mInflater.inflate(R.layout.view_func_emoticon, null);
    }

    protected void initView() {
        mEtChat = ((EmoticonsEditText) findViewById(R.id.et_chat));
        mBtnFace = ((ImageView) findViewById(R.id.btn_face));
        ivLike = ((ImageView) findViewById(R.id.iv_like));
        mRlInput = ((RelativeLayout) findViewById(R.id.rl_input));
        mBtnMultimedia = ((ImageView) findViewById(R.id.btn_multimedia));
        mBtnSend = ((Button) findViewById(R.id.btn_send));
        mLyKvml = ((FuncLayout) findViewById(R.id.ly_kvml));
        rlFunction = ((RelativeLayout) findViewById(R.id.rl_function));
//        mBtnVoiceOrText.setOnClickListener(this);
        mBtnFace.setOnClickListener(this);
        mBtnMultimedia.setOnClickListener(this);
        mEtChat.setOnBackKeyClickListener(this);
    }

    public void setLevel(int level){
             mLevel = level;
    }

    protected void initFuncView() {
        initEmoticonFuncView();
        initEditView();
    }

    protected void initEmoticonFuncView() {
        View keyboardView = inflateFunc();
        mLyKvml.addFuncView(FUNC_TYPE_EMOTION, keyboardView);
        allComment = ((AutoLinearLayout) findViewById(R.id.all_comment));
        allLike = ((AutoLinearLayout) findViewById(R.id.all_like));
        tvComment = ((TextView) findViewById(R.id.tv_comment));
        tvLike = ((TextView) findViewById(R.id.tv_like));
        mEmoticonsFuncView = ((EmoticonsFuncView) findViewById(R.id.view_epv));
        mEmoticonsIndicatorView = ((EmoticonsIndicatorView) findViewById(R.id.view_eiv));
        mEmoticonsToolBarView = ((EmoticonsToolBarView) findViewById(R.id.view_etv));
        mEmoticonsFuncView.setOnIndicatorListener(this);
        mEmoticonsToolBarView.setOnToolBarItemClickListener(this);
        mLyKvml.setOnFuncChangeListener(this);
        allLike.setOnClickListener(this);
    }

    public void initData(SpaceDetailBean.DataBean data, RxManager mRxManager){
        mData=data;
        this.mRxManager=mRxManager;
        clickNum = data.getClickNum();
        tvLike.setText(clickNum+"");
            if(data.getIsClick().equals("1")){
                ivLike.setImageResource(R.mipmap.icon_bottom_islike);
                isClick=true;
                tvLike.setTextColor(Color.parseColor("#F9636A"));
            }
    }

    public void setNum(int comment){
        tvComment.setText(comment+"");
    }

    protected void initEditView() {
        mEtChat.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!mEtChat.isFocused()) {
                    mEtChat.setFocusable(true);
                    mEtChat.setFocusableInTouchMode(true);
                }
                return false;
            }
        });

        mEtChat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    allComment.setVisibility(GONE);
                    allLike.setVisibility(GONE);
                    mBtnSend.setVisibility(VISIBLE);
                    mBtnMultimedia.setVisibility(GONE);
                    mBtnFace.setVisibility(VISIBLE);
                    mBtnSend.setBackgroundResource(R.drawable.normal_bg_bule);
                } else {
                    if(flag){
                        mBtnMultimedia.setVisibility(VISIBLE);
                    }
                    mBtnSend.setVisibility(GONE);
                    mBtnFace.setVisibility(GONE);
                    allComment.setVisibility(VISIBLE);
                    allLike.setVisibility(VISIBLE);
                }
            }
        });
    }

    public void setAdapter(PageSetAdapter pageSetAdapter) {
        if (pageSetAdapter != null) {
            ArrayList<PageSetEntity> pageSetEntities = pageSetAdapter.getPageSetEntityList();
            if (pageSetEntities != null) {
                for (PageSetEntity pageSetEntity : pageSetEntities) {
                    mEmoticonsToolBarView.addToolItemView(pageSetEntity);
                }
            }
        }
        mEmoticonsFuncView.setAdapter(pageSetAdapter);
    }

    public void addFuncView(View view) {
        mLyKvml.addFuncView(FUNC_TYPE_APPPS, view);
    }

    public void reset() {
        EmoticonsKeyboardUtils.closeSoftKeyboard(this);
        mLyKvml.hideAllFuncView();
        mBtnFace.setImageResource(R.drawable.icon_face_nomal);
    }



    protected void showText() {
        mRlInput.setVisibility(VISIBLE);
    }

    protected void toggleFuncView(int key) {
        showText();
        mLyKvml.toggleFuncView(key, isSoftKeyboardPop(), mEtChat);
    }

    @Override
    public void onFuncChange(int key) {
        if (FUNC_TYPE_EMOTION == key) {
            mBtnFace.setImageResource(R.drawable.icon_face_pop);
        } else {
            mBtnFace.setImageResource(R.drawable.icon_face_nomal);
        }
    }


    @Override
    public void onSoftKeyboardHeightChanged(int height) {
        mLyKvml.updateHeight(height);
    }


    @Override
    public void OnSoftPop(int height) {
        super.OnSoftPop(height);
        mLyKvml.setVisibility(true);
        onFuncChange(mLyKvml.DEF_KEY);
    }

    @Override
    public void OnSoftClose() {
        super.OnSoftClose();
        if (mLyKvml.isOnlyShowSoftKeyboard()) {
            reset();
        } else {
            onFuncChange(mLyKvml.getCurrentFuncKey());
        }
    }

    public void addOnFuncKeyBoardListener(FuncLayout.OnFuncKeyBoardListener l) {
        mLyKvml.addOnKeyBoardListener(l);
    }

    @Override
    public void emoticonSetChanged(PageSetEntity pageSetEntity) {
        mEmoticonsToolBarView.setToolBtnSelect(pageSetEntity.getUuid());
    }

    @Override
    public void playTo(int position, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playTo(position, pageSetEntity);
    }

    @Override
    public void playBy(int oldPosition, int newPosition, PageSetEntity pageSetEntity) {
        mEmoticonsIndicatorView.playBy(oldPosition, newPosition, pageSetEntity);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_face) {
            toggleFuncView(FUNC_TYPE_EMOTION);
        } else if (i == R.id.btn_multimedia) {

            toggleFuncView(FUNC_TYPE_APPPS);
        }else if(i==R.id.all_like){
            if(isClick){
                ivLike.setImageResource(R.mipmap.icon_bottom_like);
                tvLike.setTextColor(Color.parseColor("#474766"));
                clickNum--;
                tvLike.setText(clickNum+"");
                isClick=false;
            }else {
                ivLike.setImageResource(R.mipmap.icon_bottom_islike);
                tvLike.setTextColor(Color.parseColor("#F9636A"));
                clickNum++;
                tvLike.setText(clickNum+"");
                isClick=true;
            }
            Map<String, String> defMap = MapUtils.getDefMap(true);
            defMap.put("clickContentId",mData.getId());
            defMap.put("userId", MyAPP.getUserId());
            defMap.put("type","0");

            OkHttpClientManager.postAsyncAddHead(ApiKey.ADMIN_USERCLICK_ADD, defMap, "", new
                    NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {

                        @Override
                        public void onSuccess(StringDataV2Bean listBean) {
                            NewCacheUtil newCacheUtil = new NewCacheUtil(mContext);
                            if(listBean.getData()){
                                mData.setIsClick("1");
                            }else {
                                mData.setIsClick("0");
                            }
                            mData.setClickNum(clickNum);
                            newCacheUtil.saveSpaceDeatilBean(mData,mData.getId());
                            mRxManager.post(SUBSCRIBE_UPDATE,true);
                        }

                        @Override
                        public void onError(String msg, int code) {
                            ToastUitl.showShort(msg);
                        }
                    });

        }
    }



    @Override
    public void onToolBarItemClick(PageSetEntity pageSetEntity) {
        mEmoticonsFuncView.setCurrentPageSet(pageSetEntity);
    }

    @Override
    public void onBackKeyClick() {
        if (mLyKvml.isShown()) {
            mDispatchKeyEventPreImeLock = true;
            reset();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                if (mDispatchKeyEventPreImeLock) {
                    mDispatchKeyEventPreImeLock = false;
                    return true;
                }
                if (mLyKvml.isShown()) {
                    reset();
                    return true;
                } else {
                    return super.dispatchKeyEvent(event);
                }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (EmoticonsKeyboardUtils.isFullScreen((Activity) getContext())) {
            return false;
        }
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        if (EmoticonsKeyboardUtils.isFullScreen((Activity) getContext())) {
            return;
        }
        super.requestChildFocus(child, focused);
    }

    public boolean dispatchKeyEventInFullScreen(KeyEvent event) {
        if (event == null) {
            return false;
        }
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
                if (EmoticonsKeyboardUtils.isFullScreen((Activity) getContext()) && mLyKvml.isShown()) {
                    reset();
                    return true;
                }
            default:
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    boolean isFocused;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        isFocused = mEtChat.getShowSoftInputOnFocus();
                    } else {
                        isFocused = mEtChat.isFocused();
                    }
                    if (isFocused) {
                        mEtChat.onKeyDown(event.getKeyCode(), event);
                    }
                }
                return false;
        }
    }

    public EmoticonsEditText getEtChat() {
        return mEtChat;
    }


    public Button getBtnSend() {
        return mBtnSend;
    }
    public void setBtnMultimedia() {
        flag=false;
        mBtnMultimedia.setVisibility(GONE);
    }
}
