package com.ziran.meiliao.ui.priavteclasses.presenter;

import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.bean.TrailerDetailBean;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKCourseProfileContract;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/11 9:58
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/11$
 * @updateDes ${TODO}
 */

public class SJKCourseProfilePresenter extends SJKCourseProfileContract.Presenter {


    @Override
    public void getCourseDetail(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<SJKLiveDetailProfileBean>(SJKLiveDetailProfileBean.class,mView) {
            @Override
            public void onSuccess(SJKLiveDetailProfileBean result) {
                if (result != null)
                {

                    mView.showCourseDetail(result.getData());
                }

            }
        });
    }
    private List<VideoSectionEntry> changeData(List<VideoSectionEntry> videoSectionEntries) {
        List<VideoSectionEntry> result = new ArrayList<>();
        Set<String> sets = new HashSet<>();
        for (int i = 0; i < videoSectionEntries.size(); i++) {
            VideoSectionEntry sectionEntry = videoSectionEntries.get(i);
            String[] monthAndDayByFormat = TimeUtil.getMonthAndDayByFormat(sectionEntry.getStartTime());
            if (i == 0) {
                sectionEntry.setSelect(true);
                sectionEntry.setFree(true);
            }
            if (sets.contains(monthAndDayByFormat[0])) {
                sectionEntry.setShowMonth(false);
            } else {
                sets.add(monthAndDayByFormat[0]);
                sectionEntry.setShowMonth(true);
            }
            sectionEntry.setMonth(monthAndDayByFormat[0]);
            sectionEntry.setDate(monthAndDayByFormat[1]);
            result.add(sectionEntry);
        }
        return result;
    }

    @Override
    public void getCourseProfile(String url, Map<String, String> parmars) {
        mModel.getData(url, parmars, new NewRequestCallBack<TrailerDetailBean>(TrailerDetailBean.class) {
            @Override
            public void onSuccess(TrailerDetailBean result) {
                switch (result.getResultCode()) {
                    case 1:
                        changeData(result.getData().getDir());
                        mView.showCourseProfile(result.getData());
                        break;
                }
            }
        });
    }
}
