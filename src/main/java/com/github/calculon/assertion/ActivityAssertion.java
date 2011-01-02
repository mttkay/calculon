package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import android.app.Activity;
import android.test.InstrumentationTestCase;

public class ActivityAssertion extends TargetedAssertion<Activity> {

    public ActivityAssertion(InstrumentationTestCase testCase, Activity activity) {
        super(testCase, activity, activity);
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

    public ActionAssertion keyPress(final int... keyCodes) {
        return AssertionResolver.actionAssertion(testCase, activity, new Runnable() {
            public void run() {
                for (int code : keyCodes) {
                    instrumentation.sendKeyDownUpSync(code);
                }

                instrumentation.waitForIdleSync();
            }
        }, false);
    }
}
