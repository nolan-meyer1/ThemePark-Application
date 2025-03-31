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
        mainLayout.getStyleClass().add(CSSConstants.CLASS_REVIEW_POPUP);

        // Header
        Label parkTitleLabel = new Label(parkName + " Reviews");
        parkTitleLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_TITLE);
        mainLayout.getChildren().add(parkTitleLabel);

        if (reviewInformation != null && reviewInformation.getListOfReviews() != null && !reviewInformation.getListOfReviews().isEmpty()) {

            Label ratingLabel = new Label("Average Rating: " + String.format("%.2f", reviewInformation.getRating()) + " ★");
            ratingLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_RATING);
            mainLayout.getChildren().add(ratingLabel);

            VBox reviewsContainer = new VBox(10);
            reviewsContainer.setPadding(new Insets(10));

            for (Review review : reviewInformation.getListOfReviews()) {
                HBox reviewBox = new HBox(15);
                reviewBox.setPadding(new Insets(UIConstants.PADDING));
                reviewBox.getStyleClass().add(CSSConstants.CLASS_REVIEW_BOX);
                reviewBox.setAlignment(Pos.TOP_LEFT);

                // Profile Image
                ImageView profileImageView = new ImageView(new Image(review.profilePhotoURL(), 50, 50, true, true));
                profileImageView.getStyleClass().add(CSSConstants.CLASS_PROFILE_IMAGE);

                // Review Details
                VBox reviewDetails = new VBox(5);
                reviewDetails.setAlignment(Pos.TOP_LEFT);

                Label authorLabel = new Label(review.authorName());
                authorLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_AUTHOR);

                Label timeLabel = new Label(review.relativeTimeDescription());
                timeLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_TIME);

                Label reviewTextLabel = new Label(review.text());
                reviewTextLabel.setWrapText(true);
                reviewTextLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_REVIEW_TEXT);

                Label reviewRatingLabel = new Label("Rating: " + review.rating() + " ★");
                reviewRatingLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_REVIEW_RATING);

                reviewDetails.getChildren().addAll(authorLabel, timeLabel, reviewTextLabel, reviewRatingLabel);
                reviewBox.getChildren().addAll(profileImageView, reviewDetails);

                reviewsContainer.getChildren().add(reviewBox);
            }

            ScrollPane scrollPane = new ScrollPane(reviewsContainer);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().add(CSSConstants.CLASS_SCROLL_PANE);
            mainLayout.getChildren().add(scrollPane);
        } else {
            Label noReviewsLabel = new Label("No reviews available for this park.");
            noReviewsLabel.getStyleClass().add(CSSConstants.CLASS_LABEL_NO_REVIEWS);
            mainLayout.getChildren().add(noReviewsLabel);
        }

        Scene reviewScene = new Scene(mainLayout, 600, 450);
        reviewScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(ResourcePathsConstants.STYLE_PATH)).toExternalForm());
        reviewStage.setScene(reviewScene);
        reviewStage.show();
    }
}
