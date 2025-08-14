package graph;

import java.util.*;

// Взвешенный граф в виде списка смежности: для каждой вершины список исходящих ребер
public class WeightGraph {
    private final Map<String, List<Edge>>adj = new HashMap<>();

    // Добавляем вершину, если ещё нет
    void addVertex(String v){
        adj.putIfAbsent(v, new ArrayList<>());
    }

    // Добавляем неориентированное ребро: a <-> b с весом w
    public void addUndirectedEdge(String a, String b, int w){
        addVertex(a);
        addVertex(b);
        adj.get(a).add(new Edge(b,w));
        adj.get(b).add(new Edge(a,w));
    }

    public List<Edge> neighbors(String v){
        return adj.getOrDefault(v, Collections.emptyList());
    }

    public boolean contains(String v){
        return adj.containsKey(v);
    }

    public void printGraph(){
        System.out.println("Граф (список смежности с весами):");
        for (Map.Entry<String, List<Edge>>e: adj.entrySet())
            System.out.println(" " + e.getKey() + " -> "+ e.getValue());
    }

    public Set<String>vertices(){
        return adj.keySet();
    }
}