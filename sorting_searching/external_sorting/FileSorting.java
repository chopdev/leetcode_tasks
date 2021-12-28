import javafx.util.Pair;

import java.io.*;
import java.util.*;

/*
    Given a large file that cannot fit into RAM memory. Perform sorting of the file producing new file.

   Solution:
   the idea is to split file on chunks. Each chunk can fit into memory, so we sort it and write to some temporary file.
   Then using Stream Reader, we read first symbol from each of the files and put it into Priority Queue.
   PriorityQ helps us to sort leading chars of each file.
   We continue to read chars one by one from temporary files until they are empty.

   Variables:
    N - all symbols in the input file
    M - how much symbols can fit into memory
    K = N / M  - count of chunks

    total time complexity: O(N*logM + N*logK) = O(N*(logM+logK))
    total space complexity: O(K+M)
* */
public class FileSorting {
    private static final int MAX_BUFFER_CHARS = 10;

    public void sortFile(String inputFilePath, String outputFilePath) throws IOException {
        List<File> tempFiles = splitOnFilesAndSort(inputFilePath);
        mergeFiles(tempFiles, outputFilePath);
    }

    // time O(K*M*logM) = O(N*logM)
    // space O(K + M)
    private List<File> splitOnFilesAndSort(String inputFilePath) throws IOException {
        List<File> tempFiles = new ArrayList<>();
        try(Reader reader = new FileReader(inputFilePath)) {
            char[] chunk = new char[MAX_BUFFER_CHARS];
            int charsRead = -1;
            int counter = -1;

            while ((charsRead = reader.read(chunk, 0, chunk.length)) != -1) {
                counter++;
                String prefix = String.format("%03d", counter);
                File tempFile = File.createTempFile(prefix, prefix);
                char[] sordedChunk = Arrays.copyOf(chunk, charsRead);
                Arrays.sort(sordedChunk);
                try (Writer writer = new FileWriter(tempFile)) {
                    writer.write(sordedChunk, 0, sordedChunk.length);
                }
                tempFiles.add(tempFile);
                System.out.println(tempFile.getAbsolutePath());
            }
        }
        return tempFiles;
    }

    // time O(N*logK)
    // space O(K)
    private void mergeFiles(List<File> tempFiles, String outputFilePath) throws IOException {
        try (Writer outputWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
            PriorityQueue<Pair<Character, Reader>> queue = new PriorityQueue<>((x, y) -> Character.compare(x.getKey(), y.getKey()));
            for (File tempFile : tempFiles) {
                Reader tempFileReader = new BufferedReader(new FileReader(tempFile));
                int nextSymbol = tempFileReader.read();
                queue.offer(new Pair<>((char)nextSymbol, tempFileReader));
            }

            while (!queue.isEmpty()) {
                Pair<Character, Reader> pair = queue.poll();
                outputWriter.write(pair.getKey());
                Reader tempFileReader = pair.getValue();

                int nextSymbol = tempFileReader.read();
                if (nextSymbol == -1) {
                    tempFileReader.close();
                    continue;
                }
                queue.offer(new Pair<>((char)nextSymbol, tempFileReader));
            }
        }
    }
}
