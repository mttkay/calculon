package com.github.calculon.annotation;

import junit.framework.Assert;
import android.content.Intent;
import android.test.ActivityTestCase;

public class Annotations {

    public static void validateExpectedExtras(Class<? extends ActivityTestCase> tcClass,
            Intent intent) {
        ExpectsExtras annotation = tcClass.getAnnotation(ExpectsExtras.class);
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

}
