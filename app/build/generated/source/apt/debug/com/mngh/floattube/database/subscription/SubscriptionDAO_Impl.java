package com.mngh.floattube.database.subscription;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

public class SubscriptionDAO_Impl extends SubscriptionDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSubscriptionEntity;

  private final EntityInsertionAdapter __insertionAdapterOfSubscriptionEntity_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfSubscriptionEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfSubscriptionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public SubscriptionDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSubscriptionEntity = new EntityInsertionAdapter<SubscriptionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR FAIL INTO `subscriptions`(`uid`,`service_id`,`url`,`name`,`avatar_url`,`subscriber_count`,`description`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubscriptionEntity value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getServiceId());
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getAvatarUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAvatarUrl());
        }
        if (value.getSubscriberCount() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getSubscriberCount());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
      }
    };
    this.__insertionAdapterOfSubscriptionEntity_1 = new EntityInsertionAdapter<SubscriptionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `subscriptions`(`uid`,`service_id`,`url`,`name`,`avatar_url`,`subscriber_count`,`description`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubscriptionEntity value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getServiceId());
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getAvatarUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAvatarUrl());
        }
        if (value.getSubscriberCount() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getSubscriberCount());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
      }
    };
    this.__deletionAdapterOfSubscriptionEntity = new EntityDeletionOrUpdateAdapter<SubscriptionEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `subscriptions` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubscriptionEntity value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__updateAdapterOfSubscriptionEntity = new EntityDeletionOrUpdateAdapter<SubscriptionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `subscriptions` SET `uid` = ?,`service_id` = ?,`url` = ?,`name` = ?,`avatar_url` = ?,`subscriber_count` = ?,`description` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubscriptionEntity value) {
        stmt.bindLong(1, value.getUid());
        stmt.bindLong(2, value.getServiceId());
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getAvatarUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAvatarUrl());
        }
        if (value.getSubscriberCount() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getSubscriberCount());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        stmt.bindLong(8, value.getUid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM subscriptions";
        return _query;
      }
    };
  }

  @Override
  public long insert(final SubscriptionEntity entity) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfSubscriptionEntity.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final SubscriptionEntity... entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfSubscriptionEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final Collection<SubscriptionEntity> entities) {
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfSubscriptionEntity.insertAndReturnIdsList(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  Long insertInternal(final SubscriptionEntity entities) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfSubscriptionEntity_1.insertAndReturnId(entities);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final SubscriptionEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfSubscriptionEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Collection<SubscriptionEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfSubscriptionEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final SubscriptionEntity entity) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfSubscriptionEntity.handle(entity);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Collection<SubscriptionEntity> entities) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfSubscriptionEntity.handleMultiple(entities);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<SubscriptionEntity> upsertAll(List<SubscriptionEntity> entities) {
    __db.beginTransaction();
    try {
      List<SubscriptionEntity> _result = super.upsertAll(entities);
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
  public Flowable<List<SubscriptionEntity>> getAll() {
    final String _sql = "SELECT * FROM subscriptions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"subscriptions"}, new Callable<List<SubscriptionEntity>>() {
      public List<SubscriptionEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfAvatarUrl = _cursor.getColumnIndexOrThrow("avatar_url");
          final int _cursorIndexOfSubscriberCount = _cursor.getColumnIndexOrThrow("subscriber_count");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final List<SubscriptionEntity> _result = new ArrayList<SubscriptionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SubscriptionEntity _item;
            _item = new SubscriptionEntity();
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            _item.setServiceId(_tmpServiceId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _item.setUrl(_tmpUrl);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpAvatarUrl;
            _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            _item.setAvatarUrl(_tmpAvatarUrl);
            final Long _tmpSubscriberCount;
            if (_cursor.isNull(_cursorIndexOfSubscriberCount)) {
              _tmpSubscriberCount = null;
            } else {
              _tmpSubscriberCount = _cursor.getLong(_cursorIndexOfSubscriberCount);
            }
            _item.setSubscriberCount(_tmpSubscriberCount);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
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
  public Flowable<List<SubscriptionEntity>> listByService(int serviceId) {
    final String _sql = "SELECT * FROM subscriptions WHERE service_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, serviceId);
    return RxRoom.createFlowable(__db, new String[]{"subscriptions"}, new Callable<List<SubscriptionEntity>>() {
      public List<SubscriptionEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfAvatarUrl = _cursor.getColumnIndexOrThrow("avatar_url");
          final int _cursorIndexOfSubscriberCount = _cursor.getColumnIndexOrThrow("subscriber_count");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final List<SubscriptionEntity> _result = new ArrayList<SubscriptionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SubscriptionEntity _item;
            _item = new SubscriptionEntity();
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            _item.setServiceId(_tmpServiceId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _item.setUrl(_tmpUrl);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpAvatarUrl;
            _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            _item.setAvatarUrl(_tmpAvatarUrl);
            final Long _tmpSubscriberCount;
            if (_cursor.isNull(_cursorIndexOfSubscriberCount)) {
              _tmpSubscriberCount = null;
            } else {
              _tmpSubscriberCount = _cursor.getLong(_cursorIndexOfSubscriberCount);
            }
            _item.setSubscriberCount(_tmpSubscriberCount);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
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
  public Flowable<List<SubscriptionEntity>> getSubscription(int serviceId, String url) {
    final String _sql = "SELECT * FROM subscriptions WHERE url LIKE ? AND service_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, serviceId);
    return RxRoom.createFlowable(__db, new String[]{"subscriptions"}, new Callable<List<SubscriptionEntity>>() {
      public List<SubscriptionEntity> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
          final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
          final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfAvatarUrl = _cursor.getColumnIndexOrThrow("avatar_url");
          final int _cursorIndexOfSubscriberCount = _cursor.getColumnIndexOrThrow("subscriber_count");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final List<SubscriptionEntity> _result = new ArrayList<SubscriptionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SubscriptionEntity _item;
            _item = new SubscriptionEntity();
            final long _tmpUid;
            _tmpUid = _cursor.getLong(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            final int _tmpServiceId;
            _tmpServiceId = _cursor.getInt(_cursorIndexOfServiceId);
            _item.setServiceId(_tmpServiceId);
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            _item.setUrl(_tmpUrl);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpAvatarUrl;
            _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            _item.setAvatarUrl(_tmpAvatarUrl);
            final Long _tmpSubscriberCount;
            if (_cursor.isNull(_cursorIndexOfSubscriberCount)) {
              _tmpSubscriberCount = null;
            } else {
              _tmpSubscriberCount = _cursor.getLong(_cursorIndexOfSubscriberCount);
            }
            _item.setSubscriberCount(_tmpSubscriberCount);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
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
  Long getSubscriptionIdInternal(int serviceId, String url) {
    final String _sql = "SELECT uid FROM subscriptions WHERE url LIKE ? AND service_id = ?";
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
