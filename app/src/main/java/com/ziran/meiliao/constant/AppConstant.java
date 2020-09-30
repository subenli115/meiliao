package com.ziran.meiliao.constant;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     基本常量
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */
public interface AppConstant {

    String URL = "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512405681970&di=8224522cd94a416d9af9dc0be1b97a3a" +
            "&imgtype=0&src=http%3A%2F%2Fimg1.3lian.com%2F2015%2Fa1%2F105%2Fd%2F40.jpg";


    /**
     * 保存主页面的底部Tab选择索引
     */
    String HOME_CURRENT_TAB_POSITION = "HOME_CURRENT_TAB_POSITION";

    /**
     * 用户信息编辑来源(1 编辑用户昵称, 2 编辑email)
     */
    String KEY_EDIT_USERINFO_FROM = "KEY_EDIT_USERINFO_FROM";

    /**
     * 下载的来源
     */
    String DOWN_ALBUM = "downAlbum";
    /**
     * 下载的来源
     */
    String DOWN_COURSE = "downCourse";

    /**
     * 当前播放的音乐对象
     */
    String KEY_MUSIC = "music";


    /**
     * 当前是否是练习中状态
     */
    String UPDATE_ENROL_ACTIVITY = "refreshAct";
    String UPDATE_COLLECT_ACTIVITY = "updateCollect";
    java.lang.String VERSION_CODE = "VERSION_CODE";

    /**
     * author 吴祖清
     * create  2017/3/31 10
     * des     偏好设置常量与Intent 和 Bundle 传参 常量 RxBus tag 常量
     * <p>
     * updateAuthor
     * updateDate
     * updateDes
     */

    interface SPKey {
        String TOKEN = "accessToken";
        String R_TOKEN = "r_accessToken";
        String PHONE = "phone";
        String USERID = "USERID";
        String EXTRAS_URL = "url";
        String ALBUM_ID = "albumId";
        String COURSE_ID = "courseId";
        String MESSAGE_PUSH = "MESSAGE_PUSH";
        String EXERCISE_NUMBER = "EXERCISE_NUMBER";
        String PLAY_MODE = "PLAY_MODE";
        String WIFI_DOWNLOAD_SWITCH = "WIFI_DOWNLOAD_SWITCH";
        String SYSTEM_VERSION = "system_version";

        String SHOW_JYG_FRAGMENT_PLAY = "showplay";
        String PLAY_CURRENT_DATA = "PLAY_CURRENT_DATA";

        String PLAY_DATA = "loadData";

        String FIRST_SHOW_PUSH_TIPS = "FIRST_SHOW_PUSH_TIPS";
        String COURSE_FRIST_TIPS = "COURSE_FRIST_TIPS";
        String NOTIFY_LIAN_XI = "NOTIFY_LIAN_XI";
        String ALBUM_FLAG = "ALBUM_FLAG";

    }

    interface RXTag {
        String PLAY_STATE = "PLAY_STATE";
        /**
         * 是否显示底部动作栏
         */

        String FITNESS_BACK = "FITNESS_BACK";
        /**
         * 当前音频播放界面的音乐的播放进度
         */
        String MUSIC_PLAY_UPDATE_POSITION = "MUSIC_PLAY_UPDATE_POSITION";

        /**
         * 当前练习界面音乐的播放进度
         */
        String EXERCISE_PLAY_UPDATE_POSITION = "EXERCISE_PLAY_UPDATE_POSITION";

        /**
         * 设置当前播放视频的路径
         */
        String VIDEO_PATH = "viedoPath";
        String UPDATE_TITLE = "updataTitle";
        String UPDATE_MEMBER="updataMember";
        String UPDATE_COLLECT = "updateCollect";
        String DELETE_UPDATE = "deleteUpdate";
        String CLOSE_VIEW = "closeView";
        String UMENG_MSG = "UMessage";
        String UMENG_MSG_CLICK = "UMessageClick";
        String SEND = "send";
        String USER_COUNT = "userCount";
        String PUSH = "push";
        String UPDATE_TRAILER = "updateTrailer";
        String TRAILER_WEB_ACTIVITY_DATA = "TrailerWebActivityData";
        String DELETE = "delete";
        String CLOSE = "close";
        String UPDATE_SEL = "updateSel";
        String SHOW_DIALOG = "showDialog";
        String ON_WEB_PAY = "onWebPay";
        String EDIT_USER_INFO = "EDIT_USER_INFO";
        String UPDATE_USER = "UPDATE_USER";
        String UPDATE_ACCOUNT = "UPDATE_ACCOUNT";
        String UPDATE_OTHERUSER = "UPDATE_OTHERUSER";
        String NETWORK_CHANGE_STATE = "netWorkState";

