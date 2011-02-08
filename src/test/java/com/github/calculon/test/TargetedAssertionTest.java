package com.github.calculon.test;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import android.app.Activity;
import android.test.InstrumentationTestCase;

import com.github.calculon.assertion.TargetedAssertion;

@RunWith(PowerMockRunner.class)
public class TargetedAssertionTest extends CalculonTestBase {

    public static class Hunk {
        public boolean isHunk() {
            return true;
        }

        public boolean hasWings() {
            return true;
        }
    }

    private static class TestTargetedAssertion extends TargetedAssertion<Hunk> {
        public TestTargetedAssertion(InstrumentationTestCase testCase, Activity activity, Hunk hunk) {
            super(testCase, activity, hunk);
        }
    }

    TestTargetedAssertion assertion;

    @Mock
    Hunk hunk;

    @Override
	@Before
    public void setup() {
        super.setup();
        assertion = new TestTargetedAssertion(testCase, activity, hunk);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowOnMalformedCondition() {
        assertion.satisfies("not well-formed!");
    }

    @Test
    public void shouldPassWhenPredicateIsTrue() {
        when(hunk.isHunk()).thenReturn(true);
        assertion.satisfies("is hunk");
        verify(hunk).isHunk();
    }

    @Test(expected = AssertionFailedError.class)
    public void shouldFailWhenPredicateIsFalse() {
        when(hunk.isHunk()).thenReturn(false);
        assertion.satisfies("is hunk");
        verify(hunk).isHunk();
    }

    @Test
    public void shouldPassWhenNegativePredicateIsFalse() {
        when(hunk.isHunk()).thenReturn(false);
        assertion.satisfies("is not hunk");
        verify(hunk).isHunk();
    }

    @Test(expected = AssertionFailedError.class)
    public void shouldFailWhenNegativePredicateIsTrue() {
        when(hunk.isHunk()).thenReturn(true);
        assertion.satisfies("is not hunk");
        verify(hunk).isHunk();
    }

    @Test
    public void shouldWorkWithAlternateNegation() {
        when(hunk.hasWings()).thenReturn(false);
        assertion.satisfies("has no wings");
        verify(hunk).hasWings();
    }
}
