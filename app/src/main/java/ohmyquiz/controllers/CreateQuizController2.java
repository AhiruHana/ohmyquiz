package ohmyquiz.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.bson.Document;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ohmyquiz.App;
import ohmyquiz.bussinesses.UserBussiness;
import ohmyquiz.dataAccesses.QuizDataAccess;

import javafx.scene.Node;
public class CreateQuizController2 implements Initializable {
    @FXML
    private Button AddSC;

    @FXML
    private HBox OpenSectionForm;

    @FXML
    private VBox menuBarVbox;

    @FXML
    private ScrollPane scrollPane;


    private QuizDataAccess qz;

    @FXML
    private TextField WriteSection;
    
    @FXML
    private Accordion AllSection;


    @FXML
    private void  ClickToAddSC() {
        qz = new QuizDataAccess();
        String sectionText = WriteSection.getText();
        qz.AddSection(sectionText);
    }


    

    
    private void addQuestionButtons(VBox questionButtonsVBox, List<Document> questions) {
        for (int j = 0; j < questions.size(); j++) {
            Document question = questions.get(j);
            String questionTitle = question.getString("content");
    
            Button questionButton = new Button("Question " + (j + 1) + ": " + questionTitle);
            questionButton.getStyleClass().add("button");
            questionButton.setPrefSize(535, 65);
            questionButtonsVBox.getChildren().add(questionButton);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        qz = new QuizDataAccess();
        List<Document> sections = qz.getSections();
        
        
// Hàm để lặp qua danh sách các question và tạo các button tương ứng


// Vòng lặp cho mỗi section
for (int i = 1; i <= sections.size(); i++) {
    TitledPane titledPane = new TitledPane();
    Document section = sections.get(i - 1);
    String sectionTitle = section.getString("title");
    titledPane.setText(sectionTitle);
    titledPane.getStyleClass().add("titled-pane");
    titledPane.setAnimated(false);
    titledPane.setPrefSize(590, 416);

    VBox contentVBox = new VBox();
    contentVBox.setStyle("-fx-background-color: white;");
    contentVBox.setPrefSize(572, 412);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setPrefSize(570, 363);
    VBox scrollContentVBox = new VBox();
    scrollContentVBox.setPrefSize(548, 358);

    VBox menuButtonVBox = new VBox();
    menuButtonVBox.setPrefSize(500, 53);
    menuButtonVBox.setPadding(new javafx.geometry.Insets(10, 0, 0, 10));

    MenuButton menuButton = new MenuButton("Add question");
    menuButton.getStyleClass().add("menu-button");

    MenuItem singleChoiceItem = new MenuItem("Single choice");
    MenuItem multipleChoiceItem = new MenuItem("Multiple choice");
    menuButton.getItems().addAll(singleChoiceItem, multipleChoiceItem);

    menuButtonVBox.getChildren().add(menuButton);

    VBox questionButtonsVBox = new VBox();
    questionButtonsVBox.setPrefSize(548, 351);
    questionButtonsVBox.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));

    // Lấy danh sách các question từ section hiện tại
    List<Document> questions = section.getList("questions", Document.class);
    
    // Gọi hàm addQuestionButtons để thêm button cho từng question
    addQuestionButtons(questionButtonsVBox, questions);

    scrollContentVBox.getChildren().addAll(menuButtonVBox, questionButtonsVBox);
    scrollPane.setContent(scrollContentVBox);

    contentVBox.getChildren().add(scrollPane);
    titledPane.setContent(contentVBox);

    AllSection.getPanes().add(titledPane);
}





    }
}
