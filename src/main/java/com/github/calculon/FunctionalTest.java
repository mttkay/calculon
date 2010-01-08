package com.github.calculon;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.github.calculon.assertion.ActivityAssertion;
import com.github.calculon.assertion.ViewAssertion;

public class FunctionalTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    public FunctionalTest(String pkg, Class<T> activityClass) {
        super(pkg, activityClass);
    }

    protected ActivityAssertion assertThat() {
        return new ActivityAssertion(getActivity(), getInstrumentation());
    }

    protected ActivityAssertion assertThat(Activity activity) {
        return new ActivityAssertion(activity, getInstrumentation());
    }

    protected ViewAssertion assertThat(View view) {
        return new ViewAssertion(getActivity(), getInstrumentation(), view);
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
