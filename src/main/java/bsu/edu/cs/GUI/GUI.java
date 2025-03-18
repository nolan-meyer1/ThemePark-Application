package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.GUI.Components.ParksListComponent;
import bsu.edu.cs.GUI.Components.ThemeManager;
import bsu.edu.cs.GUI.Components.WeatherComponent;
import bsu.edu.cs.Parsers.*;
import bsu.edu.cs.Utils.Constants;
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
        errorPopUp.setTitle(Constants.ERROR_TITLE);
        errorPopUp.getDialogPane().getStyleClass().add(Constants.ALERT_CLASS);

        Map<String, Park> parksMap;
        try {
            parksMap = controller.fetchParks();
        } catch (networkErrorException | openInputStreamException | noItemFoundException e) {
            errorPopUp.setContentText(Constants.NETWORK_ERROR);
            errorPopUp.showAndWait();
            return;
        }
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        Button toggleThemeButton = themeManager.createThemeToggleButton(scene);

        // Left Sidebar (10%)
        ParksListComponent sideBar = new ParksListComponent();

        // Main Display Area (90%)
        VBox mainContent = new VBox(Constants.MEDIUM_SPACING);
        mainContent.setPadding(new Insets(Constants.PADDING_LARGE));
        mainContent.getStyleClass().add(Constants.CLASS_MAIN_CONTENT);
        Label parkTitle = new Label(Constants.SELECT_PARK_TEXT);
        parkTitle.getStyleClass().add(Constants.CLASS_PARK_TITLE);

        ListView<Ride> ridesList = new ListView<>();
        ridesList.getStyleClass().add(Constants.CLASS_RIDES_CONTAINER);

        HBox ridesHeader = new HBox(Constants.MEDIUM_SPACING);
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        ridesHeader.getChildren().addAll(parkTitle, spacer, toggleThemeButton);
        ridesHeader.setAlignment(Pos.CENTER_LEFT);

        HBox weather = weatherComponent.createWeatherDisplay(new Weather(45,Constants.NO_WEATHER_INFO,0,0,0,0,"01d"));
        weather.setMaxWidth(Constants.WEATHER_MAX_WIDTH);
        weather.setMaxHeight(Constants.WEATHER_MAX_HEIGHT);
        weather.getStyleClass().add(Constants.CLASS_WEATHER_CONTAINER);

        VBox sideBarVBox = sideBar.createSideBar(parksMap, errorPopUp, parkTitle, ridesList, mainContent, weatherComponent);

        root.setLeft(sideBarVBox);
        mainContent.getChildren().addAll(ridesHeader,weather,ridesList);
        root.setCenter(mainContent);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(Constants.STYLE_PATH)).toExternalForm());
        primaryStage.setTitle(Constants.APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
