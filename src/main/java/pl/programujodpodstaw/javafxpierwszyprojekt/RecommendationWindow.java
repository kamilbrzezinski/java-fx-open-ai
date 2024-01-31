package pl.programujodpodstaw.javafxpierwszyprojekt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RecommendationWindow {
    private FXMLLoader fxmlLoader;
    private final List<String> products;

    public RecommendationWindow(List<String> products) {
        this.products = products;
    }

    public void open() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Co mogę zjeść?");
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);

        this.fxmlLoader = new FXMLLoader(RecommendationWindow.class.getResource("recommendation-window-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
        stage.show();

        recommend();
    }

    private void recommend() {
        RecommendationWindowController controller = fxmlLoader.getController();
        controller.initializeWithData(products);
        controller.recommend();
    }
}
