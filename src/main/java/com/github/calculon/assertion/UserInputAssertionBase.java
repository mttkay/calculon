package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import android.app.Activity;
import android.app.Instrumentation;

import com.github.calculon.CalculonStoryTest;
import com.github.calculon.predicate.Predicate;

public abstract class UserInputAssertionBase<TargetT, ActivityT extends Activity> extends
        AssertionBase<ActivityT> {

    protected TargetT target;

    public UserInputAssertionBase(CalculonStoryTest<ActivityT> testCase, Activity activity,
            Instrumentation instrumentation) {
        super(testCase, activity, instrumentation);
    }

    public ActionAssertion<ActivityT> keyPress(final int... keyCodes) {
        return new ActionAssertion<ActivityT>(testCase, activity, instrumentation, new Runnable() {
            public void run() {
                UserInputAssertionBase.this.testCase.sendKeys(keyCodes);
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
