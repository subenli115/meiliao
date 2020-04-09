package com.ziran.meiliao.ui.workshops.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 11:57
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class CrowdFundingStepView extends View {

    private int finishColor, unFinishColor, numberTextColor;
    private float textSize, numberTextSize;
    private int bgRadius;

    private Paint finishPaint, unFinishPaint, numberPaint;
    private List<Item> mItems;

    public CrowdFundingStepView(Context context) {
        this(context, null);
    }

    public CrowdFundingStepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrowdFundingStepView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CrowdFundingStepView);
        finishColor = array.getColor(R.styleable.CrowdFundingStepView_cfsv_finish_color, Color.parseColor("#45b000"));
        unFinishColor = array.getColor(R.styleable.CrowdFundingStepView_cfsv_finish_color, Color.parseColor("#cccccc"));
        numberTextColor = array.getColor(R.styleable.CrowdFundingStepView_cfsv_finish_color, Color.WHITE);
        textSize = array.getDimension(R.styleable.CrowdFundingStepView_cfsv_text_size, DisplayUtil.sp2px(getResources(), 13));
        numberTextSize = array.getDimension(R.styleable.CrowdFundingStepView_cfsv_text_size, DisplayUtil.sp2px(getResources(), 16));
        bgRadius = (int) array.getDimension(R.styleable.CrowdFundingStepView_cfsv_bg_radius, DisplayUtil.dip2px(getResources(), 16));
        array.recycle();

        space = DisplayUtil.dip2px(getResources(), 4);
        finishPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        finishPaint.setColor(finishColor);

        numberPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setColor(numberTextColor);
        numberPaint.setTextSize(numberTextSize);

        unFinishPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        unFinishPaint.setColor(unFinishColor);
        unFinishPaint.setTextSize(textSize);

        initData();
    }

    private int itemWidth;
    private int space;
    private Rect mRect = new Rect();
    int halfItemWidth;
    int bgCY, textCY;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (EmptyUtils.isEmpty(mItems)) return;
        itemWidth = (w - getPaddingLeft() - getPaddingRight()) / mItems.size();
        String title = mItems.get(0).getTitle();
        unFinishPaint.getTextBounds(title, 0, title.length(), mRect);
        int itemHeight = bgRadius * 2 + space + mRect.height();
        int startTop = (h - itemHeight) / 3;
        halfItemWidth = itemWidth / 2;
        bgCY = startTop + bgRadius;
        textCY = startTop + bgRadius * 2 + space * 2 + mRect.height() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (EmptyUtils.isEmpty(mItems)) return;

        canvas.drawLine(halfItemWidth, bgCY, getWidth() - getPaddingRight() - halfItemWidth, bgCY, unFinishPaint);
        float textCx;
        float baseLine;
        for (int i = 0; i < mItems.size(); i++) {
            Item item = mItems.get(i);
            int cx = halfItemWidth + itemWidth * i;
            if (item.isFinish()) {
                canvas.drawCircle(cx, bgCY, bgRadius, finishPaint);
            } else {
                canvas.drawCircle(cx, bgCY, bgRadius, unFinishPaint);
            }
            textCx = cx - numberPaint.measureText(item.getNumber()) / 2;

            baseLine =ViewUtil.getBaseLine(numberPaint);
            canvas.drawText(item.getNumber(), textCx, bgCY + baseLine, numberPaint);

            textCx = cx - unFinishPaint.measureText(item.getTitle()) / 2;
            canvas.drawText(item.getTitle(), textCx, textCY, unFinishPaint);
        }
    }

    private void initData() {
        mItems = new ArrayList<>();
        mItems.add(new Item("1", "个人信息", true));
        mItems.add(new Item("2", "项目信息", false));
        mItems.add(new Item("3", "完成", false));
    }


    public void setData(List<Item> list) {
        if (list != null && !list.isEmpty()) {
            mItems = list;
            requestLayout();
        }
    }

    private class Item {
        private String number;
        private String title;
        private boolean isFinish;

        public Item() {
        }

        public Item(String number, String title, boolean isFinish) {
            this.number = number;
            this.title = title;
            this.isFinish = isFinish;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isFinish() {
            return isFinish;
        }

        public void setFinish(boolean finish) {
            isFinish = finish;
        }
    }
}
