package controller;

import abstraction.Database;
import abstraction.OutputTransmitter;
import abstraction.SensorGeneratorInformation;
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

        database.getSensorGeneratorMap().addListener((MapChangeListener<Integer, SensorGeneratorInformation>) (c ->
        {
            if (c.wasAdded())
            {
                model.addGenerator(c.getKey(), c.getValueAdded().getSensorGenerator(), c.getValueAdded().getRate());
                c.getValueAdded().countProperty().addListener((obs, o, n) -> model.setGeneratorCount(c.getKey(), n.longValue()));
                
                transmitter.addGenerator(c.getKey(), c.getValueAdded());
            }
            else if (c.wasRemoved())
            {
                model.removeGenerator(c.getKey());
                // TODO remove count listener?
                transmitter.removeGenerator(c.getKey());
            }
        }));
    }

    public void stop()
    {
        transmitter.stop();
    }

    public MainModel getModel()
    {
        return model;
    }

    public MainPresentation getPresentation()
    {
        return presentation;
    }

    public void addGenerator(int rate, SensorGenerator generator)
    {
        database.addGenerator(rate, generator);
    }

    public void removeGenerator(int generatorId)
    {
        database.removeGenerator(generatorId);
    }

    public void setEnabled(int generatorId, boolean enabled)
    {
        database.setEnabled(generatorId, enabled);
    }
}
