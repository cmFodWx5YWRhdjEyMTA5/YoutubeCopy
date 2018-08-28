package com.mngh.floattube.database.history.dao;

import com.mngh.floattube.database.BasicDAO;

public interface HistoryDAO<T> extends BasicDAO<T> {
    T getLatestEntry();
}
