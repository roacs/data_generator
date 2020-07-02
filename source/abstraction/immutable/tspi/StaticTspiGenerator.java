package abstraction.immutable.tspi;

import external.Position;

public class StaticTspiGenerator implements TspiGenerator
{
    private Position position;
    
    public StaticTspiGenerator(Position position)
    {
        this.position = position;
    }

    @Override
    public void next()
    {
        // nothing to do
    }

    @Override
    public Position getPosition()
    {
        return position;
    }

}
