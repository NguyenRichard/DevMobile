package espresso

import androidx.test.ext.junit.rules.activityScenarioRule
import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.rng.tpapp.MainActivity
import com.rng.tpapp.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class Test{

    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()

    companion object{
        const val USER_NAME = "richard nguyen"
    }

    @Test fun headerFragment(){
        onView(withId(R.id.user_name)).check(matches(withText("Hi $USER_NAME")))
    }
}