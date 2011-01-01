package com.github.calculon.assertion;

import android.app.Activity;
import android.test.InstrumentationTestCase;

import com.github.calculon.CalculonStoryTest;
import com.github.calculon.CalculonUnitTest;
import com.github.calculon.assertion.story.StoryTestActionAssertion;
import com.github.calculon.assertion.unit.UnitTestActionAssertion;

public class AssertionClassResolver {

    public static ActionAssertion actionAssertion(InstrumentationTestCase testCase,
            Activity activity, Runnable action, boolean runOnMainThread) {
        if (testCase instanceof CalculonUnitTest<?>) {
            return (ActionAssertion) new UnitTestActionAssertion(testCase, activity, action,
                    runOnMainThread);
        } else if (testCase instanceof CalculonStoryTest<?>) {
            return (ActionAssertion) new StoryTestActionAssertion(testCase, activity, action,
                    runOnMainThread);
        } else {
            throw new IllegalArgumentException("Test case must be either a "
                    + CalculonUnitTest.class.getName() + " or " + CalculonStoryTest.class.getName());
        }
    }
}
