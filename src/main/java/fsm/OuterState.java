package fsm;

import fsm.action.Action;
import model.sprites.ImageSprite;
import model.sprites.Sprite;
import view.Audio;

import java.awt.*;
import java.util.List;

import static fsm.InnerState.innerState;
import static fsm.action.Move.moveAct;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class OuterState {
    private final InnerState[] states;
    private InnerState currentIS;
    private int index = 0;
    private final int BASE;

    public OuterState clone(Sprite sprite) {
        return new OuterState(this.BASE, ISArrayClone(this.states, sprite));
    }

    public static OuterState outerState(int base, InnerState... innerStates) {
        return new OuterState(base, innerStates);
    }

    public static OuterState outerState(int base, Sprite sprite, List<Image> images, Action... actions) {
        InnerState[] innerStates = images.stream()
                .map(image -> innerState(sprite, image, actions))
                .toArray(InnerState[]::new);
        return new OuterState(base, innerStates);
    }

    public OuterState(int base, InnerState... innerStates) {
        this.states = innerStates;
        this.BASE = base;
        this.currentIS = states[0];
    }

    public boolean isEnd() {
        return index >= states.length * BASE;
    }

    private InnerState next() {
        return states[index++ / BASE];
    }

    public void execute() {
        currentIS = next();
        if (index / BASE != (index + 1) / BASE) {
            currentIS.execute();
        }
    }

    public Image getImage() {
        return currentIS.getImage();
    }

    public Audio getAudio() {
        return currentIS.getAudio();
    }

    public void reset() {
        index = 0;
        currentIS = states[0];
    }

    private InnerState[] ISArrayClone(InnerState[] origin, Sprite sprite) {
        int length = origin.length;
        InnerState[] copy = new InnerState[length];
        for (int i = 0; i < length; i++) {
            InnerState IS = origin[i];
            copy[i] = IS.clone(sprite);
        }
        return copy;
    }

    public static void main(String[] args) {
        ImageSprite sprite = new ImageSprite();
        OuterState outerState = outerState(100,
                innerState(sprite, "adventurer/adventurer-run-00.png", moveAct()),
                innerState(sprite, "adventurer/adventurer-run-01.png", moveAct()));

        ImageSprite newSprite = new ImageSprite();
        OuterState copy = outerState.clone(newSprite);
        System.out.println("c");
    }
}
