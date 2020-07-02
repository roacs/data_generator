package abstraction.immutable.tspi;

import external.Acceleration;
import external.Position;
import external.Velocity;

public class StaticTspiGenerator implements TspiGenerator
{
    private Position     position;
    private Velocity     velocity;
    private Acceleration acceleration;

    public StaticTspiGenerator(Position position, Velocity velocity, Acceleration acceleration)
    {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    @Override
    public void moveToTime(long millis)
    {
        // nothing to do
    }

    @Override
    public Position getPosition()
    {
        return position;
    }

    @Override
    public Velocity getVelocity()
    {
        return velocity;
    }

    @Override
    public Acceleration getAcceleration()
    {
        return acceleration;
    }

    @Override
    public String getDescription()
    {
        return "Static";
    }

}
