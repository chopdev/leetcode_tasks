public class Main {

    public static void main(String[] args)  throws  InterruptedException {
        Foo foo = new Foo();
        TestThread th1 = new TestThread(foo, new PrintFirst(), 1);
        TestThread th2 = new TestThread(foo, new PrintSecond(), 2);
        TestThread th3 = new TestThread(foo, new PrintThird(), 3);


        // change start order - output should be the same
        th2.start();
        th1.start();
        th3.start();



        th1.join();
        th2.join();
        th3.join();
    }
}

class TestThread extends Thread {
    private Runnable runnable;
    private Foo foo;
    private int threadId;

    public TestThread(Foo foo, Runnable runnable, int threadId) {
        this.runnable = runnable;
        this.foo = foo;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        try {
            if(threadId == 1)
                foo.first(runnable);
            else if(threadId == 2)
                foo.second(runnable);
            else if(threadId == 3)
                foo.third(runnable);
        }
        catch (InterruptedException ex) {

        }

    }
}
