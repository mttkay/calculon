package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import android.app.Activity;
import android.test.InstrumentationTestCase;

import com.github.calculon.predicate.Predicate;

public abstract class UserInputAssertion<TargetT> extends AssertionBase {

    protected TargetT target;

    public UserInputAssertion(InstrumentationTestCase testCase, Activity activity) {
        super(testCase, activity);
    }

    public ActionAssertion keyPress(final int... keyCodes) {
        return AssertionClassResolver.actionAssertion(testCase, activity, new Runnable() {
            public void run() {
                for (int code : keyCodes) {
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
