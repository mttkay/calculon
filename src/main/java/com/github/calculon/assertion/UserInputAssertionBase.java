package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonStoryTest;
import com.github.calculon.predicate.Predicate;

public abstract class UserInputAssertionBase<T> extends AssertionBase {

    protected T target;

    public UserInputAssertionBase(CalculonStoryTest testCase, Activity activity, Instrumentation instrumentation) {
        super(testCase, activity, instrumentation);
    }

    public ActionAssertion keyPress(int... keyCodes) {
        final int[] codes = keyCodes;
        return new ActionAssertion(testCase, activity, instrumentation, new Runnable() {
            public void run() {
            	for (int code : codes) {
            		UserInputAssertionBase.this.testCase.sendKeys(code);
            	}
            }
        }, false);
    }

//    public ActionAssertion clickMenuItem(int menuItemIndex) {
//    	final int itemIndex = menuItemIndex;
//        return new ActionAssertion(activity, instrumentation, new Runnable() {
//            public void run() {
//            	/**
//                UserInputAssertionBase.this.instrumentation.sendKeySync(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MENU));
//                for (int i = 0; i<itemIndex; i++) {
//                    UserInputAssertionBase.this.instrumentation.sendKeySync(new KeyEvent(
//                            KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MENU));
//                }
//                UserInputAssertionBase.this.instrumentation.sendKeySync(new KeyEvent(
//                        KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN));
//                */
//            	
//            }
//        }, true);
//    }

    public void satisfies(Predicate<T> predicate) {
        try {
            assertTrue(predicate.check(target));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Terminated unexpectedly by exception");
        }
    }
}
