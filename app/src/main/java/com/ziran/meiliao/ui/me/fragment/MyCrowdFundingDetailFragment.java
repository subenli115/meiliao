package com.ziran.meiliao.ui.me.fragment;

import android.support.v4.app.Fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.base.CommonViewPagerFragment;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 14:02
 * @des ${发起众筹查看明细页面}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class MyCrowdFundingDetailFragment extends CommonViewPagerFragment {


    @Override
    protected void initView() {
        super.initView();
        ntb.setTitleText("众筹明细");
    }

    @Override
    protected void initFragments(List<Fragment> fragments) {
        fragments.add(new MyCrowdFundingDetailBuyFragment());
        fragments.add(new MyCrowdFundingDetailRefundFragment());
    }

    @Override
    protected int getArrayResId() {
        return R.array.my_crowd_funding_tabs;
    }
}
