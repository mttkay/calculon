package com.github.calculon;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;

import com.github.calculon.annotation.AnnotationChecker;
import com.github.calculon.annotation.ExpectsExtras;
import com.github.calculon.assertion.CalculonAssertions;
import com.github.calculon.support.ActivityLauncher;
import com.github.calculon.support.ActivityLauncher.LaunchConfiguration;

public abstract class CalculonUnitTest<ActivityT extends Activity> extends
        ActivityUnitTestCase<ActivityT> implements CalculonTestCase {

    private Class<ActivityT> mActivityClass = null;

    private AnnotationChecker annotationChecker;

    private LaunchConfiguration launchConfig;

    public CalculonUnitTest(Class<ActivityT> activityClass) {
        super(activityClass);
        mActivityClass = activityClass;
        annotationChecker = new AnnotationChecker();
    }

    @Override
    public ActivityT getCurrentActivity() {
        return getActivity();
    }

    @Override
    public Class<? extends Activity> getActivityClass() {
        return mActivityClass;
    }

    @Override
    public LaunchConfiguration getLaunchConfiguration() {
        return launchConfig;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        CalculonAssertions.register(this);
        ActivityLauncher.setTestCase(this);
        launchConfig = new LaunchConfiguration();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        launchConfig = null;
    }

    /**
     * A hook into Intent preparation. Allows test cases to add custom flags or extras to the intent
     * used to start the activity under test.
     * 
     * @param intent
     *            the intent to customize
     */
    protected void setUpIntent(Intent intent) {
        // to be implemented by subclasses
    }

    /**
     * Can be implemented by test cases to specify any bundle extras that should be added by default
     * to the intent used to start the activity under test. These extras should usually correlate
     * with any extras specified using the {@link ExpectsExtras} annotation.
     * 
     * @return the set of default extras for starting the activity under test
     */
    protected Bundle getDefaultExtras() {
        return null;
    }

    @Override
	public ActivityT startActivity(Intent intent, Bundle savedInstanceState,
            Object lastNonConfigurationInstance) {
        return super.startActivity(intent, savedInstanceState, lastNonConfigurationInstance);
    }

    /**
     * Starts the activity under test. This method will honor extras configured by
     * {@link #getDefaultExtras()}. Note that this method will only call onCreate, no other
     * life-cycle methods. If you want to test a full activity launch, use
     * {@link #launchActivity(Bundle)} and its derivatives instead.
     * 
     * @return the intent used to start the activity
     */
    public Intent startActivity() {
        return startActivity(getDefaultExtras());
    }

    /**
     * Starts the activity under test using the given extras. Note that this method will only call
     * onCreate, no other life-cycle methods. If you want to test a full activity launch, use
     * {@link #launchActivity(Bundle)} and its derivatives instead.
     * 
     * @param extras
     *            any custom extras to add to the intent used to start the activity
     * @return the intent used to start the activity
     */
    public Intent startActivity(Bundle extras) {
        Intent intent = prepareIntent(extras);
        startActivity(intent, null, null);
        getInstrumentation().waitForIdleSync();
        return intent;
    }

    /**
     * Launches the activity under test. This method will honor extras configured by
     * {@link #getDefaultExtras()}. This method will call all life-cycle methods involved in an
     * activity launch.
     * 
     * @return the intent used to start the activity
     */
    public Intent launchActivity() {
        return launchActivity(getDefaultExtras());
    }

    /**
     * Launches the activity under test using the given extras. This method will call all life-cycle
     * methods involved in an activity launch.
     * 
     * @param extras
     *            any custom extras to add to the intent used to start the activity
     * @return the intent used to start the activity
     */
    public Intent launchActivity(Bundle extras) {
        Intent intent = prepareIntent(extras);
        startActivity(extras); // this calls onCreate
        getInstrumentation().callActivityOnStart(getActivity());
        getInstrumentation().callActivityOnResume(getActivity());
        getInstrumentation().waitForIdleSync();
        return intent;
    }

    /**
     * Shortcut to {@link Instrumentation#getTargetContext()}.
     * 
     * @see Instrumentation#getTargetContext()
     */
    public Context getTargetContext() {
        return getInstrumentation().getTargetContext();
    }

    /**
     * Shortcut to {@link Instrumentation#getContext()}.
     * 
     * @see Instrumentation#getContext()
     */
    public Context getTestContext() {
        return getInstrumentation().getContext();
    }

    private Intent prepareIntent(Bundle extras) {
        Intent intent = new Intent(getTargetContext(), mActivityClass);
        setUpIntent(intent);
        if (extras != null) {
            intent.putExtras(extras);
        }
        annotationChecker.validateExpectedExtras(getClass(), getName(), intent);
        return intent;
    }
}
