package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.adapter.RecordChildInfoAdapter;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.ui.main.adapter.ZLAudioOneAdapter;
import com.ziran.meiliao.utils.MapUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class DeletePopupWindow extends BasePopupWindow {
    private final int position;
    private final RecyclerView.Adapter mAdapter;
    private final String mid;


//    private final FragmentActivity activity;

    public DeletePopupWindow(Context context, String id, RecyclerView.Adapter adapter, int position) {
        super(context);
        this.mid = id;
        this.position = position;
        this.mAdapter = adapter;
        this.mContext = context;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_delete_head;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.tv_delete);
        setOnClickListener(R.id.tv_cancel);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_delete:
                delete();
                break;
        }
        dismiss();
    }

    public void delete() {
        if(mAdapter instanceof RecordChildInfoAdapter){
            OkHttpClientManager.deleteAsync(ApiKey.ADMIN_SPACE_DELETE, MapUtils.getDefMap(true), mid, new
                    NewRequestCallBack<Result>(Result.class) {
                        @Override
                        public void onSuccess(Result result) {
                            ((RecordChildInfoAdapter)mAdapter).removeData(position);
                        }

                        @Override
                        public void onError(String msg, int code) {
                            ToastUitl.showShort(msg);
                            super.onError(msg, code);
                        }
                    });
        }else {
            OkHttpClientManager.deleteAsync(ApiKey.ADMIN_COMMENT_DELETE, MapUtils.getDefMap(true), mid, new
                    NewRequestCallBack<Result>(Result.class) {
                        @Override
                        public void onSuccess(Result result) {
                            ((ZLAudioOneAdapter)mAdapter).removeData(position);
                        }

                        @Override
                        public void onError(String msg, int code) {
                            ToastUitl.showShort(msg);
                            super.onError(msg, code);
                        }
                    });
        }
    }









}