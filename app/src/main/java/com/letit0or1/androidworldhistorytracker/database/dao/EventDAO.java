package com.letit0or1.androidworldhistorytracker.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.letit0or1.androidworldhistorytracker.entity.Event;

import java.sql.SQLException;
import java.util.List;


public class EventDAO extends BaseDaoImpl<Event, Integer> {

    public EventDAO(ConnectionSource connectionSource, Class<Event> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Event> getAll() throws SQLException {
        return this.queryForAll();
    }

    public void addAll(List<Event> dataset) throws SQLException {
        TableUtils.clearTable(getConnectionSource(), Event.class);
        create(dataset);
    }

    public void clearAll() throws SQLException {
        TableUtils.clearTable(getConnectionSource(), Event.class);
    }
}
