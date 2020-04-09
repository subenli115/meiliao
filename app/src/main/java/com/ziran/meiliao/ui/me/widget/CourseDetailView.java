package com.ziran.meiliao.ui.me.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.utils.HtmlUtil;

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

public class CourseDetailView extends RelativeLayout {
    @Bind(R.id.iv_img)
    RoundImageView ivImg;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_address_or_author)
    TextView tvAddressOrAuthor;

    public CourseDetailView(Context context) {
        this(context, null);
    }

    public CourseDetailView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CourseDetailView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.custom_course_detail, this, true);
        ButterKnife.bind(this, this);
    }


    public void setParams(String pic, String title, Object startTime, Object endTime, String addressOrAuthor, int price) {
        ViewUtil.setText(tvTitle, title);
        ViewUtil.setText(tvAddressOrAuthor, addressOrAuthor);
        if (price > 0) {
            ViewUtil.setText(tvPrice, HtmlUtil.format("¥%d", price));
            tvPrice.setVisibility(VISIBLE);
        } else {
            tvPrice.setVisibility(GONE);
        }

        ViewUtil.setText(tvTime, HtmlUtil.format("%s至%s", TimeUtil.parseObjectToString(startTime), TimeUtil.parseObjectToString(endTime)));

        ImageLoaderUtils.displayTager(getContext(), ivImg, pic, R.mipmap.ic_loading_square);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }


}
