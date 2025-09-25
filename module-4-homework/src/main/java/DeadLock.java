import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    private static final ReentrantLock lock1 = new ReentrantLock(true);
    private static final ReentrantLock lock2 = new ReentrantLock(true);

    public static void startDeadLockSituation() {
        Thread thread1 = new Thread(() -> {
            lock1.lock();
            System.out.println("Монитор 1 захвачен. Пробую захватить второй...");
            try {
                lock2.lock();
                System.out.println("Монитор 2 захвачен.");
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

            lock2.lock();
        });
        Thread thread2 = new Thread(() -> {
            lock2.lock();
            System.out.println("Монитор 2 захвачен. Пробую захватить первый...");
            try {
                lock1.lock();
                System.out.println("Монитор 1 захвачен.");
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        });
        thread1.start();
        thread2.start();
    }

}
