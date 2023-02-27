package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @Auther: ynhj
 * @Date: 2019-12-04 20:13
 * @Description:
 */
public class ZeroEvenOdd {
    private int n;
    volatile int m = 1;
    Lock lock = new ReentrantLock();
    Condition zeroCondition = lock.newCondition();
    Condition condition = lock.newCondition();


    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (m <= n) {
            try {
                lock.lock();
                zeroCondition.await();
                printNumber.accept(0);
            } finally {
                lock.unlock();
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            if (m % 2 == 0) {
                condition.await();
            }
            zeroCondition.signal();
            printNumber.accept(m);
            m++;
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            if (m % 2 == 1) {
                condition.await();
            }
            zeroCondition.signal();
            printNumber.accept(m);
            m++;
        } finally {
            lock.unlock();
        }
    }
}
