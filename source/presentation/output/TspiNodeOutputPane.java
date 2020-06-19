package presentation.output;

import controller.MainController;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TspiNodeOutputPane extends BorderPane
{
    public TspiNodeOutputPane(MainController controller)
    {
        TextField textHostname = new TextField();
        textHostname.setPromptText("Hostname");
        textHostname.textProperty().addListener((obs, o, n) -> controller.setHostname(n));
        
        VBox vbox = new VBox();
        {
            vbox.getChildren().add(textHostname);
        }
        
        setCenter(vbox);
        
        // TODO any validation to be added for hostnames?
    }
}
