public class Solution {
    public static void main(String[] args) {

    }

    //4.1
    private static int sum(int[] arr, int index) {
        if (arr.length == index)
            return 0;

        return arr[index] + sum(arr, index + 1);
    }

    //4.2
    private static int size(int[] arr, int index) {
        if (arr.length == index)
            return 0;

        return 1 + size(arr, index + 1);
    }

    //4.3
    private static int max(int[] arr, int max, int index) {
        if (arr.length == index)
            return max;

        if (arr[index] > max)
            max = arr[index];

        return Math.max(max(arr, max, index + 1), arr[index]);
    }

    //4.4
    private static int binarySearch(int[] array, int find, int l, int r) {
        int index = (l + r) / 2;
        int mid = array[index];

        if (find == mid) return mid;
        if (mid > find) return binarySearch(array, find, l, index - 1);

        return binarySearch(array, find, index + 1, r);
    }
}