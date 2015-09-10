package com.project.sep.budgetmanager.databasemodels;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Upekka on 7/28/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";

    // columns of the transaction table
    public static final String TABLE_TRANSACTION = "transactiontable";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TYPE = "incomeorexpense";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_NOTE = "note";

    // columns of the category table
    public static final String TABLE_CATEGORY = "categorylist";
    public static final String COLUMN_CAT_TYPE = "incomeorexpense";
    public static final String COLUMN_NAME = "category";

    // columns of the Budget table
    public static final String TABLE_BUDGET = "budget";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_BUDGET_INCOME = "income";
    public static final String COLUMN_BUDGET_EXPENSE = "expense";


    private static final String DATABASE_NAME = "budgetmanager.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the transaction table creation
    private static final String SQL_CREATE_TABLE_TRANSACTION = "CREATE TABLE " + TABLE_TRANSACTION + " ("
            + COLUMN_DATE + " TEXT NOT NULL, "
            + COLUMN_TYPE + " TEXT NOT NULL, "
            + COLUMN_CATEGORY + " TEXT NOT NULL, "
            + COLUMN_AMOUNT + " REAL NOT NULL, "
            + COLUMN_NOTE + " TEXT "
            +");";

    // SQL statement of the category table creation
    private static final String SQL_CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " ("
            + COLUMN_CAT_TYPE + " TEXT NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL "
            +");";

    // SQL statement of the Budget table creation
    private static final String SQL_CREATE_TABLE_BUDGET = "CREATE TABLE " + TABLE_BUDGET + " ("
            + COLUMN_YEAR + " INTEGER NOT NULL, "
            + COLUMN_MONTH + " TEXT NOT NULL, "
            + COLUMN_BUDGET_INCOME + " REAL NOT NULL,"
            + COLUMN_BUDGET_EXPENSE + " REAL NOT NULL"
            +");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TRANSACTION);
        db.execSQL(SQL_CREATE_TABLE_CATEGORY);
        db.execSQL(SQL_CREATE_TABLE_BUDGET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
}