        String REQ_BUY_COURSE = "REQ_BUY_COURSE";
        String REQ_BUY_COURSE_FINISH = "REQ_BUY_COURSE_FINISH";
        String LIVE_OVER = "LIVE_OVER";
        String USER_TICK = "USER_TICK";
        String GIVE_GIFT = "GIVE_GIFT";
        String GIVE_GIFT1 = "GIVE_GIFT1";
        String GIVE_GIFT2 = "GIVE_GIFT2";
        String BALANCE = "balance";
        String GIVE_ALBUM = "GIVE_ALBUM";
        String ALBUM_COUNT_DOWN_TIME = "ALBUM_COUNT_DOWN_TIME";
        String PLAYER_END = "PLAYER_END";
        String POPUW_SET_TIME_DISMISS = "POPUW_SET_TIME_DISMISS";
        String UNBIND_BANK = "UNBIND_BANK";
        String SUBSCRIBE_AUDIO_TAG = "SUBSCRIBE_AUDIO_TAG";

        int SUBSCRIBE_AUDIO_TAG_POST_COMMENT = 0x6e21;
        int SUBSCRIBE_AUDIO_TAG_POST_PRAISE_COMMENT = 0x6e22;


        String CATEGORY_MORE_CLICK = "CATEGORY_MORE_CLICK";
        String MAIN_HOME_MORE_CLICK = "MAIN_HOME_MORE_CLICK";
        String SJKZHUANLAN_MORE_CLICK = "SJKZHUANLAN_MORE_CLICK";
        String DELETE_NOTES = "DELETE_NOTES";
        String HOME_MUSIC_PLANE_SHOW_OR_HIDE = "HOME_MUSIC_PLANE_SHOW_OR_HIDE";
        String EXERCISE_PLAY = "EXERCISE_PLAY";
        String SUBSCRIBE_COMMENT_FRAGMENT_SHOW_OR_HIDE = "SUBSCRIBE_COMMENT_FRAGMENT_SHOW_OR_HIDE";


        String AUDIO_ID = "audioId";
        String SWITCH_FRAGMENT = "SWITCH_FRAGMENT";
        String HOME_UPDATE = "HOME_UPDATE";
        String SUBSCRIBE_UPDATE = "SUBSCRIBE_UPDATE";
        String BIG_IN_TAG = "BIG_IN_TAG";

        String SUBMIT_USER_MSG = "SUBMIT_USER_MSG";
        String PRACTICE_CAN_SCROLL = "PRACTICE_CAN_SCROLL";
        String CONFERENCE_GET_CONFERENCE = "CONFERENCE_GET_CONFERENCE";
        String GET_GAIN_SPREAD = "GET_GAIN_SPREAD";
        String BIND_VIEWPAGER_UTIL = "BIND_VIEWPAGER_UTIL";
        String WORKSHOPS_MAIN_TOP_BAR_SHOW_HIDE = "WORKSHOPS_MAIN_TOP_BAR_SHOW_HIDE";
        String BASE_ITEM_VIEW_CLICK_ID = "BASE_ITEM_VIEW_CLICK_ID";
        String CROWD_FUNDING_CHOOSE_DATA = "CROWD_FUNDING_CHOOSE_DATA";
        String CHANGE_VIDEO_PLAY_STATE = "CHANGE_VIDEO_PLAY_STATE";
        String CITY_DATA = "CITY_DATA";
        String USER_TEMPLATE = "USER_TEMPLATE";
        String IMAGE_RESULT = "image";
        String SUBMIT_CROWD_FUNDING_MSG = "SUBMIT_CROWD_FUNDING_MSG";
        String PREVIEW_CLOSE = "PREVIEW_CLOSE";
        String MPS_COMPLETION = "MPS_COMPLETION";
        String UPDATE_STUDY_FINISH = "UPDATE_STUDY_FINISH";
        String REFUND_RESULT = "REFUND_RESULT";
        String ZL_BUY_SUCESS = "ZL_BUY_SUCESS";
        String MUSIC_BUY_SUCESS = "MUSIC_BUY_SUCESS";
    }


