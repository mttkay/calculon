package com.github.calculon.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import junit.framework.Assert;
import junit.framework.TestCase;
import android.content.Intent;
import android.os.Bundle;

public class AnnotationChecker {

    public void validateExpectedExtras(Class<? extends TestCase> tcClass, String testName,
            Intent intent) {
        ExpectsExtras annotation = findAnnotation(tcClass, testName, ExpectsExtras.class);
        if (annotation == null) {
            return;
        }
        String[] extras = annotation.value();
        for (String extra : extras) {
        	Bundle intentExtras = intent.getExtras();
			if (intentExtras == null) {
            	Assert.fail("The intent used to start the activity has no extras");            	
            } else if (!intent.hasExtra(extra)) {
                Assert.fail("The intent used to start the activity is missing the extra '" + extra
                        + "'");
            } else if (intentExtras.get(extra) == null) {
            	Assert.fail("The extra '" + extra + "' in the intent used to start the activity is null");
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
