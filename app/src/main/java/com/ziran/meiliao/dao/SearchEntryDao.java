package com.ziran.meiliao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ziran.meiliao.entry.SearchEntry;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SEARCH_ENTRY".
*/
public class SearchEntryDao extends AbstractDao<SearchEntry, Long> {

    public static final String TABLENAME = "SEARCH_ENTRY";

    /**
     * Properties of entity SearchEntry.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Content = new Property(1, String.class, "content", false, "CONTENT");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
    }


    public SearchEntryDao(DaoConfig config) {
        super(config);
    }
    
    public SearchEntryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SEARCH_ENTRY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CONTENT\" TEXT," + // 1: content
                "\"TYPE\" INTEGER NOT NULL );"); // 2: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SEARCH_ENTRY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SearchEntry entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
        stmt.bindLong(3, entity.getType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SearchEntry entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
        stmt.bindLong(3, entity.getType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SearchEntry readEntity(Cursor cursor, int offset) {
        SearchEntry entity = new SearchEntry( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // content
            cursor.getInt(offset + 2) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SearchEntry entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContent(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SearchEntry entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SearchEntry entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SearchEntry entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
