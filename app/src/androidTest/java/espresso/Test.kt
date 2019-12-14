package espresso

import androidx.test.ext.junit.rules.activityScenarioRule
import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
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
class Test {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    companion object {
        const val USER_NAME = "richard nguyen"
        const val NAME_TASK_EXAMPLE = "Task 1"
        const val DESC_TASK_EXAMPLE = "Description of task 1 :)"
    }


    @Test fun headerFragment(){
        onView(withId(R.id.user_name)).check(matches(withText("Hi $USER_NAME")))
    }


    @Test
    fun initialTaskFragment(){
        onView(withId(R.id.tasks_recycler_view)).check(matches(withText("My first task")))
    }
/*
    @Test
    fun clickCreateTaskButton() {
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.task_title_create_input)).check(matches(isDisplayed()))
        onView(withId(R.id.task_description_create_input)).check(matches(isDisplayed()))
        onView(withId(R.id.button_createTask)).check(matches(isDisplayed()))
        onView(withId(R.id.button_back)).check(matches(isDisplayed()))
    }

    @Test
    fun createTask_isTextChanged(){
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.task_title_create_input)).perform(typeText(NAME_TASK_EXAMPLE))
        onView(withId(R.id.task_title_create_input)).check(matches(withText(NAME_TASK_EXAMPLE)))
        onView(withId(R.id.task_description_create_input)).perform(typeText(DESC_TASK_EXAMPLE))
        onView(withId(R.id.task_description_create_input)).check(matches(withText(DESC_TASK_EXAMPLE)))
    }

    @Test
    fun createTask_clickBackButton(){
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.button_back)).perform((click()))
        // if we clicked on back button, then we can see USER_NAME again
        onView(withId(R.id.user_name)).check(matches(withText("Hi $USER_NAME")))
    }

    //TODO check create button quand la tache est vide ? Meme effet que backButton pour l'instant

    @Test
    fun createTask() {
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.button_back)).perform((click()))
        onView(withId(R.id.task_title_create_input)).perform(typeText(NAME_TASK_EXAMPLE))
        onView(withId(R.id.task_description_create_input)).perform(typeText(DESC_TASK_EXAMPLE))
        onView(withId(R.id.button_createTask)).perform(click())
        // is the task created
        //onView(withId(R.id.tasks_recycler_view)).check(matches(withText("My first task")))
    }

     */
}