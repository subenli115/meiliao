package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/8 11:53
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/8$
 * @updateDes ${TODO}
 */

public class TeamDetailBean extends Result {

    /**
     * data : {"missionBuiltList":{"createTime":1511937194000,"targetMembers":40,"accessToken":"2771133090f5017869622de22294f04b9e62a72",
     * "status":1,"shareTitle":"报名 | 医学及心理学中的正念3","sharePic":"index_act_banner1228.png","feeDetail":null,"authorMembers":3,
     * "authorId":[10,11,12],"id":1,"picture":"https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png","title":"报名
     * | 医学及心理学中的正念3","shareUrl":"","attentionDetail":null,"marchDetail":[{"title":"第1天","index":1,"detail":[{"content":" ","title":"深圳 -
     * 昆明","time":" ","pic":" ","type":1}]},{"title":"第2天","index":2,"detail":[{"content":" ","title":"深圳 - 昆明","time":" ","pic":" ",
     * "type":1},{"content":"客栈店长举行欢迎仪式，介绍庄园周边位置、场景。从这里俯瞰洱海，宛如一轮新月，静静地依卧在苍山和大理坝子之间。","title":"下榻利舍庄园与大理小院子（距离约500米)","time":"上午",
     * "pic":"","type":3},{"content":"介绍正念相关理论及背景知识；掌握一些练习方法。","title":"开营仪式","time":"14：30\u201417：30","pic":" ","type":2},
     * {"content":"","title":"淘宝大理古城","time":"","pic":"http://timgsa.baidu.com.jpg","type":1}]},{"title":"第3天","index":3,
     * "detail":[{"content":"沿利舍庄园步道散步，在摄人心魄的日出美景中，进行呼吸觉察的小练习。","title":"开营仪式","time":"6：30\u20147：30","pic":"","type":2}]}],
     * "teachersList":[{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png",
     * "createTime":1512098525000,"accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙","courseId":1,
     * "inrto":"简介简介简介","authorId":10},{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png",
     * "createTime":1512098536000,"accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙","courseId":1,
     * "inrto":"简介简介简介","authorId":11},{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png",
     * "createTime":1512098533000,"accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙","courseId":1,
     * "inrto":"简介简介简介","authorId":12}],"officePrice":50000,"totalTime":"1天1晚"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * missionBuiltList : {"createTime":1511937194000,"targetMembers":40,"accessToken":"2771133090f5017869622de22294f04b9e62a72",
         * "status":1,"shareTitle":"报名 | 医学及心理学中的正念3","sharePic":"index_act_banner1228.png","feeDetail":null,"authorMembers":3,
         * "authorId":[10,11,12],"id":1,"picture":"https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png",
         * "title":"报名 | 医学及心理学中的正念3","shareUrl":"","attentionDetail":null,"marchDetail":[{"title":"第1天","index":1,"detail":[{"content":"
         * ","title":"深圳 - 昆明","time":" ","pic":" ","type":1}]},{"title":"第2天","index":2,"detail":[{"content":" ","title":"深圳 - 昆明",
         * "time":" ","pic":" ","type":1},{"content":"客栈店长举行欢迎仪式，介绍庄园周边位置、场景。从这里俯瞰洱海，宛如一轮新月，静静地依卧在苍山和大理坝子之间。",
         * "title":"下榻利舍庄园与大理小院子（距离约500米)","time":"上午","pic":"","type":3},{"content":"介绍正念相关理论及背景知识；掌握一些练习方法。","title":"开营仪式",
         * "time":"14：30\u201417：30","pic":" ","type":2},{"content":"","title":"淘宝大理古城","time":"","pic":"http://timgsa.baidu.com.jpg",
         * "type":1}]},{"title":"第3天","index":3,"detail":[{"content":"沿利舍庄园步道散步，在摄人心魄的日出美景中，进行呼吸觉察的小练习。","title":"开营仪式",
         * "time":"6：30\u20147：30","pic":"","type":2}]}],"teachersList":[{"picture":"https://www.dgli
         * .net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","createTime":1512098525000,
         * "accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙","courseId":1,"inrto":"简介简介简介","authorId":10},
         * {"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","createTime":1512098536000,
         * "accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙","courseId":1,"inrto":"简介简介简介","authorId":11},
         * {"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","createTime":1512098533000,
         * "accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙","courseId":1,"inrto":"简介简介简介","authorId":12}],
         * "officePrice":50000,"totalTime":"1天1晚"}
         */

