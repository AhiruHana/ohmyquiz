package ohmyquiz.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ohmyquiz.models.User;

public class SettingsController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox mainVbox;

    @FXML
    private ComboBox topicComboBox;

    @FXML
    private VBox menuBarVbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            responseWidth(newWidth.doubleValue());
        });

        ObservableList<String> options = FXCollections.observableArrayList("Topic 1","Topic 2","Topic 3");
        topicComboBox.setItems(options);
        
        topicComboBox.setEditable(true);
        topicComboBox.setPromptText("Type here");
        topicComboBox.setFocusTraversable(false);
        topicComboBox.setVisibleRowCount(4);
    }

    public void responseWidth(double totalWidth) {
        menuBarVbox.setPrefWidth(totalWidth);
        mainVbox.setPrefWidth(totalWidth);

    }
}
