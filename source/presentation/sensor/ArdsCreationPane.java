package presentation.sensor;

import controller.MainController;
import javafx.scene.control.TextField;
import mil.af.eglin.ccf.rt.fx.layout.VBox;

public class ArdsCreationPane extends SensorCreationPane
{
    public ArdsCreationPane(MainController controller)
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
    void add()
    {
        // TODO Auto-generated method stub
    }

}
