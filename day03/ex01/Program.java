public class Program {
    public static void main(String[] args) throws InterruptedException {
        int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        Talks discuss = new Talks();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++){
                    discuss.sayHen();

                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++){
                    discuss.sayEgg();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

class Talks{
    private boolean said = false;

    public synchronized void sayHen(){
        while(!said){
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        said = false;
        System.out.println("Hen");
        notify();
    }

    public synchronized void sayEgg(){
        while (said){
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        said = true;
        System.out.println("Egg");
        notify();
    }
}
