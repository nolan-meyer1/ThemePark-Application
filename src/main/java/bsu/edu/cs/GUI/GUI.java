package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.GUI.Components.ParksListComponent;
import bsu.edu.cs.GUI.Components.ReviewsComponent;
import bsu.edu.cs.GUI.Components.ThemeManager;
import bsu.edu.cs.GUI.Components.WeatherComponent;
import bsu.edu.cs.Parsers.*;
import bsu.edu.cs.Utils.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.web.WebView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.*;

public class GUI extends Application {
    private final Controller controller = new Controller();
    private final SharedState sharedState = new SharedState();
    private final WeatherComponent weatherComponent = new WeatherComponent();
    private final ThemeManager themeManager = new ThemeManager(sharedState);
    private final ReviewsComponent reviewsComponent = new ReviewsComponent(sharedState);
    private final ParksListComponent sideBar = new ParksListComponent(reviewsComponent);
    @Override
    public void start(Stage primaryStage) {
        Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
        errorPopUp.setTitle(AlertConstants.ERROR_TITLE);
        errorPopUp.getDialogPane().getStyleClass().add(AlertConstants.ALERT_CLASS);

        Map<String, Park> parksMap;
        try {
            parksMap = controller.fetchParks();
        } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
            errorPopUp.setContentText(TextConstants.NETWORK_ERROR);
            errorPopUp.showAndWait();
            return;
        }
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        Button toggleThemeButton = themeManager.createThemeToggleButton(scene);



        VBox ridesSidebar = new VBox(UIConstants.MEDIUM_SPACING);
        ridesSidebar.setPadding(new Insets(UIConstants.PADDING));
        ridesSidebar.getStyleClass().add(CSSConstants.CLASS_SIDEBAR);

        ListView<Ride> ridesList = new ListView<>();
        ridesList.getStyleClass().add(CSSConstants.CLASS_RIDES_CONTAINER);

        VBox.setVgrow(ridesList, Priority.ALWAYS); // This makes the ridesList stretch to fill the height with the sidebar
        ridesSidebar.getChildren().add(ridesList);

        VBox mainContent = new VBox(UIConstants.MEDIUM_SPACING);
        mainContent.setPadding(new Insets(UIConstants.PADDING_LARGE));
        mainContent.getStyleClass().add(CSSConstants.CLASS_MAIN_CONTENT);

        Label parkTitle = new Label(TextConstants.SELECT_PARK_TEXT);
        parkTitle.getStyleClass().add(CSSConstants.CLASS_PARK_TITLE);

        // Button to open the review pop-up
        Button viewReviewsButton = new Button(TextConstants.VIEW_REVIEWS);
        viewReviewsButton.getStyleClass().add(CSSConstants.CLASS_REVIEWS_BUTTON);

        WebView webView = new WebView();
        File htmlFile = new File(ResourcePathsConstants.HTML_FILE);
        webView.setPrefHeight(UIConstants.WEBVIEW_HEIGHT);
        webView.getEngine().load(htmlFile.toURI().toString());
        MapManager mapManager = new MapManager(webView.getEngine(),parksMap);

        HBox webContainer = new HBox(UIConstants.MEDIUM_SPACING);
        webContainer.setPadding(new Insets(UIConstants.PADDING_LARGE));
        webContainer.setAlignment(Pos.CENTER_LEFT);

        webView.setPrefWidth(UIConstants.WINDOW_WIDTH * 0.8);

        ridesSidebar.setPrefWidth(UIConstants.WINDOW_WIDTH * 0.22);

        ridesSidebar.setMaxHeight(Double.MAX_VALUE);
        ridesSidebar.setMinHeight(UIConstants.ZERO_VALUE);

        webContainer.getChildren().add(webView);

        HBox ridesHeader = new HBox(UIConstants.MEDIUM_SPACING);
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        ridesHeader.getChildren().addAll(parkTitle, spacer, viewReviewsButton, toggleThemeButton);
        ridesHeader.setAlignment(Pos.CENTER_LEFT);

        HBox weather = weatherComponent.createWeatherDisplay(new Weather(UIConstants.WEATHER_DEFAULT_ID, TextConstants.NO_WEATHER_INFO, UIConstants.WEATHER_DEFAULT_VALUE, UIConstants.WEATHER_DEFAULT_VALUE, UIConstants.WEATHER_DEFAULT_VALUE, UIConstants.WEATHER_DEFAULT_VALUE, TextConstants.WEATHER_DEFAULT_ICON_ID));
        weather.setMaxWidth(UIConstants.WEATHER_MAX_WIDTH);
        weather.setMaxHeight(UIConstants.WEATHER_MAX_HEIGHT);
        weather.getStyleClass().add(CSSConstants.CLASS_WEATHER_CONTAINER);

        VBox sideBarVBox = sideBar.createSideBar(parksMap, errorPopUp, parkTitle, ridesList, mainContent, weatherComponent,mapManager, viewReviewsButton);

        mainContent.getChildren().addAll(ridesHeader, weather, webContainer);

        root.setLeft(sideBarVBox);
        root.setCenter(mainContent);
        root.setRight(ridesSidebar);

        Image appIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(ResourcePathsConstants.APP_ICON_PATH)));
        primaryStage.getIcons().add(appIcon);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(ResourcePathsConstants.STYLE_PATH)).toExternalForm());
        primaryStage.setTitle(TextConstants.APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
