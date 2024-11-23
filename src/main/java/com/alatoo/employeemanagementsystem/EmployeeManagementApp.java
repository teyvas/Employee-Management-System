package com.alatoo.employeemanagementsystem;

import com.alatoo.utils.FXMLLoaderUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmployeeManagementApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Scene scene = FXMLLoaderUtil.loadFXML("employee-management-view.fxml", 600, 400);
            stage.setTitle("Employee Management System");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load the application: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
