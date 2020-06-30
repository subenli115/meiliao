package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.ziran.meiliao.ui.bean.PointsListBean;
import com.ziran.meiliao.ui.bean.RechargeBean;
import com.ziran.meiliao.widget.GlideCircleTransform;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/6 15:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/6$
 * @updateDes ${TODO}
 */


public class AmountPointsAdapter extends CommonAblistViewAdapter<RechargeBean.DataBean.RecordsBean> {

    private  double mMoney;
    private int mPoint;

    public AmountPointsAdapter(Context context, int layoutId, double money) {
        super(context, layoutId);
        mMoney = money;
    }

    public void update(double money){
        mMoney = money;
        notifyDataSetChanged();
    }
    @Override
    public void convert(ViewHolderHelper holder, RechargeBean.DataBean.RecordsBean item, int position) {
        if (item.getPrice() > 1) {
            holder.setText(R.id.tv_recharge_amount, item.getName());
        } else {
            holder.setText(R.id.tv_recharge_amount, item.getName());
        }
        if (item.getReserve3().equals("1")) {
            holder.setVisible(R.id.tv_new, true);
        }
        double price = item.getPrice();
        holder.setText(R.id.tv_recharge_gold, "价值" + replace(price * 1000+"") + "ML币");
        if (mMoney > price) {
            holder.setProgress(R.id.progressBarHorizontal, 100);
        } else {
            int progress = (int) (mMoney / item.getPrice());
            holder.setProgress(R.id.progressBarHorizontal, progress);

        }
    }
    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return  string
     */
    public static String replace(String s){
        if(null != s && s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public void update(int point) {
        mPoint = point;
        notifyDataSetChanged();

    }


}
