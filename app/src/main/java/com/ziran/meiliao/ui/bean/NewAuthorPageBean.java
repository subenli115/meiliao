package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.MusicEntry;

import java.util.List;

/**
 * Created by Administrator on 2019/2/20.
 */

public class NewAuthorPageBean extends Result{


    /**
     * data : {"album":{"collectCount":1,"detail":"这个专辑很不错","title":"七天睡眠","headImg":null,"name":"玛吉薇蒳","isCollectAlbum":false,"shareUrl":""},"albumCatalog":[{"catalogName":"初学者的冥想书","albumMusic":[{"duration":"00:18:24","name":"冥想1：观呼吸","url":"/static/upload/music/albumMusic/1.mp3","isCollectMusic":false}]},{"catalogName":"能量清理冥想","albumMusic":[]},{"catalogName":"朵琳.芙秋冥想","albumMusic":[]},{"catalogName":"祈祷文","albumMusic":[]}]}
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
         * album : {"zf":"https://www.dgli.net/resource/images/album/zf/music_type2_cover82@2x.png","collectCount":10,"finishTimes":0,"detail":"https://www.dgli.net/page/static/albumDetail/a82.html?albumId=82","isBuy":false,"descript":"医生","levelDetail":"普通会员","userCoin":19600,"enough":true,"type":"album","listenCount":435,"roundPic":"https://www.dgli.net/resource/images/album/shareMusic/musictype3_share82@2x.png","ableUsedCoupon":false,"picture":"https://www.dgli.net/resource/images/album/tx/music_type2_cover82@2x.png","vip":"vip","title":"静观减压引导音频(粤语版)","price":0,"name":"赵玲","isCollectAlbum":false,"bg":"https://www.dgli.net/resource/images/album/bg/musictype3_bg82@2x.png","shareTimes":5,"needCoin":0,"memberPrice":0}
         * albumCatalog : [{"catalogName":"静观减压引导音频","albumMusic":[{"duration":"00:03:10","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"3分钟呼吸空间.mp3","shareTitle":"3分钟呼吸空间.mp3","shareUrl":"https://www.dgli.net/album/musicIndex/tid/799/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":799,"st":true,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonADF+CUa/uZ+MrrauQR/i5f+wj7imQhp+Nm\nKaoA+L5NbQ==","isCollectMusic":false,"size":"9.0M"},{"duration":"00:10:10","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"静坐10分钟.mp3","shareTitle":"静坐10分钟.mp3","shareUrl":"https://www.dgli.net/album/musicIndex/tid/800/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":800,"st":true,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonDg7Pj9s8XBMZMfVQVrsue0/ZuNgl+YgMKT\nv9jKzYnVQA==","isCollectMusic":false,"size":"9.0M"},{"duration":"00:10:10","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"身体扫描10分钟 .mp3","shareTitle":"身体扫描10分钟 .mp3","shareUrl":"https://www.dgli.net/album/musicIndex/tid/801/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":801,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBCUAjt8r0ukjb5QCybrJSuX5K7wlQImav9\nMP6q1koGLw==","isCollectMusic":false,"size":"9.0M"},{"duration":"00:42:35","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"1身体扫描","shareTitle":"1身体扫描","shareUrl":"https://www.dgli.net/album/musicIndex/tid/791/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":791,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonDSKGk2mxI3vH0NqXGErGem","isCollectMusic":false,"size":"21.9M"},{"duration":"00:47:01","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"2静坐练习","shareTitle":"2静坐练习","shareUrl":"https://www.dgli.net/album/musicIndex/tid/792/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":792,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonByI6Z5/YWq/Y8JiuJq4A5F","isCollectMusic":false,"size":"21.9M"},{"duration":"00:03:36","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"3伸展练习(一)","shareTitle":"3伸展练习(一)","shareUrl":"https://www.dgli.net/album/musicIndex/tid/793/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":793,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBxMoNaT62JFRTrqeIaL6ZG","isCollectMusic":false,"size":"21.9M"},{"duration":"00:44:46","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"4伸展练习(二)","shareTitle":"4伸展练习(二)","shareUrl":"https://www.dgli.net/album/musicIndex/tid/794/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":794,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBFnH3+YC93GJ4p5GUNEc/J","isCollectMusic":false,"size":"21.9M"},{"duration":"00:01:01","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"5伸展练习(一)","shareTitle":"5伸展练习(一)","shareUrl":"https://www.dgli.net/album/musicIndex/tid/795/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":795,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonDeyA4r9heXz2bJDGCvMg5q","isCollectMusic":false,"size":"21.9M"},{"duration":"00:44:43","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"6伸展练习(二)","shareTitle":"6伸展练习(二)","shareUrl":"https://www.dgli.net/album/musicIndex/tid/796/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":796,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBuzfascSahYnwYd/fhJfST","isCollectMusic":false,"size":"21.9M"},{"duration":"00:18:14","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"7慈心练习","shareTitle":"7慈心练习","shareUrl":"https://www.dgli.net/album/musicIndex/tid/797/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":797,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonAUgpvw+GLQW7ZdzPq3NN1C","isCollectMusic":false,"size":"21.9M"},{"duration":"00:20:10","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"8静坐练习","shareTitle":"8静坐练习","shareUrl":"https://www.dgli.net/album/musicIndex/tid/798/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":798,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBNwrVf+oIPKF94xzxzDfOw","isCollectMusic":false,"size":"21.9M"}]}]
         */

