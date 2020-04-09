package com.ziran.meiliao.ui.settings.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseModel;
import com.ziran.meiliao.common.base.BasePresenter;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.irecyclerview.slide.ISlideHelper;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.utils.CanEditUtil;
import com.ziran.meiliao.widget.SmoothCheckBox;

/**
 * author admin
 * create  2017/4/6 11
 * des     取消我的收藏和删除我的下载后刷新的Fragment
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/4/6 11
 * updateDes    ${TODO}
 */

public abstract class DeleteRefreshFragment<T extends BasePresenter, E extends BaseModel> extends CommonRefreshFragment<T, E> {


    private CanEditUtil mCanEditUtil;

    /**
     * 标记需要订阅删除事件 默认为false
     */
    protected boolean needRxDelete = true;

    /**
     * 标记在没有网络的情况下是否可以启动其他Activity
     */
    protected boolean canNotStartAct;

    //    private OneSlideAdapter mOneSlideAdapter;
    @Override
    protected void initView() {
        super.initView();
        View ntb = getActivity().findViewById(R.id.ntb);
        if (ntb instanceof NormalTitleBar) {
            mCanEditUtil = new CanEditUtil(((NormalTitleBar) ntb).getRightTextView());
        }
        if (needRxDelete) {
            mRxManager.on(AppConstant.RXTag.DELETE, new rx.functions.Action1<Integer>() {
                @Override
                public void call(Integer fromType) {
                    deleteItem(fromType);
                }
            });
        }
    }


    /**
     * 删除数据时执行
     *
     * @param fromType
     */
    protected void deleteItem(Integer fromType) {
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (canStartAct()) {
            SmoothCheckBox checkBox = ViewUtil.getView(view, R.id.include_smooth_checkbox);
            if (checkBox != null) {
                checkBox.toggle();
            }
            canNotStartAct = true;
            return;
        }
        canNotStartAct = false;
    }

    public void showEmptyState() {
        if (mAdapter.getItemCount() == 0) {
            showEmtry();
        }
    }

    public boolean canStartAct() {
        return mCanEditUtil.isEdit();
    }


    public void setCheck(boolean check) {
        setCheckAndUpdate(check);
    }

    public void toggle() {
        setCheckAndUpdate(!mAdapter.isCheck());
    }


    //检查是否开启删除选择
    public void setCheckAndUpdate(boolean check) {
        mAdapter.setCheck(check);
        if (mAdapter instanceof ISlideHelper) {
            ISlideHelper adapter = (ISlideHelper) mAdapter;
            if (adapter.isCheck()) {
                adapter.slideOpen();
            } else {
                adapter.slideClose();
                adapter.clearSelect();
            }
        }
    }

    public boolean isCheck() {
        return mAdapter.isCheck();
    }

    //是否选中所有
    public void selectAll() {
        if (mAdapter instanceof ISlideHelper) {
            ISlideHelper adapter = (ISlideHelper) mAdapter;
            adapter.selectAll();
            mRxManager.post(AppConstant.RXTag.UPDATE_SEL, adapter.getSelectSize());
        }
    }


    //删除所有提示
    public void delete(String tips, String type) {
        if (mAdapter instanceof ISlideHelper) {
            ISlideHelper adapter = (ISlideHelper) mAdapter;
            if (adapter.getSelectSize() > 0) {
                adapter.delete(String.format(getString(R.string.delete_toast), tips, adapter.getSelectSize(), type));
            }
        }
    }

}
