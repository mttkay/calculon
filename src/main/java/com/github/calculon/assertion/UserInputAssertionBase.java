package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import android.app.Activity;
import android.app.Instrumentation;
import android.view.KeyEvent;

import com.github.calculon.predicate.Predicate;

public abstract class UserInputAssertionBase<T> extends AssertionBase {

    protected T target;

    public UserInputAssertionBase(Activity activity, Instrumentation instrumentation) {
        super(activity, instrumentation);
    }

    public ActionAssertion keyDown(int keyCode) {
        final int finalKeyCode = keyCode;
        return new ActionAssertion(activity, instrumentation, new Runnable() {
            public void run() {
                UserInputAssertionBase.this.instrumentation.sendKeySync(new KeyEvent(
                    KeyEvent.ACTION_DOWN, finalKeyCode));
            }
        }, false);
    }

    public ActionAssertion keyUp(int keyCode) {
        final int finalKeyCode = keyCode;
        return new ActionAssertion(activity, instrumentation, new Runnable() {
            public void run() {
                UserInputAssertionBase.this.instrumentation.sendKeySync(new KeyEvent(
                    KeyEvent.ACTION_UP, finalKeyCode));
            }
        }, false);
    }

    public void satisfies(Predicate<T> predicate) {
        try {
            assertTrue(predicate.check(target));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Terminated unexpectedly by exception");
        }
    }
}
