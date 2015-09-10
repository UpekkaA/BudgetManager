package com.project.sep.budgetmanager.controllers;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.sep.budgetmanager.R;
import com.project.sep.budgetmanager.databasemodels.BudgetB;
import com.project.sep.budgetmanager.databasemodels.BudgetDA;
import com.project.sep.budgetmanager.databasemodels.Transaction;


public class Budget extends ActionBarActivity {
    private Spinner yearChooser;
    private Spinner monthChooser;
    private EditText income;
    private EditText expense;
    private Button saveBtn;
    private BudgetDA budgetDA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        yearChooser = (Spinner) findViewById(R.id.yearChooser);
        monthChooser = (Spinner) findViewById(R.id.monthChooser);
        income =(EditText) findViewById(R.id.income);
        expense = (EditText) findViewById(R.id.expense);
        saveBtn = (Button) findViewById(R.id.setBudget);

        this.budgetDA = new BudgetDA(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget, menu);
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

    public void showToast(View v){
        Toast.makeText(Budget.this, "Successfully Saved",Toast.LENGTH_SHORT).show();
    }

    public void onClickSave(View v) {

        try {
            int year = Integer.valueOf((String) yearChooser.getSelectedItem());
            String month = (String) monthChooser.getSelectedItem();
            float inc = Float.valueOf(income.getText().toString());
            float exp = Float.valueOf(income.getText().toString());

            if(inc<=0 || exp<=0 ){
                Toast.makeText(Budget.this, "Please enter your budgeted income and expense", Toast.LENGTH_SHORT).show();
            }else {
                // add the budget information to  to database
                BudgetB setBudget = budgetDA.saveBudgetInfo(year, month, inc, exp);
                Toast.makeText(Budget.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                budgetDA.close();
                Intent i;
                i=new Intent(Budget.this,BudgetManagerHome.class);
                startActivity(i);
            }

        }catch(Exception ex){
            Toast.makeText(Budget.this, "Error while saving.", Toast.LENGTH_SHORT).show();
        }

    }
}
