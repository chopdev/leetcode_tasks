import java.util.List;


/**
 * Given a list of files, we need to sort them in alphabetical order, but taking into account numbers in the name
 *
 * file.gif
 * file30.gif
 * file100.gif
 *
 * Good questions to ask:
 * - What about leading numbers e.g. 0002? - Leading zeros are ignored, so 0002 is like 2
 * - Can we have point numbers in the files? - no, point is only for file extension
 * - How number is compared to char? - Alphabetically
 *
 *
 *  a.gif
 *  a00001b.gif
 *  a2b.gif
 *  e0002.gif
 *  e2.gif
 *  file.gif
 *  file30.gif
 *  file100.gif
 *  file00150.gif
 * */
public class Solution {

    // Complexity
    // O(N*logN*avg(F)) - where N is a count of files, avg(F) is an average file name
    // O(N) space
    public List<String> sort(List<String> files) {
        files.sort((file1, file2) -> {
            for (int i = 0, j = 0; i < file1.length() && j < file2.length(); i++, j++) {
                // firstly compare by number if both names are starting from number
                if (isNumber(file1.charAt(i)) && isNumber(file2.charAt(j))) {
                    StringBuilder numb1 = new StringBuilder();
                    StringBuilder numb2 = new StringBuilder();

                    for (; i < file1.length() && isNumber(file1.charAt(i)); i++) {
                        numb1.append(file1.charAt(i));
                    }
                    i--;

                    for (; j < file2.length() && isNumber(file2.charAt(j)); j++) {
                        numb2.append(file2.charAt(j));
                    }
                    j--;

                    int res = Integer.parseInt(numb1.toString()) - Integer.parseInt(numb2.toString());
                    if (res != 0) return res;
                }

                // compare alphabetically
                int diff = file1.charAt(i) - file2.charAt(j);
                if (diff < 0) return -1;
                if (diff > 0) return 1;
            }

            return file1.length() - file2.length(); // if one file is longer than another, then compare by length
        });

        return files;
    }

    private boolean isNumber(Character ch) {
        return ch >= '0' && ch <= '9';
    }
}
