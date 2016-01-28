package fmi.uni.sofia.bg.store;


public class Product {


    private final String line;
    private final String fileName;
    private final int lineNumber;

    public Product(String line, String fileName, int lineNumber) {
        this.line = line;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public boolean containsString(String string) {
        return getLine().contains(string);// TODO: 14-Jan-16 further tests between contains and indexOf!=-1
    }

    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "File name: " + fileName + " line:" + lineNumber + ": " + line;
    }
}