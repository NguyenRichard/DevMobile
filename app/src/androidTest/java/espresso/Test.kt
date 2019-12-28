package espresso

import androidx.test.ext.junit.rules.activityScenarioRule
import android.app.Activity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.util.Log
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


@RunWith(AndroidJUnit4::class)
@LargeTest
class Test {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    companion object {
        const val USER_NAME = "richard nguyen"
        const val NAME_TASK_EXAMPLE_1 = "Task 1"
        const val DESC_TASK_EXAMPLE_1 = "Description of task 1 :)"
        const val NAME_TASK_EXAMPLE_2 = "Task 2"
        const val DESC_TASK_EXAMPLE_2 = "Description of task 2 :)"
        const val INDEX_DEL_TASK = 2
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    @Test fun headerFragment(){
        onView(withId(R.id.user_name)).check(matches(withText("Hi $USER_NAME")))
    }


    @Test
    fun initialTaskFragment(){
        // OK   //onView(withId(R.id.testView)).check(matches(withText("TextView TEST")))
        // OK   //onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(0, R.id.task_delet_button)).check(matches(isDisplayed()))
        //onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(1, R.id.task_title)).check(matches((withText("Je teste"))))
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(0, R.id.task_title)).check(matches((withText("My first task: "))))
        //Espresso.onView(ViewMatchers.withId(R.id.tasks_recycler_view)).check(matches((withText("My first task:"))))   //.perform(RecyclerViewActions.actionOnItemAtPosition<TasksAdapter.TaskViewHolder>(0, click()))
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

    //TODO check create button quand la tache est vide ? Meme effet que backButton pour l'instant

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
    fun deleteTask(){
        onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(INDEX_DEL_TASK, R.id.task_delet_button)).perform(
            click())
        try {
            onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(INDEX_DEL_TASK, R.id.task_delet_button)).check(doesNotExist())
        }
        catch (e : NullPointerException){

        }
    }


    // nettoie toutes les tâches

    /*
    @Test

    fun cleanTasks(){
        for (i in 0..findViewById()){
            onView(withRecyclerView(R.id.tasks_recycler_view).atPositionOnView(i, R.id.task_delet_button)).perform(
                click())
        }
    }
    
     */
}