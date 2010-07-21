package com.github.calculon.unit.assertion;

import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonUnitTest;

public abstract class UnitTestAssertionBase<ActivityT extends Activity> {

    protected CalculonUnitTest<ActivityT> testCase;
    protected Activity activity;
    protected Instrumentation instrumentation;

    public UnitTestAssertionBase(CalculonUnitTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation) {
    	this.testCase = testCase;
        this.activity = activity;
        this.instrumentation = instrumentation;
    }
}
