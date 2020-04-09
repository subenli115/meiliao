package com.ziran.meiliao.ui.NewDecompressionmuseum.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.FintessDetailBean;

import java.util.List;
//健身功法适配器


public class FitnessGridAdapter extends BaseAdapter {
        private  List<FintessDetailBean.DataBean.DetailsBean> mNameList ;
        private LayoutInflater mInflater;
        private Context mContext;
        LinearLayout.LayoutParams params;

        public FitnessGridAdapter(Context context, List<FintessDetailBean.DataBean.DetailsBean> nameList) {
            mNameList = nameList;
            mContext = context;
            mInflater = LayoutInflater.from(context);

            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
        }

        public int getCount() {
            return mNameList.size();
        }

        public Object getItem(int position) {
            return mNameList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ItemViewTag viewTag;

            if (convertView == null)
            {
                convertView = mInflater.inflate(R.layout.item_fitness_gridview, null);

                // construct an item tag
                viewTag = new ItemViewTag( (TextView) convertView.findViewById(R.id.tv_title));
                convertView.setTag(viewTag);
            } else
            {
                viewTag = (ItemViewTag) convertView.getTag();
            }

            // set name
            viewTag.mName.setText(mNameList.get(position).getName());

            return convertView;
        }

        class ItemViewTag
        {
            protected TextView mName;

            public ItemViewTag(TextView name)
            {
                this.mName = name;
            }
        }

}
