package presentation.sensor;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mil.af.eglin.ccf.rt.fx.layout.VBox;

public abstract class SensorCreationPane extends BorderPane
{
    private final TextField textRate = new TextField();
    
    abstract void clear();
    abstract void add();
    
    public SensorCreationPane()
    {
        textRate.setPromptText("Rate (Hz)");
        textRate.setTextFormatter(new TextFormatter<>(c ->
        {
            if (c.getControlNewText().isEmpty())
            {
                return c;
            }
            
            int rate;
            try
            {
                rate = Integer.parseInt(c.getControlNewText());
            }
            catch (NumberFormatException e)
            {
                return null;
            }
            
            return (rate > 0 && rate < 1000) ? c : null;
        }));
        
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
        
        VBox vbox = new VBox();
        {
            vbox.getChildren().add(textRate);
        }
        setTop(vbox);
        
        HBox hbox = new HBox();
        {
            hbox.getChildren().add(clear);
            hbox.getChildren().add(add);
            hbox.getChildren().add(cancel);
        }
        setBottom(hbox);
    }
    
    private void closeDialog()
    {
        ((Stage)getScene().getWindow()).close();
    }
    
    int getRate()
    {
        return Integer.parseInt(textRate.getText());
    }
    
    void setRate(int rate)
    {
        textRate.setText(String.valueOf(rate));
    }
}
