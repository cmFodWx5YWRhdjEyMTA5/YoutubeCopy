package com.mngh.floattube.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.mngh.floattube.database.history.dao.SearchHistoryDAO;
import com.mngh.floattube.database.history.dao.SearchHistoryDAO_Impl;
import com.mngh.floattube.database.history.dao.StreamHistoryDAO;
import com.mngh.floattube.database.history.dao.StreamHistoryDAO_Impl;
import com.mngh.floattube.database.playlist.dao.PlaylistDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistDAO_Impl;
import com.mngh.floattube.database.playlist.dao.PlaylistRemoteDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistRemoteDAO_Impl;
import com.mngh.floattube.database.playlist.dao.PlaylistStreamDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistStreamDAO_Impl;
import com.mngh.floattube.database.stream.dao.StreamDAO;
import com.mngh.floattube.database.stream.dao.StreamDAO_Impl;
import com.mngh.floattube.database.stream.dao.StreamStateDAO;
import com.mngh.floattube.database.stream.dao.StreamStateDAO_Impl;
import com.mngh.floattube.database.subscription.SubscriptionDAO;
import com.mngh.floattube.database.subscription.SubscriptionDAO_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class AppDatabase_Impl extends AppDatabase {
  private volatile SubscriptionDAO _subscriptionDAO;

  private volatile SearchHistoryDAO _searchHistoryDAO;

  private volatile StreamDAO _streamDAO;

  private volatile StreamHistoryDAO _streamHistoryDAO;

  private volatile StreamStateDAO _streamStateDAO;

  private volatile PlaylistDAO _playlistDAO;

  private volatile PlaylistStreamDAO _playlistStreamDAO;

  private volatile PlaylistRemoteDAO _playlistRemoteDAO;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `subscriptions` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `service_id` INTEGER NOT NULL, `url` TEXT, `name` TEXT, `avatar_url` TEXT, `subscriber_count` INTEGER, `description` TEXT)");
        _db.execSQL("CREATE UNIQUE INDEX `index_subscriptions_service_id_url` ON `subscriptions` (`service_id`, `url`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `search_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `creation_date` INTEGER, `service_id` INTEGER NOT NULL, `search` TEXT)");
        _db.execSQL("CREATE  INDEX `index_search_history_search` ON `search_history` (`search`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `streams` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `service_id` INTEGER NOT NULL, `url` TEXT, `title` TEXT, `stream_type` TEXT, `duration` INTEGER, `uploader` TEXT, `thumbnail_url` TEXT)");
        _db.execSQL("CREATE UNIQUE INDEX `index_streams_service_id_url` ON `streams` (`service_id`, `url`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `stream_history` (`stream_id` INTEGER NOT NULL, `access_date` INTEGER NOT NULL, `repeat_count` INTEGER NOT NULL, PRIMARY KEY(`stream_id`, `access_date`), FOREIGN KEY(`stream_id`) REFERENCES `streams`(`uid`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE  INDEX `index_stream_history_stream_id` ON `stream_history` (`stream_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `stream_state` (`stream_id` INTEGER NOT NULL, `progress_time` INTEGER NOT NULL, PRIMARY KEY(`stream_id`), FOREIGN KEY(`stream_id`) REFERENCES `streams`(`uid`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `playlists` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `thumbnail_url` TEXT)");
        _db.execSQL("CREATE  INDEX `index_playlists_name` ON `playlists` (`name`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `playlist_stream_join` (`playlist_id` INTEGER NOT NULL, `stream_id` INTEGER NOT NULL, `join_index` INTEGER NOT NULL, PRIMARY KEY(`playlist_id`, `join_index`), FOREIGN KEY(`playlist_id`) REFERENCES `playlists`(`uid`) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED, FOREIGN KEY(`stream_id`) REFERENCES `streams`(`uid`) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED)");
        _db.execSQL("CREATE UNIQUE INDEX `index_playlist_stream_join_playlist_id_join_index` ON `playlist_stream_join` (`playlist_id`, `join_index`)");
        _db.execSQL("CREATE  INDEX `index_playlist_stream_join_stream_id` ON `playlist_stream_join` (`stream_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `remote_playlists` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `service_id` INTEGER NOT NULL, `name` TEXT, `url` TEXT, `thumbnail_url` TEXT, `uploader` TEXT, `stream_count` INTEGER)");
        _db.execSQL("CREATE  INDEX `index_remote_playlists_name` ON `remote_playlists` (`name`)");
        _db.execSQL("CREATE UNIQUE INDEX `index_remote_playlists_service_id_url` ON `remote_playlists` (`service_id`, `url`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"6f8633820abb824f205135a8b8eeee67\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `subscriptions`");
        _db.execSQL("DROP TABLE IF EXISTS `search_history`");
        _db.execSQL("DROP TABLE IF EXISTS `streams`");
        _db.execSQL("DROP TABLE IF EXISTS `stream_history`");
        _db.execSQL("DROP TABLE IF EXISTS `stream_state`");
        _db.execSQL("DROP TABLE IF EXISTS `playlists`");
        _db.execSQL("DROP TABLE IF EXISTS `playlist_stream_join`");
        _db.execSQL("DROP TABLE IF EXISTS `remote_playlists`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSubscriptions = new HashMap<String, TableInfo.Column>(7);
        _columnsSubscriptions.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsSubscriptions.put("service_id", new TableInfo.Column("service_id", "INTEGER", true, 0));
        _columnsSubscriptions.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        _columnsSubscriptions.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsSubscriptions.put("avatar_url", new TableInfo.Column("avatar_url", "TEXT", false, 0));
        _columnsSubscriptions.put("subscriber_count", new TableInfo.Column("subscriber_count", "INTEGER", false, 0));
        _columnsSubscriptions.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSubscriptions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSubscriptions = new HashSet<TableInfo.Index>(1);
        _indicesSubscriptions.add(new TableInfo.Index("index_subscriptions_service_id_url", true, Arrays.asList("service_id","url")));
        final TableInfo _infoSubscriptions = new TableInfo("subscriptions", _columnsSubscriptions, _foreignKeysSubscriptions, _indicesSubscriptions);
        final TableInfo _existingSubscriptions = TableInfo.read(_db, "subscriptions");
        if (! _infoSubscriptions.equals(_existingSubscriptions)) {
          throw new IllegalStateException("Migration didn't properly handle subscriptions(com.mngh.floattube.database.subscription.SubscriptionEntity).\n"
                  + " Expected:\n" + _infoSubscriptions + "\n"
                  + " Found:\n" + _existingSubscriptions);
        }
        final HashMap<String, TableInfo.Column> _columnsSearchHistory = new HashMap<String, TableInfo.Column>(4);
        _columnsSearchHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsSearchHistory.put("creation_date", new TableInfo.Column("creation_date", "INTEGER", false, 0));
        _columnsSearchHistory.put("service_id", new TableInfo.Column("service_id", "INTEGER", true, 0));
        _columnsSearchHistory.put("search", new TableInfo.Column("search", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSearchHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSearchHistory = new HashSet<TableInfo.Index>(1);
        _indicesSearchHistory.add(new TableInfo.Index("index_search_history_search", false, Arrays.asList("search")));
        final TableInfo _infoSearchHistory = new TableInfo("search_history", _columnsSearchHistory, _foreignKeysSearchHistory, _indicesSearchHistory);
        final TableInfo _existingSearchHistory = TableInfo.read(_db, "search_history");
        if (! _infoSearchHistory.equals(_existingSearchHistory)) {
          throw new IllegalStateException("Migration didn't properly handle search_history(com.mngh.floattube.database.history.model.SearchHistoryEntry).\n"
                  + " Expected:\n" + _infoSearchHistory + "\n"
                  + " Found:\n" + _existingSearchHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsStreams = new HashMap<String, TableInfo.Column>(8);
        _columnsStreams.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsStreams.put("service_id", new TableInfo.Column("service_id", "INTEGER", true, 0));
        _columnsStreams.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        _columnsStreams.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsStreams.put("stream_type", new TableInfo.Column("stream_type", "TEXT", false, 0));
        _columnsStreams.put("duration", new TableInfo.Column("duration", "INTEGER", false, 0));
        _columnsStreams.put("uploader", new TableInfo.Column("uploader", "TEXT", false, 0));
        _columnsStreams.put("thumbnail_url", new TableInfo.Column("thumbnail_url", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStreams = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStreams = new HashSet<TableInfo.Index>(1);
        _indicesStreams.add(new TableInfo.Index("index_streams_service_id_url", true, Arrays.asList("service_id","url")));
        final TableInfo _infoStreams = new TableInfo("streams", _columnsStreams, _foreignKeysStreams, _indicesStreams);
        final TableInfo _existingStreams = TableInfo.read(_db, "streams");
        if (! _infoStreams.equals(_existingStreams)) {
          throw new IllegalStateException("Migration didn't properly handle streams(com.mngh.floattube.database.stream.model.StreamEntity).\n"
                  + " Expected:\n" + _infoStreams + "\n"
                  + " Found:\n" + _existingStreams);
        }
        final HashMap<String, TableInfo.Column> _columnsStreamHistory = new HashMap<String, TableInfo.Column>(3);
        _columnsStreamHistory.put("stream_id", new TableInfo.Column("stream_id", "INTEGER", true, 1));
        _columnsStreamHistory.put("access_date", new TableInfo.Column("access_date", "INTEGER", true, 2));
        _columnsStreamHistory.put("repeat_count", new TableInfo.Column("repeat_count", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStreamHistory = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysStreamHistory.add(new TableInfo.ForeignKey("streams", "CASCADE", "CASCADE",Arrays.asList("stream_id"), Arrays.asList("uid")));
        final HashSet<TableInfo.Index> _indicesStreamHistory = new HashSet<TableInfo.Index>(1);
        _indicesStreamHistory.add(new TableInfo.Index("index_stream_history_stream_id", false, Arrays.asList("stream_id")));
        final TableInfo _infoStreamHistory = new TableInfo("stream_history", _columnsStreamHistory, _foreignKeysStreamHistory, _indicesStreamHistory);
        final TableInfo _existingStreamHistory = TableInfo.read(_db, "stream_history");
        if (! _infoStreamHistory.equals(_existingStreamHistory)) {
          throw new IllegalStateException("Migration didn't properly handle stream_history(com.mngh.floattube.database.history.model.StreamHistoryEntity).\n"
                  + " Expected:\n" + _infoStreamHistory + "\n"
                  + " Found:\n" + _existingStreamHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsStreamState = new HashMap<String, TableInfo.Column>(2);
        _columnsStreamState.put("stream_id", new TableInfo.Column("stream_id", "INTEGER", true, 1));
        _columnsStreamState.put("progress_time", new TableInfo.Column("progress_time", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStreamState = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysStreamState.add(new TableInfo.ForeignKey("streams", "CASCADE", "CASCADE",Arrays.asList("stream_id"), Arrays.asList("uid")));
        final HashSet<TableInfo.Index> _indicesStreamState = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStreamState = new TableInfo("stream_state", _columnsStreamState, _foreignKeysStreamState, _indicesStreamState);
        final TableInfo _existingStreamState = TableInfo.read(_db, "stream_state");
        if (! _infoStreamState.equals(_existingStreamState)) {
          throw new IllegalStateException("Migration didn't properly handle stream_state(com.mngh.floattube.database.stream.model.StreamStateEntity).\n"
                  + " Expected:\n" + _infoStreamState + "\n"
                  + " Found:\n" + _existingStreamState);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylists = new HashMap<String, TableInfo.Column>(3);
        _columnsPlaylists.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsPlaylists.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsPlaylists.put("thumbnail_url", new TableInfo.Column("thumbnail_url", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylists = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaylists = new HashSet<TableInfo.Index>(1);
        _indicesPlaylists.add(new TableInfo.Index("index_playlists_name", false, Arrays.asList("name")));
        final TableInfo _infoPlaylists = new TableInfo("playlists", _columnsPlaylists, _foreignKeysPlaylists, _indicesPlaylists);
        final TableInfo _existingPlaylists = TableInfo.read(_db, "playlists");
        if (! _infoPlaylists.equals(_existingPlaylists)) {
          throw new IllegalStateException("Migration didn't properly handle playlists(com.mngh.floattube.database.playlist.model.PlaylistEntity).\n"
                  + " Expected:\n" + _infoPlaylists + "\n"
                  + " Found:\n" + _existingPlaylists);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylistStreamJoin = new HashMap<String, TableInfo.Column>(3);
        _columnsPlaylistStreamJoin.put("playlist_id", new TableInfo.Column("playlist_id", "INTEGER", true, 1));
        _columnsPlaylistStreamJoin.put("stream_id", new TableInfo.Column("stream_id", "INTEGER", true, 0));
        _columnsPlaylistStreamJoin.put("join_index", new TableInfo.Column("join_index", "INTEGER", true, 2));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylistStreamJoin = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysPlaylistStreamJoin.add(new TableInfo.ForeignKey("playlists", "CASCADE", "CASCADE",Arrays.asList("playlist_id"), Arrays.asList("uid")));
        _foreignKeysPlaylistStreamJoin.add(new TableInfo.ForeignKey("streams", "CASCADE", "CASCADE",Arrays.asList("stream_id"), Arrays.asList("uid")));
        final HashSet<TableInfo.Index> _indicesPlaylistStreamJoin = new HashSet<TableInfo.Index>(2);
        _indicesPlaylistStreamJoin.add(new TableInfo.Index("index_playlist_stream_join_playlist_id_join_index", true, Arrays.asList("playlist_id","join_index")));
        _indicesPlaylistStreamJoin.add(new TableInfo.Index("index_playlist_stream_join_stream_id", false, Arrays.asList("stream_id")));
        final TableInfo _infoPlaylistStreamJoin = new TableInfo("playlist_stream_join", _columnsPlaylistStreamJoin, _foreignKeysPlaylistStreamJoin, _indicesPlaylistStreamJoin);
        final TableInfo _existingPlaylistStreamJoin = TableInfo.read(_db, "playlist_stream_join");
        if (! _infoPlaylistStreamJoin.equals(_existingPlaylistStreamJoin)) {
          throw new IllegalStateException("Migration didn't properly handle playlist_stream_join(com.mngh.floattube.database.playlist.model.PlaylistStreamEntity).\n"
                  + " Expected:\n" + _infoPlaylistStreamJoin + "\n"
                  + " Found:\n" + _existingPlaylistStreamJoin);
        }
        final HashMap<String, TableInfo.Column> _columnsRemotePlaylists = new HashMap<String, TableInfo.Column>(7);
        _columnsRemotePlaylists.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsRemotePlaylists.put("service_id", new TableInfo.Column("service_id", "INTEGER", true, 0));
        _columnsRemotePlaylists.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsRemotePlaylists.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        _columnsRemotePlaylists.put("thumbnail_url", new TableInfo.Column("thumbnail_url", "TEXT", false, 0));
        _columnsRemotePlaylists.put("uploader", new TableInfo.Column("uploader", "TEXT", false, 0));
        _columnsRemotePlaylists.put("stream_count", new TableInfo.Column("stream_count", "INTEGER", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRemotePlaylists = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRemotePlaylists = new HashSet<TableInfo.Index>(2);
        _indicesRemotePlaylists.add(new TableInfo.Index("index_remote_playlists_name", false, Arrays.asList("name")));
        _indicesRemotePlaylists.add(new TableInfo.Index("index_remote_playlists_service_id_url", true, Arrays.asList("service_id","url")));
        final TableInfo _infoRemotePlaylists = new TableInfo("remote_playlists", _columnsRemotePlaylists, _foreignKeysRemotePlaylists, _indicesRemotePlaylists);
        final TableInfo _existingRemotePlaylists = TableInfo.read(_db, "remote_playlists");
        if (! _infoRemotePlaylists.equals(_existingRemotePlaylists)) {
          throw new IllegalStateException("Migration didn't properly handle remote_playlists(com.mngh.floattube.database.playlist.model.PlaylistRemoteEntity).\n"
                  + " Expected:\n" + _infoRemotePlaylists + "\n"
                  + " Found:\n" + _existingRemotePlaylists);
        }
      }
    }, "6f8633820abb824f205135a8b8eeee67");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "subscriptions","search_history","streams","stream_history","stream_state","playlists","playlist_stream_join","remote_playlists");
  }

  @Override
  public SubscriptionDAO subscriptionDAO() {
    if (_subscriptionDAO != null) {
      return _subscriptionDAO;
    } else {
      synchronized(this) {
        if(_subscriptionDAO == null) {
          _subscriptionDAO = new SubscriptionDAO_Impl(this);
        }
        return _subscriptionDAO;
      }
    }
  }

  @Override
  public SearchHistoryDAO searchHistoryDAO() {
    if (_searchHistoryDAO != null) {
      return _searchHistoryDAO;
    } else {
      synchronized(this) {
        if(_searchHistoryDAO == null) {
          _searchHistoryDAO = new SearchHistoryDAO_Impl(this);
        }
        return _searchHistoryDAO;
      }
    }
  }

  @Override
  public StreamDAO streamDAO() {
    if (_streamDAO != null) {
      return _streamDAO;
    } else {
      synchronized(this) {
        if(_streamDAO == null) {
          _streamDAO = new StreamDAO_Impl(this);
        }
        return _streamDAO;
      }
    }
  }

  @Override
  public StreamHistoryDAO streamHistoryDAO() {
    if (_streamHistoryDAO != null) {
      return _streamHistoryDAO;
    } else {
      synchronized(this) {
        if(_streamHistoryDAO == null) {
          _streamHistoryDAO = new StreamHistoryDAO_Impl(this);
        }
        return _streamHistoryDAO;
      }
    }
  }

  @Override
  public StreamStateDAO streamStateDAO() {
    if (_streamStateDAO != null) {
      return _streamStateDAO;
    } else {
      synchronized(this) {
        if(_streamStateDAO == null) {
          _streamStateDAO = new StreamStateDAO_Impl(this);
        }
        return _streamStateDAO;
      }
    }
  }

  @Override
  public PlaylistDAO playlistDAO() {
    if (_playlistDAO != null) {
      return _playlistDAO;
    } else {
      synchronized(this) {
        if(_playlistDAO == null) {
          _playlistDAO = new PlaylistDAO_Impl(this);
        }
        return _playlistDAO;
      }
    }
  }

  @Override
  public PlaylistStreamDAO playlistStreamDAO() {
    if (_playlistStreamDAO != null) {
      return _playlistStreamDAO;
    } else {
      synchronized(this) {
        if(_playlistStreamDAO == null) {
          _playlistStreamDAO = new PlaylistStreamDAO_Impl(this);
        }
        return _playlistStreamDAO;
      }
    }
  }

  @Override
  public PlaylistRemoteDAO playlistRemoteDAO() {
    if (_playlistRemoteDAO != null) {
      return _playlistRemoteDAO;
    } else {
      synchronized(this) {
        if(_playlistRemoteDAO == null) {
          _playlistRemoteDAO = new PlaylistRemoteDAO_Impl(this);
        }
        return _playlistRemoteDAO;
      }
    }
  }
}
