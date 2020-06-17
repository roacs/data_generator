package presentation;

import java.util.HashMap;
import java.util.Map;

import abstraction.immutable.Sensor;
import abstraction.immutable.SensorGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel
{
    // TODO put updates on the FX thread

    private ObservableList<TableRowEntry> tableItems      = FXCollections.observableArrayList();
    private ObservableList<Sensor>        sensorListItems = FXCollections.observableArrayList();

    private Map<Integer, TableRowEntry>   idToTableEntry  = new HashMap<>();

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
        TableRowEntry entry = new TableRowEntry(generatorId, s.getSensor(), s.getDescription());
        tableItems.add(entry);
        idToTableEntry.put(generatorId, entry);
    }

    public void removeGenerator(int generatorId)
    {
        if (idToTableEntry.containsKey(generatorId))
        {
            tableItems.remove(idToTableEntry.get(generatorId));
            idToTableEntry.remove(generatorId);
        }
    }

    public void setGeneratorCount(Integer generatorId, long count)
    {
        if (idToTableEntry.containsKey(generatorId))
        {
            idToTableEntry.get(generatorId).setCount(count);
        }
    }
}
