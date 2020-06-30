package com.ziran.meiliao.constant;

import com.ziran.meiliao.ui.bean.VersionNewBean;

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
    String USER_GET_LOG_CODE = "admin/mobile/alibabaSendSms";

    /**
     * 请求登录
     */
    String USER_LOGIN = "auth/oauth/token";
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
     * /practice/updateNote 修改笔记
     */
    String PRACTICE_UPDATE_NOTE = "practice/updateNote/";


    /**
     * /message/getNoReadCount 获取未读消息数
     */
    String MESSAGE_GET_NO_READ_COUNT = "message/getNoReadCount/";
    /**
     * /message/readAll 已读所有消息
     */
    String MESSAGE_READ_ALL = "message/readAll/";


    /**
     * /practice/downloadVedio 下载练习视频
     */
    String PRACTICE_DOWNLOAD_VEDIO = "practice/downloadVedio/";


    /**
     * conference/getConference
     */
    String CONFERENCE_GET_CONFERENCE = "conference/getConference/";




    /**
     * /missionBuilt/collectMissionList 收藏众筹课程列表
     */
    String MISSION_BUILT_COLLECT_MISSION_LIST = "missionBuilt/collectMissionList/";


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
     *  /jsCourse/listMedal
     *
     * 训练营收藏列表
     */
    String JSCOURSE_LISTMEDAL= "/jsCourse/listMedal  ";



    /**
     *  一键登录
     */
    String ONEKEYLOGIN= "admin/mobile/oneKeyLogin";

    /**
     * 请求登录
     */
    String CODEANDPHONE_LOGIN= "auth/mobile/token/sms";


    /**
     * 文件上传
     */
    String ADMIN_FILE_UPLOAD= "admin/sys-file/upload";



    /**
     * 检测昵称是否存在
     */
    String ADMIN_USER_EXISTENCE= "admin/user/existence";


    /**
     * 完善资料
     */
    String ADMIN_USER_INFOPERFECT= "admin/user/infoPerfect";

    /**
     * 退出登录
     */
    String AUTH_TOKEN_LOGOUT= "auth/token/logout";

    /**
     * 刷新token
     */
    String AUTH_OAUTH_TOKEN= "auth/oauth/token";

    /**
     *  绑定手机
     */
    String ADMIN_USER_BINGDINGUSERPHONE= "admin/user/bindingUserPhone";

    /**
     *  获取用户信息
     */
    String ADMIN_USER_COMPLETEUSERINFO= "admin/user/completeUserInfo";


    /**
     *  修改用户信息
     */
    String ADMIN_USER_UPDATE= "admin/user/update";

    /**
     *  实名认证
     */
    String ADMIN_USER_AUTHENTICATION= "admin/user/authentication";


    /**
     *  修改手机号
     */
    String ADMIN_USER_UPDATEUSERPHONE= "admin/user/updateUserPhone";

    /**
     *  校验验证码
     */
    String ADMIN_USER_VERIFICATION= "admin/user/verification";

    /**
     *  设置密码
     */
    String ADMIN_USER_EDIT= "admin/user/edit";


    /**
     *  青少年模式修改
     */
    String ADMIN_TEENAGERS_EDIT= "admin/teenagers/edit";


    /**
     *  我的动态展示列表
     */
    String ADMIN_SPACE_IMGPAGE= "admin/space/imgPage";

    /**
     *  批量上传
     */
    String ADMIN_SYS_FILE_UPLOADS= "admin/sys-file/uploads";

    /**
     *  他人主页
     */
    String ADMIN_USER_COMPLETEOTHERSUSERINFO= "admin/user/completeOthersUserInfo";


    /**
     *  用户动态列表分页
     */
    String ADMIN_SPACE_APPPAGE= "admin/space/appPage";

    /**
     *  3元现金红包
     */
    String ADMIN_USER_REGISTERRE= "admin/user/registerReward";


    /**
     *  用户动态删除
     */
    String ADMIN_SPACE_DELETE= "admin/space/delete";

    /**
     *  获取匹配推荐用户列表
     */
    String ADMIN_USER_RECOMMENDUSERPGE= "admin/user/recommendUserPage";

    /**
     *  喜欢匹配
     */
    String ADMIN_USERSIGN_ADD= "admin/usersign/add";


    /**
     *  发布动态
     */
    String ADMIN_SPACE_ADD= "admin/space/add";

    /**
     *  三方登录
     */
    String AUTH_MOBILE_TOKEN_SOCIAL= "auth/mobile/token/social";

    /**
     *  商品查询
     */
    String ACCOUNT_COMMODITY_PAGE= "account/commodity/page";


    /**
     *  账户信息查询
     */
    String ACCOUNT_ACCOUNT_INFO= "account/account/info";

    /**
     *  提现
     */
    String ACCOUNT_CAPITALEXTRACT_ADD= "account/capitalextract/add";


    /**
     *  微信h5
     */
    String ACCOUNT_WX_WXPAY= "account/wxh5/wxpay";

    /**
     *  微信统一支付
     */
    String ACCOUNT_WX_WXCHATPAY= "account/wxh5/wechatpay";

    /**
     *  收到的礼物
     */
    String ADMIN_GIFTRECORD_PAGE= "admin/giftrecord/getPage";

    /**
     *  赠送礼物
     */
    String ADMIN_GIFTRECORD_ADD= "admin/giftrecord/add";

    /**
     * 通过第三方账户
     */
    String ACCOUNT_EXTERNAL= "account/external";

    /**
     * 用户绑定第三方账户
     */
    String ACCOUNT_EXTERNAL_BINDING= "account/external/binding";

    /**
     * 用户修改第三方账户
     */
    String ACCOUNT_EXTERNAL_UPDATEBYUSERID= "account/external/updateByUserId";

    /**
     *  聊天付费
     */
    String ADMIN_GIFTRECORD_CHAT= "admin/giftrecord/chat";

    /**
     *  广告领金币
     */
    String ACCOUNT_ADVERTISEMENTRECORD_RECEIVE= "account/advertisementrecord/receive";

    /**
     *  获取三方用户信息
     */
    String ADMIN_SOCIAL_SOCIAL= "admin/social/social";

    /**
     * 验证两个用户是否加过好友
     */
    String ADMIN_GIFTRECORD_REPEAT= "admin/giftrecord/repeat";

    /**
     * 绑定三方信息
     */
    String ADMIN_SOCIAL_BIND= "admin/social/bind";


    /**
     * 支付方式管理
     */
    String ACCOUNT_METHOD_PAGE= "account/method/page";

    /**
     * 支付宝APP下单支付
     */
    String ACCOUNT_ZFB_APPALIPAY= "account/zfb/appalipay";

    /**
     * 订单信息查询
     */
    String ACCOUNT_ORDER= "account/order";


    /**
     * 根据条件查询版本信息
     */
    String ADMIN_APPVERSION_APPVERSION= "admin/appversion/appVersion";


    /**
     * 根据条件查询列表信息
     */
    String ADMIN_DICT_TYPE= "admin/dict/type";

    /**
     * 新增举报信息
     */
    String ADMIN_TREPORT_ADD= "admin/treport/add";

    /**
     * 金币明细
     */
    String ACCOUNT_ACCOUNTLISTING_PAGE= "account/accountlisting/page";

}