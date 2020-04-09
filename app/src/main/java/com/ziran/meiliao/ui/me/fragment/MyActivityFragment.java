package com.ziran.meiliao.ui.me.fragment;

import android.support.v4.app.Fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.base.CommonViewPagerFragment;
import com.ziran.meiliao.ui.settings.fragment.MyEnrolActFragment;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 14:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class MyActivityFragment extends CommonViewPagerFragment {



    @Override
    protected void initFragments(List<Fragment> fragments) {
        fragments.add(new MyTeamFragment());
        fragments.add(new MyCrowdFundingFragment());
        fragments.add(new MyEnrolActFragment());
    }

    @Override
    protected int getArrayResId() {
        return R.array.my_activity_tabs;
    }
}
