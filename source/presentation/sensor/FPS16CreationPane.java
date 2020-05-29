package presentation.sensor;

import abstraction.immutable.SensorGenerator;
import javafx.scene.control.TextField;
import mil.af.eglin.ccf.rt.fx.layout.VBox;

public class FPS16CreationPane extends SensorCreationPane
{
    public FPS16CreationPane()
    {
        VBox vbox = new VBox();
        
        TextField textMissionNumber = new TextField("9999");
        textMissionNumber.setPromptText("Mission Number");
        
        TextField textJobOrderNumber = new TextField("99999999");
        textJobOrderNumber.setPromptText("JON");
        
        vbox.getChildren().addAll(textMissionNumber, textJobOrderNumber);
        
        setCenter(vbox);
    }

    @Override
    void clear()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    SensorGenerator add()
    {
        // TODO read the input from the pane and create a SensorGenerator
        return null;
    }
}
