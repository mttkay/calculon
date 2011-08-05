package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import junit.framework.AssertionFailedError;
import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.AbsListView;

public class ViewAssertion extends TargetedAssertion<View> {

    public ViewAssertion(InstrumentationTestCase testCase, Activity activity, View view) {
        super(testCase, activity, view);
    }

    // ------------ ACTIONS -----------------------------------

    public ActionAssertion<? extends ViewAssertion> click() {
        CalculonAssertions.assertViewNotNull(target);
        if (target.getLayoutParams() instanceof AbsListView.LayoutParams) {
            throw new IllegalStateException(
                    "use clickFirst(), clickLast(), and click(position) to click list items");
        }
        return AssertionResolver.actionAssertion(this, testCase, activity, new Runnable() {
            public void run() {
                target.performClick();
            }
        }, true);
    }

    public ActionAssertion<? extends ViewAssertion> longClick() {
        CalculonAssertions.assertViewNotNull(target);
        return AssertionResolver.actionAssertion(this, testCase, activity, new Runnable() {
            public void run() {
                target.performLongClick();
            }
        }, true);
    }

    // ------------ PREDICATES ---------------------------------

    /**
     * Asserts that the given view exists in the current layout.
     */
    public void exists() {
        CalculonAssertions.assertViewNotNull(target);
    }

    /**
     * Asserts that the given view does not exist in the current layout.
     */
    public void missing() {
        assertNull("view expected to be missing from layout, but it's there", target);
    }

    /**
     * Asserts that the current view is either completely missing from the current layout, or its
     * visibility is set to {@link View#GONE}. This is useful is you're testing ViewStubs, where you
     * typically don't care if they're just GONE or haven't been inflated yet.
     */
    public void missingOrGone() {
        try {
            missing();
        } catch (AssertionFailedError e) {
            gone();
        }
    }

    /**
     * Asserts that the given view exists and is visible in the current layout.
     * 
     * @see View#VISIBLE
     */
    public void visible() {
        CalculonAssertions.assertViewNotNull(target);
        assertEquals("view expected to be VISIBLE, but wasn't", View.VISIBLE, target
                .getVisibility());
    }

    /**
     * Asserts that the given view exists but is invisible in the current layout.
     * 
     * @see View#INVISIBLE
     */
    public void invisible() {
        CalculonAssertions.assertViewNotNull(target);
        assertEquals("view expected to be INVISIBLE, but wasn't", View.INVISIBLE, target
                .getVisibility());
    }

    /**
     * Asserts that the given view exists in the current layout, but is invisble and not part of the
     * rendering chain.
     * 
     * @see View#GONE
     */
    public void gone() {
        CalculonAssertions.assertViewNotNull(target);
        assertEquals("view expected to be GONE, but wasn't", View.GONE, target.getVisibility());
    }
}
