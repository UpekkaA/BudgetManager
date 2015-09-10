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
 * Created by Upekka on 9/8/2015.
 */
public class BudgetDA {
    public static final String TAG = "BudgetDA";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_YEAR, DBHelper.COLUMN_MONTH,DBHelper.COLUMN_BUDGET_INCOME,DBHelper.COLUMN_BUDGET_EXPENSE};

    public BudgetDA(Context context) {
        mDbHelper = new DBHelper(context);
        this.mContext = context;
        // open the database
        try {
            open();
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

    public BudgetB saveBudgetInfo(int year,String month,float income,float expense) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_YEAR, year);
        values.put(DBHelper.COLUMN_MONTH, month);
        values.put(DBHelper.COLUMN_BUDGET_INCOME, income);
        values.put(DBHelper.COLUMN_BUDGET_EXPENSE, expense);

        long insertId = mDatabase.insert(DBHelper.TABLE_BUDGET, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_BUDGET, mAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        BudgetB newBudget = cursorToBudget(cursor);
        cursor.close();
        return newBudget;
    }



    public List<BudgetB> getAllBudgetInfo() {
        List<BudgetB> listBudgets = new ArrayList<BudgetB>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_BUDGET,
                mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BudgetB budget = cursorToBudget(cursor);
            listBudgets.add(budget);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listBudgets;
    }


    private BudgetB cursorToBudget(Cursor cursor) {
        BudgetB budget = new BudgetB();
        budget.setYear(cursor.getInt(0));
        budget.setMonth(cursor.getString(1));
        budget.setIncome(cursor.getFloat(2));
        budget.setExpense(cursor.getFloat(3));

        return budget;
    }
}
