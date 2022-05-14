import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileOperations operations = new FileOperations();
        Thread fileReader = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    operations.read();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread fileWriter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    operations.write();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        fileReader.start();
        fileWriter.start();
    }
}