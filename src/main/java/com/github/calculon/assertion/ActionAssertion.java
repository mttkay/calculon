package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public abstract class ActionAssertion extends AssertionBase {

    protected static final String STARTED_ACTIVITY_FAIL_MSG = "Expected that %s was started, but was %s";
    protected static final String STARTED_INTENT_ACTION_FAIL_MSG = "Expected that intent action %s was passed, but was %s";

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

    public ViewAssertion implies(View view) {
        performPendingAction();
        return new ViewAssertion(testCase, activity, view);
    }

    public TextViewAssertion implies(TextView view) {
        performPendingAction();
        return new TextViewAssertion(testCase, activity, view);
    }

    public ListViewAssertion implies(ListView view) {
        performPendingAction();
        return new ListViewAssertion(testCase, activity, view);
    }

    public ViewGroupAssertion implies(ViewGroup view) {
        performPendingAction();
        return new ViewGroupAssertion(testCase, activity, view);
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

    /**
     * Asserts whether an Intent with the given action has been passed. <b>Note
     * that you must never call this assertion more than once per test method,
     * since the started Activity or the Intent used to start it will stick
     * around and may yield false positives on subsequent invocations.</b>
     * 
     * @param intentAction
     *            the Intent action of the Activity expected to be started
     */
    public abstract void startsWithIntentAction(String intentAction);

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

	public IntentAssertion assertThat(Intent startedActivityIntent) {
		performPendingAction();
		return new IntentAssertion(testCase, activity, startedActivityIntent);
	}

}
