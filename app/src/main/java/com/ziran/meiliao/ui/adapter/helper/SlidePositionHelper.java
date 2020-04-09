//package com.ziran.meiliao.ui.adapter.helper;
//
//import com.ziran.meiliao.common.baserx.RxManagerUtil;
//import com.ziran.meiliao.constant.AppConstant;
//import com.ziran.meiliao.ui.adapter.OneSlideAdapter;
//
//import java.util.HashSet;
//
///**
// * @author 吴祖清
// * @version $Rev$
// * @createTime 2018/1/25 16:53
// * @des ${TODO}
// * @updateAuthor $Author$
// * @updateDate 2018/1/25$
// * @updateDes ${TODO}
// */
//
//public class SlidePositionHelper {
//    /**
//     * 选中的下标
//     */
//    protected HashSet<Integer> positionSet = new HashSet<>();
//
//
//    public boolean contains(int position){
//        return positionSet.contains(position);
//    }
//
//    public void addOrRemove(int position, boolean isCheck){
//        if (isCheck) {
//            // 如果包含，则撤销选择
//            positionSet.add(position);
//        } else {
//            // 如果不包含，则添加
//            positionSet.remove(position);
//        }
//        OneSlideAdapter.DeleteItem.setAllSize(getAllSize());
//        OneSlideAdapter.DeleteItem.setSelectSize(getSelectSize());
//        isSelectAll = OneSlideAdapter.DeleteItem.isSelectAll();
//        RxManagerUtil.post(AppConstant.RXTag.UPDATE_SEL, getSelectSize());
//    }
//
//}
