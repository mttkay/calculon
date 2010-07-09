package com.github.calculon.assertion;

import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonStoryTest;

public abstract class AssertionBase<ActivityT extends Activity> {

    protected CalculonStoryTest<ActivityT> testCase;
    protected Activity activity;
    protected Instrumentation instrumentation;

    public AssertionBase(CalculonStoryTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation) {
    	this.testCase = testCase;
        this.activity = activity;
        this.instrumentation = instrumentation;
    }
}
