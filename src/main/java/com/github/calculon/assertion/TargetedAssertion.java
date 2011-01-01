package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;

import java.lang.reflect.Method;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import android.app.Activity;
import android.test.InstrumentationTestCase;

import com.github.calculon.predicate.Predicate;

public abstract class TargetedAssertion<TargetT> extends AssertionBase {

    protected TargetT target;

    public TargetedAssertion(InstrumentationTestCase testCase, Activity activity, TargetT target) {
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

        // check whether the condition is a negation
        boolean negate = false;
        if (words.length > 1 && "not".equalsIgnoreCase(words[1]) || "no".equalsIgnoreCase(words[1])) {
            words = (String[]) ArrayUtils.remove(words, 1);
            negate = true;
        }

        String methodName = StringUtils.join(words);

        boolean result = false;
        try {
            Method method = target.getClass().getMethod(methodName, (Class<?>[]) null);
            result = (Boolean) method.invoke(target);
        } catch (Exception e) {
            throw new IllegalArgumentException("not a valid condition or method not accessible: '"
                    + condition + "' (translated to '" + methodName + "')");
        }

        assertTrue(condition + " was not satisfied", negate ? !result : result);
    }
}
