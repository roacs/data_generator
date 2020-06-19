package control;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MissionNumberTextField extends TextField
{
    public MissionNumberTextField()
    {
        setPromptText("Mission Number");
        setTextFormatter(new TextFormatter<>(c ->
        {
            if (c.getControlNewText().isEmpty())
            {
                return c;
            }
            
            // TODO change to mission number
            int missionNumber;
            try
            {
                missionNumber = Integer.parseInt(c.getControlNewText());
            }
            catch (NumberFormatException e)
            {
                return null;
            }
            
            return (missionNumber > 0 && missionNumber < 10000) ? c : null;
        }));
    }
}
