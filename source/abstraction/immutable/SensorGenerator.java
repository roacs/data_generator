package abstraction.immutable;

import java.nio.ByteBuffer;

public interface SensorGenerator
{
    public ByteBuffer getNext();
}
