package presentation.tspi;

import java.util.HashMap;
import java.util.Map;

import abstraction.immutable.tspi.TspiGenerator;
import abstraction.immutable.tspi.TspiType;
import control.DeckPane;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

//TODO this class will present input options for everything about generating the TSPI for the packet. The information
// will be used to create some kind of generator that will be used inside a SensorGenerator
// - some packets may only want position, some may want position/velocity, some position/velocity/acceleration
// - we may want to allow selection of a rawch file for population of TSPI
public class TSPIConfigurator extends BorderPane
{
    private final ComboBox<TspiType>         comboType       = new ComboBox<>(FXCollections.observableArrayList(TspiType.values()));
    private final Map<String, TspiInputPane> typeToInputPane = new HashMap<>();
    
    public TSPIConfigurator()
    {
        typeToInputPane.put(TspiType.STATIC.toString(), new StaticTspiInputPane());
        
        DeckPane tspiDeck = new DeckPane();
        for (String id : typeToInputPane.keySet())
        {
            tspiDeck.addCard(id, typeToInputPane.get(id));
        }
        
        comboType.setOnAction((e) -> 
        {
            tspiDeck.showCard(comboType.getSelectionModel().getSelectedItem().toString());
        });
        comboType.getSelectionModel().select(TspiType.STATIC);
        
        setTop(comboType);
        setCenter(tspiDeck);
    }
    
    public TspiGenerator getTspiGenerator()
    {
        return typeToInputPane.get(comboType.getSelectionModel().getSelectedItem().toString()).getTspiGenerator();
    }
}
