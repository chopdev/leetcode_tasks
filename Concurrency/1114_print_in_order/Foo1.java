/**
 Not mine short solution
 https://leetcode.com/problems/print-in-order/discuss/332890/Java-Basic-semaphore-solution-8ms-36MB

 Synchronized allows only one thread of execution to access the resource at the same time.
 Semaphore allows up to n (you get to choose n) threads of execution to access the resource at the same time.

 What does the 0 mean when initialize a semaphore？
 hat input is the amount of times acquire() can be called before blocking. i.e. Your semaphore initially has 0 permits,
 in which case permits must be released before they may be acquired.

 In short, a semaphore maintains a set of permits (tickets), each acquire() will take a permit (ticket) from
 semaphore, each release() will return back the permit (ticket) back to the semaphore. If permits (tickets) are not
 available, acquire() will block until one is available.
* */
import java.util.concurrent.*;
class Foo1 {
    Semaphore run2, run3;

    public Foo1() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        run2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        run2.acquire();
        printSecond.run();
        run3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        run3.acquire();
        printThird.run();
    }
}