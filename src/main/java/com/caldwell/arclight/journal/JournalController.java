package com.caldwell.arclight.journal;

import com.caldwell.arclight.bodies.Star;
import com.caldwell.arclight.manager.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JournalController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Manager manager;


    @FXML
    private Button managerButton;
    @FXML
    private Button menuButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;
    @FXML
    private ComboBox<Star> removeChoiceBox;
    @FXML
    private TextField nameField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField distanceField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initializing journal...");
        manager = new Manager();
        manager.readStars();
        for (Star s : manager.getStars()) {
            removeChoiceBox.getItems().add(s);
        }
    }

    // return to main menu
    @FXML
    public void onMainMenuButton(ActionEvent event) throws IOException {
        System.out.println("returning to the menu...");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1125, 632);
        stage.setScene(scene);
        stage.show();
    }

    // switch to journal view
    @FXML
    public void onManagerButton(ActionEvent event) throws IOException {
        System.out.println("switching to manager mode...");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("manager.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1125, 632);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onRemoveButton() {
        if (removeChoiceBox.getSelectionModel().getSelectedItem() != null) {
            System.out.println("removing star: " + removeChoiceBox.getSelectionModel().getSelectedItem().getName() + "...");
            manager.getStars().remove(removeChoiceBox.getSelectionModel().getSelectedItem());
            manager.writeStars();
            removeChoiceBox.getItems().remove(removeChoiceBox.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void onAddButton() {
        if (!nameField.getText().isEmpty() && !colorField.getText().isEmpty() && distanceField.getText().matches(".*[0-9].*")) {
            System.out.println("adding new star: " + nameField.getText() + "...");
            manager.getStars().add(new Star(nameField.getText(),colorField.getText(),Integer.parseInt(distanceField.getText())));
            manager.writeStars();
            removeChoiceBox.getItems().removeAll(manager.getStars());
            removeChoiceBox.getItems().addAll(manager.getStars());
        }
    }

}
