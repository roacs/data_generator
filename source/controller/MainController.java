package controller;

import abstraction.Database;
import abstraction.OutputTransmitter;
import abstraction.immutable.Sensor;
import abstraction.immutable.SensorGenerator;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import presentation.MainModel;
import presentation.MainPresentation;

public class MainController
{
    private final MainModel         model;
    private final MainPresentation  presentation;

    private final Database          database    = new Database();
    private final OutputTransmitter transmitter = new OutputTransmitter();

    public MainController()
    {
        model = new MainModel();
        presentation = new MainPresentation(this);
        
        model.setSensorListItems(FXCollections.observableArrayList(Sensor.values()));
        
        database.getSensorGeneratorMap().addListener((MapChangeListener<Integer, SensorGenerator>)(c ->
        {
            if (c.wasAdded())
            {
                model.addGenerator(c.getKey(), c.getValueAdded());
            }
            else if (c.wasRemoved())
            {
                model.removeGenerator(c.getKey());
            }
        }));
        
        // TODO need to propagate a checkbox enabled change to the database

        // TODO create a thread to periodically poll the transmitters and update statistics in the model
    }

    public MainModel getModel()
    {
        return model;
    }
    
    public MainPresentation getPresentation()
    {
        return presentation;
    }

    public void addGenerator(SensorGenerator generator)
    {
        database.addGenerator(generator);
    }
    
    public void removeGenerator(int generatorId)
    {
        database.removeGenerator(generatorId);
    }
}
