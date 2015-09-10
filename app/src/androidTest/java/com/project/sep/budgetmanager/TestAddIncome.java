package com.project.sep.budgetmanager;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.project.sep.budgetmanager.controllers.CashIn;
import com.robotium.solo.Solo;

/**
 * Created by Upekka on 9/10/2015.
 */
public class TestAddIncome extends ActivityInstrumentationTestCase2 <CashIn>{

    private Solo solo;
    public TestAddIncome() {
        super(CashIn.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void test_add() throws Exception{
        solo.unlockScreen();

        Button btn = (Button)solo.getView(R.id.savebutton);
        EditText txt = (EditText)solo.getView(R.id.amount);
        solo.enterText(txt,"100.0");
        solo.clickOnView(btn);
        solo.waitForText("Successfully Saved",1,5000);


    }
}
