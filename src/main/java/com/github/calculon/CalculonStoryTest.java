package com.github.calculon;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.github.calculon.assertion.CalculonAssertions;
import com.github.calculon.story.StoryTestActivityStack;
import com.github.calculon.support.ActivityLauncher;

public abstract class CalculonStoryTest<ActivityT extends Activity> extends
        ActivityInstrumentationTestCase2<ActivityT> implements CalculonTestCase{

    private Class<ActivityT> mActivityClass = null;
    private StoryTestActivityStack activityStack = null;

    public CalculonStoryTest(String pkg, Class<ActivityT> activityClass) {
        super(pkg, activityClass);
        mActivityClass = activityClass;
    }

    @Override
    public ActivityLauncher.LaunchConfiguration getLaunchConfiguration() {
        return new ActivityLauncher.LaunchConfiguration();
    }



    @Override
    protected void setUp() throws Exception {
        Intent startingIntent = new Intent(getInstrumentation().getTargetContext(), mActivityClass
                .getClass());
        startingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        setUp(startingIntent);
    }

    protected void setUp(Intent startingIntent) throws Exception {
        setActivityIntent(startingIntent);

        super.setUp();

        activityStack = new StoryTestActivityStack(getActivity());
        setActivityInitialTouchMode(false);

        CalculonAssertions.register(this);
    }

    public void setCurrentActivity(Activity currentActivity) {
        activityStack.push(currentActivity);
    }

    public Activity getCurrentActivity() {
        return activityStack.pop();
    }

    @Override
    public Class<? extends Activity> getActivityClass() {
        return mActivityClass;
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
