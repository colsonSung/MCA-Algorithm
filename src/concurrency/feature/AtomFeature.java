package concurrency.feature;

public class AtomFeature {
    private static volatile int count;

    public static void increment(){
        synchronized (AtomFeature.class){
            count++;
        }
    }

    public static void main(String[] args) {
        increment();
    }
}
