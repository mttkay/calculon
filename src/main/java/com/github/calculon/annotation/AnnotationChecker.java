package com.github.calculon.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.content.Intent;

public class AnnotationChecker {

    public void validateExpectedExtras(Class<? extends TestCase> tcClass, String testName,
            Intent intent) {
        ExpectsExtras annotation = findAnnotation(tcClass, testName, ExpectsExtras.class);
        if (annotation == null) {
            return;
        }
        String[] extras = annotation.value();
        for (String extra : extras) {
            if (!intent.hasExtra(extra)) {
                Assert.fail("The intent used to start the activity is missing the extra '" + extra
                        + "'");
            }
        }
    }

    public <T extends Annotation> T findAnnotation(Class<? extends TestCase> tcClass,
            String testName, Class<T> annotationClass) {
        T annotation = tcClass.getAnnotation(annotationClass);
        if (annotation == null) {
            // no class level annotation, check whether the current test has one
            try {
                Method m = tcClass.getMethod(testName);
                annotation = m.getAnnotation(annotationClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return annotation;
    }
}
