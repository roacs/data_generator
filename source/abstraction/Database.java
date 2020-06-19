package abstraction;

import abstraction.immutable.MissionNumber;
import abstraction.immutable.OutputChannel;
import abstraction.immutable.SensorGenerator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Database
{
    private int                                                uniqueId              = 0;

    private ObservableMap<Integer, SensorGeneratorInformation> idToSensorGenerator   = FXCollections.observableHashMap();
    private ObjectProperty<OutputChannel>                      outputChannelProperty = new SimpleObjectProperty<>();
    private ObjectProperty<MissionNumber>                      missionNumberProperty = new SimpleObjectProperty<>();
    private StringProperty                                     hostnameProperty      = new SimpleStringProperty();

    public void addGenerator(int rate, int channel, SensorGenerator generator)
    {
        idToSensorGenerator.put(++uniqueId, new SensorGeneratorInformation(rate, channel, generator));
    }

    public void removeGenerator(int generatorId)
    {
        idToSensorGenerator.remove(generatorId);
    }

    public ObservableMap<Integer, SensorGeneratorInformation> getSensorGeneratorMap()
    {
        return idToSensorGenerator;
    }

    public void setEnabled(int generatorId, boolean enabled)
    {
        if (idToSensorGenerator.containsKey(generatorId))
        {
            idToSensorGenerator.get(generatorId).setEnabled(enabled);
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
