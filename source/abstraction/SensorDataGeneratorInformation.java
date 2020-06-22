package abstraction;

import abstraction.immutable.sensor.SensorDataGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class SensorDataGeneratorInformation
{
    private final SensorDataGenerator sensorDataGenerator;
    private final BooleanProperty     enabledProperty = new SimpleBooleanProperty(true);
    private final IntegerProperty     channelProperty = new SimpleIntegerProperty(0);
    private final LongProperty        countProperty   = new SimpleLongProperty(0);
    private final IntegerProperty     rateProperty    = new SimpleIntegerProperty(1);
    private final BooleanProperty     errorProperty   = new SimpleBooleanProperty(false);

    public SensorDataGeneratorInformation(int rate, int channel, SensorDataGenerator sensorDataGenerator)
    {
        this.sensorDataGenerator = sensorDataGenerator;
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

    public SensorDataGenerator getSensorGenerator()
    {
        return sensorDataGenerator;
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
    
    public BooleanProperty errorProperty()
    {
        return errorProperty;
    }
    
    public boolean isError()
    {
        return errorProperty.get();
    }

    public void setError(boolean error)
    {
        this.errorProperty.set(error);
    }
}
