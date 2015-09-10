package com.project.sep.budgetmanager.controllers;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.project.sep.budgetmanager.R;


public class Settings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
        i=new Intent(Settings.this,AddCategory.class);
        startActivity(i);
    }

    public void showToast(View v){
            Toast.makeText(Settings.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
            Intent i;
            i=new Intent(Settings.this,BudgetManagerHome.class);
            startActivity(i);
        }

}
