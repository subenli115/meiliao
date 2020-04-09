package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.bean.PracticeFourCheckBean;
import com.ziran.meiliao.widget.InputTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 问卷页面适配器 on 2018/8/16.
 */

public class PracticeQuestionAdapter extends RecyclerView.Adapter<PracticeQuestionAdapter.ViewHolder>{

    private boolean isButton=false;
    private boolean isEdit;
    private InputMethodManager imm;
    private boolean one=true;

    public ArrayList<Boolean> getList() {
        return list;
    }

    private final ArrayList<Boolean> list;
    private List<PracticeFourCheckBean.DataBean.AnswerListBean> mData;
    private Context context;
    protected OnItemClickListener mOnItemClickListener;
    private int mSelectedPos=-1;

    public PracticeQuestionAdapter(List<PracticeFourCheckBean.DataBean.AnswerListBean> data, ArrayList<Boolean> list, Context context, boolean isEdit,boolean one) {
        this.mData = data;
        this.one=one;
        this.isEdit=isEdit;
        this.context=context;
        this.list=list;
    }

    public void updateData(List<PracticeFourCheckBean.DataBean.AnswerListBean> data, Context context,boolean isEdit) {
        this.mData = data;
        this.isEdit=isEdit;
        this.context=context;
        notifyDataSetChanged();
    }
    public void updateDataForEdit( Context context,boolean isEdit,boolean one) {
        this.one=one;
        this.isEdit=isEdit;
        this.context=context;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jdx_editview, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
}
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

    }
    public interface ItemClickListener{
        void itemClick(int position, EditText v);
    }
    private ItemClickListener itemClickListener;

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
                // 绑定数据
                holder.tv_content.setOnFocusChangeListener(onFocusAutoClearHintListener);
                holder.tv_title.setText(mData.get(position).getQuestionDetail());
                holder.tv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isButton=true;
            }
        });
                holder.tv_content.setHint(mData.get(position).getNoticeDetail());
                    holder.tv_content.setText(mData.get(position).getAnswerDetail());
                setEditEnable(holder,isEdit,one);
                holder.tv_content.addTextChangedListener(new TextSwitcher(holder));
                holder.tv_content.setTag(position);
                        if(list.get(position)){
                    setBG(holder.tv_voice,R.mipmap.jdx_speak);
                    holder.tv_voice.setText("输入中.... 说完了");
                }else{
                    setBG(holder.tv_voice,R.mipmap.jdx_no_speak);
                    holder.tv_voice.setText("试着用语音说说");
                }
                 holder.tv_voice.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         isButton=false;
                         if(itemClickListener!=null) {
                             itemClickListener.itemClick(position, holder.tv_content);
                         }
                         if (mSelectedPos!=position){
                             //先取消上个item的勾选状态
                             if(mSelectedPos!=-1) {
                                 list.set(mSelectedPos,false);
                                 notifyItemChanged(mSelectedPos);
                             }
                             //设置新Item的勾选状态
                             mSelectedPos = position;
                             list.set(mSelectedPos,true);
                             notifyItemChanged(mSelectedPos);
                         }else{
                             list.set(mSelectedPos,!list.get(mSelectedPos));
                             notifyItemChanged(mSelectedPos);
                         }
                     }
                 });
    }

    private void setEditEnable(ViewHolder holder, boolean isEdit,boolean one) {
        holder.tv_content.setEnabled(isEdit);
        holder.tv_content.setFocusable(isEdit);
        holder.tv_content.setFocusableInTouchMode(isEdit);
        holder.tv_content.setLongClickable(isEdit);
//        holder.tv_content.setInputType(isEdit? InputType.TYPE_TEXT_FLAG_MULTI_LINE:InputType.TYPE_NULL);
        if(!one){
            InputTools.KeyBoard(holder.tv_content,"");
        }
    }

    public static View.OnFocusChangeListener onFocusAutoClearHintListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText textView = (EditText) v;


            if (!hasFocus) {// 失去焦点
                textView.setHint(textView.getTag(R.id.tag_first).toString());
            } else {
                String hint=textView.getHint().toString();
                textView.setTag(R.id.tag_first,hint);
                textView.setHint("");
            }
        }
    };

    private void setBG(TextView tv_voice,Integer r) {
        Drawable  dra = context.getDrawable(r);
        dra.setBounds(0,0,34,46); //width即为你需要设置的图片宽度，height即为你设置的图片的高度
        tv_voice.setCompoundDrawables(null,null,dra,null);
    }
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView  tv_title,tv_voice;
            EditText tv_content;
            public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content= itemView.findViewById(R.id.tv_content);
            tv_voice= itemView.findViewById(R.id.tv_voice);
        }
    }

    //自定义EditText的监听类
    class TextSwitcher implements TextWatcher {

        private ViewHolder mHolder;
        public TextSwitcher(ViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //用户输入完毕后，处理输入数据，回调给主界面处理
            SaveEditListener listener= (SaveEditListener) context;
            if(isButton){
                if(s!=null){
                    listener.SaveEdit(Integer.parseInt(mHolder.tv_content.getTag().toString()),s.toString());
                }

            }
        }
    }
    public interface SaveEditListener{
        void SaveEdit(int position, String string);
    }
}