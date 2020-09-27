package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class DynamicBean extends Result {


    /**
     * data : {"records":[{"id":"e7b683451ac8496af31ba7775f450d15","userId":114,"username":"17700000015","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"llloo","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000015","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/f8b99fa30b7d417fa87a59cf9db713c1.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":17,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/a8078ea64b254c5cb94ca5a936bd2451.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/a8078ea64b254c5cb94ca5a936bd2451.png","latitude":"29.604026878510435","longitude":"106.5307552117435","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000015"},{"id":"e7b683451ac8496af31ba7775f450d16","userId":115,"username":"17700000016","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"小兔纸","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000016","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b5fef00585f44283ad3799bd400d88a1.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":23,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/c5c5ae78254d4f69b208088a210d1e1a.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/c5c5ae78254d4f69b208088a210d1e1a.png","latitude":"29.603940271166977","longitude":"106.53078361958336","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000016"},{"id":"e7b683451ac8496af31ba7775f450d17","userId":116,"username":"17700000017","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"Miumiu 熙","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000017","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/977b1252f3b94a92acd8109b72045174.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":25,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/7445532bbd4e4fac81162de4e68a5945.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/7445532bbd4e4fac81162de4e68a5945.png","latitude":"29.604040777794744","longitude":"106.5307762686876","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000017"},{"id":"e7b683451ac8496af31ba7775f450d18","userId":117,"username":"17700000018","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"李涵","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000018","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/bbff5a4152cb4ba88bef2637e492f7a2.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":22,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/2b545403182b4dff9ea73bbc7d9b9a10.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/2b545403182b4dff9ea73bbc7d9b9a10.png","latitude":"29.60402243243532","longitude":"106.53076547684005","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000018"},{"id":"e7b683451ac8496af31ba7775f450d19","userId":118,"username":"17700000019","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"👄","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000019","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/fbbf4f6f1b2f4c6296a1e7bf38882139.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":25,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/12f2359b8fca4ae4b6597652a15532be.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/12f2359b8fca4ae4b6597652a15532be.png","latitude":"29.604262625557183","longitude":"106.53077532949574","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000019"},{"id":"e7b683451ac8496af31ba7775f450d20","userId":119,"username":"17700000020","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"Pink","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000020","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ea46b2831ace47119ce5c580bb620129.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":23,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b287cc0abc00479e886ddd08630cdf7f.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b287cc0abc00479e886ddd08630cdf7f.png","latitude":"29.60392442282145","longitude":"106.530843853316","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000020"},{"id":"e7b683451ac8496af31ba7775f450d3","userId":102,"username":"17700000003","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"白敬亭亭白","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000003","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/edd912b3c8584a649afc554ec02a0eb0.png","sex":2,"introduce":null,"realName":null,"idCard":null,"age":20,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/edd912b3c8584a649afc554ec02a0eb0.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/edd912b3c8584a649afc554ec02a0eb0.png","latitude":"29.60438074080873","longitude":"106.53110365697489","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000003"},{"id":"e7b683451ac8496af31ba7775f450d98","userId":107,"username":"17700000008","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"NiNi倪妮","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000008","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/601872f4d6fd46938a092a474b74d74e.png","sex":2,"introduce":null,"realName":null,"idCard":null,"age":19,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/601872f4d6fd46938a092a474b74d74e.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/601872f4d6fd46938a092a474b74d74e.png","latitude":"29.603841620381736","longitude":"106.5307545109966","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000008"},{"id":"e7b683451ac8496af31ba7775f450df1","userId":100,"username":"17700000001","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"修风是心动的感觉","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000001","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ca74a85e216d44408128440d49260900.png","sex":2,"introduce":"时常开心，偶尔烦恼","realName":"张明森","idCard":"50023819930304553x","age":19,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/2d26ecde28f64ee28094f8aafdba2603.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/2d26ecde28f64ee28094f8aafdba2603.png","latitude":"29.604089784193473","longitude":"106.53082161087538","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000001"},{"id":"e7b683451ac8496af31ba7775f450df2","userId":101,"username":"17700000002","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"小小果核喜欢颖~","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000002","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ad37b780cfcb48f3b6f5b11e000ead12.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":19,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ad37b780cfcb48f3b6f5b11e000ead12.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ad37b780cfcb48f3b6f5b11e000ead12.png","latitude":"29.604036075579284","longitude":"106.53080435819008","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000002"}],"total":25,"size":10,"current":2,"orders":[],"hitCount":false,"searchCount":true,"pages":3}
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
         * records : [{"id":"e7b683451ac8496af31ba7775f450d15","userId":114,"username":"17700000015","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"llloo","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000015","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/f8b99fa30b7d417fa87a59cf9db713c1.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":17,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/a8078ea64b254c5cb94ca5a936bd2451.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/a8078ea64b254c5cb94ca5a936bd2451.png","latitude":"29.604026878510435","longitude":"106.5307552117435","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000015"},{"id":"e7b683451ac8496af31ba7775f450d16","userId":115,"username":"17700000016","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"小兔纸","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000016","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b5fef00585f44283ad3799bd400d88a1.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":23,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/c5c5ae78254d4f69b208088a210d1e1a.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/c5c5ae78254d4f69b208088a210d1e1a.png","latitude":"29.603940271166977","longitude":"106.53078361958336","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000016"},{"id":"e7b683451ac8496af31ba7775f450d17","userId":116,"username":"17700000017","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"Miumiu 熙","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000017","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/977b1252f3b94a92acd8109b72045174.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":25,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/7445532bbd4e4fac81162de4e68a5945.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/7445532bbd4e4fac81162de4e68a5945.png","latitude":"29.604040777794744","longitude":"106.5307762686876","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000017"},{"id":"e7b683451ac8496af31ba7775f450d18","userId":117,"username":"17700000018","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"李涵","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000018","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/bbff5a4152cb4ba88bef2637e492f7a2.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":22,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/2b545403182b4dff9ea73bbc7d9b9a10.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/2b545403182b4dff9ea73bbc7d9b9a10.png","latitude":"29.60402243243532","longitude":"106.53076547684005","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000018"},{"id":"e7b683451ac8496af31ba7775f450d19","userId":118,"username":"17700000019","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"👄","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000019","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/fbbf4f6f1b2f4c6296a1e7bf38882139.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":25,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/12f2359b8fca4ae4b6597652a15532be.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/12f2359b8fca4ae4b6597652a15532be.png","latitude":"29.604262625557183","longitude":"106.53077532949574","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000019"},{"id":"e7b683451ac8496af31ba7775f450d20","userId":119,"username":"17700000020","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"Pink","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000020","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ea46b2831ace47119ce5c580bb620129.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":23,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b287cc0abc00479e886ddd08630cdf7f.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/b287cc0abc00479e886ddd08630cdf7f.png","latitude":"29.60392442282145","longitude":"106.530843853316","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000020"},{"id":"e7b683451ac8496af31ba7775f450d3","userId":102,"username":"17700000003","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"白敬亭亭白","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000003","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/edd912b3c8584a649afc554ec02a0eb0.png","sex":2,"introduce":null,"realName":null,"idCard":null,"age":20,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/edd912b3c8584a649afc554ec02a0eb0.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/edd912b3c8584a649afc554ec02a0eb0.png","latitude":"29.60438074080873","longitude":"106.53110365697489","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000003"},{"id":"e7b683451ac8496af31ba7775f450d98","userId":107,"username":"17700000008","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"NiNi倪妮","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000008","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/601872f4d6fd46938a092a474b74d74e.png","sex":2,"introduce":null,"realName":null,"idCard":null,"age":19,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/601872f4d6fd46938a092a474b74d74e.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/601872f4d6fd46938a092a474b74d74e.png","latitude":"29.603841620381736","longitude":"106.5307545109966","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000008"},{"id":"e7b683451ac8496af31ba7775f450df1","userId":100,"username":"17700000001","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"修风是心动的感觉","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000001","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ca74a85e216d44408128440d49260900.png","sex":2,"introduce":"时常开心，偶尔烦恼","realName":"张明森","idCard":"50023819930304553x","age":19,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/2d26ecde28f64ee28094f8aafdba2603.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/2d26ecde28f64ee28094f8aafdba2603.png","latitude":"29.604089784193473","longitude":"106.53082161087538","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000001"},{"id":"e7b683451ac8496af31ba7775f450df2","userId":101,"username":"17700000002","password":"$2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze","nickname":"小小果核喜欢颖~","salt":null,"wxOpenid":null,"qqOpenid":null,"createTime":"2020-05-22 09:48:04","updateTime":"2020-07-15 15:09:15","delFlag":"0","lockFlag":"0","phone":"17700000002","avatar":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ad37b780cfcb48f3b6f5b11e000ead12.png","sex":2,"introduce":"","realName":null,"idCard":null,"age":19,"preference":"1","region":"重庆市-重庆市","gradeValue":0,"offline":0,"userAccount":null,"homepageImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ad37b780cfcb48f3b6f5b11e000ead12.png","recommendImages":"http://zrwlmeiliao.oss-accelerate.aliyuncs.com/ad37b780cfcb48f3b6f5b11e000ead12.png","latitude":"29.604036075579284","longitude":"106.53080435819008","personality":null,"shape":null,"height":null,"objective":null,"click":null,"video":null,"voice":null,"job":null,"annualSalary":null,"industry":null,"major":null,"school":null,"education":null,"constellation":null,"contactStatus":null,"status":"0","abnormal":null,"visitorNum":null,"likeNum":null,"distance":null,"teenagersIsOpen":null,"channelNo":"applestore","versionNo":"1.0.0","isFollow":null,"userTables":null,"isReal":"1","invitationCode":"000002"}]
         * total : 25
         * size : 10
         * current : 2
         * orders : []
         * hitCount : false
         * searchCount : true
         * pages : 3
         */

        private int total;
        private int size;
        private int current;
        private boolean hitCount;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
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

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
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
             * id : e7b683451ac8496af31ba7775f450d15
             * userId : 114
             * username : 17700000015
             * password : $2a$10$IpWDnoEHMZwVXaT7V6iRVOcRYA4UwoptUgNNJP5L7o3FdjQZkNtze
             * nickname : llloo
             * salt : null
             * wxOpenid : null
             * qqOpenid : null
             * createTime : 2020-05-22 09:48:04
             * updateTime : 2020-07-15 15:09:15
             * delFlag : 0
             * lockFlag : 0
             * phone : 17700000015
             * avatar : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/f8b99fa30b7d417fa87a59cf9db713c1.png
             * sex : 2
             * introduce :
             * realName : null
             * idCard : null
             * age : 17
             * preference : 1
             * region : 重庆市-重庆市
             * gradeValue : 0
             * offline : 0
             * userAccount : null
             * homepageImages : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/a8078ea64b254c5cb94ca5a936bd2451.png
             * recommendImages : http://zrwlmeiliao.oss-accelerate.aliyuncs.com/a8078ea64b254c5cb94ca5a936bd2451.png
             * latitude : 29.604026878510435
             * longitude : 106.5307552117435
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
             * invitationCode : 000015
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
            private String introduce;
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
            private String video;
            private Object voice;
            private Object job;
            private Object annualSalary;
            private Object industry;
            private Object major;
            private Object school;
            private Object education;
            private Object constellation;
            private Object contactStatus;
            private String statusX;
            private Object abnormal;
            private Object visitorNum;
            private Object likeNum;
            private Object distance;
            private Object teenagersIsOpen;
            private String channelNo;
            private String versionNo;
            private Object isFollow;
            private Object userTables;
            private String isReal;
            private String invitationCode;
            private  String videoCover;

            public String getVideoCover() {
                return videoCover;
            }

            public void setVideoCover(String videoCover) {
                this.videoCover = videoCover;
            }

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

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
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

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
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

            public Object getDistance() {
                return distance;
            }

            public void setDistance(Object distance) {
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
