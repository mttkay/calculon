package com.github.calculon.assertion;

import static junit.framework.Assert.assertNotNull;
import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityTestCase;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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

    public static View view(int id) {
    	return getActivity().findViewById(id);
    }
    
    public static View view(int layoutId, int viewId) {
    	return view(layoutId).findViewById(viewId);
    }
    
    public static ListView list() {
        return list(android.R.id.list);
    }

    public static ListView list(int id) {
        return (ListView) getActivity().findViewById(id);
    }

    public static ListView list(int layoutId, int id) {
        return (ListView) view(layoutId).findViewById(id);
    }

    public static View emptyListView() {
    	return view(android.R.id.empty);
    }

    public static TextView text(int id) {
        return (TextView) getActivity().findViewById(id);
    }

    public static TextView text(int layoutId, int id) {
        return (TextView) view(layoutId).findViewById(id);
    }

    public static CheckBox checkBox(int id) {
        return (CheckBox) getActivity().findViewById(id);
    }

    public static CheckBox checkBox(int layoutId, int id) {
        return (CheckBox) view(layoutId).findViewById(id);
    }

    public static ToggleButton toggleButton(int id) {
        return (ToggleButton) getActivity().findViewById(id);
    }

    public static ToggleButton toggleButton(int layoutId, int id) {
        return (ToggleButton) view(layoutId).findViewById(id);
    }

    public static RadioButton radioButton(int id) {
        return (RadioButton) getActivity().findViewById(id);
    }

    public static RadioButton radioButton(int layoutId, int id) {
        return (RadioButton) view(layoutId).findViewById(id);
    }

    public static ViewGroup parent(int id) {
        return (ViewGroup) getActivity().findViewById(id);
    }

    public static ViewGroup parent(int layoutId, int id) {
        return (ViewGroup) view(layoutId).findViewById(id);
    }

    // -------- ASSERTIONS --------------------------------------------

    public static void assertViewNotNull(View view) {
        assertNotNull("view expected to exist, but didn't", view);
    }

    public static ActivityAssertion assertThat() {
        return new ActivityAssertion(testCase, getActivity());
    }

    public static ActivityAssertion assertThat(Activity activity) {
        return new ActivityAssertion(testCase, activity);
    }
    
    public static IntentAssertion assertThat(Intent intent) {
    	return new IntentAssertion(testCase, getActivity(), intent);
    }

    public static ViewAssertion assertThat(View view) {
        return new ViewAssertion(testCase, getActivity(), view);
    }

    public static ViewAssertion assertThat(int viewId) {
        return assertThat(getActivity().findViewById(viewId));
    }

    public static ViewGroupAssertion assertThat(ViewGroup view) {
        return new ViewGroupAssertion(testCase, getActivity(), view);
    }

    public static ListViewAssertion assertThat(ListView view) {
        return new ListViewAssertion(testCase, getActivity(), view);
    }

    public static ExpandableListViewAssertion assertThat(ExpandableListView view) {
        return new ExpandableListViewAssertion(testCase, getActivity(), view);
    }

    public static TextViewAssertion assertThat(TextView view) {
        return new TextViewAssertion(testCase, getActivity(), view);
    }

    public static CompoundButtonAssertion assertThat(CompoundButton view) {
        return new CompoundButtonAssertion(testCase, getActivity(), view);
    }
}
