package com.ziran.meiliao.ui.me.widget;

import android.content.Context;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.utils.HtmlUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 17:59
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class SomeTextView extends LinearLayout {


    @Bind(R.id.tv_top)
    TextView tvTop;
    @Bind(R.id.tv_bottom)
    TextView tvBottom;
    @Bind(R.id.viewStub_mid)
    ViewStub viewStubMid;
    private boolean isInflateMid;

    public SomeTextView(Context context) {
        this(context, null);
    }

    public SomeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SomeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.custom_some_text_view1, this, true);
        ButterKnife.bind(this, this);
    }


    public void setParams(String name, String phone, String email, String orderNumber, String effectTime) {
        setNamePhoneEmail(name, phone, email);
        ViewUtil.setText(tvBottom, HtmlUtil.format("订单编号：%s%n生成时间：%s", orderNumber, effectTime));
    }

    public void setParams(String name, String phone, String orderNumber, String effectTime) {
        setNamePhoneEmail(name, phone);
        ViewUtil.setText(tvBottom, HtmlUtil.format("订单编号：%s%n生成时间：%s", orderNumber, effectTime));
    }

    public void setNamePhoneEmail(String name, String phone, String email) {
        StringBuilder sb = new StringBuilder();
        if (EmptyUtils.isNotEmpty(name)) {
            sb.append(HtmlUtil.getTeamString1("姓名", name));
        }
        if (EmptyUtils.isNotEmpty(phone)) {
            sb.append("<br/>").append(HtmlUtil.getTeamString1("电话", phone));
        }
        if (EmptyUtils.isNotEmpty(email)) {
            sb.append("<br/>").append(HtmlUtil.getTeamString1("邮箱", email));
        }
        ViewUtil.setText(tvTop, HtmlUtil.fromHtml(sb.toString()));
    }

    public void setNamePhoneEmail(String name, String phone) {
        String htmlStrTop = HtmlUtil.getTeamString1("姓名", name) + "<br/>" + HtmlUtil.getTeamString1("电话", phone);
        ViewUtil.setText(tvTop, HtmlUtil.fromHtml(htmlStrTop));
    }

    public void setTopString(String topString) {
        ViewUtil.setText(tvTop, HtmlUtil.fromHtml(topString));
    }

    public void setBottomString(String bottomString) {
        ViewUtil.setText(tvBottom, bottomString);
    }

    public void showMid() {
        if (!isInflateMid) {
            viewStubMid.inflate();
            isInflateMid = true;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    public void setRefundResult(String name, String phone, String email, String orderNumber, String effectTime, String refundNumber) {
        setNamePhoneEmail(name, phone, email);
        ViewUtil.setText(tvBottom, HtmlUtil.format("订单编号：%s%n生成时间：%s%n退款编号：%s", orderNumber, effectTime, refundNumber));
    }

    public void setPartakeParams(String name, String profile, String servicePhone, String crowdFundingNumber, String effectTime) {
        showMid();
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlUtil.getTeamString1("姓名", name));
        if (EmptyUtils.isNotEmpty(profile)){
            sb.append("<br/>").append(HtmlUtil.getTeamString1("发起人简介", profile));
        }
        if (EmptyUtils.isNotEmpty(servicePhone)){
            sb.append("<br/>").append(HtmlUtil.getTeamString1("客服热线", servicePhone));
        }
        ViewUtil.setText(tvTop, HtmlUtil.fromHtml(sb.toString()));
        ViewUtil.setText(tvBottom, HtmlUtil.format("众筹编号：%s%n生成时间：%s", crowdFundingNumber, effectTime));
    }

    public void setTeamParams(String people, String phone, String demo, String orderId, String createTime) {
        StringBuilder sb = new StringBuilder();
        if (EmptyUtils.isNotEmpty(people)) {
            sb.append(HtmlUtil.getTeamString1("人数", people));
        }
        if (EmptyUtils.isNotEmpty(phone)) {
            sb.append("<br/>").append(HtmlUtil.getTeamString1("电话", phone));
        }
        if (EmptyUtils.isNotEmpty(demo)) {
            sb.append("<br/>").append(HtmlUtil.getTeamString1("备注", demo));
        }
        ViewUtil.setText(tvTop, HtmlUtil.fromHtml(sb.toString()));

        ViewUtil.setText(tvBottom, HtmlUtil.format("订单编号：%s%n生成时间：%s", orderId, createTime));
    }

    public static class Builder {
        private List<KeyValue> data;

        public Builder() {
            this.data = new ArrayList<>();
        }

        public Builder set(String key, String value) {
            data.add(new KeyValue(key, value));
            return this;
        }

        public String buildStr() {
            if (EmptyUtils.isEmpty(data)) return "";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                KeyValue keyValue = data.get(i);
                sb.append(HtmlUtil.getTeamString1(keyValue.getKey(), keyValue.getValue()));
                if (i != data.size() - 1) {
                    sb.append("<br/>");
                }
            }
            return sb.toString();
        }

        public Spanned buildSpand() {
            String buildStr = buildStr();
            if (EmptyUtils.isEmpty(buildStr)) return null;
            return HtmlUtil.fromHtml(buildStr);
        }

    }

    private static class KeyValue {
        private String key;
        private String value;

        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
