package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

public class ViewAssertion extends UserInputAssertionBase<View> {

    public ViewAssertion(Activity activity, Instrumentation instrumentation, View view) {
        super(activity, instrumentation);
        this.target = view;
    }

    public ActionAssertion click() {
        return new ActionAssertion(activity, instrumentation, new Runnable() {
            public void run() {
                target.performClick();
            }
        }, true);
    }

    public ActionAssertion longClick() {
        return new ActionAssertion(activity, instrumentation, new Runnable() {
            public void run() {
                target.performLongClick();
            }
        }, true);
    }

    public void isVisible() {
        assertEquals("view expected to be VISIBLE, but wasn't", View.VISIBLE, target
            .getVisibility());
    }

    public void isInvisible() {
        assertEquals("view expected to be INVISIBLE, but wasn't", View.INVISIBLE, target
            .getVisibility());
    }

    public void isGone() {
        assertEquals("view expected to be GONE, but wasn't", View.GONE, target.getVisibility());
    }
}
