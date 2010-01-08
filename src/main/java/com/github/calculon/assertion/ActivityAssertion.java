package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import android.app.Activity;
import android.app.Instrumentation;

public class ActivityAssertion extends UserInputAssertionBase<Activity> {

    public ActivityAssertion(Activity activity, Instrumentation instrumentation) {
        super(activity, instrumentation);
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
