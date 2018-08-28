package com.mngh.floattube.database.history.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.support.annotation.Nullable;

import com.mngh.floattube.database.history.model.SearchHistoryEntry;
import com.mngh.floattube.database.history.model.SearchHistoryEntry;

import com.mngh.floattube.database.BasicDAO;
import com.mngh.floattube.database.history.model.SearchHistoryEntry;

import java.util.List;

import io.reactivex.Flowable;

import static com.mngh.floattube.database.history.model.SearchHistoryEntry.CREATION_DATE;
import static com.mngh.floattube.database.history.model.SearchHistoryEntry.ID;
import static com.mngh.floattube.database.history.model.SearchHistoryEntry.SEARCH;
import static com.mngh.floattube.database.history.model.SearchHistoryEntry.SERVICE_ID;
import static com.mngh.floattube.database.history.model.SearchHistoryEntry.TABLE_NAME;

@Dao
public interface SearchHistoryDAO extends HistoryDAO<SearchHistoryEntry> {

    String ORDER_BY_CREATION_DATE = " ORDER BY " + SearchHistoryEntry.CREATION_DATE + " DESC";

    @Query("SELECT * FROM " + SearchHistoryEntry.TABLE_NAME +
            " WHERE " + SearchHistoryEntry.ID + " = (SELECT MAX(" + SearchHistoryEntry.ID + ") FROM " + SearchHistoryEntry.TABLE_NAME + ")")
    @Nullable
    SearchHistoryEntry getLatestEntry();

    @Query("DELETE FROM " + SearchHistoryEntry.TABLE_NAME)
    @Override
    int deleteAll();

    @Query("DELETE FROM " + SearchHistoryEntry.TABLE_NAME + " WHERE " + SearchHistoryEntry.SEARCH + " = :query")
    int deleteAllWhereQuery(String query);

    @Query("SELECT * FROM " + SearchHistoryEntry.TABLE_NAME + ORDER_BY_CREATION_DATE)
    @Override
    Flowable<List<SearchHistoryEntry>> getAll();

    @Query("SELECT * FROM " + SearchHistoryEntry.TABLE_NAME + " GROUP BY " + SearchHistoryEntry.SEARCH + ORDER_BY_CREATION_DATE + " LIMIT :limit")
    Flowable<List<SearchHistoryEntry>> getUniqueEntries(int limit);

    @Query("SELECT * FROM " + SearchHistoryEntry.TABLE_NAME + " WHERE " + SearchHistoryEntry.SERVICE_ID + " = :serviceId" + ORDER_BY_CREATION_DATE)
    @Override
    Flowable<List<SearchHistoryEntry>> listByService(int serviceId);

    @Query("SELECT * FROM " + SearchHistoryEntry.TABLE_NAME + " WHERE " + SearchHistoryEntry.SEARCH + " LIKE :query || '%' GROUP BY " + SearchHistoryEntry.SEARCH + " LIMIT :limit")
    Flowable<List<SearchHistoryEntry>> getSimilarEntries(String query, int limit);
}
