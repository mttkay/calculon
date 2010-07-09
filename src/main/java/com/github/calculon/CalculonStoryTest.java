package com.github.calculon;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.github.calculon.assertion.ActivityAssertion;
import com.github.calculon.assertion.ViewAssertion;

public abstract class CalculonStoryTest<ActivityT extends Activity> extends
        ActivityInstrumentationTestCase2<ActivityT> {

    public CalculonStoryTest(String pkg, Class<ActivityT> activityClass) {
        super(pkg, activityClass);
    }
    
    @Override
    protected void setUp() throws Exception {
    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		setActivityIntent(intent);
    	super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
    	super.tearDown();
    }
    
    protected ActivityAssertion<ActivityT> assertThat() {
        return new ActivityAssertion<ActivityT>(this, getActivity(), getInstrumentation());
    }

    protected ActivityAssertion<ActivityT> assertThat(Activity activity) {
        return new ActivityAssertion<ActivityT>(this, activity, getInstrumentation());
    }

    protected ViewAssertion<ActivityT> assertThat(View view) {
        return new ViewAssertion<ActivityT>(this, getActivity(), getInstrumentation(), view);
    }

    protected ViewAssertion<ActivityT> assertThat(int viewId) {
        View view = getActivity().findViewById(viewId);
        return assertThat(view);
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
