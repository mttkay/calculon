package com.github.calculon.assertion;

import android.app.Activity;
import android.test.InstrumentationTestCase;

import com.github.calculon.CalculonStoryTest;
import com.github.calculon.CalculonUnitTest;
import com.github.calculon.assertion.story.StoryTestActionAssertion;
import com.github.calculon.assertion.unit.UnitTestActionAssertion;

public class AssertionResolver {

    public static <AssertionT> ActionAssertion<AssertionT> actionAssertion(
            AssertionT fromAssertion, InstrumentationTestCase testCase, Activity activity,
            Runnable action, boolean runOnMainThread) {
        if (testCase instanceof CalculonUnitTest<?>) {
            return new UnitTestActionAssertion<AssertionT>(fromAssertion, testCase, activity,
                    action, runOnMainThread);
        } else if (testCase instanceof CalculonStoryTest<?>) {
            return new StoryTestActionAssertion<AssertionT>(fromAssertion, testCase, activity,
                    action, runOnMainThread);
        } else {
            throw new IllegalArgumentException("Test case must be either a "
                    + CalculonUnitTest.class.getName() + " or " + CalculonStoryTest.class.getName());
        }
    }
}
