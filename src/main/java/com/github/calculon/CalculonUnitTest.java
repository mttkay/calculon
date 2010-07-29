package com.github.calculon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.view.View;

import com.github.calculon.unit.assertion.UnitTestActivityAssertion;
import com.github.calculon.unit.assertion.UnitTestViewAssertion;

public abstract class CalculonUnitTest<ActivityT extends Activity> extends
        ActivityUnitTestCase<ActivityT> {

    private Class<ActivityT> mActivityClass = null;

    public CalculonUnitTest(Class<ActivityT> activityClass) {
        super(activityClass);
        mActivityClass = activityClass;
    }

    protected void startActivity() {
        startActivity(new Intent(getInstrumentation().getTargetContext(), mActivityClass), null,
                null);
        getInstrumentation().waitForIdleSync();
    }

    protected void startActivity(Bundle extras) {
        Intent intent = new Intent(getInstrumentation().getTargetContext(), mActivityClass);
        intent.putExtras(extras);
        startActivity(intent, null, null);
        getInstrumentation().waitForIdleSync();
    }

    protected UnitTestActivityAssertion<ActivityT> assertThat() {
        return new UnitTestActivityAssertion<ActivityT>(this, getActivity(), getInstrumentation());
    }

    protected UnitTestActivityAssertion<ActivityT> assertThat(Activity activity) {
        return new UnitTestActivityAssertion<ActivityT>(this, activity, getInstrumentation());
    }

    protected UnitTestViewAssertion<ActivityT> assertThat(View view) {
        return new UnitTestViewAssertion<ActivityT>(this, getActivity(), getInstrumentation(), view);
    }

    protected UnitTestViewAssertion<ActivityT> assertThat(int viewId) {
        View view = getActivity().findViewById(viewId);
        return assertThat(view);
    }
}
