package presentation.sensor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import abstraction.immutable.Sensor;

public class SensorCreationPaneMap
{
    private static final Map<Sensor, Class<? extends SensorCreationPane>> SENSOR_TO_PANE_CLASS;
    
    static
    {
        Map<Sensor, Class<? extends SensorCreationPane>> map = new HashMap<>();
        map.put(Sensor.ARDS, ArdsCreationPane.class);
        map.put(Sensor.FPS16, FPS16CreationPane.class);
        map.put(Sensor.P5, P5CreationPane.class);

        SENSOR_TO_PANE_CLASS = Collections.unmodifiableMap(map);
    }
    
    public static Map<Sensor, Class<? extends SensorCreationPane>> get()
    {
        return SENSOR_TO_PANE_CLASS;
    }
}
