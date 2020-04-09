package com.ziran.meiliao.ui.me.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.ui.me.activity.MyApplyRefundActivity;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 17:59
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class RefundView extends RelativeLayout {


    @Bind(R.id.tv_refund_last_time)
    TextView tvRefundLastTime;

    public RefundView(Context context) {
        this(context, null);
    }

    public RefundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private String mOrderId = "2";

    public RefundView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.custom_refund_view, this, true);
        ButterKnife.bind(this, this);
        int pd = DisplayUtil.dip2px(getResources(), 12);
        setPadding(pd, pd, pd, pd);
    }


    public void setRefundLastTime(String effectTime) {

        ViewUtil.setText(tvRefundLastTime, String.format("退款有效时间：%s", effectTime));
    }

    public void setRefundLastTime(Object startTime, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUtil.parseObject(startTime));
        calendar.add(Calendar.DATE, offset * -1);
        ViewUtil.setText(tvRefundLastTime, String.format("退款有效时间：%s", TimeUtil.getStringByFormat(calendar.getTimeInMillis(),"yyyy-MM-dd HH:mm:ss")));
    }



    public void setOrderId(String mOrderId) {
        this.mOrderId = mOrderId;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_tuikuan)
    public void onViewClicked(View view) {
        view.setEnabled(false);
        MyApplyRefundActivity.startAction(MyApplyRefundActivity.FROM_TYPE_APPLY_REFUND, mOrderId);
    }
}
