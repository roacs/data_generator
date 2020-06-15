package presentation;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.immutable.Sensor;
import controller.MainController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.sensor.SensorCreationPane;
import presentation.sensor.SensorCreationPaneMap;

public class CreationDialog extends Stage
{
    private final Map<Sensor, SensorCreationPane> sensorToPane;

    public CreationDialog(MainController controller)
    {
        Map<Sensor, SensorCreationPane> map = new HashMap<>();
        for (Sensor s : SensorCreationPaneMap.get().keySet())
        {
            try
            {
                map.put(s, (SensorCreationPane)SensorCreationPaneMap.get().get(s).getConstructor(MainController.class).newInstance(controller));
            }
            catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1)
            {
                throw new RuntimeException("Unable to construct SensorCreationPane for " + s);
            }
        }
        sensorToPane = Collections.unmodifiableMap(map);
        
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Sensor Data Creation");
        
        addEventHandler(KeyEvent.KEY_RELEASED, e ->
        {
            if (KeyCode.ESCAPE == e.getCode())
            {
                this.close();
            }
        });
        
        StackPane stackPane = new StackPane();
        for (Sensor key : sensorToPane.keySet())
        {
            stackPane.getChildren().add(sensorToPane.get(key));
        }

        ListView<Sensor> listView = new ListView<Sensor>(controller.getModel().getSensorListItems());
        listView.getSelectionModel().selectedItemProperty().addListener((obs, o, n) ->
        {
            showNode(stackPane.getChildren(), sensorToPane.get(n));
        });
        
        BorderPane main = new BorderPane();
        main.setLeft(listView);
        main.setCenter(stackPane);
        
        listView.getSelectionModel().select(Sensor.FPS16);
        
        setScene(new Scene(main));
        showAndWait();
    }
    
    private static void showNode(List<Node> nodeList, Node nodeToShow)
    {
        for (Node n : nodeList)
        {
            n.setVisible(n.equals(nodeToShow));
        }
    }

}
