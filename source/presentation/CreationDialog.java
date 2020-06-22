package presentation;

import abstraction.immutable.sensor.Sensor;
import control.DeckPane;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.sensor.ArdsCreationPane;
import presentation.sensor.FPS16CreationPane;
import presentation.sensor.P5CreationPane;

public class CreationDialog extends Stage
{
    public CreationDialog(MainController controller)
    {
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Sensor Data Creation");
        
        addEventHandler(KeyEvent.KEY_RELEASED, e ->
        {
            if (KeyCode.ESCAPE == e.getCode())
            {
                this.close();
            }
        });
        
        DeckPane sensorDeck = new DeckPane();
        sensorDeck.addCard(Sensor.ARDS.toString(), new ArdsCreationPane(controller));
        sensorDeck.addCard(Sensor.FPS16.toString(), new FPS16CreationPane(controller));
        sensorDeck.addCard(Sensor.P5.toString(), new P5CreationPane(controller));

        ListView<Sensor> listView = new ListView<Sensor>(controller.getModel().getSensorListItems());
        listView.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> sensorDeck.showCard(n.toString()));
        
        BorderPane main = new BorderPane();
        main.setLeft(listView);
        main.setCenter(sensorDeck);
        
        listView.getSelectionModel().select(Sensor.FPS16);
        
        setScene(new Scene(main));
        showAndWait();
    }
}
