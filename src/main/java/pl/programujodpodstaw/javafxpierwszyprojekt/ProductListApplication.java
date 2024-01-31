package pl.programujodpodstaw.javafxpierwszyprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductListApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProductListApplication.class.getResource("products-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 600);
        stage.setTitle("Lista produktów");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}