package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.test.InstrumentationTestCase;

public abstract class ActionAssertion extends AssertionBase {

    protected static final String STARTED_ACTIVITY_FAIL_MSG = "Expected that %s was started, but was %s";

    private Runnable action;

    private boolean runOnMainThread;

    public ActionAssertion(InstrumentationTestCase testCase, Activity activity, Runnable action,
            boolean runOnMainThread) {
        super(testCase, activity);
        this.action = action;
        this.runOnMainThread = runOnMainThread;
    }

    public ViewAssertion implies(int otherViewId) {
        performPendingAction();
        return new ViewAssertion(testCase, activity, activity.findViewById(otherViewId));
    }

    /**
     * Asserts whether an Activity of the given type has been started. <b>Note
     * that you must never call this assertion more than once per test method,
     * since the started Activity or the Intent used to start it will stick
     * around and may yield false positives on subsequent invocations.</b>
     * 
     * @param activityClass
     *            the class of the Activity expected to be started
     */
    public abstract void starts(Class<? extends Activity> activityClass);

    public void finishesActivity() {
        performPendingAction();
        assertTrue(activity.isFinishing());
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
