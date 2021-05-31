import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        // Creating executor service with thread factory, which creates daemon threads
        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        //Starting execute service, start timer
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //  Name downloading simulation
        Future<String> futureName = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "John";
            }
        });

        //  Age downloading simulation
        Future<Integer> futureAge = executorService.submit(new Callable<Integer> () {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(4000);
                return 21;
            }
        });

        //
        try {
            String name = futureName.get();
            int age = futureAge.get();
            System.out.println("\nName: " + name + "\nAge: " + age);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
