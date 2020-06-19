package abstraction;

import abstraction.immutable.SensorGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class SensorGeneratorInformation
{
    private final SensorGenerator sensorGenerator;
    private final BooleanProperty enabledProperty = new SimpleBooleanProperty(true);
    private final IntegerProperty channelProperty = new SimpleIntegerProperty(0);
    private final LongProperty    countProperty   = new SimpleLongProperty(0);
    private final IntegerProperty rateProperty    = new SimpleIntegerProperty(1);

    public SensorGeneratorInformation(int rate, int channel, SensorGenerator sensorGenerator)
    {
        this.sensorGenerator = sensorGenerator;
        this.channelProperty.set(channel);
        this.countProperty.set(0);
        this.rateProperty.set(rate);
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
    
    public void incrementCount()
    {
        this.countProperty.set(getCount() + 1);
    }
    
    public IntegerProperty channelProperty()
    {
        return channelProperty;
    }

    public void setChannel(int channel)
    {
        this.channelProperty.set(channel);
    }

    public int getChannel()
    {
        return this.channelProperty.get();
    }

    public SensorGenerator getSensorGenerator()
    {
        return sensorGenerator;
    }

    public IntegerProperty rateProperty()
    {
        return rateProperty;
    }

    public void setRate(int rate)
    {
        this.rateProperty.set(rate);
    }

    public int getRate()
    {
        return this.rateProperty.get();
    }
}
