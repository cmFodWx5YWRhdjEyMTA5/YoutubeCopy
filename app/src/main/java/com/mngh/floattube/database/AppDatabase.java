package com.mngh.floattube.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.mngh.floattube.database.history.dao.SearchHistoryDAO;
import com.mngh.floattube.database.history.dao.StreamHistoryDAO;
import com.mngh.floattube.database.history.model.SearchHistoryEntry;
import com.mngh.floattube.database.history.model.StreamHistoryEntity;
import com.mngh.floattube.database.playlist.dao.PlaylistDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistRemoteDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistStreamDAO;
import com.mngh.floattube.database.playlist.model.PlaylistEntity;
import com.mngh.floattube.database.playlist.model.PlaylistRemoteEntity;
import com.mngh.floattube.database.playlist.model.PlaylistStreamEntity;
import com.mngh.floattube.database.stream.dao.StreamDAO;
import com.mngh.floattube.database.stream.dao.StreamStateDAO;
import com.mngh.floattube.database.stream.model.StreamEntity;
import com.mngh.floattube.database.stream.model.StreamStateEntity;
import com.mngh.floattube.database.subscription.SubscriptionDAO;
import com.mngh.floattube.database.subscription.SubscriptionEntity;
import com.mngh.floattube.database.history.dao.SearchHistoryDAO;
import com.mngh.floattube.database.history.dao.StreamHistoryDAO;
import com.mngh.floattube.database.history.model.SearchHistoryEntry;
import com.mngh.floattube.database.history.model.StreamHistoryEntity;
import com.mngh.floattube.database.playlist.dao.PlaylistDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistRemoteDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistStreamDAO;
import com.mngh.floattube.database.playlist.model.PlaylistEntity;
import com.mngh.floattube.database.playlist.model.PlaylistRemoteEntity;
import com.mngh.floattube.database.playlist.model.PlaylistStreamEntity;
import com.mngh.floattube.database.stream.dao.StreamDAO;
import com.mngh.floattube.database.stream.dao.StreamStateDAO;
import com.mngh.floattube.database.stream.model.StreamEntity;
import com.mngh.floattube.database.stream.model.StreamStateEntity;
import com.mngh.floattube.database.subscription.SubscriptionDAO;
import com.mngh.floattube.database.subscription.SubscriptionEntity;

import com.mngh.floattube.database.history.dao.SearchHistoryDAO;
import com.mngh.floattube.database.history.dao.StreamHistoryDAO;
import com.mngh.floattube.database.history.model.SearchHistoryEntry;
import com.mngh.floattube.database.history.model.StreamHistoryEntity;
import com.mngh.floattube.database.playlist.dao.PlaylistDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistRemoteDAO;
import com.mngh.floattube.database.playlist.dao.PlaylistStreamDAO;
import com.mngh.floattube.database.playlist.model.PlaylistEntity;
import com.mngh.floattube.database.playlist.model.PlaylistRemoteEntity;
import com.mngh.floattube.database.playlist.model.PlaylistStreamEntity;
import com.mngh.floattube.database.stream.dao.StreamDAO;
import com.mngh.floattube.database.stream.dao.StreamStateDAO;
import com.mngh.floattube.database.stream.model.StreamEntity;
import com.mngh.floattube.database.stream.model.StreamStateEntity;
import com.mngh.floattube.database.subscription.SubscriptionDAO;
import com.mngh.floattube.database.subscription.SubscriptionEntity;

import static com.mngh.floattube.database.Migrations.DB_VER_12_0;

@TypeConverters({Converters.class})
@Database(
        entities = {
                SubscriptionEntity.class, SearchHistoryEntry.class,
                StreamEntity.class, StreamHistoryEntity.class, StreamStateEntity.class,
                PlaylistEntity.class, PlaylistStreamEntity.class, PlaylistRemoteEntity.class
        },
        version = Migrations.DB_VER_12_0,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "newpipe.db";

    public abstract SubscriptionDAO subscriptionDAO();

    public abstract SearchHistoryDAO searchHistoryDAO();

    public abstract StreamDAO streamDAO();

    public abstract StreamHistoryDAO streamHistoryDAO();

    public abstract StreamStateDAO streamStateDAO();

    public abstract PlaylistDAO playlistDAO();

    public abstract PlaylistStreamDAO playlistStreamDAO();

    public abstract PlaylistRemoteDAO playlistRemoteDAO();
}
