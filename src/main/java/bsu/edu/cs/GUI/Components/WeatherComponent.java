package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Parsers.Weather;
import bsu.edu.cs.Utils.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WeatherComponent {

    public HBox createWeatherDisplay(Weather weather) {
        Label temperatureLabel = new Label(weather.getTemperature() + "℉");
        Label humidityLabel = new Label(weather.getHumidity() + "%");
        Label windSpeedLabel = new Label(weather.getWindSpeed() + " MPH");

        ImageView humidityIcon = new ImageView(new Image(Constants.HUMIDITY_ICON_PATH));
        ImageView windIcon = new ImageView(new Image(Constants.WIND_ICON_PATH));

        humidityIcon.setFitWidth(Constants.ICON_SIZE);
        humidityIcon.setFitHeight(Constants.ICON_SIZE);
        windIcon.setFitWidth(Constants.ICON_SIZE);
        windIcon.setFitHeight(Constants.ICON_SIZE);

        HBox humidityDetails = new HBox(2);
        humidityDetails.getChildren().addAll(humidityIcon, humidityLabel);

        HBox windDetails = new HBox(2);
        windDetails.getChildren().addAll(windIcon, windSpeedLabel);

        temperatureLabel.getStyleClass().addAll(Constants.CLASS_WHITE, Constants.CLASS_TEMP);
        humidityLabel.getStyleClass().add(Constants.CLASS_WHITE);
        windSpeedLabel.getStyleClass().add(Constants.CLASS_WHITE);

        HBox weatherSpeed = new HBox(10);
        weatherSpeed.getChildren().addAll(humidityDetails, windDetails);
        weatherSpeed.getStyleClass().add(Constants.CLASS_WEATHER_SPEED);

        ImageView weatherIcon = new ImageView(new Image(Constants.WEATHER_ICON_URL_PREFIX + weather.getIconID() + Constants.WEATHER_ICON_URL_SUFFIX));
        VBox weatherImage = new VBox();
        weatherImage.getChildren().addAll(weatherIcon, temperatureLabel, weatherSpeed);

        weatherIcon.setFitHeight(Constants.WEATHER_ICON_SIZE);
        weatherIcon.setFitWidth(Constants.WEATHER_ICON_SIZE);

        return new HBox(10, weatherImage);
    }
}
