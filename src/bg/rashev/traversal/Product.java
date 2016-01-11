package bg.rashev.traversal;

/**
 * Created by CyberSirius on 09-Jan-16.
 */
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

    @Override
    public String toString() {
        return "File name: " + fileName + " line:" + lineNumber + ": " + line;
    }
}
