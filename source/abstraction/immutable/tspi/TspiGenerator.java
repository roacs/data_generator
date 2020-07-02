package abstraction.immutable.tspi;

import external.Position;

public interface TspiGenerator
{
    public void next();
    
    public Position getPosition();
//    public Velocity getVelocity();
//    public Acceleration getAcceleration();
}
