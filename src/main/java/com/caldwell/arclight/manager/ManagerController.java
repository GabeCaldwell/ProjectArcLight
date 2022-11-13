package com.caldwell.arclight.manager;

import com.caldwell.arclight.bodies.Star;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Manager manager;

    @FXML
    private TableView<Star> starTable;
    @FXML
    private TableColumn<Star, String> nameColumn;
    @FXML
    private TableColumn<Star, String> colorColumn;
    @FXML
    private TableColumn<Star, Double> distanceColumn;


    @FXML
    private Button menuButton;
    @FXML
    private Button journalButton;
    @FXML
    private RadioButton nameRadioButton;
    @FXML
    private RadioButton colorRadioButton;
    @FXML
    private RadioButton distanceRadioButton;

    ObservableList<Star> starObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initializing manager...");

        // set up values for columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

        // read the stars from memory
        manager = new Manager();
        manager.readStars();

        // add items to list
        starObservableList = FXCollections.observableArrayList(manager.getStars());
        starTable.setItems(starObservableList);
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
    public void onJournalButton(ActionEvent event) throws IOException {
        System.out.println("switching to journal mode...");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("journal.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1125, 632);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onNameRadioButton() {
        // sort list by name then update table
        if (nameRadioButton.isSelected()) {
            // disable radio button when selected
            nameRadioButton.setDisable(true);
            // deselect other radio buttons
            colorRadioButton.setSelected(false);
            distanceRadioButton.setSelected(false);
            // enable other radio buttons
            colorRadioButton.setDisable(false);
            distanceRadioButton.setDisable(false);

            // quicksort the array by name then update table
            System.out.println("now sorting by name...");
            ArrayList<Star> sortedListName = manager.returnQuickSortString(manager.getStars());
            starObservableList.removeAll(sortedListName);
            starObservableList.addAll(sortedListName);
            starTable.setItems(starObservableList);
        }
    }

    @FXML
    public void onColorRadioButton() {
        // sort list by color then update table
        if (colorRadioButton.isSelected()) {
            // disable radio button when selected
            colorRadioButton.setDisable(true);
            // deselect other radio buttons
            nameRadioButton.setSelected(false);
            distanceRadioButton.setSelected(false);
            // enable other radio buttons
            distanceRadioButton.setDisable(false);
            nameRadioButton.setDisable(false);

            // quicksort the array by color then update table
            System.out.println("now sorting by color...");
            ArrayList<Star> sortedListColor = manager.returnQuickSortColor(manager.getStars());
            starObservableList.removeAll(sortedListColor);
            starObservableList.addAll(sortedListColor);
            starTable.setItems(starObservableList);
        }
    }

    @FXML
    public void onDistanceRadioButton() {
        if (distanceRadioButton.isSelected()) {
            // disable radio button when selected
            distanceRadioButton.setDisable(true);
            // deselect other radio buttons
            colorRadioButton.setSelected(false);
            nameRadioButton.setSelected(false);
            // enable other radio buttons
            colorRadioButton.setDisable(false);
            nameRadioButton.setDisable(false);

            // quicksort the array by distance then update table
            System.out.println("now sorting by distance...");
            ArrayList<Star> sortedListDistance = manager.returnQuickSort(manager.getStars());
            starObservableList.removeAll(sortedListDistance);
            starObservableList.addAll(sortedListDistance);
            starTable.setItems(starObservableList);
        }
    }
}
