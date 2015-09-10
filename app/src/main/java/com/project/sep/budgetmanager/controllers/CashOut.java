package com.project.sep.budgetmanager.controllers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.sep.budgetmanager.R;
import com.project.sep.budgetmanager.databasemodels.Transaction;
import com.project.sep.budgetmanager.databasemodels.TransactionDA;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CashOut extends FragmentActivity {
    Date curDate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String DateToStr = format.format(curDate);
    EditText editDate;
    private EditText amountText;
    private EditText noteText;
    private Spinner categorySpinner;
    private Button saveBtn;
    private TransactionDA transactionDA;
    ImageButton addBut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out);

        editDate = (EditText) findViewById(R.id.date);
        editDate.setText(DateToStr);
        addBut= (ImageButton) findViewById(R.id.imageButton2);
        categorySpinner = (Spinner) findViewById(R.id.categoryspinner);
        amountText =(EditText) findViewById(R.id.amount);
        noteText = (EditText) findViewById(R.id.notes);
        saveBtn = (Button) findViewById(R.id.savebutton);

        this.transactionDA=new TransactionDA(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cash_out, menu);
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

    public void showAddCategory(View v){
        Intent i;
        i=new Intent(CashOut.this,AddCategory.class);
        startActivity(i);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
        //newFragment.show(getSupportFragmentManager(), "datePicker");
        //setNewDate();

    }

    public void populateSetDate(int year, int month, int day) {
        editDate = (EditText) findViewById(R.id.date);
        editDate.setText(year + "-" + (month + 1) + "-" + day);
    }


    //Method which run when save button is pressed
    //Insert data to the database
    public void onClickSave(View v) {

        try {
            Editable date = editDate.getText();
            String category = (String) categorySpinner.getSelectedItem();
            //Editable amount = (Float) amountText.getText();
            float amount = Float.valueOf(amountText.getText().toString());
            Editable notes = noteText.getText();
            if(amount<=0){
                Toast.makeText(CashOut.this, "Please enter the amount", Toast.LENGTH_SHORT).show();
            }else {
                // add the transaction to database
                Transaction createdTransaction = transactionDA.createTransaction(date.toString(), "expense", category, amount, notes.toString());
                Toast.makeText(CashOut.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                transactionDA.close();
                Intent i;
                i=new Intent(CashOut.this,BudgetManagerHome.class);
                startActivity(i);
            }

        }catch(Exception ex){
            Toast.makeText(CashOut.this, "Error while saving.", Toast.LENGTH_SHORT).show();
        }

    }

    public void showToast(View v){
        float amount=0;
        amount = Float.valueOf(amountText.getText().toString());
        if(amount<=0){
            Toast.makeText(CashOut.this, "Please enter the amount", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(CashOut.this, "Successfully Saved", Toast.LENGTH_LONG).show();
            Intent i;
            i=new Intent(CashOut.this,BudgetManagerHome.class);
            startActivity(i);
        }
    }

    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public DatePickerFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            populateSetDate(year,month,day);
        }


    }



}
