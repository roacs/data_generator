package presentation.tspi;

import java.util.HashMap;
import java.util.Map;

import abstraction.immutable.tspi.TspiGenerator;
import abstraction.immutable.tspi.TspiType;
import control.DeckPane;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class TSPIConfigurator extends BorderPane
{
    private final ComboBox<TspiType>         comboType       = new ComboBox<>(FXCollections.observableArrayList(TspiType.values()));
    private final Map<String, TspiInputPane> typeToInputPane = new HashMap<>();
    
    public TSPIConfigurator()
    {
        typeToInputPane.put(TspiType.STATIC.toString(), new StaticTspiInputPane());
        typeToInputPane.put(TspiType.CIRCLE.toString(), new CircleTspiInputPane());
        
        DeckPane tspiDeck = new DeckPane();
        for (String id : typeToInputPane.keySet())
        {
            tspiDeck.addCard(id, typeToInputPane.get(id));
        }
        
        comboType.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> tspiDeck.showCard(n.toString()));
        comboType.getSelectionModel().select(TspiType.STATIC);
        
        setTop(comboType);
        setCenter(tspiDeck);
    }
    
    public TspiGenerator getTspiGenerator()
    {
        return typeToInputPane.get(comboType.getSelectionModel().getSelectedItem().toString()).getTspiGenerator();
    }
}
