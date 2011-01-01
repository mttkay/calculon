package com.github.calculon.assertion;

import static junit.framework.Assert.assertNotNull;
import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.ListView;

import com.github.calculon.CalculonTestCase;

public class CalculonAssertions {

    private static InstrumentationTestCase testCase;

    private static Activity getActivity() {
        Activity activity = ((CalculonTestCase) testCase).getCurrentActivity();
        assertNotNull("Activity is null", activity);
        return activity;
    }

    public static void register(InstrumentationTestCase testCase) {
        CalculonAssertions.testCase = testCase;
    }

    // -------- VIEW RESOLVERS-----------------------------------------

    public static ListView list() {
        return list(android.R.id.list);
    }

    public static ListView list(int id) {
        return (ListView) getActivity().findViewById(id);
    }

    // -------- ASSERTIONS --------------------------------------------

    public static void assertViewNotNull(View view) {
        assertNotNull("View " + view.getId() + " is null in Activity "
                + getActivity().getClass().getCanonicalName(), view);
    }

    public static ActivityAssertion assertThat() {
        return new ActivityAssertion(testCase, getActivity());
    }

    public static ActivityAssertion assertThat(Activity activity) {
        return new ActivityAssertion(testCase, activity);
    }

    public static ViewAssertion assertThat(View view) {
        return new ViewAssertion(testCase, getActivity(), view);
    }

    public static ViewAssertion assertThat(int viewId) {
        return assertThat(getActivity().findViewById(viewId));
    }

    public static ListViewAssertion assertThat(ListView view) {
        return new ListViewAssertion(testCase, getActivity(), view);
    }

}
