package fsm;

import fsm.action.Action;
import model.sprites.Sprite;
import utils.ImageUtils;
import view.Audio;

import java.awt.*;

import static fsm.action.DefaultAction.defaultAct;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class InnerState {
    private final Image image;
    private final Audio audio;
    private final Action[] actions;
    private final Sprite sprite;

    public InnerState clone(Sprite sprite) {
        return new InnerState(sprite, this.image, this.audio, this.actions);
    }

    public static InnerState innerState(Sprite sprite, String path, Audio audio, Action... actions) {
        Image image = ImageUtils.getImage(path);
        return new InnerState(sprite, image, audio, actions);
    }

    public static InnerState innerState(Sprite sprite, String path, Action... actions) {
        Image image = ImageUtils.getImage(path);
        return new InnerState(sprite, image, Audio.MULT, actions);
    }

    public static InnerState innerState(Sprite sprite, String path) {
        Image image = ImageUtils.getImage(path);
        return new InnerState(sprite, image, Audio.MULT, new Action[]{defaultAct()});
    }

    public static InnerState innerState(Sprite sprite, Image image, Action... actions) {
        return new InnerState(sprite, image, Audio.MULT, actions);
    }

    public static InnerState innerState(Sprite sprite, Image image) {
        return new InnerState(sprite, image, Audio.MULT, new Action[]{defaultAct()});
    }

    private InnerState(Sprite sprite, Image image, Audio audio, Action[] actions) {
        this.sprite = sprite;
        this.image = image;
        this.audio = audio;
        this.actions = actions;
    }

    public Image getImage() {
        return image;
    }

    public Audio getAudio() {
        return audio;
    }

    public void execute() {
        for (Action action : actions) {
            action.execute(sprite);
        }
    }
}
