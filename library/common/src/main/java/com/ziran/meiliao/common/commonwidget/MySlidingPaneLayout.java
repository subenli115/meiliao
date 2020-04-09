//package com.ziran.meiliao.common.commonwidget;
//
//import android.content.Context;
//import android.support.v4.widget.SlidingPaneLayout;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//
///**
// * @author 吴祖清
// * @version $Rev$
// * @createTime 2017/9/27 14:33
// * @des ${TODO}
// * @updateAuthor $Author$
// * @updateDate 2017/9/27$
// * @updateDes ${TODO}
// */
//
//public class MySlidingPaneLayout extends SlidingPaneLayout {
//    public MySlidingPaneLayout(Context context) {
//        super(context);
//    }
//
//    public MySlidingPaneLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MySlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return !(mOnCanTouchListener!=null && mOnCanTouchListener.canTouch()) && super.onInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        return !(mOnCanTouchListener!=null && mOnCanTouchListener.canTouch()) && super.onTouchEvent(ev);
//    }
//
////    private ViewPager mViewPager;
////
////
////    public void setViewPager(ViewPager viewPager) {
////        mViewPager = viewPager;
////    }
//    private OnCanTouchListener mOnCanTouchListener;
//
//    public void setOnCanTouchListener(OnCanTouchListener onCanTouchListener) {
//        mOnCanTouchListener = onCanTouchListener;
//    }
//
//    public interface OnCanTouchListener{
//        boolean canTouch();
//    }
//}
