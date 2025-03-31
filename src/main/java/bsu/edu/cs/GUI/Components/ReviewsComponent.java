package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Utils.ResourcePathsConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ReviewsComponent {
    public void showReviewsPopup() {
        Stage reviewStage = new Stage();
        reviewStage.setTitle("Park Reviews");

        VBox reviewBox = new VBox(10);
        reviewBox.setPadding(new Insets(15));
        reviewBox.getStyleClass().add("review-popup");
        reviewBox.setAlignment(Pos.CENTER);

        List<String> reviews = Arrays.asList(
                "Great park! Loved the rides. ★★★★★",
                "It was okay, but the food was overpriced. ★★★☆☆",
                "Amazing experience, will visit again! ★★★★★",
                "Not well maintained. ★★☆☆☆"
        );

        for (String review : reviews) {
            Label reviewLabel = new Label(review);
            reviewLabel.setWrapText(true);
            reviewLabel.getStyleClass().add("review-text");
            reviewBox.getChildren().add(reviewLabel);
        }

        Scene reviewScene = new Scene(reviewBox, 500, 200);
        reviewScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(ResourcePathsConstants.STYLE_PATH)).toExternalForm());
        reviewStage.setScene(reviewScene);
        reviewStage.show();
    }
}
