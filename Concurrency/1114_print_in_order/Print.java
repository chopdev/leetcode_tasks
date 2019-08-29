class PrintFirst implements Runnable {
    @Override
    public void run() {
        System.out.println("first");
    }
}

class PrintSecond implements Runnable {
    @Override
    public void run() {
        System.out.println("second");
    }
}

class PrintThird implements Runnable {
    @Override
    public void run() {
        System.out.println("third");
    }
}
