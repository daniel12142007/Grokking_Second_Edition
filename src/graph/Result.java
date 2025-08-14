package graph;

import java.util.List;

public class Result {
    public final List<String>path;
    public final long totalWeight;

    public Result(List<String> path, long totalWeight) {
        this.path = path;
        this.totalWeight = totalWeight;
    }
}
