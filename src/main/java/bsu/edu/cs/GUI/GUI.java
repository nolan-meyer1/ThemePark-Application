package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.GUI.Components.ParksListComponent;
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
import java.util.*;
public class GUI extends Application {

    private final Controller controller = new Controller();
    private final WeatherComponent weatherComponent = new WeatherComponent();
    private final ThemeManager themeManager = new ThemeManager();

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

        // Left Sidebar (10%)
        ParksListComponent sideBar = new ParksListComponent();

        // Main Display Area (90%)
        VBox mainContent = new VBox(UIConstants.MEDIUM_SPACING);
        mainContent.setPadding(new Insets(UIConstants.PADDING_LARGE));
        mainContent.getStyleClass().add(CSSConstants.CLASS_MAIN_CONTENT);
        Label parkTitle = new Label(TextConstants.SELECT_PARK_TEXT);
        parkTitle.getStyleClass().add(CSSConstants.CLASS_PARK_TITLE);

        ListView<Ride> ridesList = new ListView<>();
        ridesList.getStyleClass().add(CSSConstants.CLASS_RIDES_CONTAINER);

        HBox ridesHeader = new HBox(UIConstants.MEDIUM_SPACING);
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        ridesHeader.getChildren().addAll(parkTitle, spacer, toggleThemeButton);
        ridesHeader.setAlignment(Pos.CENTER_LEFT);

        HBox weather = weatherComponent.createWeatherDisplay(new Weather(45, TextConstants.NO_WEATHER_INFO,0,0,0,0,"01d"));
        weather.setMaxWidth(UIConstants.WEATHER_MAX_WIDTH);
        weather.setMaxHeight(UIConstants.WEATHER_MAX_HEIGHT);
        weather.getStyleClass().add(CSSConstants.CLASS_WEATHER_CONTAINER);

        VBox sideBarVBox = sideBar.createSideBar(parksMap, errorPopUp, parkTitle, ridesList, mainContent, weatherComponent);

        root.setLeft(sideBarVBox);
        mainContent.getChildren().addAll(ridesHeader,weather,ridesList);
        root.setCenter(mainContent);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(ResourcePathsConstants.STYLE_PATH)).toExternalForm());
        primaryStage.setTitle(TextConstants.APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
