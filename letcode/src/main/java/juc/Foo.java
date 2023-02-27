package juc;

/**
 * @Auther: ynhj
 * @Date: 2019-12-03 21:42
 * @Description:
 */
class Foo {
    volatile int i = 0;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        while (i % 3 != 0) {
            Thread.sleep(5000);
        }
        i++;
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (i % 3 != 1) {
            Thread.sleep(5000);
        }
        i++;
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();


    }

    public void third(Runnable printThird) throws InterruptedException {
        while (i % 3 != 2) {
            Thread.sleep(5000);
        }
        i++;
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

    }



    public static void main(String[] args) {

    }
}
