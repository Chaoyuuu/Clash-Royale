package model.sprites;

public enum State {
    MOVING, ATTACK, DIE, IDLE,
    // TODO: rename & separate the Unit's / Card's state
    WAITING, SELETABLE, SELECTED, REMOVE, EMPTY,

    STATIC
}
