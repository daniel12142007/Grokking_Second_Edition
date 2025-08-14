public class NodeDist implements Comparable<NodeDist> {
    final String node;
    final long dist;

    public NodeDist(String node, long dist) {
        this.node = node;
        this.dist = dist;
    }

    @Override
    public int compareTo(NodeDist o) {
        return Long.compare(this.dist, o.dist);
    }

    @Override
    public String toString() {
        return node + "(" + dist + ")";
    }
}
