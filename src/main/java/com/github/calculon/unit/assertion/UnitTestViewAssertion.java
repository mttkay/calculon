package com.github.calculon.unit.assertion;

import static junit.framework.Assert.assertEquals;
import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import com.github.calculon.CalculonUnitTest;


public class UnitTestViewAssertion<ActivityT extends Activity> extends
UnitTestUserInputAssertionBase<View, ActivityT> {

	public UnitTestViewAssertion(CalculonUnitTest<ActivityT> testCase, Activity activity,
			Instrumentation instrumentation, View view) {
		super(testCase, activity, instrumentation);
		this.target = view;
	}

	public UnitTestActionAssertion<ActivityT> click() {
		return new UnitTestActionAssertion<ActivityT>(testCase, activity, instrumentation, new Runnable() {
			public void run() {
				target.performClick();
			}
		}, true);
	}

	public UnitTestActionAssertion<ActivityT> longClick() {
		return new UnitTestActionAssertion<ActivityT>(testCase, activity, instrumentation, new Runnable() {
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
