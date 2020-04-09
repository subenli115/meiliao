package com.ziran.meiliao.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.irecyclerview.slide.ISlideHelper;
import com.ziran.meiliao.common.irecyclerview.slide.SlideHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.widget.SmoothCheckBox;
import com.wevey.selector.dialog.MDAlertDialog;
import com.wevey.selector.dialog.SimpleDialogClickListener;

import java.util.HashSet;


/**
 * author 吴祖清
 * create  2017/3/8
 * des     可以显示复选框和删除的适配器
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public abstract class OneSlideAdapter<T> extends CommonRecycleViewAdapter<T> implements ISlideHelper {
    /**
     * 选中的下标
     */
    protected HashSet<Integer> positionSet;

    /**
     * 滑动偏移工具类
     */
    private SlideHelper mISlideHelper;
    /**
     * 是否选中所有item
     */
    private boolean isSelectAll;

    /**
     * 是那个界面的adapter
     */
    protected int from_type = 0;
    public static final int FROM_MUSIC = 0;
    public static final int FROM_ALBUM_MUSIC = 8;
    public static final int FROM_UPDATE_MUSIC = 9;
    public static final int FROM_ALBUM = 1;
    public static final int FROM_VIDEO = 5;
    public static final int FROM_COURSE = 2;
    public static final int FROM_GUANZHU = 15;
    public static final int FROM_ACT = 3;

    public OneSlideAdapter(Context context, int layoutId) {
        super(context, layoutId);
        mISlideHelper = new SlideHelper();
        positionSet = new HashSet<>();
    }

    /**
     * 创建OneSlideViewHolder
     *
     * @param parent   父容器
     * @param viewType 布局类型
     * @return OneSlideViewHolder对象
     */
    @Override
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        OneSlideViewHolder viewHolder = OneSlideViewHolder.get(mContext, null, parent, mLayoutId, -1);
        mISlideHelper.add(viewHolder);
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    /**
     * 绑定  OneSlideViewHolder
     *
     * @param holder   OneSlideViewHolder对象
     * @param position 下标
     */
    @Override
    public void onBindViewHolder(ViewHolderHelper holder, final int position) {
        OneSlideViewHolder slideViewHolder = (OneSlideViewHolder) holder;
        slideViewHolder.onBindSlide(holder.getView(onBindSlideViewId()));
        holder.updatePosition(position);
        //绑定数据
        convertData(slideViewHolder, mDatas.get(position), position);

        SmoothCheckBox smoothCheckBox = holder.getView(R.id.include_smooth_checkbox);
        slideViewHolder.setCheckBox(smoothCheckBox);
        smoothCheckBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                addOrRemove(position, isChecked);
            }
        });
        if (isCheck() && !smoothCheckBox.isShown()) {
            smoothCheckBox.setVisibility(View.VISIBLE);
        }
        smoothCheckBox.setChecked(positionSet.contains(position));
    }


    /**
     * @return 绑定偏移的控件id
     */
    public abstract int onBindSlideViewId();

    /**
     * 绑定数据
     *
     * @param helper   OneSlideViewHolder对象对象
     * @param itemData item数据
     * @param position 下标
     */
    public abstract void convertData(OneSlideViewHolder helper, T itemData, int position);

    //无用
    @Override
    public void convert(ViewHolderHelper helper, T t, int position) {
    }

    /**
     * 打开偏移
     */
    public void slideOpen() {
        mISlideHelper.slideOpen();
    }

    /**
     * 关闭偏移
     */
    public void slideClose() {
        mISlideHelper.slideClose();
    }

    /**
     * 操作Item记录集合
     */
    public void addOrRemove(int position, boolean isCheck) {
        if (isCheck) {
            // 如果包含，则撤销选择
            positionSet.add(position);
        } else {
            // 如果不包含，则添加
            positionSet.remove(position);
        }
        DeleteItem.setAllSize(getItemCount());
        DeleteItem.setSelectSize(getSelectSize());
        isSelectAll = DeleteItem.isSelectAll();
        RxManagerUtil.post(AppConstant.RXTag.UPDATE_SEL, getSelectSize());
    }

    public void setFromType(int fromType) {
        this.from_type = fromType;
    }

    /**
     * 删除对象
     */
    public static class DeleteItem {
        /**
         * 选中的数量
         */
        private static int selectSize;

        /**
         * 总数量
         */
        private static int allSize;

        public static boolean isSelectAll() {
            return allSize != 0 && allSize == selectSize;
        }

        public static int getSelectSize() {
            return selectSize;
        }

        public static void setSelectSize(int selectSize) {
            DeleteItem.selectSize = selectSize;
        }

        public static int getAllSize() {
            return allSize;
        }

        public static void setAllSize(int allSize) {
            DeleteItem.allSize = allSize;
        }
    }

    /**
     * 全选或取消全选
     */
    public void selectAll() {
        if (!isSelectAll) {
            positionSet.clear();
            isSelectAll = true;
            for (int i = 0; i < mDatas.size(); i++) {
                positionSet.add(i);
            }
            notifyDataSetChanged();
        } else {
            positionSet.clear();
            isSelectAll = false;
            for (int i = 0; i < mDatas.size(); i++) {
                positionSet.remove(i);
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 清楚选中的数据
     */
    public void clearSelect() {
        positionSet.clear();
    }

    /**
     * 删除选中的数据
     *
     * @param msg 对话框提示的内容
     */
    public void delete(String msg) {
        MDAlertDialog.createDialog(mContext, msg, new SimpleDialogClickListener() {
            @Override
            public void clickRightButton(Dialog dialog, View view) {
                dialog.dismiss();
                HashSet<T> valueSet = new HashSet<>();
                for (Integer integer : positionSet) {
                    valueSet.add(get(integer));
                }
                for (T t : valueSet) {
                    delete(t);
                }
                deleteSlef(valueSet);
                removeAll(valueSet);
                clearSelect();
                RxManagerUtil.post(AppConstant.RXTag.CLOSE, "1");
                RxManagerUtil.post(AppConstant.RXTag.DELETE, from_type);
            }
        });
    }

    /**
     * 删除单个item
     *
     * @param t item数据
     */
    protected void delete(T t) {

    }

    /**
     * 删除单个item
     *
     * @param valueSet item数据集
     */
    protected void deleteSlef(HashSet<T> valueSet) {
    }

    /**
     * 获取选中的数量
     *
     * @return 选中的数量
     */
    public int getSelectSize() {
        return positionSet.size();
    }
}
