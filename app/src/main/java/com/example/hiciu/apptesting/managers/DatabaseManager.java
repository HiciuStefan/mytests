package com.example.hiciu.apptesting.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.hiciu.apptesting.DaoGenerated.DaoMaster;
import com.example.hiciu.apptesting.DaoGenerated.DaoSession;

/**
 * Created by Hiciu on 7/25/2016.
 */
public class DatabaseManager implements IDatabaseManager{

    private static DatabaseManager instance;


    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    private Context context;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;


    public DatabaseManager(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(this.context, "chatlist-database", null);
    }

    /**
     * @param context The Android {@link android.content.Context}.
     * @return this.instance
     */
    public static DatabaseManager getInstance(Context context) {

        if (instance == null) {
            instance = new DatabaseManager(context);
        }

        return instance;
    }

    /**
     * Query for readable DB
     */
    public void openReadableDb() throws SQLiteException {
        database = mHelper.getReadableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    /**
     * Query for writable DB
     */
    public void openWritableDb() throws SQLiteException {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(this.context, "MYdB", null);
        }
        database = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }
}
