package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Context;

import com.github.calculon.CalculonStoryTest;

public class ActionAssertion<ActivityT extends Activity> extends AssertionBase<ActivityT> {

    private Runnable action;

    private boolean runOnMainThread;

    public ActionAssertion(CalculonStoryTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation, Runnable action,
            boolean runOnMainThread) {
        super(testCase, activity, instrumentation);
        this.action = action;
        this.runOnMainThread = runOnMainThread;
    }

    public ViewAssertion<ActivityT> implies(int otherViewId) {
        performPendingAction();
        return new ViewAssertion<ActivityT>(testCase, activity, instrumentation, activity
            .findViewById(otherViewId));
    }

    public <C extends Context> Activity starts(Class<C> contextClass) {
        ActivityMonitor monitor = instrumentation.addMonitor(contextClass.getCanonicalName(), null,
            false);
        
        requirePendingAction();
        performPendingAction();
        
        assertTrue(instrumentation.checkMonitorHit(monitor, 1));

        return monitor.getLastActivity();
    }

    public void finishesActivity() {
        performPendingAction();
        assertTrue(activity.isFinishing());
    }

    protected void requirePendingAction() {
        if (action == null) {
            throw new IllegalStateException(
                "This assertion relies on an action being set which triggers it, such as a click()");
        }
    }

    protected void performPendingAction() {
        if (action != null) {
            if (runOnMainThread) {
                instrumentation.runOnMainSync(action);
            } else {
                action.run();
            }
            instrumentation.waitForIdleSync();
        }
    }

}
