import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program{

    public static void downloadFile(String fileUrl, int countOfThread) throws InterruptedException {
        try {
            System.out.println("file " + fileUrl + " added to download queue by thread " + countOfThread);
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            String [] fileNames = fileUrl.split("/");
            String fileName = fileNames[fileNames.length - 1];
            File f1 = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(f1);
            byte[] b = new byte[1024];
            int count = 0;
            while ((count = bis.read(b)) != -1){
                fileOutputStream.write(b, 0, count);
            }
            fileOutputStream.close();
            System.out.println("file " + fileUrl + "was download by thread " + countOfThread);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        int threadsCount = 0;
        try {
            threadsCount = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        if (threadsCount < 1){
            System.err.println("threads count minimum is 1");
            System.exit(-1);
        }
        ExecutorService pool = Executors.newFixedThreadPool(threadsCount);
        String path = System.getenv("PWD") + "/urls.txt";
        List<String> urls = Files.readAllLines(new File(path).toPath(), Charset.defaultCharset());
        int count = 0;
        for (String url : urls) {
            pool.submit(new Downloads(url, ++count));
        }
        pool.shutdown();
    }

    public static class Downloads implements Runnable{
        private final String fileUrl;
        private final int countOfThread;

        public Downloads(String fileUrl, int countOfThread) {
            this.fileUrl = fileUrl;
            this.countOfThread = countOfThread;
        }

        @Override
        public void run() {
            try {
                downloadFile(fileUrl, countOfThread);
            }  catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}