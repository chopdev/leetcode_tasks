import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {
        URL basePath = Main.class.getClassLoader().getResource("");

        String inputFilePath = basePath.getPath() + "test1.txt";
        String outputFilePath = basePath.getPath() + "sorted_test1.txt";

        FileSorting fileSorting = new FileSorting();
        fileSorting.sortFile(inputFilePath, outputFilePath);
    }
}
