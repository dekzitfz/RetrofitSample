package id.dekz.retrofitexample;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
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

    private MyIdlingRes idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){
        idlingResource = rule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void unregisterIdlingResource(){
        if(idlingResource != null) Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void testList(){
        onData(anything()).inAdapterView(withId(R.id.lv))
                .atPosition(0)
                .check(matches(hasDescendant(withText("mojombo"))));
    }
}
