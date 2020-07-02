package control;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class DeckPane extends StackPane
{
    private final Map<String, Node> idToNode = new HashMap<>();
    
    public void addCard(String id, Node card)
    {
        idToNode.put(id, card);
        getChildren().add(card);
        card.setVisible(false);
    }
    
    public void showCard(String id)
    {
        for (Node n : getChildren())
        {
            n.setVisible(n.equals(idToNode.get(id)));
        }
    }
}
