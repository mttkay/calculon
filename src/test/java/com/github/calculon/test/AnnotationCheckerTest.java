package com.github.calculon.test;

import static org.mockito.Mockito.when;
import junit.framework.AssertionFailedError;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import android.test.ActivityTestCase;

import com.github.calculon.annotation.AnnotationChecker;
import com.github.calculon.annotation.ExpectsExtras;

@RunWith(PowerMockRunner.class)
public class AnnotationCheckerTest extends CalculonTestBase {

    private AnnotationChecker checker = new AnnotationChecker();

    @Test
    public void shouldPassWhenExpectedExtrasNotDefined() throws Exception {
        when(intent.hasExtra("some_extra")).thenReturn(false);
        checker.validateExpectedExtras(SingleAnnotatedTestCase.class, "testWithoutAnnotation",
                intent);
    }

    @Test(expected = AssertionFailedError.class)
    public void shouldFailWhenExpectedExtrasAreMissing() throws Exception {
        when(intent.hasExtra("some_extra")).thenReturn(false);
        checker.validateExpectedExtras(AnnotatedTestCase.class, "testSomething", intent);
    }

    @Test
    public void shouldPassWhenClassExpectedExtrasArePresent() throws Exception {
        when(intent.getExtras()).thenReturn(bundle);
        when(bundle.get("some_extra")).thenReturn(bundle);
    	when(intent.hasExtra("some_extra")).thenReturn(true);
        checker.validateExpectedExtras(AnnotatedTestCase.class, "testSomething", intent);
    }

    @Test(expected = AssertionFailedError.class)
    public void shouldFailWhenMethodExpectedExtrasAreMissing() throws Exception {
        when(intent.hasExtra("some_extra")).thenReturn(false);
        checker.validateExpectedExtras(SingleAnnotatedTestCase.class, "testWithAnnotation", intent);
    }

    @Test
    public void shouldPassWhenMethodExpectedExtrasArePresent() throws Exception {
    	when(intent.getExtras()).thenReturn(bundle);
        when(bundle.get("some_extra")).thenReturn(bundle);
        when(intent.hasExtra("some_extra")).thenReturn(true);
        checker.validateExpectedExtras(SingleAnnotatedTestCase.class, "testWithAnnotation", intent);
    }

    @SuppressWarnings("unused")
    @ExpectsExtras( { "some_extra" })
    private static class AnnotatedTestCase extends ActivityTestCase {
        public void testSomething() {
        }
    }

    @SuppressWarnings("unused")
    private static class SingleAnnotatedTestCase extends ActivityTestCase {
        public void testWithoutAnnotation() {
        }

        @ExpectsExtras( { "some_extra" })
        public void testWithAnnotation() {
        }
    }

}
