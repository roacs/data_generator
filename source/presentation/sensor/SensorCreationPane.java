package presentation.sensor;

import abstraction.immutable.SensorGenerator;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public abstract class SensorCreationPane extends BorderPane
{
    abstract void clear();
    abstract SensorGenerator add();
    
    public SensorCreationPane()
    {
        Button clear = new Button("Clear");
        clear.setOnAction((e) -> clear());
        
        Button add = new Button("Add");
        add.setOnAction((e) -> add());
        
        Button cancel = new Button("Cancel");
        
        // TODO add buttons to the bottom of every sensor creation pane
    }
}
