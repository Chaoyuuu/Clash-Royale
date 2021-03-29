import gameloop.GameLoop;
import model.CardDeck;
import model.Unitpedia;
import model.arena.Arena;
import model.players.Elixir;
import model.players.HandCard;
import model.players.Player;
import view.GameGUI;

import java.awt.*;

import static model.CardDeck.CardName.*;
import static model.Unitpedia.UnitName.ADVENTURER_U;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Main {
    public static void main(String[] args) {

        CardDeck cardDeck = new CardDeck();
        Elixir elixir = new Elixir();

        Arena arena = new Arena(new Unitpedia());
        arena.setPlayer(setupPlayer(cardDeck, elixir));
        test(arena);
        startGame(arena, elixir);
    }

    public static void test(Arena arena) {
        arena.summonUnitOnArena(ADVENTURER_U, new Point(150, 300));
        arena.summonUnitOnArena(ADVENTURER_U, new Point(200, 300));
    }

    public static void startGame(Arena arena, GameLoop.Listener listener) {
        GameGUI gameGUI = new GameGUI(arena);
        gameGUI.launch();
        GameLoop gameLoop = new GameLoop(gameGUI, arena);
        gameLoop.addListener(listener);
        gameLoop.start();
    }

    public static Player setupPlayer(CardDeck cardDeck, Elixir elixir) {
        HandCard handCard = new HandCard(cardDeck);
        Player player = new Player(elixir, handCard);

        player.chooseCard(ADVENTURER_A);
        player.chooseCard(ADVENTURER_B);
        player.chooseCard(ADVENTURER_C);
        player.chooseCard(ADVENTURER_D);

        return player;
    }
}
