package fsm;

import view.Audio;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class FSM<T> extends HashMap<T, OuterState> {
    private OuterState outerState;
    private T initialState;
    private T state;
    private final Map<T, Map<Object, T>> transitionTable = new HashMap<>();
    public static final String EOS = "EndOfOuterState";

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
        T newState = transitionTable.get(state).get(event);
        if (newState != null) {
            state = newState;
            outerState = this.get(state);
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
}
