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
import com.mngh.floattube.database.history.model.SearchHistoryEntry;
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

public class SearchHistoryDAO_Impl implements SearchHistoryDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSearchHistoryEntry;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfSearchHistoryEntry;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfSearchHistoryEntry;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllWhereQuery;

  public SearchHistoryDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSearchHistoryEntry = new EntityInsertionAdapter<SearchHistoryEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR FAIL INTO `search_history`(`id`,`creation_date`,`service_id`,`search`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SearchHistoryEntry value) {
        stmt.bindLong(1, value.getId());
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getCreationDate());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getServiceId());
        if (value.getSearch() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSearch());
        }
      }
    };
    this.__deletionAdapterOfSearchHistoryEntry = new EntityDeletionOrUpdateAdapter<SearchHistoryEntry>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `search_history` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SearchHistoryEntry value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfSearchHistoryEntry = new EntityDeletionOrUpdateAdapter<SearchHistoryEntry>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `search_history` SET `id` = ?,`creation_date` = ?,`service_id` = ?,`search` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SearchHistoryEntry value) {
        stmt.bindLong(1, value.getId());
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.getCreationDate());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getServiceId());
        if (value.getSearch() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSearch());
        }
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM search_history";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllWhereQuery = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM search_history WHERE search = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final SearchHistoryEntry entity) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfSearchHistoryEntry.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final SearchHistoryEntry... entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfSearchHistoryEntry.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final Collection<SearchHistoryEntry> entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfSearchHistoryEntry.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final SearchHistoryEntry entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfSearchHistoryEntry.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Collection<SearchHistoryEntry> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfSearchHistoryEntry.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final SearchHistoryEntry entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfSearchHistoryEntry.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Collection<SearchHistoryEntry> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfSearchHistoryEntry.handleMultiple(entities);
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
  public int deleteAllWhereQuery(String query) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllWhereQuery.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (query == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, query);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllWhereQuery.release(_stmt);
    }
  }

  @Override
  public SearchHistoryEntry getLatestEntry() {
    final String _sql = "SELECT * FROM search_history WHERE id = (SELECT MAX(id) FROM search_history)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCreationDate = _cursor.getColumnIndexOrThrow("creation_date");
      final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
      final int _cursorIndexOfSearch = _cursor.getColumnIndexOrThrow("search");
      final SearchHistoryEntry _result;
      if(_cursor.moveToFirst()) {
        final Date _tmpCreationDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfCreationDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfCreationDate);
        }
        _tmpCreationDate = Converters.fromTimestamp(_tmp);
        final int _tmpServiceId;
        _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
        final String _tmpSearch;
        _tmpSearch = _cursor.getString(_cursorIndexOfSearch);
        _result = new SearchHistoryEntry(_tmpCreationDate,_tmpServiceId,_tmpSearch);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
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
  public Flowable<List<SearchHistoryEntry>> getAll() {
    final String _sql = "SELECT * FROM search_history ORDER BY creation_date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"search_history"}, new Callable<List<SearchHistoryEntry>>() {
      public List<SearchHistoryEntry> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfCreationDate = _cursor.getColumnIndexOrThrow("creation_date");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfSearch = _cursor.getColumnIndexOrThrow("search");
          final List<SearchHistoryEntry> _result = new ArrayList<SearchHistoryEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SearchHistoryEntry _item;
            final Date _tmpCreationDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreationDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreationDate);
            }
            _tmpCreationDate = Converters.fromTimestamp(_tmp);
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpSearch;
            _tmpSearch = _cursor.getString(_cursorIndexOfSearch);
            _item = new SearchHistoryEntry(_tmpCreationDate,_tmpServiceId,_tmpSearch);
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
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
  public Flowable<List<SearchHistoryEntry>> getUniqueEntries(int limit) {
    final String _sql = "SELECT * FROM search_history GROUP BY search ORDER BY creation_date DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return RxRoom.createFlowable(__db, new String[]{"search_history"}, new Callable<List<SearchHistoryEntry>>() {
      public List<SearchHistoryEntry> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfCreationDate = _cursor.getColumnIndexOrThrow("creation_date");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfSearch = _cursor.getColumnIndexOrThrow("search");
          final List<SearchHistoryEntry> _result = new ArrayList<SearchHistoryEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SearchHistoryEntry _item;
            final Date _tmpCreationDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreationDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreationDate);
            }
            _tmpCreationDate = Converters.fromTimestamp(_tmp);
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpSearch;
            _tmpSearch = _cursor.getString(_cursorIndexOfSearch);
            _item = new SearchHistoryEntry(_tmpCreationDate,_tmpServiceId,_tmpSearch);
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
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
  public Flowable<List<SearchHistoryEntry>> listByService(int serviceId) {
    final String _sql = "SELECT * FROM search_history WHERE service_id = ? ORDER BY creation_date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, serviceId);
    return RxRoom.createFlowable(__db, new String[]{"search_history"}, new Callable<List<SearchHistoryEntry>>() {
      public List<SearchHistoryEntry> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfCreationDate = _cursor.getColumnIndexOrThrow("creation_date");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfSearch = _cursor.getColumnIndexOrThrow("search");
          final List<SearchHistoryEntry> _result = new ArrayList<SearchHistoryEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SearchHistoryEntry _item;
            final Date _tmpCreationDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreationDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreationDate);
            }
            _tmpCreationDate = Converters.fromTimestamp(_tmp);
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpSearch;
            _tmpSearch = _cursor.getString(_cursorIndexOfSearch);
            _item = new SearchHistoryEntry(_tmpCreationDate,_tmpServiceId,_tmpSearch);
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
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
  public Flowable<List<SearchHistoryEntry>> getSimilarEntries(String query, int limit) {
    final String _sql = "SELECT * FROM search_history WHERE search LIKE ? || '%' GROUP BY search LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    return RxRoom.createFlowable(__db, new String[]{"search_history"}, new Callable<List<SearchHistoryEntry>>() {
      public List<SearchHistoryEntry> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfCreationDate = _cursor.getColumnIndexOrThrow("creation_date");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfSearch = _cursor.getColumnIndexOrThrow("search");
          final List<SearchHistoryEntry> _result = new ArrayList<SearchHistoryEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SearchHistoryEntry _item;
            final Date _tmpCreationDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfCreationDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfCreationDate);
            }
            _tmpCreationDate = Converters.fromTimestamp(_tmp);
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            final String _tmpSearch;
            _tmpSearch = _cursor.getString(_cursorIndexOfSearch);
            _item = new SearchHistoryEntry(_tmpCreationDate,_tmpServiceId,_tmpSearch);
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
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
