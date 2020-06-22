package presentation.sensor;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// TODO need to make the channel box enabled/disabled visible/invisible depending on output selection
public abstract class SensorCreationPane extends BorderPane
{
    private final TextField textRate    = new TextField();
    private final TextField textChannel = new TextField();
    
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
        
        textChannel.setPromptText("Channel");
        textChannel.setTextFormatter(new TextFormatter<>(c ->
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
            
            return rate > 0 ? c : null;
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
            vbox.getChildren().add(new Label("Output"));
            vbox.getChildren().add(textRate);
            vbox.getChildren().add(textChannel);
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

    int getChannel()
    {
        return Integer.parseInt(textChannel.getText());
    }
    
    void setChannel(int channel)
    {
        textChannel.setText(String.valueOf(channel));
    }
}
