package presentation;

import abstraction.immutable.Sensor;
import controller.MainController;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainPresentation extends BorderPane
{
    public MainPresentation(MainController controller)
    {
        Button create = new Button("Create");
        create.setOnAction((e) ->
        {
            new CreationDialog(controller);
        });
        
        TableView<TableRowEntry> table = new TableView<TableRowEntry>();
        
        TableColumn<TableRowEntry, Boolean> columnEnabled = new TableColumn<>("Enabled");
        columnEnabled.setCellValueFactory(cellData -> cellData.getValue().enabledProperty());
        columnEnabled.setPrefWidth(60);
        
        TableColumn<TableRowEntry, Sensor> columnSensor = new TableColumn<>("Sensor");
        columnSensor.setCellValueFactory(cellData -> cellData.getValue().sensorProperty());
        columnSensor.setPrefWidth(125);
        
        TableColumn<TableRowEntry, String> columnDescription = new TableColumn<>("Description");
        columnDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        columnDescription.setPrefWidth(200);
        
        TableColumn<TableRowEntry, Number> columnCount = new TableColumn<>("Count");
        columnCount.setCellValueFactory(cellData -> cellData.getValue().countProperty());
        columnCount.setPrefWidth(50);
        
        table.getColumns().add(columnEnabled);
        table.getColumns().add(columnSensor);
        table.getColumns().add(columnDescription);
        table.getColumns().add(columnCount);
        table.setItems(controller.getModel().getTableItems());
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(create, table);
        
        // TODO add output destination information

        setCenter(vbox);
    }
}
