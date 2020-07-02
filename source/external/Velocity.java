package external;

public class Velocity
{
    private double x;
    private double y;
    private double z;
    
    private Velocity()
    {
        
    }
    
    public static Velocity createWithGeocentric(double x, double y, double z)
    {
        Velocity v = new Velocity();
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
