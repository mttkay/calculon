package com.github.calculon;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.github.calculon.assertion.ActivityAssertion;
import com.github.calculon.assertion.ViewAssertion;

public abstract class CalculonStoryTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    public CalculonStoryTest(String pkg, Class<T> activityClass) {
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
    
    protected ActivityAssertion assertThat() {
        return new ActivityAssertion(this, getActivity(), getInstrumentation());
    }

    protected ActivityAssertion assertThat(Activity activity) {
        return new ActivityAssertion(this, activity, getInstrumentation());
    }

    protected ViewAssertion assertThat(View view) {
        return new ViewAssertion(this, getActivity(), getInstrumentation(), view);
    }

    protected ViewAssertion assertThat(int viewId) {
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
