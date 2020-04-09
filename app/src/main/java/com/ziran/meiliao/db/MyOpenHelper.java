/*
******************* Copyright (c) ***********************\
**
**         (c) Copyright 2016, 蒋朋, china, sxkj. sd
**                  All Rights Reserved
**
**                 By(青岛世新科技有限公司)
**                    www.qdsxkj.com
**
**                       _oo0oo_
**                      o8888888o
**                      88" . "88
**                      (| -_- |)
**                      0\  =  /0
**                    ___/`---'\___
**                  .' \\|     |// '.
**                 / \\|||  :  |||// \
**                / _||||| -:- |||||- \
**               |   | \\\  -  /// |   |
**               | \_|  ''\---/''  |_/ |
**               \  .-\__  '-'  ___/-. /
**             ___'. .'  /--.--\  `. .'___
**          ."" '<  `.___\_<|>_/___.' >' "".
**         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
**         \  \ `_.   \_ __\ /__ _/   .-` /  /
**     =====`-.____`.___ \_____/___.-`___.-'=====
**                       `=---='
**
**
**     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**
**               佛祖保佑         永无BUG
**
**
**                   南无本师释迦牟尼佛
**

**----------------------版本信息------------------------
** 版    本: V0.1
**
******************* End of Head **********************\
*/

package com.ziran.meiliao.db;

import android.content.Context;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.dao.DaoMaster;
import com.ziran.meiliao.dao.SearchEntryDao;
import com.ziran.meiliao.dao.UserInfoDao;

import org.greenrobot.greendao.database.Database;

/**
 * User: xiehehe
 * Date: 2016-10-16
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        LogUtils.logd("Database oldVersion" + oldVersion + "newVersion" + newVersion);
        switch (oldVersion) {
            case 1:
                SearchEntryDao.createTable(db, true);
            case 2:
            case 3:
            case 4:  //status  watchCount  price
                db.execSQL("ALTER TABLE 'AUTHOR_BEAN' ADD  'DESCRIPT' TEXT ;");
                db.execSQL("ALTER TABLE 'AUTHOR_BEAN' ADD 'HEAD_IMG' TEXT ;");
                db.execSQL("ALTER TABLE 'AUTHOR_BEAN' ADD 'TEA_INTRO' TEXT;");
                db.execSQL("ALTER TABLE 'VIDEO_SECTION_ENTRY' ADD  'M_FREE' TEXT;");
                db.execSQL("ALTER TABLE 'VIDEO_SECTION_ENTRY' ADD 'HEAD_IMG' ;");
                db.execSQL("ALTER TABLE 'VIDEO_SECTION_ENTRY' ADD  'TAG' INTEGER ;");
                db.execSQL("ALTER TABLE 'MUSIC_ENTRY' ADD  'SIZE' TEXT;");
                db.execSQL("ALTER TABLE 'MUSIC_ENTRY' ADD 'CATALOG_NAME' TEXT;");
                db.execSQL("ALTER TABLE 'MUSIC_ENTRY' ADD 'IS_HEAD' BOOL ;");
                db.execSQL("ALTER TABLE 'MUSIC_ENTRY' ADD 'AUDITION' BOOL ;");
//                db.execSQL("ALTER TABLE 'MUSIC_ENTRY' ADD 'IS_HEAD' INTEGER NOT NULL ;");
//                db.execSQL("ALTER TABLE 'MUSIC_ENTRY' ADD 'AUDITION' INTEGER NOT NULL ;");
                db.execSQL("ALTER TABLE 'USER_INFO' ADD  'PHONE' TEXT ;");
                db.execSQL("ALTER TABLE 'USER_INFO' ADD 'IS_TEACHER' BOOL ;");
//                db.execSQL("ALTER TABLE 'USER_INFO' ADD 'IS_TEACHER' INTEGER NOT NULL ;");
                db.execSQL("ALTER TABLE 'COURSE_ENTRY' ADD  'WATCH_COUNT' INTEGER ;");
                db.execSQL("ALTER TABLE 'COURSE_ENTRY' ADD 'PRICE' TEXT ;");
                db.execSQL("ALTER TABLE 'COURSE_ENTRY' ADD 'IS_CHECK_IS_BUY' BOOL ;");
//                db.execSQL("ALTER TABLE 'COURSE_ENTRY' ADD 'IS_CHECK_IS_BUY' INTEGER NOT NULL ;");
                db.execSQL("ALTER TABLE 'COURSE_ENTRY' ADD 'IS_BUY' BOOL ;");
//                db.execSQL("ALTER TABLE 'COURSE_ENTRY' ADD 'IS_BUY' INTEGER NOT NULL ;");
                db.execSQL("ALTER TABLE 'COURSE_ENTRY' ADD  'STATUS' INTEGER ;");
                db.execSQL("ALTER TABLE 'COURSE_ENTRY' ADD 'TAG' INTEGER ;");
            case 6:
//                db.execSQL("ALTER TABLE 'USER_INFO' ADD  'REAL_NAME' TEXT ;");
        }

        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
            //注意此处的参数StudentDao.class，很重要（一开始没注意，给坑了一下），它就是需要升级的table的Dao,
            //不填的话数据丢失，
            // 这里可以放多个Dao.class，也就是可以做到很多table的安全升级，Good~
        }, UserInfoDao.class);
    }

}
