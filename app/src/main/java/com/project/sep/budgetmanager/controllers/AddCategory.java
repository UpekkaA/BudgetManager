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
import com.project.sep.budgetmanager.databasemodels.Category;
import com.project.sep.budgetmanager.databasemodels.CategoryDA;
import com.project.sep.budgetmanager.databasemodels.Transaction;


public class AddCategory extends ActionBarActivity {

    private EditText newCategoryText;
    private Spinner typeSpinner;
    private Button saveBtn;
    private CategoryDA categoryDA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        typeSpinner = (Spinner) findViewById(R.id.typespinner);
        newCategoryText=(EditText) findViewById(R.id.newCategoryText);
        this.categoryDA = new CategoryDA(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_category, menu);
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

    //Insert data to the database
    public void onClickSave(View v) {

        try {
            Editable newcategory = newCategoryText.getText();
            String type = (String) typeSpinner.getSelectedItem();

            if(newcategory.toString().isEmpty()){
                Toast.makeText(AddCategory.this, "Please enter the category", Toast.LENGTH_SHORT).show();
            }else {
                // add the category to database
                Category newCat = categoryDA.createCategory(type, newcategory.toString());
                Toast.makeText(AddCategory.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                categoryDA.close();
                Intent i;
                i=new Intent(AddCategory.this,Settings.class);
                startActivity(i);

            }

        }catch(Exception ex){
            Toast.makeText(AddCategory.this, "Error while saving.", Toast.LENGTH_SHORT).show();
        }

    }
}
