package presentation.tspi;

import abstraction.immutable.tspi.StaticTspiGenerator;
import abstraction.immutable.tspi.TspiGenerator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StaticTspiInputPane extends TspiInputPane
{
    private final TextField textLatitude = new TextField();
    private final TextField textLongitude = new TextField();
    private final TextField textAltitudeHAE = new TextField();
    
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
        // TODO make position and provide to StaticTspiGenerator
        return new StaticTspiGenerator();
    }
}
