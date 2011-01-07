package com.github.calculon.test.fixtures;

import android.app.Activity;

import com.github.calculon.CalculonUnitTest;

public class MockCalculonUnitTest<ActivityT extends Activity> extends CalculonUnitTest<ActivityT> {

    public MockCalculonUnitTest(Class<ActivityT> activityClass) {
        super(activityClass);
    }
}
