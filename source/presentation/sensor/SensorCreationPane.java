package presentation.sensor;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public abstract class SensorCreationPane extends BorderPane
{
    abstract void clear();
    abstract void add();
    
    public SensorCreationPane()
    {
        Button clear = new Button("Clear");
        clear.setOnAction((e) -> clear());
        
        Button add = new Button("Add");
        add.setOnAction((e) -> 
        {
            add();
            closeDialog();
        });
        add.setDefaultButton(true);
        
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> closeDialog());
        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(clear, add, cancel);
        setBottom(hbox);
    }
    
    private void closeDialog()
    {
        ((Stage)getScene().getWindow()).close();
    }
}
