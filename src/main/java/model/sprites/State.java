package model.sprites;

public enum State {
    MOVING, ATTACK, DIE,
    // TODO: rename & separate the Unit's / Card's state
    WAITING, GAME, SELECTED, REMOVE, EMPTY,

    STATIC
}
