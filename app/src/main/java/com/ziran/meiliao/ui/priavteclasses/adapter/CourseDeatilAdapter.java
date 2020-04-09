package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.MTPCourseModel;
import com.ziran.meiliao.widget.ExpandableTextView;
import com.zhy.autolayout.AutoLinearLayout;


import java.util.List;

public class CourseDeatilAdapter extends RecyclerView.Adapter<CourseDeatilAdapter.ViewHolder>{

    private List<MTPCourseModel> data ;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private int mposition;


    public CourseDeatilAdapter(List<MTPCourseModel> data, Context context){
        this.data = data;
        this.context = context;
    }

    private View VIEW_HEADER;
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }
    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            AutoLinearLayout.LayoutParams params = new AutoLinearLayout.LayoutParams(AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);

            VIEW_HEADER = headerView;
            notifyItemInserted(0);
        }
    }
    public void updateData(List<MTPCourseModel> data , Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_deatil, parent, false);
        // 实例化viewholder

        ViewHolder viewHolder = new ViewHolder(v);
        if(viewType == TYPE_HEADER){
            return new ViewHolder(VIEW_HEADER);
        }else {
            return viewHolder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }

    }
    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        mposition=position;
        if (!isHeaderView(mposition) ) {
            if (haveHeaderView()) mposition--;
          MTPCourseModel bean = data.get(mposition);
        if(bean.getTeacherName()==null||bean.getTeacherName().equals("")){
            holder.allTeacher.setVisibility(View.GONE);
        }else {
            holder.allTeacher.setVisibility(View.VISIBLE);
            holder.tvTeacherName.setText(bean.getTeacherName()+"   "+bean.getTeacherDegree());
            ImageLoaderUtils.displayCircle(context, holder.ivBigTeacher, bean.getAvatarUrl(), R.mipmap.ic_user_pic);
        }
        if(bean.getTitle()==null||bean.getTitle().equals("")){

            holder.allTop.setVisibility(View.GONE);
        }else {
            holder.allTop.setVisibility(View.VISIBLE);
            holder.tvTitle.setText(bean.getTitle());
        }
        if(bean.getCourseUrl()!=null&&bean.getCourseUrl().length()>0){

            holder.ivTable.setVisibility(View.VISIBLE);
            Glide.with(context).load(bean.getCourseUrl()).into(holder.ivTable);
        }else{http://ojlzx3sl8.bkt.clouddn.com/

            holder.ivTable.setVisibility(View.GONE);
        }
        holder.tvHeadview.setText(bean.getContent().replace("\\n", "\n") );
        if(!holder.tvTitle.getText().equals("课程内容")){
            holder.tvHeadview.setmMaxLinesOnShrink(99);
        }else {
            holder.tvHeadview.setmMaxLinesOnShrink(13);

        }
        holder.itemView.setOnClickListener ( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        }
    }

    @Override
    public int getItemCount() {
        int count = data == null ? 0 : data.size();

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AutoLinearLayout  allTop;
        TextView tvTeacherName,tvTitle;
        ImageView ivTeacher,ivBigTeacher,ivTable;
        AutoLinearLayout allTeacher;
        ExpandableTextView tvHeadview;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTeacherName = itemView.findViewById(R.id.tv_teacher_name);
            ivTeacher = itemView.findViewById(R.id.iv_teacher);
            tvTitle = itemView.findViewById(R.id.tv_title);
            allTeacher = itemView.findViewById(R.id.all_teacher);
            allTop = itemView.findViewById(R.id.all_top);
            ivTeacher = itemView.findViewById(R.id.iv_teacher);
            tvHeadview = itemView.findViewById(R.id.tv_headview_sjk_zhuanlan_profile);
            ivBigTeacher = itemView.findViewById(R.id.iv_big_teacher);
            ivTable = itemView.findViewById(R.id.iv_table);
        }
    }
}
