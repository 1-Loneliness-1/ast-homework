import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class PrinterToConsoleInTurn {

    private static boolean isFirstThreadTimeToRun = true;

    private static final ReentrantLock lock1 = new ReentrantLock(true);
    private static final Condition conditionForFirstThread = lock1.newCondition();
    private static final Condition conditionForSecondThread = lock1.newCondition();

    void startPrintingInTurn() {

        Thread firstThread = new Thread(() -> {
            while (true) {
                try {
                    lock1.lock();
                    while (!isFirstThreadTimeToRun) {
                        conditionForFirstThread.await();
                    }

                    System.out.println("1");
                    isFirstThreadTimeToRun = false;
                    conditionForSecondThread.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock1.unlock();
                }
            }
        });

        Thread secondThread = new Thread(() -> {
            while (true) {
                try {
                    lock1.lock();
                    while (isFirstThreadTimeToRun) {
                        conditionForSecondThread.await();
                    }

                    System.out.println("2");
                    isFirstThreadTimeToRun = true;
                    conditionForFirstThread.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock1.unlock();
                }
            }
        });

        firstThread.start();
        secondThread.start();
    }

}
