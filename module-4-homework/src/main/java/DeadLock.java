import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    private static final ReentrantLock lock1 = new ReentrantLock(true);
    private static final ReentrantLock lock2 = new ReentrantLock(true);

    private static class Worker implements Runnable {

        private final String name;
        private final ReentrantLock lock1;
        private final ReentrantLock lock2;

        Worker (String name, ReentrantLock lock1, ReentrantLock lock2) {
            this.name = name;
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            lock1.lock();
            System.out.printf("Захват одного из ресурсов потоком %s. Пробую захватить следующий...\n", name);
            try {
                lock2.lock();
                System.out.printf("Второй ресурс захвачен потоком %s.\n", name);
            } finally {
                lock1.unlock();
                lock2.unlock();
                System.out.printf("Все ресурсы освобождены, поток %s заканчивает работу.\n", name);
            }
        }
    }

    public static void startDeadLockSituation() {
        new Thread (new Worker ("Поток #1", lock1, lock2)).start();
        new Thread (new Worker ("Поток #2", lock2, lock1)).start();
    }

}
