package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.ui.main.adapter.MainMeItemAdapter;
import com.ziran.meiliao.ui.main.util.MainMeNewItemUtil;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/4/10 16:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/4/10$
 * @updateDes ${TODO}
 */

public class MainMeItemView extends LinearLayout {

    private View rootView;
    private TextView tvTitle;
    private RecyclerView mRecyclerView;
    private MainMeItemAdapter mMainMeItemAdapter;

    public MainMeItemView(Context context) {
        this(context, null);
    }

    public MainMeItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainMeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MainMeItemView);
        String titleStr = typedArray.getString(R.styleable.MainMeItemView_main_me_item_title);
        if (typedArray.hasValue(R.styleable.MainMeItemView_main_me_item_title_color)) {
            int color = typedArray.getColor(R.styleable.MainMeItemView_main_me_item_title_color, getResources().getColor(R.color
                    .textColor_333));
            tvTitle.setTextColor(color);

        }
        if (typedArray.hasValue(R.styleable.MainMeItemView_main_me_item_title_size)) {
            int textSize = typedArray.getDimensionPixelSize(R.styleable.MainMeItemView_main_me_item_title_size, getResources()
                    .getDimensionPixelSize(R.dimen.text_margin));
            tvTitle.setTextSize(textSize);
        }
        if (typedArray.hasValue(R.styleable.MainMeItemView_main_me_item_default_data)) {
            int defDataType = typedArray.getInt(R.styleable.MainMeItemView_main_me_item_default_data, -1);
            List<Items> data = MainMeNewItemUtil.getData(defDataType);
            setData(data);
        }
        ViewUtil.setText(tvTitle, titleStr);
        typedArray.recycle();
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.custom_main_me_item, this, true);
        tvTitle = ViewUtil.getView(rootView, R.id.tv_main_me_new_item_title);
        mRecyclerView = ViewUtil.getView(rootView, R.id.recyclerView);
    }
    public void setData(List<Items> datas) {
        if (EmptyUtils.isEmpty(datas)) return;
        if (mMainMeItemAdapter == null) {
            mMainMeItemAdapter = new MainMeItemAdapter(getContext(), R.layout.item_main_me_new_item);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setAdapter(mMainMeItemAdapter);
            mMainMeItemAdapter.setHeadCount(0);
            mMainMeItemAdapter.setOnItemClickListener(new OnItemClickListener<Items>() {
                @Override
                public void onItemClick(ViewGroup parent, View view, Items o, int position) {
                    MainMeNewItemUtil.onItemClick(getContext(), o, position,view);
                }

                @Override
                public boolean onItemLongClick(ViewGroup parent, View view, Items o, int position) {
                    return false;
                }
            });
        }
        mMainMeItemAdapter.replaceAll(datas);
    }

    public static class Items {
        public Items(int id, int icon, String name) {
            this.icon = icon;
            this.name = name;
            this.id = id;
        }

        public Items() {
        }

        private int id;
        private int icon;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
