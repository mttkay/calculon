package com.github.calculon.story;

import java.util.Vector;

import android.app.Activity;

public class StoryTestActivityStack {

	private Vector<Activity> activities;

	public StoryTestActivityStack(Activity rootActivity) {
		activities = new Vector<Activity>();
		activities.add(rootActivity);
	}

	public void push(Activity currentActivity) {
		if (!currentActivity.isFinishing()) {
			activities.add(currentActivity);
		}
	}

	public Activity pop() {
		int size = activities.size();
		if (size == 0) {
			return null;
		}
		Activity activity = activities.get(size - 1);
		if (activity.isFinishing()) {
			activities.remove(size - 1);
			return pop();
		} else {
			return activity;
		}
	}

}
