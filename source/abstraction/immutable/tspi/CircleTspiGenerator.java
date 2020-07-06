package abstraction.immutable.tspi;

import java.util.Random;

import external.Acceleration;
import external.Position;
import external.Velocity;

public class CircleTspiGenerator implements TspiGenerator
{
    private final Random   noise;
    private final double   radiusMeters;
    private final double   elevationDegrees;
    private final double   azimuthPerSecond;
    private final Position origin;
    private final double   standardDeviationRadius;
    private final double   standardDeviationAzimuth;
    private final double   standardDeviationElevation;
    private final boolean  noiseAdded;

    private double         currentAzimuth;
    private long           currentTime = 0;

    public CircleTspiGenerator(double radiusMeters, double elevationDegrees, double azimuthPerSecond, Position origin)
    {
        this.noise = new Random(System.currentTimeMillis());
        this.radiusMeters = radiusMeters;
        this.elevationDegrees = elevationDegrees;
        this.azimuthPerSecond = azimuthPerSecond;
        this.origin = origin;
        
        this.standardDeviationRadius = 0;
        this.standardDeviationAzimuth = 0;
        this.standardDeviationElevation = 0;
        this.noiseAdded = false;
    }
    
    public CircleTspiGenerator(double radiusMeters, double elevationDegrees, double azimuthPerSecond, Position origin, double standardDeviationRadius, double standardDeviationAzimuth, double standardDeviationElevation)
    {
        this.noise = new Random(System.currentTimeMillis());
        this.radiusMeters = radiusMeters;
        this.elevationDegrees = elevationDegrees;
        this.azimuthPerSecond = azimuthPerSecond;
        this.origin = origin;
        
        this.standardDeviationRadius = standardDeviationRadius;
        this.standardDeviationAzimuth = standardDeviationAzimuth;
        this.standardDeviationElevation = standardDeviationElevation;
        this.noiseAdded = true;
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
        double azimuth = noiseAdded ? currentAzimuth + (noise.nextGaussian() * standardDeviationAzimuth) : currentAzimuth;
        double elevation = noiseAdded ? elevationDegrees + (noise.nextGaussian() * standardDeviationElevation) : elevationDegrees;
        double range = noiseAdded ? radiusMeters + (noise.nextGaussian() * standardDeviationRadius) : radiusMeters;
        
        System.out.println(azimuth + ", " + elevation + ", " + range);
        return Position.createWithSpherical(azimuth, elevation, range, origin);
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
