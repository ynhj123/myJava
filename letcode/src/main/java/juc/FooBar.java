package juc;

/**
 * @Auther: ynhj
 * @Date: 2019-12-03 21:32
 * @Description:
 */
public class FooBar {
    private int n;
    volatile int m = 0;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            synchronized (this) {
                if (m % 2 != 0) {
                    wait();
                }
                printFoo.run();
                m++;
                notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                if (m % 2 != 1) {
                    wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                m++;
                notify();
            }
        }
    }

}
