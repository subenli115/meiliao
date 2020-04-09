package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wu on 2015/12/17.
 */
public class SelectNumMoreAdapter extends RecyclerView.Adapter<SelectNumMoreAdapter.AgeItemViewHolder> {


    private  ArrayList<Integer> array;
    private Context mContext;
    private int selectedPosition=-1;
    /**
     * 显示的数据个数
     */
    public int ITEM_NUM = 7;
    /**
     * 当前选中位置，高亮显示
     */
    public HashMap<Integer, Boolean> isSelected;

    // 初始化 设置所有item都为未选择
    public SelectNumMoreAdapter(Context context, ArrayList<Integer> array, String timePoint) {
        mContext = context;
        this.array = array;
        for(int i=0;i<array.size();i++){
            if((array.get(i)+"").equals(timePoint)){
                selectedPosition=i;
                notifyDataSetChanged();
            }
        }
    }

    public void update(Context context, ArrayList<Integer> array, String timePoint) {
        mContext = context;
        this.array = array;
        for(int i=0;i<array.size();i++){
            if((array.get(i)+"").equals(timePoint)){
                selectedPosition=i;
                notifyDataSetChanged();
            }
        }
    }


    @Override
    public AgeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_age_item, parent, false);
        //设置宽度
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width =(int)getItemWidth();
        return new AgeItemViewHolder(view);
    }

//    public void update(Context context, List array, String timePoint) {
//        mContext = context;
//        this.array = array;
//        for(int i=0;i<array.size();i++){
//            if(array.get(i).equals(timePoint)){
//                selectedPosition=i;
//                notifyDataSetChanged();
//            }
//        }
//    }

    public interface ItemClickListener{
        void itemClick(int position, TextView v);
    }
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

    }

    @Override
    public void onBindViewHolder(final AgeItemViewHolder holder, final int position) {
        holder.mTextView.setText(array.get(position)+"时");
        if (selectedPosition == position) {
            holder.mTextView.setTextColor(Color.parseColor("#5FA9F8"));
        }else{
            holder.mTextView.setTextColor(Color.parseColor("#999999"));
        }

        holder.mTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(itemClickListener!=null){
                    itemClickListener.itemClick(position,(TextView) v);
                    selectedPosition = position; //选择的position赋值给参数，
                    notifyDataSetChanged();
                }
            }

        });
    }



    @Override
    public int getItemCount() {
        return array.size();
    }

    /**
     * 获取每一个条目的宽度
     *
     * @return
     */
    public float getItemWidth() {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels / ITEM_NUM;
    }


    /**
     * ViewHolder类
     */
    public class AgeItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public AgeItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item_age);
            mTextView.setTag(this);
            int layoutPosition = getLayoutPosition();
        }

        public TextView getmTextView() {
            return mTextView;
        }
    }
}
