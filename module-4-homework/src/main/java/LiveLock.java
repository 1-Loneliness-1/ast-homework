import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private static final ReentrantLock lock1 = new ReentrantLock(true);
    private static final ReentrantLock lock2 = new ReentrantLock(true);

    private static class Worker implements Runnable {

        private final String threadName;
        private final ReentrantLock lock1;
        private final ReentrantLock lock2;

        Worker(String threadName, ReentrantLock lock1, ReentrantLock lock2) {
            this.threadName = threadName;
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            int attempts = 0;

            while (attempts < 100) {
                attempts++;

                if (lock1.tryLock()) {
                    System.out.printf("Поток %s забрал первый ресурс. Пробуем забрать второй...\n", threadName);

                    if (lock2.tryLock()) {
                        System.out.printf("Поток %s забрал второй ресурс. Выполняем работу...\n", threadName);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                        System.out.printf("Поток %s завершил работу и освобождает ресурсы.\n", threadName);
                        lock1.unlock();
                        lock2.unlock();
                    } else {
                        System.out.printf("У потока %s не получилось заполучить второй ресурс, поэтому отпускаем все захваченные ресурсы\n", threadName);
                        lock1.unlock();
                    }
                } else {
                    System.out.printf("Поток %s не смог получить доступ к первому ресурсу!\n", threadName);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        }

    }

    public static void startLiveLockSituation() {
        new Thread(new Worker("Поток №1", lock1, lock2)).start();
        new Thread(new Worker("Поток №2", lock2, lock1)).start();
    }

}
