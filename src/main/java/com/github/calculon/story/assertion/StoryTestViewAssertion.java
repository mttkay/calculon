package com.github.calculon.story.assertion;

import static junit.framework.Assert.assertEquals;
import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import com.github.calculon.CalculonStoryTest;

public class StoryTestViewAssertion<ActivityT extends Activity> extends
StoryTestUserInputAssertionBase<View, ActivityT> {

	public StoryTestViewAssertion(CalculonStoryTest<ActivityT> testCase, Activity activity,
			Instrumentation instrumentation, View view) {
		super(testCase, activity, instrumentation);
		this.target = view;
	}

	public StoryTestActionAssertion<ActivityT> click() {
		return new StoryTestActionAssertion<ActivityT>(testCase, activity, instrumentation, new Runnable() {
			public void run() {
				target.performClick();
			}
		}, true);
	}

	public StoryTestActionAssertion<ActivityT> longClick() {
		return new StoryTestActionAssertion<ActivityT>(testCase, activity, instrumentation, new Runnable() {
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
