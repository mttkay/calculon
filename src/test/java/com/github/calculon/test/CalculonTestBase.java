package com.github.calculon.test;

import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.mockito.Mock;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.InstrumentationTestCase;

public class CalculonTestBase {

    @Mock
    InstrumentationTestCase testCase;
    @Mock
    Instrumentation instrumentation;
    @Mock
    Activity activity;
    @Mock
    Intent intent;

    @Before
    public void setup() {
        when(testCase.getInstrumentation()).thenReturn(instrumentation);
    }
}
