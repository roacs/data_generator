package abstraction.immutable;

import java.nio.ByteBuffer;

public interface SensorGenerator
{
    public String getSensor();
    public String getDescription();
    public long getCount();
    
    public ByteBuffer getNext();
}
