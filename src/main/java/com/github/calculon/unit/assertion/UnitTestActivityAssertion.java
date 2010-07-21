package com.github.calculon.unit.assertion;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonUnitTest;

public class UnitTestActivityAssertion<ActivityT extends Activity> extends
        UnitTestUserInputAssertionBase<Activity, ActivityT> {

    public UnitTestActivityAssertion(CalculonUnitTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation) {
        super(testCase, activity, instrumentation);
        this.target = activity;
    }

    public void viewExists(int... viewIds) {
        for (int id : viewIds) {
            assertNotNull("View " + id + " is null, but expected to exist", activity
                .findViewById(id));
        }
    }

    public void inPortraitMode() {
        assertEquals("Expected portrait mode, but was landscape", 0, activity.getWindowManager()
            .getDefaultDisplay().getOrientation());
    }

    public void inLandscapeMode() {
        assertEquals("Expected landscape mode, but was portrait", 1, activity.getWindowManager()
            .getDefaultDisplay().getOrientation());
    }
}
