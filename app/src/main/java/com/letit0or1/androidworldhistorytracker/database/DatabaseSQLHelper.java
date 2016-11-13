package com.letit0or1.androidworldhistorytracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.fitness.data.Goal;
import com.google.android.gms.wallet.LineItem;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.letit0or1.androidworldhistorytracker.database.dao.EventDAO;
import com.letit0or1.androidworldhistorytracker.entity.Event;

import java.sql.SQLException;


public class DatabaseSQLHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseSQLHelper.class.getSimpleName();

    //имя файла базы данных который будет храниться в /data/data/APPNAME/DATABASE_NAME.db
    private static final String DATABASE_NAME = "worldhistorytracker.db";

    //с каждым увеличением версии, при нахождении в устройстве БД с предыдущей версией будет выполнен метод onUpgrade();
    private static final int DATABASE_VERSION = 1;

    //ссылки на DAO соответсвующие сущностям, хранимым в БД
    private EventDAO eventDAO = null;

    public DatabaseSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Выполняется, когда файл с БД не найден на устройстве
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Event.class);
        } catch (SQLException e) {
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    //Выполняется, когда БД имеет версию отличную от текущей
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            //Так делают ленивые, гораздо предпочтительнее не удаляя БД аккуратно вносить изменения
            TableUtils.dropTable(connectionSource, Event.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver " + oldVer);
            throw new RuntimeException(e);
        }
    }

    //синглтон для RoleDAO
    public EventDAO getEventDAO() throws SQLException {
        if (eventDAO == null) {
            eventDAO = new EventDAO(getConnectionSource(), Event.class);
        }
        return eventDAO;
    }

    //выполняется при закрытии приложения
    @Override
    public void close() {
        super.close();
        eventDAO = null;
    }

}
