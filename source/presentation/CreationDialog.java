package presentation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.immutable.Sensor;
import controller.MainController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.sensor.ArdsCreationPane;
import presentation.sensor.FPS16CreationPane;
import presentation.sensor.P5CreationPane;
import presentation.sensor.SensorCreationPane;

public class CreationDialog extends Stage
{
    private static final Map<Sensor, SensorCreationPane> sensorToPane;
    static
    {
        Map<Sensor, SensorCreationPane> map = new HashMap<>();
        map.put(Sensor.ARDS, new ArdsCreationPane());
        map.put(Sensor.FPS16, new FPS16CreationPane());
        map.put(Sensor.P5, new P5CreationPane());
        
        sensorToPane = Collections.unmodifiableMap(map);
    }

    public CreationDialog(MainController controller)
    {
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Sensor Data Creation");
        
        StackPane stackPane = new StackPane();
        for (Sensor key : sensorToPane.keySet())
        {
            stackPane.getChildren().add(sensorToPane.get(key));
        }

        ListView<Sensor> listView = new ListView<Sensor>(controller.getModel().getSensorListItems());
        listView.setOnMousePressed((e) ->
        {
            showNode(stackPane.getChildren(), sensorToPane.get(listView.getSelectionModel().getSelectedItem()));
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
