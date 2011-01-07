package com.github.calculon.test;

import static org.mockito.Mockito.when;
import junit.framework.AssertionFailedError;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import android.test.ActivityTestCase;

import com.github.calculon.annotation.Annotations;
import com.github.calculon.annotation.ExpectsExtras;

@RunWith(PowerMockRunner.class)
public class AnnotationsTest extends CalculonTestBase {

    @Test(expected = AssertionFailedError.class)
    public void shouldFailWhenExpectedExtrasAreMissing() throws Exception {
        Annotations.validateExpectedExtras(AnnotatedTestCase.class, intent);
    }

    @Test
    public void shouldPassWhenExpectedExtrasArePresent() throws Exception {
        when(intent.hasExtra("some_extra")).thenReturn(true);
        Annotations.validateExpectedExtras(AnnotatedTestCase.class, intent);
    }

    @ExpectsExtras( { "some_extra" })
    private static class AnnotatedTestCase extends ActivityTestCase {
    }
}
