package espresso

import androidx.test.ext.junit.rules.activityScenarioRule
import android.app.Activity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.rng.tpapp.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import java.lang.NullPointerException
import java.util.regex.Matcher
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.Matchers.`is`





@RunWith(AndroidJUnit4::class)
@LargeTest
class Test {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    companion object {
        const val USER_NAME = "Jordan Aurey "
        const val NAME_TASK_EXAMPLE_1 = "Task 1"
        const val DESC_TASK_EXAMPLE_1 = "Description of task 1 :)"
        const val NAME_TASK_EXAMPLE_2 = "Task 2"
        const val DESC_TASK_EXAMPLE_2 = "Description of task 2 :)"
        const val INDEX_DEL_TASK = 1
        const val INDEX_EDIT_TASK = 1
        const val NEW_NAME_TASK_EXAMPLE_ = "NEW title of task"
        const val NEW_DESCRIPTION_TASK_EXAMPLE_ = "NEW Description of task :)"
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    @Test fun headerFragment(){
        onView(withId(R.id.user_name)).check(matches(withText("Hi $USER_NAME")))
    }


    @Test
    fun initialTaskFragment(){
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(0, R.id.task_title)).check(matches((withText("My first task: "))))
    }

    @Test
    fun clickCreateFormTaskButton() {
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.task_title_create_input)).check(matches(isDisplayed()))
        onView(withId(R.id.task_description_create_input)).check(matches(isDisplayed()))
        onView(withId(R.id.button_createTask)).check(matches(isDisplayed()))
        onView(withId(R.id.button_back)).check(matches(isDisplayed()))
    }

    @Test
    fun createTask_isTextChanged(){
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.task_title_create_input)).perform(typeText(NAME_TASK_EXAMPLE_1))
        onView(withId(R.id.task_title_create_input)).check(matches(withText(NAME_TASK_EXAMPLE_1)))
        onView(withId(R.id.task_description_create_input)).perform(typeText(DESC_TASK_EXAMPLE_1))
        onView(withId(R.id.task_description_create_input)).check(matches(withText(DESC_TASK_EXAMPLE_1)))
    }

    @Test
    fun createTask_clickBackButton(){
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.button_back)).perform((click()))
        // if we clicked on back button, then we can see USER_NAME again
        onView(withId(R.id.user_name)).check(matches(withText("Hi $USER_NAME")))
    }




    @Test
    fun createTask_checkTitle() {
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.task_title_create_input)).perform(typeText(NAME_TASK_EXAMPLE_1), closeSoftKeyboard())
        onView(withId(R.id.task_description_create_input)).perform(typeText(DESC_TASK_EXAMPLE_1), closeSoftKeyboard())
        onView(withId(R.id.button_createTask)).perform(click())
        // is the task created ?
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(1, R.id.task_title)).check(matches((withText("$NAME_TASK_EXAMPLE_1: "))))
    }

    @Test
    fun createTask_checkDescription() {
        onView(withId(R.id.button_addTask)).perform(click())
        onView(withId(R.id.task_title_create_input)).perform(typeText(NAME_TASK_EXAMPLE_2), closeSoftKeyboard())
        onView(withId(R.id.task_description_create_input)).perform(typeText(DESC_TASK_EXAMPLE_2), closeSoftKeyboard())
        onView(withId(R.id.button_createTask)).perform(click())
        // is the task created ?
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(2, R.id.task_description)).check(matches((withText(DESC_TASK_EXAMPLE_2))))
    }



    @Test
    fun editTask_title(){
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(INDEX_EDIT_TASK, R.id.task_edit_button)).perform(
            click())
        onView(withId(R.id.task_title_create_input)).perform(clearText())
        onView(withId(R.id.task_title_create_input)).perform(typeText(NEW_NAME_TASK_EXAMPLE_), closeSoftKeyboard())
        onView(withId(R.id.button_createTask)).perform(click())
        //check title
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(INDEX_EDIT_TASK, R.id.task_title)).check(matches((withText("$NEW_NAME_TASK_EXAMPLE_: "))))
    }

    @Test
    fun editTask_description(){
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(INDEX_EDIT_TASK, R.id.task_edit_button)).perform(
            click())
        onView(withId(R.id.task_description_create_input)).perform(clearText())
        onView(withId(R.id.task_description_create_input)).perform(typeText(
            NEW_DESCRIPTION_TASK_EXAMPLE_), closeSoftKeyboard())
        onView(withId(R.id.button_createTask)).perform(click())
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(INDEX_EDIT_TASK, R.id.task_description)).check(matches((withText(
            NEW_DESCRIPTION_TASK_EXAMPLE_))))
    }

    @Test
    fun deleteTask(){
        try {
            onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(INDEX_DEL_TASK, R.id.task_delet_button)).check(doesNotExist())
        }
        catch (e : NullPointerException){

        }
    }



}