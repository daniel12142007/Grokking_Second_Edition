import graph.Edge;
import graph.Result;
import graph.WeightGraph;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        WeightGraph graph = buildSampleWeightedGraph();

        // Покажем граф
        graph.printGraph();

        String start = "Твинликс(start)";
        String goal = "Золотые Ворота(bridge)";

        // debug=true — увидим релаксации и шаги
        boolean debug = false;
        Result res = dijkstra(graph, start, goal, debug);

        System.out.println("\n=== РЕЗУЛЬТАТ ===");
        if (res.path.isEmpty()) {
            System.out.println("Пути от \"" + start + "\" до \"" + goal + "\" не найдено.");
        } else {
            System.out.println("Кратчайший путь (по сумме весов):");
            System.out.println(String.join(" -> ", res.path));
            System.out.println("Общая стоимость (сумма весов): " + res.totalWeight);
        }
    }

    // debug = true - печатаем шаги алгоритма
    public static Result dijkstra(WeightGraph g, String start, String goal, boolean debug) {
        if (!g.contains(start) || !g.contains(goal))
            return new Result(Collections.emptyList(), Long.MAX_VALUE);

        // dist: минимально найденная дистанция от start до вершины
        Map<String, Long> dist = new HashMap<>();
        // prev: предыдущая вершина на кратчайшем пути (для восстановления пути)
        Map<String, String> prev = new HashMap<>();

        // Инициализация: расстояния = +inf, кроме start = 0
        for (String v : g.vertices()) {
            dist.put(v, Long.MAX_VALUE);
            prev.put(v, null);
        }
        dist.put(start, 0L);

        //Очередь с приоритетом по минимальной dist
        PriorityQueue<NodeDist> pq = new PriorityQueue<>();
        pq.add(new NodeDist(start, 0L));

        if (debug)
            System.out.println("Старт Дейкстры:" + start + " -> " + goal);

        while (!pq.isEmpty()) {
            NodeDist nd = pq.poll();
            String u = nd.node;
            long d = nd.dist;

            // Если этот элемент устарел (нашли лучший путь ранее) - пропускаем
            if (d > dist.get(u)) continue;

            if (debug)
                System.out.println("\n Извлечена вершина " + u + " c dist=" + d);

            // Если дошли до цели - можем остановиться (веса неотрицательные)
            if (u.equals(goal)) {
                if (debug) System.out.println("Достигнута цель: " + goal);
                break;
            }

            // Проходим по всем соседям и пробуем "релаксировать" ребро
            for (Edge e : g.neighbors(u)) {
                String v = e.to;
                long alt = dist.get(u) + e.weight;

                if (debug) System.out.println("  Проверяем соседя " + v + " (вес ребра " + e.weight + "), alt=" + alt +
                        ", старое dist=" + dist.get(v));

                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    pq.add(new NodeDist(v, alt));

                    if (debug)
                        System.out.println("    Обновляем: dist[" + v + "] = " + alt + ", prev[" + v + "] = " + u);

                } else if (debug)
                    System.out.println("    Не улучшается — пропускаем.");
            }
        }

        List<String> path = new ArrayList<>();
        if (dist.get(goal) != Long.MAX_VALUE) {
            String cur = goal;
            while (cur != null) {
                path.add(cur);
                cur = prev.get(cur);
            }
            Collections.reverse(path);
        }

        return new Result(path, dist.getOrDefault(goal, Long.MAX_VALUE));
    }

    private static WeightGraph buildSampleWeightedGraph() {
        WeightGraph g = new WeightGraph();

        String start = "Твинликс(start)";
        String cafe = "Кафе(cafe)";
        String haight = "Район Хейт(haight)";
        String mission = "Район Миссия(mission)";
        String center = "Центр города(center)";
        String north = "Северный берег(north)";
        String bridge = "Золотые Ворота(bridge)";

        // Пример весов (минутах)
        g.addUndirectedEdge(start, cafe, 4);      // Твинликс <-> Кафе: 4
        g.addUndirectedEdge(start, haight, 10);    // Твинликс <-> Хейт: 10

        g.addUndirectedEdge(cafe, mission, 21);    // Кафе <-> Миссия: 21

        g.addUndirectedEdge(mission, bridge, 4);  // Миссия <-> Мост: 4

        g.addUndirectedEdge(haight, center, 5);   // Хейт <-> Центр: 5
        g.addUndirectedEdge(haight, north, 8);   // Хейт <-> Северный берег: 8

        g.addUndirectedEdge(center, mission, 5); // Центр <-> Миссия: 5

        g.addUndirectedEdge(north, mission, 12);    // Северный берег <-> Мост: 12

        return g;
    }

    //Tree BST
    private static Integer find(Tree tree, int find) {
        if (tree == null) return null;
        int index = tree.value;
        if (index == find) return index;
        if (index < find) return find(tree.right, find);
        return find(tree.left, find);
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