        private MissionBuiltListBean missionBuiltList;

        public MissionBuiltListBean getMissionBuiltList() {
            return missionBuiltList;
        }

        public void setMissionBuiltList(MissionBuiltListBean missionBuiltList) {
            this.missionBuiltList = missionBuiltList;
        }

        public static class MissionBuiltListBean extends ShareBean {
            /**
             * createTime : 1511937194000
             * targetMembers : 40
             * accessToken : 2771133090f5017869622de22294f04b9e62a72
             * status : 1
             * shareTitle : 报名 | 医学及心理学中的正念3
             * sharePic : index_act_banner1228.png
             * feeDetail : null
             * authorMembers : 3
             * authorId : [10,11,12]
             * id : 1
             * picture : https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png
             * title : 报名 | 医学及心理学中的正念3
             * shareUrl :
             * attentionDetail : null
             * marchDetail : [{"title":"第1天","index":1,"detail":[{"content":" ","title":"深圳 - 昆明","time":" ","pic":" ","type":1}]},
             * {"title":"第2天","index":2,"detail":[{"content":" ","title":"深圳 - 昆明","time":" ","pic":" ","type":1},
             * {"content":"客栈店长举行欢迎仪式，介绍庄园周边位置、场景。从这里俯瞰洱海，宛如一轮新月，静静地依卧在苍山和大理坝子之间。","title":"下榻利舍庄园与大理小院子（距离约500米)","time":"上午","pic":"",
             * "type":3},{"content":"介绍正念相关理论及背景知识；掌握一些练习方法。","title":"开营仪式","time":"14：30\u201417：30","pic":" ","type":2},{"content":"",
             * "title":"淘宝大理古城","time":"","pic":"http://timgsa.baidu.com.jpg","type":1}]},{"title":"第3天","index":3,
             * "detail":[{"content":"沿利舍庄园步道散步，在摄人心魄的日出美景中，进行呼吸觉察的小练习。","title":"开营仪式","time":"6：30\u20147：30","pic":"","type":2}]}]
             * teachersList : [{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png",
             * "createTime":1512098525000,"accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙","courseId":1,
             * "inrto":"简介简介简介","authorId":10},{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui
             * .png","createTime":1512098536000,"accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙",
             * "courseId":1,"inrto":"简介简介简介","authorId":11},{"picture":"https://www.dgli
             * .net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","createTime":1512098533000,
             * "accessToken":"2771133090f5017869622de22294f04b9e62a72","status":1,"name":"李燕蕙","courseId":1,"inrto":"简介简介简介","authorId":12}]
             * officePrice : 50000.0
             * totalTime : 1天1晚
             */

            private long createTime;
            private int targetMembers;
            private String accessToken;
            private int status;
            private String feeDetail;
            private int authorMembers;
            private int id;
            private String picture;
            private String title;
            private String attentionDetail;
            private double officePrice;
            private String totalTime;
            private List<Integer> authorId;
            private String marchDetail;
//            private List<MarchDetailBean> marchDetail;
            private List<CourseLibraryTeamBean> teachersList;
            private int isCollect;


            public int isCollect() {
                return isCollect;
            }

            public void setCollect(int collect) {
                isCollect = collect;
            }

