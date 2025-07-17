import java.util.LinkedList;
import java.util.Queue;

public class Node {
    String name;
    Queue<Node> neighbors;

    public Node(String name) {
        this.name = name;
        this.neighbors = new LinkedList<>();
    }

    void connect(Node other) {
        neighbors.add(other);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
}