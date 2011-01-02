package com.github.calculon.assertion;

import java.util.List;

import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.view.View;

import com.github.calculon.predicate.Predicate;

public class MultiViewAssertion extends AssertionBase {

    private List<View> views;

    public MultiViewAssertion(InstrumentationTestCase testCase, Activity activity, List<View> views) {
        super(testCase, activity);
        this.views = views;
    }

    public void satisfy(final Predicate<View> predicate) {
        for (View view : views) {
            new ViewAssertion(testCase, activity, view).satisfies(predicate);
        }
    }

    public void satisfy(final String condition) {
        for (View view : views) {
            new ViewAssertion(testCase, activity, view).satisfies(condition);
        }
    }
}

