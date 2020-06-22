package presentation;

import java.util.HashMap;
import java.util.Map;

import abstraction.immutable.OutputChannel;
import control.DeckPane;
import controller.MainController;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import presentation.output.MissionSensorOutputPane;
import presentation.output.RawOutputPane;
import presentation.output.TspiNodeOutputPane;

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
        
        TableColumn<TableRowEntry, Number> columnRate = new TableColumn<>("Rate");
        columnRate.setCellValueFactory(cellData -> cellData.getValue().rateProperty());
        columnRate.setPrefWidth(50);
        
        TableColumn<TableRowEntry, Number> columnChannel = new TableColumn<>("Channel");
        columnChannel.setCellValueFactory(cellData -> cellData.getValue().channelProperty());
        columnChannel.setPrefWidth(50);
        
        TableColumn<TableRowEntry, Number> columnCount = new TableColumn<>("Count");
        columnCount.setCellValueFactory(cellData -> cellData.getValue().countProperty());
        columnCount.setPrefWidth(50);
        
        table.getColumns().add(columnEnabled);
        table.getColumns().add(columnSensor);
        table.getColumns().add(columnDescription);
        table.getColumns().add(columnRate);
        table.getColumns().add(columnChannel);
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
        
        Map<OutputChannel, RadioButton> outputChannelRadioButtons = new HashMap<OutputChannel, RadioButton>();
        ToggleGroup outputGroup = new ToggleGroup();
        for (OutputChannel channel : OutputChannel.values())
        {
            RadioButton radioButton = new RadioButton(channel.toString());
            radioButton.setOnAction(e -> controller.setOutputChannelSelected(channel));
            radioButton.setToggleGroup(outputGroup);
            
            outputChannelRadioButtons.put(channel, radioButton);
        }
        
        DeckPane outputDeck = new DeckPane();
        {
            // TODO enumerate and create dynamically in the above for loop? similar to SensorCreationPane?
            outputDeck.addCard(OutputChannel.TSPI_NODE.toString(), new TspiNodeOutputPane(controller));
            outputDeck.addCard(OutputChannel.RAW.toString(), new RawOutputPane(controller));
            outputDeck.addCard(OutputChannel.MISSION_SENSOR.toString(), new MissionSensorOutputPane(controller));
        }
        
        controller.getModel().outputChannelProperty().addListener((obj, o, n) -> 
        {
            outputGroup.selectToggle(outputChannelRadioButtons.get(n));
            outputDeck.showCard(n.toString());
        });
        
        HBox output = new HBox();
        {
            VBox outputSelection = new VBox();
            {
                for (RadioButton radio : outputChannelRadioButtons.values())
                {
                    outputSelection.getChildren().add(radio);
                }
            }
            output.getChildren().add(outputSelection);

            output.getChildren().add(outputDeck);
        }
        
        VBox center = new VBox();
        {
            HBox top = new HBox();
            {
                top.getChildren().add(create);
                top.getChildren().add(output);
            }
            center.getChildren().add(top);
            center.getChildren().add(table);
        }
        setCenter(center);
    }
}
