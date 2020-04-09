package com.ziran.meiliao.module;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.ziran.meiliao.common.compressorutils.FileUtil;

import java.io.File;
import java.io.InputStream;

/**
 *  统一配置Glide模型
 * Created by Administrator on 2017/1/7.
 */

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
        //设置缓存的目录
        builder.setDiskCache(
                new DiskCache.Factory() {
                    @Override
                    public DiskCache build() {
                        File cacheLocation = new File( FileUtil.getImageCacheFolder());
                        cacheLocation.mkdirs();
                        return DiskLruCacheWrapper.get(cacheLocation, 100 * 1024 * 1024);
                    }
                });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        //处理信任所有证书
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

}
