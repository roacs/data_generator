package abstraction;

import abstraction.immutable.SensorGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Database
{
    private int uniqueId = 0;
    
    private ObservableMap<Integer, SensorGenerator> idToSensorGenerator = FXCollections.observableHashMap();
    
    public void addGenerator(SensorGenerator generator)
    {
        idToSensorGenerator.put(++uniqueId, generator);
    }
    
    public void removeGenerator(int generatorId)
    {
        idToSensorGenerator.remove(generatorId);
    }
    
    public ObservableMap<Integer, SensorGenerator> getSensorGeneratorMap()
    {
        return idToSensorGenerator;
    }
}
