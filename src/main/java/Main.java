import gameloop.GameLoop;
import model.CardDeck;
import model.Unitpedia;
import model.arena.Arena;
import model.players.HandCard;
import view.GameGUI;

import java.awt.*;

import static model.CardDeck.CardName.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        Arena arena = new Arena(new Unitpedia());
        arena.setHandCard(setupHandCards());
        test(arena);
        run(arena);
    }

    public static void test(Arena arena) {
            arena.summonUnitOnArena(Unitpedia.UnitName.ADVENTURER_IDLE, new Point(300, 300));
    }

    public static void run(Arena arena) {
        GameGUI gameGUI = new GameGUI(arena);
        gameGUI.launch();
        GameLoop gameLoop = new GameLoop(gameGUI, arena);
        gameLoop.start();
    }

    public static HandCard setupHandCards() {
        CardDeck cardDeck = new CardDeck();
        HandCard handCard = new HandCard(cardDeck);

        handCard.addCards(ADVENTURER_A);
        handCard.addCards(ADVENTURER_B);
        handCard.addCards(ADVENTURER_C);
        handCard.addCards(ADVENTURER_D);

        return handCard;
    }
}
