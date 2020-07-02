package external;

public class Acceleration
{
    private double x;
    private double y;
    private double z;
    
    private Acceleration()
    {
        
    }
    
    public static Acceleration createWithGeocentric(double x, double y, double z)
    {
        Acceleration v = new Acceleration();
        v.x = x;
        v.y = y;
        v.z = z;
        return v;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    
}
