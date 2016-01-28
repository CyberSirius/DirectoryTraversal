package fmi.uni.sofia.bg.threads;

import fmi.uni.sofia.bg.store.Product;
import fmi.uni.sofia.bg.store.Store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Producer implements Runnable {
    private final File file;
    private final Store store;

    public Producer(File file, Store store) {
        this.file = file;
        this.store = store;
    }

    @Override
    public void run() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            String fileName = file.getName();
            int lineCounter = 1;
            while ((line = fileReader.readLine()) != null) {
                store.add(new Product(line, fileName, lineCounter));
                lineCounter++;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
