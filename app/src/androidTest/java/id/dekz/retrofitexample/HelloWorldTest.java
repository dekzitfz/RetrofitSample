package id.dekz.retrofitexample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by DEKZ on 8/24/2017.
 */

@RunWith(AndroidJUnit4.class)
public class HelloWorldTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateHelloWorld(){
        onView(withId(R.id.tvHello)).check(matches(withText("Hello World!")));
    }

}
