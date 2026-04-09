import java.util.*;

// Class for edges in Dijkstra
class Pair {
    String node;
    int weight;

    Pair(String node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

// Class for Bellman-Ford edges
class Edge {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class GraphAlgorithms {

    // ------------------ DIJKSTRA ------------------
    public static Map<String, Integer> dijkstra(Map<String, List<Pair>> graph, String source) {

        Map<String, Integer> distances = new HashMap<>();

        // Initialize distances
        for (String vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        // Min Heap (priority queue)
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            String currentNode = current.node;
            int currentDistance = current.weight;

            // Skip outdated entries
            if (currentDistance > distances.get(currentNode)) continue;

            for (Pair neighbor : graph.get(currentNode)) {
                int newDistance = currentDistance + neighbor.weight;

                if (newDistance < distances.get(neighbor.node)) {
                    distances.put(neighbor.node, newDistance);
                    pq.add(new Pair(neighbor.node, newDistance));
                }
            }
        }

        return distances;
    }

    // ------------------ BELLMAN-FORD ------------------
    public static int[] bellmanFord(List<Edge> edges, int vertices, int source) {

        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Relax edges V-1 times
        for (int i = 0; i < vertices - 1; i++) {
            for (Edge e : edges) {
                if (distance[e.u] != Integer.MAX_VALUE &&
                        distance[e.u] + e.w < distance[e.v]) {
                    distance[e.v] = distance[e.u] + e.w;
                }
            }
        }

        // Check negative cycle
        for (Edge e : edges) {
            if (distance[e.u] != Integer.MAX_VALUE &&
                    distance[e.u] + e.w < distance[e.v]) {
                System.out.println("Graph contains a negative weight cycle!");
                return null;
            }
        }

        return distance;
    }

    // ------------------ MAIN ------------------
    public static void main(String[] args) {

        // Dijkstra Graph
        Map<String, List<Pair>> graph = new HashMap<>();

        graph.put("A", Arrays.asList(new Pair("B", 2), new Pair("C", 5)));
        graph.put("B", Arrays.asList(new Pair("C", 1), new Pair("D", 4)));
        graph.put("C", Arrays.asList(new Pair("D", 2)));
        graph.put("D", new ArrayList<>());

        System.out.println("Dijkstra Result: " + dijkstra(graph, "A"));

        // Bellman-Ford Graph
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(1, 2, -2));
        edges.add(new Edge(2, 3, 3));

        int[] result = bellmanFord(edges, 4, 0);

        if (result != null) {
            System.out.print("Bellman-Ford Result: ");
            for (int d : result) {
                System.out.print(d + " ");
            }
        }
    }
}
