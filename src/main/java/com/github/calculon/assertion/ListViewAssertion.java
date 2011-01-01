package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewAssertion extends ViewAssertion {

    private ListView listView;

    public ListViewAssertion(InstrumentationTestCase testCase, Activity activity, ListView listView) {
        super(testCase, activity, listView);
        this.listView = listView;
    }

    public void hasItems() {
        assertFalse("list view expected not to be empty, but it was", getAdapter().isEmpty());
    }

    public void hasItems(int count) {
        int actual = getAdapter().getCount();
        assertEquals("list item count mismatch:", count, actual);
    }

    public void hasNoItems() {
        assertTrue("list view expected to be empty, but wasn't", getAdapter().isEmpty());
    }

    public ViewAssertion item(int position) {
        return new ViewAssertion(testCase, activity, getAdapter().getView(position, null, listView));
    }

    public ActionAssertion click(final int position) {
        return AssertionResolver.actionAssertion(testCase, activity, new Runnable() {
            public void run() {
                View itemView = getAdapter().getView(position, null, listView);
                assertNotNull("item view at position " + position + " was null", itemView);
                listView.performItemClick(itemView, position, itemView.getId());
            }
        }, true);
    }

    public ActionAssertion clickFirst() {
        return click(0);
    }

    public ActionAssertion clickLast() {
        return click(getAdapter().getCount() - 1);
    }

    private ListAdapter getAdapter() {
        ListAdapter adapter = listView.getAdapter();
        assertNotNull("list adapter expected to exist, but was null", adapter);
        return adapter;
    }
}
