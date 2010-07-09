package com.github.calculon;

import android.app.Activity;
import android.test.ActivityUnitTestCase;

public abstract class CalculonUnitTest<T extends Activity> extends ActivityUnitTestCase<T> {

	public CalculonUnitTest(Class<T> activityClass) {
		super(activityClass);
	}

}
