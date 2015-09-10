package com.project.sep.budgetmanager.databasemodels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Upekka on 8/17/2015.
 */
public class TransactionDA {
    public static final String TAG = "TransactionDA";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = {DBHelper.COLUMN_DATE,DBHelper.COLUMN_TYPE,DBHelper.COLUMN_CATEGORY,DBHelper.COLUMN_AMOUNT,DBHelper.COLUMN_NOTE};

    public TransactionDA(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
        // open the database
        try {
            open();
            System.out.println("DB opened");
        }
        catch(SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Transaction createTransaction(String date, String type, String category, float amount, String note) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_DATE, date);
        values.put(DBHelper.COLUMN_TYPE, type);
        values.put(DBHelper.COLUMN_CATEGORY, category);
        values.put(DBHelper.COLUMN_AMOUNT, amount);
        values.put(DBHelper.COLUMN_NOTE, note);

        long insertId = mDatabase.insert(DBHelper.TABLE_TRANSACTION, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_TRANSACTION,
                mAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        Transaction newTransaction = cursorToTransaction(cursor);
        cursor.close();
        System.out.println("Transaction added");

        return newTransaction;
    }


    //Method to get a list of all transactions
    public List<Transaction> getAllTransactions() {
        List<Transaction> listTransactions = new ArrayList<Transaction>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_TRANSACTION,
                mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Transaction transaction = cursorToTransaction(cursor);
            listTransactions.add(transaction);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listTransactions;
    }


    private Transaction cursorToTransaction(Cursor cursor) {
        Transaction transaction = new Transaction();
        transaction.setDate(cursor.getString(0));
        transaction.setIncomeorexpense(cursor.getString(1));
        transaction.setCategory(cursor.getString(2));
        transaction.setAmount(cursor.getFloat(3));
        transaction.setNote(cursor.getString(4));

        return transaction;
    }

}
