package abstraction.immutable.tspi;

import external.Acceleration;
import external.Position;
import external.Velocity;

public interface TspiGenerator
{
    public void moveToTime(long millis);
    
    public Position getPosition();
    public Velocity getVelocity();
    public Acceleration getAcceleration();
    
    public String getDescription();
    
}
