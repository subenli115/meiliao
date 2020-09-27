package com.ziran.meiliao.ui.bean;

import com.google.gson.annotations.SerializedName;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class HomeRecommendBean extends Result {

    /**
     * data : {"records":[{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a90","userId":41,"username":"17080000090","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"只吃一口","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000090","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":19,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png","latitude":"29.60400005383401","longitude":"106.53067231712397","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h90"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a91","userId":42,"username":"17080000091","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"越然纸上","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000091","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e075c5cac15c43a59a1a2975115fc0af.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":19,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e075c5cac15c43a59a1a2975115fc0af.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e075c5cac15c43a59a1a2975115fc0af.png","latitude":"29.604184298884714","longitude":"106.53074512747098","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h91"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a92","userId":43,"username":"17080000092","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"侃叔吃不下了","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000092","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/10487019379e48f3ab4d39e9365102b7.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":18,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/10487019379e48f3ab4d39e9365102b7.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/10487019379e48f3ab4d39e9365102b7.png","latitude":"29.604058020693962","longitude":"106.53070145034121","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h92"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a93","userId":44,"username":"17080000093","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"国民打工仔","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000093","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/5b89a7f02e84489bb04fef077342873c.png","sex":1,"introduce":"","realName":null,"idCard":null,"age":27,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/5b89a7f02e84489bb04fef077342873c.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/5b89a7f02e84489bb04fef077342873c.png","latitude":"29.604200609034326","longitude":"106.53067976195943","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h93"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a94","userId":46,"username":"17080000094","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"孤单的猫","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000094","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/79d335b49bcb49a8baa78fcae338d422.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":17,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/79d335b49bcb49a8baa78fcae338d422.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/79d335b49bcb49a8baa78fcae338d422.png","latitude":"29.604073029293364","longitude":"106.53079164350619","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h94"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a95","userId":45,"username":"17080000095","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"落叶随风NbL","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000095","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/d89c3b26a0e8498aad48c85643591120.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":21,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/d89c3b26a0e8498aad48c85643591120.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/d89c3b26a0e8498aad48c85643591120.png","latitude":"29.603965867087783","longitude":"106.5305529670154","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h95"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a96","userId":47,"username":"17080000096","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"呆呆宁与","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000096","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8ca06dccaada4c07b1f48b3eb2bc33d5.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":19,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8ca06dccaada4c07b1f48b3eb2bc33d5.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8ca06dccaada4c07b1f48b3eb2bc33d5.png","latitude":"29.6039821102063","longitude":"106.53072039955644","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h96"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a97","userId":48,"username":"17080000097","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"总有刁民撩大叔","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000097","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/59d1443a384a433780358ec78f899f38.png","sex":1,"introduce":"","realName":null,"idCard":null,"age":27,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/59d1443a384a433780358ec78f899f38.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/59d1443a384a433780358ec78f899f38.png","latitude":"29.604122446939417","longitude":"106.5307443532326","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h97"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a98","userId":49,"username":"17080000098","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"肥飞","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000098","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/413c6847dfad439a8df88232f6fb658a.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":23,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/413c6847dfad439a8df88232f6fb658a.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/413c6847dfad439a8df88232f6fb658a.png","latitude":"29.603958011223906","longitude":"106.53057052615874","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h98"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a99","userId":50,"username":"17080000099","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"迟日幕林","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000099","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/66d1f23b34144ad6a04fa40a511aa949.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":24,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/66d1f23b34144ad6a04fa40a511aa949.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/66d1f23b34144ad6a04fa40a511aa949.png","latitude":"29.604086025971064","longitude":"106.53070575502747","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h99"}],"total":64,"size":10,"current":4,"orders":[],"hitCount":false,"searchCount":true,"pages":7}
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
         * records : [{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a90","userId":41,"username":"17080000090","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"只吃一口","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000090","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":19,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png","latitude":"29.60400005383401","longitude":"106.53067231712397","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h90"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a91","userId":42,"username":"17080000091","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"越然纸上","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000091","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e075c5cac15c43a59a1a2975115fc0af.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":19,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e075c5cac15c43a59a1a2975115fc0af.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e075c5cac15c43a59a1a2975115fc0af.png","latitude":"29.604184298884714","longitude":"106.53074512747098","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h91"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a92","userId":43,"username":"17080000092","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"侃叔吃不下了","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000092","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/10487019379e48f3ab4d39e9365102b7.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":18,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/10487019379e48f3ab4d39e9365102b7.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/10487019379e48f3ab4d39e9365102b7.png","latitude":"29.604058020693962","longitude":"106.53070145034121","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h92"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a93","userId":44,"username":"17080000093","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"国民打工仔","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000093","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/5b89a7f02e84489bb04fef077342873c.png","sex":1,"introduce":"","realName":null,"idCard":null,"age":27,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/5b89a7f02e84489bb04fef077342873c.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/5b89a7f02e84489bb04fef077342873c.png","latitude":"29.604200609034326","longitude":"106.53067976195943","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h93"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a94","userId":46,"username":"17080000094","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"孤单的猫","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000094","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/79d335b49bcb49a8baa78fcae338d422.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":17,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/79d335b49bcb49a8baa78fcae338d422.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/79d335b49bcb49a8baa78fcae338d422.png","latitude":"29.604073029293364","longitude":"106.53079164350619","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h94"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a95","userId":45,"username":"17080000095","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"落叶随风NbL","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000095","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/d89c3b26a0e8498aad48c85643591120.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":21,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/d89c3b26a0e8498aad48c85643591120.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/d89c3b26a0e8498aad48c85643591120.png","latitude":"29.603965867087783","longitude":"106.5305529670154","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h95"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a96","userId":47,"username":"17080000096","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"呆呆宁与","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000096","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8ca06dccaada4c07b1f48b3eb2bc33d5.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":19,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8ca06dccaada4c07b1f48b3eb2bc33d5.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/8ca06dccaada4c07b1f48b3eb2bc33d5.png","latitude":"29.6039821102063","longitude":"106.53072039955644","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h96"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a97","userId":48,"username":"17080000097","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"总有刁民撩大叔","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000097","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/59d1443a384a433780358ec78f899f38.png","sex":1,"introduce":"","realName":null,"idCard":null,"age":27,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/59d1443a384a433780358ec78f899f38.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/59d1443a384a433780358ec78f899f38.png","latitude":"29.604122446939417","longitude":"106.5307443532326","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h97"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a98","userId":49,"username":"17080000098","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"肥飞","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000098","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/413c6847dfad439a8df88232f6fb658a.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":23,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/413c6847dfad439a8df88232f6fb658a.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/413c6847dfad439a8df88232f6fb658a.png","latitude":"29.603958011223906","longitude":"106.53057052615874","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h98"},{"id":"b2f7918eda4f8d5320b0ca6fe5ad2a99","userId":50,"username":"17080000099","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"迟日幕林","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-19 13:20:53","updateTime":"2020-07-15 15:09:14","delFlag":"0","lockFlag":"0","phone":"17080000099","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/66d1f23b34144ad6a04fa40a511aa949.png","sex":1,"introduce":null,"realName":null,"idCard":null,"age":24,"preference":"2","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/66d1f23b34144ad6a04fa40a511aa949.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/66d1f23b34144ad6a04fa40a511aa949.png","latitude":"29.604086025971064","longitude":"106.53070575502747","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"id0h99"}]
         * total : 64
         * size : 10
         * current : 4
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 7
         */

        private int total;
        private int size;
        private int current;
        private boolean hitCount;
        private boolean searchCount;
        private int pages;
        private List<UserBean.DataBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isHitCount() {
            return hitCount;
        }

        public void setHitCount(boolean hitCount) {
            this.hitCount = hitCount;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<UserBean.DataBean> getRecords() {
            return records;
        }

        public void setRecords(List<UserBean.DataBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean {
            /**
             * id : b2f7918eda4f8d5320b0ca6fe5ad2a90
             * userId : 41
             * username : 17080000090
             * password : $2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze
             * nickname : 只吃一口
             * salt : null
             * wxOpenid : null
             * qqOpenid : null
             * createTime : 2020-05-19 13:20:53
             * updateTime : 2020-07-15 15:09:14
             * delFlag : 0
             * lockFlag : 0
             * phone : 17080000090
             * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png
             * sex : 1
             * introduce : null
             * realName : null
             * idCard : null
             * age : 19
             * preference : 1
             * region : 重庆市-重庆市
             * gradeValue : 0
             * offline : 0
             * userAccount : null
             * homepageImages : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png
             * recommendImages : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/e09528cfc36948189f3f69c75bf80d04.png
             * latitude : 29.60400005383401
             * longitude : 106.53067231712397
             * personality : null
             * shape : null
             * height : null
             * objective : null
             * click : null
             * video : null
             * voice : null
             * job : null
             * annualSalary : null
             * industry : null
             * major : null
             * school : null
             * education : null
             * constellation : null
             * contactStatus : null
             * status : 0
             * abnormal : null
             * visitorNum : null
             * likeNum : null
             * distance : null
             * teenagersIsOpen : null
             * channelNo : applestore
             * versionNo : 1.0.0
             * isFollow : null
             * userTables : null
             * isReal : 1
             * invitationCode : id0h90
             */

            private String id;
            private int userId;
            private String username;
            private String password;
            private String nickname;
            private Object salt;
            private Object wxOpenid;
            private Object qqOpenid;
            private String createTime;
            private String updateTime;
            private String delFlag;
            private String lockFlag;
            private String phone;
            private String avatar;
            private int sex;
            private Object introduce;
            private Object realName;
            private Object idCard;
            private int age;
            private String preference;
            private String region;
            private int gradeValue;
            private int offline;
            private Object userAccount;
            private String homepageImages;
            private String recommendImages;
            private String latitude;
            private String longitude;
            private Object personality;
            private Object shape;
            private Object height;
            private Object objective;
            private Object click;
            private Object video;
            private Object voice;
            private Object job;
            private Object annualSalary;
            private Object industry;
            private Object major;
            private Object school;
            private Object education;
            private Object constellation;
            private Object contactStatus;
            @SerializedName("status")
            private String statusX;
            private Object abnormal;
            private Object visitorNum;
            private Object likeNum;
            private String distance;
            private Object teenagersIsOpen;
            private String channelNo;
            private String versionNo;
            private Object isFollow;
            private Object userTables;
            private String isReal;
            private String invitationCode;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Object getSalt() {
                return salt;
            }

            public void setSalt(Object salt) {
                this.salt = salt;
            }

            public Object getWxOpenid() {
                return wxOpenid;
            }

            public void setWxOpenid(Object wxOpenid) {
                this.wxOpenid = wxOpenid;
            }

            public Object getQqOpenid() {
                return qqOpenid;
            }

            public void setQqOpenid(Object qqOpenid) {
                this.qqOpenid = qqOpenid;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getLockFlag() {
                return lockFlag;
            }

            public void setLockFlag(String lockFlag) {
                this.lockFlag = lockFlag;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getIntroduce() {
                return introduce;
            }

            public void setIntroduce(Object introduce) {
                this.introduce = introduce;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public Object getIdCard() {
                return idCard;
            }

            public void setIdCard(Object idCard) {
                this.idCard = idCard;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getPreference() {
                return preference;
            }

            public void setPreference(String preference) {
                this.preference = preference;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public int getGradeValue() {
                return gradeValue;
            }

            public void setGradeValue(int gradeValue) {
                this.gradeValue = gradeValue;
            }

            public int getOffline() {
                return offline;
            }

            public void setOffline(int offline) {
                this.offline = offline;
            }

            public Object getUserAccount() {
                return userAccount;
            }

            public void setUserAccount(Object userAccount) {
                this.userAccount = userAccount;
            }

            public String getHomepageImages() {
                return homepageImages;
            }

            public void setHomepageImages(String homepageImages) {
                this.homepageImages = homepageImages;
            }

            public String getRecommendImages() {
                return recommendImages;
            }

            public void setRecommendImages(String recommendImages) {
                this.recommendImages = recommendImages;
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

            public Object getPersonality() {
                return personality;
            }

            public void setPersonality(Object personality) {
                this.personality = personality;
            }

            public Object getShape() {
                return shape;
            }

            public void setShape(Object shape) {
                this.shape = shape;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }

            public Object getObjective() {
                return objective;
            }

            public void setObjective(Object objective) {
                this.objective = objective;
            }

            public Object getClick() {
                return click;
            }

            public void setClick(Object click) {
                this.click = click;
            }

            public Object getVideo() {
                return video;
            }

            public void setVideo(Object video) {
                this.video = video;
            }

            public Object getVoice() {
                return voice;
            }

            public void setVoice(Object voice) {
                this.voice = voice;
            }

            public Object getJob() {
                return job;
            }

            public void setJob(Object job) {
                this.job = job;
            }

            public Object getAnnualSalary() {
                return annualSalary;
            }

            public void setAnnualSalary(Object annualSalary) {
                this.annualSalary = annualSalary;
            }

            public Object getIndustry() {
                return industry;
            }

            public void setIndustry(Object industry) {
                this.industry = industry;
            }

            public Object getMajor() {
                return major;
            }

            public void setMajor(Object major) {
                this.major = major;
            }

            public Object getSchool() {
                return school;
            }

            public void setSchool(Object school) {
                this.school = school;
            }

            public Object getEducation() {
                return education;
            }

            public void setEducation(Object education) {
                this.education = education;
            }

            public Object getConstellation() {
                return constellation;
            }

            public void setConstellation(Object constellation) {
                this.constellation = constellation;
            }

            public Object getContactStatus() {
                return contactStatus;
            }

            public void setContactStatus(Object contactStatus) {
                this.contactStatus = contactStatus;
            }

            public String getStatusX() {
                return statusX;
            }

            public void setStatusX(String statusX) {
                this.statusX = statusX;
            }

            public Object getAbnormal() {
                return abnormal;
            }

            public void setAbnormal(Object abnormal) {
                this.abnormal = abnormal;
            }

            public Object getVisitorNum() {
                return visitorNum;
            }

            public void setVisitorNum(Object visitorNum) {
                this.visitorNum = visitorNum;
            }

            public Object getLikeNum() {
                return likeNum;
            }

            public void setLikeNum(Object likeNum) {
                this.likeNum = likeNum;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public Object getTeenagersIsOpen() {
                return teenagersIsOpen;
            }

            public void setTeenagersIsOpen(Object teenagersIsOpen) {
                this.teenagersIsOpen = teenagersIsOpen;
            }

            public String getChannelNo() {
                return channelNo;
            }

            public void setChannelNo(String channelNo) {
                this.channelNo = channelNo;
            }

            public String getVersionNo() {
                return versionNo;
            }

            public void setVersionNo(String versionNo) {
                this.versionNo = versionNo;
            }

            public Object getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(Object isFollow) {
                this.isFollow = isFollow;
            }

            public Object getUserTables() {
                return userTables;
            }

            public void setUserTables(Object userTables) {
                this.userTables = userTables;
            }

            public String getIsReal() {
                return isReal;
            }

            public void setIsReal(String isReal) {
                this.isReal = isReal;
            }

            public String getInvitationCode() {
                return invitationCode;
            }

            public void setInvitationCode(String invitationCode) {
                this.invitationCode = invitationCode;
            }
        }
    }
}
