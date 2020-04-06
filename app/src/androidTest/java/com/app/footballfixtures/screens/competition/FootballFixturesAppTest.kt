package com.app.footballfixtures.screens.competition


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.app.footballfixtures.HomeActivity
import com.app.footballfixtures.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FootballFixturesAppTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun footballFixturesAppTest() {
        Thread.sleep(3000)
        val textView = onView(
            allOf(
                withId(R.id.first_player), withText("1. FC Nürnberg"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fixtures_recyclerview),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("1. FC Nürnberg")))

        Thread.sleep(3000)
        val viewGroup = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.fixtures_recyclerview),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))

        Thread.sleep(3000)
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_competition), withContentDescription("Competitions"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        Thread.sleep(3000)
        val recyclerView = onView(
            allOf(
                withId(R.id.competitions_recyclerview),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(11, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(3000)

        val tabView = onView(
            allOf(
                withContentDescription("Table"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        Thread.sleep(3000)
        val tabView2 = onView(
            allOf(
                withContentDescription("Fixtures"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView2.perform(click())

        Thread.sleep(3000)
        val tabView3 = onView(
            allOf(
                withContentDescription("Teams"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        tabView3.perform(click())

        Thread.sleep(3000)
        val recyclerView2 = onView(
            allOf(
                withId(R.id.team_recyclerview),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(16, click()))

        Thread.sleep(3000)
        val appCompatImageView = onView(
            allOf(
                withId(R.id.imageView2),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout2),
                        childAtPosition(
                            withClassName(`is`("android.support.constraint.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        Thread.sleep(3000)
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withId(R.id.appbar),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
