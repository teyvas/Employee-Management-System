package com.alatoo.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class FXMLLoaderUtil {
    public static Scene loadFXML(String fxmlFileName, double width, double height) throws IOException {
        URL fxmlLocation = FXMLLoaderUtil.class.getResource(fxmlFileName);
        if (fxmlLocation == null) {
            throw new IOException("Cannot find FXML file: " + fxmlFileName);
        }
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        return new Scene(root, width, height);
    }
}
