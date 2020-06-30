package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.PayListBean;

import java.util.List;


public class PayListAdapter extends RecyclerView.Adapter<PayListAdapter.MyViewHolder> {

    List<PayListBean.DataBean.RecordsBean> mDatas = null;
    Context mContext = null;

    //通过构造函数把要显示的数据读取进来
    public PayListAdapter(List<PayListBean.DataBean.RecordsBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.mContext = context;
    }

    //相当于ListView中的getView()方法
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//      View v = View.inflate(mContext, R.layout.item, null);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        //加载布局的时候需要注意要把parent传进去
        View v = inflater.inflate(R.layout.item_pay_list, parent, false);

        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    //在这里需要设置显示的数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(mDatas.get(position).getPayName().contains("微信")){
            holder.all.setBackgroundResource(R.drawable.normal_bg_green_real);
            holder.iv_pay.setImageResource(R.mipmap.icon_wx);

        }
        holder.tv_name.setText(mDatas.get(position).getPayName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //自定义ViewHolder必须继承RecyclerView.ViewHolder，在构造函数中进行ID绑定控件，条目监听等
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        ImageView iv_pay;
        AutoLinearLayout all;

        public MyViewHolder(final View itemView) {
            super(itemView);
            all = (AutoLinearLayout) itemView.findViewById(R.id.all);
            iv_pay = (ImageView) itemView.findViewById(R.id.iv_pay);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(itemView, getLayoutPosition(), mDatas.get(getLayoutPosition()).getPayType());
                    }
                }
            });
        }
    }

    //自定义监听
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, String data);
    }



}
