package com.ziran.meiliao.ui.priavteclasses.util;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

import java.util.LinkedList;
import java.util.List;

import cn.rongcloud.live.LiveKit;
import cn.rongcloud.live.controller.ChatListAdapter;
import cn.rongcloud.live.ui.widget.ChatListView;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.MessageContent;
import io.rong.message.InformationNotificationMessage;
import io.rong.message.TextMessage;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/6 16:14
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/6$
 * @updateDes ${TODO}
 */

public class SJKRoomHelper implements Handler.Callback {
    private View lLayoutContainer;
    private EditText inputEditor;
    private Handler handler = new Handler(this);
    private ChatListAdapter chatListAdapter;
    private String roomId;
    private String chrmUserToken;
    private boolean isFirstInit = true;
    private boolean isFristLoad = true;
    private boolean showJoinAnimant = true;
    private View rootView;
    private List<InformationNotificationMessage> newUserJoin = new LinkedList<>();

    private boolean showKeyboard;

    private static final int GET_USER_COUNT = 1000;
    private boolean needConn = true;
    public SJKRoomHelper() {
    }

    public SJKRoomHelper(View rootView) {
        init(rootView);
    }
    public SJKRoomHelper(View rootView,boolean needConn) {
        this.needConn = needConn;
        init(rootView);
    }

