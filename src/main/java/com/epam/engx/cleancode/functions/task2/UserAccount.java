package com.epam.engx.cleancode.functions.task2;


import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Level;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.NotActiveUserException;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;

import static org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserAccount implements User {
    private TreeMap<Integer, Level> levels;

    public Level retrieveActivityLevel() {
        checkIsUserRegisteredAndActive();
        return retrieveActivityLevelByReviewAnswers(retrieveReviewAnswers());
    }

    private void checkIsUserRegisteredAndActive() {
        if (!isRegistered() || getVisitNumber() <= INTEGER_ZERO) {
            throw new NotActiveUserException();
        }
    }

    private Level retrieveActivityLevelByReviewAnswers(int reviewAnswers) {
        Level level = Level.defaultLevel();
        for (Integer reviewAnswersThreshold : levels.keySet()) {
            if (reviewAnswers >= reviewAnswersThreshold) {
                level = levels.get(reviewAnswersThreshold);
            }
        }
        return level;
    }

    private int retrieveReviewAnswers() {
        int reviewAnswers = 0;
        for (Review review : getAllReviews()) {
            reviewAnswers += review.getAnswers().size();
        }
        return reviewAnswers;
    }
}
