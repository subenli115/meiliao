package com.ziran.meiliao.ui.settings.activity;


import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.entry.ClassBean;
import com.ziran.meiliao.ui.priavteclasses.activity.NewGongZuoFangActivity;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.zhy.autolayout.AutoRelativeLayout;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;

public class BuyOrderDetailsActivity extends BaseActivity {

    @Bind(R.id.ntb)
    public NormalTitleBar ntb;

    @Bind(R.id.tv_order_num)
    public TextView tvOrderNum;
    @Bind(R.id.tv_pay_way)
    public TextView tvPayWay;
    @Bind(R.id.tv_found_time)
    public TextView tvFoundTime;
    @Bind(R.id.tv_over_time)
    public TextView tvOverTime;

    @Bind(R.id.tv_realpay)
    public TextView tvRealpay;
    @Bind(R.id.tv_left_price)
    public TextView tvLeftPrice;
    @Bind(R.id.tv_price)
    public TextView tvPrice;
    @Bind(R.id.tv_person)
    public TextView tvPerson;
    @Bind(R.id.tv_address)
    public TextView tvAddress;
    @Bind(R.id.tv_teacher_name)
    public TextView tvTeacherName;
    @Bind(R.id.tv_title_order)
    public TextView tvTitle;
    @Bind(R.id.iv_item_sjk_act_img)
    public ImageView ivAct;
    @Bind(R.id.iv_item_sjk_act_label)
    public TextView tvLable;
    @Bind(R.id.arl_detail)
    public AutoRelativeLayout arlDetail;






    private ClassBean.DataBean.ListBean classBean;
    private ClassBean.DataBean.ListBean mClassBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_me_buy_class;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        if(getIntent()!=null){
             classBean = (ClassBean.DataBean.ListBean)getIntent().getSerializableExtra("ClassBean");
        }
        ntb.setNewTitleText("订单详情");
        arlDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewGongZuoFangActivity.startAction(mContext,mClassBean.getActivity_id()+"", mClassBean.getTitle());
            }
        });
        showData(classBean);
    }

    private void showData(ClassBean.DataBean.ListBean classBean) {
        String courseStatus = classBean.getCourseStatus();
        mClassBean=classBean;
        if(classBean.equals("进行中")){
            tvLable.setBackgroundColor(Color.RED);
        }else if(courseStatus.equals("未开始")){
            tvLable.setBackgroundColor( Color.parseColor("#F0AE5C"));
        }else{
            tvLable.setBackgroundColor(Color.GRAY);
        }
        tvLable.setText(courseStatus);
        tvTitle.setText(classBean.getTitle());
        tvOrderNum.setText(classBean.getOrders());
        tvPayWay.setText(classBean.getChannel_type());
        tvPerson.setText(classBean.getMembers()+"人");
        tvAddress.setText(classBean.getDetail());
        tvTeacherName.setText(classBean.getHost());
        tvPrice.setText("￥"+classBean.getRaw_price());
        tvLeftPrice.setText("￥"+classBean.getRaw_price()+"*"+classBean.getMembers()+"人");
        tvRealpay.setText("￥"+classBean.getPrice()+"");

        tvFoundTime.setText(timeStamp2Date(classBean.getCreate_time()+""));
        tvOverTime.setText(timeStamp2Date(classBean.getEnd_time()+""));

        Glide.with(mContext).load(classBean.getPicture()).transform(new GlideRoundTransform(mContext)).error(R.mipmap.ic_launcher).into(ivAct);
    }


    /**
          * 日期格式字符串转换成时间戳
          *
          * @param dateStr
          *            字符串日期
          * @param format
          *            如：yyyy-MM-dd HH:mm:ss
          *
          * @return
          */
    public static String timeStamp2Date(String time) {
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
