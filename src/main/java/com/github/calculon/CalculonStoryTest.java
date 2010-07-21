package com.github.calculon;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.github.calculon.story.StoryTestActivityStack;
import com.github.calculon.story.assertion.StoryTestActivityAssertion;
import com.github.calculon.story.assertion.StoryTestViewAssertion;


public abstract class CalculonStoryTest<ActivityT extends Activity> 
				extends ActivityInstrumentationTestCase2<ActivityT> {

	private Class<ActivityT> mActivityClass = null;
	private StoryTestActivityStack activityStack = null;
	
	
	public CalculonStoryTest(String pkg, Class<ActivityT> activityClass) {
		super(pkg, activityClass);
		mActivityClass = activityClass;
	}

	@Override
	protected void setUp() throws Exception {
		Intent intent = new Intent(getInstrumentation().getTargetContext(), 
				mActivityClass.getClass());
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		setActivityIntent(intent);

		super.setUp();

		activityStack = new StoryTestActivityStack(getActivity());
		setActivityInitialTouchMode(false);
		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	protected StoryTestActivityAssertion<ActivityT> assertThat() {
		return new StoryTestActivityAssertion<ActivityT>(this, assertCurrentActivity(), getInstrumentation());
	}

	protected StoryTestActivityAssertion<ActivityT> assertThat(Activity activity) {
		return new StoryTestActivityAssertion<ActivityT>(this, activity, getInstrumentation());
	}

	protected StoryTestViewAssertion<ActivityT> assertThat(View view) {
		return new StoryTestViewAssertion<ActivityT>(this, assertCurrentActivity(), getInstrumentation(), view);
	}

	protected StoryTestViewAssertion<ActivityT> assertThat(int viewId) {
		Activity activity = assertCurrentActivity();
		
		View view = activity.findViewById(viewId);
		assertNotNull("View " + viewId + " is null in Activity " + activity.getClass().getCanonicalName(), view);
		
		return assertThat(view);
	}

	private Activity assertCurrentActivity() {
		Activity activity = getCurrentActivity();
		assertNotNull("Activity is null", activity);
		return activity;
	}
	
	public void setCurrentActivity(Activity currentActivity) {
		activityStack.push(currentActivity);
	}
	
	public Activity getCurrentActivity() {
		return activityStack.pop();
	}


	// protected void flipScreen(Activity activity) {
	// DisplayMetrics dm = activity.getResources().getDisplayMetrics();
	// Configuration config = activity.getResources().getConfiguration();
	// int orientation = activity.getResources().getConfiguration().orientation;
	// if (orientation == Configuration.ORIENTATION_PORTRAIT) {
	// activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	// config.orientation = Configuration.ORIENTATION_LANDSCAPE;
	// } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
	// activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	// config.orientation = Configuration.ORIENTATION_PORTRAIT;
	// }
	// activity.getResources().updateConfiguration(config, dm);
	// getInstrumentation().waitForIdleSync();
	// }
	//
	// protected void flipScreen() {
	// flipScreen(getActivity());
	// }
}
