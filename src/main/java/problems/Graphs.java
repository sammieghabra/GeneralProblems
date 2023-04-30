package problems;

import java.util.*;

public class Graphs {

    Set<Integer> visited = new HashSet<>();
    Set<Integer> visited_bfs = new HashSet<>();
    public boolean pathExistsDFS(Map<Integer, List<Integer>> graph, int start, int end) {
        int temp = start;

        List<Integer> connections = graph.get(temp);

        if (connections == null || connections.isEmpty()) {
            return false;
        }

        visited.add(temp);

        for (Integer i: connections) {
            if (i.equals(end)) {
                return true;
            } else {
                if (visited.contains(i)) {

                } else {
                    boolean result = pathExistsDFS(graph, i, end);

                    if (result == true) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean pathExistsBFS(Map<Integer, List<Integer>> graph, int start, int end) {

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        int temp = start;

        queue.push(temp);

        while (!queue.isEmpty()) {
            temp = queue.poll();

            if (temp == end) {
                return true;
            }

            List<Integer> connections = graph.get(temp);

            visited_bfs.add(temp);

            if (connections != null) {
                for (int i: connections) {
                    if (!visited_bfs.contains(i)) {
                        queue.add(i);
                    }
                }
            }
        }

        return temp == end;
    }

    public Integer shortestPathDijkstra(Map<Integer, Map<Integer, Integer>> graph, int source, int target) {

        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visitedSet = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Well, we need a shortest distance map. Why? we need to keep track of all the distances from the source
        // to each node. Why? Well I guess we could then just look up that key with the taget at the end? It will
        // keep track of the best paths so far.
        distances.put(source, 0);

        graph.keySet().forEach(node -> {
            if (node != source) {
                distances.put(node, Integer.MAX_VALUE);
            }
        });

        pq.offer(source);

        while (!pq.isEmpty()) {
            int currentNode = pq.poll();

            if (currentNode == target) {
                break;
            }

            if (visitedSet.contains(currentNode)) {
                continue;
            }

            visitedSet.add(currentNode);
            Map<Integer, Integer> currentDistance = graph.get(currentNode);

            for (Map.Entry<Integer, Integer> entry : currentDistance.entrySet()) {
                int nodeId = entry.getKey();
                int distanceFromNode = entry.getValue();
                int currentShortestDistance = distances.get(nodeId);

                if (!visitedSet.contains(nodeId)
                        && currentShortestDistance > distances.get(currentNode) + distanceFromNode) {
                    distances.put(nodeId, distances.get(currentNode) + distanceFromNode);
                    pq.offer(nodeId);
                }
            }
        }

        return distances.get(target);
    }

    public int shortestPathLength(Map<Integer, List<Integer>> graph, int source, int target) {

        Map<Integer, Integer> distances = new HashMap<>();

        class Levels {
            public int nodeId;
            public int distance;

            public Levels(int nodeId, int distance) {
                this.nodeId = nodeId;
                this.distance = distance;
            }
        }

        graph.forEach((key, value) -> {
            distances.put(key, Integer.MAX_VALUE);
        });

        Set<Integer> vis = new HashSet<>();

        ArrayDeque<Levels> arrayDeque = new ArrayDeque();
        arrayDeque.offer(new Levels(source, 0));

        while (!arrayDeque.isEmpty()) {
            Levels temp = arrayDeque.poll();

            List<Integer> connections = graph.get(temp.nodeId);

            for (Integer nodeId: connections) {

                if (!vis.contains(nodeId)) {
                    Integer currentDistance = temp.distance + 1;
                    Integer shortestSeen = distances.get(nodeId);

                    if (shortestSeen > currentDistance) {
                        distances.put(nodeId, currentDistance);
                    }
                    arrayDeque.push(new Levels(nodeId, temp.distance + 1));
                }
            }

            vis.add(temp.nodeId);
        }

        return distances.get(target) == Integer.MAX_VALUE ? -1 : distances.get(target);
    }

    /**
     * Problem: Detect cycle in an undirected graph
     *
     * Given an undirected graph represented as an adjacency list, determine if the graph contains a cycle.
     * @param graph
     * @return
     */
    public boolean hasCycleUndirectedGraph(Map<Integer, List<Integer>> graph) {

        class StackThing {
            public Integer nodeId;
            public Integer parent;

            public StackThing(int nodeId, int parent) {
                this.nodeId = nodeId;
                this.parent = parent;
            }
        }

        Set<Integer> visited_un = new HashSet<>();
        Stack<StackThing> stack = new Stack<>();

        Set<Integer> keys = new HashSet<>(graph.keySet());

        while (!keys.isEmpty()) {

            Integer key = keys.iterator().next();

            stack.push(new StackThing(key, -1));

            while (!stack.isEmpty()) {

                StackThing stackThing = stack.pop();
                Integer keyToInspect = stackThing.nodeId;

                keys.remove(keyToInspect);

                if (visited_un.contains(keyToInspect)) {
                    return true;
                }

                List<Integer> connections = graph.getOrDefault(keyToInspect, List.of());

                for (Integer connection: connections) {
                    if (stackThing.parent != connection) {
                        stack.push(new StackThing(connection, keyToInspect));
                    }
                }

                visited_un.add(keyToInspect);
            }
        }

        return false;
    }

    public boolean hasCycleDirectedGraph(Map<Integer, List<Integer>> graph) {

        Set<Integer> keys = new HashSet<>(graph.keySet());
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited_3 = new HashSet<>();

        while (!keys.isEmpty()) {
            Integer key = keys.iterator().next();
            stack.push(key);

            while (!stack.isEmpty()) {
                Integer keyToInspect = stack.pop();
                if (visited_3.contains(keyToInspect)) {
                    return true;
                } else {
                    visited_3.add(keyToInspect);
                }

                List<Integer> connections = graph.getOrDefault(keyToInspect, List.of());

                for (Integer connection: connections) {
                    stack.push(connection);
                }

                keys.remove(keyToInspect);
            }
        }

        return false;
    }
}
