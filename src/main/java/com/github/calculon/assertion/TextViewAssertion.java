package com.github.calculon.assertion;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import android.app.Activity;
import android.test.InstrumentationTestCase;
import android.test.MoreAsserts;
import android.widget.TextView;

public class TextViewAssertion extends ViewAssertion {

    private TextView textView;

    public TextViewAssertion(InstrumentationTestCase testCase, Activity activity, TextView textView) {
        super(testCase, activity, textView);
        this.textView = textView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionAssertion<TextViewAssertion> click() {
        return (ActionAssertion<TextViewAssertion>) super.click();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ActionAssertion<TextViewAssertion> longClick() {
        return (ActionAssertion<TextViewAssertion>) super.longClick();
    }

    public void reads(String text) {
        assertEquals("text mismatch:", text, textView.getText());
    }

    public void contains(String text) {
        assertTrue("expected text to contain '" + text + "', but it didn't", textView.getText()
                .toString().contains(text));
    }

    public void matches(String regex) {
        MoreAsserts.assertMatchesRegex(regex, textView.getText().toString());
    }

}
