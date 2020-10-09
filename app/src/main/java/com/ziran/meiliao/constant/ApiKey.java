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
    String UPDATE_USER_INFOV2 = "userInfo/updateUserInfoV2";

    /**
     * /version/getLastestVersion 获取平台最新版本号
     */
    String VERSION = "version/getLastestVersion";




    /**
     * /album/uploadPoster 海报上传
     */
    String ALBUM_UPLOAD_POSTER = "album/uploadPoster/";


    /**
     * POST /message/listMessageOfficial 获取官方消息
     */
    String LIST_MESSAGE_OFFICIAL = "message/listMessageOfficial/";



    /**
     * /userHome/listActivity 收藏的活动列表
     */
    String USER_HOME_LIST_ACTIVITY = "userHome/listActivity/";


    /**
     * /userHome/trailer 直播预告
     */
    String USER_HOME_TRAILER = "userHome/trailer/";



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
     * /userHome/rec 推广大使和推荐好友
     */
    String USER_HOME_RES = "userHome/rec/";

    /**
     * //user/checkToken 检查token
     */
    String USER_CHECK_TOKEN = "user/checkToken/";


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
     * /album/gainSpread 获取推广音频信息
     */
    String ALBUM_GAIN_SPREAD = "album/gainSpread/";


    /**
     * /album/giveAlbum 赠送推广音频
     */
    String ALBUM_GIVE_ALBUM = "album/giveAlbum/";


    /**
     * /home/getData
     */
    String HOME_GET_DATA = "home/getData/";



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
     *  /home/getaDataV2 获取首页数据v2
     */
    String HOME_GDATAV2= "/home/getDataV2" ;




    /**
     *  /userInfo/tagSaveCheck 第一次采集
     */
    String USERINFO_TAGSAVECHECK= "/userInfo/tagSaveCheck";


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
     *  /activity/getActivityDetails  课程详情
     */
    String ACTIVITY_GETACTIVITYDETAILS= "/activity/getActivityDetails";




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

    /**
     * 新增用户关注
     */
    String ADMIN_USERFOLLOW_ADD= "admin/userfollow/add";

    /**
     * 取消用户关注
     */
    String ADMIN_USERFOLLOW_DEL= "admin/userfollow/del";

    /**
     * APP分页查询用户关注或者关注用户的列表记录
     */
    String ADMIN_USERFOLLOW_PAGEUSERTYPE= "admin/userfollow/pageUserType";


    /**
     * APP分页查询用户关注和关注了用户的记录
     */
    String ADMIN_USERFOLLOW_PAGEBYUSERID= "admin/userfollow/pageByUserId";


    /**
     * 获取上传地址和凭证
     */
    String ADMIN_TOOL_CREATEUPLOADVIDEO= "admin/tool/createUploadVideo";


    /**
     * 刷新视频上传凭证
     */
    String ADMIN_TOOL_REFRESH= "admin/tool/refreshUploadVideoResponse";

    /**
     * 获取视频播放凭证
     */
    String ADMIN_TOOL_GETVIDEOPLAYAUTH= "admin/tool/getVideoPlayAuth";

    /**
     * 根据时间排序的动态推荐
     */
    String ADMIN_SPACE_SPACERECOMMENDCREATETIME= "admin/space/spaceRecommendCreateTime";

    /**
     * 获取视频播放信息
     */
    String ADMIN_TOOL_GETPLAYINFO= "admin/tool/getPlayInfo";

    /**
     * 获取发过动态的用户=关注推荐
     */
    String ADMIN_USER_DYNAMIC= "admin/user/dynamic";

    /**
     * 广告分类查询全部
     */
    String ADMIN_EXHIBITION_LIST= "admin/exhibition/list";

    /**
     * 首页推荐
     */
    String ADMIN_USER_RECOMMEND= "admin/user/recommend";


    /**
     * 菜单查询
     */
    String ADMIN_APPMENU_PAGE= "admin/appmenu/pageByType";


    /**
     * 查询访问过自己主页的访客列表
     */
    String ADMIN_VISITOR_PAGEBYID= "admin/visitor/pageById";

    /**
     * 分页查询标记喜欢自己的用户列表
     */
    String ADMIN_USERSIGN_PAGEBYID= "admin/usersign/pageById";

    /**
     * 分页查询用户选择标记标签
     */
    String ADMIN_USERTABLE_GETPAGE= "admin/usertable/getPage";

    /**
     * 分页查询兴趣标签
     */
    String ADMIN_LABEL_GETPAGE= "admin/label/getPage";

    /**
     * 新增用户标签
     */
    String ADMIN_USERTABLE_ADD= "admin/usertable/add";

    /**
     * 根据用户名搜索用户信息
     */
    String ADMIN_USER_SEARCH= "admin/user/search";

    /**
     * 查询标记我和访问我的未读数量
     */
    String ADMIN_VISITOR_VISIORNUM= "admin/visitor/visitorNum";

    /**
     * 根据消息类型分页查询
     */
    String ADMIN_NEWSNOTICE_PAGE= "admin/newsnotice/page";


    /**
     * 分类查询所有类型消息和未读数量
     */
    String ADMIN_NEWSNOTICE_LIST= "admin/newsnotice/list";

    /**
     * 真人匹配
     */
    String ADMIN_USER_REALPEOPLE= "admin/user/realPeople";

    /**
     * 附近的人
     */
    String ADMIN_USER_NEARBY= "admin/user/nearby";

    /**
     * 分页查询用户所获礼物列表
     */
    String ADMIN_GIFTBYRECEIVEUSERID= "admin/giftrecord/getGiftByReceiveUserId";

    /**
     * 点赞
     */
    String ADMIN_USERCLICK_ADD= "admin/userclick/add";

    /**
     * 解除黑名单
     */
    String ADMIN_BLACKLIST_DELETE= "admin/blacklist/delete";

    /**
     * 添加黑名单
     */
    String ADMIN_BLACKLIST_ADD= "admin/blacklist/add";

    /**
     * 通过两个用户id查询用户亲密度
     */
    String ADMIN_INTIMACY_INFO= "admin/intimacy/info";


    /**
     * 用户关注用户的动态推荐
     */
    String ADMIN_SPACE_FOLLOWSPACE= "admin/space/followSpace";


    /**
     * 新增用户评论
     */
    String ADMIN_COMMENT_ADD= "admin/comment/add";


    /**
     * 用户评论
     */
    String ADMIN_COMMENT_PAGE= "admin/comment/page";

    /**
     * 动态详情
     */
    String ADMIN_SPACE_GETBYID= "admin/space/getById";

    /**
     * 分页查询动态接收礼物
     */
    String ADMIN_GIFTRECORD_GETGIFTBYID= "admin/giftrecord/getGiftById";

    /**
     * 删除评论
     */
    String ADMIN_COMMENT_DELETE= "admin/comment/delete";

    /**
     * 增加亲密度
     */
    String ADMIN_INTIMACY_ADD= "admin/intimacy/add";

    /**
     * 查询冻结
0     */
    String ADMIN_USER_GETFROZEN= "admin/user/getFrozen";

    /**
     * 判断文本内容是否有违规信息im
     */
    String ADMIN_TOOL_IMSECURITY= "admin/tool/IMSecurity";

    /**
     * 判断文本内容是否有违规信息
     */
    String ADMIN_TOOL_TESTSECURITY= "admin/tool/testSecurity";

    /**
     * 根据条件查询开关信息
     */
    String ADMIN_SWITCH_GETSYSSWITCH= "admin/switch/getSysSwitch";

    /**
     * 注销用户
     */
    String ADMIN_USER_CANCELLATION= "admin/user/cancellation";


}