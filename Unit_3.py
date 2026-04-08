# --DIJKSTRA ALGORITHM --

import heapq

def dijkstra_shortest_path(graph, source):
    # Initialize distances with infinity
    distances = {}
    for vertex in graph:
        distances[vertex] = float('inf')

    distances[source] = 0

    # Priority queue (min-heap)
    min_heap = [(0, source)]

    while min_heap:
        current_distance, current_node = heapq.heappop(min_heap)

        # Skip if we already found a shorter path
        if current_distance > distances[current_node]:
            continue

        for neighbor, weight in graph[current_node]:
            new_distance = current_distance + weight

            if new_distance < distances[neighbor]:
                distances[neighbor] = new_distance
                heapq.heappush(min_heap, (new_distance, neighbor))

    return distances


# Example Graph for Dijkstra
graph_data = {
    'A': [('B', 2), ('C', 5)],
    'B': [('C', 1), ('D', 4)],
    'C': [('D', 2)],
    'D': []
}

print("Dijkstra Result:", dijkstra_shortest_path(graph_data, 'A'))


# ------------------ BELLMAN-FORD ALGORITHM ------------------

def bellman_ford_algorithm(edges, vertices, source):
    # Initialize distances
    distance = [float('inf')] * vertices
    distance[source] = 0

    # Relax edges (V-1 times)
    for i in range(vertices - 1):
        for u, v, w in edges:
            if distance[u] != float('inf') and distance[u] + w < distance[v]:
                distance[v] = distance[u] + w

    # Check for negative weight cycle
    for u, v, w in edges:
        if distance[u] != float('inf') and distance[u] + w < distance[v]:
            print("Graph contains a negative weight cycle!")
            return None

    return distance


# Example Graph for Bellman-Ford
edge_list = [
    (0, 1, 4),
    (0, 2, 6),
    (1, 2, -2),
    (2, 3, 3)
]

print("Bellman-Ford Result:", bellman_ford_algorithm(edge_list, 4, 0))
