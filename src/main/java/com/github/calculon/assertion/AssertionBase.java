package com.github.calculon.assertion;

import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonStoryTest;

public abstract class AssertionBase {

    protected CalculonStoryTest testCase;
    protected Activity activity;
    protected Instrumentation instrumentation;

    public AssertionBase(CalculonStoryTest testCase, Activity activity, Instrumentation instrumentation) {
    	this.testCase = testCase;
        this.activity = activity;
        this.instrumentation = instrumentation;
    }
}
