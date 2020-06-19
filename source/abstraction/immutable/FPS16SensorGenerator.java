package abstraction.immutable;

import java.nio.ByteBuffer;

public class FPS16SensorGenerator implements SensorGenerator
{
    public static final int DEFAULT_RATE = 10;

    private String          missionNumber;
    private String          jobOrderNumber;

    private long            count        = 0;
    
    public FPS16SensorGenerator(String missionNumber, String jobOrderNumber)
    {
        this.missionNumber = missionNumber;
        this.jobOrderNumber = jobOrderNumber;
        // TODO create an instance of FPS16 codec and populate all the fields
    }

    @Override
    public String getSensor()
    {
        return "FPS-16";
    }

    @Override
    public String getDescription()
    {
        return missionNumber + " " + jobOrderNumber;
    }

    @Override
    public long getCount()
    {
        return count;
    }
    
    @Override
    public ByteBuffer getNext()
    {
        // TODO call to the Position generator (static/dynamic) and set the latest position in the codec
        // TODO use the codec to create a ByteBuffer
        
        return ByteBuffer.allocate(3);
    }
}
