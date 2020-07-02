package presentation.sensor;

import abstraction.immutable.sensor.FPS16SensorDataGenerator;
import control.JobOrderNumberTextField;
import control.MissionNumberTextField;
import controller.MainController;
import external.MissionNumber;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import presentation.tspi.TSPIConfigurator;

public class FPS16CreationPane extends SensorCreationPane
{
    private final MainController   controller;

    private final TextField              textSensorId       = new TextField();
    private final MissionNumberTextField textMissionNumber  = new MissionNumberTextField();
    private final TextField              textJobOrderNumber = new JobOrderNumberTextField();
    private final TSPIConfigurator       tspiConfigurator   = new TSPIConfigurator();
    
    public FPS16CreationPane(MainController controller)
    {
        this.controller = controller;
        
        setRate(FPS16SensorDataGenerator.DEFAULT_RATE);
        setChannel(FPS16SensorDataGenerator.DEFAULT_CHANNEL);
        
        textSensorId.setPromptText("Sensor ID");
        
        textSensorId.setText("31");
        textMissionNumber.setText("9999");
        textJobOrderNumber.setText("99999999");
        
        VBox vbox = new VBox();
        {
            vbox.getChildren().add(new Label("Sensor"));
            vbox.getChildren().add(textMissionNumber);
            vbox.getChildren().add(textJobOrderNumber);
            vbox.getChildren().add(new Label("TSPI"));
            vbox.getChildren().add(tspiConfigurator);
        }
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
        long sensorId = Long.parseLong(textSensorId.getText());
        MissionNumber missionNumber = textMissionNumber.getValue();
        String jobOrderNumber = textJobOrderNumber.getText();
        
        FPS16SensorDataGenerator generator = new FPS16SensorDataGenerator(tspiConfigurator.getTspiGenerator(), sensorId, missionNumber, jobOrderNumber);
        controller.addSensorDataGenerator(getRate(), getChannel(), generator);
    }
}
