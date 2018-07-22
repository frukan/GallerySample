package net.furkanakdemir.myapplication;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.NumberPicker;
import net.furkanakdemir.myapplication.view.MainActivity;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class GalleryTest {

    @Rule public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    private CountingIdlingResource idlingResource;

    @Before
    public void registerIdlingResources() {
        idlingResource = mActivityRule.getActivity().getEspressoIdlingResourceForMainActivity();

        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void testInitialLoad() {

        // DEFAULT_SOURCE_COUNT = 24;
        // DEFAULT_COLUMN_COUNT = 6;
        onView(withId(R.id.gridview_main)).check(matches(hasChildCount(4)));
    }

    @Test
    public void testColumnCountLoad() {

        onView(withId(R.id.column)).perform(click());

        ViewInteraction numPicker = onView(withId(R.id.numberpicker_column));
        numPicker.perform(setNumber(3));

        onView(withText("OK")).perform(click());

        // DEFAULT_SOURCE_COUNT = 24;
        // DEFAULT_COLUMN_COUNT = 3;
        onView(withId(R.id.gridview_main)).check(matches(hasChildCount(8)));
    }

    @Test
    public void testSourceCountLoad() {

        onView(withId(R.id.source)).perform(click());

        ViewInteraction numPicker = onView(withId(R.id.numberpicker_source));
        numPicker.perform(setNumber(13));

        onView(withText("OK")).perform(click());

        // DEFAULT_SOURCE_COUNT = 13;
        // DEFAULT_COLUMN_COUNT = 6;
        onView(withId(R.id.gridview_main)).check(matches(hasChildCount(3)));
    }

    @Test
    public void testSourceCountDialogDisplayed() {

        onView(withId(R.id.source)).perform(click());
        onView(withText(R.string.title_dialog_source)).check(matches(isDisplayed()));
    }

    @Test
    public void testColumnCountDialogDisplayed() {

        onView(withId(R.id.column)).perform(click());
        onView(withText(R.string.title_dialog_column)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }

    public static ViewAction setNumber(final int num) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController,
                                View view) {
                NumberPicker np = (NumberPicker) view;
                np.setValue(num);
            }

            @Override
            public String getDescription() {
                return "Set the passed number into the NumberPicker";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(NumberPicker.class);
            }
        };
    }


}
