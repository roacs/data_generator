package presentation;

import controller.MainController;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class MainPresentation extends BorderPane
{
    public MainPresentation(MainController controller)
    {
        Button create = new Button("Create");
        create.setOnAction((e) ->
        {
            new CreationDialog(controller);
        });
        create.setDefaultButton(true);
        
        TableView<TableRowEntry> table = new TableView<>();
        
        TableColumn<TableRowEntry, Boolean> columnEnabled = new TableColumn<>("Enabled");
        columnEnabled.setCellValueFactory(cellData -> cellData.getValue().enabledProperty());
        columnEnabled.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>()
        {
            @Override
            public ObservableValue<Boolean> call(Integer index)
            {
                TableRowEntry entry = table.getItems().get(index);
                controller.setEnabled(entry.getGeneratorId(), entry.isEnabled());
                return entry.enabledProperty();
            }
        }));
        columnEnabled.setPrefWidth(60);
        
        TableColumn<TableRowEntry, String> columnSensor = new TableColumn<>("Sensor");
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
        
        ContextMenu menu = new ContextMenu();
        {
            MenuItem menuRemove = new MenuItem("Remove");
            menuRemove.setOnAction(e -> controller.removeGenerator(table.getSelectionModel().getSelectedItem().getGeneratorId()));
            
            menu.getItems().add(menuRemove);
        }
        table.setContextMenu(menu);
        table.setEditable(true);
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(create, table);
        
        // TODO change enabled column to checkboxes
        // TODO add callbacks for enabled 
        
        // TODO add output destination information

        setCenter(vbox);
    }
}
