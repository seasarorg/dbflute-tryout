package javatry.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class SemaphoreTest extends UnitTryTestCase {

    public void test_acquire_release() throws Exception {
        final Semaphore sem = new Semaphore(2, false);
        log(sem);

        final Runnable tranOne = new Runnable() {
            public void run() {
                try {
                    log("Start One1");
                    Thread.sleep(5000);
                    log("1 before sem.acquire(): " + sem);
                    Thread.sleep(200);
                    sem.acquire();
                    Thread.sleep(200);
                    log("1 after  sem.acquire(): " + sem);
                    Thread.sleep(5000);
                    log("End One2");
                    sem.release();
                } catch (InterruptedException continued) {
                    log(continued.getMessage());
                }
            }
        };

        final Runnable tranTwo = new Runnable() {
            public void run() {
                try {
                    log("Start Two2");
                    Thread.sleep(5000);
                    log("2 before sem.acquire(): " + sem);
                    Thread.sleep(200);
                    sem.acquire();
                    Thread.sleep(200);
                    log("2 after  sem.acquire(): " + sem);
                    Thread.sleep(5000);
                    log("End Two2");
                    sem.release();
                } catch (InterruptedException continued) {
                    log(continued.getMessage());
                }
            }
        };
        final Runnable tranThree = new Runnable() {
            public void run() {
                try {
                    log("Start Three3");
                    Thread.sleep(5000);
                    log("3 before sem.acquire(): " + sem);
                    Thread.sleep(200);
                    sem.acquire();
                    Thread.sleep(200);
                    log("3 after  sem.acquire(): " + sem);
                    Thread.sleep(5000);
                    log("End Three3");
                    sem.release();
                } catch (InterruptedException continued) {
                    log(continued.getMessage());
                }
            }
        };

        final Thread threadOne = new Thread(tranOne);
        sleep(500);
        final Thread threadTwo = new Thread(tranTwo);
        sleep(500);
        final Thread threadThree = new Thread(tranThree);

        ExecutorService es = Executors.newCachedThreadPool();
        es.submit(threadOne);
        es.submit(threadTwo);
        es.submit(threadThree);

        sleep(30000);
        log(sem);
    }

    protected void sleep(int count) {
        try {
            Thread.sleep(count);
        } catch (InterruptedException continued) {
            log(continued.getMessage());
        }
    }
}
