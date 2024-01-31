package pl.programujodpodstaw.javafxpierwszyprojekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class RecommendationWindowController {
    @FXML
    private Label recommendationLabel;

    private List<String> products;
    private OpenAiHelper openAiHelper;

    public void initializeWithData(List<String> products) {
        this.openAiHelper = new OpenAiHelper();
        this.products = products;
    }

    @FXML
    protected void recommend() {
        String recommendation = openAiHelper.recommend(products);

        recommendationLabel.setText(recommendation);
    }
}
