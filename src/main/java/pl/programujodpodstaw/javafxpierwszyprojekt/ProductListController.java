package pl.programujodpodstaw.javafxpierwszyprojekt;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class ProductListController {
    @FXML
    private TextField todoItem;

    @FXML
    private ListView<String> todoList;

    public void initialize() {
        todoList.setOnKeyPressed(this::handleKeyPress);
        todoList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            onRemoveButtonClick();
        }
    }

    @FXML
    protected void onAddButtonClick() {
        String text = todoItem.getText();

        if (text == null || text.isEmpty() || text.isBlank()) return;

        todoList.getItems().add(text);
        todoItem.clear();
    }

    @FXML
    protected void onRemoveButtonClick() {
        ObservableList<String> items = todoList.getSelectionModel().getSelectedItems();

        if (items.size() == 0) return;

        todoList.getItems().removeAll(items);
    }

    @FXML
    protected void onRecommendButtonClick() throws IOException {
        ObservableList<String> items = todoList.getItems();
        new RecommendationWindow(items).open();
    }
}
