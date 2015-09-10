package com.project.sep.budgetmanager;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import com.project.sep.budgetmanager.controllers.BudgetManagerHome;

/**
 * Created by Upekka on 7/29/2015.
 */

//Unit test goal is Verifying that LaunchActivity fires an Intent when a button is pushed clicked.
public class LaunchActivityTest extends ActivityUnitTestCase<BudgetManagerHome> {

    Intent mLaunchIntent;
    public LaunchActivityTest(Class<BudgetManagerHome> activityClass) {
        super(activityClass);

    }


    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), BudgetManagerHome.class);
        startActivity(mLaunchIntent, null, null);
        final Button launchNextButton =
                (Button) getActivity()
                        .findViewById(R.id.inbutton);
        launchNextButton.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
        assertTrue(isFinishCalled());

    }
}
