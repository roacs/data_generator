package abstraction;

import abstraction.immutable.SensorGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Database
{
    private int uniqueId = 0;
    
    private ObservableMap<Integer, SensorGeneratorInformation> idToSensorGenerator = FXCollections.observableHashMap();
    
    public void addGenerator(SensorGenerator generator)
    {
        idToSensorGenerator.put(++uniqueId, new SensorGeneratorInformation(generator));
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
}
