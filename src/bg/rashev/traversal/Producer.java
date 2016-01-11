package bg.rashev.traversal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
class Producer implements Runnable {
    private final File file;
    private final Store store;

    public Producer(File file, Store store) {
        this.file = file;
        this.store = store;
    }

    @Override
    public void run() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {//todo test with BufferedOutputStream
            String line;
            String fileName = file.getName();
            int lineCounter = 1;
            while ((line = fileReader.readLine()) != null) {
                store.add(new Product(line, fileName, lineCounter));
                Thread.sleep(1);//// TODO: 11-Jan-16 make sure you know why you need this
                lineCounter++;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
