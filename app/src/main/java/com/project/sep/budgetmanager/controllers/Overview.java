package com.project.sep.budgetmanager.controllers;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.project.sep.budgetmanager.R;
import com.project.sep.budgetmanager.databasemodels.Transaction;
import com.project.sep.budgetmanager.databasemodels.TransactionDA;

import java.util.ArrayList;
import java.util.List;


public class Overview extends ActionBarActivity {

    private TransactionDA transactionDA;
    TableLayout table_layout;
    List<Transaction> transactionList =new ArrayList<Transaction>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        this.transactionDA=new TransactionDA(this);
        table_layout = (TableLayout) findViewById(R.id.tableLayout1);

        transactionList=transactionDA.getAllTransactions();

        buildTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Method to show table of transaction history
    private void buildTable() {

        int length=transactionList.size();

        // outer for loop
        for (int i = 0; i < length; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            String rowText=transactionList.get(i).getDate()+"\t"+transactionList.get(i).getIncomeorexpense()+"\t"+transactionList.get(i).getCategory()+"\t"+transactionList.get(i).getAmount();
            TextView tv = new TextView(this);
            tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.LEFT);
            tv.setTextSize(15);
            tv.setPadding(0, 5, 0, 5);

            tv.setText(rowText);
            row.addView(tv);


            table_layout.addView(row);


        }

    }


}
