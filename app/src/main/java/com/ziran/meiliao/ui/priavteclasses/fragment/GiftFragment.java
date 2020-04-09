package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;

import butterknife.Bind;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/20 14:34
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/20$
 * @updateDes ${TODO}
 */

public class GiftFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.gridView)
    GridView mGridView;
    private int type;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_gift;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
//        mGridView.setAdapter();
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
