package com.iotta.challenge.repositorylist;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.iotta.challenge.R;
import com.iotta.challenge.model.api.ApiClient;
import com.iotta.challenge.model.api.ApiInterface;
import com.iotta.challenge.model.pojo.Owner;
import com.iotta.challenge.model.pojo.Repository;
import com.iotta.challenge.model.repositoriesmgr.IRepositoriesManager;
import com.iotta.challenge.model.repositoriesmgr.RepositoriesManager;
import com.iotta.challenge.utils.JavaUtils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RepositoryListActivityTestSortAndClick {

    private UiDevice device;
    private static final String PACKAGE = "com.iotta.challenge";
    private static final int LAUNCH_TIMEOUT = 20000;
    private static HashMap<String, Long> TEST_LANGUAGES_LIST = new HashMap<>();
    private static Owner TEST_OWNER;
    private static Repository TEST_LIST_REPOSITORY;

    @Rule
    public ActivityTestRule<RepositoryListActivity> mActivityTestRule = new ActivityTestRule<>(RepositoryListActivity.class);

    @Before
    public void init(){
        TEST_LANGUAGES_LIST.put("Groovy", 107001l);
        TEST_OWNER = new Owner("https://avatars3.githubusercontent.com/u/2835506?v=3", "HERE", "https://github.com/heremaps", null);
        TEST_LIST_REPOSITORY = new Repository(TEST_OWNER, "93070513", "gradle-job-dsl-plugin", "A plugin for Gradle to manage Jenkins Job DSL projects.",
                JavaUtils.convert("2017-07-05T15:05:35Z"), false, 2, TEST_LANGUAGES_LIST);
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        device.pressHome();

        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }


    @Test
    public void repositoryListActivityTestSortDescending() {
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.sortSpinner),
                        withParent(allOf(withId(R.id.tasksLL),
                                withParent(withId(R.id.tasksContainer)))),
                        isDisplayed()));
        try {
            appCompatSpinner.perform(click());

            ViewInteraction appCompatCheckedTextView = onView(
                    allOf(withId(android.R.id.text1), withText("Sort: newest to oldest"), isDisplayed()));

            appCompatCheckedTextView.perform(click());


            ViewInteraction recyclerView = onView(allOf(withId(R.id.repositories_list), withParent(allOf(withId(R.id.tasksLL), withParent(withId(R.id.tasksContainer)))),
                    isDisplayed()));
            recyclerView.perform(actionOnItemAtPosition(0, click()));
            onData(allOf(is(instanceOf(List.class)), is(withContent(TEST_LIST_REPOSITORY))));
        } catch (NoMatchingViewException e) {
            onView(allOf(withId(R.id.emptyRepositoryLayout), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        }
    }


    @Test
    public void repositoryListActivityTestSortAsc() {
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.sortSpinner),
                        withParent(allOf(withId(R.id.tasksLL),
                                withParent(withId(R.id.tasksContainer)))),
                        isDisplayed()));

      try{
        appCompatSpinner.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1), withText("Sort: oldest to newest"), isDisplayed()));

        appCompatCheckedTextView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.repositories_list),
                        withParent(allOf(withId(R.id.tasksLL),
                                withParent(withId(R.id.tasksContainer)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onData(allOf(is(instanceOf(List.class)), is(withContent( TEST_LIST_REPOSITORY ))));//.perform(click());
        } catch (NoMatchingViewException e) {
            onView(allOf(withId(R.id.emptyRepositoryLayout), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        }
    }

    public static Matcher<Repository> withContent(final Repository content) {
        return new BoundedMatcher<Repository, Repository>(Repository.class) {

            @Override
            public boolean matchesSafely(Repository myObj) {
                return myObj.equals(content);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("With content '" + content + "'");
            }
        };
    }

}
