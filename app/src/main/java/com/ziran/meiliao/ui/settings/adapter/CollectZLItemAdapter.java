package com.ziran.meiliao.ui.settings.adapter;

/**
 * Created by Administrator on 2018/7/23.
 */



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;


/**
     * Created by lixu on 2017/6/27.
     */

    public class CollectZLItemAdapter extends RecyclerView.Adapter <CollectZLItemAdapter.MyViewHolder> {

    private final List<String> list;
    private final List<String> listTime;
    private LayoutInflater inflater;
        private Context context;
        //声明自定义的监听接口
        public interface ItemClickListener{
            void itemClick(int position, AutoLinearLayout v);
        }
        private ItemClickListener itemClickListener;
      public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
        //构造方法中添加自定义监听接口
        public CollectZLItemAdapter(Context context, List<String> list, List<String> listTime) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.list=list;
            this.listTime=listTime;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_collect_zl_item, parent, false);
            //这里 我们可以拿到点击的item的view 对象，所以在这里给view设置点击监听，
            return new MyViewHolder(view);
        }

    @Override
    public void onBindViewHolder(MyViewHolder holder1, final int position) {
        holder1.itemView.setTag(position);//给view设置tag以作为参数传递到监听回调方法中
        holder1.tv.setText(""+list.get(position));
        holder1.tv_time.setText(""+listTime.get(position));
        holder1.all_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null){
                    itemClickListener.itemClick(position, (AutoLinearLayout) v);
                }
            }
        });
    }

        @Override
        public int getItemCount() {
            return list.size();
        }



        class MyViewHolder extends RecyclerView.ViewHolder{
            private TextView tv;    private  TextView tv_time;
        private AutoLinearLayout all_main;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                tv_time = itemView.findViewById(R.id.tv_time);
                all_main = itemView.findViewById(R.id.all_main);
            }
        }

    }