package controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.MainPresentation;

public class DataGenerator extends Application
{
    private Stage stage;

    public static void main(String[] args)
    {
        launch(args);
    }

    public Stage getStage()
    {
        return this.stage;
    }

    @Override
    public void start(Stage stage)
    {
        this.stage = stage;

        MainController controller = new MainController();

        stage.setTitle("Data Generator");

        Scene scene = new Scene(new MainPresentation(controller));
        this.stage.setScene(scene);
        this.stage.show();
    }
}