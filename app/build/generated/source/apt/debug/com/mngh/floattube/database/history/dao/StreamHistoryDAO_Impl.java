package com.mngh.floattube.database.history.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.mngh.floattube.database.Converters;
import com.mngh.floattube.database.history.model.StreamHistoryEntity;
import com.mngh.floattube.database.history.model.StreamHistoryEntry;
import com.mngh.floattube.database.stream.StreamStatisticsEntry;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import org.schabi.newpipe.extractor.stream.StreamType;

public class StreamHistoryDAO_Impl extends StreamHistoryDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStreamHistoryEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfStreamHistoryEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfStreamHistoryEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteStreamHistory;

  public StreamHistoryDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStreamHistoryEntity = new EntityInsertionAdapter<StreamHistoryEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR FAIL INTO `stream_history`(`stream_id`,`access_date`,`repeat_count`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamHistoryEntity value) {
        stmt.bindLong(1, value.getStreamUid());
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getAccessDate());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getRepeatCount());
      }
    };
    this.__deletionAdapterOfStreamHistoryEntity = new EntityDeletionOrUpdateAdapter<StreamHistoryEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `stream_history` WHERE `stream_id` = ? AND `access_date` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamHistoryEntity value) {
        stmt.bindLong(1, value.getStreamUid());
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getAccessDate());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
      }
    };
    this.__updateAdapterOfStreamHistoryEntity = new EntityDeletionOrUpdateAdapter<StreamHistoryEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `stream_history` SET `stream_id` = ?,`access_date` = ?,`repeat_count` = ? WHERE `stream_id` = ? AND `access_date` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamHistoryEntity value) {
        stmt.bindLong(1, value.getStreamUid());
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getAccessDate());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getRepeatCount());
        stmt.bindLong(4, value.getStreamUid());
        final Long _tmp_1;
        _tmp_1 = Converters.dateToTimestamp(value.getAccessDate());
        if (_tmp_1 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp_1);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM stream_history";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteStreamHistory = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM stream_history WHERE stream_id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final StreamHistoryEntity entity) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfStreamHistoryEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final StreamHistoryEntity... entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfStreamHistoryEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final Collection<StreamHistoryEntity> entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfStreamHistoryEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final StreamHistoryEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfStreamHistoryEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Collection<StreamHistoryEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfStreamHistoryEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final StreamHistoryEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfStreamHistoryEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Collection<StreamHistoryEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfStreamHistoryEntity.handleMultiple(entities);
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
  public int deleteStreamHistory(final long streamId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteStreamHistory.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, streamId);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteStreamHistory.release(_stmt);
    }
  }

  @Override
  public StreamHistoryEntity getLatestEntry() {
    final String _sql = "SELECT * FROM stream_history WHERE access_date = (SELECT MAX(access_date) FROM stream_history)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfStreamUid = _cursor.getColumnIndexOrThrow("stream_id");
      final int _cursorIndexOfAccessDate = _cursor.getColumnIndexOrThrow("access_date");
      final int _cursorIndexOfRepeatCount = _cursor.getColumnIndexOrThrow("repeat_count");
      final StreamHistoryEntity _result;
      if(_cursor.moveToFirst()) {
        final long _tmpStreamUid;
        _tmpStreamUid = _cursor.getLong(_cursorIndexOfStreamUid);
        final Date _tmpAccessDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfAccessDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfAccessDate);
        }
        _tmpAccessDate = Converters.fromTimestamp(_tmp);
        final long _tmpRepeatCount;
        _tmpRepeatCount = _cursor.getLong(_cursorIndexOfRepeatCount);
        _result = new StreamHistoryEntity(_tmpStreamUid,_tmpAccessDate,_tmpRepeatCount);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flowable<List<StreamHistoryEntity>> getAll() {
    final String _sql = "SELECT * FROM stream_history";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"stream_history"}, new Callable<List<StreamHistoryEntity>>() {
      public List<StreamHistoryEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfStreamUid = _cursor.getColumnIndexOrThrow("stream_id");
          final int _cursorIndexOfAccessDate = _cursor.getColumnIndexOrThrow("access_date");
          final int _cursorIndexOfRepeatCount = _cursor.getColumnIndexOrThrow("repeat_count");
          final List<StreamHistoryEntity> _result = new ArrayList<StreamHistoryEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StreamHistoryEntity _item;
            final long _tmpStreamUid;
            _tmpStreamUid = _cursor.getLong(_cursorIndexOfStreamUid);
            final Date _tmpAccessDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfAccessDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfAccessDate);
            }
            _tmpAccessDate = Converters.fromTimestamp(_tmp);
            final long _tmpRepeatCount;
            _tmpRepeatCount = _cursor.getLong(_cursorIndexOfRepeatCount);
            _item = new StreamHistoryEntity(_tmpStreamUid,_tmpAccessDate,_tmpRepeatCount);
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
  public Flowable<List<StreamHistoryEntry>> getHistory() {
    final String _sql = "SELECT * FROM streams INNER JOIN stream_history ON uid = stream_id ORDER BY access_date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"streams","stream_history"}, new Callable<List<StreamHistoryEntry>>() {
      public List<StreamHistoryEntry> call() throws Exception {
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
          final int _cursorIndexOfAccessDate = _cursor.getColumnIndexOrThrow("access_date");
          final int _cursorIndexOfRepeatCount = _cursor.getColumnIndexOrThrow("repeat_count");
          final List<StreamHistoryEntry> _result = new ArrayList<StreamHistoryEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StreamHistoryEntry _item;
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
            final Date _tmpAccessDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfAccessDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfAccessDate);
            }
            _tmpAccessDate = Converters.fromTimestamp(_tmp_1);
            final long _tmpRepeatCount;
            _tmpRepeatCount = _cursor.getLong(_cursorIndexOfRepeatCount);
            _item = new StreamHistoryEntry(_tmpUid,_tmpServiceId,_tmpUrl,_tmpTitle,_tmpStreamType,_tmpDuration,_tmpUploader,_tmpThumbnailUrl,_tmpStreamId,_tmpAccessDate,_tmpRepeatCount);
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
  public Flowable<List<StreamStatisticsEntry>> getStatistics() {
    final String _sql = "SELECT * FROM streams INNER JOIN (SELECT stream_id,   MAX(access_date) AS latestAccess,   SUM(repeat_count) AS watchCount FROM stream_history GROUP BY stream_id) ON uid = stream_id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"streams","stream_history"}, new Callable<List<StreamStatisticsEntry>>() {
      public List<StreamStatisticsEntry> call() throws Exception {
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
          final int _cursorIndexOfLatestAccessDate = _cursor.getColumnIndexOrThrow("latestAccess");
          final int _cursorIndexOfWatchCount = _cursor.getColumnIndexOrThrow("watchCount");
          final List<StreamStatisticsEntry> _result = new ArrayList<StreamStatisticsEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StreamStatisticsEntry _item;
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
            final Date _tmpLatestAccessDate;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLatestAccessDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLatestAccessDate);
            }
            _tmpLatestAccessDate = Converters.fromTimestamp(_tmp_1);
            final long _tmpWatchCount;
            _tmpWatchCount = _cursor.getLong(_cursorIndexOfWatchCount);
            _item = new StreamStatisticsEntry(_tmpUid,_tmpServiceId,_tmpUrl,_tmpTitle,_tmpStreamType,_tmpDuration,_tmpUploader,_tmpThumbnailUrl,_tmpStreamId,_tmpLatestAccessDate,_tmpWatchCount);
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
}
