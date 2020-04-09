package com.ziran.meiliao.ui.main.fragment;


import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.citypicker.citylist.sortlistview.CharacterParser;
import com.citypicker.citylist.sortlistview.PinyinComparator;
import com.citypicker.citylist.sortlistview.SortAdapter;
import com.citypicker.citylist.sortlistview.SortModel;
import com.citypicker.citylist.utils.CityDataDb;
import com.citypicker.citylist.widget.ClearEditText;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.SimpleTextWatcher;
import com.ziran.meiliao.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * 地区选择界面Fragment
 */
public class RegionFragment extends BaseFragment {


    @Bind(R.id.filter_edit)
    ClearEditText mSearchView;
    @Bind(R.id.tv_region_cancel)
    TextView tvCancel;
    @Bind(R.id.sidrbar)
    SideBar mSideBar;
    @Bind(R.id.tv_region_toast)
    TextView tvToast;
    @Bind(R.id.lsv_region_country)
    ListView lsvCountry;
    SortAdapter mCityAdapter;
    List<SortModel> mCityItems;
    private PinyinComparator pinyinComparator;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_region;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {

        mSideBar.setTextView(tvToast);
        pinyinComparator = new PinyinComparator();
        //设置右侧触摸监听
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = mCityAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    lsvCountry.setSelection(position);
                }
            }
        });

        lsvCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                SortModel item = (SortModel) mCityAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("cityData",item);
                RxManagerUtil.post(AppConstant.RXTag.CITY_DATA,item);
                getActivity().setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        mCityItems = CityDataDb.get().getData(getContext());
        // 根据a-z进行排序源数据
        Collections.sort(mCityItems, new PinyinComparator());
        mCityAdapter = new SortAdapter(getContext(), mCityItems);
        lsvCountry.setAdapter(mCityAdapter);
        //根据输入框输入值的改变来过滤搜索
        mSearchView.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }
        });
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = mCityItems;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : mCityItems) {
                String name = sortModel.getName();
                if (name.contains(filterStr) || CharacterParser.getInstance().getSelling(name).startsWith(filterStr)) {
                    filterDateList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        mCityAdapter.updateListView(filterDateList);
    }
}
