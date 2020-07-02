package presentation.tspi;

import abstraction.immutable.tspi.StaticTspiGenerator;
import abstraction.immutable.tspi.TspiGenerator;
import control.DoubleTextField;
import external.Position;
import javafx.scene.layout.VBox;

public class StaticTspiInputPane extends TspiInputPane
{
    private final DoubleTextField textLatitude    = new DoubleTextField();
    private final DoubleTextField textLongitude   = new DoubleTextField();
    private final DoubleTextField textAltitudeHAE = new DoubleTextField();

    public StaticTspiInputPane()
    {
        textLatitude.setPromptText("Latitude (degrees)");
        textLongitude.setPromptText("Longitude (degrees)");
        textAltitudeHAE.setPromptText("Altitude HAE (meters)");

        VBox vbox = new VBox();
        {
            vbox.getChildren().add(textLatitude);
            vbox.getChildren().add(textLongitude);
            vbox.getChildren().add(textAltitudeHAE);
        }
        setCenter(vbox);
    }

    @Override
    public TspiGenerator getTspiGenerator()
    {
        return new StaticTspiGenerator(Position.createWithGeodeticHAE(textLatitude.getValue(), textLongitude.getValue(), textAltitudeHAE.getValue()));
    }
}
