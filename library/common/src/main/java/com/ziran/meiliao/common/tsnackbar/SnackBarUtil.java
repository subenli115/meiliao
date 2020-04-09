package com.ziran.meiliao.common.tsnackbar;

import android.app.Activity;
import android.view.View;
import com.ziran.meiliao.common.R;
/**
 * author admin
 * create  2017/4/7 11
 * des     ${TODO}
 * <p>
 * updateAuthor     $admin
 * updateDate   2017/4/7 11
 * updateDes    ${TODO}
 */

public class SnackBarUtil {

    public  static void init(Activity activity) {
        //跟布局为CoordinatorLayout,并且添加了fitsSystemWindows属性,需要调用该方法
        //建议将该属性添加在Toolbar上或者AppBarLayout上
        TSnackBar.setCoordinatorLayoutFitsSystemWindows(true);

        //若将fitsSystemWindows添加在AppBarLayout或者Toolbar上,则不用调用此方法
        if (LUtils.hasKitKat()) {
            LUtils.instance(activity)
                    .setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static void makeSuccess(View view,String text) {
        TSnackBar.make(view, text, Prompt.SUCCESS)
                .show();
    }

    public static void makeError(View view,String text) {
        TSnackBar.make(view, text, Prompt.ERROR)
                .show();
    }

    public static  void makeWarning(View view,String text) {
        TSnackBar.make(view, text, Prompt.WARNING)
                .show();
    }
}
