package com.dnd.ui.utils;

import java.io.IOException;
import com.dnd.dao.ModelDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowSpawner {

    

    public static void spawnWindow(String fxmlFile, String title, Stage ownerStage, Controller controller, ModelDAO modelDAO) {
        try {

            FXMLLoader loader = new FXMLLoader(WindowSpawner.class.getResource(fxmlFile));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ownerStage);
            Scene scene = new Scene(loader.load());

            controller = loader.getController();
            controller.setStage(stage);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error loading " + title + " window: " + e.getMessage());
        }
    }


    
}
