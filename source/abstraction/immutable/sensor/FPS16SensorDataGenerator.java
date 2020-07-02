package abstraction.immutable.sensor;

import java.nio.ByteBuffer;

import abstraction.immutable.tspi.TspiGenerator;
import external.MissionNumber;
import external.Rics1TrackingRadarCodec;

public class FPS16SensorDataGenerator implements SensorDataGenerator
{
    public static final int         DEFAULT_RATE    = 10;
    public static final int         DEFAULT_CHANNEL = 31;

    private TspiGenerator           tspiGenerator;
    private String                  jobOrderNumber;
    private Rics1TrackingRadarCodec codec;

    private long                    count           = 0;

    public FPS16SensorDataGenerator(TspiGenerator tspiGenerator, long sensorId, MissionNumber missionNumber, String jobOrderNumber)
    {
        this.tspiGenerator = tspiGenerator;
        this.jobOrderNumber = jobOrderNumber;

        codec = new Rics1TrackingRadarCodec();
        codec.setSensorId(sensorId);
        codec.setMissionNumber(missionNumber.getMissionNumber());
        // TODO populate all the fields of the codec
    }

    @Override
    public String getSensor()
    {
        return "FPS-16";
    }

    @Override
    public String getDescription()
    {
        return codec.getMissionNumber() + jobOrderNumber;
    }

    @Override
    public long getCount()
    {
        return count;
    }

    @Override
    public ByteBuffer getNext()
    {
        tspiGenerator.next();
        codec.setPosition(tspiGenerator.getPosition());
        
        return Rics1TrackingRadarCodec.encode(codec);
    }
}
