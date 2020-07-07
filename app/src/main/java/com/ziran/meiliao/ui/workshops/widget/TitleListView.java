package com.ziran.meiliao.ui.workshops.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 吴祖清 @version $Rev$ @createTime 2017/11/8 18:31 @des ${TODO} @updateAuthor $Author$ @updateDate 2017/11/8$ @updateDes ${TODO}
 */
public class TitleListView extends RelativeLayout {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_title_more)
    TextView tvMore;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    public TitleListView(Context context) {
        this(context, null);
    }

    public TitleListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.custom_title_listview, this, true);
        ButterKnife.bind(this, this);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleListView);
        String title = array.getString(R.styleable.TitleListView_tlv_title);
        array.recycle();
        setTvTitle(title);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    public void setTvTitle(String title) {
        ViewUtil.setText(tvTitle, title);
    }
    public void openSnapHelper(){
        LinearSnapHelper mMySnapHelper = new LinearSnapHelper();
        mMySnapHelper.attachToRecyclerView(recyclerView);
    }
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    public void setMoreTitleVis(int vis){
        tvMore.setVisibility(vis);
    }

    public void setMoreClickListener(OnClickListener listener) {
        if (tvMore != null && listener != null) {
            tvMore.setOnClickListener(listener);
        }
    }

    public void setTvTitleIcon(int id) {
        if (tvTitle!=null){
            tvTitle.setCompoundDrawablesWithIntrinsicBounds(id,0,0,0);
        }
    }

    public void setRecyclerViewPaddLeft(int paddingLeft){
        if (recyclerView!=null){
            recyclerView.setPadding(paddingLeft,recyclerView.getPaddingTop(),recyclerView.getPaddingRight(),recyclerView.getPaddingBottom());
        }
    }
    public void setMoreTag(String tag) {
        if (tvMore != null && tag != null) {
            tvMore.setTag(tag);
        }
    }
}
