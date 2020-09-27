package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class AliUploadFileBean extends Result {

    /**
     * data : {"requestId":"C42A7115-165B-4844-821D-449CA4AF2EBC","videoId":"cfca802bd1a04da98f164c6e0d0f562a","uploadAddress":"eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTMyYmJlOTMxYzY0NTExZWE4NzBhMDAxNjNlMWE2MjVlIiwiRmlsZU5hbWUiOiJzdi80MzAzMmZkZS0xNzM2NjQ4MjlkYS80MzAzMmZkZS0xNzM2NjQ4MjlkYS5hbXIifQ==","uploadAuth":"eyJTZWN1cml0eVRva2VuIjoiQ0FJUzBBUjFxNkZ0NUIyeWZTaklyNURiTE9qYmxMZG04b0dwVDBqRGptUW5TLzVZaDZib2hUejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsclVxRmNRWUd4eWJNcE1vdHM0Sm9GdjlKcGZadjh1ODRZQURpNUNqUWZ0TG44VWxtcDI4V2Y3d2FmK0FVQlhHQ1RtZDVNTVlvOWJUY1RHbFFDWnVXLy90b0pWN2I5TVJjeENsWkQ1ZGZybC9MUmRqcjhsbzF4R3pVUEcyS1V6U24zYjNCa2hsc1JZZTcyUms4dmFIeGRhQXpSRGNnVmJtcUpjU3ZKK2pDNEM4WXM5Z0c1MTlYdHlwdm9weGJiR1Q4Q05aNXo5QTlxcDlrTTQ5L2l6YzdQNlFIMzViNFJpTkw4L1o3dFFOWHdoaWZmb2JIYTlZcmZIZ21OaGx2dkRTajQzdDF5dFZPZVpjWDBha1E1dTdrdTdaSFArb0x0OGphWXZqUDNQRTNyTHBNWUx1NFQ0OFpYVVNPRHREWWNaRFVIaHJFazRSVWpYZEk2T2Y4VXJXU1FDN1dzcjIxN290ZzdGeXlrM3M4TWFIQWtXTFg3U0IyRHdFQjRjNGFFb2tWVzRSeG5lelc2VUJhUkJwYmxkN0JxNmNWNWxPZEJSWm9LK0t6UXJKVFg5RXoycExtdUQ2ZS9MT3M3b0RWSjM3V1p0S3l1aDRZNDlkNFU4clZFalBRcWl5a1QwcEZncGZUSzFSemJQbU5MS205YmFCMjUvelcrUGREZTBkc1Znb0lGS09waUdXRzNSTE5uK3p0Sjl4YmtlRStzS1VsL2JHK1o0eFMxSWx1SXdDVkZpSWVJWXk5MVUrdS9Mc3RCbksrYkc1RDMzdDVYUi91UHVncHRVUXN4UThJNjM3MmJiQzVtNlA0a2I5Ty9kcHhKM2xQMFIwV2dteWRuQkR4L1NmdTJrS3ZSaHBrUnZ2WTB0Q3NRdk1pRDdySnB4R2dxelJseWxlZm81WG1QWEZUUW1uOGw1cEFNbXkvNjB4WHVkdmJDakgxMHA2V0tjREdvQUJrOEpaeU0xcWlmMUVUSlNYUVlhTnZCZStqVDRsOEp2TzhjZUM4QW52djRFOXZYYUZ5RFgzLzloZXJCZlZxbyswWnJYOW9JMEN5THNmYTN3M0RnREFmdzFPK3RzL25vS0l0Q21mdTlqT2pWaTBlZVlGT1pudmY4VnFRcVNRaXVQMHpGbFJDY1lMQVRjQkkwdDllMGtxQzBybkpRSzlTYzFHR3E1VHJ5ck1ucmM9IiwiQWNjZXNzS2V5SWQiOiJTVFMuTlNuZ1JveWhHRUNCTW5yamRyR3F0aGNDZyIsIkV4cGlyZVVUQ1RpbWUiOiIyMDIwLTA3LTE5VDA5OjU0OjMyWiIsIkFjY2Vzc0tleVNlY3JldCI6IjI5RkhQWmo0am9HQ0RMcmJpNmFZQVpXcnlKWm1OZ3VIeWpYR1NnWHJVWEQyIiwiRXhwaXJhdGlvbiI6IjM2MDAiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSJ9"}
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
         * requestId : C42A7115-165B-4844-821D-449CA4AF2EBC
         * videoId : cfca802bd1a04da98f164c6e0d0f562a
         * uploadAddress : eyJFbmRwb2ludCI6Imh0dHBzOi8vb3NzLWNuLXNoYW5naGFpLmFsaXl1bmNzLmNvbSIsIkJ1Y2tldCI6Im91dGluLTMyYmJlOTMxYzY0NTExZWE4NzBhMDAxNjNlMWE2MjVlIiwiRmlsZU5hbWUiOiJzdi80MzAzMmZkZS0xNzM2NjQ4MjlkYS80MzAzMmZkZS0xNzM2NjQ4MjlkYS5hbXIifQ==
         * uploadAuth : eyJTZWN1cml0eVRva2VuIjoiQ0FJUzBBUjFxNkZ0NUIyeWZTaklyNURiTE9qYmxMZG04b0dwVDBqRGptUW5TLzVZaDZib2hUejJJSGhKZVhOdkJPMGV0ZjQrbVdCWTdQY1lsclVxRmNRWUd4eWJNcE1vdHM0Sm9GdjlKcGZadjh1ODRZQURpNUNqUWZ0TG44VWxtcDI4V2Y3d2FmK0FVQlhHQ1RtZDVNTVlvOWJUY1RHbFFDWnVXLy90b0pWN2I5TVJjeENsWkQ1ZGZybC9MUmRqcjhsbzF4R3pVUEcyS1V6U24zYjNCa2hsc1JZZTcyUms4dmFIeGRhQXpSRGNnVmJtcUpjU3ZKK2pDNEM4WXM5Z0c1MTlYdHlwdm9weGJiR1Q4Q05aNXo5QTlxcDlrTTQ5L2l6YzdQNlFIMzViNFJpTkw4L1o3dFFOWHdoaWZmb2JIYTlZcmZIZ21OaGx2dkRTajQzdDF5dFZPZVpjWDBha1E1dTdrdTdaSFArb0x0OGphWXZqUDNQRTNyTHBNWUx1NFQ0OFpYVVNPRHREWWNaRFVIaHJFazRSVWpYZEk2T2Y4VXJXU1FDN1dzcjIxN290ZzdGeXlrM3M4TWFIQWtXTFg3U0IyRHdFQjRjNGFFb2tWVzRSeG5lelc2VUJhUkJwYmxkN0JxNmNWNWxPZEJSWm9LK0t6UXJKVFg5RXoycExtdUQ2ZS9MT3M3b0RWSjM3V1p0S3l1aDRZNDlkNFU4clZFalBRcWl5a1QwcEZncGZUSzFSemJQbU5MS205YmFCMjUvelcrUGREZTBkc1Znb0lGS09waUdXRzNSTE5uK3p0Sjl4YmtlRStzS1VsL2JHK1o0eFMxSWx1SXdDVkZpSWVJWXk5MVUrdS9Mc3RCbksrYkc1RDMzdDVYUi91UHVncHRVUXN4UThJNjM3MmJiQzVtNlA0a2I5Ty9kcHhKM2xQMFIwV2dteWRuQkR4L1NmdTJrS3ZSaHBrUnZ2WTB0Q3NRdk1pRDdySnB4R2dxelJseWxlZm81WG1QWEZUUW1uOGw1cEFNbXkvNjB4WHVkdmJDakgxMHA2V0tjREdvQUJrOEpaeU0xcWlmMUVUSlNYUVlhTnZCZStqVDRsOEp2TzhjZUM4QW52djRFOXZYYUZ5RFgzLzloZXJCZlZxbyswWnJYOW9JMEN5THNmYTN3M0RnREFmdzFPK3RzL25vS0l0Q21mdTlqT2pWaTBlZVlGT1pudmY4VnFRcVNRaXVQMHpGbFJDY1lMQVRjQkkwdDllMGtxQzBybkpRSzlTYzFHR3E1VHJ5ck1ucmM9IiwiQWNjZXNzS2V5SWQiOiJTVFMuTlNuZ1JveWhHRUNCTW5yamRyR3F0aGNDZyIsIkV4cGlyZVVUQ1RpbWUiOiIyMDIwLTA3LTE5VDA5OjU0OjMyWiIsIkFjY2Vzc0tleVNlY3JldCI6IjI5RkhQWmo0am9HQ0RMcmJpNmFZQVpXcnlKWm1OZ3VIeWpYR1NnWHJVWEQyIiwiRXhwaXJhdGlvbiI6IjM2MDAiLCJSZWdpb24iOiJjbi1zaGFuZ2hhaSJ9
         */

        private String requestId;
        private String videoId;
        private String uploadAddress;
        private String uploadAuth;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getUploadAddress() {
            return uploadAddress;
        }

        public void setUploadAddress(String uploadAddress) {
            this.uploadAddress = uploadAddress;
        }

        public String getUploadAuth() {
            return uploadAuth;
        }

        public void setUploadAuth(String uploadAuth) {
            this.uploadAuth = uploadAuth;
        }
    }
}
