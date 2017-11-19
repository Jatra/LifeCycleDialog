package uk.co.jatra.lifecycledialog;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LiveDialogFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkButtonText() {
        onView(withId(R.id.button)).check(matches(withText(R.string.button_label)));
    }

    @Test
    public void shouldDisplayDialog() {
        onView(withId(R.id.button)).perform(click());
        onView(withText(R.string.dialog_title))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldUpdateTextPositive() {
        onView(withId(R.id.textView)).check(matches(withText(R.string.pending_text)));
        onView(withId(R.id.button)).perform(click());
        onView(withText(R.string.dialog_positive_button_label)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("POSITIVE")));
    }

    @Test
    public void shouldUpdateTextNegative() {
        onView(withId(R.id.button)).perform(click());
        onView(withText(R.string.dialog_negative_button_label)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("NEGATIVE")));
    }
}