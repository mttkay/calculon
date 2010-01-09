![Calculon](http://github.com/kaeppler/calculon/raw/master/assets/calculon.png)

## "Calculon never does two takes!"

Calculon is a testing DSL for Google Android. It allows you to write functional tests using cool stuff like this:

    public class FooTest extends FunctionalTest<FooActivity> {

        public FooTest() {
            super("com.example", FooActivity.class);
        }

        public void testFooAndBarStuff() {

            // direct assertion on current activity
            assertThat().inPortraitMode();
            assertThat().viewExists(R.id.launch_bar_button);

            // assert specific condition on current activity
            assertThat().satisfies(new Predicate<Activity>() {
                public boolean check(Activity target) {
                    return target.isTaskRoot();
                }
            });

            // a view assertion that performs an activity check
            Activity barActivity = assertThat(R.id.launch_bar_button).click().starts(BarActivity.class);
            // other activity objects are also testable
            assertThat(barActivity).inLandscapeMode();

            // a direct view assertion
            assertThat(R.id.button_2).isVisible();
            // a view assertion that performs a check on another view
            assertThat(R.id.button_2).click().implies(R.id.launch_bar_button).isGone();
            // assert a specific condition on another view
            assertThat(R.id.button_2).click().implies(R.id.launch_bar_button).satisfies(
                new Predicate<View>() {
                    public boolean check(View view) {
                        return view.getVisibility() == View.GONE;
                    }
                });

            // a key event assertion on an activity
            assertThat(barActivity).keyDown(KeyEvent.KEYCODE_X).finishesActivity();
        }
    }

