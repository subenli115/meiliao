package com.ziran.meiliao.common.imagePager;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.base.BaseFragmentAdapter;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 查看大图 glide
 * Created by jaydenxiao
 */
public class ImagePreviewActivity extends BaseActivity {
    public static final String INTENT_IMGURLS = "imgurls";
    public static final String INTENT_POSITION = "position";

    public static void startImagePagerActivity(Activity activity, ArrayList<String> imgUrls, int position) {
        Intent intent = new Intent(activity, ImagePreviewActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, imgUrls);
        intent.putExtra(INTENT_POSITION, position);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ImagePreviewActivity.this.finish();
            ImagePreviewActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_image_preview;
    }

    private TextView tvTitle;
    private ArrayList<String> imgUrls;

    @Override
    public void initView() {
        //设置透明状态栏
        Constant.deleteUrl.clear();
        final ViewPager viewPager = (ViewPagerFixed) findViewById(R.id.pager);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int startPos = getIntent().getIntExtra(INTENT_POSITION, 0);
        imgUrls = getIntent().getStringArrayListExtra(INTENT_IMGURLS);
      final  List<Fragment> fragmentList = new ArrayList<>();
        for (String imgUrl : imgUrls) {
            fragmentList.add(ImageFragment.getInstance(imgUrl));
        }
        final BaseFragmentAdapter fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(imgUrls.size());
//        final ImageAdapter mAdapter = new ImageAdapter(this);
//        mAdapter.setDatas(imgUrls);
//        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                setTitleText(position);
            }
        });
        viewPager.setCurrentItem(startPos);
        setTitleText(startPos);
        findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewPager.getCurrentItem();
                if ( pos < imgUrls.size()) {
                    LogUtils.logd("getCurrentItem:" + pos);
                    Constant.deleteUrl.add(imgUrls.remove(pos));

                    if (EmptyUtils.isEmpty(imgUrls)){
                        finish();
                        return;
                    }
                    fragmentList.remove(pos);
                    fragmentAdapter.setFragments(getSupportFragmentManager(),fragmentList,imgUrls);
//                    viewPager.setAdapter(fragmentAdapter);
                    setTitleText(viewPager.getCurrentItem() == imgUrls.size() ? (viewPager.getCurrentItem() - 1) : viewPager
                            .getCurrentItem());
                } else {
                    finish();
                }
            }
        });
    }

    private void setTitleText(int startPos) {
        tvTitle.setText(String.format("%d/%d", startPos + 1, imgUrls.size()));
    }

    @Override
    public void initPresenter() {

    }
//
//    private class ImageAdapter extends PagerAdapter {
//
//        private List<String> datas = new ArrayList<>();
//        private LayoutInflater inflater;
//        private Context context;
//
//        public void setDatas(List<String> datas) {
//            if (datas != null) this.datas = datas;
//        }
//
//        private ImageAdapter(Context context) {
//            this.context = context;
//            this.inflater = LayoutInflater.from(context);
//
//        }
//
//        @Override
//        public int getCount() {
//            if (datas == null) return 0;
//            return datas.size();
//        }
//
//
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) {
//            View view = inflater.inflate(R.layout.item_pager_image, container, false);
//            if (view != null) {
//                final PhotoView imageView = (PhotoView) view.findViewById(R.id.image);
//
//                //单击图片退出
//                imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
//                    @Override
//                    public void onPhotoTap(View view, float x, float y) {
//                        ImagePreviewActivity.this.finish();
//                    }
//                });
//
//                //loading
//                final ProgressBar loading = new ProgressBar(context);
//                FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                loadingLayoutParams.gravity = Gravity.CENTER;
//                loading.setLayoutParams(loadingLayoutParams);
//                ((FrameLayout) view).addView(loading);
//
//                final String imgurl = datas.get(position);
//
//                loading.setVisibility(View.VISIBLE);
//
//                String url = imgurl;
//
//                Glide.with(context).load(url).asBitmap().fitCenter().thumbnail(0.3f).into(new CommonTarget(imageView) {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
//                        loading.setVisibility(View.GONE);
//                        super.onResourceReady(resource, glideAnimation);
//                    }
//
//                    @Override
//                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                        loading.setVisibility(View.GONE);
//                        e.printStackTrace();
//                        super.onLoadFailed(e, errorDrawable);
//                    }
//                });
//
//                container.addView(view, 0);
//            }
//            return view;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view.equals(object);
//        }
//
//        @Override
//        public void restoreState(Parcelable state, ClassLoader loader) {
//        }
//
//        @Override
//        public Parcelable saveState() {
//            return null;
//        }
//
//
//        public void remove(int pos) {
//            Constant.deleteUrl.add(datas.remove(pos));
////            notifyDataSetChanged();
//            LogUtils.logd("deleteUrl:" + Constant.deleteUrl.toString());
//        }
//
//        public boolean checkIndex(int pos) {
//            return pos < getCount();
//        }
//    }
}
