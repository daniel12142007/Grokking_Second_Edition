import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {

    }

    //6.1 and 6.2
    private static Integer find(Queue<Node> nodes, String search) {
        Queue<Node> queue = new LinkedList<>(nodes);
        Map<Node, Integer> distance = new HashMap<>();
        nodes.forEach(node -> distance.put(node, 0));
        while (!queue.isEmpty()) {
            if (queue.peek().name.equals(search)) {
                return distance.get(queue.poll()) + 1;
            }
            if (!queue.peek().neighbors.isEmpty()) {
                Node current = queue.poll();
                queue.addAll(current.neighbors);
                current.neighbors.forEach(a -> distance.put(a, distance.get(current) + 1));
            } else {
                queue.poll();
            }
        }
        return null;
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