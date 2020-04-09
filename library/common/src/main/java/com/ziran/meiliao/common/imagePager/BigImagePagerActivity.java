package com.ziran.meiliao.common.imagePager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.CommonTarget;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;



/**
 * 查看大图 glide
 * Created by jaydenxiao
 */
public class BigImagePagerActivity extends BaseActivity {
    public static final String INTENT_IMGURLS = "imgurls";
    public static final String INTENT_POSITION = "position";
    public static final String INTENT_PREFIX = "prefix";
    private List<View> guideViewList = new ArrayList<>();

    public static void startImagePagerActivity(Activity activity, List<String> imgUrls, int position){
        Intent intent = new Intent(activity, BigImagePagerActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, new ArrayList<>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    public static void startImagePagerActivity(Activity activity, List<String> imgUrls, int position,String prefix){
        Intent intent = new Intent(activity, BigImagePagerActivity.class);
        intent.putStringArrayListExtra(INTENT_IMGURLS, new ArrayList<>(imgUrls));
        intent.putExtra(INTENT_POSITION, position);
        intent.putExtra(INTENT_PREFIX, prefix);
//        LogUtils.logd("prefix"+prefix);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
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
            BigImagePagerActivity.this.finish();
            BigImagePagerActivity.this.overridePendingTransition(R.anim.fade_in,
                    R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_image_pager;
    }
    private String prefix ;
    @Override
    public void initView() {
        //设置透明状态栏
        SetTranslanteBar();

        ViewPager viewPager = (ViewPagerFixed) findViewById(R.id.pager);
        LinearLayout guideGroup = (LinearLayout) findViewById(R.id.guideGroup);

        int startPos = getIntent().getIntExtra(INTENT_POSITION, 0);
        prefix = getIntent().getStringExtra(INTENT_PREFIX);
        ArrayList<String> imgUrls = getIntent().getStringArrayListExtra(INTENT_IMGURLS);

        ImageAdapter mAdapter = new ImageAdapter(this);
        mAdapter.setDatas(imgUrls);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                for(int i=0; i<guideViewList.size(); i++){
                    guideViewList.get(i).setSelected(i==position ? true : false);
                }
            }

        });
        viewPager.setCurrentItem(startPos);

        addGuideView(guideGroup, startPos, imgUrls);
    }

    @Override
    public void initPresenter() {

    }

    private void addGuideView(LinearLayout guideGroup, int startPos, ArrayList<String> imgUrls) {
        if(imgUrls!=null && imgUrls.size()>0){
            guideViewList.clear();
            for (int i=0; i<imgUrls.size(); i++){
                View view = new View(this);
                view.setBackgroundResource(R.drawable.selector_guide_bg);
                view.setSelected(i == startPos);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.gudieview_width),
                        getResources().getDimensionPixelSize(R.dimen.gudieview_heigh));
                layoutParams.setMargins(10, 0, 0, 0);
                guideGroup.addView(view, layoutParams);
                guideViewList.add(view);
            }
        }
    }

    private  class ImageAdapter extends PagerAdapter{

        private List<String> datas = new ArrayList<>();
        private LayoutInflater inflater;
        private Context context;

        public void setDatas(List<String> datas) {
            if(datas != null )
                this.datas = datas;
        }

        private ImageAdapter(Context context){
            this.context = context;
            this.inflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            if(datas == null) return 0;
            return datas.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = inflater.inflate(R.layout.item_pager_image, container, false);
            if(view != null){
                final PhotoView imageView = (PhotoView) view.findViewById(R.id.image);

                //单击图片退出
                imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {
                        BigImagePagerActivity.this.finish();
//                        BigImagePagerActivity.this.overridePendingTransition(R.anim.act_fade_in_center,
//                                R.anim.act_fade_out_center);
//                        BigImagePagerActivity.this.overridePendingTransition(R.anim.act_fade_out_center,
//                                R.anim.act_fade_in_center);
                    }
                });

                //loading
                final ProgressBar loading = new ProgressBar(context);
                FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingLayoutParams.gravity = Gravity.CENTER;
                loading.setLayoutParams(loadingLayoutParams);
                ((FrameLayout)view).addView(loading);

                final String imgurl = datas.get(position);

                loading.setVisibility(View.VISIBLE);

                String url ;
                if (EmptyUtils.isNotEmpty(prefix)){
                    url = prefix+imgurl;
                }else{
                    url = imgurl;
                }
                LogUtils.logd("format   "+url);
                Glide.with(context).load(url)
                        .asBitmap().fitCenter()
//                        .placeholder(R.drawable.ic_empty_picture)
//                        .error(R.drawable.ic_empty_picture)
                        .thumbnail(0.3f)
                        .into(new CommonTarget(imageView){
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                                loading.setVisibility(View.GONE);
                                super.onResourceReady(resource, glideAnimation);
                            }
                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                loading.setVisibility(View.GONE);
                                e.printStackTrace();
                                super.onLoadFailed(e, errorDrawable);
                            }
                        });

                container.addView(view, 0);
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }


    }
}
