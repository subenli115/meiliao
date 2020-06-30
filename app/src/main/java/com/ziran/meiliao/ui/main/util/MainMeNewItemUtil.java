package com.ziran.meiliao.ui.main.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.ui.me.activity.MyActivityActivity;
import com.ziran.meiliao.ui.settings.activity.WalletActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.widget.MainMeItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:15
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */

public class MainMeNewItemUtil {
    public static final int TYPE_FIRENDS = 1;
    public static final int TYPE_HEALTH = 2;


    public static final int ID_ME_COURSE = 111;
    public static final int ID_ME_ACT = 112;

    public static final int ID_ME_FOLLOW = 113;
    public static final int ID_ME_ZHENGNIAN= 114;
    public static final int ID_ME_WALLET= 115;
    public static final int ID_ME_HEALTH = 201;
    public static final int ID_ME_DOCTOR = 202;
    public static final int ID_ME_REPORT = 203;
    public static final int ID_ME_EQUIPMENT = 204;

    public static List<MainMeItemView.Items> getData(int type) {
        List<MainMeItemView.Items> itemsList = new ArrayList<>();
        switch (type) {
            case TYPE_FIRENDS:
                itemsList.add(new MainMeItemView.Items(ID_ME_ZHENGNIAN, R.mipmap.me_record, "记录"));
                itemsList.add(new MainMeItemView.Items(ID_ME_WALLET, R.mipmap.me_concern, "钱包"));
                itemsList.add(new MainMeItemView.Items(ID_ME_COURSE, R.mipmap.me_course, "已购"));
                itemsList.add(new MainMeItemView.Items(ID_ME_ACT, R.mipmap.me_active, "活动"));
//                itemsList.add(new MainMeItemView.Items(ID_ME_FOLLOW, R.mipmap.me_concern, "我的关注"));
                break;
            case TYPE_HEALTH:
                itemsList.add(new MainMeItemView.Items(ID_ME_HEALTH, R.mipmap.me_health, "健康云"));
                itemsList.add(new MainMeItemView.Items(ID_ME_DOCTOR, R.mipmap.me_doctor, "家庭医生"));
                itemsList.add(new MainMeItemView.Items(ID_ME_REPORT, R.mipmap.me_report, "健康报告"));
                itemsList.add(new MainMeItemView.Items(ID_ME_EQUIPMENT, R.mipmap.me_equipment, "设备接入"));
                break;
        }
        return itemsList;
    }

    public static void onItemClick(Context context, MainMeItemView.Items item, int position,View view) {
        switch (item.getId()) {
            case ID_ME_COURSE:
                break;
            case ID_ME_ACT:
                startActivity(context,  MyActivityActivity.class);
                break;
            case ID_ME_FOLLOW:
                ToastUitl.showShort(R.string.not_function);
                break;
            case ID_ME_ZHENGNIAN:
                break;
            case ID_ME_WALLET:
                if (CheckUtil.check(context, view)) {
                    WalletActivity.startAction(context);
                }
                break;
            case ID_ME_HEALTH:
                ToastUitl.showShort(R.string.not_function);
                break;
            case ID_ME_DOCTOR:
                ToastUitl.showShort(R.string.not_function);
                break;
            case ID_ME_REPORT:
                ToastUitl.showShort(R.string.not_function);
                break;
            case ID_ME_EQUIPMENT:
                ToastUitl.showShort(R.string.not_function);
                break;
        }
    }
    public static void startActivity(Context context,Class clz){
        Intent i = new Intent(context,clz);
        context.startActivity(i);
    }
}
