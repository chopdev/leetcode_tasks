/**
 https://leetcode.com/problems/print-in-order/discuss/333446/Java-solution-beats-100-in-both-time-and-space
 * */
class Foo2 {

    private volatile boolean onePrinted;
    private volatile boolean twoPrinted;

    public Foo2() {
        onePrinted = false;
        twoPrinted = false;
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        onePrinted = true;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while(!onePrinted) {
            wait();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        twoPrinted = true;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while(!twoPrinted) {
            wait();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}