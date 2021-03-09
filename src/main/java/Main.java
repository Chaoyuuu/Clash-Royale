import gameloop.GameLoop;
import model.CardDeck;
import model.Unitpedia;
import model.arena.Arena;
import model.players.HandCard;
import view.GameGUI;

import static model.CardDeck.CardName.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        Unitpedia unitpedia = new Unitpedia();
        Arena arena = new Arena(unitpedia);
        CardDeck cardDeck = new CardDeck();
        HandCard handCard = new HandCard(cardDeck);
        handCard.addCards(ADVENTURER_A);
        handCard.addCards(ADVENTURER_B);
        handCard.addCards(ADVENTURER_C);
        handCard.addCards(ADVENTURER_D);


        arena.setHandCard(handCard);
        GameGUI gameGUI = new GameGUI(arena);
        gameGUI.launch();
        GameLoop gameLoop = new GameLoop(gameGUI, arena);
        gameLoop.start();
    }
}
