package com.hassan.cfg2pda;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Scene scene;
    private static boolean darkMode = true;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
        scene = new Scene(loader.load(), 1200, 760);
        scene.getStylesheets().add(getClass().getResource("/styles/app.css").toExternalForm());
        applyTheme();
        stage.setTitle("CFG → PDA Converter");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();
    }

    public static void toggleTheme() {
        darkMode = !darkMode;
        applyTheme();
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    private static void applyTheme() {
        Application.setUserAgentStylesheet(darkMode ? new PrimerDark().getUserAgentStylesheet()
                : new PrimerLight().getUserAgentStylesheet());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
