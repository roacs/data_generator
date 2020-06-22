package presentation.tspi;

import abstraction.immutable.tspi.TspiGenerator;
import javafx.scene.layout.BorderPane;

public abstract class TspiInputPane extends BorderPane
{
    public abstract TspiGenerator getTspiGenerator();
}
