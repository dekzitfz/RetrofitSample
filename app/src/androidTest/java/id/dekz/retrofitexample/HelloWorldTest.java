package id.dekz.retrofitexample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by DEKZ on 8/24/2017.
 */

@RunWith(AndroidJUnit4.class)
public class HelloWorldTest {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class);



}
