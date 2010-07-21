package com.github.calculon.story.assertion;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonStoryTest;
import com.github.calculon.predicate.Predicate;

public abstract class StoryTestUserInputAssertionBase<TargetT, ActivityT extends Activity> extends
        StoryTestAssertionBase<ActivityT> {

    protected TargetT target;

    public StoryTestUserInputAssertionBase(CalculonStoryTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation) {
        super(testCase, activity, instrumentation);
    }

    public StoryTestActionAssertion<ActivityT> keyPress(final int... keyCodes) {
        return new StoryTestActionAssertion<ActivityT>(testCase, activity, instrumentation, new Runnable() {
            public void run() {
            	for(int code: keyCodes) {
            		instrumentation.sendKeyDownUpSync(code);
//            		try {
//            			Thread.sleep(500);
//            		} catch (InterruptedException e) {
//            			e.printStackTrace();
//            		}
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
