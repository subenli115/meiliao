package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.utils.MusicPanelFloatManager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/1 12:28
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/1$
 * @updateDes ${TODO}
 */

public class MyCoordinatorLayout extends FrameLayout {

    @Bind(R.id.headerContainer)
    RelativeLayout headerContainer;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.contentContainer)
    LinearLayout contentContainer;
    @Bind(R.id.root_coordinator)
    CoordinatorLayout rootCoordinator;
    private int headerLayoutRes;
    private int titleLayoutRes;
    private int contentLayoutRes;

    private View mHeaderView;
    private View mTitleView;
    private View mContentView;
    private NormalTitleBar mNormalTitleBar;
    private boolean usedDefaultTitle;

    public MyCoordinatorLayout(@NonNull Context context) {
        this(context, null);
    }

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_coordinator_layout, this, true);
        ButterKnife.bind(this, this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyCoordinatorLayout);
        headerLayoutRes = a.getResourceId(R.styleable.MyCoordinatorLayout_headerLayout, -1);
        contentLayoutRes = a.getResourceId(R.styleable.MyCoordinatorLayout_contentLayout, -1);
        titleLayoutRes = a.getResourceId(R.styleable.MyCoordinatorLayout_titleLayout, -1);
        usedDefaultTitle = a.getBoolean(R.styleable.MyCoordinatorLayout_used_default_title, false);
        a.recycle();

        if (titleLayoutRes != -1) {
            setTitleView(titleLayoutRes);
        } else if (usedDefaultTitle) {
            mNormalTitleBar = new NormalTitleBar(context);
            setTitleView(mNormalTitleBar);
        }
        if (headerLayoutRes != -1) {
            sethHeaderView(headerLayoutRes);
        }
        if (contentLayoutRes != -1) {
            setContentView(contentLayoutRes);
        }
        if (!usedDefaultTitle) {
            toolbar.setVisibility(GONE);
        }
    }

    private void setTitleView(int titleLayoutRes) {
        final View contentView = LayoutInflater.from(getContext()).inflate(titleLayoutRes, toolbar, false);
        if (contentView != null) {
            setTitleView(contentView);
        }
    }

    private void setTitleView(View titleView) {
        removeChildView(toolbar, mTitleView);
        if (mTitleView != titleView) {
            this.mTitleView = titleView;
            toolbar.addView(titleView);
        }
    }

    public void setContentView(int contentLayoutRes) {
        final View contentView = LayoutInflater.from(getContext()).inflate(contentLayoutRes, contentContainer, false);
        if (contentView != null) {
            setContentView(contentView);
        }
    }

    public void setContentView(View contentView) {
        if (contentContainer.getChildCount() > 0) {
            contentContainer.removeAllViews();
        }
        this.mContentView = contentView;
        contentContainer.addView(contentView);
    }

    public void addContentView(View contentView, LinearLayout.LayoutParams params) {
        if (contentView != null) {
            contentContainer.removeView(contentView);
            contentContainer.addView(contentView, params);
        }
    }

    public void addContentView(View contentView, int width, int height) {
        if (contentView != null) {
            contentContainer.removeView(contentView);
            contentContainer.addView(contentView, new LinearLayout.LayoutParams(width, height));
        }
    }

    /**
     * 添加刷新header layout
     *
     * @param refreshHeaderLayoutRes
     */
    public void sethHeaderView(@LayoutRes int refreshHeaderLayoutRes) {
        final View refreshHeader = LayoutInflater.from(getContext()).inflate(refreshHeaderLayoutRes, headerContainer, false);
        if (refreshHeader != null) {
            setHeaderView(refreshHeader);
        }
    }

    /**
     * 添加刷新header view
     *
     * @param refreshHeaderView
     */
    public void setHeaderView(View refreshHeaderView) {
        removeChildView(headerContainer, mHeaderView);
        if (mHeaderView != refreshHeaderView) {
            this.mHeaderView = refreshHeaderView;
            headerContainer.addView(refreshHeaderView);
        }
    }

    public RecyclerView getDefaultContentView() {
        return getDefaultContentView(new LinearLayoutManager(getContext()));
    }

    public RecyclerView getDefaultContentView(RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    private void removeChildView(ViewGroup viewGroup, View view) {
        if (viewGroup != null && view != null) {
            viewGroup.removeView(view);
        }
    }


    public <T> T getHeaderChildView(int id) {
        return (T) headerContainer.findViewById(id);
    }

    public <T> T getContentChildView(int id) {
        return (T) contentContainer.findViewById(id);
    }

    public <T> T getTitleChildView(int id) {
        return (T) mTitleView.findViewById(id);
    }

    public NormalTitleBar getNormalTitleBar() {
        return mNormalTitleBar;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    public void addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener listener) {
        appBar.addOnOffsetChangedListener(listener);
    }

    public void openTitleBarColorChange() {
        if (mTitleView == null) return;
        addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset) {

                mTitleView.setBackgroundColor(ViewUtil.getColor(200, titleColor));
            }

        });
    }

    private int titleColor = Color.WHITE;

    //非本项目需注释该方法
    public void openOffsetChanged(final boolean changeTitle, final boolean changeMusicPanelVis) {
        addOnOffsetChangedListener(new MyCoordinatorLayout.AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset) {
                if (state == State.EXPANDED) {
                    if (mNormalTitleBar != null && changeTitle) {
                        mNormalTitleBar.setTitleVisibility(false);
                        mNormalTitleBar.setBackGroundColor(R.color.transparent);
                    }
                    if (changeMusicPanelVis) {
                        MusicPanelFloatManager.getInstance().setIsShowingAnimation(true);
                    }

                    //展开状态
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    if (mNormalTitleBar != null && changeTitle) {
                        mNormalTitleBar.setTitleVisibility(true);
                        mNormalTitleBar.setBackGroundColor();
                    }
                    if (changeMusicPanelVis) {
                        MusicPanelFloatManager.getInstance().setIsShowingAnimation(false);
                    }
                }
            }
        });
    }

    public static abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

        public enum State {
            EXPANDED, COLLAPSED, IDLE
        }

        private State mCurrentState = State.IDLE;

        @Override
        public final void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (verticalOffset == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED, verticalOffset);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED, verticalOffset);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE, verticalOffset);
                }
                mCurrentState = State.IDLE;
            }
        }

        public abstract void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset);
    }
}
