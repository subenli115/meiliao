package com.ziran.meiliao.common.irecyclerview.slide;

import java.util.ArrayList;
import java.util.List;

/**
 * author 吴祖清
 * create  2017/3/8
 * des     打开和关闭滑动的工具类
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class SlideHelper {
    
    /**
     * 滑动集合
     */
    private List<ISlide> mISlides = new ArrayList<>();
    
    /**
     * 添加滑动对象
     *
     * @param iSlide 滑动对象
     */
    public void add (ISlide iSlide) {
        mISlides.add(iSlide);
    }
    
    /**
     * 移除滑动对象
     *
     * @param iSlide 滑动对象
     */
    public void remove (ISlide iSlide) {
        mISlides.remove(iSlide);
    }
    
    /**
     * 清空集合
     */
    public void clear () {
        mISlides.clear();
    }
    
    public List<ISlide> getISlideList () {
        return mISlides;
    }
    
    /**
     * 打开滑动
     */
    public void slideOpen () {
        for (ISlide slide : mISlides) {
            slide.slideOpen();
        }
    }
    
    /**
     * 关闭滑动
     */
    public void slideClose () {
        for (ISlide slide : mISlides) {
            slide.slideClose();
        }
    }


}
