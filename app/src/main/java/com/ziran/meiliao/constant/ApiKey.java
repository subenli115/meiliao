package com.ziran.meiliao.constant;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     api接口常量
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public interface ApiKey {


    /**
     * 获取注册的验证码
     */
    String USER_GET_REG_CODE = "user/getRegCode";

    /**
     * 请求注册
     */
    String USER_REGISIT = "user/regisit";

    /**
     * 获取登录的验证码
     */
    String USER_GET_LOG_CODE = "user/getLogCode";

    /**
     * 请求登录
     */
    String USER_LOGIN = "user/login";
    /**
     * 获取重置密码的验证码
     */
    String RESET_CODE = "user/getResetCode";

    /**
     * 重置密码
     */
    String RESET_PWD = "user/resetpw";
    /**
     * /user/recordToken 上传友盟设备token
     */
    String USER_RECORD_TOKEN = "user/recordToken";

    /**
     * 返回个人资料
     */
    String USER_INFO = "userInfo/getUserInfo";

    /**
     * 更新个人资料头像
     */
    String USER_INFO_HEAD = "userInfo/updateHeadImg";


    /**
     * 更新个人资料
     */
    String UPDATE_USER_INFO = "userInfo/updateUserInfo";

    /**
     * 更新个人资料
     */
    String UPDATE_USER_INFOV2 = "userInfo/updateUserInfoV2";

    /**
     * /version/getLastestVersion 获取平台最新版本号
     */
    String VERSION = "version/getLastestVersion";

    /**
     * POST /user/authorizeCheck 第三方登录检测
     */
    String PARTY_LOGIN = "user/authorizeCheck/";

    /**
     * /album/listColumn 获取专辑分类栏目
     */
    String GET_ALBUM_COLUMN = "album/listColumn/";
    /**
     * /album/collectAlbum 收藏专辑
     */
    String COLLECT_ALBUM = "album/collectAlbum/";

    /**
     * /album/collectMusic 收藏音乐
     */
    String COLLECT_MUSIC = "album/collectMusic/";


    /**
     * /album/getAlbumData 获取专辑详情页数据
     */
    String GET_AUTHOR_DATA = "album/getAlbumData/";


    /**
     * POST /author/listByColumn 获取分类页面数据
     */
    String GET_LIST_BY_COLUMN = "album/listAlbum/";

    /**
     * POST /album/listRecommendAlbum 获取推荐专辑列表
     */
    String LIST_RECOMMEND_ALBUM = "album/listRecommendAlbum/";

    /**
     * /album/gain 获取专辑分享信息
     */
    String ALBUM_GAIN = "album/gain/";


    /**
     * /album/uploadPoster 海报上传
     */
    String ALBUM_UPLOAD_POSTER = "album/uploadPoster/";


    /**
     * /album/checkPay 检测专辑是否已经支付
     */
    String CHECK_PAY_ALBUM = "album/checkPay/";

    /**
     * album/listenUp 专辑收听+1
     */
    String listenUp = "album/listenUp/";

    /**
     * POST /message/listMessageOfficial 获取官方消息
     */
    String LIST_MESSAGE_OFFICIAL = "message/listMessageOfficial/";


    /**
     * //course/watchUp 课程观看+1
     */
    String watchUp = "course/watchUp/";
    /**
     * POST course/collectCourse    收藏课程
     */
    String COLLECT_COURSE = "course/collectCourse/";
    /**
     * POST /course/getCourseComment 获取课程评论
     *
     */
    String GET_COURSE_COMMENT = "course/getCourseComment/";

    /**
     * POST /course/getRecommend 获取推荐课程
     */
    String GET_COURSE_RECOMMEND = "course/getRecommend/";

    /**
     * POST /course/getZhiboPageData 获取直播页面数据
     */
    String GET_ZHIBO_PAGEDATA = "course/getZhiboPageData/";

    /**
     * /course/listHistory 获取历史课程列表
     */
    String GET_COURSE_HISTORY = "course/listHistory/";
    /**
     * /course/listZhibo 返回精彩直播的列表
     */
    String GET_COURSE_ZHIBO_LIST = "course/listZhibo/";
    /**
     * /course/listTrailer 返回精彩预告的列表
     */
    String GET_COURSE_TRAILER_LIST = "course/listTrailer/";

    /**
     * getCourseDetailV2
     */
    String GET_COURSE_DETAIL = "course/getCourseDetailV2/";


    /**
     * /course/comment 评论课程
     */
    String POST_COURSE_COMMENT = "course/comment/";


    /**
     * /course/like 喜欢课程
     */
    String LIKE_COURSE = "course/like/";


    /**
     * /beeCloud/getAppSecret 获取BeeCloud-AppSecret
     */
    String GET_BEECLOUD_APPSECRET = "beeCloud/getAppSecret/";


    /**
     * /course/checkPay 检查课程是否已支付
     */
    String COURSE_CHECKPAY = "course/checkPay/";


    /**
     * /activity/listActivity 获取活动页面列表
     */
    String GET_LIST_ACTIVITY = "activity/listActivity/";

    /**
     * /activity/collectActivity 活动收藏
     */
    String COLLECT_ACT = "activity/collectActivity/";


    /**
     * /userHome/listCollectAlbum 收藏的专辑列表
     */
    String COLLECT_ALBUM_LIST = "userHome/listCollectAlbum/";

    /**
     * /userHome/listCollectCourse 收藏的课程列表
     */
    String COLLECT_COURSE_LIST = "userHome/listCollectCourse/";

    /**
     * /userHome/listCollectMusic 收藏的音乐列表
     */
    String COLLECT_MUSIC_LIST = "userHome/listCollectMusic/";

    /**
     * /userHome/listUserActivity 报名的活动
     */
    String JOIN_ACTIVITY_LIST = "userHome/listUserActivity/";

    /**
     * /userHome/listUserBuyCourse 返回用户购买的课程列表
     */
    String BUY_COURSE_LIST = "/subscription/subscriptionBuyList/";
    /**
     * /userHome/listUserBuyCourse 返回用户购买的课程列表
     */
    String BUY_ALBUM_LIST = "userHome/listUserBuyAlbum/";


    /**
     * /userHome/listActivity 收藏的活动列表
     */
    String USER_HOME_LIST_ACTIVITY = "userHome/listActivity/";


    /**
     * /userHome/trailer 直播预告
     */
    String USER_HOME_TRAILER = "userHome/trailer/";


    /**
     * 已过时
     * GET /userVip/yearMember 获取VIP会员信息
     */
    String VIP_YEAR_MEMBER = "userVip/yearMember/";
    /**
     * /userVip/yearMember_1 一年会员费用
     */
    String VIP_YEAR_MEMBER_1 = "userVip/yearMember_1/";

    /**
     * POST /userVip/checkLevel 检测会员等级
     */
    String VIP_CHECK_LEVEL = "userVip/checkLevel/";


    /**
     * /userCoupon/listByParams 返回优惠券列表
     */
    String USER_COUPON_LIST = "userCoupon/listByParams/";


    /**
     * /userCoupon/gain 我要领优惠券
     */
    String GAIN_COUPON = "userCoupon/gain/";

    /**
     * /userCoupon/uploadPoster 优惠券海报上传
     */
    String UPLOAD_POSTER = "userCoupon/uploadPoster/";




    /**
     * /userAdvice/advice 意见反馈
     */
    String USER_ADVICE = "userAdvice/advice/";

    /**
     * /album/refresh 刷新音频推送
     */
    String ALBUM_REFRESH = "album/refresh/";

    /**
     * /userHome/rec 推广大使和推荐好友
     */
    String USER_HOME_RES = "userHome/rec/";

    /**
     * //user/checkToken 检查token
     */
    String USER_CHECK_TOKEN = "user/checkToken/";

    /**
     * GET /teacher/index 获取开播首页数据
     */
    String TEATHER_INDEX = "teacher/index/";

    /**
     * /teacher/goin 开启直播
     */
    String TEATHER_GOIN = "teacher/goin/";

    /**
     * /teacher/checkTeacher 检测用户是否有开播的权限
     */
    String TEATHER_CHECK_ROOT = "teacher/checkTeacher/";

    /**
     * /teacher/end 课程结束
     */
    String TEATHER_END = "teacher/end/";

    /**
     * /course/shikan 课程试看
     */
    String COURSE_SHIKAN = "course/shikan/";
    /**
     * /course/getChrmUserCount 获取房间人数
     */
    String COURSE_GETCHRM_SUERCOUNT = "course/getChrmUserCount/";
    /**
     * /search/getHotTag 获取热门标签
     */
    String SEARCH_GET_HOT_TAG = "search/getHotTag/";
    /**
     * /search/delHis 根据内容搜索
     */
    String SEARCH_GET_DEL_HIS = "search/delHis/";
    /**
     * /search/selectByParams 根据内容搜索
     */
    String SEARCH_SELECT_BY_PARAMS = "search/selectByParams/";

    /**
     * /search/selectByParamsNew 根据内容搜索2.0以上版本
     */
    String SEARCH_SELECT_BY_PARAMS_NEW = "search/selectByParamsNew/";

    /**
     * /purse/listCoin 获取充值金币列表
     */
    String PURSE_LIST_COIN = "purse/listCoin/";

    /**
     * /purse/getBalance 获取钱包余额
     */
    String PURSE_GET_BALANCE = "purse/getBalance/";

    /**
     * /purse/getUserOutcome 获取支出明细
     */
    String PURSE_GET_USER_OUTCOME = "purse/getUserOutcome/";
    /**
     * /purse/getUserIncome 获取收入明细
     */
    String PURSE_GET_USER_INCOME = "purse/getUserIncome/";

    /**
     * /album/buy 购买音频（虚拟币）
     */
    String PURSE_BUY = "album/buy/";

    /**
     * /course/webTrailerNative 预告页面
     */
    String TRAILER_NATIVE = "course/webTrailerNative/";

    /**
     * /course/buy 购买课程（虚拟币）
     */
    String COURSE_BUY = "course/buy/";

    /**
     * /courseTicket/useTick 使用观影票
     */
    String COURSE_TICKET_USETICK = "courseTicket/useTick/";

    /**
     * /courseTicket/getTicket 免费获券
     */
    String COURSE_TICKET_GET_TICKET = "courseTicket/getTicket/";

    /**
     * /courseTicket/createInvi 分享抢券页面
     */
    String COURSE_TICKET_CREATEINVI = "courseTicket/createInvi/";


    /**
     * /course/giveGift 赠送礼物
     */
    String COURSE_GIVE_GIFT = "course/giveGift/";

    /**
     * /subscription/getCertificate 获取毕业证所需的信息
     */
    String SUBSCRIPTION_GET_CERTIFICATE = "subscription/getCertificate/";

    /**
     * /subscription/getSingleCourseShare
     */
    String
            SUBSCRIPTION_GET_SINGLE_COURSE_SHARE = "subscription/getSingleCourseShare/";

    /**
     * /course/listGift 礼物列表
     */
    String COURSE_LIST_GIFT = "course/listGift/";

    /**
     * /album/gainSpread 获取推广音频信息
     */
    String ALBUM_GAIN_SPREAD = "album/gainSpread/";

    /**
     * /album/uploadSpreadPoster 推广海报上传
     */
    String ALBUM_UPLOAD_SPREAD_POSTER = "album/uploadSpreadPoster/";

    /**
     * /album/giveAlbum 赠送推广音频
     */
    String ALBUM_GIVE_ALBUM = "album/giveAlbum/";

    /**
     * /course/updateStudy 用户学习进度上传
     */
    String COURSE_UPDATE_STUDY = "course/updateStudy/";

    /**
     * /teacher/getData 直播间后台首页数据
     */
    String TEACHER_GET_DATA = "teacher/getData/";

    /**
     * live/liveTeacherInfo 直播间后台首页数据
     */
    String LIVE_USER_LIVE_HOME = "live/userLiveHome/";

    /**
     *  /live/bankCardAdd 添加银行卡
     */
    String LIVE_BANK_CARD_ADD = "live/bankCardAdd/";

    /**
     *  /live/bankCardCheck 银行卡校验
     */
    String LIVE_BANK_CARD_CHECK = "live/bankCardCheck/";

    /**
     *  /live/bankCardDelete 添加银行卡
     */
    String LIVE_BANK_CARD_DELETE = "live/bankCardDelete/";

    /**
     *   /live/bankCardList 银行卡列表
     */
    String LIVE_BANK_CARD_LIST = "live/bankCardList/";

    /**
     *   /live/getMoneySupply 提现申请
     */
    String LIVE_GET_MONEY_SUPPLY = "live/getMoneySupply/";

    /**
     *   /live/getMoneySupplyDetail 提现明细详情
     */
    String LIVE_GET_MONEY_SUPPLY_DETAIL = "live/getMoneySupplyDetail/";

    /**
     *  /live/getMoneySupplyIntro 提现明细列表
     */
    String LIVE_GET_MONEY_SUPPLY_INTRO = "live/getMoneySupplyIntro/";

    /**
     *   /live/liveIncome 直播间后台收益
     */
    String LIVE_LIVE_INCOME = "live/liveIncome/";

    /**
     *  /live/liveIncomeDetail 直播间后台收益明细
     */
    String LIVE_LIVE_INCOME_DETAIL = "live/liveIncomeDetail/";

    /**
     *  /live/liveTeacherInfo 直播间后台老师简介
     */
    String LIVE_LIVE_TEACHER_INFO = "live/liveTeacherInfo/";


    /**
     *  /live/updateTeacherImg 直播间后台老师更改头像
     */
    String LIVE_UPDATE_TEACHER_IMG = "live/updateTeacherImg/";


    /**
     *  /live/updateTeacherInfo 直播间后台老师简介修改
     */
    String LIVE_UPDATE_TEACHER_INFO = "live/updateTeacherInfo/";


    /**
     * /user/verifyIdentity 实名认证
     */
    String USER_VERIFY_IDENTITY = "user/verifyIdentity";

    /**
     * /audio/updateStudy 用户学习进度上传
     */
    String AUDIO_UPDATE_STUDY = "audio/updateStudy";

    /**
     * /teacher/incomeListCourse 已开课课程列表
     */
    String TEACHER_INCOME_LIST_COURSE = "teacher/incomeListCourse";
    /**
     * /teacher/listFans 已开课课程列表
     */
    String TEACHER_LIST_FANS = "teacher/listFans";
    /**
     * /teacher/listFans 已开课课程列表
     */
    String LIVE_FANS_LIST = "live/fansList";

    /**
     * /teacher/listTrailer 预开课列表
     */
    String TEACHER_LIST_TRAILER = "teacher/listTrailer";

    /**
     * /bankCard/checkCard 检查卡号是属于哪个银行的：
     */
    String BANK_CARD_CHECK_CARD = "bankCard/checkCard";

    /**
     * /bankCard/getCode 绑定银行卡时给手机发送验证码：
     */
    String BANK_CARD_GET_CODE = "bankCard/getCode";


    /**
     * /bankCard/bindCard 绑定银行卡时给手机发送验证码：
     */
    String BANK_CARD_BIND_CARD = "bankCard/bindCard";

    /**
     * /bankCard/bindCard 解绑银行卡：
     */
    String BANK_CARD_UN_BIND_CARD = "bankCard/unBindCard";
    /**
     * /bankCard/listBindCard 获取绑定的卡列表：
     */
    String BANK_CARD_LIST_BIND_CARD = "bankCard/listBindCard";

    /**
     * /bankCard/drawCash 提现申请：
     */
    String BANK_CARD_DRAW_CASH = "bankCard/drawCash";

    /**
     * /bankCard/listDraw 提现明细：
     */
    String BANK_CARD_LIST_DRAW = "bankCard/listDraw";

    /**
     * /practice/upload 练习完成后上传并返回数据
     */
    String PRACTICE_UPLOAD1 = "practice/upload";

    /**
     * /practice/getDailyMind 获取每日正念时间
     */
    String PRACTICE_GET_DAILY_MIND = "practice/getDailyMind";

    /**
     * /practice/getNoteQues 返回笔记问题
     */
    String PRACTICE_GET_NOTE_QUES = "practice/getNoteQues";

    /**
     * /practice/getShareData 获取分享的H5数据
     */
    String PRACTICE_GET_SHARE_DATA = "practice/getShareData";


    /**
     * /practice/getTotal 获取用户总的冥想时间、冥想课程、连续天数
     */
    String PRACTICE_GET_TOTAL = "practice/getTotal";

    /**
     * /practice/getTotalByDate 根据日期获取用户的总冥想时间
     */
    String PRACTICE_GET_TOTAL_BY_DATE = "practice/getTotalByDate";

    /**
     * /practice/getUserNote 根据日期获取用户的笔记
     */
    String PRACTICE_GET_USER_NOTE = "practice/getUserNote";
    /**
     * /practice/getTagByMonth 根据月份返回每日是否正念
     */
    String PRACTICE_GET_TAG_BY_MONTH = "practice/getTagByMonth";

    /**
     * /practice/listPra 获取练习的记录列表
     */
    String PRACTICE_LIST_PRA = "practice/listPra";

    /**
     * /practice/updateDailyMind 设置每日正念时间
     */
    String PRACTICE_UPDATE_DAILY_MINDV2 = "practice/updateDailyMindV2";


    /**
     * /practice/upload 练习完成后上传并返回数据
     */
    String PRACTICE_UPLOAD = "practice/upload/";
    /**
     * /practice/uploadPoster 海报上传
     */
    String PRACTICE_UPLOADPOSTER = "practice/uploadPoster/";
    /**
     * /practice/writeNote 做笔记
     */
    String PRACTICE_WRITE_NOTE = "practice/writeNote/";
    /**
     * /practice/writeNote 做笔记
     */
    String PRACTICE_UPLOAD_NOTE_IMGS = "practice/uploadNoteImgs/";

    /**
     * /home/getData
     */
    String HOME_GET_DATA = "home/getData/";

    /**
     * /sjk/getActData 获取工作坊数据
     */
    String SJK_GET_ACT_DATA = "sjk/getActData/";

    /**
     * /sjk/getRecData 获取推荐页数据
     */
    String SJK_GET_REC_DATA = "sjk/getRecData/";

    /**
     * /sjk/getSpecData 获取专栏页数据 / 换一换
     */
    String SJK_GET_SPEC_DATA = "sjk/getSpecData/";

    /**
     * 导铃推荐页面接口：/album/getRecData
     */
    String ALBUM_GET_REC_DATA = "album/getRecData/";

    /**
     * /practice/delNote 删除笔记
     */
    String PRACTICE_DEL_NOTE = "practice/delNote/";

    /**
     * /practice/updateNote 修改笔记
     */
    String PRACTICE_UPDATE_NOTE = "practice/updateNote/";

    /**
     * /practice/getUserNoteById 根据日期获取用户的笔记
     */
    String PRACTICE_GET_USER_NOTE_BY_ID = "practice/getUserNoteById/";

    /**
     * /subscription/buy 购买专栏
     */
    String SPEC_COLUMN_BUY = "subscription/buy/";

    /**
     * /subscription/listRecord 专栏列表
     */
    String SPEC_COLUMN_LIST_RECORD = "subscription/listRecord/";

    /**
     * /subscription/mediaDetail 图文详情
     */
    String SUBSCRIPTION_MEDIA_DETAIL = "subscription/mediaDetail/";
    /**
     *  /subscription/mediaAndText 看图文，听音频
     */
    String SUBSCRIPTION_MEDIA_AND_TEXT = "subscription/mediaAndText/";

    /**
     * /subscription/gz 关注专栏
     */
    String SPEC_COLUMN_GZ = "subscription/gz/";

    /**
     * /subscription/nativeIndex 专栏原生首页
     */
    String SPEC_COLUMN_NATIVE_INDEX = "subscription/nativeIndex/";


    /**
     * /subscription/bigBrand 大牌进驻内容
     */
    String SUBSCRIPTION_BIG_BRAND = "subscription/bigBrand/";

    /**
     * /subscription/seeUp 增加查阅次数
     */
    String SPEC_COLUMN_SEE_UP = "subscription/seeUp/";


    /**
     * /subscription/webIndex/{authorId} 专栏Web首页
     */
    String SPEC_COLUMN_WEB_INDEX = "subscription/webIndex/";
    /**
     * /subscription/getCoursePPt 获取课程ppt 专栏Web首页
     */
    String SUBSCRIPTION_GET_COURSE_PPT = "subscription/getCoursePPt/";


    /**
     * /audio/collect 收藏音频课程
     */
    String AUDIO_COLLECT = "audio/collect/";
    /**
     * /audio/comment 评论音频课程
     */
    String AUDIO_COMMENT = "audio/comment/";
    /**
     * /audio/deleteComment 删除评论
     */
    String AUDIO_DELETE_COMMENT = "audio/deleteComment/";
    /**
     * /audio/getComment 获取音频课程评论
     */
    String AUDIO_GET_COMMENT = "audio/getComment/";
    /**
     * /audio/getData 音频课程首页数据
     */
    String AUDIO_GET_DATA = "audio/getData/";

    /**
     * /audio/like 点赞音频课程
     */
    String AUDIO_LIKE = "audio/like/";

    /**
     * /audio/listenUp 增加收听次数
     */
    String AUDIO_LISTEN_UP = "audio/listenUp/";

    /**
     * /audio/praiseComment 点赞音频课程评论
     */
    String AUDIO_PRAISE_COMMENT = "audio/praiseComment/";

    /**
     * /course/praiseComment 点赞视频课程评论
     */
    String COURSE_PRAISE_COMMENT = "course/praiseComment/";

    /**
     * /course/deleteComment 删除视频课程评论
     */
    String COURSE_DELETE_COMMENT = "course/deleteComment/";

    /**
     * /course/getData点赞音频课程评论
     */
    String COURSE_GET_DATA = "course/getData/";
    /**
     * /message/getNoReadCount 获取未读消息数
     */
    String MESSAGE_GET_NO_READ_COUNT = "message/getNoReadCount/";
    /**
     * /message/readAll 已读所有消息
     */
    String MESSAGE_READ_ALL = "message/readAll/";

    /**
     * /practice/getChartData 根据日期获取用户的总冥想时间
     */
    String PRACTICE_GET_CHART_DATA = "practice/getChartData/";

    /**
     * /practice/downloadVedio 下载练习视频
     */
    String PRACTICE_DOWNLOAD_VEDIO = "practice/downloadVedio/";


    /**
     * conference/getConference
     */
    String CONFERENCE_GET_CONFERENCE = "conference/getConference/";


    /**
     * /famousTeachers/famousTeachersList 名师教场列表
     */
    String FAMOUS_TEACHERS_FAMOUS_TEACHERS_LIST = "famousTeachers/famousTeachersList/";


    /**
     * /missionBuilt/buyMissionBuilt 购买（定制）团建课程库详情
     */
    String MISSION_BUILT_BUY_MISSION_BUILT = "missionBuilt/buyMissionBuilt/";


    /**
     * /missionBuilt/createMissionBuilt 团建定制页面信息填写
     */
    String MISSION_BUILT_CREATE_MISSION_BUILT = "missionBuilt/createMissionBuilt/";


    /**
     * /missionBuilt/listMissionBuilt 团建课程库列表
     */
    String MISSION_BUILT_LIST_MISSION_BUILT = "missionBuilt/listMissionBuilt/";


    /**
     * /missionBuilt/listMissionBuiltInfo 获取团建库课程库分类信息
     */
    String MISSION_BUILT_LIST_MISSION_BUILT_INFO = "missionBuilt/listMissionBuiltInfo/";


    /**
     * /missionBuilt/missionBuiltDetail 团建课程库详情
     */
    String MISSION_BUILT_MISSION_BUILT_DETAIL = "missionBuilt/missionBuiltDetail/";


    /**
     * /qCourseLibrary/searchQCourseLibrary 课题库搜索
     */
    String Q_COURSE_LIBRARY_SEARCH_QCOURSE_LIBRARY = "qCourseLibrary/searchQCourseLibrary/";


    /**
     * /qjg/getRecData 获取趣静观推荐页数据
     */
    String QJG_GET_REC_DATA = "qjg/getRecData/";


    /**
     * /crownFund/crowdFundDetail 获取众筹详情
     */
    String CROWN_FUND_CROWD_FUND_DETAIL = "crownFund/crowdFundDetail/";

    /**
     * /crownFund/addCrowdFundUser 填写众筹用户信息
     */
    String CROWN_FUND_ADD_CROWD_FUND_USER = "crownFund/addCrowdFundUser/";


    /**
     * /crownFund/buyCrowdFund 众筹购买
     */
    String CROWN_FUND_BUY_CROWD_FUND = "crownFund/buyCrowdFund/";


    /**
     * /crownFund/checkCrowdFundUser 检查是否已经被审核通过众筹个人信息
     */
    String CROWN_FUND_CHECK_CROWD_FUND_USER = "crownFund/checkCrowdFundUser/";


    /**
     * /crownFund/createCrowdFund 发起众筹
     */
    String CROWN_FUND_CREATE_CROWD_FUND = "crownFund/createCrowdFund/";


    /**
     * /crownFund/getCrowdFundModule 获取众筹模板
     */
    String CROWN_FUND_GET_CROWD_FUND_MODULE = "crownFund/getCrowdFundModule/";


    /**
     * /crownFund/listCFQcourseLibrary 获取众筹课程库
     */
    String CROWN_FUND_LIST_CFQCOURSE_LIBRARY = "crownFund/listCFQcourseLibrary/";


    /**
     * /crownFund/listCrowdFund 获取众筹页面列表
     */
    String CROWN_FUND_LIST_CROWD_FUND = "crownFund/listCrowdFund/";

    /**
     * /crownFund/searchCFQcourseTeachers 条件搜索老师众筹库
     */
    String CROWN_FUND_SEARCH_CFQCOURSE_TEACHERS = "crownFund/searchCFQcourseTeachers/";

    /**
     * /crownFund/searchCFQcourseLibrary 条件搜索众筹库
     */
    String CROWN_FUND_SEARCH_CFQCOURSE_LIBRARY = "crownFund/searchCFQcourseLibrary/";


    /**
     * /famousTeachers/collectFamousTeacher 名师教场课程收藏
     */
    String FAMOUS_TEACHERS_COLLECT_FAMOUS_TEACHER = "famousTeachers/collectFamousTeacher/";


    /**
     * /crownFund/listCFQcourseTeachers 获取众筹老师列表
     */
    String CROWN_FUND_LIST_CFQCOURSE_TEACHERS = "crownFund/listCFQcourseTeachers/";


    /**
     * /missionBuilt/missionBuiltOrderList 团建课订单/列表
     */
    String MISSION_BUILT_MISSION_BUILT_ORDER_LIST = "missionBuilt/missionBuiltOrderList/";

    /**
     * /missionBuilt/collectMissionBuilt 收藏团建课程
     */
    String MISSION_BUILT_COLLECT_MISSION_BUILT = "missionBuilt/collectMissionBuilt/";

    /**
     * /missionBuilt/collectMissionList 收藏众筹课程列表
     */
    String MISSION_BUILT_COLLECT_MISSION_LIST = "missionBuilt/collectMissionList/";

    /**
     * /missionBuilt/missionBuiltOrderListIntro 团建课订单简要信息
     */
    String MISSION_BUILT_MISSION_BUILT_ORDER_LIST_INTRO = "missionBuilt/missionBuiltOrderListIntro/";


    /**
     * /missionBuilt/missionBuiltOrderDetail 团建课订单详情或者跳转到课程详情
     */
    String MISSION_BUILT_MISSION_BUILT_ORDER_DETAIL = "missionBuilt/missionBuiltOrderDetail/";


    /**
     * /crownFund/collectCrownFund 收藏众筹课程
     */
    String CROWN_FUND_COLLECT_CROWN_FUND = "crownFund/collectCrownFund/";

    /**
     * /crownFund/crowdFundOrderList 众筹(发起/购买)订单/列表
     */
    String CROWN_FUND_CROWD_FUND_ORDER_LIST = "crownFund/crowdFundOrderList/";

    /**
     * /crownFund/crownFundOrderDetail 众筹订单明细
     */
    String CROWN_FUND_CROWN_FUND_ORDER_DETAIL = "crownFund/crownFundOrderDetail/";

    /**
     * /crownFund/crownFundOrderListIntro 众筹订单简要信息
     */
    String CROWN_FUND_CROWN_FUND_ORDER_LIST_INTRO = "crownFund/crownFundOrderListIntro/";

    /**
     * /crownFund/refundDetail 众筹订单退款明细
     */
    String CROWN_FUND_REFUND_DETAIL = "crownFund/refundDetail/";

    /**
     * /crownFund/refundSupply 提交众筹订单退款申请
     */
    String CROWN_FUND_REFUND_SUPPLY = "crownFund/refundSupply/";

    /**
     * /crownFund/refundSupplyInfo 众筹订单申请退款数据
     */
    String CROWN_FUND_REFUND_SUPPLY_INFO = "crownFund/refundSupplyInfo/";


    /**
     * /crownFund/refundSupplyProgress 众筹订单退款申请进度数据
     */
    String CROWN_FUND_REFUND_SUPPLY_PROGRESS = "crownFund/refundSupplyProgress/";

    /**
     * /uploadImgs/uploadImgs 图片（单张/多张）上传通用
     */
    String UPLOAD_IMGS_UPLOAD_IMGS = "uploadImgs/uploadImgs/";

    /**
     * /famousTeachers/famousTeachersDetail 名师教场详情
     */
    String FAMOUS_TEACHERS_FAMOUS_TEACHERS_DETAIL = "famousTeachers/famousTeachersDetail/";


    /**
     * /sms/getSMSCode 获取手机登录验证码
     */
    String SMS_GET_SMSCODE = "sms/getSMSCode/";


    /**
     * /sms/checkSMSCode 获取手机登录验证码
     */
    String SMS_CHECK_SMSCODE = "sms/checkSMSCode/";

    /**
     * /advert/getAdvert 下载启动页广告界面
     */
    String ADVERT_GET_ADVERT = "advert/getAdvert/";

    /**
     * /subscription/subscriptionShareSave 专栏课程分享记录保存
     */
    String SUBSCRIPTION_SUBSCRIPTION_SHARE_SAVE = "subscription/subscriptionShareSave/";

    /**
     * /crownFund/crowdFundShareSave 众筹课程分享记录保存
     */
    String CROWN_FUND_CROWD_FUND_SHARE_SAVE = "crownFund/crowdFundShareSave/";

    /**
     * /missionBuilt/missionBuiltShareSave 团建课程分享记录保存
     */
    String MISSION_BUILT_MISSION_BUILT_SHARE_SAVE = "missionBuilt/missionBuiltShareSave/";

    /**
     * /famousTeachers/famousTeachersShareSave 名师课程分享记录保存
     */
    String FAMOUS_TEACHERS_FAMOUS_TEACHERS_SHARE_SAVE = "famousTeachers/famousTeachersShareSave/";

    /**
     * /coupon/couponSave 优惠券保存
     */
    String COUPON_COUPON_SAVE = "coupon/couponSave/";

    /**
     * /subscription/collectSubscriptionList 收藏专栏课程列表
     */
    String SUBSCRIPTION_COLLECT_SUBSCRIPTION_LIST = "subscription/collectSubscriptionList/";

    /**
     * /subscription/collectSubscription 收藏专栏课程
     */
    String SUBSCRIPTION_COLLECT_SUBSCRIPTION = "subscription/collectSubscription/";


    /**
     * /practice/getByDay 获取正念币
     */
    String PRACTICE_GET_BY_DAY = "practice/getByDay/";

    /**
     * /crownFund/collectCrownFundList 收藏众筹课程列表
     */
    String CROWN_FUND_COLLECT_CROWN_FUND_LIST = "crownFund/collectCrownFundList/";

    /**
     * /famousTeachers/collectFamousTeacherList 名师收藏课程列表
     */
    String FAMOUS_TEACHERS_COLLECT_FAMOUS_TEACHER_LIST = "famousTeachers/collectFamousTeacherList/";

    /**
     *  /userHome/recUrl 推广app，发送好友
     */
    String USER_HOME_REC_URL = "userHome/recUrl/";
    /**
     *  /level/level 会员等级
     */
    String USER_MEMBER_LEVEL = "level/level/";
    /**
     *  /level/levelDetail 会员详情
     */
//    String USER_MEMBER_Detail =  "level/levelDetail/";

    /**
     *  /user/checkLoginPhone 检测是否登录
     */
    String USER_CHECKLOGINPHONE = "admin/mobile/{mobile}";


    /**
     *  /practiceActivity/homeData 新首页数据
     */
    String PRACTIEACTIVITY_HOMEDATA = "/practiceActivity/homeData" ;


    /**
     *
     *  /practiceActivity/INFO mbsr练习册详情
     */
    String PRACTIEACTIVITY_INFO = "/practiceActivity/info" ;

    /**
     *  /practiceActivity/join mbsr练习加入/退出
     */
    String PRACTIEACTIVITY_JOIN = "/practiceActivity/join" ;
    /**
     *  /practiceActivity/head mbsr练习头
     */
    String PRACTIEACTIVITY_HEAD = "/practiceActivity/head" ;

    /**
     *  /practiceActivity/start 开始第一周练习
     */
    String PRACTIEACTIVITY_START = "/practiceActivity/start" ;


    /**
     *  /practiceActivity/calendar 练习日历
     */
    String PRACTIEACTIVITY_CALENDAR = "/practiceActivity/calendar" ;


    /**
     *  /practiceActivity/detailOne 第一类练习数据
     */
    String PRACTIEACTIVITY_DetailONE = "/practiceActivity/detailOne" ;

    /**
     *  /practiceActivity/detailTwo 第二类练习数据
     */
    String PRACTIEACTIVITY_DetailTWO = "/practiceActivity/detailTwo" ;



    /**
     *  /practiceActivity/detailOneSave 第三类练习数据保存
     */
    String PRACTIEACTIVITY_DetailThreeSave = "/practiceActivity/detailThreeSave" ;

    /**
     *  /practiceActivity/detailTreeCheck 第三类练习数据检查
     */
    String PRACTIEACTIVITY_DetailThreeCheck = "/practiceActivity/detailThreeCheck" ;


    /**
     *  /practiceActivity/detailFourSave 第四类练习数据
     */
    String PRACTIEACTIVITY_DetailFOURSAVE = "/practiceActivity/detailFourSave" ;
    /**
     *  /practiceActivity/detailFourCheck 第四类练习数据
     */
    String PRACTIEACTIVITY_DetailFOURCheck = "/practiceActivity/detailFourCheck" ;

    /**
     *  /practiceActivity/detailFiveList 第五类练习数据，觉察
     */
    String PRACTIEACTIVITY_DETATILFIVELIST = "/practiceActivity/detailFiveList" ;



    /**
     *  /practiceActivity/detailFiveSave 第五类练习数据保存
     */
    String PRACTIEACTIVITY_FIVESTATUSSAVE = "/practiceActivity/detailFiveSave" ;


    /**
     *  /practiceActivity/statusUpdate 更新练习状态
     */
    String PRACTIEACTIVITY_STATUSUPDATE = "/practiceActivity/statusUpdate" ;

    /**
     *  /practiceActivity/statusCalendar 练习记录日历
     */
    String PRACTIEACTIVITY_STATUSCALENDAR = "/practiceActivity/statusCalendar" ;

    /**
     *  /practiceActivity/practiceHis 练习记录
     */
    String PRACTIEACTIVITY_PRACTICEHIS = "/practiceActivity/practiceHis" ;
    /**
     *  /practiceActivity/uploadPracticeBooks 练习册图片上传
     */
    String PRACTIEACTIVITY_UPLOADPRACTICEBOOKS= "/practiceActivity/uploadPracticeBooks" ;


    /**
     *  /subscription/nativeIndexV3 专栏原生首页,3.0
     */
    String SUBSCRIPTION_NATIVEINDEXV3= "/subscription/nativeIndexV3" ;

    /**
     *  /userInfo/tagList 获取标签列表
     */
    String UserINFO_TAGLIST= "/userInfo/tagList" ;


    /**
     *  /userInfo/tagSave 保存标签列表
     */
    String UserINFO_TAGSAVE= "/userInfo/tagSave" ;

    /**
     *  /practice/getChartDataV2 下拉统计图
     */
    String PRACTICE_GETCHARTDATAV2= "/practice/getChartDataV2" ;


    /**
     *  /home/getaDataV2 获取首页数据v2
     */
    String HOME_GDATAV2= "/home/getDataV2" ;


    /**
     *  /album/getBgMusic 获取专辑
     */
    String ALBUM_GETBGMUSIC= "/album/getBgMusic" ;


    /**
     *  /album/getAlbumShare 音频每日分享后链接
     */
    String ALBUM_GETALBUMSHARE= "/album/getAlbumShare" ;

    /**
     *  /album/getAlbumPrac 每日打卡
     */
    String ALBUM_GETALBUMPRAC= "/album/getAlbumPrac" ;


    /**
     *  /album/getAlbumList 更多音频
     */
    String ALBUM_GETALBUMLIST= "/album/getAlbumList" ;

    /**
     *  /subscription/getSubscriptionList 获取专栏标签列表
     */
    String SUBSCRIPTION_GETSUBSCRIPTIONLIST= "/subscription/getSubscriptionList" ;


    /**
     *  /practiceActivity/getList 获取课程列表
     */
    String PRACTICEACTIVITY_GETLIST= "/practiceActivity/getList" ;


    /**
     *  /activity/getActivityList 获取课程列表
     */
    String ACTIVITY_GETACTIVITYLIST= "/activity/getActivityList" ;

    /**
     *  /course/getZhiboPageData 获取直播界面列表
     */
    String COURSE_GETZHIBOPAGEDATA= "/course/getZhiboPageData" ;

    /**
     *  /subscription/subscriptionList 获取专栏标签列表内容
     */
    String SUBSCRIPTION_SUBSCRIPTIONLIST= "/subscription/subscriptionList" ;
    /**
     *  /practice/getPunchHis 获取打卡记录
     */
    String PRACTICE_GETPUNCHHIS= "/practice/getPunchHis" ;

    /**
     *  /album/getMoreAlbum 获取更多音频
     */
    String PRACTICE_GETMOREALBUM= "/album/getMoreAlbum";

    /**
     *  /album/getAlbumCatalog 標簽分類
     */
    String ALBUM_GETALBUMCATALOG= "/album/getAlbumCatalog";

    /**
     *  /userInfo/tagSaveCheck 第一次采集
     */
    String USERINFO_TAGSAVECHECK= "/userInfo/tagSaveCheck";

    /**
     *  /album/saveRecAlbum 保存状态
     */
    String ALBUM_SAVERECALBUM= "/album/saveRecAlbum";

    /**
     *  /activity/getShareData 课程分享
     */
    String ACTIVITY_GETSHAREDATA= "/activity/getShareData";

    /**
     *  /album/getAlbumShareData 新版V2--每首音频播放结束，获取分享数据
     */
    String ALBUM_GETALBUMSHAREDATA= "/album/getAlbumShareData";


    /**
     *  /userCenter/centerIntro 新版V2--会员中心预览
     */
    String USERCENTER_CENTERINTRO= "/userCenter/centerIntro";

    /**
     *  /userCenter/goodsList 新版V2——积分兑换商品列表
     */
    String USERCENTER_GOODSLIST= "/userCenter/goodsList";

    /**
     *  /userCenter/buyByScore 新版V2——积分兑换商品
     */
    String USERCENTER_BUYBYSCORE= "/userCenter/buyByScore";


    /**
     *  /userCenter/scoreDetail 新版V2——积分明细
     */
    String USERCENTER_SCOREDETAIL= "/userCenter/scoreDetail";


    /**
     *  /userCenter/shareUrl  新版V2——返回邀请好友(获取积分)
     */
    String USERCENTER_SHAREURL= "/userCenter/shareUrl";


    /**
     *  /userCenter/shareFrdsList  新版V2——邀请好友列表
     */
    String USERCENTER_SHAREFRDSLIST= "/userCenter/shareFrdsList";


    /**
     *  /record/getRecSummary  新版V2——录播
     */
    String RECORD_GETRECSUMMARY= "/record/getRecSummary";


    /**
     *  /record/recShareSuccess  新版V2——录播课程分享成功
     *
     */
    String RECORD_RECSHARESUCCESS= "/record/recShareSuccess";


    /**
     *  /record/getRecList  新版V2——获取播放目录
     */
    String RECORD_GETRECLIST= "/record/getRecList";

    /**
     *  /activity/getActivityDetails  课程详情
     */
    String ACTIVITY_GETACTIVITYDETAILS= "/activity/getActivityDetails";

    /**
     *  /activity/toJoin  获取报名信息及用户可用积分
     */
    String ACTIVITY_TOJOIN= "/activity/toJoin";

    /**
     *  /activity/appJoin  APP 课程报名
     */
    String ACTIVITY_APPJOIN= "/activity/appJoin";

    /**
     *  /activity/recommend  APP 报名成功数据
     */
    String ACTIVITY_RECOMMEND= "/activity/recommend";


    /**
     *  /purse/getUserScore  获取用户积分
     */
    String PURSE_GETUSERSCORE= "/purse/getUserScore";

    /**
     *  /activity/getAcSucList
     *    获取已购课程列表
     */
    String ACTIVITY_GETACSUCLIST= "/activity/getAcSucList";

    /**
     *  /jsCourse/detail
     *    课程详情页
     */
    String JSCOURSE_DETAIL= "/jsCourse/detail";


    /**
     *  /jsCourse/buy
     *    健身功法购买
     */
    String JSCOURSE_BUY= "/jsCourse/buy";

    /**
     *  /jsCourse/saveExitReason
     *    保存离开原因
     */
    String JSCOURSE_SAVEEXITREASON= "/jsCourse/saveExitReason";


    /**
     *  /jsCourse/collect
     *    收藏健身功法
     */
    String JSCOURSE_COLLECT= "/jsCourse/collect ";

    /**
     *  /jsCourse/savePractice
     *
     * 保存动作练习次数
     */
    String JSCOURSE_SAVEPRACTICE= "/jsCourse/savePractice  ";

    /**
     *  /jsCourse/listcollect
     *
     * 训练营收藏列表
     */
    String JSCOURSE_LISTCOLLECT= "/jsCourse/listcollect  ";

    /**
     *  /jsCourse/listMedal
     *
     * 训练营收藏列表
     */
    String JSCOURSE_LISTMEDAL= "/jsCourse/listMedal  ";

    /**
     *  /jsCourse/listBuy
     *
     * 训练营已购
     */
    String JSCOURSE_LISTBUY= "/jsCourse/listBuy  ";
}