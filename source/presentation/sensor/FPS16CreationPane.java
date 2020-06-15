package presentation.sensor;

import abstraction.immutable.FPS16SensorGenerator;
import controller.MainController;
import javafx.scene.control.TextField;
import mil.af.eglin.ccf.rt.fx.layout.VBox;

public class FPS16CreationPane extends SensorCreationPane
{
    private final MainController controller;
    
    private final TextField textMissionNumber = new TextField();
    private final TextField textJobOrderNumber = new TextField();
    
    public FPS16CreationPane(MainController controller)
    {
        this.controller = controller;
        
        VBox vbox = new VBox();
        
        textMissionNumber.setPromptText("Mission Number");
        textJobOrderNumber.setPromptText("JON");
        
        textMissionNumber.setText("9999");
        textJobOrderNumber.setText("99999999");
        
        // TODO add a TSPI Configurator
        
        vbox.getChildren().addAll(textMissionNumber, textJobOrderNumber);
        
        setCenter(vbox);
    }

    @Override
    void clear()
    {
        textMissionNumber.clear();
        textJobOrderNumber.clear();
    }

    @Override
    void add()
    {
        // TODO read the input from the pane - create a generator and call controller to add it
        String missionNumber = textMissionNumber.getText();
        String jobOrderNumber = textJobOrderNumber.getText();
        
        FPS16SensorGenerator generator = new FPS16SensorGenerator(missionNumber, jobOrderNumber);
        controller.addGenerator(generator);
    }
}
