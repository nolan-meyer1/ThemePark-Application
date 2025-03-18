package bsu.edu.cs.GUI.Components;

import bsu.edu.cs.Utils.Constants;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Objects;

public class ThemeManager {
    private final AtomicBoolean isDarkMode = new AtomicBoolean(false);
    private final Image sunIcon;
    private final Image moonIcon;

    public ThemeManager() {
        this.sunIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.SUN_ICON_PATH)));
        this.moonIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.MOON_ICON_PATH)));
    }

    public Button createThemeToggleButton(Scene scene) {
        ImageView toggleIcon = new ImageView(sunIcon);
        toggleIcon.setFitHeight(Constants.ICON_SIZE);
        toggleIcon.setFitWidth(Constants.ICON_SIZE);

        Button toggleThemeButton = new Button();
        toggleThemeButton.setGraphic(toggleIcon);
        toggleThemeButton.getStyleClass().add(Constants.CLASS_TOGGLE_BUTTON);

        toggleThemeButton.setOnAction(e -> toggleDarkMode(scene, toggleIcon));

        return toggleThemeButton;
    }

    private void toggleDarkMode(Scene scene, ImageView toggleIcon) {
        String theme = isDarkMode.get() ? Constants.STYLE_PATH : Constants.DARK_STYLE_PATH;
        scene.getStylesheets().setAll(Objects.requireNonNull(getClass().getResource(theme)).toExternalForm());
        toggleIcon.setImage(isDarkMode.get() ? sunIcon : moonIcon);
        isDarkMode.set(!isDarkMode.get());
    }
}
