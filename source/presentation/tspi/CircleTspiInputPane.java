package presentation.tspi;

import abstraction.immutable.tspi.CircleTspiGenerator;
import abstraction.immutable.tspi.TspiGenerator;
import control.DoubleTextField;
import external.Position;
import javafx.scene.layout.VBox;

public class CircleTspiInputPane extends TspiInputPane
{
    private final DoubleTextField textRadius           = new DoubleTextField();
    private final DoubleTextField textElevation        = new DoubleTextField();
    private final DoubleTextField textAzimuthPerSecond = new DoubleTextField();

    private final DoubleTextField textLatitude         = new DoubleTextField();
    private final DoubleTextField textLongitude        = new DoubleTextField();
    private final DoubleTextField textAltitudeHAE      = new DoubleTextField();

    public CircleTspiInputPane()
    {
        textRadius.setPromptText("Radius (meters)");
        textElevation.setPromptText("Elevation (degrees)");
        textAzimuthPerSecond.setPromptText("Azimuth/Second (degrees/sec)");
        
        textLatitude.setPromptText("Latitude (degrees)");
        textLongitude.setPromptText("Longitude (degrees)");
        textAltitudeHAE.setPromptText("Altitude HAE (meters)");

        VBox vbox = new VBox();
        {
            vbox.getChildren().add(textRadius);
            vbox.getChildren().add(textElevation);
            vbox.getChildren().add(textAzimuthPerSecond);
            
            vbox.getChildren().add(textLatitude);
            vbox.getChildren().add(textLongitude);
            vbox.getChildren().add(textAltitudeHAE);
        }
        setCenter(vbox);
    }

    @Override
    public TspiGenerator getTspiGenerator()
    {
        return new CircleTspiGenerator(textRadius.getValue(), textElevation.getValue(), textAzimuthPerSecond.getValue(),
                Position.createWithGeodeticHAE(textLatitude.getValue(), textLongitude.getValue(), textAltitudeHAE.getValue()));
    }
}
