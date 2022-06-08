public class Program {
    public static void main(String[] args) throws InterruptedException {
        int arraySize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        int threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));
        if (arraySize < threadsCount) {
            System.out.println("invalid data");
            System.exit(-1);
        }
        int [] arr = new int[arraySize];
        int firstSum = 0;
        for (int i = 0; i < arraySize; i++){
            arr[i] = i;
            firstSum += i;
        }
        System.out.println(firstSum);
        SumOfAll sum = new SumOfAll();
        Thread [] threads = new Thread[threadsCount];
        int start = 0;
        int sep = arraySize / threadsCount;
        int localcounter = 0;
        int sep2 = sep;
        for (int i = 0; i < threadsCount; i++){
            int finalStart = start;
            int finalSep = sep;
            int finalcounter = ++localcounter;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int localsum = 0;
                    for (int i = finalStart; i < finalSep; i++)
                        localsum += arr[i];
                    System.out.println("Сумма в " + finalcounter + " потоке равна " + localsum + "--------" + "Участки сложения от " + finalStart + " до " + finalSep);
                    synchronized (sum) {
                        sum.increase(localsum);
                    }
                }
            });
            threads[i].start();
            start = sep;
            if (sep + sep2 >= arraySize)
                sep = arraySize;
            else if (finalcounter == threadsCount - 1 && sep + sep2 < arraySize)
                sep = arraySize;
            else
                sep += sep2;
        }
        for (int i = 0; i < threadsCount; i++)
            threads[i].join();
        System.out.println(sum);
    }
}

class SumOfAll{
    private Integer sum;
    public SumOfAll(){
        sum = 0;
    }

    public void increase(Integer newSum){
        sum += newSum;
    }

    @Override
    public String toString() {
        return "SumOfAll{" +
                "sum=" + sum +
                '}';
    }
}