    private void init(final View rootView) {
        if (isFirstInit) {
            this.rootView = rootView;
            lLayoutContainer = ViewUtil.getView(rootView, R.id.layout_container);
            ChatListView chatListView = ViewUtil.getView(rootView, R.id.chatListView);
            inputEditor = ViewUtil.getView(rootView, R.id.input_editor);
            ImageView inputSend = ViewUtil.getView(rootView, R.id.tv_sjk_fulllive_send);
            isFirstInit = false;
            LiveKit.addEventHandler(handler);
            chatListAdapter =  new ChatListAdapter();
            chatListView.setAdapter(chatListAdapter);
            inputEditor.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    switch (actionId) {
                        case EditorInfo.IME_ACTION_SEND:
                            sendMessage(rootView);
                            break;
                    }
                    return true;
                }
            });
            inputSend.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {
                    sendMessage(rootView);
                }
            });
        }
    }

    private void sendMessage(View rootView) {
        if (!CheckUtil.check(rootView.getContext(), rootView)) return;
        if (EmptyUtils.isEmpty(chrmUserToken)) {
            ToastUitl.showShort("暂无讲师开课,还不能进行聊天");
            return;
        }
        String text = inputEditor.getText().toString().trim();

        if (EmptyUtils.isEmpty(text)) {
            ToastUitl.showShort("说的什么好呢...");
            return;
        }
        final TextMessage content = TextMessage.obtain(text);
        LiveKit.sendMessage(content);
        inputEditor.setText("");
        RxManagerUtil.post(AppConstant.RXTag.SEND, true);
        KeyBordUtil.hideSoftKeyboard(inputEditor);
    }

    public void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible && isFristLoad) {
            try {
                roomId = LiveRoomUtil.get().getRoomId();
//                LiveRoomUtil.get().printData();
                if (   LiveRoomUtil.isNeedConn()){
                    LiveRoomUtil.get().setNeedConn(false);
                    fakeLogin();
                }else if (LiveKit.getCurrentUser()!=null){
                    chrmUserToken = LiveRoomUtil.get().getRoomToken();
                }else{
                    LiveRoomUtil.get().setNeedConn(false);
                    fakeLogin();
                }
                isFristLoad = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!isVisible) {
            if (inputEditor != null) KeyBordUtil.hideSoftKeyboard(inputEditor);
        }
    }
    private void fakeLogin() {
        chrmUserToken = LiveRoomUtil.get().getRoomToken();
        if (EmptyUtils.isEmpty(chrmUserToken)) {
            ToastUitl.showShort("加入房间失败,暂无讲师开课...");
            return;
        }
        LiveKit.connect(chrmUserToken, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                // 检查appKey 与token是否匹配.
                ToastUitl.showShort("onTokenIncorrect");
            }

            @Override
            public void onSuccess(String userId) {
                LiveKit.setCurrentUser(userId, MyAPP.getUserInfo().getNickName(), StringUtils.headImg());
                joinChatRoom(roomId);
//                LogUtils.logd("userId:"+userId + " roomId:"+roomId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                // 根据errorCode 检查原因.
                LogUtils.logd("onError" + errorCode.getValue());
            }
        });
    }

    private void joinChatRoom(final String roomId) {
        LiveKit.joinChatRoom(roomId, 1, new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                final InformationNotificationMessage content = InformationNotificationMessage.obtain("进入聊天室");
                LiveKit.sendMessage(content);
                startGetUserCount();
                chatListAdapter.addMessage(LiveKit.getNotifytionMessage());
//                LogUtils.logd( " roomId:"+roomId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.logd("聊天室加入失败! errorCode = " + errorCode);
            }
        });
    }

    private boolean isShowJoin;

    private boolean check(String userName) {
        boolean flag = false;
        for (int i = 0; i < newUserJoin.size(); i++) {
            if (userName.equals(newUserJoin.get(i).getUserInfo().getName())) {
                flag = true;
            }
        }
        return flag;
    }

    private int count = 0;

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case LiveKit.MESSAGE_ARRIVED: {
                count++;
//                LogUtils.logd("MESSAGE_ARRIVED.msg:"+msg.obj);
                if (count <= 1) {
                    break;
                }
                MessageContent content = (MessageContent) msg.obj;
                if (content instanceof InformationNotificationMessage && isShowJoinAnimant()) {

                    InformationNotificationMessage infoMsg = ((InformationNotificationMessage) content);
                    if ("进入聊天室".equals(infoMsg.getMessage())) {
                        if (!check(infoMsg.getUserInfo().getName())) {
                            newUserJoin.add(infoMsg);
                        }
                        if (!isShowJoin) {
                            isShowJoin = true;
                            handler.sendEmptyMessage(500);
                        }
                    }
                    String infoMsgExtra = infoMsg.getExtra();
                    if ("直播已结束".equals(infoMsgExtra)) {
                        RxManagerUtil.post(AppConstant.RXTag.LIVE_OVER, true);
//                    }else if (EmptyUtils.isNotEmpty(infoMsgExtra) && infoMsgExtra.contains("gift,")){
//                        String[] split = infoMsgExtra.split(",");
//
                    }
                }
                if (content.getUserInfo() != null) {
                    LogUtils.logd("id : " + content.getUserInfo().getUserId() + " /name  " + content.getUserInfo().getName());
                }
                chatListAdapter.addMessage(content);
                break;
            }
            case LiveKit.MESSAGE_SENT: {
                MessageContent content = (MessageContent) msg.obj;
                chatListAdapter.addMessage(content);
                break;
            }
            case LiveKit.MESSAGE_SEND_ERROR: {
                LogUtils.logd("MESSAGE_SEND_ERROR" + msg.what + msg.obj.toString());
                break;
            }
            case 500: {
                if (EmptyUtils.isNotEmpty(newUserJoin)) {
                    PopupWindowUtil.showTipJoinRoom(rootView, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupWindowUtil.dismiss();
                        }
                    }, newUserJoin.remove(0));
                    handler.sendEmptyMessageDelayed(500, 1500);
                } else {
                    PopupWindowUtil.dismiss();
                    isShowJoin = false;
                }
                break;
            }
            case GET_USER_COUNT: {
                RxManagerUtil.post(AppConstant.RXTag.USER_COUNT, true);
                handler.sendEmptyMessageDelayed(GET_USER_COUNT, 30 * 1000);
                break;
            }
            default:
        }
        chatListAdapter.notifyDataSetChanged();
        return false;
    }


    public void showInputPanel(boolean show) {

        if (lLayoutContainer != null) {
            lLayoutContainer.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void showKeyboard(boolean show) {
        if (!show) {
            KeyBordUtil.hideSoftKeyboard(inputEditor);
        } else {
            KeyBordUtil.showSoftKeyboard(inputEditor);
        }
        showKeyboard = show;
    }

    public void setShowKeyboard(boolean showKeyboard) {
        this.showKeyboard = showKeyboard;
    }

    public boolean isShowKeyboard() {
        return showKeyboard;
    }

    public void onPause(){
        if (isStartGetUserCount){
            handler.removeMessages(GET_USER_COUNT);
        }
    }
    public void onResume(){
        if (isStartGetUserCount){
            handler.sendEmptyMessage(GET_USER_COUNT);
        }
    }
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        LiveKit.quitChatRoom(new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                LiveKit.clearEventHandler();
                LiveKit.logout();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LiveKit.clearEventHandler();
                LiveKit.logout();
            }
        });
        LiveRoomUtil.get().onDestroy(null);
    }
    boolean isStartGetUserCount;
    public void startGetUserCount() {
        isStartGetUserCount = true;
        handler.sendEmptyMessage(GET_USER_COUNT);
    }



    public boolean isShowJoinAnimant() {
        return showJoinAnimant;
    }

    public void setShowJoinAnimant(boolean showJoinAnimant) {
        this.showJoinAnimant = showJoinAnimant;
    }

    public void setTextColorType(int type) {
        chatListAdapter.setTextColorType(type);
    }
}
