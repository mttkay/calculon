package com.github.calculon.unit.assertion;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonUnitTest;
import com.github.calculon.predicate.Predicate;

public abstract class UnitTestUserInputAssertionBase<TargetT, ActivityT extends Activity> extends
        UnitTestAssertionBase<ActivityT> {

    protected TargetT target;

    public UnitTestUserInputAssertionBase(CalculonUnitTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation) {
        super(testCase, activity, instrumentation);
    }

    public UnitTestActionAssertion<ActivityT> keyPress(final int... keyCodes) {
        return new UnitTestActionAssertion<ActivityT>(testCase, activity, instrumentation, new Runnable() {
            public void run() {
            	for(int code: keyCodes) {
            		instrumentation.sendKeyDownUpSync(code);
            	}
            	
            	instrumentation.waitForIdleSync();
            }
        }, false);
    }

    public void satisfies(Predicate<TargetT> predicate) {
        try {
            assertTrue(predicate.check(target));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Terminated unexpectedly by exception");
        }
    }
}
