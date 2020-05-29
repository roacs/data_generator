package abstraction.sensor;

import java.nio.ByteBuffer;

import abstraction.immutable.SensorGenerator;

public class FPS16SensorGenerator implements SensorGenerator
{

    public FPS16SensorGenerator()
    {
        // TODO create an instance of FPS16 codec and populate all the fields
    }
    
    @Override
    public ByteBuffer getNext()
    {
        // TODO call to the Position generator (static/dynamic) and set the latest position in the codec
        // TODO use the codec to create a ByteBuffer
        return null;
    }

}
