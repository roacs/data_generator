package presentation.tspi;

import abstraction.immutable.tspi.CircleTspiGenerator;
import abstraction.immutable.tspi.TspiGenerator;
import control.DoubleTextField;
import external.Position;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class CircleTspiInputPane extends TspiInputPane
{
    private final DoubleTextField textRadius           = new DoubleTextField();
    private final DoubleTextField textElevation        = new DoubleTextField();
    private final DoubleTextField textAzimuthPerSecond = new DoubleTextField();

    private final DoubleTextField textLatitude         = new DoubleTextField();
    private final DoubleTextField textLongitude        = new DoubleTextField();
    private final DoubleTextField textAltitudeHAE      = new DoubleTextField();

    private final CheckBox        checkNoise           = new CheckBox("Gaussian Noise");
    private final DoubleTextField textNoiseRadius      = new DoubleTextField();
    private final DoubleTextField textNoiseAzimuth     = new DoubleTextField();
    private final DoubleTextField textNoiseElevation   = new DoubleTextField();

    public CircleTspiInputPane()
    {
        textRadius.setPromptText("Radius (meters)");
        textElevation.setPromptText("Elevation (degrees)");
        textAzimuthPerSecond.setPromptText("Azimuth/Second (degrees/sec)");

        textLatitude.setPromptText("Latitude (degrees)");
        textLongitude.setPromptText("Longitude (degrees)");
        textAltitudeHAE.setPromptText("Altitude HAE (meters)");
        
        textNoiseRadius.setPromptText("Radius Std. Dev. (meters)");
        textNoiseAzimuth.setPromptText("Azimuth Std. Dev. (degrees)");
        textNoiseElevation.setPromptText("Elevation Std. Dev. (degrees)");
        textNoiseRadius.visibleProperty().bind(checkNoise.selectedProperty());
        textNoiseAzimuth.visibleProperty().bind(checkNoise.selectedProperty());
        textNoiseElevation.visibleProperty().bind(checkNoise.selectedProperty());

        VBox vbox = new VBox();
        {
            vbox.getChildren().add(textRadius);
            vbox.getChildren().add(textElevation);
            vbox.getChildren().add(textAzimuthPerSecond);

            vbox.getChildren().add(textLatitude);
            vbox.getChildren().add(textLongitude);
            vbox.getChildren().add(textAltitudeHAE);

            vbox.getChildren().add(checkNoise);
            vbox.getChildren().add(textNoiseRadius);
            vbox.getChildren().add(textNoiseAzimuth);
            vbox.getChildren().add(textNoiseElevation);
        }
        setCenter(vbox);
    }

    @Override
    public TspiGenerator getTspiGenerator()
    {
        if (checkNoise.isSelected())
        {
            return new CircleTspiGenerator(textRadius.getValue(), textElevation.getValue(), textAzimuthPerSecond.getValue(),
                    Position.createWithGeodeticHAE(textLatitude.getValue(), textLongitude.getValue(), textAltitudeHAE.getValue()),
                    textNoiseRadius.getValue(), textNoiseAzimuth.getValue(), textNoiseElevation.getValue());
        }
        else
        {
            return new CircleTspiGenerator(textRadius.getValue(), textElevation.getValue(), textAzimuthPerSecond.getValue(),
                    Position.createWithGeodeticHAE(textLatitude.getValue(), textLongitude.getValue(), textAltitudeHAE.getValue()));
        }
    }
}
