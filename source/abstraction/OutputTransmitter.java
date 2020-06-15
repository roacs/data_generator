package abstraction;

import abstraction.immutable.SensorGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OutputTransmitter
{
    private ObservableList<SensorGenerator> sensorGenerators = FXCollections.observableArrayList();
    
    public void addGenerator(SensorGenerator generator)
    {
        sensorGenerators.add(generator);
    }

    public ObservableList<SensorGenerator> sensorGeneratorList()
    {
        return sensorGenerators;
    }
}
