package com.citypicker.citywheel;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.citypicker.Interface.OnCityItemClickListener;
import com.citypicker.R;
import com.citypicker.bean.CityBean;
import com.citypicker.bean.DistrictBean;
import com.citypicker.bean.ProvinceBean;
import com.citypicker.citypickerview.widget.CanShow;
import com.citypicker.citypickerview.widget.wheel.OnWheelChangedListener;
import com.citypicker.citypickerview.widget.wheel.WheelView;
import com.citypicker.citypickerview.widget.wheel.adapters.ArrayWheelAdapter;


/**
 * 省市区三级选择
 * 作者：liji on 2015/12/17 10:40
 * 邮箱：lijiwork@sina.com
 */
public class CityPickerView implements CanShow, OnWheelChangedListener {
    
    private String TAG = "citypicker_log";
    
    private PopupWindow popwindow;
    
    private View popview;
    
    private WheelView mViewProvince;
    
    private WheelView mViewCity;
    
    private WheelView mViewDistrict;
    
    private RelativeLayout mRelativeTitleBg;
    
    private TextView mTvOK;
    
    private TextView mTvTitle;
    private View viewMask;

    private TextView mTvCancel;
    
    private OnCityItemClickListener mBaseListener;
    
    private CityParseHelper parseHelper;
    
