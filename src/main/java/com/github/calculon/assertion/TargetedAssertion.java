package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.test.InstrumentationTestCase;

import com.github.calculon.predicate.Predicate;
import com.github.calculon.predicate.PredicateParser;

public abstract class TargetedAssertion<TargetT> extends AssertionBase {

    protected TargetT target;

    public TargetedAssertion(InstrumentationTestCase testCase, Activity activity, TargetT target) {
        super(testCase, activity);
        this.target = target;
    }

    public void satisfies(final Predicate<TargetT> predicate) {
        assertTrue("the " + target.getClass().getSimpleName()
                + " did not satisfy the given condition", predicate.check(target));
    }

    public void satisfies(final String condition) {
        PredicateParser<TargetT> parser = new PredicateParser<TargetT>(target);
        assertTrue(condition + " was not satisfied", parser.evaluateCondition(condition));
    }
}
