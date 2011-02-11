package com.github.calculon;

import android.app.Activity;

import com.github.calculon.support.ActivityLauncher.LaunchConfiguration;

public interface CalculonTestCase {

    Activity getCurrentActivity();

    Class<? extends Activity> getActivityClass();

    LaunchConfiguration getLaunchConfiguration();
}
