package fsm;

import model.sprites.ImageSprite;
import model.sprites.Sprite;
import model.sprites.State;
import view.Audio;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static fsm.InnerState.innerState;
import static fsm.OuterState.outerState;
import static fsm.action.Move.moveAct;
import static model.sprites.State.*;
import static model.sprites.State.SELETABLE;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class FSM<T> extends HashMap<T, OuterState> implements Cloneable{
    private OuterState outerState;
    private T initialState;
    private T state;
    private final Map<T, Map<Object, T>> transitionTable = new HashMap<>();
    public static final String EOS = "EndOfOuterState";

    public FSM<T> clone(Sprite sprite) {
        FSM<T> copy = new FSM<>();
        copy.putAll(this);
        copy.replaceAll((t, os) -> os.clone(sprite));
        copy.setInitialState(this.initialState);
        return copy;
    }

    public void update() {
        if (outerState == null) {
            outerState = this.get(state);
        }

        if (outerState.isEnd()) {
            outerState.reset();
            trigger(EOS);
        } else {
            outerState.execute();
        }
    }

    public Optional<Audio> getAudio() {
        return Optional.of(outerState.getAudio());
    }

    public Image getImage() {
        return outerState.getImage();
    }

    public void trigger(Object event) {
        try {
            T newState = transitionTable.get(state).get(event);
            if (newState != null) {
                state = newState;
                outerState = this.get(state);
            }
        } catch (NullPointerException ignored) {
        }

    }

    public void setInitialState(T state) {
        this.initialState = state;
        if (this.state == null) {
            this.state = initialState;
        }
    }

    public void addTransition(T state1, Object event, T state2) {
        transitionTable.get(state1).put(event, state2);
    }

    public boolean removeTransition(T state1, Object event, T state2) {
        return transitionTable.get(state1).remove(event, state2);
    }

    public void replaceState(T state, OuterState value) {
        this.replace(state, value);
    }

    public T getState() {
        return state;
    }

    @Override
    public OuterState put(T key, OuterState value) {
        Map<Object, T> OSMap = new HashMap<>();
        transitionTable.put(key, OSMap);
        return super.put(key, value);
    }

    public T getInitialState() {
        return initialState;
    }

    public void reset() {
        this.state = initialState;
        this.outerState = this.get(state);
    }

    //for testing
    private OuterState getOuterState() {
        return outerState;
    }

    private T getCurrentState() {
        return this.state;
    }

    public static void main(String[] args) {
        Sprite sprite = new ImageSprite();
        FSM<State> fsm = new FSM<>();
        fsm.setInitialState(DIE);

        fsm.put(MOVING,
                outerState(10,
                        innerState(sprite, "adventurer/adventurer-run-00.png", moveAct()),
                        innerState(sprite, "adventurer/adventurer-run-01.png", moveAct())));

        fsm.addTransition(MOVING, EOS, SELETABLE);

        OuterState thisOuter = fsm.getOuterState();

        Sprite sprite2 = new ImageSprite();
        FSM<State> copy = fsm.clone(sprite2);

        fsm.setInitialState(MOVING);
        State initial = fsm.getInitialState();
        State thisCurrentState = fsm.getCurrentState();


        copy.setInitialState(MOVING);
        State copyInitial = copy.getInitialState();
        OuterState copyOuter = copy.getOuterState();
        State copyCurrentState = copy.getCurrentState();

        System.out.println("f");

    }
}
