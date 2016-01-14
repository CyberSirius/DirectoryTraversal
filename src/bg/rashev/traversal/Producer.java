package bg.rashev.traversal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Producer implements Runnable {
    private final File file;
    private final Store store;

    public Producer(File file, Store store) {
        this.file = file;
        this.store = store;
    }

    @Override
    public void run() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {//todo test with BufferedInputStream
            String line;
            String fileName = file.getName();
            int lineCounter = 1;
            while ((line = fileReader.readLine()) != null) {
                store.add(new Product(line, fileName, lineCounter));
                //Thread.sleep(1);// TODO: 14-Jan-16 i have no idea if I need this, but it's 10 times faster without it :D
                lineCounter++;// TODO: 14-Jan-16 try reading a couple of lines at the same time with threads
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
