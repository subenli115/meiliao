package com.ziran.meiliao.im.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import com.opensource.svgaplayer.SVGAImageView;
import com.sj.emoji.EmojiBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.FileContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.LocationContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.event.CommandNotificationEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.MessageReceiptStatusChangeEvent;
import cn.jpush.im.android.api.event.MessageRetractEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.adapter.ChattingListAdapter;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.entity.Event;
import com.ziran.meiliao.im.entity.EventType;
import com.ziran.meiliao.im.location.activity.MapPickerActivity;
import com.ziran.meiliao.im.model.Constants;
import com.ziran.meiliao.im.pickerimage.PickImageActivity;
import com.ziran.meiliao.im.pickerimage.utils.Extras;
import com.ziran.meiliao.im.pickerimage.utils.RequestCode;
import com.ziran.meiliao.im.pickerimage.utils.SendImageHelper;
import com.ziran.meiliao.im.pickerimage.utils.StorageType;
import com.ziran.meiliao.im.pickerimage.utils.StorageUtil;
import com.ziran.meiliao.im.pickerimage.utils.StringUtil;
import com.ziran.meiliao.im.utils.CommonUtils;
import com.ziran.meiliao.im.utils.IdHelper;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.SimpleCommonUtils;
import com.ziran.meiliao.im.utils.ToastUtil;
import com.ziran.meiliao.im.utils.event.ImageEvent;
import com.ziran.meiliao.im.utils.imagepicker.bean.ImageItem;
import com.ziran.meiliao.im.utils.keyboard.XhsEmoticonsKeyBoard;
import com.ziran.meiliao.im.utils.keyboard.data.EmoticonEntity;
import com.ziran.meiliao.im.utils.keyboard.interfaces.EmoticonClickListener;
import com.ziran.meiliao.im.utils.keyboard.utils.EmoticonsKeyboardUtils;
import com.ziran.meiliao.im.utils.keyboard.widget.FuncLayout;
import com.ziran.meiliao.im.utils.photovideo.takevideo.CameraActivity;
import com.ziran.meiliao.im.view.ChatView;
import com.ziran.meiliao.im.view.FunctionGridView;
import com.ziran.meiliao.im.view.SimpleAppsGridView;
import com.ziran.meiliao.im.view.TipItem;
import com.ziran.meiliao.im.view.TipView;
import com.ziran.meiliao.im.view.listview.DropDownListView;
import com.ziran.meiliao.ui.bean.IntimacyBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.ui.settings.activity.IntputCodeActivity;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewCacheUtil;
import com.ziran.meiliao.widget.BannerUtil;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_GIFTRECORD_CHAT;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_INTIMACY_ADD;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_INTIMACY_INFO;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_USER_GETFROZEN;


/**`
 * Created by ${chenyn} on 2017/3/26.
 */

public class ChatActivity extends BaseActivity implements FuncLayout.OnFuncKeyBoardListener, View.OnClickListener {
    @Bind(R.id.lv_chat)
    DropDownListView lvChat;
    @Bind(R.id.ek_bar)
    XhsEmoticonsKeyBoard ekBar;
    public static final String JPG = ".jpg";
    private static String MsgIDs = "msgIDs";
    private static final String TAG = "ChatActivity";
    private String mTitle;
    private boolean mLongClick = false;

    private static final String MEMBERS_COUNT = "membersCount";
    private static final String GROUP_NAME = "groupName";

