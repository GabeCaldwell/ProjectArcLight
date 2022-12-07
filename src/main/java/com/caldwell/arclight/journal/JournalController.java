package com.caldwell.arclight.journal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// ********************************************************************************** //
// Title: Arclight                                                                    //
// Author: Gabriel Caldwell                                                           //
// Course Section: CMIS201-ONL1 (Seidel) Fall 2022                                    //
// File: JournalController.java                                                       //
// Description: Controller for journal.fxml                                           //
// ********************************************************************************** //

public class JournalController implements Initializable {

    // fields
    //=================================================================================================================
    private int pageNum = 0;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Journal journal;

    @FXML
    private Button managerButton;
    @FXML
    private Button menuButton;
    @FXML
    private TextArea pageText;
    @FXML
    private Button rightButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button saveButton;
    @FXML
    private Text pageNumId;

    final File pageData = new File("pages.dat");
    //=================================================================================================================



    // methods
    //*****************************************************************************************************************
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // to be removed
        //--------------------------------------------
        leftButton.setDisable(true);
        rightButton.setDisable(true);
        addButton.setDisable(true);
        removeButton.setDisable(true);
        //--------------------------------------------


        System.out.println("initializing journal...");
        journal = new Journal();
        if (pageData.exists()) {
            journal.readJournal();
            pageText.setText(journal.getPages().getHead().getText());
        }
        else if (!pageData.exists()) {
            journal.getPages().addFirst(new Journal.Page());
            journal.writeJournal();
        }

        if (journal.getPages().getSize() == 1) {
            pageNum = 0;
            leftButton.setDisable(true);
            rightButton.setDisable(true);
        }
        else if (journal.getPages().getSize() > 1) {
            pageNum = 0;
            leftButton.setDisable(true);
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

    public void onRightButton() {
        // get page text from linked list

        /*if (pageNum == 0) {
            pageText.setText(journal.getPages().getHead().getText());
        }*/

        if (pageNum+1 < journal.getPages().getSize() && pageNum >= 0) {
            pageNum++;
            pageText.setText(journal.getPages().get(pageNum-1).getText());
        }

        // checks to see if page num is in bounds
        rightButton.setDisable(journal.getPages().getSize()+1 >= pageNum);
        leftButton.setDisable(false);
        System.out.println(pageNum);
    }

    public void onLeftButton() {
        if (pageNum > 0 && pageNum < journal.getPages().getSize()) {
            pageNum--;
            pageText.setText(journal.getPages().get(pageNum+1).getText());
        }

        leftButton.setDisable(pageNum == 0);
        rightButton.setDisable(false);
        System.out.println(pageNum);
    }

    public void onAddPage() {
        journal.getPages().addLast(new Journal.Page(""));
        journal.writeJournal();
        if (rightButton.isDisabled()) {
            rightButton.setDisable(false);
        }
    }

    public void onRemovePage() {
        journal.getPages().remove(pageNum);
        journal.writeJournal();
        if (journal.getPages().getSize() == 1) {
            removeButton.setDisable(true);
        }
    }

    public void onSaveButton() {
        journal.getPages().get(pageNum).setText(pageText.getText());
        journal.writeJournal();
    }

    public void onUndoButton() {
        // undoes the deletion of a page
    }
    //*****************************************************************************************************************

}
