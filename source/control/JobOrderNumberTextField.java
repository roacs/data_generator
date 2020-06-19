package control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class JobOrderNumberTextField extends TextField
{
    private final Pattern pattern = Pattern.compile("[a-zA-Z0-9]{0,8}");
    
    public JobOrderNumberTextField()
    {
        setPromptText("Job Order Number");
        setTextFormatter(new TextFormatter<>(c ->
        {
            if (c.getControlNewText().isEmpty())
            {
                return c;
            }
            
            // TODO change to Job Order Number
            Matcher m = pattern.matcher(c.getControlNewText());
            return m.matches() ? c : null;
        }));
    }
}