    interface ExtraKey {
        String ALBUM_GAIN = "ALBUM_GAIN";
        String EXTRAS_TITLE = "title";
        String COLUMN_ID = "columnId";
        String SUBSCRIPTION_ID = "subscriptionId";
        String TARGET_ID = "targetId";
        String ALBUM_TITLE = "ALBUM_TITLE";
        /**
         * 当前编辑用户个人资料的内容
         */
        String KEY_CONTENT = "KEY_CONTENT";


        String LIVE_STREAMING = "liveStreaming";
        String M_VIDEO_PATH = "videoPath";
        String VIDEO_TITLE = "title";
        String FROM_TYPE = "FROM_TYPE";
        String FROM_CONDITION = "FROM_CONDITION";
        String FROM_ID = "_ID";
        String DEVICE_TOKEN = "deviceToken";
        String BALANCE = "BALANCE";


        String IS_LOAD_DETAIL = "IS_LOAD_DETAIL";
        String AUTHOR_DATA = "AUTHOR_DATA";
        String CARD_NO = "bankCardNo";
        String BANK_INFO = "BANK_INFO";
        String BANK_LIST = "BANK_LIST";
        String ZHUANLAN_PAY_DATA = "ZHUANLAN_PAY_DATA";
        String BEAN = "bean";
        String UNREAD_COUNT = "UNREAD_COUNT";
        String TARGET_KEY = "TARGET_KEY";
        //        String COMMON_ID = "COMMON_ID";
        String EXTRAS_DATA = "EXTRAS_DATA";
        String EXTRAS_ZHIBO = "EXTRAS_ZHIBO";
        String EXTRAS_DATA_NEEDED = "EXTRAS_DATA_NEEDED";
        String COUNT_DOWN_STOP = "COUNT_DOWN_STOP";
        String CLEAR_FILTER = "CLEAR_FILTER";
        String FROM_CAN_CLOSE = "FROM_CAN_CLOSE";
    }

    interface CollectCourse{
         String  ITEM_TYPE_ACTIVITY = "activity";
         String ITEM_TYPE_CROWD_FUND = "crowdFunds";
         String ITEM_TYPE_TEAM = "missionBuilts";
         String ITEM_TYPE_TEACHER = "famousTeachers";
    }
    interface TeamDetail {
        int TYPE_DAY = 1;
        int TYPE_NORMAL = 2;
        int ICON_TYPE_ADDRESS = 1;
        int ICON_TYPE_RITE = 2;
        int ICON_TYPE_HOURSE = 3;
        int ICON_TYPE_HOTEL = 4;
    }

    interface SearchId {
        int WHAT_FROM_TYPE_CROWD_FUNDING_PROJECT_MSG = 10;
        int WHAT_FROM_TYPE_CROWD_FUNDING_PROJECT_MSG_AVATAR = 12;
        int WHAT_FROM_TYPE_CROWD_FUNDING_USED_INPUT_MSG = 11;
        int WHAT_TOPIC = 1;
        int WHAT_TEACHER = 2;
        int WHAT_HOME_TEACHER_LIST = 3;
        String TYPE_FORM_TOPIC = "TYPE_FORM_TOPIC";
        String TYPE_FORM_TEACHER = "TYPE_FORM_TEACHER";
        String TYPE_FORM_HOME_TEACHER_LIST = "TYPE_FORM_HOME_TEACHER_LIST";

        int RESULT_TEAM = 1114;
        int RESULT_TEACHER = 1115;
        int RESULT_XIANG_GUAN = 1118;
        int RESULT_EMPTY = 1119;
        int RESULT_CROWD_FUNDING = 1113;

        int RESULT_ALBUM = 1110;
        int RESULT_COURSE = 1111;
        int RESULT_ACTIVITY = 1112;
    }


}
