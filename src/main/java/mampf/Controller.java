package mampf;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class Controller {
    @FXML private TableView<Recipe> recipeTableView;

    private DatabaseHandler db;

    @FXML
    public void initialize() {
        Platform.runLater(this::addAllRecipes);
    }

    private void addAllRecipes() {
        ObservableList<Recipe> observableRecipeList = recipeTableView.getItems();
        List<Recipe> recipeList = db.getAllRecipes();
        observableRecipeList.addAll(recipeList);
    }

    public void setDatabaseHandler(DatabaseHandler db) {
        this.db = db;
    }
}
