package com.example.yummychina;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.util.Log;


import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Inject
    FirebaseAuth mAuth;

    //ActivityScenario<LoginActivity> scenario;
    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule
            = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setUp() throws Exception {
        //scenario = ActivityScenario.launch(LoginActivity.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onStart() {
    }

//    @Test
//    public void clickingSignUp_shouldStarSignUpActivity() {
//        SignUpActivity activity = Robolectric.setupActivity(SignUpActivity.class);
//        activity.findViewById(R.id.login).performClick();
//
//        Intent expectedIntent = new Intent(activity, LoginActivity.class);
//        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
//        assertEquals(expectedIntent.getComponent(), actual.getComponent());
//    }

    @Test
    public void loginTest() throws Exception {
        //activityScenarioRule.getScenario().moveToState(Lifecycle.State.STARTED);
        onView(withId(R.id.username)).perform(typeText("huici1@test.com"),
                closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("shichangch"),
                closeSoftKeyboard());
        onView(withId(R.id.login_btn)).perform(click());
    }
}