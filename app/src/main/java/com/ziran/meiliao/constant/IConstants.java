/**
 * Copyright (c) www.longdw.com
 */
package com.ziran.meiliao.constant;

/**
 * 常量
 *
 */
public interface IConstants {
	
	String BROADCAST_NAME = "com.ldw.music.broadcast";
	String BROADCAST_SHAKE = "com.ldw.music.shake";


	//是否开启了振动模式
	String SHAKE_ON_OFF = "SHAKE_ON_OFF";
	
	String SP_SHAKE_CHANGE_SONG = "shake_change_song";
	// 播放状态
	int MPS_NOFILE = -1; // 无音乐文件
	int MPS_INVALID = 0; // 当前音乐文件无效
	int MPS_PREPARE = 1; // 准备就绪
	int MPS_PREPARE_OTHER = 18; // 准备就绪
	int MPS_PLAYING = 2; // 播放中
	int MPS_PAUSE = 3; // 暂停
	int MPS_COMPLETION = 8; // 播放完成

	// 播放模式
	int MPM_LIST_LOOP_PLAY = 0; // 列表循环
	int MPM_ORDER_PLAY = 1; // 顺序播放
	int MPM_RANDOM_PLAY = 2; // 随机播放
	int MPM_SINGLE_LOOP_PLAY = 3; // 单曲循环
	
	String PLAY_STATE_NAME = "PLAY_STATE_NAME";
	String PLAY_MUSIC_INDEX = "PLAY_MUSIC_INDEX";
	String PLAY_MUSIC_URL = "PLAY_MUSIC_URL";

	//歌手和专辑列表点击都会进入MyMusic 此时要传递参数表明是从哪里进入的
	String NOTIFY_FLAG = "notifyFlag";

	String ACTION	= "action";
	String DEVICE_ID = "deviceId";
	String LOGIN = "login";
	String FLAG = "FLAG";
}
