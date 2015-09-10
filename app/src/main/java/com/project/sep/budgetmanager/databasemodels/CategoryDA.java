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
 * Created by Upekka on 8/12/2015.
 */
public class CategoryDA {
    public static final String TAG = "CategoryDA";

    private Context mContext;

    // Database fields
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_CAT_TYPE, DBHelper.COLUMN_NAME};

    public CategoryDA(Context context) {
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

    //Method to save a new category in the database
    public Category createCategory(String incomeorexpense, String category) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_CAT_TYPE, incomeorexpense);
        values.put(DBHelper.COLUMN_NAME, category);

        long insertId = mDatabase.insert(DBHelper.TABLE_CATEGORY, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CATEGORY, mAllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        Category newCategory = cursorToCategory(cursor);
        cursor.close();
        return newCategory;
    }

    public void deleteCategory(Category category) {
        String cat=category.getCategory();
        mDatabase.delete(DBHelper.TABLE_CATEGORY, DBHelper.COLUMN_NAME + " = " + cat, null);
    }

    //Method to get a list of all categories
    public List<Category> getAllCategories() {
        List<Category> listCategories = new ArrayList<Category>();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_CATEGORY,
                mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            listCategories.add(category);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listCategories;
    }


    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setIncomeorexpense(cursor.getString(0));
        category.setCategory(cursor.getString(1));

        return category;
    }
}
