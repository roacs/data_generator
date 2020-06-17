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
        // TODO properly shut down the executor
        
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->
        {
            synchronized (database.getSensorGeneratorMap())
            {
                for (Integer id : database.getSensorGeneratorMap().keySet())
                {
                    database.getSensorGeneratorMap().get(id).incrementCount();
                }
            }
        }, 0, 500, TimeUnit.MILLISECONDS); 
    }
}
