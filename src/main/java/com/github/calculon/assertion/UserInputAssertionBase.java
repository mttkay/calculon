package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonStoryTest;
import com.github.calculon.predicate.Predicate;

public abstract class UserInputAssertionBase<TargetT, ActivityT extends Activity> extends
        AssertionBase<ActivityT> {

    protected TargetT target;

    public UserInputAssertionBase(CalculonStoryTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation) {
        super(testCase, activity, instrumentation);
    }

    public ActionAssertion<ActivityT> keyPress(final int... keyCodes) {
        return new ActionAssertion<ActivityT>(testCase, activity, instrumentation, new Runnable() {
            public void run() {
                UserInputAssertionBase.this.testCase.sendKeys(keyCodes);
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
