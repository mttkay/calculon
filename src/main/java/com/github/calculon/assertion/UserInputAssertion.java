package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import android.app.Activity;
import android.test.InstrumentationTestCase;

import com.github.calculon.predicate.Predicate;

public abstract class UserInputAssertion<TargetT> extends AssertionBase {

    protected TargetT target;

    public UserInputAssertion(InstrumentationTestCase testCase, Activity activity, TargetT target) {
        super(testCase, activity);
        this.target = target;
    }

    public ActionAssertion keyPress(final int... keyCodes) {
        return AssertionResolver.actionAssertion(testCase, activity, new Runnable() {
            public void run() {
                for (int code : keyCodes) {
                    instrumentation.sendKeyDownUpSync(code);
                }

                instrumentation.waitForIdleSync();
            }
        }, false);
    }

    public void satisfies(Predicate<TargetT> predicate) {
        assertTrue("the " + target.getClass().getSimpleName()
                + " did not satisfy the given condition", predicate.check(target));
    }

    public void satisfies(final String condition) {
        String[] words = WordUtils.capitalize(condition).split("\\s");
        if (words.length == 0) {
            throw new IllegalArgumentException("not a valid condition: " + condition);
        }

        words[0] = words[0].toLowerCase();
        String methodName = StringUtils.join(words);

        boolean result = false;
        try {
            Method method = target.getClass().getMethod(methodName, (Class<?>[]) null);
            result = (Boolean) method.invoke(target);
        } catch (Exception e) {
            throw new IllegalArgumentException("not a valid condition: " + condition);
        }

         assertTrue(condition + " was not satisfied", result);
    }

}
