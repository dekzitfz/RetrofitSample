package id.dekz.retrofitexample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by DEKZ on 8/24/2017.
 */

@RunWith(AndroidJUnit4.class)
public class ListViewTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testList(){
        onData(anything()).inAdapterView(withId(R.id.lv))
                .atPosition(0)
                .check(matches(hasDescendant(withText("Oreo"))));

        onData(anything()).inAdapterView(withId(R.id.lv))
                .atPosition(1)
                .check(matches(hasDescendant(withText("Nougat"))));

        onData(anything()).inAdapterView(withId(R.id.lv))
                .atPosition(0)
                .perform(click());
    }
}
