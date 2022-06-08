public class Program {
    public static void main(String[] args) throws InterruptedException {
        int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++)
                    System.out.println("egg");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++)
                    System.out.println("Hen");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Human");

        return;
    }
}
