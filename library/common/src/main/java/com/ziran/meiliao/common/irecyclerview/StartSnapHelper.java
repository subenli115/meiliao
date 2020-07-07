package com.ziran.meiliao.common.irecyclerview;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/25 9:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/25$
 * @updateDes ${TODO}
 */


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 定位到第一个子View的SnapHelper
 * Created by xmuSistone on 2017/9/19.
 */
public class StartSnapHelper extends LinearSnapHelper {

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] out = new int[2];
        out[0] = 0;
        out[1] = ((VegaLayoutManager) layoutManager).getSnapHeight();
        return out;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        VegaLayoutManager custLayoutManager = (VegaLayoutManager) layoutManager;
        return custLayoutManager.findSnapView();
    }
}