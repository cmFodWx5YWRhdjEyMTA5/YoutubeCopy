package com.mngh.floattube.database.playlist.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.mngh.floattube.database.playlist.model.PlaylistRemoteEntity;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

public class PlaylistRemoteDAO_Impl extends PlaylistRemoteDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPlaylistRemoteEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPlaylistRemoteEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfPlaylistRemoteEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeletePlaylist;

  public PlaylistRemoteDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlaylistRemoteEntity = new EntityInsertionAdapter<PlaylistRemoteEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR FAIL INTO `remote_playlists`(`uid`,`service_id`,`name`,`url`,`thumbnail_url`,`uploader`,`stream_count`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlaylistRemoteEntity value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getServiceId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUrl());
        }
        if (value.getThumbnailUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getThumbnailUrl());
        }
        if (value.getUploader() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUploader());
        }
        if (value.getStreamCount() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getStreamCount());
        }
      }
    };
    this.__deletionAdapterOfPlaylistRemoteEntity = new EntityDeletionOrUpdateAdapter<PlaylistRemoteEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `remote_playlists` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlaylistRemoteEntity value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__updateAdapterOfPlaylistRemoteEntity = new EntityDeletionOrUpdateAdapter<PlaylistRemoteEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `remote_playlists` SET `uid` = ?,`service_id` = ?,`name` = ?,`url` = ?,`thumbnail_url` = ?,`uploader` = ?,`stream_count` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlaylistRemoteEntity value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getServiceId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUrl());
        }
        if (value.getThumbnailUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getThumbnailUrl());
        }
        if (value.getUploader() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUploader());
        }
        if (value.getStreamCount() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getStreamCount());
        }
        stmt.bindLong(8, value.getUid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM remote_playlists";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePlaylist = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM remote_playlists WHERE uid = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final PlaylistRemoteEntity entity) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfPlaylistRemoteEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final PlaylistRemoteEntity... entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfPlaylistRemoteEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final Collection<PlaylistRemoteEntity> entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfPlaylistRemoteEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final PlaylistRemoteEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfPlaylistRemoteEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Collection<PlaylistRemoteEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfPlaylistRemoteEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final PlaylistRemoteEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfPlaylistRemoteEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Collection<PlaylistRemoteEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfPlaylistRemoteEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long upsert(PlaylistRemoteEntity playlist) {
    __db.beginTransaction();
    try {
      long _result = super.upsert(playlist);
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
  public int deletePlaylist(final long playlistId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePlaylist.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, playlistId);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeletePlaylist.release(_stmt);
    }
  }

  @Override
  public Flowable<List<PlaylistRemoteEntity>> getAll() {
    final String _sql = "SELECT * FROM remote_playlists";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"remote_playlists"}, new Callable<List<PlaylistRemoteEntity>>() {
      public List<PlaylistRemoteEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfThumbnailUrl = _cursor.getColumnIndexOrThrow("thumbnail_url");
          final int _cursorIndexOfUploader = _cursor.getColumnIndexOrThrow("uploader");
          final int _cursorIndexOfStreamCount = _cursor.getColumnIndexOrThrow("stream_count");
          final List<PlaylistRemoteEntity> _result = new ArrayList<PlaylistRemoteEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PlaylistRemoteEntity _item;
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpThumbnailUrl;
            _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            final String _tmpUploader;
            _tmpUploader = _cursor.getString(_cursorIndexOfUploader);
            final Long _tmpStreamCount;
            if (_cursor.isNull(_cursorIndexOfStreamCount)) {
              _tmpStreamCount = null;
            } else {
              _tmpStreamCount = _cursor.getLong(_cursorIndexOfStreamCount);
            }
            _item = new PlaylistRemoteEntity(_tmpServiceId,_tmpName,_tmpUrl,_tmpThumbnailUrl,_tmpUploader,_tmpStreamCount);
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
  public Flowable<List<PlaylistRemoteEntity>> listByService(int serviceId) {
    final String _sql = "SELECT * FROM remote_playlists WHERE service_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, serviceId);
    return RxRoom.createFlowable(__db, new String[]{"remote_playlists"}, new Callable<List<PlaylistRemoteEntity>>() {
      public List<PlaylistRemoteEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfThumbnailUrl = _cursor.getColumnIndexOrThrow("thumbnail_url");
          final int _cursorIndexOfUploader = _cursor.getColumnIndexOrThrow("uploader");
          final int _cursorIndexOfStreamCount = _cursor.getColumnIndexOrThrow("stream_count");
          final List<PlaylistRemoteEntity> _result = new ArrayList<PlaylistRemoteEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PlaylistRemoteEntity _item;
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpThumbnailUrl;
            _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            final String _tmpUploader;
            _tmpUploader = _cursor.getString(_cursorIndexOfUploader);
            final Long _tmpStreamCount;
            if (_cursor.isNull(_cursorIndexOfStreamCount)) {
              _tmpStreamCount = null;
            } else {
              _tmpStreamCount = _cursor.getLong(_cursorIndexOfStreamCount);
            }
            _item = new PlaylistRemoteEntity(_tmpServiceId,_tmpName,_tmpUrl,_tmpThumbnailUrl,_tmpUploader,_tmpStreamCount);
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
  public Flowable<List<PlaylistRemoteEntity>> getPlaylist(long serviceId, String url) {
    final String _sql = "SELECT * FROM remote_playlists WHERE url = ? AND service_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, serviceId);
    return RxRoom.createFlowable(__db, new String[]{"remote_playlists"}, new Callable<List<PlaylistRemoteEntity>>() {
      public List<PlaylistRemoteEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfThumbnailUrl = _cursor.getColumnIndexOrThrow("thumbnail_url");
          final int _cursorIndexOfUploader = _cursor.getColumnIndexOrThrow("uploader");
          final int _cursorIndexOfStreamCount = _cursor.getColumnIndexOrThrow("stream_count");
          final List<PlaylistRemoteEntity> _result = new ArrayList<PlaylistRemoteEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PlaylistRemoteEntity _item;
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final String _tmpThumbnailUrl;
            _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            final String _tmpUploader;
            _tmpUploader = _cursor.getString(_cursorIndexOfUploader);
            final Long _tmpStreamCount;
            if (_cursor.isNull(_cursorIndexOfStreamCount)) {
              _tmpStreamCount = null;
            } else {
              _tmpStreamCount = _cursor.getLong(_cursorIndexOfStreamCount);
            }
            _item = new PlaylistRemoteEntity(_tmpServiceId,_tmpName,_tmpUrl,_tmpThumbnailUrl,_tmpUploader,_tmpStreamCount);
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
  Long getPlaylistIdInternal(long serviceId, String url) {
    final String _sql = "SELECT uid FROM remote_playlists WHERE url = ? AND service_id = ?";
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
