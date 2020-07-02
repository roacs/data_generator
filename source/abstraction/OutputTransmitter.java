package abstraction;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import abstraction.immutable.OutputChannel;
import external.MissionNumber;

public class OutputTransmitter
{
    private final Map<Integer, ScheduledFuture<?>> generatorToThread = new HashMap<>();
    private final ScheduledExecutorService         executor          = Executors.newScheduledThreadPool(10);
    private final DatagramChannel                  datagramChannel;
    private OutputChannel                          outputChannel;
    private InetSocketAddress                      destinationAddress;

    public OutputTransmitter() throws IOException
    {
        datagramChannel = DatagramChannel.open();
    }
    
    public void stop()
    {
        executor.shutdownNow();
        try
        {
            datagramChannel.close();
        }
        catch (IOException ignore)
        {
        }
    }

    public void addGenerator(Integer generatorId, SensorDataGeneratorInformation generatorInformation)
    {
        generatorToThread.put(generatorId, executor.scheduleAtFixedRate(() ->
        {
            if (generatorInformation.isEnabled())
            {
                ByteBuffer rawSensorData = generatorInformation.getSensorGenerator().generateAtTime(System.currentTimeMillis());
                try
                {
                    datagramChannel.send(wrapSensorData(outputChannel, rawSensorData), destinationAddress);
                    generatorInformation.incrementCount();
                }
                catch (IOException e)
                {
                    generatorInformation.setError(true);
                }
            }
        }, 0, getRateInMilliseconds(generatorInformation.getRate()), TimeUnit.MILLISECONDS));
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

    public void setOutputDestination(OutputChannel outputChannel, String hostname, MissionNumber missionNumber)
    {
        this.outputChannel = outputChannel;
        
        destinationAddress = getOutputAddress(outputChannel, hostname, missionNumber);
    }
    
    private static InetSocketAddress getOutputAddress(OutputChannel outputChannel, String hostname, MissionNumber missionNumber)
    {
        // TODO set up output destinations based on inputs, error if something is missing
        if (outputChannel == OutputChannel.MISSION_SENSOR)
        {
            return new InetSocketAddress("127.0.0.1", 55001);
        }
        else if (outputChannel == OutputChannel.RAW)
        {
            return new InetSocketAddress("127.0.0.1", 55001);
        }
        else if (outputChannel == OutputChannel.TSPI_NODE)
        {
            return new InetSocketAddress("127.0.0.1", 55001);
        }
        else
        {
            throw new IllegalStateException("Somehow an enum was selected that does not exist.");
        }
    }
    
    private static ByteBuffer wrapSensorData(OutputChannel outputChannel, ByteBuffer rawSensorData)
    {
        // TODO wrap the packet in the appropriate header
        ByteBuffer packet;
        if (outputChannel == OutputChannel.MISSION_SENSOR)
        {
            
        }
        else if (outputChannel == OutputChannel.RAW)
        {
            
        }
        else if (outputChannel == OutputChannel.TSPI_NODE)
        {
            
        }
        
        return rawSensorData;
    }
}
