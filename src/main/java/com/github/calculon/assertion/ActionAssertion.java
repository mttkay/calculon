package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.content.Context;
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

    public abstract <C extends Context> void starts(Class<C> contextClass);

    public void finishesActivity() {
        performPendingAction();
        assertTrue(activity.isFinishing());
    }

    protected void requirePendingAction() {
        if (action == null) {
            throw new IllegalStateException(
                    "This assertion relies on an action that triggers it, such as a click()");
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
