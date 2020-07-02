package external;

public class Position
{
    private double latitude;
    private double longitude;
    private double hae;
    
    private double azimuth;
    private double elevation;
    private double range;
    private Position origin;
    
    private Position()
    {
        
    }
    
    public static Position createWithSpherical(double azimuth, double elevation, double range, Position origin)
    {
        Position p = new Position();
        p.azimuth = azimuth;
        p.elevation = elevation;
        p.range = range;
        p.origin = origin;
        return p;
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

    public double getAzimuth()
    {
        return azimuth;
    }

    public double getElevation()
    {
        return elevation;
    }

    public double getRange()
    {
        return range;
    }

    public Position getOrigin()
    {
        return origin;
    }
}
