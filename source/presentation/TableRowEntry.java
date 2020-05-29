package presentation;

import abstraction.immutable.Sensor;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableRowEntry
{
    private BooleanProperty        enabledProperty     = new SimpleBooleanProperty();
    private ObjectProperty<Sensor> sensorProperty      = new SimpleObjectProperty<>();
    private StringProperty         descriptionProperty = new SimpleStringProperty();
    private LongProperty           countProperty       = new SimpleLongProperty();

    public BooleanProperty enabledProperty()
    {
        return enabledProperty;
    }

    public void setEnabled(boolean enabled)
    {
        enabledProperty.setValue(enabled);
    }

    public boolean isEnabled()
    {
        return enabledProperty.get();
    }

    public ObjectProperty<Sensor> sensorProperty()
    {
        return sensorProperty;
    }

    public void setSensor(Sensor enabled)
    {
        sensorProperty.setValue(enabled);
    }

    public Sensor getSensor()
    {
        return sensorProperty.get();
    }

    public StringProperty descriptionProperty()
    {
        return descriptionProperty;
    }

    public void setDescription(String description)
    {
        descriptionProperty.setValue(description);
    }

    public String getDescription()
    {
        return descriptionProperty.get();
    }

    public LongProperty countProperty()
    {
        return countProperty;
    }

    public void setCount(long count)
    {
        countProperty.setValue(count);
    }

    public long getCount()
    {
        return countProperty.get();
    }
}
