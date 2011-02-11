package com.github.calculon.support;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityTestCase;

import com.github.calculon.CalculonTestCase;
import com.github.calculon.CalculonUnitTest;

public class ActivityLauncher {

    private static CalculonTestCase cTestCase;
    private static ActivityTestCase aTestCase;

    public static void setTestCase(CalculonTestCase testCase) {
        ActivityLauncher.cTestCase = testCase;
        ActivityLauncher.aTestCase = (ActivityTestCase) testCase;
    }

    public static class LaunchConfiguration {
        private Intent intent = new Intent();
        private Bundle bundle = new Bundle();

        public Intent getIntent() {
            return intent;
        }

        public void setIntent(Intent intent) {
            this.intent = intent;
        }

        public Bundle getBundle() {
            return bundle;
        }

        public void setBundle(Bundle bundle) {
            this.bundle = bundle;
        }

        public void start() {
            try {
                intent.setClass(aTestCase.getInstrumentation().getTargetContext(), cTestCase
                        .getActivityClass());
                CalculonUnitTest tc = (CalculonUnitTest) aTestCase;
                tc.startActivity(intent, null, null);
            } catch (Exception e) {
                throw new IllegalStateException(
                        "Starting activities this way is not supported by this kind of test case");
            }
        }

        public LaunchConfiguration and(String key, boolean value) {
            getIntent().putExtra(key, value);
            return this;
        }
    }

    public static <T> LaunchConfiguration with(String key, boolean value) {
        return cTestCase.getLaunchConfiguration().and(key, value);
    }

    public static Intent intent() {
        LaunchConfiguration lc = cTestCase.getLaunchConfiguration();
        Intent intent = lc.getIntent();
        if (intent == null) {
            Context context = aTestCase.getInstrumentation().getTargetContext();
            intent = new Intent(context, cTestCase.getActivityClass());
            lc.setIntent(intent);
        }
        return intent;
    }
}
