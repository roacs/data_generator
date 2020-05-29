package controller;

import abstraction.immutable.Sensor;
import javafx.collections.FXCollections;
import presentation.MainModel;

public class MainController
{
    private final MainModel model;

    public MainController()
    {
        model = new MainModel();
        
        model.setSensorListItems(FXCollections.observableArrayList(Sensor.values()));
    }

    public MainModel getModel()
    {
        return model;
    }
}
