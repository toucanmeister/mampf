package mampf;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller {
    @FXML private TableView<Recipe> recipeTableView;
    @FXML private TableColumn<Recipe, Integer> idColumn;
    @FXML private TableColumn<Recipe, String> titleColumn;
    @FXML private TableColumn<Recipe, Integer> minutesColumn;
    @FXML private TableColumn<Recipe, String> bookTitleColumn;
    @FXML private TableColumn<Recipe, Integer> pageColumn;
    @FXML private TableColumn<Recipe, String> ratingColumn;
}
