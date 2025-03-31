package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Parsers.ParkReviewInformation;
import bsu.edu.cs.Parsers.Review;
import bsu.edu.cs.Utils.CSSConstants;
import bsu.edu.cs.Utils.ResourcePathsConstants;
import bsu.edu.cs.Utils.UIConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Objects;

public class ReviewsComponent {
    public void showReviewsPopup(String parkName, ParkReviewInformation reviewInformation) {
        Stage reviewStage = new Stage();
        reviewStage.setTitle("Reviews for " + parkName);

        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.getStyleClass().add("review-popup");

        // Header
        Label parkTitleLabel = new Label(parkName + " Reviews");
        parkTitleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        mainLayout.getChildren().add(parkTitleLabel);

        if (reviewInformation != null && reviewInformation.getListOfReviews() != null && !reviewInformation.getListOfReviews().isEmpty()) {

            Label ratingLabel = new Label("Average Rating: " + String.format("%.2f", reviewInformation.getRating()) + " ★");
            ratingLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #f39c12;");
            mainLayout.getChildren().add(ratingLabel);

            VBox reviewsContainer = new VBox(10);
            reviewsContainer.setPadding(new Insets(10));

            // Display all reviews
            for (Review review : reviewInformation.getListOfReviews()) {
                HBox reviewBox = new HBox(15);
                reviewBox.setPadding(new Insets(UIConstants.PADDING));
                reviewBox.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 10; -fx-border-color: #dcdcdc; -fx-border-radius: 10;");
                reviewBox.setAlignment(Pos.TOP_LEFT);

                // Profile Image
                ImageView profileImageView = new ImageView(new Image(review.profilePhotoURL(), 50, 50, true, true));
                profileImageView.setStyle("-fx-background-radius: 25; -fx-border-radius: 25;");

                // Review Details
                VBox reviewDetails = new VBox(5);
                reviewDetails.setAlignment(Pos.TOP_LEFT);

                Label authorLabel = new Label(review.authorName());
                authorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #34495e; -fx-font-size: 14px;");

                Label timeLabel = new Label(review.relativeTimeDescription());
                timeLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

                Label reviewTextLabel = new Label(review.text());
                reviewTextLabel.setWrapText(true);
                reviewTextLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");

                Label reviewRatingLabel = new Label("Rating: " + review.rating() + " ★");
                reviewRatingLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #f39c12;");

                reviewDetails.getChildren().addAll(authorLabel, timeLabel, reviewTextLabel, reviewRatingLabel);
                reviewBox.getChildren().addAll(profileImageView, reviewDetails);

                reviewsContainer.getChildren().add(reviewBox);
            }

            ScrollPane scrollPane = new ScrollPane(reviewsContainer);
            scrollPane.setFitToWidth(true);
            scrollPane.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            mainLayout.getChildren().add(scrollPane);
        } else {
            Label noReviewsLabel = new Label("No reviews available for this park.");
            noReviewsLabel.setStyle("-fx-font-size: 14px; -fx-font-style: italic; -fx-text-fill: #7f8c8d;");
            mainLayout.getChildren().add(noReviewsLabel);
        }

        Scene reviewScene = new Scene(mainLayout, 600, 450);
        reviewScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(ResourcePathsConstants.STYLE_PATH)).toExternalForm());
        reviewStage.setScene(reviewScene);
        reviewStage.show();
    }
}
