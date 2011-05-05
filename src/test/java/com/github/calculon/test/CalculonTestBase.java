package com.github.calculon.test;

import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.test.InstrumentationTestCase;

@PrepareForTest ({ Bundle.class })
public class CalculonTestBase {

    @Mock
    InstrumentationTestCase testCase;
    @Mock
    Instrumentation instrumentation;
    @Mock
    Activity activity;
    @Mock
    Intent intent;
    @Mock
    Bundle bundle;

    @Before
    public void setup() {
        when(testCase.getInstrumentation()).thenReturn(instrumentation);
    }
}
