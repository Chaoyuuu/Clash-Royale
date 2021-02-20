package fsm;

import view.Audio;

import java.awt.*;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class OuterState {
    private final InnerState[] states;
    private InnerState currentIS;
    private int index = 0;
    private final int BASE;

    public static OuterState outerState(int base, InnerState... innerStates) {
        return new OuterState(base, innerStates);
    }

    public OuterState(int base, InnerState... innerStates) {
        this.states = innerStates;
        this.BASE = base;
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
    }

}
