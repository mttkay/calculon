package com.github.calculon.assertion;

import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

public class ExpandableListViewAssertion extends ViewAssertion {

    private ExpandableListView listView;

    public ExpandableListViewAssertion(InstrumentationTestCase testCase, Activity activity, ExpandableListView listView) {
        super(testCase, activity, listView);
        this.listView = listView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionAssertion<ExpandableListViewAssertion> click() {
        return (ActionAssertion<ExpandableListViewAssertion>) super.click();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionAssertion<ExpandableListViewAssertion> longClick() {
        return (ActionAssertion<ExpandableListViewAssertion>) super.longClick();
    }

    public void hasGroups() {
        assertFalse("list view expected not to be empty, but it was", getAdapter().getGroupCount() > 0);
    }

    public void hasGroups(int count) {
        int actual = getAdapter().getGroupCount();
        assertEquals("list group count mismatch:", count, actual);
    }

    public void isEmpty() {
        assertTrue("list view expected to be empty, but wasn't", getAdapter().isEmpty());
    }

    public ViewAssertion group(int position) {
        return new ViewAssertion(testCase, activity, getGroupView(position));
    }

    public ViewAssertion child(int groupPosition, int childPosition) {
        boolean lastChild = isLastChild(groupPosition, childPosition);
        return new ViewAssertion(testCase, activity, getChildView(groupPosition, childPosition, lastChild));
    }

    private boolean isLastChild(int groupPosition, int childPosition) {
        return (childPosition == getAdapter().getChildrenCount(groupPosition) - 1);
    }

    public ViewAssertion firstGroup() {
        return group(0);
    }

    public ViewAssertion lastGroup() {
        return group(getAdapter().getGroupCount() - 1);
    }

    public ViewAssertion firstChild(int groupPosition) {
        return child(groupPosition, 0);
    }

    public ViewAssertion lastChild(int groupPosition) {
        return child(getAdapter().getGroupCount() - 1, getAdapter().getChildrenCount(groupPosition) - 1);
    }

    public MultiViewAssertion groups() {
        ExpandableListAdapter adapter = getAdapter();
        int count = adapter.getGroupCount();
        List<View> views = new ArrayList<View>(count);
        for (int i = 0; i < count; i++) {
            views.add(adapter.getGroupView(i, false, null, listView));
        }
        return new MultiViewAssertion(testCase, activity, views);
    }

    public MultiViewAssertion children(int groupPosition) {
        ExpandableListAdapter adapter = getAdapter();
        int count = adapter.getChildrenCount(groupPosition);
        List<View> views = new ArrayList<View>(count);
        boolean lastChild;
        for (int i = 0; i < count; i++) {
            lastChild = isLastChild(groupPosition, i);
            views.add(adapter.getChildView(groupPosition, i, lastChild, null, listView));
        }
        return new MultiViewAssertion(testCase, activity, views);
    }

    public ActionAssertion<?> clickChild(final int groupPosition, final int childPosition) {
        return AssertionResolver.actionAssertion(this, testCase, activity, new Runnable() {
            public void run() {
                View itemView = getChildView(groupPosition, childPosition, false);
                assertNotNull("item view at position " + groupPosition + " was null", itemView);
                itemView.performClick();
            }
        }, true);
    }
    public ActionAssertion<?> clickGroup(final int groupPosition) {
        return AssertionResolver.actionAssertion(this, testCase, activity, new Runnable() {
            public void run() {
                View itemView = getGroupView(groupPosition);
                assertNotNull("item view at position " + groupPosition + " was null", itemView);
                itemView.performClick();
                //listView.performItemClick(itemView, groupPosition, itemView.getId());
            }
        }, true);
    }

    private View getGroupView(int groupPosition) {
        return getAdapter().getGroupView(groupPosition, false, null, listView);
    }

    private View getChildView(int groupPosition, int childPosition, boolean lastChild) {
        return getAdapter().getChildView(groupPosition, childPosition, lastChild, null, listView);
    }

    private ExpandableListAdapter getAdapter() {
        ExpandableListAdapter adapter = listView.getExpandableListAdapter();
        assertNotNull("list adapter expected to exist, but was null", adapter);
        return adapter;
    }
}
