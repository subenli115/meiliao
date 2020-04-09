package com.ziran.meiliao.ui.workshops.util;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.constant.BaseItemId;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.BaseItemView;

import java.util.List;
import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/5 21:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/5$
 * @updateDes ${TODO}
 */

public class CheckBaseItemUtil implements BaseItemId {
    public static boolean check(BaseItemView itemView, String tips) {
        if (EmptyUtils.isEmpty(itemView.getContent())) {
            ToastUitl.showShort(tips);
            return false;
        }
        return true;
    }


    public static boolean check(BaseItemView... baseItemViews) {
        for (BaseItemView itemView : baseItemViews) {
            if (!checkTips(itemView)) {
                return false;
            }
        }
        return true;
    }
    public static boolean check(List<BaseItemView> baseItemViews) {
        for (BaseItemView itemView : baseItemViews) {
            if (!checkTips(itemView)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkTips(BaseItemView itemView) {

        switch (itemView.getId()) {
//            case R.id.bivCourse:
//              return   itemView.checkNull("课程不能为空");
//            case R.id.bivAddress:
//              return   itemView.checkNull("人数不能为空");
//            case R.id.bivOfficialPrice:
//              return   itemView.checkNull("人数不能为空");
//            case R.id.bivDays:
//              return   itemView.checkNull("人数不能为空");
//            case R.id.bivCourseProfile:
//              return   itemView.checkNull("人数不能为空");
//            case R.id.bivSponsorMsg:
//              return   itemView.checkNull("人数不能为空");
//            case R.id.bivPeople:
//              return   itemView.checkNull("人数不能为空");
//            case R.id.bivTime:
//                return   itemView.checkNull("时间不能为空");
//            case R.id.bivTeacher:
//                return   itemView.checkNull("老师不能为空");
//            case R.id.bivName:
//                return   itemView.checkNull("姓名不能为空");
            case R.id.bivPhone:
            case R.id.biv_phone:
                if (itemView.checkNull("手机号不能为空")){
                    if (!RegexUtils.isMobileExact(itemView.getContent())){
                        ToastUitl.showShort("手机号不正确");
                        return false;
                    }
                    return  true ;
                }
//            case R.id.bivDemo:
//                return   itemView.checkNull("不能为空");
//            case R.id.bivGoal:
//                return   itemView.checkNull("目的不能为空");
//            case R.id.bivType:
//                return   itemView.checkNull("类型不能为空");
//            case R.id.bivDuration:
//                return   itemView.checkNull("时长不能为空");
//            case R.id.biv_one_price:
//                return   itemView.checkNull("人均价格不能为空");
        }
        return itemView.checkNull("请填写完整数据");
    }

    public static Map<String, String> getTeamBuyMap(String courseId, String authorId, String buyTag, BaseItemView bivPeople, BaseItemView
            bivTime, BaseItemView bivName, BaseItemView bivPhone, BaseItemView bivDemo) {

        Map<String, String> stringMap = MapUtils.getDefMap(true);
        stringMap.put("courseId", courseId);
        stringMap.put("authorId", authorId);
        stringMap.put("buyTag", buyTag); //定制通道标识（名师库、团建库）
        bivPeople.put("supportMembers", stringMap);
        String[] times = bivTime.getContent().split("至");
        if (times.length == 2) {
            stringMap.put("beginTime", times[0]);
            stringMap.put("endTime", times[1]);
        }
        bivPhone.put("phone", stringMap);
        bivName.put("name", stringMap);
        bivDemo.put("remarks", stringMap);
        return stringMap;
    }
}
