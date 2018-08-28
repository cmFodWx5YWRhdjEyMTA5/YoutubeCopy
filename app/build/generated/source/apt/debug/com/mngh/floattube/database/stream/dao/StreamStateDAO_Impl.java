package com.mngh.floattube.database.stream.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.mngh.floattube.database.stream.model.StreamStateEntity;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

public class StreamStateDAO_Impl extends StreamStateDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStreamStateEntity;

  private final EntityInsertionAdapter __insertionAdapterOfStreamStateEntity_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfStreamStateEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfStreamStateEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteState;

  public StreamStateDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStreamStateEntity = new EntityInsertionAdapter<StreamStateEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR FAIL INTO `stream_state`(`stream_id`,`progress_time`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamStateEntity value) {
        stmt.bindLong(1, value.getStreamUid());
        stmt.bindLong(2, value.getProgressTime());
      }
    };
    this.__insertionAdapterOfStreamStateEntity_1 = new EntityInsertionAdapter<StreamStateEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `stream_state`(`stream_id`,`progress_time`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamStateEntity value) {
        stmt.bindLong(1, value.getStreamUid());
        stmt.bindLong(2, value.getProgressTime());
      }
    };
    this.__deletionAdapterOfStreamStateEntity = new EntityDeletionOrUpdateAdapter<StreamStateEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `stream_state` WHERE `stream_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamStateEntity value) {
        stmt.bindLong(1, value.getStreamUid());
      }
    };
    this.__updateAdapterOfStreamStateEntity = new EntityDeletionOrUpdateAdapter<StreamStateEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `stream_state` SET `stream_id` = ?,`progress_time` = ? WHERE `stream_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StreamStateEntity value) {
        stmt.bindLong(1, value.getStreamUid());
        stmt.bindLong(2, value.getProgressTime());
        stmt.bindLong(3, value.getStreamUid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM stream_state";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteState = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM stream_state WHERE stream_id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final StreamStateEntity entity) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfStreamStateEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final StreamStateEntity... entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfStreamStateEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final Collection<StreamStateEntity> entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfStreamStateEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  void silentInsertInternal(final StreamStateEntity streamState) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStreamStateEntity_1.insert(streamState);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final StreamStateEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfStreamStateEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Collection<StreamStateEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfStreamStateEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final StreamStateEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfStreamStateEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Collection<StreamStateEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfStreamStateEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long upsert(StreamStateEntity stream) {
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
  public int deleteState(final long streamId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteState.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, streamId);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteState.release(_stmt);
    }
  }

  @Override
  public Flowable<List<StreamStateEntity>> getAll() {
    final String _sql = "SELECT * FROM stream_state";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"stream_state"}, new Callable<List<StreamStateEntity>>() {
      public List<StreamStateEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfStreamUid = _cursor.getColumnIndexOrThrow("stream_id");
          final int _cursorIndexOfProgressTime = _cursor.getColumnIndexOrThrow("progress_time");
          final List<StreamStateEntity> _result = new ArrayList<StreamStateEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StreamStateEntity _item;
            final long _tmpStreamUid;
            _tmpStreamUid = _cursor.getLong(_cursorIndexOfStreamUid);
            final long _tmpProgressTime;
            _tmpProgressTime = _cursor.getLong(_cursorIndexOfProgressTime);
            _item = new StreamStateEntity(_tmpStreamUid,_tmpProgressTime);
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
  public Flowable<List<StreamStateEntity>> getState(final long streamId) {
    final String _sql = "SELECT * FROM stream_state WHERE stream_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, streamId);
    return RxRoom.createFlowable(__db, new String[]{"stream_state"}, new Callable<List<StreamStateEntity>>() {
      public List<StreamStateEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfStreamUid = _cursor.getColumnIndexOrThrow("stream_id");
          final int _cursorIndexOfProgressTime = _cursor.getColumnIndexOrThrow("progress_time");
          final List<StreamStateEntity> _result = new ArrayList<StreamStateEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StreamStateEntity _item;
            final long _tmpStreamUid;
            _tmpStreamUid = _cursor.getLong(_cursorIndexOfStreamUid);
            final long _tmpProgressTime;
            _tmpProgressTime = _cursor.getLong(_cursorIndexOfProgressTime);
            _item = new StreamStateEntity(_tmpStreamUid,_tmpProgressTime);
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
