package com.ziran.meiliao.ui.workshops.util;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.AuthorBean;
import com.ziran.meiliao.ui.bean.CourseLibraryTeamBean;
import com.ziran.meiliao.ui.bean.CrowdFundingPreviewBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.MultiItemBean;
import com.ziran.meiliao.ui.bean.PicsBean;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;
import com.ziran.meiliao.ui.bean.SearchItemBean;
import com.ziran.meiliao.ui.bean.TeacherDetailBean;
import com.ziran.meiliao.ui.bean.TeamDetailBean;
import com.ziran.meiliao.ui.bean.OrderDetailBean;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibraryTeamAdapter;
import com.ziran.meiliao.ui.workshops.adapter.CourseLibraryTeamDetailAdapter;
import com.ziran.meiliao.ui.bean.CheckMsgBean;
import com.ziran.meiliao.ui.bean.CrowdFundingPreviewDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/20 11:17
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/20$
 * @updateDes ${TODO}
 */

public class QJGDataUtil implements AppConstant.TeamDetail {

    public static List<String> getStringData(int size) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            result.add(String.valueOf(i));
        }
        return result;
    }

    public static List<OrderDetailBean> getOrderDetail(int type, int size) {
        List<OrderDetailBean> result = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            result.add(new OrderDetailBean(type, i));
        }
        return result;
    }


    private static String url = "https://timgsa.baidu" + "" + "" + "" + "" + "" +
            ".com/timg?image&quality=80&size=b9999_10000&sec=1511160099936&di=02ddec53c282a4437da670ab4ad346e5&imgtype=0&src=http%3A%2F"
            + "%2Fimg3.tbcdn.cn%2Ftfscom%2Fi1%2F780043469%2FTB2z3iMawMPMeJjy1XcXXXpppXa_%2521%2521780043469.jpg";


    public static List getTeacherData(TeacherDetailBean.DataBean dataBean) {
        TeacherDetailBean.DataBean.FamousTeacherMapBean mapBean = dataBean.getFamousTeacherMap();
        List result = new ArrayList();
        AuthorBean authorBean = new AuthorBean();
        authorBean.setName(mapBean.getName());
        authorBean.setTeaIntro(mapBean.getIntro());
        authorBean.setHeadImg(mapBean.getPicture());
        result.add(authorBean);
        List<CourseLibraryTeamBean> courseDetailList = mapBean.getCourseDetailList();
        if (EmptyUtils.isNotEmpty(courseDetailList)) {
            result.add(HeadData.create(0, "团建课程", false, false));
            result.addAll(courseDetailList);
        }
        List<ActisData> activityList = mapBean.getActivityList();

        if (EmptyUtils.isNotEmpty(activityList)) {
            result.add(HeadData.create(1, "工作坊", false, false));
            result.addAll(activityList);
        }

        if (EmptyUtils.isNotEmpty(dataBean.getRecMap())) {
            result.add(HeadData.create(0, "相关", false, false));
            TeacherDetailBean.DataBean.RecListMap recMap = dataBean.getRecMap();
            if (EmptyUtils.isNotEmpty(recMap.getAlbum())) {
                result.addAll(recMap.getAlbum());
            }
            if (EmptyUtils.isNotEmpty(recMap.getActivity())) {
                result.addAll(recMap.getActivity());
            }
            if (EmptyUtils.isNotEmpty(recMap.getCourse())) {
                result.addAll(recMap.getCourse());
            }
            if (EmptyUtils.isNotEmpty(recMap.getCrowdFunds())) {
                result.addAll(refresh(recMap.getCrowdFunds(), "crowdFunds"));
            }
            if (EmptyUtils.isNotEmpty(recMap.getFamousTeachers())) {
                result.addAll(refresh(recMap.getFamousTeachers(), "famousTeachers"));
            }
            if (EmptyUtils.isNotEmpty(recMap.getMissionBuilts())) {
                result.addAll(refresh(recMap.getMissionBuilts(), "missionBuilts"));
            }
        }
        return result;
    }

    private static List<SearchItemBean> refresh(List<SearchItemBean> album, String type) {
        for (SearchItemBean searchItemBean : album) {
            searchItemBean.setType(type);
        }
        return album;
    }


    public static List<SJKSingeLiveData> getCourse(int size) {
        List<SJKSingeLiveData> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            SJKSingeLiveData sjkSingeLiveData = new SJKSingeLiveData();
            sjkSingeLiveData.setTitle("瑜伽与冥想");
            sjkSingeLiveData.setId("71");
            sjkSingeLiveData.setPicture(url);
            sjkSingeLiveData.setVip("限免");
            sjkSingeLiveData.setWatchCount("666");
            sjkSingeLiveData.setDuration("60:35");
            result.add(sjkSingeLiveData);
        }
        return result;
    }

    public static List<String> getImg() {
        List<String> result = new ArrayList<>();
        result.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511272660729&di=82c32fc3a3d78b8e2dea3edbcd6b9159" +
                "" + "" + "" + "" + "" + "&imgtype=0&src=http%3A%2F%2Fp2.wmpic.me%2Farticle%2F2017%2F01%2F06%2F1483682065_gZnrfTTu.jpg");
        result.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511272660727&di=f4ff17dd438ae6d3d00c8a2b834c7f3b" +
                "" + "" + "" + "" + "" + "&imgtype=0&src=http%3A%2F%2Fwww.feizl.com%2Fupload2007%2F2014_09%2F14091213456063.jpg");
        result.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511272660727&di=ee0fa7e2aeb3c2f732c164d17ccadf43" +
                "" + "" + "" + "" + "" + "&imgtype=0&src=http%3A%2F%2Fbaijunjian.keliren" +
                ".cn%2Ftuku%2Fa%2F20160713%2F5785c73b6dd49.jpg_600.jpg");
        result.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511272660727&di=1c0755a177e017c6b9d7186b85eb327f" +
                "" + "" + "" + "" + "" + "&imgtype=0&src=http%3A%2F%2Fwww.86zw.org%2Fpic1%2F2017%2F05%2F1811%2F4m5gikszsir1064110518.jpg");
        return result;
    }


    public static String[] getImgArray(List<String> img) {
        String[] result = new String[img.size()];
        for (int i = 0; i < img.size(); i++) {
            result[i] = img.get(i);
        }
        return result;
    }

    public static String[] getImgArrayPic(List<PicsBean> img) {
        String[] result = new String[img.size()];
        for (int i = 0; i < img.size(); i++) {
            result[i] = img.get(i).getPicture();
        }
        return result;
    }

    public static List<CheckMsgBean> getCheckMsg() {
        List<CheckMsgBean> result = new ArrayList<>();
        result.add(new CheckMsgBean(true, true, "姓名", "name"));
        result.add(new CheckMsgBean(true, true, "手机", "phone"));
        result.add(new CheckMsgBean(false, false, "性别", "sex"));
        result.add(new CheckMsgBean(false, false, "年龄", "age"));
        result.add(new CheckMsgBean(false, false, "邮箱", "email"));
        result.add(new CheckMsgBean(false, false, "备注", "remarks"));
        return result;
    }

    public static List<CourseLibraryTeamBean> getTeamData() {
        List<CourseLibraryTeamBean> result = new ArrayList<>();
        result.add(new CourseLibraryTeamBean(CourseLibraryTeamAdapter.TYPE_TEAM_LEFT, AppConstant.URL, "正念翻转小课堂，寒假班开班啦～！", "99-984",
                "2天1晚"));
        result.add(new CourseLibraryTeamBean(CourseLibraryTeamAdapter.TYPE_TEAM_TOP, AppConstant.URL, "正念翻转小课堂，寒假班开班啦～！", "99-984",
                "2天1晚"));
        result.add(new CourseLibraryTeamBean(CourseLibraryTeamAdapter.TYPE_TEAM_TOP, AppConstant.URL, "正念翻转小课堂，寒假班开班啦～！", "99-984",
                "2天1晚"));
        result.add(new CourseLibraryTeamBean(CourseLibraryTeamAdapter.TYPE_TEAM_LEFT, AppConstant.URL, "正念翻转小课堂，寒假班开班啦～！", "99-984",
                "2天1晚"));
        result.add(new CourseLibraryTeamBean(CourseLibraryTeamAdapter.TYPE_TEAM_TOP, AppConstant.URL, "正念翻转小课堂，寒假班开班啦～！", "99-984",
                "2天1晚"));
        result.add(new CourseLibraryTeamBean(CourseLibraryTeamAdapter.TYPE_TEAM_LEFT, AppConstant.URL, "正念翻转小课堂，寒假班开班啦～！", "99-984",
                "2天1晚"));
        result.add(new CourseLibraryTeamBean(CourseLibraryTeamAdapter.TYPE_TEAM_TOP, AppConstant.URL, "正念翻转小课堂，寒假班开班啦～！", "99-984",
                "2天1晚"));
        return result;
    }

    public static List<MultiItemBean> getTeamDetailData(TeamDetailBean.DataBean.MissionBuiltListBean data) {
        List<MultiItemBean> result = new ArrayList<>();

        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TOP, data));

        List<CourseLibraryTeamBean> list = data.getTeachersList();

        if (EmptyUtils.isNotEmpty(list)) {
            result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TITLE, "课程老师"));
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setItemType(CourseLibraryTeamAdapter.TYPE_TEAM_LEFT);
                result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_LEFT, list.get(i)));
            }
        }
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TITLE, "行程安排"));
//        List<TeamDetailBean.DataBean.MissionBuiltListBean.MarchDetailBean> marchDetail = data.getMarchDetail();
//        List<MultiItemBean> multiItemBean = new ArrayList<>();
//        boolean showLine;
//        for (int i = 0; i < marchDetail.size(); i++) {
//            TeamDetailBean.DataBean.MissionBuiltListBean.MarchDetailBean marchDetailBean = marchDetail.get(i);
//            multiItemBean.add(new MultiItemBean(CourseLibraryTeamDetailTripAdapter.TYPE_DAY, marchDetailBean.getTitle()));
//            List<TeamDetailTripBean> detail = marchDetailBean.getDetail();
//            if (EmptyUtils.isNotEmpty(detail)) {
//                showLine = detail.size() > 1 || detail.get(0).checkShowLine();
//                for (int j = 0; j < detail.size(); j++) {
//                    detail.get(j).setShowLine(showLine);
//                    multiItemBean.add(new MultiItemBean(CourseLibraryTeamDetailTripAdapter.TYPE_NORMAL, detail.get(j)));
//                }
//            }
//
//        }
//        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_LIST, multiItemBean));
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TITLE, "费用说明"));
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TEXT, data.getFeeDetail()));
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TITLE, "注意事项"));
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TEXT, data.getAttentionDetail()));
        return result;
    }

    public static List<MultiItemBean> getTeamDetailDataNew(TeamDetailBean.DataBean.MissionBuiltListBean data) {
        List<MultiItemBean> result = new ArrayList<>();

        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TOP, data));

        List<CourseLibraryTeamBean> list = data.getTeachersList();

        if (EmptyUtils.isNotEmpty(list)) {
            result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TITLE, "课程老师"));
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setItemType(CourseLibraryTeamAdapter.TYPE_TEAM_LEFT);
                result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_LEFT, list.get(i)));
            }
        }
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TITLE, "行程安排"));

        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_IMAGE, data.getMarchDetail()));
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TITLE, "费用说明"));
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_IMAGE, data.getFeeDetail()));
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_TITLE, "注意事项"));
        result.add(new MultiItemBean(CourseLibraryTeamDetailAdapter.TYPE_TEAM_IMAGE, data.getAttentionDetail()));
        return result;
    }

    private static List<CrowdFundingPreviewBean> templateData;

    public static void setTemplateData(List<CrowdFundingPreviewBean> all) {
        templateData = all;
    }

    public static List<CrowdFundingPreviewBean> getTemplateData() {
        return templateData;
    }

    public static void clearTemplateData() {
        if (templateData != null) {
            templateData.clear();
            templateData = null;
        }
    }

    private static CrowdFundingPreviewDetailBean mDetailBean;

    public static CrowdFundingPreviewDetailBean setTemplateHeadAndFooter() {
        if (mDetailBean == null) {
            mDetailBean = new CrowdFundingPreviewDetailBean();
        }
        return mDetailBean;
    }
}
