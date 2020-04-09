package com.ziran.meiliao.ui.workshops.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/10 12:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/10$
 * @updateDes ${TODO}
 */

public class TitleGridView extends RelativeLayout {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.gridView)
    GridView gridView;

    public TitleGridView(Context context) {
        this(context, null);
    }

    public TitleGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.custom_title_gridview, this, true);
        ButterKnife.bind(this, this);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleGridView);
        if (array.hasValue(R.styleable.TitleGridView_tgv_title)) {
            tvTitle.setText(array.getString(R.styleable.TitleGridView_tgv_title));
        }
        if (array.hasValue(R.styleable.TitleGridView_tgv_title_color)) {
            tvTitle.setTextColor(array.getColor(R.styleable.TitleGridView_tgv_title_color, Color.parseColor("#666666")));
        }
        if (array.hasValue(R.styleable.TitleGridView_tgv_title_size)) {
            tvTitle.setTextSize(array.getFloat(R.styleable.TitleGridView_tgv_title_size, 14f));
        }
        if (array.hasValue(R.styleable.TitleGridView_tgv_grid_number)) {
            gridView.setNumColumns(array.getInt(R.styleable.TitleGridView_tgv_grid_number, 4));
        }
        if (array.hasValue(R.styleable.TitleGridView_tgv_ver_space)) {
            gridView.setVerticalSpacing(array.getDimensionPixelSize(R.styleable.TitleGridView_tgv_ver_space, 0));
        }
        if (array.hasValue(R.styleable.TitleGridView_tgv_hor_space)) {
            gridView.setHorizontalSpacing(array.getDimensionPixelSize(R.styleable.TitleGridView_tgv_hor_space, 0));
        }

        array.recycle();
    }


    public void setTitle(CharSequence title) {
        ViewUtil.setText(tvTitle, title);
    }

    public GridView setAdapter(BaseAdapter adapter) {
        gridView.setAdapter(adapter);
        return gridView;
    }

    public GridView setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        gridView.setOnItemClickListener(listener);
        return gridView;
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        super.onDetachedFromWindow();

    }

    public void setGridViewPadding(int value) {
        int dip2px = DisplayUtil.dip2px(getResources(), value);
        gridView.setPadding(dip2px, 0, dip2px, 0);
    }
}
