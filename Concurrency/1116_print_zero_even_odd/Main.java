import java.util.function.IntConsumer;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ZeroEvenOdd obj = new ZeroEvenOdd(5);
        IntConsumer consumer = (numb) -> System.out.print(numb);

	    Thread th1 = new Thread() {
            @Override
            public synchronized void run() {
                try {
                    obj.odd(consumer);
                }
                catch (Exception ex){}
            }
        };

        Thread th2 = new Thread() {
            @Override
            public synchronized void run() {
                try {
                    obj.zero(consumer);
                }
                catch (Exception ex){}
            }
        };

        Thread th3 = new Thread() {
            @Override
            public synchronized void run() {
                try {
                    obj.even(consumer);
                }
                catch (Exception ex){}
            }
        };

        th1.start();
        th2.start();
        th3.start();


        th1.join();
        th2.join();
        th3.join();

    }
}
