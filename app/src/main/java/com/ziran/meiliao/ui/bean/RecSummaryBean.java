package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class RecSummaryBean extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  {


        /**
         * record : {"id":1,"createTime":1560928743000,"courseDesc":"<div class='card' style='font-size:13px'> <div class='title'> <p style='font-weight:bold;font-size:16px'>介绍<\/p> <\/div> <p class='text'>正念冥想,古老又神秘,很多人的印象还停留在古老的佛教徒的修行上,其实当今的主流科学和社会精英,已经广泛的接纳正念冥想。<\/p> <\/div> <div class='card'> <div class='title'> <p style='font-weight:bold;font-size:16px'>课程大纲<\/p> <\/div> <div class='mulu-container'> <div class='mulu-item'> <p style='font-weight:500;font-size:14px' class href='#'>01&nbsp;&nbsp;第一章：了解你的情商工具- 正念<\/p> <\/div> <ul style='background-color: #F5F9FA;border-radius:8px;font-size:13px'> <li> <p>正念的定义<\/p> <\/li> <li> <p>基本原则<\/p> <\/li> <li> <p>锻炼方法<\/p> <\/li> <li> <p>迷你静观<\/p> <\/li> <li> <p>科学依据<\/p> <\/li> <\/ul> <\/div> <div class='mulu-container'> <div class='mulu-item'> <p style='font-weight:500;font-size:14px' class href='#'>02&nbsp;&nbsp;第二章：自我觉察<\/p> <\/div> <ul style='background-color: #F5F9FA;border-radius:8px;font-size:13px'> <li> <p>如何觉察情绪？<\/p> <\/li> <li> <p>情绪反映在身体<\/p> <\/li> <li> <p>身体扫描<\/p> <\/li> <li> <p>正念步行<\/p> <\/li> <li> <p>失眠怎么办？<\/p> <\/li> <\/ul> <\/div> <div class='mulu-container'> <div class='mulu-item'> <p style='font-weight:500;font-size:14px' class href='#'>03&nbsp;&nbsp;第三章：自我管理<\/p> <\/div> <ul style='background-color: #F5F9FA;border-radius:8px;font-size:13px'> <li> <p>回应空间<\/p> <\/li> <li> <p>STOP<\/p> <\/li> <li> <p>情绪面面观<\/p> <\/li> <li> <p>与艰辛共处<\/p> <\/li> <li> <p>观察念头<\/p> <\/li> <\/ul> <\/div> <div class='mulu-container'> <div class='mulu-item'> <p style='font-weight:500;font-size:14px' class href='#'>04&nbsp;&nbsp;第四章：同理心&人际交往<\/p> <\/div> <ul style='background-color: #F5F9FA;border-radius:8px;font-size:13px'> <li> <p>将心比心<\/p> <\/li> <li> <p>正念倾听<\/p> <\/li> <li> <p>慈心祝福<\/p> <\/li> <li> <p>正向影响<\/p> <\/li> <li> <p>我的故事<\/p> <\/li> <\/ul> <\/div><p style=\"        font-size: 20px; line-height: 40px;border-radius: 8px; min-height: 40px; background: #F9755F; padding-left: 2%; text-align: center; color: #fff;\">限时免费观看<\/p>","title":"全民正念\u2014\u2014《正念金融观》","shareUrl":"https://www.psytap.com/resource/yangjiaqing/index.html","tip":"提醒：5月20-6月17限时免费观看，之后观看请到【研修社】-【专栏】中付费观看","coverImage":"http://ojlzx3sl8.bkt.clouddn.com/cover_qmzl.png","shareTitle":"【全民正念第一期：正念情商软实力】","shareDesc":"《正念情商软实力》系列视频 从【情商工具】【自我觉察】【自我管理】【同理心&人际交往】四个板块讲述如何通过正念提升情商，获得收益。","sharePicture":"https://www.dgli.net/resource/images/conference/qmzl.png"}
         */

        private RecordBean record;

        public RecordBean getRecord() {
            return record;
        }

        public void setRecord(RecordBean record) {
            this.record = record;
        }

        public static class RecordBean {
            /**
             * id : 1
             * createTime : 1560928743000
             * courseDesc : <div class='card' style='font-size:13px'> <div class='title'> <p style='font-weight:bold;font-size:16px'>介绍</p> </div> <p class='text'>正念冥想,古老又神秘,很多人的印象还停留在古老的佛教徒的修行上,其实当今的主流科学和社会精英,已经广泛的接纳正念冥想。</p> </div> <div class='card'> <div class='title'> <p style='font-weight:bold;font-size:16px'>课程大纲</p> </div> <div class='mulu-container'> <div class='mulu-item'> <p style='font-weight:500;font-size:14px' class href='#'>01&nbsp;&nbsp;第一章：了解你的情商工具- 正念</p> </div> <ul style='background-color: #F5F9FA;border-radius:8px;font-size:13px'> <li> <p>正念的定义</p> </li> <li> <p>基本原则</p> </li> <li> <p>锻炼方法</p> </li> <li> <p>迷你静观</p> </li> <li> <p>科学依据</p> </li> </ul> </div> <div class='mulu-container'> <div class='mulu-item'> <p style='font-weight:500;font-size:14px' class href='#'>02&nbsp;&nbsp;第二章：自我觉察</p> </div> <ul style='background-color: #F5F9FA;border-radius:8px;font-size:13px'> <li> <p>如何觉察情绪？</p> </li> <li> <p>情绪反映在身体</p> </li> <li> <p>身体扫描</p> </li> <li> <p>正念步行</p> </li> <li> <p>失眠怎么办？</p> </li> </ul> </div> <div class='mulu-container'> <div class='mulu-item'> <p style='font-weight:500;font-size:14px' class href='#'>03&nbsp;&nbsp;第三章：自我管理</p> </div> <ul style='background-color: #F5F9FA;border-radius:8px;font-size:13px'> <li> <p>回应空间</p> </li> <li> <p>STOP</p> </li> <li> <p>情绪面面观</p> </li> <li> <p>与艰辛共处</p> </li> <li> <p>观察念头</p> </li> </ul> </div> <div class='mulu-container'> <div class='mulu-item'> <p style='font-weight:500;font-size:14px' class href='#'>04&nbsp;&nbsp;第四章：同理心&人际交往</p> </div> <ul style='background-color: #F5F9FA;border-radius:8px;font-size:13px'> <li> <p>将心比心</p> </li> <li> <p>正念倾听</p> </li> <li> <p>慈心祝福</p> </li> <li> <p>正向影响</p> </li> <li> <p>我的故事</p> </li> </ul> </div><p style="        font-size: 20px; line-height: 40px;border-radius: 8px; min-height: 40px; background: #F9755F; padding-left: 2%; text-align: center; color: #fff;">限时免费观看</p>
             * title : 全民正念——《正念金融观》
             * shareUrl : https://www.psytap.com/resource/yangjiaqing/index.html
             * tip : 提醒：5月20-6月17限时免费观看，之后观看请到【研修社】-【专栏】中付费观看
             * coverImage : http://ojlzx3sl8.bkt.clouddn.com/cover_qmzl.png
             * shareTitle : 【全民正念第一期：正念情商软实力】
             * shareDesc : 《正念情商软实力》系列视频 从【情商工具】【自我觉察】【自我管理】【同理心&人际交往】四个板块讲述如何通过正念提升情商，获得收益。
             * sharePicture : https://www.dgli.net/resource/images/conference/qmzl.png
             */

            private int id;
            private long createTime;
            private String courseDesc;
            private String title;
            private String shareUrl;
            private String tip;
            private String coverImage;
            private String shareTitle;
            private String shareDesc;
            private String sharePicture;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getCourseDesc() {
                return courseDesc;
            }

            public void setCourseDesc(String courseDesc) {
                this.courseDesc = courseDesc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public String getCoverImage() {
                return coverImage;
            }

            public void setCoverImage(String coverImage) {
                this.coverImage = coverImage;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareDesc() {
                return shareDesc;
            }

            public void setShareDesc(String shareDesc) {
                this.shareDesc = shareDesc;
            }

            public String getSharePicture() {
                return sharePicture;
            }

            public void setSharePicture(String sharePicture) {
                this.sharePicture = sharePicture;
            }
        }
    }

    @Override
    public String toString() {
        return "ResBean{" +
                "data=" + data +
                '}';
    }

}
