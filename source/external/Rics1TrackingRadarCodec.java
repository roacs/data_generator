package external;

import java.nio.ByteBuffer;

public class Rics1TrackingRadarCodec
{
    private long sensorId;
    private int missionNumber;
    
    public Rics1TrackingRadarCodec()
    {
        
    }
    
    public static ByteBuffer encode(Rics1TrackingRadarCodec codec)
    {
        ByteBuffer buffer = ByteBuffer.allocate(50);
        buffer.putLong(codec.sensorId);
        buffer.putInt(codec.missionNumber);
        
        buffer.rewind();
        return buffer;
    }

    public long getSensorId()
    {
        return sensorId;
    }

    public void setSensorId(long sensorId)
    {
        this.sensorId = sensorId;
    }

    public int getMissionNumber()
    {
        return missionNumber;
    }

    public void setMissionNumber(int missionNumber)
    {
        this.missionNumber = missionNumber;
    }
    
    public void setPosition(Position position)
    {
        // TODO Auto-generated method stub
        
    }
}
