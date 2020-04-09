package com.ziran.meiliao.ui.main.fragment;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.commonwidget.CircleIndicator;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.utils.HandlerUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 第一次使用APP导引Fragment
 */
public class GuideFragment extends BaseFragment {

    @Bind(R.id.iv_guide_go)
    ImageView ivGo;

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator mCircleIndicator;
    private List<View> viewList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_guide;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        HandlerUtil.runMain(new Runnable() {
            @Override
            public void run() {
                MyAPP.init();
            }
        },2000);
        initData();
        viewPager.setAdapter(pagerAdapter);
        mCircleIndicator.setViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                ivGo.setVisibility(resId.length-1 == position ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    //点击监听
    @OnClick(R.id.iv_guide_go)
    public void onClick(View view) {
        NewLoginActivity.startAction(getContext());
        finish();
    }

    private int[] resId = {R.mipmap.loading1, R.mipmap.loading2};

    private void initData() {
        viewList = new ArrayList<>();
        for (int i = 0; i < resId.length; i++) {
            ImageView view = new ImageView(getContext());
            view.setImageResource(resId[i]);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setLayoutParams(new ViewPager.LayoutParams());
            viewList.add(view);
        }
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public int getCount() {

            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));

        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return "title";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));

            return viewList.get(position);
        }

    };
}
