package mampf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    final String path = System.getProperty("user.dir") + "\\food.db";
    private DatabaseHandler db;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("main.fxml")));
        Parent root = loader.load();

        db = new DatabaseHandler(path);
        Controller controller = loader.getController();
        controller.setDatabaseHandler(db);

        primaryStage.setTitle("Mampf");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

    }

    @Override
    public void stop(){
        db.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
