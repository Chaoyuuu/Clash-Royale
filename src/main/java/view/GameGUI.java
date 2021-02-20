package view;

import model.players.HandCards;
import model.sprites.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Collections;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class GameGUI extends JFrame implements View {
    private Canvas canvas;
    private Collection<Sprite> sprites = Collections.emptySet();
    private static final int CANVAS_WEIGHT = 1000;
    private static final int CANVAS_HEIGHT = 800;
    private final HandCards handCards;

    public GameGUI(HandCards handCards) {
        this.handCards = handCards;
    }

    public void launch() {
        canvas = new Canvas();
        setContentPane(canvas);
        setSize(CANVAS_WEIGHT, CANVAS_HEIGHT);
        setLocation(200, 200);
        setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getPoint());
                handCards.selectCard(e.getPoint());
            }
        });
    }

    @Override
    public void repaint(Collection<Sprite> sprites) {
        this.sprites = sprites;
        canvas.repaint();
    }

    private class Canvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // background
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, CANVAS_WEIGHT, CANVAS_HEIGHT);

            sprites.forEach(s -> {
                s.render(g);
            });
        }
    }

}
