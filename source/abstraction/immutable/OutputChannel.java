package abstraction.immutable;

public enum OutputChannel
{
    TSPI_NODE("Tspi Node"),
    RAW("Raw"),
    MISSION_SENSOR("Mission Sensor");

    private String name;
    
    OutputChannel(String name)
    {
        this.name = name;
    }
    
    public String toString()
    {
        return name;
    }
}
