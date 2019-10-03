package de.deliveryhero.mailordercoffeeshop;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoWorkshopTest {

    @Rule
    public ActivityTestRule < MainActivity > mActivityTestRule = new ActivityTestRule < > (MainActivity.class);

    @Test
    public void espressoWorkShopTask() {


        //Assertion here to make sure that close button exists
        ViewInteraction introductionPageCloseButton = onView((withId(R.id.close_button)));
        introductionPageCloseButton.check(matches(isDisplayed()));


        //Click the close button on introduction page
        ViewInteraction appCompatImageButton = onView((withId(R.id.close_button)));
        appCompatImageButton.perform(click());


        //Make sure that Menu button exists
        ViewInteraction menuButton = onView((withId(R.id.use_menu)));
        menuButton.check(matches(isDisplayed()));


        //Click on menu button
        ViewInteraction materialButton = onView(allOf(withId(R.id.use_menu), withText("Menu")));
        materialButton.perform(click());


        //Select Cappuccino as my beverage
        ViewInteraction constraintLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.beverage_recycler_view),
                                childAtPosition(
                                        withId(R.id.order_fragment_container),
                                        0)),
                        2),
                        isDisplayed()));
        constraintLayout.perform(click());


        //Input my First name as "Sherif"
        ViewInteraction textInputEditText = onView((withId(R.id.name_text_box)));
        textInputEditText.perform(replaceText("sherif"), closeSoftKeyboard());


        //Input my Email as sherif.hamad@live.com, I didn't check entry of wrong format
        ViewInteraction textInputEditText2 = onView((withId(R.id.email_text_box)));
        textInputEditText2.perform(replaceText("sherif.hamad@live.com"), closeSoftKeyboard());

        //Click on Submit order
        ViewInteraction materialButton2 = onView(allOf(withId(R.id.mail_order_button), withText("Submit order")));
        materialButton2.perform(scrollTo(), click());
    }

    private static Matcher < View > childAtPosition(
            final Matcher < View > parentMatcher, final int position) {

        return new TypeSafeMatcher < View > () {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) &&
                        view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}