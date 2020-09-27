package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

public class AliAuthBean extends Result {

    /**
     * data : {"requestId":"EBA3C83D-F161-4A26-8B48-DADB50D96AA5","playAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklyNUROQmR2QXFLZEUrNU9qYlh6TGpYUUFmdGhmaVovU29EejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsck1xRmNRWUd4eWJNcE1vdHM0Sm9GdjlKcExGc3QySjZyOEpqc1VwNUpzQjFWaXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0dOLzZuNU9aUXJ6emI0WmhKZWVsQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQ2QvTDlwdzJYT2x6NUd2WFZnUHRuaTRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDQvNWVUWWFwazFNVWFnQUdqdk1HRUpPLzRuS2srZkM1V2pzdmoxa05CbktHSzQ4MFdteVVxampMKzRrWXA1VVlIUGpGTEpnVTJHUCtySmQwK1dkcnlrTkdnRUtzbEhkQUxHdTV1ZWIzRE5WWkxyUUEya0s2UEFaeHgxdkc2bVJ0S1ZLVWVmLzE0U0dxZitWeGNpYVRHYU0rd3JHZldoc1hQc0xpNHd6Y1R4VEVtYlIremFEOVYvMkJ3R1E9PSIsIkF1dGhJbmZvIjoie1wiQ0lcIjpcIlJOVzFYUFFGOGZKNElHVE5DSXA4Q1VvaGNCa3psaVB6RkZaT1lsOE5jL2svT3ZjbTBzQ0hiQ0ZUN1UvcGtFa0RcXHJcXG5cIixcIkNhbGxlclwiOlwieTg4Z2Z3T0VxSzFGY3hEL3M4N3BNM2VmRk1LZVdIREF6TlVTL0llbWpVOD1cXHJcXG5cIixcIkV4cGlyZVRpbWVcIjpcIjIwMjAtMDctMjFUMDE6NTE6MTdaXCIsXCJNZWRpYUlkXCI6XCIxMzNmMzUzMjRmYmM0NTY3YTJjOTNmMjhmYTQyNTRkNVwiLFwiU2lnbmF0dXJlXCI6XCJxOXlSMXV2WVlaS3RkR3VKOEkzdjBON2xKUTQ9XCJ9IiwiVmlkZW9NZXRhIjp7IlN0YXR1cyI6Ik5vcm1hbCIsIlZpZGVvSWQiOiIxMzNmMzUzMjRmYmM0NTY3YTJjOTNmMjhmYTQyNTRkNSIsIlRpdGxlIjoi6Z+z6aKRIiwiRHVyYXRpb24iOjUuNDh9LCJBY2Nlc3NLZXlJZCI6IlNUUy5OU3hOYXRFeGVMUUhvWnppdFVyV3NmWnlCIiwiQWNjZXNzS2V5U2VjcmV0IjoiNXo3NUQzUDdTcVR1WFJLaWNOcnhQbjFMbmVNc1VyTnFiNEFXdTNNclJUYUIiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSIsIkN1c3RvbWVySWQiOjE2MzU2ODc3MDAwMjY5MDR9","videoMeta":{"coverURL":null,"duration":5.48,"status":"Normal","title":"音频","videoId":"133f35324fbc4567a2c93f28fa4254d5"}}
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
         * requestId : EBA3C83D-F161-4A26-8B48-DADB50D96AA5
         * playAuth : eyJTZWN1cml0eVRva2VuIjoiQ0FJUzN3SjFxNkZ0NUIyeWZTaklyNUROQmR2QXFLZEUrNU9qYlh6TGpYUUFmdGhmaVovU29EejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsck1xRmNRWUd4eWJNcE1vdHM0Sm9GdjlKcExGc3QySjZyOEpqc1VwNUpzQjFWaXBzdlhKYXNEVkVma3VFNVhFTWlJNS8wMGU2TC8rY2lyWVhEN0JHSmFWaUpsaFE4MEtWdzJqRjFSdkQ4dFhJUTBRazYxOUszemRaOW1nTGlidWkzdnhDa1J2MkhCaWptOHR4cW1qL015UTV4MzFpMXYweStCM3dZSHRPY3FjYThCOU1ZMVdUc3Uxdm9oemFyR1Q2Q3BaK2psTStxQVU2cWxZNG1YcnM5cUhFa0ZOd0JpWFNaMjJsT2RpTndoa2ZLTTNOcmRacGZ6bjc1MUN0L2ZVaXA3OHhtUW1YNGdYY1Z5R0dOLzZuNU9aUXJ6emI0WmhKZWVsQVJtWGpJRFRiS3VTbWhnL2ZIY1dPRGxOZjljY01YSnFBWFF1TUdxQ2QvTDlwdzJYT2x6NUd2WFZnUHRuaTRBSjVsSHA3TWVNR1YrRGVMeVF5aDBFSWFVN2EwNDQvNWVUWWFwazFNVWFnQUdqdk1HRUpPLzRuS2srZkM1V2pzdmoxa05CbktHSzQ4MFdteVVxampMKzRrWXA1VVlIUGpGTEpnVTJHUCtySmQwK1dkcnlrTkdnRUtzbEhkQUxHdTV1ZWIzRE5WWkxyUUEya0s2UEFaeHgxdkc2bVJ0S1ZLVWVmLzE0U0dxZitWeGNpYVRHYU0rd3JHZldoc1hQc0xpNHd6Y1R4VEVtYlIremFEOVYvMkJ3R1E9PSIsIkF1dGhJbmZvIjoie1wiQ0lcIjpcIlJOVzFYUFFGOGZKNElHVE5DSXA4Q1VvaGNCa3psaVB6RkZaT1lsOE5jL2svT3ZjbTBzQ0hiQ0ZUN1UvcGtFa0RcXHJcXG5cIixcIkNhbGxlclwiOlwieTg4Z2Z3T0VxSzFGY3hEL3M4N3BNM2VmRk1LZVdIREF6TlVTL0llbWpVOD1cXHJcXG5cIixcIkV4cGlyZVRpbWVcIjpcIjIwMjAtMDctMjFUMDE6NTE6MTdaXCIsXCJNZWRpYUlkXCI6XCIxMzNmMzUzMjRmYmM0NTY3YTJjOTNmMjhmYTQyNTRkNVwiLFwiU2lnbmF0dXJlXCI6XCJxOXlSMXV2WVlaS3RkR3VKOEkzdjBON2xKUTQ9XCJ9IiwiVmlkZW9NZXRhIjp7IlN0YXR1cyI6Ik5vcm1hbCIsIlZpZGVvSWQiOiIxMzNmMzUzMjRmYmM0NTY3YTJjOTNmMjhmYTQyNTRkNSIsIlRpdGxlIjoi6Z+z6aKRIiwiRHVyYXRpb24iOjUuNDh9LCJBY2Nlc3NLZXlJZCI6IlNUUy5OU3hOYXRFeGVMUUhvWnppdFVyV3NmWnlCIiwiQWNjZXNzS2V5U2VjcmV0IjoiNXo3NUQzUDdTcVR1WFJLaWNOcnhQbjFMbmVNc1VyTnFiNEFXdTNNclJUYUIiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSIsIkN1c3RvbWVySWQiOjE2MzU2ODc3MDAwMjY5MDR9
         * videoMeta : {"coverURL":null,"duration":5.48,"status":"Normal","title":"音频","videoId":"133f35324fbc4567a2c93f28fa4254d5"}
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
             * duration : 5.48
             * status : Normal
             * title : 音频
             * videoId : 133f35324fbc4567a2c93f28fa4254d5
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
