package utils;

/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class DelayUtils {
    public static void delay(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}