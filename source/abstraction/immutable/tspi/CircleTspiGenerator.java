package abstraction.immutable.tspi;

import external.Acceleration;
import external.Position;
import external.Velocity;

public class CircleTspiGenerator implements TspiGenerator
{
    private final double   radiusMeters;
    private final double   elevationDegrees;
    private final double   azimuthPerSecond;
    private final Position origin;

    private double         currentAzimuth;
    private long           currentTime = 0;

    public CircleTspiGenerator(double radiusMeters, double elevationDegrees, double azimuthPerSecond, Position origin)
    {
        this.radiusMeters = radiusMeters;
        this.elevationDegrees = elevationDegrees;
        this.azimuthPerSecond = azimuthPerSecond;
        this.origin = origin;
    }

    @Override
    public void moveToTime(long millis)
    {
        if (currentTime == 0)
        {
            currentTime = millis;
            currentAzimuth = 0.0;
        }
        else
        {
            double delta = ((double) (millis - currentTime)) / 1000;
            currentAzimuth += normalize(azimuthPerSecond * delta);
            currentTime = millis;
        }
    }

    private static double normalize(double degrees)
    {
        while (degrees < 0.0)
        {
            degrees += 360;
        }
        return degrees % 360.0;
    }

    @Override
    public Position getPosition()
    {
        return Position.createWithSpherical(currentAzimuth, elevationDegrees, radiusMeters, origin);
    }

    @Override
    public Velocity getVelocity()
    {
        // TODO use a filter to generate velocity and acceleration from the modeled positions
        return null;
    }

    @Override
    public Acceleration getAcceleration()
    {
        // TODO use a filter to generate velocity and acceleration from the modeled positions
        return null;
    }
    
    @Override
    public String getDescription()
    {
        return String.format("Circle [%.1f, %.1f, %.1f]", radiusMeters, elevationDegrees, azimuthPerSecond);
    }
}
