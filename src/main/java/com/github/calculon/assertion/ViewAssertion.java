package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.TextView;

public class ViewAssertion extends UserInputAssertion<View> {

    public ViewAssertion(InstrumentationTestCase testCase, Activity activity, View view) {
        super(testCase, activity, view);
        CalculonAssertions.assertViewNotNull(view);
    }

    // ------------ ACTIONS -----------------------------------

    public ActionAssertion click() {
        return AssertionClassResolver.actionAssertion(testCase, activity, new Runnable() {
            public void run() {
                target.performClick();
            }
        }, true);
    }

    public ActionAssertion longClick() {
        return AssertionClassResolver.actionAssertion(testCase, activity, new Runnable() {
            public void run() {
                target.performLongClick();
            }
        }, true);
    }

    // ------------ PREDICATES ---------------------------------

    public void exists() {
        // just syntactic sugar
    }

    public void visible() {
        assertEquals("view expected to be VISIBLE, but wasn't", View.VISIBLE, target
                .getVisibility());
    }

    public void invisible() {
        assertEquals("view expected to be INVISIBLE, but wasn't", View.INVISIBLE, target
                .getVisibility());
    }

    public void gone() {
        assertEquals("view expected to be GONE, but wasn't", View.GONE, target.getVisibility());
    }

    // ------------ CONVERTERS -----------------------------------

    public TextViewAssertion asText() {
        assertTrue("expected view to be a TextView, but wasn't", target instanceof TextView);
        return new TextViewAssertion(testCase, activity, (TextView) target);
    }
}
