package controller;

import java.io.IOException;

import abstraction.Database;
import abstraction.OutputTransmitter;
import abstraction.SensorDataGeneratorInformation;
import abstraction.immutable.OutputChannel;
import abstraction.immutable.sensor.Sensor;
import abstraction.immutable.sensor.SensorDataGenerator;
import external.MissionNumber;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import presentation.MainModel;
import presentation.MainPresentation;

public class MainController
{
    private final MainModel         model;
    private final MainPresentation  presentation;
    private final OutputTransmitter transmitter;
    private final Database          database = new Database();

    public MainController() throws IOException
    {
        model = new MainModel();
        presentation = new MainPresentation(this);
        transmitter = new OutputTransmitter();

        model.setSensorListItems(FXCollections.observableArrayList(Sensor.values()));

        database.getSensorDataGeneratorMap().addListener((MapChangeListener<Integer, SensorDataGeneratorInformation>) (c ->
        {
            if (c.wasAdded())
            {
                model.addSensorDataGenerator(c.getKey(), c.getValueAdded().getSensorGenerator(), c.getValueAdded().getRate(), c.getValueAdded().getChannel());
                c.getValueAdded().countProperty().addListener((obs, o, n) -> model.setSensorDataGeneratorCount(c.getKey(), n.longValue()));
                
                transmitter.addGenerator(c.getKey(), c.getValueAdded());
            }
            else if (c.wasRemoved())
            {
                model.removeSensorDataGenerator(c.getKey());
                // TODO remove count listener?
                transmitter.removeGenerator(c.getKey());
            }
        }));
        
        database.outputChannelProperty().addListener((obj, o, n) ->
        {
            model.setOutputChannel(n);
            transmitter.setOutputDestination(database.outputChannelProperty().get(), database.hostnameProperty().get(), database.missionNumberProperty().get());
        });
        database.missionNumberProperty().addListener((obj, o, n) -> transmitter.setOutputDestination(database.outputChannelProperty().get(), database.hostnameProperty().get(), database.missionNumberProperty().get()));
        database.hostnameProperty().addListener((obj, o, n) -> transmitter.setOutputDestination(database.outputChannelProperty().get(), database.hostnameProperty().get(), database.missionNumberProperty().get()));
        
        model.setOutputChannel(OutputChannel.TSPI_NODE);
        setOutputChannelSelected(OutputChannel.TSPI_NODE);
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

    public void addSensorDataGenerator(int rate, int channel, SensorDataGenerator sensorDataGenerator)
    {
        database.addSensorDataGenerator(rate, channel, sensorDataGenerator);
    }

    public void removeSensorDataGenerator(int sensorDataGeneratorId)
    {
        database.removeSensorDataGenerator(sensorDataGeneratorId);
    }

    public void setEnabled(int sensorDataGeneratorId, boolean enabled)
    {
        database.setEnabled(sensorDataGeneratorId, enabled);
    }

    public void setOutputChannelSelected(OutputChannel channel)
    {
        database.setOutputChannel(channel);
    }

    public void setMissionNumber(MissionNumber missionNumber)
    {
        database.setMissionNumber(missionNumber);
    }

    public void setHostname(String hostname)
    {
        database.setHostname(hostname);
    }
}
