package com.github.calculon.story.assertion;

import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonStoryTest;

public abstract class StoryTestAssertionBase<ActivityT extends Activity> {

    protected CalculonStoryTest<ActivityT> testCase;
    protected Activity activity;
    protected Instrumentation instrumentation;

    public StoryTestAssertionBase(CalculonStoryTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation) {
    	this.testCase = testCase;
        this.activity = activity;
        this.instrumentation = instrumentation;
    }
}
