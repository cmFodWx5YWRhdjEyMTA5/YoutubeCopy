package com.mngh.floattube.database.stream.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.mngh.floattube.database.Converters;
import com.mngh.floattube.database.stream.model.StreamEntity;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import org.schabi.newpipe.extractor.stream.StreamType;

public class StreamDAO_Impl extends StreamDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStreamEntity;

  private final EntityInsertionAdapter __insertionAdapterOfStreamEntity_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfStreamEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfStreamEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOrphans;

  public StreamDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStreamEntity = new EntityInsertionAdapter<StreamEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR FAIL INTO `streams`(`uid`,`service_id`,`url`,`title`,`stream_type`,`duration`,`uploader`,`thumbnail_url`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamEntity value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getServiceId());
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
        final String _tmp;
        _tmp = Converters.stringOf(value.getStreamType());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp);
        }
        if (value.getDuration() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getDuration());
        }
        if (value.getUploader() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUploader());
        }
        if (value.getThumbnailUrl() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getThumbnailUrl());
        }
      }
    };
    this.__insertionAdapterOfStreamEntity_1 = new EntityInsertionAdapter<StreamEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `streams`(`uid`,`service_id`,`url`,`title`,`stream_type`,`duration`,`uploader`,`thumbnail_url`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamEntity value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getServiceId());
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
        final String _tmp;
        _tmp = Converters.stringOf(value.getStreamType());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp);
        }
        if (value.getDuration() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getDuration());
        }
        if (value.getUploader() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUploader());
        }
        if (value.getThumbnailUrl() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getThumbnailUrl());
        }
      }
    };
    this.__deletionAdapterOfStreamEntity = new EntityDeletionOrUpdateAdapter<StreamEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `streams` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamEntity value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__updateAdapterOfStreamEntity = new EntityDeletionOrUpdateAdapter<StreamEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `streams` SET `uid` = ?,`service_id` = ?,`url` = ?,`title` = ?,`stream_type` = ?,`duration` = ?,`uploader` = ?,`thumbnail_url` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamEntity value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getServiceId());
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
        final String _tmp;
        _tmp = Converters.stringOf(value.getStreamType());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp);
        }
        if (value.getDuration() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getDuration());
        }
        if (value.getUploader() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUploader());
        }
        if (value.getThumbnailUrl() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getThumbnailUrl());
        }
        stmt.bindLong(9, value.getUid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM streams";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOrphans = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM streams WHERE uid NOT IN (SELECT DISTINCT uid FROM streams LEFT JOIN stream_history ON uid = stream_history.stream_id LEFT JOIN playlist_stream_join ON uid = playlist_stream_join.stream_id)";
        return _query;
      }
    };
  }

  @Override
  public long insert(final StreamEntity entity) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfStreamEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final StreamEntity... entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfStreamEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final Collection<StreamEntity> entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfStreamEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  void silentInsertAllInternal(final List<StreamEntity> streams) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStreamEntity_1.insert(streams);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final StreamEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfStreamEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Collection<StreamEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfStreamEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final StreamEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfStreamEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Collection<StreamEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfStreamEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long upsert(StreamEntity stream) {
    __db.beginTransaction();
    try {
      long _result = super.upsert(stream);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> upsertAll(List<StreamEntity> streams) {
    __db.beginTransaction();
    try {
      List<Long> _result = super.upsertAll(streams);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public int deleteOrphans() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOrphans.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteOrphans.release(_stmt);
    }
  }

  @Override
  public Flowable<List<StreamEntity>> getAll() {
    final String _sql = "SELECT * FROM streams";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"streams"}, new Callable<List<StreamEntity>>() {
      public List<StreamEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfStreamType = _cursor.getColumnIndexOrThrow("stream_type");
          final int _cursorIndexOfDuration = _cursor.getColumnIndexOrThrow("duration");
          final int _cursorIndexOfUploader = _cursor.getColumnIndexOrThrow("uploader");
          final int _cursorIndexOfThumbnailUrl = _cursor.getColumnIndexOrThrow("thumbnail_url");
          final List<StreamEntity> _result = new ArrayList<StreamEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StreamEntity _item;
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final StreamType _tmpStreamType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfStreamType);
            _tmpStreamType = Converters.streamTypeOf(_tmp);
            final Long _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            }
            final String _tmpUploader;
            _tmpUploader = _cursor.getString(_cursorIndexOfUploader);
            final String _tmpThumbnailUrl;
            _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            _item = new StreamEntity(_tmpServiceId,_tmpTitle,_tmpUrl,_tmpStreamType,_tmpThumbnailUrl,_tmpUploader,_tmpDuration);
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<StreamEntity>> listByService(int serviceId) {
    final String _sql = "SELECT * FROM streams WHERE service_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, serviceId);
    return RxRoom.createFlowable(__db, new String[]{"streams"}, new Callable<List<StreamEntity>>() {
      public List<StreamEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfStreamType = _cursor.getColumnIndexOrThrow("stream_type");
          final int _cursorIndexOfDuration = _cursor.getColumnIndexOrThrow("duration");
          final int _cursorIndexOfUploader = _cursor.getColumnIndexOrThrow("uploader");
          final int _cursorIndexOfThumbnailUrl = _cursor.getColumnIndexOrThrow("thumbnail_url");
          final List<StreamEntity> _result = new ArrayList<StreamEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StreamEntity _item;
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final StreamType _tmpStreamType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfStreamType);
            _tmpStreamType = Converters.streamTypeOf(_tmp);
            final Long _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            }
            final String _tmpUploader;
            _tmpUploader = _cursor.getString(_cursorIndexOfUploader);
            final String _tmpThumbnailUrl;
            _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            _item = new StreamEntity(_tmpServiceId,_tmpTitle,_tmpUrl,_tmpStreamType,_tmpThumbnailUrl,_tmpUploader,_tmpDuration);
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<StreamEntity>> getStream(long serviceId, String url) {
    final String _sql = "SELECT * FROM streams WHERE url = ? AND service_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, serviceId);
    return RxRoom.createFlowable(__db, new String[]{"streams"}, new Callable<List<StreamEntity>>() {
      public List<StreamEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfStreamType = _cursor.getColumnIndexOrThrow("stream_type");
          final int _cursorIndexOfDuration = _cursor.getColumnIndexOrThrow("duration");
          final int _cursorIndexOfUploader = _cursor.getColumnIndexOrThrow("uploader");
          final int _cursorIndexOfThumbnailUrl = _cursor.getColumnIndexOrThrow("thumbnail_url");
          final List<StreamEntity> _result = new ArrayList<StreamEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StreamEntity _item;
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final StreamType _tmpStreamType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfStreamType);
            _tmpStreamType = Converters.streamTypeOf(_tmp);
            final Long _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            }
            final String _tmpUploader;
            _tmpUploader = _cursor.getString(_cursorIndexOfUploader);
            final String _tmpThumbnailUrl;
            _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            _item = new StreamEntity(_tmpServiceId,_tmpTitle,_tmpUrl,_tmpStreamType,_tmpThumbnailUrl,_tmpUploader,_tmpDuration);
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  Long getStreamIdInternal(long serviceId, String url) {
    final String _sql = "SELECT uid FROM streams WHERE url = ? AND service_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, serviceId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final Long _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getLong(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
