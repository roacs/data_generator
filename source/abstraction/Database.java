package abstraction;

import abstraction.immutable.OutputChannel;
import abstraction.immutable.sensor.SensorDataGenerator;
import external.MissionNumber;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Database
{
    private int                                                    uniqueId                = 0;

    private ObservableMap<Integer, SensorDataGeneratorInformation> idToSensorDataGenerator = FXCollections.observableHashMap();
    private ObjectProperty<OutputChannel>                          outputChannelProperty   = new SimpleObjectProperty<>();
    private ObjectProperty<MissionNumber>                          missionNumberProperty   = new SimpleObjectProperty<>();
    private StringProperty                                         hostnameProperty        = new SimpleStringProperty();

    public void addSensorDataGenerator(int rate, int channel, SensorDataGenerator generator)
    {
        idToSensorDataGenerator.put(++uniqueId, new SensorDataGeneratorInformation(rate, channel, generator));
    }

    public void removeSensorDataGenerator(int sensorDataGeneratorId)
    {
        idToSensorDataGenerator.remove(sensorDataGeneratorId);
    }

    public ObservableMap<Integer, SensorDataGeneratorInformation> getSensorDataGeneratorMap()
    {
        return idToSensorDataGenerator;
    }

    public void setEnabled(int sensorDataGeneratorId, boolean enabled)
    {
        if (idToSensorDataGenerator.containsKey(sensorDataGeneratorId))
        {
            idToSensorDataGenerator.get(sensorDataGeneratorId).setEnabled(enabled);
        }
    }

    public ObjectProperty<OutputChannel> outputChannelProperty()
    {
        return outputChannelProperty;
    }

    public void setOutputChannel(OutputChannel outputChannel)
    {
        outputChannelProperty.set(outputChannel);
    }

    public ObjectProperty<MissionNumber> missionNumberProperty()
    {
        return missionNumberProperty;
    }
    
    public void setMissionNumber(MissionNumber missionNumber)
    {
        missionNumberProperty.set(missionNumber);
    }

    public StringProperty hostnameProperty()
    {
        return hostnameProperty;
    }
    
    public void setHostname(String hostname)
    {
        hostnameProperty.set(hostname);
    }
}
