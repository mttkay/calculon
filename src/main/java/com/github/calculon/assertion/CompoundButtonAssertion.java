package com.github.calculon.assertion;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.widget.CompoundButton;

public class CompoundButtonAssertion extends ViewAssertion {

    private CompoundButton compoundButton;

    public CompoundButtonAssertion(InstrumentationTestCase testCase, Activity activity,
            CompoundButton compoundButton) {
        super(testCase, activity, compoundButton);
        this.compoundButton = compoundButton;
    }

    public void checked() {
        assertTrue("expected view to be checked, but it wasn't", compoundButton.isChecked());
    }

    public void unchecked() {
        assertFalse("expected view to not be checked, but it was", compoundButton.isChecked());
    }
}
