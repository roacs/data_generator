package presentation;

import java.util.HashMap;
import java.util.Map;

import abstraction.immutable.OutputChannel;
import abstraction.immutable.Sensor;
import abstraction.immutable.SensorGenerator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainModel
{
    // TODO put updates on the FX thread

    private ObservableList<TableRowEntry> tableItems            = FXCollections.observableArrayList();
    private ObservableList<Sensor>        sensorListItems       = FXCollections.observableArrayList();
    private ObjectProperty<OutputChannel> outputChannelProperty = new SimpleObjectProperty<>();

    private Map<Integer, TableRowEntry>   idToTableEntry        = new HashMap<>();

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

    public void addGenerator(int generatorId, SensorGenerator s, int rate, int channel)
    {
        TableRowEntry entry = new TableRowEntry(generatorId, s.getSensor(), s.getDescription(), rate, channel);
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

    public ObjectProperty<OutputChannel> outputChannelProperty()
    {
        return outputChannelProperty;
    }
    
    public void setOutputChannel(OutputChannel outputChannel)
    {
        outputChannelProperty.set(outputChannel);
    }
}