    private CityConfig config;
    
    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        mBaseListener = listener;
    }
    
    public CityPickerView(final CityConfig config) {
        this.config = config;
        parseHelper = new CityParseHelper(config);
        LayoutInflater layoutInflater = LayoutInflater.from(config.getContext());
        popview = layoutInflater.inflate(R.layout.pop_citypicker, null);
        
        mViewProvince = (WheelView) popview.findViewById(R.id.id_province);
        mViewCity = (WheelView) popview.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) popview.findViewById(R.id.id_district);
        mRelativeTitleBg = (RelativeLayout) popview.findViewById(R.id.rl_title);
        mTvOK = (TextView) popview.findViewById(R.id.tv_confirm);
        mTvTitle = (TextView) popview.findViewById(R.id.tv_title);
        viewMask = popview.findViewById(R.id.view_mask);
        mTvCancel = (TextView) popview.findViewById(R.id.tv_cancel);
        
        popwindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popwindow.setAnimationStyle(R.style.AnimBottom);
        popwindow.setBackgroundDrawable(new ColorDrawable());
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(false);
        popwindow.setFocusable(true);
        
        popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (config.isShowBackground()) {
//                    utils.setBackgroundAlpha(config.getContext(), 1.0f);
                }
            }
        });

        viewMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });

        /**
         * 设置标题背景颜色
         */
        if (!TextUtils.isEmpty(config.getTitleBackgroundColorStr())) {
            mRelativeTitleBg.setBackgroundColor(Color.parseColor(config.getTitleBackgroundColorStr()));
        }
        
        //标题
        if (!TextUtils.isEmpty(config.getTitle())) {
            mTvTitle.setText(config.getTitle());
        }
        
        //标题文字颜色
        if (!TextUtils.isEmpty(config.getTitleTextColorStr())) {
            mTvTitle.setTextColor(Color.parseColor(config.getTitleTextColorStr()));
        }
        
        //设置确认按钮文字颜色
        if (!TextUtils.isEmpty(config.getConfirmTextColorStr())) {
            mTvOK.setTextColor(Color.parseColor(config.getConfirmTextColorStr()));
        }
        
        //设置取消按钮文字颜色
        if (!TextUtils.isEmpty(config.getCancelTextColorStr())) {
            mTvCancel.setTextColor(Color.parseColor(config.getCancelTextColorStr()));
        }
        
        //只显示省市两级联动
        if (config.getWheelType() == CityConfig.WheelType.PRO) {
            mViewCity.setVisibility(View.GONE);
            mViewDistrict.setVisibility(View.GONE);
        }
        else if (config.getWheelType() == CityConfig.WheelType.PRO_CITY) {
            mViewDistrict.setVisibility(View.GONE);
        }
        else {
            mViewProvince.setVisibility(View.VISIBLE);
            mViewCity.setVisibility(View.VISIBLE);
            mViewDistrict.setVisibility(View.VISIBLE);
        }
        
        //解析初始数据
        if (parseHelper == null || parseHelper.getProvinceBeanArrayList().isEmpty()) {
            parseHelper.initData(config.getContext());
        }
        
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseListener.onCancel();
                hide();
            }
        });
        
        //确认选择
        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parseHelper != null) {
                    if (config.getWheelType() == CityConfig.WheelType.PRO) {
                        mBaseListener.onSelected(parseHelper.getProvinceBean());
                    }
                    else if (config.getWheelType() == CityConfig.WheelType.PRO_CITY) {
                        mBaseListener.onSelected(parseHelper.getProvinceBean(), parseHelper.getCityBean());
                    }
                    else {
                        mBaseListener.onSelected(parseHelper.getProvinceBean(),
                                parseHelper.getCityBean(),
                                parseHelper.getDistrictBean());
                    }
                    
                }
                hide();
            }
        });
        
    }
    
    /**
     * 加载数据
     */
    private void setUpData() {
        
        if (parseHelper == null || config == null) {
            return;
        }
        
        int provinceDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultProvinceName()) && parseHelper.getProvinceBeenArray().length > 0) {
            for (int i = 0; i < parseHelper.getProvinceBeenArray().length; i++) {
                if (parseHelper.getProvinceBeenArray()[i].getName().contains(config.getDefaultProvinceName())) {
                    provinceDefault = i;
                    break;
                }
            }
        }
        
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<ProvinceBean>(config.getContext(),
                parseHelper.getProvinceBeenArray());
        mViewProvince.setViewAdapter(arrayWheelAdapter);
        //获取所设置的省的位置，直接定位到该位置
        if (-1 != provinceDefault) {
            mViewProvince.setCurrentItem(provinceDefault);
        }
        // 设置可见条目数量
        mViewProvince.setVisibleItems(config.getVisibleItems());
        mViewCity.setVisibleItems(config.getVisibleItems());
        mViewDistrict.setVisibleItems(config.getVisibleItems());
        mViewProvince.setCyclic(config.isProvinceCyclic());
        mViewCity.setCyclic(config.isCityCyclic());
        mViewDistrict.setCyclic(config.isDistrictCyclic());
        arrayWheelAdapter.setPadding(config.getPadding());
        arrayWheelAdapter.setTextColor(Color.parseColor(config.getTextColor()));
        arrayWheelAdapter.setTextSize(config.getTextSize());
        
        updateCities();
        
        updateAreas();
    }
    
    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        
        if (parseHelper == null || config == null) {
            return;
        }
        
        //省份滚轮滑动的当前位置
        int pCurrent = mViewProvince.getCurrentItem();
        
        //省份选中的名称
        ProvinceBean mProvinceBean = parseHelper.getProvinceBeenArray()[pCurrent];
        parseHelper.setProvinceBean(mProvinceBean);
        
        if (parseHelper.getPro_CityMap() == null) {
            return;
        }
        
        CityBean[] cities = parseHelper.getPro_CityMap().get(mProvinceBean.getName());
        if (cities == null) {
            return;
        }
        
        //设置最初的默认城市
        int cityDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultCityName()) && cities.length > 0) {
            for (int i = 0; i < cities.length; i++) {
                if (config.getDefaultCityName().contains(cities[i].getName())) {
                    cityDefault = i;
                    break;
                }
            }
        }
        
        ArrayWheelAdapter cityWheel = new ArrayWheelAdapter<CityBean>(config.getContext(), cities);
        // 设置可见条目数量
        cityWheel.setTextColor(Color.parseColor(config.getTextColor()));
        cityWheel.setTextSize(config.getTextSize());
        mViewCity.setViewAdapter(cityWheel);
        if (-1 != cityDefault) {
            mViewCity.setCurrentItem(cityDefault);
        }
        else {
            mViewCity.setCurrentItem(0);
        }
        
        cityWheel.setPadding(config.getPadding());
        
        updateAreas();
    }
    
    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        
        int pCurrent = mViewCity.getCurrentItem();
        if (parseHelper.getPro_CityMap() == null || parseHelper.getCity_DisMap() == null) {
            return;
        }
        
        if (config.getWheelType() == CityConfig.WheelType.PRO_CITY
                || config.getWheelType() == CityConfig.WheelType.PRO_CITY_DIS) {
            
            CityBean mCityBean = parseHelper.getPro_CityMap().get(parseHelper.getProvinceBean().getName())[pCurrent];
            parseHelper.setCityBean(mCityBean);
            
            if (config.getWheelType() == CityConfig.WheelType.PRO_CITY_DIS) {
                
                DistrictBean[] areas = parseHelper.getCity_DisMap()
                        .get(parseHelper.getProvinceBean().getName() + mCityBean.getName());
                
                if (areas == null) {
                    return;
                }
                
                int districtDefault = -1;
                if (!TextUtils.isEmpty(config.getDefaultDistrict()) && areas.length > 0) {
                    for (int i = 0; i < areas.length; i++) {
                        if (config.getDefaultDistrict().contains(areas[i].getName())) {
                            districtDefault = i;
                            break;
                        }
                    }
                }
                
                ArrayWheelAdapter districtWheel = new ArrayWheelAdapter<DistrictBean>(config.getContext(), areas);
                // 设置可见条目数量
                districtWheel.setTextColor(Color.parseColor(config.getTextColor()));
                districtWheel.setTextSize(config.getTextSize());
                mViewDistrict.setViewAdapter(districtWheel);
                
                DistrictBean mDistrictBean = null;
                if (parseHelper.getDisMap() == null) {
                    return;
                }
                
                if (-1 != districtDefault) {
                    mViewDistrict.setCurrentItem(districtDefault);
                    //获取第一个区名称
                    mDistrictBean = parseHelper.getDisMap().get(parseHelper.getProvinceBean().getName()
                            + mCityBean.getName() + config.getDefaultDistrict());
                }
                else {
                    mViewDistrict.setCurrentItem(0);
                    if (areas.length > 0) {
                        mDistrictBean = areas[0];
                    }
                }
                
                parseHelper.setDistrictBean(mDistrictBean);
                districtWheel.setPadding(config.getPadding());
                
            }
        }
        
    }
    
    @Override
    public void setType(int type) {
        
    }
    
    @Override
    public void show() {
        if (!isShow()) {
            setUpData();
            popwindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        }
    }
    
    @Override
    public void hide() {
        if (isShow()) {
            popwindow.dismiss();
        }
    }
    
    @Override
    public boolean isShow() {
        if (config.isShowBackground()) {
            viewMask.setBackgroundColor(Color.parseColor("#30000000"));
        }else{
            viewMask.setBackgroundColor(Color.TRANSPARENT);
        }
        return popwindow.isShowing();
    }
    
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        }
        else if (wheel == mViewCity) {
            updateAreas();
        }
        else if (wheel == mViewDistrict) {
            if (parseHelper != null && parseHelper.getCity_DisMap() != null) {
                
                DistrictBean mDistrictBean = parseHelper.getCity_DisMap()
                        .get(parseHelper.getProvinceBean().getName() + parseHelper.getCityBean().getName())[newValue];
                
                parseHelper.setDistrictBean(mDistrictBean);
            }
        }
    }
    
}
