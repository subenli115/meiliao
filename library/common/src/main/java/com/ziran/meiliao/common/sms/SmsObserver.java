package com.ziran.meiliao.common.sms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsObserver extends ContentObserver {
	private Context mContext;
	private Handler mHandler;

	/**
	 * 构造方法:用于传值
	 * 
	 * @param context 	上下文对象
	 * @param handler	消息传递对象
	 */
	public SmsObserver(Context context, Handler handler) {
		super(handler);
		this.mHandler = handler;
		this.mContext = context;
	}

	@SuppressLint("NewApi")
	@Override
	public void onChange(boolean selfChange, Uri uri) {
		super.onChange(selfChange, uri);
		String code;

		Log.e("SMS has changed!!", uri.toString());
		/**
		 * 当我们第一次监听到短信变化时,短信还没有写入到数据库,所以在此不应该做任何的操作,直接return
		 */
		if (uri.toString().equals("content://sms/raw")) {
			return;
		}
		/**
		 * 创建一个收件箱的uri,因为我们读取到的信息,最终是在收件箱中读取到的
		 */
		Uri inboxUri = Uri.parse("content://sms/inbox");
		Cursor cursor = mContext.getContentResolver().query(inboxUri, null,
				null, null, "date desc");
		/**
		 * 如果短信还未被被读取
		 */
		if (cursor != null) {
			/**
			 * 判断收件箱是不是为空,即是利用cursor.moveToFirst()
			 */
			if (cursor.moveToFirst()) {
				// 读取到发件人
				String address = cursor.getString(cursor
						.getColumnIndex("address"));
				// 读取到短信的内容
				String body = cursor.getString(cursor.getColumnIndex("body"));
				Log.e("", "发件人为:" + address + "发信内容为:" + body);
				/**
				 * 当然我们还可以对这个程序做些修改,比如我只想接收10086发来的短信验证或者我只想接收“点融网”发来的短信
				/**
				 * 这个正则表达式的含义为:提取短信中连续6个数字的内容(这是因为验证 码一般为6位,当然这个是可以根据项目变化的)
				 */
				Pattern pattern = Pattern.compile("[0-9]{4}");
				Matcher matcher = pattern.matcher(body);
				/**
				 * 如果这条正则表达式能够成功匹配,就提取短信验证码
				 */
				if (matcher.find()) {
					// 为什么是0呢?因为这是第一个matcher中的内容
					code = matcher.group(0);
					// 在日志中打印
					// 用Handler发送消息:在非UI线程更新UI
					mHandler.obtainMessage(MSG_RECEIVED_CODE, code)
							.sendToTarget();
				}
			}
			cursor.close();
		}
	}
	public static final int MSG_RECEIVED_CODE = 1;

	public void register(){
		/**
		 * 短信的Uri
		 */
		Uri uri = Uri.parse("content://sms");
		/**
		 * 将这个观察者注册到这个短信的Uri上面,以此来监听短信的变化
		 */
		mContext.getContentResolver().registerContentObserver(uri, true, this);
	}

	public void unRegister(){
		mContext.getContentResolver().unregisterContentObserver(this);
	}


}
