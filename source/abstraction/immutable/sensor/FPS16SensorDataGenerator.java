package abstraction.immutable.sensor;

import java.nio.ByteBuffer;

import abstraction.immutable.tspi.TspiGenerator;

public class FPS16SensorDataGenerator implements SensorDataGenerator
{
    public static final int DEFAULT_RATE    = 10;
    public static final int DEFAULT_CHANNEL = 31;

    private TspiGenerator   tspiGenerator;
    private String          missionNumber;
    private String          jobOrderNumber;

    private long            count           = 0;
    
    public FPS16SensorDataGenerator(TspiGenerator tspiGenerator, String missionNumber, String jobOrderNumber)
    {
        this.tspiGenerator = tspiGenerator;
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
