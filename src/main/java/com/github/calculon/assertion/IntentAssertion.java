package com.github.calculon.assertion;

import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.InstrumentationTestCase;

public class IntentAssertion extends TargetedAssertion<Intent> {

	public IntentAssertion(InstrumentationTestCase testCase, Activity activity,
			Intent startedActivityIntent) {
		super(testCase, activity, startedActivityIntent);
	}

	public void hasExtras(String... extras) {
		Bundle intentExtras = target.getExtras();
		for (String extra : extras) {
			assertTrue("Expected that intent had extra '" + extra + "', but it was null", intentExtras.get(extra) != null);			
		}
	}

}