    public static final String TARGET_ID = "targetId";
    public static final String TARGET_APP_KEY = "targetAppKey";
    private static final String DRAFT = "draft";
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    public static final int REQUEST_CODE_SELECT = 100;
    private ChatView mChatView;
    private boolean mIsSingle = true;
    private Conversation mConv;
    private String mTargetId;
    private String mTargetAppKey;
    private Activity mContext;
    private ChattingListAdapter mChatAdapter;
    int maxImgCount = 9;
    private List<UserInfo> mAtList;
    private long mGroupId;
    private static final int REFRESH_LAST_PAGE = 0x1023;
    private static final int REFRESH_CHAT_TITLE = 0x1024;
    private static final int REFRESH_GROUP_NAME = 0x1025;
    private static final int REFRESH_GROUP_NUM = 0x1026;
    private Dialog mDialog;
    private boolean mHasShowDownloadActive = false;
    private GroupInfo mGroupInfo;
    private UserInfo mMyInfo;
    private static final String GROUP_ID = "groupId";
    private int mAtMsgId;
    private int mAtAllMsgId;
    private int mUnreadMsgCnt;
    private boolean mShowSoftInput = false;
    private List<UserInfo> forDel = new ArrayList<>();
    Window mWindow;
    InputMethodManager mImm;
    private final UIHandler mUIHandler = new UIHandler(this);
    private boolean mAtAll = false;
    private boolean isChatRoom = false;
    private View contentView;
    private String gold="0";
    private SimpleAppsGridView gridView;
    private RxManager mRxManager;
    private TextContent content;
    public SVGAImageView svgaImage;
    private ImageView ivHead,ivRealName;
    private TextView tvName,tvDistance,tvOther,tvContent;
    private ImageView ivRecommend;
    private TextView tvTag;
    private TextView tvUsername,tvAge;
    private ImageView ivSex,ivIntimacy;
    private AutoLinearLayout allAge;
    private CardView cardView;
    private int level;
    private FunctionGridView functionGridView;
    private UserBean.DataBean dataBean;
    private MaterialBanner materialBanner;
    private CirclePageIndicator circlePageIndicator;
    private boolean isService;
    private ImageView ivRealPerson;
    private static int intimacy;
    private ImageButton jrButton;
    private NewCacheUtil newCacheUtil;
    private ImageView ivHint;
    private TextView tvHint;
    private LinearLayout llStatus;
    private ImageView ivStatusHint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mContext = this;
        getUserMoney();
        mRxManager = new RxManager();
        setContentView(R.layout.activity_chat);
//
        ivHint= (ImageView) findViewById(R.id.iv_intimacy_hint);
        boolean intimacy = SPUtils.getBoolean("intimacy", false);
        if(!intimacy){
            ivHint.setVisibility(View.VISIBLE);
            ivHint.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    ivHint.setVisibility(View.GONE);
                    SPUtils.setBoolean("intimacy",true);
                }
            });
        }
        llStatus = (LinearLayout) findViewById(R.id.ll_status);
        tvHint = (TextView) findViewById(R.id.tv_hint);
        ivStatusHint = (ImageView) findViewById(R.id.iv_hint);
        ivHead = (ImageView) findViewById(R.id.iv_top_head);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvDistance = (TextView) findViewById(R.id.tv_distance);
        ivRealName = (ImageView) findViewById(R.id.iv_real_name);
        ivRealPerson = (ImageView) findViewById(R.id.iv_real_person);
        ivRecommend=(ImageView) findViewById(R.id.iv_recommend);
        tvOther=(TextView) findViewById(R.id.tv_other);
        jrButton=(ImageButton) findViewById(R.id.jmui_right_btn);
        materialBanner = (MaterialBanner)findViewById(R.id.material_banner);
        tvOther.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                follow();
            }
        });
        tvContent=(TextView) findViewById(R.id.tv_content);
        tvTag=(TextView) findViewById(R.id.tv_tag);
        ivSex=(ImageView) findViewById(R.id.iv_sex);
        tvAge=(TextView) findViewById(R.id.tv_age);
        cardView=(CardView) findViewById(R.id.card_view);
        allAge=(AutoLinearLayout) findViewById(R.id.all_age);
        ivIntimacy=(ImageView) findViewById(R.id.iv_intimacy);
        tvUsername=(TextView) findViewById(R.id.tv_username);
        svgaImage = (SVGAImageView) findViewById(R.id.svgaImage);
        mChatView = (ChatView) findViewById(R.id.chat_view);
        mChatView.initModule(mDensity, mDensityDpi);
        this.mWindow = getWindow();
        this.mImm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        mChatView.setListeners(this);
        newCacheUtil = new NewCacheUtil(mContext);
        ButterKnife.bind(this);
        initView();

        initData();
        //来自聊天室
    }
    private void getUserMoney() {
         dataBean = MyAPP.getmUserBean();
        if (dataBean != null&&dataBean.getUserAccount()!=null) {
            UserAccountBean.DataBean data = dataBean.getUserAccount();
            gold = (int)(data.getRecharge() + data.getCurrency())+"";
        }else {
            OkHttpClientManager.getDataOneHead(ApiKey.ACCOUNT_ACCOUNT_INFO, MyAPP.getUserId(),MyAPP.getAccessToken(), new
                    NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {

                        @Override
                        public void onSuccess(UserAccountBean result) {
                            gold = MyAPP.saveMoney(result)+"";
                        }

                        @Override
                        public void onError(String msg, int code) {

                        }
                    });
        }
    }

    public List<String>   getHomeBean(){
        List dataList = newCacheUtil.getDataList("homeData" + mTargetId, String.class);
        return dataList;
    }
    private void follow() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("followUserId", mTargetId);
        defMap.put("userId", MyAPP.getUserId());
        OkHttpClientManager.postAsyncAddHead(ApiKey.ADMIN_USERFOLLOW_ADD, defMap, "", new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {

                    @Override
                    public void onSuccess(StringDataV2Bean listBean) {
                        tvOther.setVisibility(View.GONE);
                        dataBean.setIsFollow("1");
                        saveDataBean(dataBean);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }
    public void saveDataBean(UserBean.DataBean dataBean) {
        newCacheUtil.saveUserBean(dataBean);
    }
//

   public void  getFrozen(){
           Map<String, String> defMap = MapUtils.getDefMap(false);
           defMap.put("id",mTargetId);
           OkHttpClientManager.getAsyncMore(ADMIN_USER_GETFROZEN, defMap,new NewRequestCallBack<Result>(Result.class) {
               @Override
               protected void onSuccess(Result result) {
               }
               @Override
               public void onError(String msg, int code) {
                   if(code==1004){
                       //封号
                       ekBar.setVisibility(View.GONE);
                       llStatus.setVisibility(View.VISIBLE);
                       ivStatusHint.setImageResource(R.mipmap.icon_chat_status_other);
                       tvHint.setText("该用户违规，已被封号");
                   }else if(code==1005){
                       //冻结
                       llStatus.setVisibility(View.VISIBLE);
                       ekBar.setVisibility(View.GONE);
                   }
               }
           });
   }
    private void getData() {
        Object bean = newCacheUtil.getDataBean(mTargetId, UserBean.DataBean.class);
        if(bean!=null){
            initUserData((UserBean.DataBean) bean);
        }else {
            OkHttpClientManager.getAsyncHead(ApiKey.ADMIN_USER_COMPLETEOTHERSUSERINFO, mTargetId, MyAPP.getAccessToken(), new
                    NewRequestCallBack<UserBean>(UserBean.class) {
                        @Override
                        public void onSuccess(UserBean result) {
                            saveDataBean(result.getData());
                            initUserData(result.getData());
                        }

                        @Override
                        public void onError(String msg, int code) {
                            ToastUitl.showShort(msg);
                        }
                    });
        }

    }

    private void getIntimacy() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("toUserId", mTargetId);
        OkHttpClientManager.postAsyncAddHead(ADMIN_INTIMACY_INFO, defMap, "", new
                NewRequestCallBack<IntimacyBean>(IntimacyBean.class) {

                    @Override
                    public void onSuccess(IntimacyBean bean) {
                        Glide.with(mContext).load(bean.getData().getLevelImg()).into(ivIntimacy);
                        if(bean.getData().getLevel()!=null){
                            level= Integer.parseInt(bean.getData().getLevel());
                        }
                        functionGridView.setLevel(level);
                        ekBar.setLevel(level);
                    }

                    @Override
                    public void onError(String msg, int code) {
                    }
                });
    }


    private void initUserData(UserBean.DataBean data) {
        if(data!=null){
            tvName.setText(data.getNickname());
            tvUsername.setText(data.getNickname());
            if(data.getSex() == 2){
                ivSex.setImageResource(R.mipmap.icon_home_sex_woman);
                tvAge.setTextColor(getResources().getColor(R.color.textColor_dynamic4));
                allAge.setBackgroundResource(R.drawable.normal_bg_red_age);
                tvAge.setText(data.getAge()+"岁");
            }else {
                ivSex.setImageResource(R.mipmap.icon_home_sex_man);
                tvAge.setTextColor(getResources().getColor(R.color.textColor_dynamic3));
                allAge.setBackgroundResource(R.drawable.normal_bg_bule_address);
                tvAge.setText(data.getAge()+"岁");
            }
            if(data.getMore()!=null&&data.getMore().length()>0){
                tvTag.setText("TA最喜欢"+data.getMore()+"哦");
            }else {
                tvTag.setVisibility(View.GONE);
            }
            Glide.with(mContext).load(data.getAvatar()).error(R.drawable.jmui_head_icon).into(ivRecommend);
            Glide.with(mContext).load(data.getAvatar()).error(R.drawable.jmui_head_icon).into(ivHead);
            ivHead.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    UserHomeActivity.startAction(mTargetId);
                }
            });
            ivRecommend.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    UserHomeActivity.startAction(mTargetId);
                }
            });
            if(data.getIsReal()!=null&&data.getIsReal().equals("1")){
                ivRealPerson.setVisibility(View.VISIBLE);
            }
            if(data.getIntroduce()!=null&&data.getIntroduce().length()>0){
                tvContent.setText(data.getIntroduce());
            }else {
                tvContent.setVisibility(View.GONE);
            }
                if(data.getDistance()!=null){
                    if(data.getDistance().equals("0.0")){
                        tvDistance.setText("距离 "+0+"m");
                    }else {
                        if(Double.parseDouble(data.getDistance())>1){
                            tvDistance.setText("距离 "+data.getDistance()+"km");
                        }else {
                            tvDistance.setText("距离 "+(int)(Double.parseDouble(data.getDistance())*1000)+"m");
                        }
                    }
                }

                if(dataBean.getIsFollow()!=null&&dataBean.getIsFollow().equals("1")){
                    tvOther.setVisibility(View.GONE);
                 }else {
                    tvOther.setVisibility(View.VISIBLE);
                }

            BannerUtil bannerUtil = new BannerUtil(this,materialBanner,data,false);
        }
    }



    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mRxManager.clear();
        super.onDestroy();
    }

    private void putGoldResult() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("reward","50");
        defMap.put("userId",MyAPP.getUserId());
        OkHttpClientManager.putAsyncAddHead(ApiKey.ACCOUNT_ADVERTISEMENTRECORD_RECEIVE, defMap, new
                NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {
                    @Override
                    public void onSuccess(UserAccountBean result) {
                        showPopWindow();
                        if(result!=null){
                            int gold = MyAPP.saveMoney(result);
                            mRxManager.post(AppConstant.RXTag.UPDATE_USER, gold+"");
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }


    private void showPopNoMoneyWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_no_enough_money, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        //设置成不被键盘挡住
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(mChatView, Gravity.CENTER, 0, 0);
        TextView tvRecharge = contentView.findViewById(R.id.tv_recharge);
        TextView tvReceive = contentView.findViewById(R.id.tv_receive);
//        setBackgroundAlpha(0.5f);
        tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                RechargeActivity.startAction(mContext,"");
            }
        });
        tvReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFastLoadAdDoubleClick()) {
                    return;
                }
                popupWindow.dismiss();

            }
        });
    }
    private void showPopWindow() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_get_money_im, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(mChatView, Gravity.CENTER, 0, 0);
        TextView qd = contentView.findViewById(R.id.tv_qd);
