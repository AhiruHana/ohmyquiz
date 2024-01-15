package ohmyquiz.dataAccesses;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import ohmyquiz.models.Quiz;

public class QuizDataAccess {

    public boolean createQuizData(Quiz quiz) {
        try {
            try (var connection = Connection.createConnection()) {
                MongoDatabase database = connection.getDatabase("OhMyQuiz");
                MongoCollection<Document> collection = database.getCollection("Quiz");

                Document quizDocument = new Document()
                        .append("guid", quiz.getGuid())
                        .append("creatorGuid", quiz.getCreatorGuid())
                        .append("title", quiz.getTitle())
                        .append("description", quiz.getDescription())
                        .append("createdAt", quiz.getCreatedAt())
                        .append("updatedAt", quiz.getUpdatedAt())
                        .append("status", (quiz.getStatus() != null) ? quiz.getStatus().toString() : null);

                List<Document> sectionDocuments = new ArrayList<>();
                for (Quiz.Section section : quiz.getSections()) {
                    Document sectionDocument = new Document()
                            .append("sectionGuid", section.getSectionGuid())
                            .append("title", section.getTitle());

                    List<Document> questionDocuments = new ArrayList<>();
                    for (Quiz.Section.Question question : section.getQuestions()) {
                        Document questionDocument = new Document()
                                .append("questionGuid", question.getQuestionGuid())
                                .append("content", question.getContent())
                                .append("title", question.getTitle())
                                .append("difficulty", (question.getDifficulty() != null) ? question.getDifficulty().toString() : null);

                        List<Document> answerDocuments = new ArrayList<>();
                        for (Quiz.Section.Question.Answer answer : question.getAnswers()) {
                            Document answerDocument = new Document()
                                    .append("answerGuid", answer.getAnswerGuid())
                                    .append("content", answer.getContent());

                            answerDocuments.add(answerDocument);
                        }

                        questionDocument.append("answers", answerDocuments);
                        questionDocuments.add(questionDocument);
                    }

                    sectionDocument.append("questions", questionDocuments);
                    sectionDocuments.add(sectionDocument);
                }

                quizDocument.append("sections", sectionDocuments);
                collection.insertOne(quizDocument);
            } // Connection will be closed automatically due to try-with-resources
            return true;
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log or rethrow)
            e.printStackTrace();
            return false;
        }
    }
}
