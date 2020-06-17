package controller;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    private final OutputTransmitter transmitter = new OutputTransmitter(database);

    public MainController()
    {
        model = new MainModel();
        presentation = new MainPresentation(this);

        model.setSensorListItems(FXCollections.observableArrayList(Sensor.values()));

        database.getSensorGeneratorMap().addListener((MapChangeListener<Integer, SensorGeneratorInformation>) (c ->
        {
            if (c.wasAdded())
            {
                model.addGenerator(c.getKey(), c.getValueAdded().getSensorGenerator());
            }
            else if (c.wasRemoved())
            {
                model.removeGenerator(c.getKey());
            }
        }));

        Executors.newSingleThreadScheduledExecutor().schedule(() ->
        {
            synchronized (database.getSensorGeneratorMap())
            {
                for (Integer id : database.getSensorGeneratorMap().keySet())
                {
                    model.setGeneratorCount(id, database.getSensorGeneratorMap().get(id).getCount());
                }
            }
        }, 500, TimeUnit.MILLISECONDS); 

        
        transmitter.start();
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

    public void setEnabled(int generatorId, boolean enabled)
    {
        database.setEnabled(generatorId, enabled);
    }
}
