package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.view.ViewGroup;

public class ViewGroupAssertion extends ViewAssertion {

    private ViewGroup parent;

    public ViewGroupAssertion(InstrumentationTestCase testCase, Activity activity,
            ViewGroup viewGroup) {
        super(testCase, activity, viewGroup);
        this.parent = viewGroup;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionAssertion<ViewGroupAssertion> click() {
        return (ActionAssertion<ViewGroupAssertion>) super.click();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionAssertion<ViewGroupAssertion> longClick() {
        return (ActionAssertion<ViewGroupAssertion>) super.longClick();
    }

    public void hasChildren(int count) {
        assertEquals("Wrong number of children:", count, parent.getChildCount());
    }

    public ViewAssertion child(int id) {
        return new ViewAssertion(testCase, activity, parent.findViewById(id));
    }

    public ViewAssertion childAt(int index) {
        return new ViewAssertion(testCase, activity, parent.getChildAt(index));
    }

}