//        setBackgroundAlpha(0.5f);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();

            }
        });
    }
    private void initData() {
        SimpleCommonUtils.initEmoticonsEditText(ekBar.getEtChat());
        Intent intent = getIntent();
        mTargetId = intent.getStringExtra(TARGET_ID);
        mTargetAppKey = intent.getStringExtra(TARGET_APP_KEY);
        functionGridView = new FunctionGridView(this,level);
        getIntimacy();
        mTitle = intent.getStringExtra(JGApplication.CONV_TITLE);
        mMyInfo = JMessageClient.getMyInfo();
        if (!TextUtils.isEmpty(mTargetId)) {
            //单聊
            mIsSingle = true;
            mChatView.setChatTitle(mTitle);
            if(mTitle.equals("在线客服")){
                isService=true;
                jrButton.setVisibility(View.GONE);
                tvOther.setVisibility(View.GONE);
                ekBar.getVoiceOrText().setVisibility(View.GONE);
            }else {
                getFrozen();
                getData();
            }
            mConv = JMessageClient.getSingleConversation(mTargetId, mTargetAppKey);
            if (mConv == null) {
                mConv = Conversation.createSingleConversation(mTargetId, mTargetAppKey);
            }
            mChatAdapter = new ChattingListAdapter(mContext, mConv, longClickListener,mRxManager);
        }
        initEmoticonsKeyBoardBar();

        String draft = intent.getStringExtra(DRAFT);
        if (draft != null && !TextUtils.isEmpty(draft)) {
            ekBar.getEtChat().setText(draft);
        }

        mChatView.setChatListAdapter(mChatAdapter);
//        mChatAdapter.initMediaPlayer();
        mChatView.getListView().setOnDropDownListener(new DropDownListView.OnDropDownListener() {
            @Override
            public void onDropDown() {
                mUIHandler.sendEmptyMessageDelayed(REFRESH_LAST_PAGE, 1000);
            }
        });
        cardView.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                UserHomeActivity.startAction(mTargetId);
            }
        });
        mChatView.setToBottom();
        mChatView.setConversation(mConv);
    }



   public void setView(boolean isVis){
        if(isVis&&!isService){
            cardView.setVisibility(View.VISIBLE);
            ivRecommend.setVisibility(View.VISIBLE);
        }else {
            cardView.setVisibility(View.GONE);
            ivRecommend.setVisibility(View.GONE);
        }
    }

    private void initView() {
        lvChat = (DropDownListView) findViewById(R.id.lv_chat);
        ekBar = (XhsEmoticonsKeyBoard) findViewById(R.id.ek_bar);
        initListView();
        UserBean.DataBean dataBean = MyAPP.getmUserBean();
        if(dataBean!=null){
            if(dataBean.getTeenagersIsOpen()!=null&&dataBean.getTeenagersIsOpen().equals("0")){
                //青少年模式
                ekBar.setBtnMultimedia();
            }
        }
        ekBar.getEtChat().addTextChangedListener(new TextWatcher() {
            private CharSequence temp = "";

            @Override
            public void afterTextChanged(Editable arg0) {
                if (temp.length() > 0) {
                    mLongClick = false;
                }

                if (mAtList != null && mAtList.size() > 0) {
                    for (UserInfo info : mAtList) {
                        String name = info.getDisplayName();

                        if (!arg0.toString().contains("@" + name + " ")) {
                            forDel.add(info);
                        }
                    }
                    mAtList.removeAll(forDel);
                }

                if (!arg0.toString().contains("@所有成员 ")) {
                    mAtAll = false;
                }

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
                if (s.length() > 0 && after >= 1 && s.subSequence(start, start + 1).charAt(0) == '@' && !mLongClick) {
                    if (null != mConv && mConv.getType() == ConversationType.group) {
                        ChooseAtMemberActivity.show(ChatActivity.this, ekBar.getEtChat(), mConv.getTargetId());
                    }
                }
            }
        });

        ekBar.getEtChat().setOnFocusChangeListener((v, hasFocus) -> {
            String content;
            if (hasFocus) {
                content = "{\"type\": \"input\",\"content\": {\"message\": \"对方正在输入\"}}";
            } else {
                content = "{\"type\": \"input\",\"content\": {\"message\": \"\"}}";
            }
            if (mIsSingle) {
                JMessageClient.sendSingleTransCommand(mTargetId, null, content, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {

                    }
                });
            }
        });

        mChatView.getChatListView().setOnTouchListener((v, event) -> {
            mChatView.getChatListView().setFocusable(true);
            mChatView.getChatListView().setFocusableInTouchMode(true);
            mChatView.getChatListView().requestFocus();
            CommonUtils.hideKeyboard(mContext);
            ekBar.reset();
            return false;
        });
    }

    private void initEmoticonsKeyBoardBar() {
        ekBar.setAdapter(SimpleCommonUtils.getCommonAdapter(this, emoticonClickListener));
        ekBar.addOnFuncKeyBoardListener(this);
         gridView = new SimpleAppsGridView(this);
        ekBar.addFuncView(gridView);
        if(!isService){
            ekBar.addFunView(functionGridView);
        }else {
            ekBar.setServiceStatus();
        }
        gridView.setBalance(gold,mTargetId,this);

        ekBar.getEtChat().setOnSizeChangedListener((w, h, oldw, oldh) -> scrollToBottom());
        //发送按钮
        //发送文本消息
        ekBar.getBtnSend().setOnClickListener(v -> {
            if(dataBean.getSex()==1){
                chatPayment();
            }else {
                checkSecurity();
            }

        });
        //切换语音输入
        ekBar.getVoiceOrText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.btn_voice_or_text&&level>0) {
                    ekBar.setVideoText();
                    ekBar.getBtnVoice().initConv(mConv, mChatAdapter, mChatView);
                }else {
                    ToastUitl.showShort("亲密度达到1级时解锁");
                }
            }
        });

    }
    private void checkSecurity() {
        String mcgContent = ekBar.getEtChat().getText().toString();
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("content",mcgContent);
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_TOOL_IMSECURITY,defMap, new
                NewRequestCallBack<Result>(Result.class) {
                    @Override
                    public void onSuccess(Result result) {
                        if(result.getResultCode()==0){
                            next();
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });
    }

    public void next() {
        addIntimacy();
        String mcgContent = ekBar.getEtChat().getText().toString();
        scrollToBottom();
        if (mcgContent.equals("")) {
            return;
        }
        Message msg;
            content = new TextContent(mcgContent);
        if (mAtAll) {
            msg = mConv.createSendMessageAtAllMember(content, null);
            mAtAll = false;
        } else if (null != mAtList) {
            msg = mConv.createSendMessage(content, mAtList, null);
        } else {
            msg = mConv.createSendMessage(content);
        }
        sendMsg(msg);
    }
    public void next(String str) {
        String mcgContent = ekBar.getEtChat().getText().toString();
        mcgContent=str;
        scrollToBottom();
        if (mcgContent.equals("")) {
            return;
        }
        Message msg;
        content = new TextContent(mcgContent);
        if (mAtAll) {
            msg = mConv.createSendMessageAtAllMember(content, null);
            mAtAll = false;
        } else if (null != mAtList) {
            msg = mConv.createSendMessage(content, mAtList, null);
        } else {
            msg = mConv.createSendMessage(content);
        }
        sendMsg(msg);
    }

    /**
     *
     * 聊天付费
     *
     */
    private void chatPayment() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("giveUserId", MyAPP.getUserId());
        defMap.put("receiveUserId", mTargetId);
        defMap.put("giveUserName", MyAPP.getmUserBean().getNickname());

        OkHttpClientManager.postAsyncAddHead(ADMIN_GIFTRECORD_CHAT, defMap, "", new
                NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {

                    @Override
                    public void onSuccess(UserAccountBean bean) {
                        //金币不足
                        if(bean!=null&&bean.getData()!=null){
                            int gold = MyAPP.saveMoney(bean);
                            gridView.setBalance(gold+"", mTargetId,null);
                            checkSecurity();
                        }
                    }

                    @Override
                    public void onError(String msg, int code) {
                        showPopNoMoneyWindow();
                    }
                });
    }

    /**
     *
     * 增加亲密度
     *
     */
    private void addIntimacy() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("toUserId", mTargetId);
        OkHttpClientManager.postAsyncAddHead(ADMIN_INTIMACY_ADD, defMap, "", new
                NewRequestCallBack<Result>(Result.class) {
                    @Override
                    public void onSuccess(Result bean) {
//                        intimacy++;
                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });
    }

    private void sendMsg(Message msg) {
        if (!isChatRoom) {
            //设置需要已读回执
            MessageSendingOptions options = new MessageSendingOptions();
            options.setNeedReadReceipt(true);
            JMessageClient.sendMessage(msg, options);
            mChatAdapter.addMsgFromReceiptToList(msg);
            ekBar.getEtChat().setText("");
            if (mAtList != null) {
                mAtList.clear();
            }
            if (forDel != null) {
                forDel.clear();
            }
        } else {
            JMessageClient.sendMessage(msg);
            mChatAdapter.addMsgToList(msg);
            ekBar.getEtChat().setText("");
        }
    }

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 4000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    private static long lastClickTime;
    public static boolean isFastLoadAdDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 4000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jmui_return_btn:
                returnBtn();
                break;
            case R.id.jmui_right_btn:
                    startChatDetailActivity(mTargetId, mTargetAppKey, mGroupId);
                break;
            case R.id.jmui_at_me_btn:
                if (mUnreadMsgCnt < ChattingListAdapter.PAGE_MESSAGE_COUNT) {
                    int position = ChattingListAdapter.PAGE_MESSAGE_COUNT + mAtMsgId - mConv.getLatestMessage().getId();
                    mChatView.setToPosition(position);
                } else {
                    mChatView.setToPosition(mAtMsgId + mUnreadMsgCnt - mConv.getLatestMessage().getId());
                }
                break;
            default:
                break;
        }
    }

    private void startChatRoomActivity(long chatRoomId) {
        Intent intent = new Intent(ChatActivity.this, ChatRoomInfoActivity.class);
        intent.putExtra("chatRoomId", chatRoomId);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        returnBtn();
    }

    private void returnBtn() {
        mConv.resetUnreadCount();
        dismissSoftInput();
        if (mChatAdapter != null) {
            mChatAdapter.stopMediaPlayer();
        }
        JMessageClient.exitConversation();
        //发送保存为草稿事件到会话列表
        EventBus.getDefault().post(new Event.Builder().setType(EventType.draft)
                .setConversation(mConv)
                .setDraft(ekBar.getEtChat().getText().toString())
                .build());
        JGApplication.delConversation = null;
        if (mConv.getAllMessage() == null || mConv.getAllMessage().size() == 0) {
            if (mIsSingle) {
                JMessageClient.deleteSingleConversation(mTargetId);
            } else {
                JMessageClient.deleteGroupConversation(mGroupId);
            }
            JGApplication.delConversation = mConv;
        }
            finish();
    }

    private void dismissSoftInput() {
        if (mShowSoftInput) {
            if (mImm != null) {
                mImm.hideSoftInputFromWindow(ekBar.getEtChat().getWindowToken(), 0);
                mShowSoftInput = false;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startChatDetailActivity(String targetId, String appKey, long groupId) {
        Intent intent = new Intent();
        intent.putExtra(TARGET_ID, targetId);
        intent.putExtra(TARGET_APP_KEY, appKey);
        intent.putExtra(GROUP_ID, groupId);

        intent.setClass(this, ChatDetailActivity.class);
        startActivityForResult(intent, JGApplication.REQUEST_CODE_CHAT_DETAIL);
    }

    EmoticonClickListener emoticonClickListener = new EmoticonClickListener() {
        @Override
        public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {

            if (isDelBtn) {
                SimpleCommonUtils.delClick(ekBar.getEtChat());
            } else {
                if (o == null) {
                    return;
                }
                if (actionType == Constants.EMOTICON_CLICK_BIGIMAGE) {
                    if (o instanceof EmoticonEntity) {
                        OnSendImage(((EmoticonEntity) o).getIconUri());
                    }
                } else {
                    String content = null;
                    if (o instanceof EmojiBean) {
                        content = ((EmojiBean) o).emoji;
                    } else if (o instanceof EmoticonEntity) {
                        content = ((EmoticonEntity) o).getContent();
                    }

                    if (TextUtils.isEmpty(content)) {
                        return;
                    }
                    int index = ekBar.getEtChat().getSelectionStart();
                    Editable editable = ekBar.getEtChat().getText();
                    editable.insert(index, content);
                }
            }
        }
    };


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (EmoticonsKeyboardUtils.isFullScreen(this)) {
            boolean isConsum = ekBar.dispatchKeyEventInFullScreen(event);
            return isConsum ? isConsum : super.dispatchKeyEvent(event);
        }
        return super.dispatchKeyEvent(event);
    }

    private void initListView() {
        lvChat.setAdapter(mChatAdapter);
        lvChat.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING:
                        Log.e("onScrollStateChanged","1");
                        break;
                    case SCROLL_STATE_IDLE:
                        Log.e("onScrollStateChanged","2");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Log.e("onScrollStateChanged","3");
                        ekBar.reset();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }


    private void scrollToBottom() {
        lvChat.requestLayout();
        lvChat.post(new Runnable() {
            @Override
            public void run() {
                lvChat.setSelection(lvChat.getBottom());
            }
        });
    }

    @Override
    public void OnFuncPop(int height) {
        Log.e("OnFuncPop","OnFuncPop");
        scrollToBottom();
    }

    @Override
    public void OnFuncClose() {
        Log.e("OnFuncClose","OnFuncClose");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JMessageClient.exitConversation();
        EmoticonsKeyboardUtils.closeSoftKeyboard(this);
    }

    @Override
    protected void onResume() {
        String targetId = getIntent().getStringExtra(TARGET_ID);

        if (mIsSingle) {
            if (null != targetId) {
                String appKey = getIntent().getStringExtra(TARGET_APP_KEY);
                JMessageClient.enterSingleConversation(targetId, appKey);
            }
        } else if (!isChatRoom) {
            long groupId = getIntent().getLongExtra(GROUP_ID, 0);
            if (groupId != 0) {
                JGApplication.isAtMe.put(groupId, false);
                JGApplication.isAtall.put(groupId, false);
                JMessageClient.enterGroupConversation(groupId);
            }
        }

        //历史消息中删除后返回到聊天界面刷新界面
        if (JGApplication.ids != null && JGApplication.ids.size() > 0) {
            for (Message msg : JGApplication.ids) {
                mChatAdapter.removeMessage(msg);
            }
        }
        if (mChatAdapter != null)
            mChatAdapter.notifyDataSetChanged();
        //发送名片返回聊天界面刷新信息
        if (SharePreferenceManager.getIsOpen()) {
            if (!isChatRoom) {
                initData();
            }
            SharePreferenceManager.setIsOpen(false);
        }
        super.onResume();

    }

    public void onEvent(CommandNotificationEvent event) {
        Log.e("onEventonEvent","33334");
        if (event.getType().equals(CommandNotificationEvent.Type.single)) {
            String msg = event.getMsg();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject object = new JSONObject(msg);
                        JSONObject jsonContent = object.getJSONObject("content");
                        String messageString = jsonContent.getString("message");
                        if (TextUtils.isEmpty(messageString)) {
                            mChatView.setTitle(mConv.getTitle());
                        } else {
                            mChatView.setTitle(messageString);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
//        else{
//            String msg = event.getMsg();
//            Log.e("event.getType()",""+event.getType()+"         "+msg);
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        JSONObject object = new JSONObject(msg);
//                        JSONObject jsonContent = object.getJSONObject("content");
//                        String messageString = jsonContent.getString("text");
//                        if (TextUtils.isEmpty(messageString)) {
//                            mChatView.setTitle(mConv.getTitle());
//                        } else {
//                            mChatView.setTitle(messageString);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
    }


    public void onEvent(MessageEvent event) {
        Log.e("onEventonEvent","3333");
        final Message message = event.getMessage();
        //若为群聊相关事件，如添加、删除群成员
        if (message.getContentType() == ContentType.eventNotification) {
            GroupInfo groupInfo = (GroupInfo) message.getTargetInfo();
            long groupId = groupInfo.getGroupID();
            EventNotificationContent.EventNotificationType type = ((EventNotificationContent) message
                    .getContent()).getEventNotificationType();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message.getTargetType() == ConversationType.single) {
                    Log.e("onEventonEvent","3333333333332212");
                    UserInfo userInfo = (UserInfo) message.getTargetInfo();
                    String targetId = userInfo.getUserName();
                    String appKey = userInfo.getAppKey();
                    Log.e("onEventonEvent",targetId+"        "+mTargetId+"   "+appKey+"       "+ mTargetAppKey);
                    if (mIsSingle && targetId.equals(mTargetId) && appKey.equals(mTargetAppKey)) {
                        Log.e("onEventonEvent","111");
                        Message lastMsg = mChatAdapter.getLastMsg();
                        if (lastMsg == null || message.getId() != lastMsg.getId()) {
                            mChatAdapter.addMsgToList(message);
                        } else {
                            mChatAdapter.notifyDataSetChanged();

                        }
                    }
                } else {
                    long groupId = ((GroupInfo) message.getTargetInfo()).getGroupID();
                    if (groupId == mGroupId) {
                        Message lastMsg = mChatAdapter.getLastMsg();
                        if (lastMsg == null || message.getId() != lastMsg.getId()) {
                            mChatAdapter.addMsgToList(message);
                        } else {
                            mChatAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    public void onEventMainThread(MessageRetractEvent event) {
        Message retractedMessage = event.getRetractedMessage();
        mChatAdapter.delMsgRetract(retractedMessage);
    }

    /**
     * 当在聊天界面断网再次连接时收离线事件刷新
     */
    public void onEvent(OfflineMessageEvent event) {
        Log.e("onEventonEvent","3332234");
        Conversation conv = event.getConversation();
        if (conv.getType().equals(ConversationType.single)) {
            UserInfo userInfo = (UserInfo) conv.getTargetInfo();
            String targetId = userInfo.getUserName();
            String appKey = userInfo.getAppKey();
            if (mIsSingle && targetId.equals(mTargetId) && appKey.equals(mTargetAppKey)) {
                List<Message> singleOfflineMsgList = event.getOfflineMessageList();
                if (singleOfflineMsgList != null && singleOfflineMsgList.size() > 0) {
                    mChatView.setToBottom();
                    mChatAdapter.addMsgListToList(singleOfflineMsgList);
                }
            }
        } else {
            long groupId = ((GroupInfo) conv.getTargetInfo()).getGroupID();
            if (groupId == mGroupId) {
                List<Message> offlineMessageList = event.getOfflineMessageList();
                if (offlineMessageList != null && offlineMessageList.size() > 0) {
                    mChatView.setToBottom();
                    mChatAdapter.addMsgListToList(offlineMessageList);
                }
            }
        }
    }


    private ChattingListAdapter.ContentLongClickListener longClickListener = new ChattingListAdapter.ContentLongClickListener() {

        @Override
        public void onContentLongClick(final int position, View view) {
            if (isChatRoom) {
                return;
            }
            final Message msg = mChatAdapter.getMessage(position);

            if (msg == null) {
                return;
            }
            //如果是文本消息
            if ((msg.getContentType() == ContentType.text) && ((TextContent) msg.getContent()).getStringExtra("businessCard") == null) {
                //接收方
                if (msg.getDirect() == MessageDirect.receive) {
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    float OldListY = (float) location[1];
                    float OldListX = (float) location[0];
                    new TipView.Builder(ChatActivity.this, mChatView, (int) OldListX + view.getWidth() / 2, (int) OldListY + view.getHeight())
                            .addItem(new TipItem("复制"))
                            .addItem(new TipItem("转发"))
                            .addItem(new TipItem("删除"))
                            .setOnItemClickListener(new TipView.OnItemClickListener() {
                                @Override
                                public void onItemClick(String str, final int position) {
                                    if (position == 0) {
                                        if (msg.getContentType() == ContentType.text) {
                                            final String content = ((TextContent) msg.getContent()).getText();
                                            if (Build.VERSION.SDK_INT > 11) {
                                                ClipboardManager clipboard = (ClipboardManager) mContext
                                                        .getSystemService(Context.CLIPBOARD_SERVICE);
                                                ClipData clip = ClipData.newPlainText("Simple text", content);
                                                clipboard.setPrimaryClip(clip);
                                            } else {
                                                android.text.ClipboardManager clip = (android.text.ClipboardManager) mContext
                                                        .getSystemService(Context.CLIPBOARD_SERVICE);
                                                if (clip.hasText()) {
                                                    clip.getText();
                                                }
                                            }
                                            Toast.makeText(ChatActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ChatActivity.this, "只支持复制文字", Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (position == 1) {
                                        Intent intent = new Intent(ChatActivity.this, ForwardMsgActivity.class);
                                        JGApplication.forwardMsg.clear();
                                        JGApplication.forwardMsg.add(msg);
                                        startActivity(intent);
                                    } else {
                                        //删除
                                        mConv.deleteMessage(msg.getId());
                                        mChatAdapter.removeMessage(msg);
                                    }
                                }

                                @Override
                                public void dismiss() {

                                }
                            })
                            .create();
                    //发送方
                } else {
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    float OldListY = (float) location[1];
                    float OldListX = (float) location[0];
                    new TipView.Builder(ChatActivity.this, mChatView, (int) OldListX + view.getWidth() / 2, (int) OldListY + view.getHeight())
                            .addItem(new TipItem("复制"))
                            .addItem(new TipItem("转发"))
                            .addItem(new TipItem("撤回"))
                            .addItem(new TipItem("删除"))
                            .setOnItemClickListener(new TipView.OnItemClickListener() {
                                @Override
                                public void onItemClick(String str, final int position) {
                                    if (position == 0) {
                                        if (msg.getContentType() == ContentType.text) {
                                            final String content = ((TextContent) msg.getContent()).getText();
                                            if (Build.VERSION.SDK_INT > 11) {
                                                ClipboardManager clipboard = (ClipboardManager) mContext
                                                        .getSystemService(Context.CLIPBOARD_SERVICE);
                                                ClipData clip = ClipData.newPlainText("Simple text", content);
                                                clipboard.setPrimaryClip(clip);
                                            } else {
                                                android.text.ClipboardManager clip = (android.text.ClipboardManager) mContext
                                                        .getSystemService(Context.CLIPBOARD_SERVICE);
                                                if (clip.hasText()) {
                                                    clip.getText();
                                                }
                                            }
                                            Toast.makeText(ChatActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ChatActivity.this, "只支持复制文字", Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (position == 1) {
                                        //转发
                                        if (msg.getContentType() == ContentType.text || msg.getContentType() == ContentType.image ||
                                                (msg.getContentType() == ContentType.file && (msg.getContent()).getStringExtra("video") != null)) {
                                            Intent intent = new Intent(ChatActivity.this, ForwardMsgActivity.class);
                                            JGApplication.forwardMsg.clear();
                                            JGApplication.forwardMsg.add(msg);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(ChatActivity.this, "只支持转发文本,图片,小视频", Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (position == 2) {
                                        //撤回
                                        mConv.retractMessage(msg, new BasicCallback() {
                                            @Override
                                            public void gotResult(int i, String s) {
                                                if (i == 855001) {
                                                    Toast.makeText(ChatActivity.this, "发送时间过长，不能撤回", Toast.LENGTH_SHORT).show();
                                                } else if (i == 0) {
                                                    mChatAdapter.delMsgRetract(msg);
                                                }
                                            }
                                        });
                                    } else {
                                        //删除
                                        mConv.deleteMessage(msg.getId());
                                        mChatAdapter.removeMessage(msg);
                                    }
                                }

                                @Override
                                public void dismiss() {

                                }
                            })
                            .create();
                }
                //除了文本消息类型之外的消息类型
            } else {
                //接收方
                if (msg.getDirect() == MessageDirect.receive) {
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    float OldListY = (float) location[1];
                    float OldListX = (float) location[0];
                    new TipView.Builder(ChatActivity.this, mChatView, (int) OldListX + view.getWidth() / 2, (int) OldListY + view.getHeight())
                            .addItem(new TipItem("转发"))
                            .addItem(new TipItem("删除"))
                            .setOnItemClickListener(new TipView.OnItemClickListener() {
                                @Override
                                public void onItemClick(String str, final int position) {
                                    if (position == 1) {
                                        //删除
                                        mConv.deleteMessage(msg.getId());
                                        mChatAdapter.removeMessage(msg);
                                    } else {
                                        Intent intent = new Intent(ChatActivity.this, ForwardMsgActivity.class);
                                        JGApplication.forwardMsg.clear();
                                        JGApplication.forwardMsg.add(msg);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void dismiss() {

                                }
                            })
                            .create();
                    //发送方
                } else {
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    float OldListY = (float) location[1];
                    float OldListX = (float) location[0];
                    new TipView.Builder(ChatActivity.this, mChatView, (int) OldListX + view.getWidth() / 2, (int) OldListY + view.getHeight())
                            .addItem(new TipItem("转发"))
                            .addItem(new TipItem("撤回"))
                            .addItem(new TipItem("删除"))
                            .setOnItemClickListener(new TipView.OnItemClickListener() {
                                @Override
                                public void onItemClick(String str, final int position) {
                                    if (position == 1) {
                                        //撤回
                                        mConv.retractMessage(msg, new BasicCallback() {
                                            @Override
                                            public void gotResult(int i, String s) {
                                                if (i == 855001) {
                                                    Toast.makeText(ChatActivity.this, "发送时间过长，不能撤回", Toast.LENGTH_SHORT).show();
                                                } else if (i == 0) {
                                                    mChatAdapter.delMsgRetract(msg);
                                                }
                                            }
                                        });
                                    } else if (position == 0) {
                                        Intent intent = new Intent(ChatActivity.this, ForwardMsgActivity.class);
                                        JGApplication.forwardMsg.clear();
                                        JGApplication.forwardMsg.add(msg);
                                        startActivity(intent);
                                    } else {
                                        //删除
                                        mConv.deleteMessage(msg.getId());
                                        mChatAdapter.removeMessage(msg);
                                    }
                                }

                                @Override
                                public void dismiss() {

                                }
                            })
                            .create();
                }
            }
        }
    };

    /**
     * 消息已读事件
     */
    public void onEventMainThread(MessageReceiptStatusChangeEvent event) {
        List<MessageReceiptStatusChangeEvent.MessageReceiptMeta> messageReceiptMetas = event.getMessageReceiptMetas();
        for (MessageReceiptStatusChangeEvent.MessageReceiptMeta meta : messageReceiptMetas) {
            long serverMsgId = meta.getServerMsgId();
            int unReceiptCnt = meta.getUnReceiptCnt();
            mChatAdapter.setUpdateReceiptCount(serverMsgId, unReceiptCnt);
        }
    }


    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ImageEvent event) {
        Intent intent;
        switch (event.getFlag()) {
            case JGApplication.IMAGE_MESSAGE:
                int from = PickImageActivity.FROM_LOCAL;
                int requestCode = RequestCode.PICK_IMAGE;
                if (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "请在应用管理中打开“读写存储”访问权限！", Toast.LENGTH_LONG).show();
                } else {
                    PickImageActivity.start(ChatActivity.this, requestCode, from, tempFile(), true, 9,
                            true, false, 0, 0);
                }
                break;
            case JGApplication.TAKE_PHOTO_MESSAGE:
                if ((ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "请在应用管理中打开“相机,读写存储,录音”访问权限！", Toast.LENGTH_LONG).show();
                } else {
                    intent = new Intent(ChatActivity.this, CameraActivity.class);
                    startActivityForResult(intent, RequestCode.TAKE_PHOTO);
                }
                break;
            case JGApplication.TAKE_LOCATION:
                if (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "请在应用管理中打开“位置”访问权限！", Toast.LENGTH_LONG).show();
                } else {
                    intent = new Intent(mContext, MapPickerActivity.class);
                    intent.putExtra(JGApplication.CONV_TYPE, mConv.getType());
                    intent.putExtra(JGApplication.TARGET_ID, mTargetId);
                    intent.putExtra(JGApplication.TARGET_APP_KEY, mTargetAppKey);
                    intent.putExtra("sendLocation", true);
                    startActivityForResult(intent, JGApplication.REQUEST_CODE_SEND_LOCATION);
                }
                break;
            case JGApplication.FILE_MESSAGE:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "请在应用管理中打开“读写存储”访问权限！", Toast.LENGTH_LONG).show();

                } else {
                    intent = new Intent(mContext, SendFileActivity.class);
                    intent.putExtra(JGApplication.TARGET_ID, mTargetId);
                    intent.putExtra(JGApplication.TARGET_APP_KEY, mTargetAppKey);
                    intent.putExtra(JGApplication.CONV_TYPE, mConv.getType());
                    startActivityForResult(intent, JGApplication.REQUEST_CODE_SEND_FILE);
                }
                break;
            case JGApplication.BUSINESS_CARD:
                intent = new Intent(mContext, FriendListActivity.class);
                intent.putExtra(JGApplication.CONV_TYPE, mConv.getType());
                intent.putExtra(JGApplication.TARGET_ID, mTargetId);
                intent.putExtra(JGApplication.TARGET_APP_KEY, mTargetAppKey);
                startActivityForResult(intent, JGApplication.REQUEST_CODE_FRIEND_LIST);
                break;
            case JGApplication.TACK_VIDEO:
            case JGApplication.TACK_VOICE:
                ToastUtil.shortToast(mContext, "该功能正在添加中");
                break;
            default:
                break;
        }

    }

    private String tempFile() {
        String filename = StringUtil.get32UUID() + JPG;
        return StorageUtil.getWritePath(filename, StorageType.TYPE_TEMP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RequestCode.PICK_IMAGE://4
                onPickImageActivityResult(requestCode, data);
                break;
            case JGApplication.REQUEST_CODE_FRIEND_LIST:
                // 发送名片成功后，聊天室需要添加消息
                if (resultCode == RESULT_OK && isChatRoom) {
                    String msgJson = data.getStringExtra(JGApplication.MSG_JSON);
                    if (msgJson != null) {
                        Message msg = Message.fromJson(msgJson);
                        if (msg != null) {
                            mChatAdapter.addMsgToList(msg);
                            mChatAdapter.notifyDataSetChanged();
                        }
                    }
                }
                break;

        }

        switch (resultCode) {
            case JGApplication.RESULT_CODE_AT_MEMBER:
                if (!mIsSingle) {
                    GroupInfo groupInfo = (GroupInfo) mConv.getTargetInfo();
                    String username = data.getStringExtra(JGApplication.TARGET_ID);
                    String appKey = data.getStringExtra(JGApplication.TARGET_APP_KEY);
                    UserInfo userInfo = groupInfo.getGroupMemberInfo(username, appKey);
                    if (null == mAtList) {
                        mAtList = new ArrayList<UserInfo>();
                    }
                    mAtList.add(userInfo);
                    mLongClick = true;
                    ekBar.getEtChat().appendMention(data.getStringExtra(JGApplication.NAME));
                    ekBar.getEtChat().setSelection(ekBar.getEtChat().getText().length());
                }
                break;
            case JGApplication.RESULT_CODE_AT_ALL:
                mAtAll = data.getBooleanExtra(JGApplication.ATALL, false);
                mLongClick = true;
                if (mAtAll) {
                    ekBar.getEtChat().setText(ekBar.getEtChat().getText().toString() + "所有成员 ");
                    ekBar.getEtChat().setSelection(ekBar.getEtChat().getText().length());
                }
                break;
            case RequestCode.TAKE_PHOTO:
                if (data != null) {
                    String name = data.getStringExtra("take_photo");
                    Bitmap bitmap = BitmapFactory.decodeFile(name);
                    ImageContent.createImageContentAsync(bitmap, new ImageContent.CreateImageContentCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseMessage, ImageContent imageContent) {
                            if (responseCode == 0) {
                                Message msg = mConv.createSendMessage(imageContent);
                                handleSendMsg(msg);
                            }
                        }
                    });
                }
                break;
            case RequestCode.TAKE_VIDEO:
                if (data != null) {
                    String path = data.getStringExtra("video");
                    try {
                        FileContent fileContent = new FileContent(new File(path));
                        fileContent.setStringExtra("video", "mp4");
                        Message msg = mConv.createSendMessage(fileContent);
                        handleSendMsg(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case JGApplication.RESULT_CODE_SEND_LOCATION:
                //之前是在地图选择那边做的发送逻辑,这里是通过msgID拿到的message放到ui上.但是发现问题,message的status始终是send_going状态
                //因为那边发送的是自己创建的对象,这里通过id取出来的不是同一个对象.尽管内容都是一样的.所以为了保证发送的对象个ui上拿出来的
                //对象是同一个,就地图那边传过来数据,在这边进行创建message
                double latitude = data.getDoubleExtra("latitude", 0);
                double longitude = data.getDoubleExtra("longitude", 0);
                int mapview = data.getIntExtra("mapview", 0);
                String street = data.getStringExtra("street");
                String path = data.getStringExtra("path");
                LocationContent locationContent = new LocationContent(latitude,
                        longitude, mapview, street);
//                locationContent.setStringExtra("path", path);
                Message message = mConv.createSendMessage(locationContent);
                MessageSendingOptions options = new MessageSendingOptions();
                options.setNeedReadReceipt(true);
                JMessageClient.sendMessage(message, options);
                mChatAdapter.addMsgFromReceiptToList(message);

                int customMsgId = data.getIntExtra("customMsg", -1);
                if (-1 != customMsgId) {
                    Message customMsg = mConv.getMessage(customMsgId);
                    mChatAdapter.addMsgToList(customMsg);
                }
                mChatView.setToBottom();
                break;
            case JGApplication.RESULT_CODE_SEND_FILE:
                String msgListJson = data.getStringExtra(JGApplication.MSG_LIST_JSON);
                if (msgListJson != null) {
                    for (Message msg : Message.fromJsonToCollection(msgListJson)) {
                        handleSendMsg(msg);
                    }
                }
                break;
            case JGApplication.RESULT_CODE_CHAT_DETAIL:
                String title = data.getStringExtra(JGApplication.CONV_TITLE);
                if (!mIsSingle) {
                    GroupInfo groupInfo = (GroupInfo) mConv.getTargetInfo();
                    UserInfo userInfo = groupInfo.getGroupMemberInfo(mMyInfo.getUserName(), mMyInfo.getAppKey());
                    //如果自己在群聊中，同时显示群人数
                    if (userInfo != null) {
                        if (TextUtils.isEmpty(title)) {
                            mChatView.setChatTitle(IdHelper.getString(mContext, "group"),
                                    data.getIntExtra(MEMBERS_COUNT, 0));
                        } else {
                            mChatView.setChatTitle(title, data.getIntExtra(MEMBERS_COUNT, 0));
                        }
                    } else {
                        if (TextUtils.isEmpty(title)) {
                            mChatView.setChatTitle(IdHelper.getString(mContext, "group"));
                        } else {
                            mChatView.setChatTitle(title);
                        }
                        mChatView.dismissGroupNum();
                    }

                } else mChatView.setChatTitle(title);
                if (data.getBooleanExtra("deleteMsg", false)) {
                    mChatAdapter.clearMsgList();
                }
                break;
        }

    }


    /**
     * 图片选取回调
     */
    private void onPickImageActivityResult(int requestCode, Intent data) {
        if (data == null) {
            return;
        }
        boolean local = data.getBooleanExtra(Extras.EXTRA_FROM_LOCAL, false);
        if (local) {
            // 本地相册
            sendImageAfterSelfImagePicker(data);
        }
    }

    /**
     * 发送图片
     */

    private void sendImageAfterSelfImagePicker(final Intent data) {
        SendImageHelper.sendImageAfterSelfImagePicker(this, data, new SendImageHelper.Callback() {
            @Override
            public void sendImage(final File file, boolean isOrig) {

                //所有图片都在这里拿到
                ImageContent.createImageContentAsync(file, new ImageContent.CreateImageContentCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseMessage, ImageContent imageContent) {
                        if (responseCode == 0) {
                            Message msg = mConv.createSendMessage(imageContent);
                            handleSendMsg(msg);
                        }
                    }
                });

            }
        });
    }

    //发送极光熊
    private void OnSendImage(String iconUri) {
        String substring = iconUri.substring(7);
        File file = new File(substring);
        ImageContent.createImageContentAsync(file, new ImageContent.CreateImageContentCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, ImageContent imageContent) {
                if (responseCode == 0) {
                    imageContent.setStringExtra("jiguang", "xiong");
                    Message msg = mConv.createSendMessage(imageContent);
                    handleSendMsg(msg);
                } else {
                    ToastUtil.shortToast(mContext, responseMessage);
                }
            }
        });
    }

    /**
     * 处理发送图片，刷新界面
     *
     * @param msg
     */
    public void handleSendMsg(Message msg) {
//        String svga =((CustomContent) msg.getContent()).getStringValue("svga");
//        if(svga!=null&&svga.length()>0){
//            mChatAdapter.setSendMsgs(msg);
//            mChatView.setToBottom();
//        }else {
//        }
        setView(false);
        mChatAdapter.setSendMsgs(msg);
        mChatView.setToBottom();
    }

    private static class UIHandler extends Handler {
        private final WeakReference<ChatActivity> mActivity;

        public UIHandler(ChatActivity activity) {
            mActivity = new WeakReference<ChatActivity>(activity);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            intimacy=0;
            Log.e("","");
            ChatActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case REFRESH_LAST_PAGE:
                        activity.mChatAdapter.dropDownToRefresh();
                        activity.mChatView.getListView().onDropDownComplete();
                        if (activity.mChatAdapter.isHasLastPage()) {
                            if (Build.VERSION.SDK_INT >= 21) {
                                activity.mChatView.getListView()
                                        .setSelectionFromTop(activity.mChatAdapter.getOffset(),
                                                activity.mChatView.getListView().getHeaderHeight());
                            } else {
                                activity.mChatView.getListView().setSelection(activity.mChatAdapter
                                        .getOffset());
                            }
                            activity.mChatAdapter.refreshStartPosition();
                        } else {
                            activity.mChatView.getListView().setSelection(0);
                        }
                        //显示上一页的消息数18条
                        activity.mChatView.getListView()
                                .setOffset(activity.mChatAdapter.getOffset());
                        break;
                    case REFRESH_GROUP_NAME:
                        if (activity.mConv != null) {
                            int num = msg.getData().getInt(MEMBERS_COUNT);
                            String groupName = msg.getData().getString(GROUP_NAME);
                            activity.mChatView.setChatTitle(groupName, num);
                        }
                        break;
                    case REFRESH_GROUP_NUM:
                        int num = msg.getData().getInt(MEMBERS_COUNT);
                        activity.mChatView.setChatTitle(R.string.group, num);
                        break;
                    case REFRESH_CHAT_TITLE:
                        if (activity.mGroupInfo != null) {
                            //检查自己是否在群组中
                            UserInfo info = activity.mGroupInfo.getGroupMemberInfo(activity.mMyInfo.getUserName(),
                                    activity.mMyInfo.getAppKey());
                            if (!TextUtils.isEmpty(activity.mGroupInfo.getGroupName())) {
                                if (info != null) {
                                    activity.mChatView.setChatTitle(activity.mTitle,
                                            activity.mGroupInfo.getGroupMembers().size());
                                    activity.mChatView.showRightBtn();
                                } else {
                                    activity.mChatView.setChatTitle(activity.mTitle);
                                    activity.mChatView.dismissRightBtn();
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

}
