package com.dnd.ui.utils;

import javafx.stage.Stage;
import com.dnd.dao.ModelDAO;

public abstract class Controller {

    private Stage stage;
    private ModelDAO modelDAO;
    public abstract void setStage(Stage stage);

    public abstract void setModelDAO(ModelDAO modelDAO);
    
}
