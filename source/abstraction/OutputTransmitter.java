package abstraction;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OutputTransmitter
{
    private final Database database;

    public OutputTransmitter(Database database)
    {
        this.database = database;
    }

    public void start()
    {
        // TODO processing thread
        // TODO need output address, format as TspiNode, RawChannel or Mission channel?
        // TODO rate selection by sensorgenerator?
        
        Executors.newSingleThreadScheduledExecutor().schedule(() ->
        {
            synchronized (database.getSensorGeneratorMap())
            {
                for (Integer id : database.getSensorGeneratorMap().keySet())
                {
                    SensorGeneratorInformation info = database.getSensorGeneratorMap().get(id);
                    info.setCount(info.getCount() + 1);
                }
            }
        }, 1000, TimeUnit.MILLISECONDS); 
    }
}
