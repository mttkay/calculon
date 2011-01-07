package com.github.calculon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;

import com.github.calculon.annotation.Annotations;
import com.github.calculon.assertion.CalculonAssertions;

public abstract class CalculonUnitTest<ActivityT extends Activity> extends
        ActivityUnitTestCase<ActivityT> implements CalculonTestCase {

    private Class<ActivityT> mActivityClass = null;

    public CalculonUnitTest(Class<ActivityT> activityClass) {
        super(activityClass);
        mActivityClass = activityClass;
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        CalculonAssertions.register(this);
    }

    protected void setUpIntent(Intent intent) {
        // to be implemented by subclasses
    }

    protected void startActivity() {
        startActivity(null);
        getInstrumentation().waitForIdleSync();
    }

    protected void startActivity(Bundle extras) {
        Intent intent = new Intent(getInstrumentation().getTargetContext(), mActivityClass);
        setUpIntent(intent);
        if (extras != null) {
            intent.putExtras(extras);
        }
        Annotations.validateExpectedExtras(getClass(), intent);
        startActivity(intent, null, null);
        getInstrumentation().waitForIdleSync();
    }
}
