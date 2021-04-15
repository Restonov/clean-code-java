package com.epam.engx.cleancode.functions.task2;

import com.epam.engx.cleancode.functions.task2.stubs.AccountStub;
import com.epam.engx.cleancode.functions.task2.stubs.NotRegisteredAccountStub;
import com.epam.engx.cleancode.functions.task2.stubs.NotVisitingAccountStub;
import com.epam.engx.cleancode.functions.task2.stubs.ReviewStub;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Level;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.NotActiveUserException;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private TreeMap<Integer, Level> levels;
    private Level level1;
    private Level level2;

    @Before
    public void setUp() {
        levels = new TreeMap<>();
        level1 = new Level("1");
        level2 = new Level("2");
        levels.put(10, level1);
        levels.put(30, level2);
    }

    @Test (expected = NotActiveUserException.class)
    public void shouldThrowExceptionWhenAccountIsNotRegistered() {
        UserAccount accountStub = new NotRegisteredAccountStub();
        accountStub.retrieveActivityLevel();
    }

    @Test (expected = NotActiveUserException.class)
    public void shouldThrowExceptionWhenAccountIsNotVisiting() {
        UserAccount accountStub = new NotVisitingAccountStub();
        accountStub.retrieveActivityLevel();
    }

    @Test
    public void shouldGetDefaultLevelWhenThereAreNoAnswers() {
        UserAccount accountStub = new AccountStub(Collections.<Review>singletonList(new ReviewStub(0)));
        accountStub.setLevels(levels);
        assertEquals(Level.defaultLevel(), accountStub.retrieveActivityLevel());
    }

    @Test
    public void shouldGetDefaultLevelWhenThereAreNotEnoughAnswers() {
        UserAccount accountStub = new AccountStub(Collections.<Review>singletonList(new ReviewStub(9)));
        accountStub.setLevels(levels);
        assertEquals(Level.defaultLevel(), accountStub.retrieveActivityLevel());
    }

    @Test
    public void shouldGetFirstLevelWhenThereAreEnoughAnswers() {
        UserAccount accountStub = new AccountStub(Collections.<Review>singletonList(new ReviewStub(10)));
        accountStub.setLevels(levels);
        assertEquals(level1, accountStub.retrieveActivityLevel());
    }

    @Test
    public void shouldGetFirstLevelWhenThereAreEnoughSumOfAnswers() {
        UserAccount accountStub = new AccountStub(Arrays.<Review>asList(new ReviewStub(5), new ReviewStub(5)));
        accountStub.setLevels(levels);
        assertEquals(level1, accountStub.retrieveActivityLevel());
    }

    @Test
    public void shouldGetSecondLevelWhenThereAreEnoughAnswers() {
        UserAccount accountStub = new AccountStub(Collections.<Review>singletonList(new ReviewStub(30)));
        accountStub.setLevels(levels);
        assertEquals(level2, accountStub.retrieveActivityLevel());
    }
}
