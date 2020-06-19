package presentation;

import java.lang.reflect.InvocationTargetException;

import abstraction.immutable.Sensor;
import control.DeckPane;
import controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.sensor.SensorCreationPane;
import presentation.sensor.SensorCreationPaneMap;

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
        for (Sensor s : SensorCreationPaneMap.get().keySet())
        {
            try
            {
                sensorDeck.addCard(s.toString(), (SensorCreationPane) SensorCreationPaneMap.get().get(s).getConstructor(MainController.class).newInstance(controller));
            }
            catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1)
            {
                throw new RuntimeException("Unable to construct SensorCreationPane for " + s);
            }
        }

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
