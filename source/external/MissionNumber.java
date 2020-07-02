package external;

// TODO use the actual rt-mission/MissionNumber
public class MissionNumber
{
    private String missionNumber;
    private boolean playback;
    
    public MissionNumber(String missionNumber, boolean playback) 
    {
        this.missionNumber = missionNumber;
        this.playback = playback;
    }
    
    public String toString()
    {
        return "[" + missionNumber + ":" + playback + "]";
    }

    public int getMissionNumber()
    {
        return Integer.parseInt(missionNumber);
    }
}
