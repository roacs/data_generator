package control;

import java.util.regex.Pattern;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class DoubleTextField extends TextField
{
    private static final Pattern VALID_EDITING_STATE = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

    public DoubleTextField()
    {
        setTextFormatter(new TextFormatter<>(c -> VALID_EDITING_STATE.matcher(c.getControlNewText()).matches() ? c : null));
    }

    public double getValue()
    {
        return Double.parseDouble(getText());
    }
}
