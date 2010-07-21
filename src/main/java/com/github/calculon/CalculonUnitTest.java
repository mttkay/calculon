package com.github.calculon;


import android.app.Activity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;

import com.github.calculon.unit.assertion.UnitTestActivityAssertion;
import com.github.calculon.unit.assertion.UnitTestViewAssertion;


public abstract class CalculonUnitTest<ActivityT extends Activity> 
				extends ActivityUnitTestCase<ActivityT> {

	private Class<ActivityT> mActivityClass = null;

	public CalculonUnitTest(Class<ActivityT> activityClass) {
		super(activityClass);
		mActivityClass = activityClass;
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		startActivity(new Intent(getInstrumentation().getTargetContext(), 
				mActivityClass.getClass()), null, null);
	}

	protected UnitTestActivityAssertion<ActivityT> assertThat() {
		return new UnitTestActivityAssertion<ActivityT>(this, getActivity(), getInstrumentation());
	}
	
	protected UnitTestActivityAssertion<ActivityT> assertThat(Activity activity) {
		return new UnitTestActivityAssertion<ActivityT>(this, activity, getInstrumentation());
	}

	protected UnitTestViewAssertion<ActivityT> assertThat(View view) {
		return new UnitTestViewAssertion<ActivityT>(this, getActivity(), getInstrumentation(), view);
	}

	protected UnitTestViewAssertion<ActivityT> assertThat(int viewId) {	
		View view = getActivity().findViewById(viewId);
		return assertThat(view);
	}
}
