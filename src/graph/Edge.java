package graph;

// Класс-ребро: целевая вершина и вес ребра
public class Edge {
    public final String to;
    public final int weight;

    public Edge(String to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + to + " вес =" + weight + ")";
    }
}
