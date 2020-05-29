package presentation.sensor;

import abstraction.immutable.SensorGenerator;
import javafx.scene.control.TextField;
import mil.af.eglin.ccf.rt.fx.layout.VBox;

public class ArdsCreationPane extends SensorCreationPane
{
    public ArdsCreationPane()
    {
        VBox vbox = new VBox();
        
        TextField textMissionNumber = new TextField();
        textMissionNumber.setPromptText("Mission Number");
        
        TextField textJobOrderNumber = new TextField();
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
        // TODO Auto-generated method stub
        return null;
    }

}
