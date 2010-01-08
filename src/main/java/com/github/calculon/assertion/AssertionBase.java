package com.github.calculon.assertion;

import android.app.Activity;
import android.app.Instrumentation;

public abstract class AssertionBase {

    protected Activity activity;

    protected Instrumentation instrumentation;

    public AssertionBase(Activity activity, Instrumentation instrumentation) {
        this.activity = activity;
        this.instrumentation = instrumentation;
    }
}
