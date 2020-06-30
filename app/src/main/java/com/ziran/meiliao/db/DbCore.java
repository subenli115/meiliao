/*
******************************* Copyright (c)*********************************\
**
**                 (c) Copyright 2015, 蒋朋, china, qd. sd
**                          All Rights Reserved
**
**                           By(青岛)
**                         
**-----------------------------------版本信息------------------------------------
** 版    本: V0.1
**
**------------------------------------------------------------------------------
********************************End of Head************************************\
*/

package com.ziran.meiliao.db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.dao.DaoMaster;
import com.ziran.meiliao.dao.DaoSession;

import java.io.File;


/**
 *
 */
public class DbCore {
    //默认数据库的名称
    private static final String DEFAULT_DB_NAME = "meiliaodata.db";


    private static DaoMaster daoMaster;

    private static DaoSession daoSession;

    private static Context mContext;

    private static String DB_NAME;

    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }

    public static void init(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        mContext = context.getApplicationContext();
        DB_NAME = dbName;
    }

    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            //自定义辅助类；DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失，此处使用OpenHelper。
            DaoMaster.OpenHelper helper = new MyOpenHelper(mContext, DB_NAME);

            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {

        if (daoMaster == null) {
            try {
                ContextWrapper wrapper = new ContextWrapper(context) {
                    /**
                     * 获得数据库路径，如果不存在，则创建对象对象
                     *
                     * @param name
                     */
                    @Override
                    public File getDatabasePath(String name) {
                        // 判断是否存在sd卡
                        return new File(FileUtil.getDbFile());
                    }

                    /**
                     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
                     *
                     * @param name
                     * @param mode
                     * @param factory
                     */
                    @Override
                    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
                        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
                    }

                    /**
                     * Android 4.0会调用此方法获取数据库。
                     *
                     * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String,
                     *      int,
                     *      android.database.sqlite.SQLiteDatabase.CursorFactory,
                     *      android.database.DatabaseErrorHandler)
                     * @param name
                     * @param mode
                     * @param factory
                     * @param errorHandler
                     */
                    @Override
                    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
                        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
                    }
                };
                DaoMaster.OpenHelper helper = new MyOpenHelper(wrapper, DB_NAME);
                daoMaster = new DaoMaster(helper.getWritableDatabase()); //获取未加密的数据库
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return daoMaster;
    }

}
