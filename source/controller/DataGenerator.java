package controller;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO
//  - maybe consider converting the output from an application-wide setting to a per sensor entry setting
public class DataGenerator extends Application
{
    private Stage          stage;
    private MainController controller;

    public static void main(String[] args)
    {
        launch(args);
    }

    public Stage getStage()
    {
        return this.stage;
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        this.stage = stage;

        controller = new MainController();

        stage.setTitle("Data Generator");

        Scene scene = new Scene(controller.getPresentation());
        this.stage.setScene(scene);
        this.stage.show();
    }
    
    @Override
    public void stop()
    {
        controller.stop();
    }
}
