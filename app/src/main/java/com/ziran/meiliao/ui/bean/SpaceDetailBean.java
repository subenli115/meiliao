package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class SpaceDetailBean extends Result {


    /**
     * data : {"id":"5db6df075d06b458b0bd84ba7cd858f3","userId":"aa66cd8fd4fe10d9021ba0869b1c0c66","userName":"沙漠认识1","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8ac05b11a9904a7f90214bddeeb4c7db.png","nickname":"沙漠认识1","title":null,"content":"语音测试","images":null,"address":"重庆市","videoId":"a2e75e1967714fbdb60c35f68ca260cf","status":1,"enclosureType":2,"latitude":"29.607443712295286","longitude":"106.54100870292055","isOwn":0,"commentNum":0,"browseNum":58,"clickNum":0,"duration":"00:06","delFlag":"0","createTime":"2020-08-26 14:57:22","avatarList":[],"distance":"0.9","isFollow":"1","isClick":"0","realName":"谭谭","isReal":"1","response":{"requestId":"AB5F2911-D43F-4D62-A2A6-5609CCFFD2C3","playAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklyNWJZREk2TjI0NXI5TE9tU2tPSjNWUXhWTzE0cXBiSm9UejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsck1xRmNRWUd4eWJNcE1vdHM0Sm9GdjlKcExGc3QySjZyOEpqc1VrbHZnMm9WaXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0dOLzZuNU9aUXJ6emI0WmhKZWVsQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQ2QvTDlwdzJYT2x6NUd2WFZnUHRuaTRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDQvNWVUWWFwazFNVWFnQUdOQ3JKbWRDaDJSL1cxWjQraEhocFlSYmxNdE5XdktwK01RTmRyajV6MGVyZUhyeCtIVk1Gbktzb0ZSdWNHaW9Bciszck54QzM4c2lMVGpNYS85V0hOS1Y2WmFRa2l4WHFzeWpZTHI5d29nL2U5aWJnT2dLeWJYOWtOblAzMHRZRkZVQ1ozNzdyamJSam9rOGlMR1hvVXpQaldMUFh3U3hmQjBiQTg4VmpDK0E9PSIsIkF1dGhJbmZvIjoie1wiQ0lcIjpcIm54aDRMZTFFdjNqYW1odWtpaTBTaUZ6QXpvY2FDRjB2a0hIV3RWdmhNQm0vRTRMa0VDMzZ6aDljTVhSSWdOZzJcXHJcXG5cIixcIkNhbGxlclwiOlwidElpTXE5byt6cW8vNXh1WGZKM2RyU1FVYVg1K09hQ3lRVHR4Mnh3Z1lqOD1cXHJcXG5cIixcIkV4cGlyZVRpbWVcIjpcIjIwMjAtMDgtMjZUMDY6NTk6NDZaXCIsXCJNZWRpYUlkXCI6XCJhMmU3NWUxOTY3NzE0ZmJkYjYwYzM1ZjY4Y2EyNjBjZlwiLFwiU2lnbmF0dXJlXCI6XCJML1R0eGV2VkZjVGV3eXdkaHgzWFVBOFcvK009XCJ9IiwiVmlkZW9NZXRhIjp7IlN0YXR1cyI6Ik5vcm1hbCIsIlZpZGVvSWQiOiJhMmU3NWUxOTY3NzE0ZmJkYjYwYzM1ZjY4Y2EyNjBjZiIsIlRpdGxlIjoi6Z+z6aKRIiwiRHVyYXRpb24iOjYuMDgzNn0sIkFjY2Vzc0tleUlkIjoiU1RTLk5VbUc0OTZRSkNxTUhlODlUZFhiVEVTYkMiLCJBY2Nlc3NLZXlTZWNyZXQiOiI3NGlyaG9abTUzZG54OHo0VHEyYTRBZFRrRDhpRThpd1JEYUxiRktkbzJhNCIsIlJlZ2lvbiI6ImNuLXNoYW5naGFpIiwiQ3VzdG9tZXJJZCI6MTYzNTY4NzcwMDAyNjkwNH0=","videoMeta":{"coverURL":null,"duration":6.0836,"status":"Normal","title":"音频","videoId":"a2e75e1967714fbdb60c35f68ca260cf"}},"giftTotal":0}
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
         * id : 5db6df075d06b458b0bd84ba7cd858f3
         * userId : aa66cd8fd4fe10d9021ba0869b1c0c66
         * userName : 沙漠认识1
         * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8ac05b11a9904a7f90214bddeeb4c7db.png
         * nickname : 沙漠认识1
         * title : null
         * content : 语音测试
         * images : null
         * address : 重庆市
         * videoId : a2e75e1967714fbdb60c35f68ca260cf
         * status : 1
         * enclosureType : 2
         * latitude : 29.607443712295286
         * longitude : 106.54100870292055
         * isOwn : 0
         * commentNum : 0
         * browseNum : 58
         * clickNum : 0
         * duration : 00:06
         * delFlag : 0
         * createTime : 2020-08-26 14:57:22
         * avatarList : []
         * distance : 0.9
         * isFollow : 1
         * isClick : 0
         * realName : 谭谭
         * isReal : 1
         * response : {"requestId":"AB5F2911-D43F-4D62-A2A6-5609CCFFD2C3","playAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklyNWJZREk2TjI0NXI5TE9tU2tPSjNWUXhWTzE0cXBiSm9UejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsck1xRmNRWUd4eWJNcE1vdHM0Sm9GdjlKcExGc3QySjZyOEpqc1VrbHZnMm9WaXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0dOLzZuNU9aUXJ6emI0WmhKZWVsQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQ2QvTDlwdzJYT2x6NUd2WFZnUHRuaTRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDQvNWVUWWFwazFNVWFnQUdOQ3JKbWRDaDJSL1cxWjQraEhocFlSYmxNdE5XdktwK01RTmRyajV6MGVyZUhyeCtIVk1Gbktzb0ZSdWNHaW9Bciszck54QzM4c2lMVGpNYS85V0hOS1Y2WmFRa2l4WHFzeWpZTHI5d29nL2U5aWJnT2dLeWJYOWtOblAzMHRZRkZVQ1ozNzdyamJSam9rOGlMR1hvVXpQaldMUFh3U3hmQjBiQTg4VmpDK0E9PSIsIkF1dGhJbmZvIjoie1wiQ0lcIjpcIm54aDRMZTFFdjNqYW1odWtpaTBTaUZ6QXpvY2FDRjB2a0hIV3RWdmhNQm0vRTRMa0VDMzZ6aDljTVhSSWdOZzJcXHJcXG5cIixcIkNhbGxlclwiOlwidElpTXE5byt6cW8vNXh1WGZKM2RyU1FVYVg1K09hQ3lRVHR4Mnh3Z1lqOD1cXHJcXG5cIixcIkV4cGlyZVRpbWVcIjpcIjIwMjAtMDgtMjZUMDY6NTk6NDZaXCIsXCJNZWRpYUlkXCI6XCJhMmU3NWUxOTY3NzE0ZmJkYjYwYzM1ZjY4Y2EyNjBjZlwiLFwiU2lnbmF0dXJlXCI6XCJML1R0eGV2VkZjVGV3eXdkaHgzWFVBOFcvK009XCJ9IiwiVmlkZW9NZXRhIjp7IlN0YXR1cyI6Ik5vcm1hbCIsIlZpZGVvSWQiOiJhMmU3NWUxOTY3NzE0ZmJkYjYwYzM1ZjY4Y2EyNjBjZiIsIlRpdGxlIjoi6Z+z6aKRIiwiRHVyYXRpb24iOjYuMDgzNn0sIkFjY2Vzc0tleUlkIjoiU1RTLk5VbUc0OTZRSkNxTUhlODlUZFhiVEVTYkMiLCJBY2Nlc3NLZXlTZWNyZXQiOiI3NGlyaG9abTUzZG54OHo0VHEyYTRBZFRrRDhpRThpd1JEYUxiRktkbzJhNCIsIlJlZ2lvbiI6ImNuLXNoYW5naGFpIiwiQ3VzdG9tZXJJZCI6MTYzNTY4NzcwMDAyNjkwNH0=","videoMeta":{"coverURL":null,"duration":6.0836,"status":"Normal","title":"音频","videoId":"a2e75e1967714fbdb60c35f68ca260cf"}}
         * giftTotal : 0
         */

        private String id;
        private String userId;
        private String userName;
        private String avatar;
        private String nickname;
        private Object title;
        private String content;
        private String images;
        private String address;
        private String videoId;
        private int status;
        private int enclosureType;
        private String latitude;
        private String longitude;
        private int isOwn;
        private int commentNum;
        private int browseNum;
        private int clickNum;
        private String duration;
        private String delFlag;
        private String createTime;
        private String distance;
        private String isFollow;
        private String isClick;
        private String realName;

        private String realImg;
        private String isReal;
        private ResponseBean response;
        private int giftTotal;
        private List<String> avatarList;

        public String getRealImg() {
            return realImg;
        }

        public void setRealImg(String realImg) {
            this.realImg = realImg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getEnclosureType() {
            return enclosureType;
        }

        public void setEnclosureType(int enclosureType) {
            this.enclosureType = enclosureType;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getIsOwn() {
            return isOwn;
        }

        public void setIsOwn(int isOwn) {
            this.isOwn = isOwn;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public int getClickNum() {
            return clickNum;
        }

        public void setClickNum(int clickNum) {
            this.clickNum = clickNum;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(String isFollow) {
            this.isFollow = isFollow;
        }

        public String getIsClick() {
            return isClick;
        }

        public void setIsClick(String isClick) {
            this.isClick = isClick;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIsReal() {
            return isReal;
        }

        public void setIsReal(String isReal) {
            this.isReal = isReal;
        }

        public ResponseBean getResponse() {
            return response;
        }

        public void setResponse(ResponseBean response) {
            this.response = response;
        }

        public int getGiftTotal() {
            return giftTotal;
        }

        public void setGiftTotal(int giftTotal) {
            this.giftTotal = giftTotal;
        }

        public List<String> getAvatarList() {
            return avatarList;
        }

        public void setAvatarList(List<String> avatarList) {
            this.avatarList = avatarList;
        }

        public static class ResponseBean {
            /**
             * requestId : AB5F2911-D43F-4D62-A2A6-5609CCFFD2C3
             * playAuth : eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklyNWJZREk2TjI0NXI5TE9tU2tPSjNWUXhWTzE0cXBiSm9UejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsck1xRmNRWUd4eWJNcE1vdHM0Sm9GdjlKcExGc3QySjZyOEpqc1VrbHZnMm9WaXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0dOLzZuNU9aUXJ6emI0WmhKZWVsQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQ2QvTDlwdzJYT2x6NUd2WFZnUHRuaTRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDQvNWVUWWFwazFNVWFnQUdOQ3JKbWRDaDJSL1cxWjQraEhocFlSYmxNdE5XdktwK01RTmRyajV6MGVyZUhyeCtIVk1Gbktzb0ZSdWNHaW9Bciszck54QzM4c2lMVGpNYS85V0hOS1Y2WmFRa2l4WHFzeWpZTHI5d29nL2U5aWJnT2dLeWJYOWtOblAzMHRZRkZVQ1ozNzdyamJSam9rOGlMR1hvVXpQaldMUFh3U3hmQjBiQTg4VmpDK0E9PSIsIkF1dGhJbmZvIjoie1wiQ0lcIjpcIm54aDRMZTFFdjNqYW1odWtpaTBTaUZ6QXpvY2FDRjB2a0hIV3RWdmhNQm0vRTRMa0VDMzZ6aDljTVhSSWdOZzJcXHJcXG5cIixcIkNhbGxlclwiOlwidElpTXE5byt6cW8vNXh1WGZKM2RyU1FVYVg1K09hQ3lRVHR4Mnh3Z1lqOD1cXHJcXG5cIixcIkV4cGlyZVRpbWVcIjpcIjIwMjAtMDgtMjZUMDY6NTk6NDZaXCIsXCJNZWRpYUlkXCI6XCJhMmU3NWUxOTY3NzE0ZmJkYjYwYzM1ZjY4Y2EyNjBjZlwiLFwiU2lnbmF0dXJlXCI6XCJML1R0eGV2VkZjVGV3eXdkaHgzWFVBOFcvK009XCJ9IiwiVmlkZW9NZXRhIjp7IlN0YXR1cyI6Ik5vcm1hbCIsIlZpZGVvSWQiOiJhMmU3NWUxOTY3NzE0ZmJkYjYwYzM1ZjY4Y2EyNjBjZiIsIlRpdGxlIjoi6Z+z6aKRIiwiRHVyYXRpb24iOjYuMDgzNn0sIkFjY2Vzc0tleUlkIjoiU1RTLk5VbUc0OTZRSkNxTUhlODlUZFhiVEVTYkMiLCJBY2Nlc3NLZXlTZWNyZXQiOiI3NGlyaG9abTUzZG54OHo0VHEyYTRBZFRrRDhpRThpd1JEYUxiRktkbzJhNCIsIlJlZ2lvbiI6ImNuLXNoYW5naGFpIiwiQ3VzdG9tZXJJZCI6MTYzNTY4NzcwMDAyNjkwNH0=
             * videoMeta : {"coverURL":null,"duration":6.0836,"status":"Normal","title":"音频","videoId":"a2e75e1967714fbdb60c35f68ca260cf"}
             */

            private String requestId;
            private String playAuth;
            private VideoMetaBean videoMeta;

            public String getRequestId() {
                return requestId;
            }

            public void setRequestId(String requestId) {
                this.requestId = requestId;
            }

            public String getPlayAuth() {
                return playAuth;
            }

            public void setPlayAuth(String playAuth) {
                this.playAuth = playAuth;
            }

            public VideoMetaBean getVideoMeta() {
                return videoMeta;
            }

            public void setVideoMeta(VideoMetaBean videoMeta) {
                this.videoMeta = videoMeta;
            }

            public static class VideoMetaBean {
                /**
                 * coverURL : null
                 * duration : 6.0836
                 * status : Normal
                 * title : 音频
                 * videoId : a2e75e1967714fbdb60c35f68ca260cf
                 */

                private Object coverURL;
                private double duration;
                @SerializedName("status")
                private String statusX;
                private String title;
                private String videoId;

                public Object getCoverURL() {
                    return coverURL;
                }

                public void setCoverURL(Object coverURL) {
                    this.coverURL = coverURL;
                }

                public double getDuration() {
                    return duration;
                }

                public void setDuration(double duration) {
                    this.duration = duration;
                }

                public String getStatusX() {
                    return statusX;
                }

                public void setStatusX(String statusX) {
                    this.statusX = statusX;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getVideoId() {
                    return videoId;
                }

                public void setVideoId(String videoId) {
                    this.videoId = videoId;
                }
            }
        }
    }
}
