package presentation.output;

import abstraction.immutable.MissionNumber;
import control.MissionNumberTextField;
import controller.MainController;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MissionSensorOutputPane extends BorderPane
{
    private MainController controller;
    
    public MissionSensorOutputPane(MainController controller)
    {
        this.controller = controller;
        
        TextField textMissionNumber = new MissionNumberTextField();
        textMissionNumber.setPromptText("Mission Number");
        CheckBox checkPlayback = new CheckBox("Playback");
        
        textMissionNumber.textProperty().addListener((obs, o, n) -> updateMissionNumber(n, checkPlayback.isSelected()));
        checkPlayback.selectedProperty().addListener((obs, o, n) -> updateMissionNumber(textMissionNumber.getText(), n));
        
        HBox hbox = new HBox();
        {
            hbox.getChildren().add(textMissionNumber);
            hbox.getChildren().add(checkPlayback);
        }
        
        setCenter(hbox);
    }

    private void updateMissionNumber(String missionNumber, boolean playback)
    {
        // TODO try catch the exception thrown and only update on valid mission numbers
        controller.setMissionNumber(new MissionNumber(missionNumber, playback));
    }
}
