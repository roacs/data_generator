package presentation;

import abstraction.immutable.Sensor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel
{
    public ObservableList<TableRowEntry> tableItems = FXCollections.observableArrayList();
    public ObservableList<Sensor> sensorListItems = FXCollections.observableArrayList();
    
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
}
