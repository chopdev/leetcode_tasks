/***
 * Remove duplicates of sorted array in place. e.g
 *
 *  [1, 1, 1, 1, 2, 3, 3, 3, 4, 4] -> [1, 2, 3, 4, 2, 3, 3, 3, 4, 4]   Only 4 first occurrences matter
 *
 *
 * */
public class Solution {

    public static void main(String[] args) {
        // int[] arr = {1, 1, 1, 1, 2, 3, 3, 3, 4, 5, 5};

        //int[] arr = {1, 1, 1, 1};

        //int[] arr = {};
        // int[] arr = {1};
        // int[] arr = {1, 2, 3, 4, 5};

        // int[] arr = null;

        int[] arr = {1, 2, 2, 3, 4, 5};

        if (arr == null) return;
        removeDuplicates(arr);


        for (int i = 0; i < arr.length; i ++) {
            System.out.println(arr[i]);
        }
    }

    // 1, 1, 1, 1, 2, 3, 3, 3, 4, 5, 5
    // 1, (update duplicateIndex, duplicateValue)
    // continue because duplicateValue == arr[i]
    // continue because duplicateValue == arr[i]
    // 1, 2, ...   if (duplicateValue != arr[i]) arr[duplicateIndex] = arr[i];
    // duplicateIndex ++
    // duplicateValue = 2;
    // 1, 2, 3 ..
    private static int[] removeDuplicates(int[] arr) {
        if (arr == null) return arr;
        if (arr.length <= 1) return arr;
        int duplicateIndex = -1;
        int duplicateValue = -1;
        for (int i = 1; i < arr.length; i ++) {
            if (duplicateValue == arr[i]) {
                continue;
            }

            if (duplicateIndex == -1 && arr[i] == arr[i - 1]) {
                duplicateIndex = i;
                duplicateValue = arr[i];
            }

            if (duplicateIndex != -1 && duplicateValue != arr[i]) {
                arr[duplicateIndex] = arr[i];
                duplicateIndex ++;
                duplicateValue = arr[i];
            }

        }

        return arr;
    }
}