        private AlbumBean album;
        private List<AlbumCatalogBean> albumCatalog;

        public AlbumBean getAlbum() {
            return album;
        }

        public void setAlbum(AlbumBean album) {
            this.album = album;
        }

        public List<AlbumCatalogBean> getAlbumCatalog() {
            return albumCatalog;
        }

        public void setAlbumCatalog(List<AlbumCatalogBean> albumCatalog) {
            this.albumCatalog = albumCatalog;
        }


        public static class AlbumCatalogBean {
            /**
             * catalogName : 静观减压引导音频
             * albumMusic : [{"duration":"00:03:10","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"3分钟呼吸空间.mp3","shareTitle":"3分钟呼吸空间.mp3","shareUrl":"https://www.dgli.net/album/musicIndex/tid/799/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":799,"st":true,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonADF+CUa/uZ+MrrauQR/i5f+wj7imQhp+Nm\nKaoA+L5NbQ==","isCollectMusic":false,"size":"9.0M"},{"duration":"00:10:10","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"静坐10分钟.mp3","shareTitle":"静坐10分钟.mp3","shareUrl":"https://www.dgli.net/album/musicIndex/tid/800/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":800,"st":true,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonDg7Pj9s8XBMZMfVQVrsue0/ZuNgl+YgMKT\nv9jKzYnVQA==","isCollectMusic":false,"size":"9.0M"},{"duration":"00:10:10","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"身体扫描10分钟 .mp3","shareTitle":"身体扫描10分钟 .mp3","shareUrl":"https://www.dgli.net/album/musicIndex/tid/801/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":801,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBCUAjt8r0ukjb5QCybrJSuX5K7wlQImav9\nMP6q1koGLw==","isCollectMusic":false,"size":"9.0M"},{"duration":"00:42:35","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"1身体扫描","shareTitle":"1身体扫描","shareUrl":"https://www.dgli.net/album/musicIndex/tid/791/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":791,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonDSKGk2mxI3vH0NqXGErGem","isCollectMusic":false,"size":"21.9M"},{"duration":"00:47:01","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"2静坐练习","shareTitle":"2静坐练习","shareUrl":"https://www.dgli.net/album/musicIndex/tid/792/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":792,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonByI6Z5/YWq/Y8JiuJq4A5F","isCollectMusic":false,"size":"21.9M"},{"duration":"00:03:36","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"3伸展练习(一)","shareTitle":"3伸展练习(一)","shareUrl":"https://www.dgli.net/album/musicIndex/tid/793/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":793,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBxMoNaT62JFRTrqeIaL6ZG","isCollectMusic":false,"size":"21.9M"},{"duration":"00:44:46","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"4伸展练习(二)","shareTitle":"4伸展练习(二)","shareUrl":"https://www.dgli.net/album/musicIndex/tid/794/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":794,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBFnH3+YC93GJ4p5GUNEc/J","isCollectMusic":false,"size":"21.9M"},{"duration":"00:01:01","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"5伸展练习(一)","shareTitle":"5伸展练习(一)","shareUrl":"https://www.dgli.net/album/musicIndex/tid/795/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":795,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonDeyA4r9heXz2bJDGCvMg5q","isCollectMusic":false,"size":"21.9M"},{"duration":"00:44:43","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"6伸展练习(二)","shareTitle":"6伸展练习(二)","shareUrl":"https://www.dgli.net/album/musicIndex/tid/796/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":796,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBuzfascSahYnwYd/fhJfST","isCollectMusic":false,"size":"21.9M"},{"duration":"00:18:14","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"7慈心练习","shareTitle":"7慈心练习","shareUrl":"https://www.dgli.net/album/musicIndex/tid/797/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":797,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonAUgpvw+GLQW7ZdzPq3NN1C","isCollectMusic":false,"size":"21.9M"},{"duration":"00:20:10","shareDescript":"助你在喧嚣忙乱的世界里，找回平静、专注、清明、幸福。","name":"8静坐练习","shareTitle":"8静坐练习","shareUrl":"https://www.dgli.net/album/musicIndex/tid/798/isShare/1?1=1&shareUser=332f34cdffced0aaade59f925b8241","sharePic":"https://www.dgli.net/resource/images/album/zf/musictype3_share82@2x.png","musicId":798,"st":false,"url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonBNwrVf+oIPKF94xzxzDfOw","isCollectMusic":false,"size":"21.9M"}]
             */

            private String catalogName;
            private List<MusicEntry> albumMusic;

            public String getCatalogName() {
                return catalogName;
            }

            public void setCatalogName(String catalogName) {
                this.catalogName = catalogName;
            }

            public List<MusicEntry> getAlbumMusic() {
                return albumMusic;
            }

            public void setAlbumMusic(List<MusicEntry> albumMusic) {
                this.albumMusic = albumMusic;
            }

        }
    }
}
