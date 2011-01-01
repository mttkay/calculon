package com.github.calculon.assertion.story;

import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.InstrumentationTestCase;

import com.github.calculon.assertion.ActionAssertion;

public class StoryTestActionAssertion extends ActionAssertion {

    public StoryTestActionAssertion(InstrumentationTestCase testCase, Activity activity,
            Runnable action, boolean runOnMainThread) {
        super(testCase, activity, action, runOnMainThread);
    }

    @Override
    public void starts(Class<? extends Activity> activityClass) {
        ActivityMonitor monitor = instrumentation.addMonitor(activityClass.getCanonicalName(),
                null, false);

        performPendingAction();

        assertTrue(instrumentation.checkMonitorHit(monitor, 1));

        Activity lastActivity = monitor.getLastActivity();
        getStoryTestCase().setCurrentActivity(lastActivity);
    }
}
