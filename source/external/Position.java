package external;

public class Position
{
    private double latitude;
    private double longitude;
    private double hae;
    
    private Position()
    {
        
    }
    
    public static Position createWithGeodeticHAE(double latitude, double longitude, double hae)
    {
        Position p = new Position();
        p.latitude = latitude;
        p.longitude = longitude;
        p.hae = hae;
        return p;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public double getHae()
    {
        return hae;
    }
}
