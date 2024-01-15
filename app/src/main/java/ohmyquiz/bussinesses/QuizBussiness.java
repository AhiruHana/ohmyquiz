package ohmyquiz.bussinesses;

import ohmyquiz.dataAccesses.QuizDataAccess;
import ohmyquiz.models.Quiz;

public class QuizBussiness {
    private QuizDataAccess quizDataAccess = new QuizDataAccess();

    public boolean createQuizData(Quiz quiz) {
        return quizDataAccess.createQuizData(quiz);
    }

}
