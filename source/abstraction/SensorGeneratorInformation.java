package abstraction;

import abstraction.immutable.SensorGenerator;

public class SensorGeneratorInformation
{
    private boolean         enabled;
    private long            count;
    private SensorGenerator sensorGenerator;

    public SensorGeneratorInformation(SensorGenerator sensorGenerator)
    {
        this.sensorGenerator = sensorGenerator;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public long getCount()
    {
        return count;
    }

    public void setCount(long count)
    {
        this.count = count;
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
