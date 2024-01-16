package ohmyquiz.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import ohmyquiz.bussinesses.QuizBussiness;
import ohmyquiz.models.Quiz;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.io.IOException;


public class CreateQuizController implements Initializable {

    private QuizBussiness quizBussiness = new QuizBussiness();
    private Quiz quiz;
    private Quiz.Section section;
    private Quiz.Section.Question question;
    private Quiz.Section.Question.Answer answer;

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField titleSectionField;

    @FXML
    private Button SectionTitleForm;

    @FXML
    private TextField contentQuestionField;

    @FXML
    private TextField answerContentField;

    @FXML
    private ChoiceBox<String> statusComboBox;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeStatusComboBox();
    }

    private void initializeStatusComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList("Public", "Private");
        statusComboBox.setItems(options);
        statusComboBox.setValue(options.get(0)); 
    }

    @FXML
    public void createQuizButtonAction() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        
        if (title.isEmpty() || description.isEmpty()) {
            showErrorAlert("Title and description cannot be empty!");
            return;
        }

        quiz = new Quiz();
    Quiz.Section section = new Quiz.Section();
    Quiz.Section.Question question = new Quiz.Section.Question();
    Quiz.Section.Question.Answer answer = new Quiz.Section.Question.Answer();
    
    quiz.addSection(section);
    section.addQuestion(question);
    question.addAnswer(answer);

    quiz.setGuid(UUID.randomUUID().toString());
    quiz.setTitle(title);
    quiz.setDescription(description);
    quiz.setStatus(status);
    
    Status status = new Status(statusValue);

    quiz.setStatus(status);

    boolean success = quizBussiness.createQuizData(quiz);
    if (success) {
        showSuccessAlert("Quiz created successfully!");
    } else {
        showErrorAlert("Unable to create quiz!");
    }
}

    // @FXML
    // public void openSectionTitleForm() {
    //     try {
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SectionForm.fxml"));
    //         Parent root = loader.load();
    
    //         Stage stage = new Stage();
    //         stage.setTitle("Window Form");
    //         stage.initModality(Modality.WINDOW_MODAL);
    //         stage.initOwner(SectionTitleForm.getScene().getWindow());
    
    //         Scene scene = new Scene(root);
    //         stage.setScene(scene);
    
    //         stage.showAndWait();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    @FXML
public void addSectionButtonAction() {
    if (quiz == null) {
        showErrorAlert("Please create a quiz first!");
        return;
    }

    String sectionTitle = titleSectionField.getText();
    if (sectionTitle.isEmpty()) {
        showErrorAlert("Section title cannot be empty!");
        return;
    }


    Quiz.Section section = new Quiz.Section();
    

    quiz.addSection(section);
    

    boolean success = quizBussiness.createQuizData(quiz);
    
    if (success) {
        showSuccessAlert("Section added successfully!");
    } else {
        showErrorAlert("Unable to add section!");
    }
}

    @FXML
    public void addQuestionButtonAction() {
        if (quiz == null) {
            showErrorAlert("Please create a quiz first!");
            return;
        }

        if (section == null) {
            showErrorAlert("Please add a section first!");
            return;
        }

        String questionContent = contentQuestionField.getText().trim();
        if (questionContent.isEmpty()) {
            showErrorAlert("Question content cannot be empty!");
            return;
        }

        Quiz.Section.Question question = new Quiz.Section.Question();
        Quiz.Section.Question.Answer answer = new Quiz.Section.Question.Answer();
        question.addAnswer(answer);

        question.setContent(questionContent);
        section.addQuestion(question);
    }

    @FXML
private void addAnswerButtonAction() {
    if (section == null) {
        showErrorAlert("Please add a section first!");
        return;
    }

    String answerContent = answerContentField.getText().trim();
    if (answerContent.isEmpty()) {
        showErrorAlert("Answer content cannot be empty!");
        return;
    }

    Quiz.Section.Question.Answer answer = new Quiz.Section.Question.Answer();
    answer.setContent(answerContent);

    if (section.getQuestions() != null && !section.getQuestions().isEmpty()) {
        Quiz.Section.Question currentQuestion = section.getQuestions().get(section.getQuestions().size() - 1);
        currentQuestion.addAnswer(answer); 
    } else {
        showErrorAlert("Please add a question first!");
    }
}

    private void showErrorAlert(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", null, message);
    }

    private void showSuccessAlert(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Success", null, message);
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
