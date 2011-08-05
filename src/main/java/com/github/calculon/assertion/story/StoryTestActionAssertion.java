package com.github.calculon.assertion.story;

import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.InstrumentationTestCase;

import com.github.calculon.assertion.ActionAssertion;

public class StoryTestActionAssertion<AssertionT> extends ActionAssertion<AssertionT> {

    public StoryTestActionAssertion(AssertionT fromAssertion, InstrumentationTestCase testCase,
            Activity activity, Runnable action, boolean runOnMainThread) {
        super(fromAssertion, testCase, activity, action, runOnMainThread);
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

    @Override
    public void startsWithIntentAction(String intentAction) {
        ActivityMonitor monitor = instrumentation.addMonitor(intentAction, null, false);

        performPendingAction();

        assertTrue(instrumentation.checkMonitorHit(monitor, 1));

        Activity lastActivity = monitor.getLastActivity();
        getStoryTestCase().setCurrentActivity(lastActivity);

    }
}
