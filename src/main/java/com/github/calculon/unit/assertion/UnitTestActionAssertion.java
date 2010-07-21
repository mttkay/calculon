package com.github.calculon.unit.assertion;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import com.github.calculon.CalculonUnitTest;


public class UnitTestActionAssertion<ActivityT extends Activity> extends UnitTestAssertionBase<ActivityT> {

    private Runnable action;
    private boolean runOnMainThread;

    
    public UnitTestActionAssertion(CalculonUnitTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation, Runnable action,
            boolean runOnMainThread) {
        super(testCase, activity, instrumentation);
        this.action = action;
        this.runOnMainThread = runOnMainThread;
    }

    public UnitTestViewAssertion<ActivityT> implies(int otherViewId) {
        performPendingAction();
        return new UnitTestViewAssertion<ActivityT>(testCase, activity, instrumentation, activity
            .findViewById(otherViewId));
    }

    public <C extends Context>Intent starts(Class<C> contextClass) {
        requirePendingAction();
        performPendingAction();
        
        Intent intent = new Intent();
        intent = testCase.getStartedActivityIntent();
		assertEquals(contextClass.getCanonicalName(), intent.getComponent().getClassName());

		return intent;
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
