package presentation;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableRowEntry
{
    private final int       generatorId;

    private BooleanProperty enabledProperty     = new SimpleBooleanProperty();
    private StringProperty  sensorProperty      = new SimpleStringProperty();
    private StringProperty  descriptionProperty = new SimpleStringProperty();
    private IntegerProperty rateProperty        = new SimpleIntegerProperty();
    private IntegerProperty channelProperty     = new SimpleIntegerProperty();
    private LongProperty    countProperty       = new SimpleLongProperty();

    public TableRowEntry(int uniqueId, String sensor, String description, int rate, int channel)
    {
        this.generatorId = uniqueId;
        
        this.enabledProperty.set(true);
        this.sensorProperty.set(sensor);
        this.descriptionProperty.set(description);
        this.rateProperty.set(rate);
        this.channelProperty.set(channel);
        this.countProperty.set(0);
    }
    
    public int getGeneratorId()
    {
        return generatorId;
    }
    
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

    public StringProperty sensorProperty()
    {
        return sensorProperty;
    }

    public void setSensor(String enabled)
    {
        sensorProperty.setValue(enabled);
    }

    public String getSensor()
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
    
    public IntegerProperty rateProperty()
    {
        return rateProperty;
    }

    public void setRate(int rate)
    {
        rateProperty.setValue(rate);
    }

    public int getRate()
    {
        return rateProperty.get();
    }

    public IntegerProperty channelProperty()
    {
        return channelProperty;
    }

    public void setChannel(int channel)
    {
        channelProperty.setValue(channel);
    }

    public int getChannel()
    {
        return channelProperty.get();
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
