package com.mngh.floattube.database.playlist.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.mngh.floattube.database.Converters;
import com.mngh.floattube.database.playlist.PlaylistMetadataEntry;
import com.mngh.floattube.database.playlist.PlaylistStreamEntry;
import com.mngh.floattube.database.playlist.model.PlaylistStreamEntity;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import org.schabi.newpipe.extractor.stream.StreamType;

public class PlaylistStreamDAO_Impl extends PlaylistStreamDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPlaylistStreamEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPlaylistStreamEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfPlaylistStreamEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBatch;

  public PlaylistStreamDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlaylistStreamEntity = new EntityInsertionAdapter<PlaylistStreamEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR FAIL INTO `playlist_stream_join`(`playlist_id`,`stream_id`,`join_index`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlaylistStreamEntity value) {
        stmt.bindLong(1, value.getPlaylistUid());
        stmt.bindLong(2, value.getStreamUid());
        stmt.bindLong(3, value.getIndex());
      }
    };
    this.__deletionAdapterOfPlaylistStreamEntity = new EntityDeletionOrUpdateAdapter<PlaylistStreamEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `playlist_stream_join` WHERE `playlist_id` = ? AND `join_index` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlaylistStreamEntity value) {
        stmt.bindLong(1, value.getPlaylistUid());
        stmt.bindLong(2, value.getIndex());
      }
    };
    this.__updateAdapterOfPlaylistStreamEntity = new EntityDeletionOrUpdateAdapter<PlaylistStreamEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `playlist_stream_join` SET `playlist_id` = ?,`stream_id` = ?,`join_index` = ? WHERE `playlist_id` = ? AND `join_index` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlaylistStreamEntity value) {
        stmt.bindLong(1, value.getPlaylistUid());
        stmt.bindLong(2, value.getStreamUid());
        stmt.bindLong(3, value.getIndex());
        stmt.bindLong(4, value.getPlaylistUid());
        stmt.bindLong(5, value.getIndex());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM playlist_stream_join";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteBatch = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM playlist_stream_join WHERE playlist_id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final PlaylistStreamEntity entity) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfPlaylistStreamEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final PlaylistStreamEntity... entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfPlaylistStreamEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final Collection<PlaylistStreamEntity> entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfPlaylistStreamEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final PlaylistStreamEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfPlaylistStreamEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Collection<PlaylistStreamEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfPlaylistStreamEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final PlaylistStreamEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfPlaylistStreamEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Collection<PlaylistStreamEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfPlaylistStreamEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
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
  public void deleteBatch(final long playlistId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBatch.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, playlistId);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteBatch.release(_stmt);
    }
  }

  @Override
  public Flowable<List<PlaylistStreamEntity>> getAll() {
    final String _sql = "SELECT * FROM playlist_stream_join";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"playlist_stream_join"}, new Callable<List<PlaylistStreamEntity>>() {
      public List<PlaylistStreamEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfPlaylistUid = _cursor.getColumnIndexOrThrow("playlist_id");
          final int _cursorIndexOfStreamUid = _cursor.getColumnIndexOrThrow("stream_id");
          final int _cursorIndexOfIndex = _cursor.getColumnIndexOrThrow("join_index");
          final List<PlaylistStreamEntity> _result = new ArrayList<PlaylistStreamEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PlaylistStreamEntity _item;
            final long _tmpPlaylistUid;
            _tmpPlaylistUid = _cursor.getLong(_cursorIndexOfPlaylistUid);
            final long _tmpStreamUid;
            _tmpStreamUid = _cursor.getLong(_cursorIndexOfStreamUid);
            final int _tmpIndex;
            _tmpIndex = _cursor.getInt(_cursorIndexOfIndex);
            _item = new PlaylistStreamEntity(_tmpPlaylistUid,_tmpStreamUid,_tmpIndex);
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
  public Flowable<Integer> getMaximumIndexOf(final long playlistId) {
    final String _sql = "SELECT COALESCE(MAX(join_index), -1) FROM playlist_stream_join WHERE playlist_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playlistId);
    return RxRoom.createFlowable(__db, new String[]{"playlist_stream_join"}, new Callable<Integer>() {
      public Integer call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
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
  public Flowable<List<PlaylistStreamEntry>> getOrderedStreamsOf(long playlistId) {
    final String _sql = "SELECT * FROM streams INNER JOIN (SELECT stream_id,join_index FROM playlist_stream_join WHERE playlist_id = ?) ON uid = stream_id ORDER BY join_index ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, playlistId);
    return RxRoom.createFlowable(__db, new String[]{"streams","playlist_stream_join"}, new Callable<List<PlaylistStreamEntry>>() {
      public List<PlaylistStreamEntry> call() throws Exception {
        __db.beginTransaction();
        try {
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
            final int _cursorIndexOfStreamId = _cursor.getColumnIndexOrThrow("stream_id");
            final int _cursorIndexOfJoinIndex = _cursor.getColumnIndexOrThrow("join_index");
            final List<PlaylistStreamEntry> _result = new ArrayList<PlaylistStreamEntry>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final PlaylistStreamEntry _item;
              final long _tmpUid;
              _tmpUid = _cursor.getLong(_cursorIndexOfUid);
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
              final long _tmpDuration;
              _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
              final String _tmpUploader;
              _tmpUploader = _cursor.getString(_cursorIndexOfUploader);
              final String _tmpThumbnailUrl;
              _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
              final long _tmpStreamId;
              _tmpStreamId = _cursor.getLong(_cursorIndexOfStreamId);
              final int _tmpJoinIndex;
              _tmpJoinIndex = _cursor.getInt(_cursorIndexOfJoinIndex);
              _item = new PlaylistStreamEntry(_tmpUid,_tmpServiceId,_tmpUrl,_tmpTitle,_tmpStreamType,_tmpDuration,_tmpUploader,_tmpThumbnailUrl,_tmpStreamId,_tmpJoinIndex);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<PlaylistMetadataEntry>> getPlaylistMetadata() {
    final String _sql = "SELECT uid, name, thumbnail_url, COALESCE(COUNT(playlist_id), 0) AS streamCount FROM playlists LEFT JOIN playlist_stream_join ON uid = playlist_id GROUP BY playlist_id ORDER BY name COLLATE NOCASE ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"playlists","playlist_stream_join"}, new Callable<List<PlaylistMetadataEntry>>() {
      public List<PlaylistMetadataEntry> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = __db.query(_statement);
          try {
            final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
            final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
            final int _cursorIndexOfThumbnailUrl = _cursor.getColumnIndexOrThrow("thumbnail_url");
            final int _cursorIndexOfStreamCount = _cursor.getColumnIndexOrThrow("streamCount");
            final List<PlaylistMetadataEntry> _result = new ArrayList<PlaylistMetadataEntry>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final PlaylistMetadataEntry _item;
              final long _tmpUid;
              _tmpUid = _cursor.getLong(_cursorIndexOfUid);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              final String _tmpThumbnailUrl;
              _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
              final long _tmpStreamCount;
              _tmpStreamCount = _cursor.getLong(_cursorIndexOfStreamCount);
              _item = new PlaylistMetadataEntry(_tmpUid,_tmpName,_tmpThumbnailUrl,_tmpStreamCount);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
