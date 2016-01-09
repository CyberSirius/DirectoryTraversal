package bg.rashev.traversal;

import java.io.*;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
public class Producer implements Runnable {
    private File file;
    private Store store;

    public Producer(File file, Store store) {
        this.file = file;
        this.store = store;
    }

    @Override
    public void run() {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(file));
            String line;
            String fileName = file.getName();
            int lineCounter = 1;
            while ((line = fileReader.readLine()) != null) {
                store.add(new Product(line, fileName, lineCounter));
                lineCounter++;
            }
            //System.out.println("producer finished");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
