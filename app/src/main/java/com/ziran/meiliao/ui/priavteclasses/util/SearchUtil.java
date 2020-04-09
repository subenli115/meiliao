package com.ziran.meiliao.ui.priavteclasses.util;

import android.content.Context;
import android.os.Bundle;

import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.SearchEntry;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.RecListMapBean;
import com.ziran.meiliao.ui.bean.SearchAllResultBean;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.TrailerBean;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.GongZuoFangActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalHistoryActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalLiveActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.TrailerWebActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.VerticalLiveActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryCrowdFundingDetailActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeacherDetailActivity;
import com.ziran.meiliao.ui.workshops.activity.CourseLibraryTeamDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/5 16:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/5$
 * @updateDes ${TODO}
 */

public class SearchUtil {

    private SearchUtil() {
    }

    public static void onItemClickNew(Context context, SearchItemBean bean,boolean collect) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.ExtraKey.FROM_ID, bean.getId());
        switch (bean.getType()) {
            case "missionBuilts":
                AppManager.getAppManager().jumpToActivity(CourseLibraryTeamDetailActivity.class, bundle);
                break;
            case "crowdFunds":
                AppManager.getAppManager().jumpToActivity(CourseLibraryCrowdFundingDetailActivity.class, bundle);
                break;
            case "famousTeachers":
                AppManager.getAppManager().jumpToActivity(CourseLibraryTeacherDetailActivity.class, bundle);
                break;
            case "album":
                AlbumDetailActivity.startAction(context, bean.getId());
                break;
            case "course":
                if ("-1".equals(bean.getTag())) {
                    TrailerBean.DataBean dataBean = new TrailerBean.DataBean();
                    dataBean.setAuthor(bean.getAuthor());
                    dataBean.setId(bean.getId());
                    dataBean.setPicture(bean.getPicture());
                    dataBean.setUrl(bean.getLink());
                    dataBean.setTitle(bean.getTitle());
                    dataBean.setDescript(bean.getDescript());
                    dataBean.setCollect(false);
                    TrailerWebActivity.startAction(context, dataBean, bean.getLink());
                } else if ("0".equals(bean.getTag())) {
                    if ("1".equals(bean.getStatus())) {
                        VerticalLiveActivity.startAction(context, bean.getLink(), 1);
                    } else if ("0".equals(bean.getStatus())) {
                        HorizontalLiveActivity.startAction(Integer.parseInt(bean.getLink()));
                    }
                } else if ("1".equals(bean.getTag())) {
                    if ("1".equals(bean.getStatus())) {
                        VerticalLiveActivity.startAction(context, bean.getLink(), 0);
                    } else if ("0".equals(bean.getStatus())) {
                        HorizontalHistoryActivity.startAction(Integer.parseInt(bean.getLink()));
                    }
                } else {
                    ToastUitl.showShort("该课程已下架,请查看其它课程");
                }
                break;
            case "activity":
                ActisData change = ActisData.change(bean);
                change.setIsCollect(collect);
                GongZuoFangActivity.startAction(context,change);
                break;
        }
    }
    public static void onItemClick(Context context, SearchItemBean bean) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.ExtraKey.FROM_ID, bean.getId());
        switch (bean.getType()) {
            case "missionBuilts":
                AppManager.getAppManager().jumpToActivity(CourseLibraryTeamDetailActivity.class, bundle);
                break;
            case "crowdFunds":
                AppManager.getAppManager().jumpToActivity(CourseLibraryCrowdFundingDetailActivity.class, bundle);
                break;
            case "famousTeachers":
                AppManager.getAppManager().jumpToActivity(CourseLibraryTeacherDetailActivity.class, bundle);
                break;
            case "album":
                AlbumDetailActivity.startAction(context, bean.getId());
                break;
            case "course":
                if ("-1".equals(bean.getTag())) {
                    TrailerBean.DataBean dataBean = new TrailerBean.DataBean();
                    dataBean.setAuthor(bean.getAuthor());
                    dataBean.setId(bean.getId());
                    dataBean.setPicture(bean.getPicture());
                    dataBean.setUrl(bean.getLink());
                    dataBean.setTitle(bean.getTitle());
                    dataBean.setDescript(bean.getDescript());
                    dataBean.setCollect(false);
                    TrailerWebActivity.startAction(context, dataBean, bean.getLink());
                } else if ("0".equals(bean.getTag())) {
                    if ("1".equals(bean.getStatus())) {
                        VerticalLiveActivity.startAction(context, bean.getLink(), 1);
                    } else if ("0".equals(bean.getStatus())) {
                        HorizontalLiveActivity.startAction(Integer.parseInt(bean.getLink()));
                    }
                } else if ("1".equals(bean.getTag())) {
                    if ("1".equals(bean.getStatus())) {
                        VerticalLiveActivity.startAction(context, bean.getLink(), 0);
                    } else if ("0".equals(bean.getStatus())) {
                        HorizontalHistoryActivity.startAction(Integer.parseInt(bean.getLink()));
                    }
                } else {
                    ToastUitl.showShort("该课程已下架,请查看其它课程");
                }
                break;
            case "activity":
                GongZuoFangActivity.startAction(context, ActisData.change(bean));
                break;
        }
    }

    public static List<SearchItemBean> changeData(SearchAllResultBean.DataBean result, boolean hasHead,boolean showEmpty) {
        List<SearchItemBean> searchItemBeanList = new ArrayList<>();

        if (EmptyUtils.isNotEmpty( result.getMissionBuilts())) {
            parseData(searchItemBeanList, result.getMissionBuilts(), result.getMissionBuiltCount(), "课程", "missionBuilts", hasHead);
        }
        if (EmptyUtils.isNotEmpty( result.getCrowdFunds())) {
            parseData(searchItemBeanList, result.getCrowdFunds(), result.getCrowdFundCount(), "众筹课程", "crowdFunds", hasHead);
        }
        if (EmptyUtils.isNotEmpty( result.getFamousTeachers())) {
            parseData(searchItemBeanList, result.getFamousTeachers(), result.getFamousTeacherCount(), "老师", "famousTeachers", hasHead);
        }

        if (EmptyUtils.isNotEmpty( result.getAlbum())) {
            parseData(searchItemBeanList, result.getAlbum(), result.getAlbumCount(), "音频", "album", hasHead);
        }

        if (EmptyUtils.isNotEmpty( result.getCourse())) {
            parseData(searchItemBeanList, result.getCourse(), result.getCourseCount(), "课程", "course", hasHead);
        }
        if (EmptyUtils.isNotEmpty( result.getActivity())) {
            parseData(searchItemBeanList, result.getActivity(), result.getActivityCount(), "活动", "activity", hasHead);
        }

        if (EmptyUtils.isEmpty(searchItemBeanList)&&showEmpty) {
            searchItemBeanList.add(SearchItemBean.empty());
        }

        RecListMapBean recListMap = result.getRecListMap();

        if (EmptyUtils.isNotEmpty(recListMap) && (EmptyUtils.isNotEmpty(recListMap.getRecFamousTeacherList()) || EmptyUtils.isNotEmpty
                (recListMap.getRecCrowdFundList())||EmptyUtils.isNotEmpty(recListMap.getRecMissionBuiltList()))) {
            searchItemBeanList.add(SearchItemBean.xiangGuan());

            if (EmptyUtils.isNotEmpty(recListMap.getRecCrowdFundList())) {
                parseData(searchItemBeanList, recListMap.getRecCrowdFundList(), recListMap.getRecCrowdFundList().size(), "众筹",
                        "crowdFunds", hasHead, "相关");
            }
            if (EmptyUtils.isNotEmpty(recListMap.getRecFamousTeacherList())) {
                parseData(searchItemBeanList, recListMap.getRecFamousTeacherList(), recListMap.getRecFamousTeacherList().size(), "老师",
                        "famousTeachers", hasHead, "相关");
            }
            if (EmptyUtils.isNotEmpty(recListMap.getRecMissionBuiltList())) {
                parseData(searchItemBeanList, recListMap.getRecMissionBuiltList(), recListMap.getRecMissionBuiltList().size(), "课程",
                        "missionBuilts", hasHead, "相关");
            }
        }

        return searchItemBeanList;
    }


    private static void parseData(List<SearchItemBean> searchItemBeanList, List<SearchItemBean> itemBeanList, int count, String msg,
                                  String type, boolean hasHead) {
        parseData(searchItemBeanList, itemBeanList, count, msg, type, hasHead, "查看全部");
    }

    private static void parseData(List<SearchItemBean> searchItemBeanList, List<SearchItemBean> itemBeanList, int count, String msg,
                                  String type, boolean hasHead, String tag) {
        if (hasHead) {
            SearchItemBean searchItemBean = new SearchItemBean();
            searchItemBean.setTitle(String.format("%s%s", tag, msg));
//            searchItemBean.setTitle(String.format("%s%d个%s", tag, count, msg));
            searchItemBean.setType(type);
            searchItemBean.setHead(true);
            searchItemBeanList.add(searchItemBean);
        }
        for (int i = 0; i < itemBeanList.size(); i++) {
            itemBeanList.get(i).setType(type);
        }
        searchItemBeanList.addAll(itemBeanList);
    }

    public static boolean check(List<SearchEntry> searchEntries, String content) {
        if (EmptyUtils.isNotEmpty(searchEntries) && EmptyUtils.isNotEmpty(content)) {
            for (int i = 0; i < searchEntries.size(); i++) {
                SearchEntry searchEntry = searchEntries.get(i);
                if (content.equals(searchEntry.getContent())) {
                    return true;
                }
            }
        }
        return false;
    }

}
