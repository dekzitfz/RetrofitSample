package id.dekz.retrofitexample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by DEKZ on 8/24/2017.
 */

@RunWith(AndroidJUnit4.class)
public class OrderTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCalculate(){
        String inputed = "3";
        String resultExpected = "7500";
        onView(withId(R.id.etTotal)).perform(typeText(inputed));
        onView(withId(R.id.btn)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText(resultExpected)));
    }
}