import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.ThreadPoolExecutor;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO
        this.parent = new int[G.V()];
        this.s = s;
        this.dist = new double[G.V()];
        for(int v=0; v < G.V();v++){
            dist[v] = Double.POSITIVE_INFINITY;
        }
        dist[s] = 0;
        TopologicalWD topologicalWD = new TopologicalWD(G);
        topologicalWD.dfs(s);
        Stack<Integer> newList = topologicalWD.order();
        Collections.reverse(newList);
        for(int v : newList)
            for(DirectedEdge e: G.incident(v)){
                relax(e);
            }
    }

    public void relax(DirectedEdge e) {
        // TODO
        int v = e.from();
        int w = e.to();
        if(dist[w]>dist[v] +e.weight()){
            dist[w] = dist[v] + e.weight();
            parent[w] = v;
        }
    }



    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