            public void toggleCollect() {
                isCollect = isCollect == 0 ? 1 : 0;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getTargetMembers() {
                return targetMembers;
            }

            public void setTargetMembers(int targetMembers) {
                this.targetMembers = targetMembers;
            }

            public String getAccessToken() {
                return accessToken;
            }

            public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
            }

            public int getStatus() {
                return status;
            }

            public void setStatusX(int statusX) {
                this.status = statusX;
            }

            public String getFeeDetail() {
                return feeDetail;
            }

            public void setFeeDetail(String feeDetail) {
                this.feeDetail = feeDetail;
            }

            public int getAuthorMembers() {
                return authorMembers;
            }

            public void setAuthorMembers(int authorMembers) {
                this.authorMembers = authorMembers;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAttentionDetail() {
                return attentionDetail;
            }

            public void setAttentionDetail(String attentionDetail) {
                this.attentionDetail = attentionDetail;
            }

            public double getOfficePrice() {
                return officePrice;
            }

            public void setOfficePrice(double officePrice) {
                this.officePrice = officePrice;
            }

            public String getTotalTime() {
                return totalTime;
            }

            public void setTotalTime(String totalTime) {
                this.totalTime = totalTime;
            }

            public List<Integer> getAuthorId() {
                return authorId;
            }

            public void setAuthorId(List<Integer> authorId) {
                this.authorId = authorId;
            }

            public String getMarchDetail() {
                return marchDetail;
            }

            public void setMarchDetail(String marchDetail) {
                this.marchDetail = marchDetail;
            }

            public List<CourseLibraryTeamBean> getTeachersList() {
                return teachersList;
            }

            public void setTeachersList(List<CourseLibraryTeamBean> teachersList) {
                this.teachersList = teachersList;
            }

            public static class MarchDetailBean {
                /**
                 * title : 第1天
                 * index : 1
                 * detail : [{"content":" ","title":"深圳 - 昆明","time":" ","pic":" ","type":1}]
                 */

                private String title;
                private int index;
                private List<TeamDetailTripBean> detail;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public List<TeamDetailTripBean> getDetail() {
                    return detail;
                }

                public void setDetail(List<TeamDetailTripBean> detail) {
                    this.detail = detail;
                }
            }

//            public static class TeachersListBean {
//                /**
//                 * picture : https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png
//                 * createTime : 1512098525000
//                 * accessToken : 2771133090f5017869622de22294f04b9e62a72
//                 * status : 1
//                 * name : 李燕蕙
//                 * courseId : 1
//                 * inrto : 简介简介简介
//                 * authorId : 10
//                 */
//
//                private String picture;
//                private long createTime;
//                private String accessToken;
//                @SerializedName("status")
//                private int statusX;
//                private String name;
//                private int courseId;
//                private String inrto;
//                private int authorId;
//
//                public String getPicture() {
//                    return picture;
//                }
//
//                public void setPicture(String picture) {
//                    this.picture = picture;
//                }
//
//                public long getCreateTime() {
//                    return createTime;
//                }
//
//                public void setCreateTime(long createTime) {
//                    this.createTime = createTime;
//                }
//
//                public String getAccessToken() {
//                    return accessToken;
//                }
//
//                public void setAccessToken(String accessToken) {
//                    this.accessToken = accessToken;
//                }
//
//                public int getStatusX() {
//                    return statusX;
//                }
//
//                public void setStatusX(int statusX) {
//                    this.statusX = statusX;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public int getCourseId() {
//                    return courseId;
//                }
//
//                public void setCourseId(int courseId) {
//                    this.courseId = courseId;
//                }
//
//                public String getInrto() {
//                    return inrto;
//                }
//
//                public void setInrto(String inrto) {
//                    this.inrto = inrto;
//                }
//
//                public int getAuthorId() {
//                    return authorId;
//                }
//
//                public void setAuthorId(int authorId) {
//                    this.authorId = authorId;
//                }
//            }
        }
    }
}
