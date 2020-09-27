package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class AliVideoInfoBean extends Result {


    /**
     * data : {"requestId":"7A521F6E-4779-4237-B996-64520B711A82","playInfoList":[{"width":1280,"height":720,"size":710270,"playURL":"https://outin-32bbe931c64511ea870a00163e1a625e.oss-cn-shanghai.aliyuncs.com/sv/762b33d-1736a626f27/762b33d-1736a626f27.mp4?Expires=1595322290&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=zFEouKvS8GhpyipOd%2BCx8UMzV0s%3D","bitrate":"3642.41","definition":"OD","duration":"1.56","format":"mp4","fps":"90000.0","encrypt":0,"plaintext":null,"complexity":null,"streamType":"video","rand":null,"jobId":"3874f1622364489aa482e51eec5ed64702","preprocessStatus":"UnPreprocess","watermarkId":null,"status":"Normal","creationTime":"2020-07-20T04:01:42Z","modificationTime":"2020-07-20T04:01:42Z","encryptType":null,"narrowBandType":"0","specification":"Original"}],"videoBase":{"outputType":"oss","coverURL":"http://outin-32bbe931c64511ea870a00163e1a625e.oss-cn-shanghai.aliyuncs.com/3874f1622364489aa482e51eec5ed647/snapshots/871d97c1d9c64a91976652ecfe896a76-00001.jpg?Expires=1595322290&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=42crn6uz3tq3xEbh9joRF5Uv79E%3D","duration":"1.56","status":"Normal","title":"视频","videoId":"3874f1622364489aa482e51eec5ed647","mediaType":"video","creationTime":"2020-07-20T04:01:42Z","transcodeMode":"NoTranscode","thumbnailList":[]}}
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
         * requestId : 7A521F6E-4779-4237-B996-64520B711A82
         * playInfoList : [{"width":1280,"height":720,"size":710270,"playURL":"https://outin-32bbe931c64511ea870a00163e1a625e.oss-cn-shanghai.aliyuncs.com/sv/762b33d-1736a626f27/762b33d-1736a626f27.mp4?Expires=1595322290&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=zFEouKvS8GhpyipOd%2BCx8UMzV0s%3D","bitrate":"3642.41","definition":"OD","duration":"1.56","format":"mp4","fps":"90000.0","encrypt":0,"plaintext":null,"complexity":null,"streamType":"video","rand":null,"jobId":"3874f1622364489aa482e51eec5ed64702","preprocessStatus":"UnPreprocess","watermarkId":null,"status":"Normal","creationTime":"2020-07-20T04:01:42Z","modificationTime":"2020-07-20T04:01:42Z","encryptType":null,"narrowBandType":"0","specification":"Original"}]
         * videoBase : {"outputType":"oss","coverURL":"http://outin-32bbe931c64511ea870a00163e1a625e.oss-cn-shanghai.aliyuncs.com/3874f1622364489aa482e51eec5ed647/snapshots/871d97c1d9c64a91976652ecfe896a76-00001.jpg?Expires=1595322290&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=42crn6uz3tq3xEbh9joRF5Uv79E%3D","duration":"1.56","status":"Normal","title":"视频","videoId":"3874f1622364489aa482e51eec5ed647","mediaType":"video","creationTime":"2020-07-20T04:01:42Z","transcodeMode":"NoTranscode","thumbnailList":[]}
         */

        private String requestId;
        private VideoBaseBean videoBase;
        private List<PlayInfoListBean> playInfoList;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public VideoBaseBean getVideoBase() {
            return videoBase;
        }

        public void setVideoBase(VideoBaseBean videoBase) {
            this.videoBase = videoBase;
        }

        public List<PlayInfoListBean> getPlayInfoList() {
            return playInfoList;
        }

        public void setPlayInfoList(List<PlayInfoListBean> playInfoList) {
            this.playInfoList = playInfoList;
        }

        public static class VideoBaseBean {
            /**
             * outputType : oss
             * coverURL : http://outin-32bbe931c64511ea870a00163e1a625e.oss-cn-shanghai.aliyuncs.com/3874f1622364489aa482e51eec5ed647/snapshots/871d97c1d9c64a91976652ecfe896a76-00001.jpg?Expires=1595322290&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=42crn6uz3tq3xEbh9joRF5Uv79E%3D
             * duration : 1.56
             * status : Normal
             * title : 视频
             * videoId : 3874f1622364489aa482e51eec5ed647
             * mediaType : video
             * creationTime : 2020-07-20T04:01:42Z
             * transcodeMode : NoTranscode
             * thumbnailList : []
             */

            private String outputType;
            private String coverURL;
            private String duration;
            @SerializedName("status")
            private String statusX;
            private String title;
            private String videoId;
            private String mediaType;
            private String creationTime;
            private String transcodeMode;
            private List<?> thumbnailList;

            public String getOutputType() {
                return outputType;
            }

            public void setOutputType(String outputType) {
                this.outputType = outputType;
            }

            public String getCoverURL() {
                return coverURL;
            }

            public void setCoverURL(String coverURL) {
                this.coverURL = coverURL;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
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

            public String getMediaType() {
                return mediaType;
            }

            public void setMediaType(String mediaType) {
                this.mediaType = mediaType;
            }

            public String getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(String creationTime) {
                this.creationTime = creationTime;
            }

            public String getTranscodeMode() {
                return transcodeMode;
            }

            public void setTranscodeMode(String transcodeMode) {
                this.transcodeMode = transcodeMode;
            }

            public List<?> getThumbnailList() {
                return thumbnailList;
            }

            public void setThumbnailList(List<?> thumbnailList) {
                this.thumbnailList = thumbnailList;
            }
        }

        public static class PlayInfoListBean {
            /**
             * width : 1280
             * height : 720
             * size : 710270
             * playURL : https://outin-32bbe931c64511ea870a00163e1a625e.oss-cn-shanghai.aliyuncs.com/sv/762b33d-1736a626f27/762b33d-1736a626f27.mp4?Expires=1595322290&OSSAccessKeyId=LTAIxSaOfEzCnBOj&Signature=zFEouKvS8GhpyipOd%2BCx8UMzV0s%3D
             * bitrate : 3642.41
             * definition : OD
             * duration : 1.56
             * format : mp4
             * fps : 90000.0
             * encrypt : 0
             * plaintext : null
             * complexity : null
             * streamType : video
             * rand : null
             * jobId : 3874f1622364489aa482e51eec5ed64702
             * preprocessStatus : UnPreprocess
             * watermarkId : null
             * status : Normal
             * creationTime : 2020-07-20T04:01:42Z
             * modificationTime : 2020-07-20T04:01:42Z
             * encryptType : null
             * narrowBandType : 0
             * specification : Original
             */

            private int width;
            private int height;
            private int size;
            private String playURL;
            private String bitrate;
            private String definition;
            private String duration;
            private String format;
            private String fps;
            private int encrypt;
            private Object plaintext;
            private Object complexity;
            private String streamType;
            private Object rand;
            private String jobId;
            private String preprocessStatus;
            private Object watermarkId;
            @SerializedName("status")
            private String statusX;
            private String creationTime;
            private String modificationTime;
            private Object encryptType;
            private String narrowBandType;
            private String specification;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getPlayURL() {
                return playURL;
            }

            public void setPlayURL(String playURL) {
                this.playURL = playURL;
            }

            public String getBitrate() {
                return bitrate;
            }

            public void setBitrate(String bitrate) {
                this.bitrate = bitrate;
            }

            public String getDefinition() {
                return definition;
            }

            public void setDefinition(String definition) {
                this.definition = definition;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public String getFps() {
                return fps;
            }

            public void setFps(String fps) {
                this.fps = fps;
            }

            public int getEncrypt() {
                return encrypt;
            }

            public void setEncrypt(int encrypt) {
                this.encrypt = encrypt;
            }

            public Object getPlaintext() {
                return plaintext;
            }

            public void setPlaintext(Object plaintext) {
                this.plaintext = plaintext;
            }

            public Object getComplexity() {
                return complexity;
            }

            public void setComplexity(Object complexity) {
                this.complexity = complexity;
            }

            public String getStreamType() {
                return streamType;
            }

            public void setStreamType(String streamType) {
                this.streamType = streamType;
            }

            public Object getRand() {
                return rand;
            }

            public void setRand(Object rand) {
                this.rand = rand;
            }

            public String getJobId() {
                return jobId;
            }

            public void setJobId(String jobId) {
                this.jobId = jobId;
            }

            public String getPreprocessStatus() {
                return preprocessStatus;
            }

            public void setPreprocessStatus(String preprocessStatus) {
                this.preprocessStatus = preprocessStatus;
            }

            public Object getWatermarkId() {
                return watermarkId;
            }

            public void setWatermarkId(Object watermarkId) {
                this.watermarkId = watermarkId;
            }

            public String getStatusX() {
                return statusX;
            }

            public void setStatusX(String statusX) {
                this.statusX = statusX;
            }

            public String getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(String creationTime) {
                this.creationTime = creationTime;
            }

            public String getModificationTime() {
                return modificationTime;
            }

            public void setModificationTime(String modificationTime) {
                this.modificationTime = modificationTime;
            }

            public Object getEncryptType() {
                return encryptType;
            }

            public void setEncryptType(Object encryptType) {
                this.encryptType = encryptType;
            }

            public String getNarrowBandType() {
                return narrowBandType;
            }

            public void setNarrowBandType(String narrowBandType) {
                this.narrowBandType = narrowBandType;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }
        }
    }
}
