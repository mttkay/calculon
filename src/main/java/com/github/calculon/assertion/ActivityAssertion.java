package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import android.app.Activity;
import android.test.InstrumentationTestCase;

public class ActivityAssertion extends TargetedAssertion<Activity> {

    public ActivityAssertion(InstrumentationTestCase testCase, Activity activity) {
        super(testCase, activity, activity);
    }

    public void inPortraitMode() {
        assertEquals("Expected portrait mode, but was landscape", 0, activity.getWindowManager()
                .getDefaultDisplay().getOrientation());
    }

    public void inLandscapeMode() {
        assertEquals("Expected landscape mode, but was portrait", 1, activity.getWindowManager()
                .getDefaultDisplay().getOrientation());
    }

    public ActionAssertion<ActivityAssertion> keyPress(final int... keyCodes) {
        return AssertionResolver.actionAssertion(this, testCase, activity, new Runnable() {
            public void run() {
                for (int code : keyCodes) {
                    instrumentation.sendKeyDownUpSync(code);
                }

                instrumentation.waitForIdleSync();
            }
        }, false);
    }
}
