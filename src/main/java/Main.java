import gameloop.GameLoop;
import model.arena.Arena;
import model.players.HandCards;
import model.sprites.unit.Adventurer;
import view.GameGUI;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        Arena arena = new Arena();
        Adventurer adventurer = new Adventurer();
        arena.addSprite(adventurer);
        GameGUI gameGUI = new GameGUI(new HandCards());
        gameGUI.launch();
        GameLoop gameLoop = new GameLoop(gameGUI, arena);
        gameLoop.start();
    }

    // TODO: Card Constructor ??
//    public static CardDeck setupCardDeck(Arena arena) {
//        CardDeck cardDeck = new CardDeck();
//        // players cards on the card decks
//        Card card = createCard(800, 600, "adventurer/adventurer-attack2-00.png", TestSprite.class);
//        cardDeck.addCard(card);
//        arena.addSprite(card);
//        return cardDeck;
//    }
}
