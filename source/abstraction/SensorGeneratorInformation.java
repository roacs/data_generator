package abstraction;

import abstraction.immutable.SensorGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;

public class SensorGeneratorInformation
{
    private BooleanProperty enabledProperty     = new SimpleBooleanProperty();
    private SensorGenerator sensorGenerator;
    private LongProperty    countProperty       = new SimpleLongProperty();


    public SensorGeneratorInformation(SensorGenerator sensorGenerator)
    {
        this.sensorGenerator = sensorGenerator;
    }

    public BooleanProperty enabledProperty()
    {
        return enabledProperty;
    }
    
    public boolean isEnabled()
    {
        return enabledProperty.get();
    }

    public void setEnabled(boolean enabled)
    {
        this.enabledProperty.set(enabled);
    }

    public LongProperty countProperty()
    {
        return countProperty;
    }
    
    public long getCount()
    {
        return countProperty.get();
    }

    public void setCount(long count)
    {
        this.countProperty.set(count);
    }
    
    public void incrementCount()
    {
        this.countProperty.set(getCount() + 1);
    }

    public SensorGenerator getSensorGenerator()
    {
        return sensorGenerator;
    }

    public void setSensorGenerator(SensorGenerator sensorGenerator)
    {
        this.sensorGenerator = sensorGenerator;
    }
}
