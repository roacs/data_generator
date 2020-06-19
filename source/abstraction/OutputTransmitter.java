package abstraction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class OutputTransmitter
{
    private final Map<Integer, ScheduledFuture<?>> generatorToThread = new HashMap<>();
    private final ScheduledExecutorService         executor          = Executors.newScheduledThreadPool(10);

    public void start()
    {
        // TODO need output address, format as TspiNode, RawChannel or Mission channel?
    }

    public void stop()
    {
        executor.shutdownNow();
    }

    public void addGenerator(Integer generatorId, SensorGeneratorInformation sensorGeneratorInformation)
    {
        generatorToThread.put(generatorId, executor.scheduleAtFixedRate(() ->
        {
            if (sensorGeneratorInformation.isEnabled())
            {
                // TODO generate a packet and send it
                sensorGeneratorInformation.getSensorGenerator().getNext();
                

                sensorGeneratorInformation.incrementCount();
            }
        }, 0, getRateInMilliseconds(sensorGeneratorInformation.getRate()), TimeUnit.MILLISECONDS));
    }

    public void removeGenerator(Integer generatorId)
    {
        if (generatorToThread.containsKey(generatorId))
        {
            generatorToThread.get(generatorId).cancel(true);
        }
        generatorToThread.remove(generatorId);
    }
    
    private long getRateInMilliseconds(long hertz)
    {
        return 1000 / hertz;
    }
}
