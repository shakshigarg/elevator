package utils;

public class Utils {
    public static void threadSleep(long mili){
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
