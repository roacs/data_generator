package presentation;

import abstraction.immutable.Sensor;
import abstraction.immutable.SensorGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel
{
    private ObservableList<TableRowEntry> tableItems      = FXCollections.observableArrayList();
    private ObservableList<Sensor>        sensorListItems = FXCollections.observableArrayList();
    
    public ObservableList<TableRowEntry> getTableItems()
    {
        return tableItems;
    }
    
    public ObservableList<Sensor> getSensorListItems()
    {
        return sensorListItems;
    }

    public void setSensorListItems(ObservableList<Sensor> sensorListItems)
    {
        this.sensorListItems = sensorListItems;
    }

    public void addGenerator(int generatorId, SensorGenerator s)
    {
        tableItems.add(new TableRowEntry(generatorId, s.getSensor(), s.getDescription()));
    }

    public void removeGenerator(int generatorId)
    {
        tableItems.removeIf(entry -> entry.getGeneratorId() == generatorId);
    }

    public void setGeneratorCount(Integer id, long count)
    {
        // TODO Auto-generated method stub
        
    }
}
