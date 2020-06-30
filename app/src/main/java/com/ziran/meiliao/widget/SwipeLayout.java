package com.ziran.meiliao.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ziran.meiliao.R;


/**
 *自定义的对条目的侧拉删除
 */
public class SwipeLayout extends FrameLayout{
    private ViewDragHelper mViewDragHelper;
    private ViewGroup mMenuView;
    private ViewGroup mMainView;
    private int mWidth;
    private int mHeight;
    private int mMaxDragRange;
    private GestureDetector mGestureDetector;

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public enum DragState{
        Open,Dragging,Close;
    }
    //当前状态也就是默认状态
    private DragState currentState= DragState.Close;
    private DragState preState= DragState.Close;///保存上一次的状态
    public interface OnSwipeStateChangedListener{
        void onOpen(SwipeLayout swipeLayout);
        void onClose(SwipeLayout swipeLayout);
    }
    private OnSwipeStateChangedListener onSwipeStateChangedListener;
    public void setOnSwipeStateChangedListener(OnSwipeStateChangedListener onSwipeStateChangedListener) {
        this.onSwipeStateChangedListener = onSwipeStateChangedListener;
    }

    private void init(Context context) {
        //1
        mViewDragHelper = ViewDragHelper.create(this, mCallback);
        //使用手势识别器解决侧拉删除条目和listview事件冲突问题
        mGestureDetector = new GestureDetector(context,mSimpleOnGestureListener);
    }
    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener=new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceX)>Math.abs(distanceY)){
                //请求不拦截
                requestDisallowInterceptTouchEvent(true);
            }
            return super.onScroll(e1,e2,distanceX,distanceY);
        }
    };
    private ViewDragHelper.Callback mCallback=new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child==mMainView){//当拖拽的是主界面的时候
                if (left<-mMaxDragRange){
                    left=-mMaxDragRange;
                }else if (left>0){
                    left=0;
                }
            }else{//当拖拽的是菜单的时候
                if (left<mWidth-mMaxDragRange){
                    left=mWidth-mMaxDragRange;
                }else if (left>mWidth){
                    left=mWidth;
                }
            }
            return left;
        }
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (changedView==mMainView){
                //让菜单也跟着动
                mMenuView.offsetLeftAndRight(dx);
            }else{
                mMainView.offsetLeftAndRight(dx);
            }
            //执行接口回调
            executeListener(mMainView.getLeft());
            //重绘
           invalidate();
        }
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (xvel==0 && mMainView.getLeft()<-mMaxDragRange/2){
                //打开
                open();
            }else if (xvel<0){
                open();
            }else{
                close(true);
            }
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mMaxDragRange;
        }
    };

    private void executeListener(int left) {
        //更新当前状态
        currentState=updateState(left);
        //执行接口回调
        if (onSwipeStateChangedListener !=null && preState!=currentState){
            if (currentState== DragState.Open){
                onSwipeStateChangedListener.onOpen(this);
            }else if (currentState== DragState.Close){
                onSwipeStateChangedListener.onClose(this);
            }
        }
        //保存为上一次状态
        preState=currentState;
    }
    private DragState updateState(int left) {
        if (left==-mMaxDragRange){
            return DragState.Open;
        }else if (left==0){
            return DragState.Close;
        }
        return DragState.Dragging;
    }

    public void close(boolean isSmooth) {
        if (isSmooth){//要平滑的滑动
            if (mViewDragHelper.smoothSlideViewTo(mMainView,0,0)){
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }else{
            //没有动画
            //把主界面和菜单 强行 摆放到初始化位置
            mMainView.layout(0,0,mWidth,mHeight);
            mMenuView.layout(mWidth,0,mWidth+mMaxDragRange,mHeight);
            //手动的更改状态，执行接口回调
            currentState= DragState.Close;
            if (onSwipeStateChangedListener!=null){
                onSwipeStateChangedListener.onClose(this);
            }
        }
    }

    private void open() {
        if (mViewDragHelper.smoothSlideViewTo(mMainView,-mMaxDragRange,0)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        mMenuView = (ViewGroup) findViewById(R.id.ll_menu);
//        mMainView = ((ViewGroup) findViewById(R.id.ll_main));
    }
    //获取主界面和菜单的宽度
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取主界面的宽度
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        //获取菜单的宽度
//        mMaxDragRange = mMenuView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //摆放主界面
        mMainView.layout(0,0,mWidth,mHeight);
        //摆放菜单
//        mMenuView.layout(mWidth,0,mWidth+mMaxDragRange,mHeight);
    }
}
