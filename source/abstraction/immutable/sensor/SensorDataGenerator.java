package abstraction.immutable.sensor;

import java.nio.ByteBuffer;

public interface SensorDataGenerator
{
    public String getSensor();
    public String getDescription();
    public long getCount();
    
    public ByteBuffer generateAtTime(long millis);
}
