package problems;

import model.DirectedEdge;

import java.util.*;
import java.util.stream.IntStream;

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

    int numberOfConnectedComponentsUndirectedGraph(Map<Integer, List<Integer>> graph) {

        class StackThing {
            public int nodeId;
            public int parent;

            public StackThing(int nodeId, int parent) {
                this.nodeId = nodeId;
                this.parent = parent;
            }
        }

        Stack<StackThing> stack = new Stack<>();

        Set<Integer> visited4 = new HashSet<>();
        Set<Integer> keys = new HashSet<>(graph.keySet());

        int result = 0;

        while (!keys.isEmpty()) {

            Integer keyToInspect = keys.iterator().next();
            result++;

            stack.push(new StackThing(keyToInspect, -1));

            while (!stack.isEmpty()) {
                StackThing stackThing = stack.pop();

                Integer poppedKey = stackThing.nodeId;

                keys.remove(poppedKey);

                if (!visited4.contains(poppedKey)) {
                    List<Integer> connections = graph.getOrDefault(poppedKey, List.of());

                    for (Integer connection : connections) {
                        if (connection != stackThing.parent) {
                            stack.push(new StackThing(connection, poppedKey));
                        }
                    }

                    visited4.add(poppedKey);
                }
            }

        }

        return result;
    }

    /**
     * Problem: Given a directed graph, check if it's a tree.
     *
     * A directed graph is called a tree if it has the following properties:
     *
     * 1. There is only one root node (a node with no incoming edges).
     * 2. All nodes except the root node have exactly one incoming edge.
     * 3. There is only one unique path from the root to every other node.
     *
     * @param graph
     * @return
     */
    public boolean isTree(Map<Integer, List<Integer>> graph) {

        class StackThing {
            public int parent;
            public int nodeId;

            public StackThing(int parent, int nodeId) {
                this.nodeId = nodeId;
                this.parent = parent;
            }
        }

        Set<Integer> keys = new HashSet<>(graph.keySet());
        Set<Integer> visited5 = new HashSet<>();

        if (graph.size() == 1 || graph.size() == 0) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();

        while (!keys.isEmpty()) {
            Integer key = keys.iterator().next();

            //stack.push(new StackThing(-1, key));
            stack.push(key);

            while (!stack.isEmpty()) {
                //StackThing stackThing = stack.pop();
                //Integer keyToInspect = stackThing.nodeId;
                Integer keyToInspect = stack.pop();

                List<Integer> connections = graph.getOrDefault(keyToInspect, List.of());

                for (Integer connection: connections) {
                    if (visited5.contains(connection)) {
                        return false;
                    }

                    visited5.add(connection);
                    //stack.push(new StackThing(keyToInspect, connection));
                    stack.push(connection);
                }

                keys.remove(keyToInspect);
            }

        }

        return Math.abs(visited5.size()-graph.keySet().size()) == 1;
    }

    public int findLongestPathInDAG(List<DirectedEdge> edges, int numberOfVertices) {
        List<Integer> result = new ArrayList<>();

        Map<Integer, List<DirectedEdge>> graph = new HashMap<>();

        Set<Integer> rootNodes = new HashSet<>();

        for (int i = 0; i < numberOfVertices; ++i) {
            rootNodes.add(i);
        }

        for (DirectedEdge edge: edges) {
            if (graph.containsKey(edge.source)) {
                List<DirectedEdge> fd = new ArrayList<>(graph.get(edge.source));
                fd.add(edge);
                graph.put(edge.source, fd);
            } else {
                List<DirectedEdge> put = new ArrayList<>();
                put.add(edge);
                graph.put(edge.source, put);
            }

            if (rootNodes.contains(edge.destination)) {
                rootNodes.remove(edge.destination);
            }
        }

        class DirectedEdgeWithAcc {
            public DirectedEdge directedEdge;
            public int weight;

            public List<Integer> path;

            public DirectedEdgeWithAcc(DirectedEdge directedEdge, int weight, List<Integer> path) {
                this.directedEdge = directedEdge;
                this.weight = weight;
                this.path = path;
            }
        }

        Stack<DirectedEdgeWithAcc> stack = new Stack<>();

        while (!rootNodes.isEmpty()) {
            Integer sc = rootNodes.iterator().next();
            List<DirectedEdge> edgeList = graph.getOrDefault(sc, new ArrayList<>());

            edgeList.forEach(edge -> {
                LinkedList linkedList = new LinkedList();
                linkedList.add(edge.source);
                stack.push(new DirectedEdgeWithAcc(edge, 0, linkedList));
            });

            while (!stack.isEmpty()) {
                DirectedEdgeWithAcc popped = stack.pop();
                DirectedEdge edge = popped.directedEdge;

                if (result.size() < popped.path.size()) {
                    result = popped.path;
                }

                if (edge == null) {
                    continue;
                }

                List<DirectedEdge> connections = graph.getOrDefault(edge.destination, new ArrayList<>());

                if (connections.isEmpty()) {
                    LinkedList linkedList = new LinkedList(popped.path);
                    linkedList.add(edge.destination);
                    stack.push(new DirectedEdgeWithAcc(null, popped.weight + edge.weight, linkedList));
                }

                connections.forEach(con -> {
                    LinkedList linkedList = new LinkedList(popped.path);
                    linkedList.add(con.source);

                    stack.push(new DirectedEdgeWithAcc(con, popped.weight + edge.weight, linkedList));
                });
            }

            rootNodes.remove(sc);
        }

        return result.size();
    }

    public int findShortestDistance(List<DirectedEdge> edges, int numberOfVertices, int source, int destination) {
        Map<Integer, List<DirectedEdge>> graph = new HashMap<>();
        Map<String, Integer> pathToWeight = new HashMap<>();

        if (source == destination) {
            return 0;
        }

        for (DirectedEdge edge: edges) {
            if (graph.containsKey(edge.source)) {
                List<DirectedEdge> fd = new ArrayList<>(graph.get(edge.source));
                fd.add(edge);
                graph.put(edge.source, fd);
            } else {
                List<DirectedEdge> put = new ArrayList<>();
                put.add(edge);
                graph.put(edge.source, put);
            }
        }

        class DirectedEdgeWithAcc {
            public DirectedEdge directedEdge;
            public int weight;

            int previous;

            public LinkedList<Integer> path;

            public DirectedEdgeWithAcc(DirectedEdge directedEdge, int weight, LinkedList<Integer> path, int previous) {
                this.directedEdge = directedEdge;
                this.weight = weight;
                this.path = path;
                this.previous = previous;
            }
        }

        Stack<DirectedEdgeWithAcc> stack = new Stack<>();

        Integer shortestDistance = Integer.MAX_VALUE;
        List<Integer> result = new ArrayList<>();
        List<DirectedEdge> edgeList = graph.getOrDefault(source, new ArrayList<>());

        edgeList.forEach(edge -> {
            LinkedList linkedList = new LinkedList();
            linkedList.add(edge.source);
            stack.push(new DirectedEdgeWithAcc(edge, 0, linkedList, -1));
        });

        while (!stack.isEmpty()) {
            DirectedEdgeWithAcc popped = stack.pop();
            DirectedEdge edge = popped.directedEdge;

            if (popped.path.getLast().equals(destination) && shortestDistance > popped.weight) {
                shortestDistance = popped.weight;
                result = new ArrayList<>(popped.path);
            }

            if (edge == null) {
                continue;
            }

            String key = edge.source + "-" + edge.destination;

            if (!pathToWeight.containsKey(key) || pathToWeight.get(key) > edge.weight + popped.weight) {
                pathToWeight.put(key, edge.weight + popped.weight);

                List<DirectedEdge> connections = graph.getOrDefault(edge.destination, new ArrayList<>());

                if (connections.isEmpty()) {
                    LinkedList linkedList = new LinkedList(popped.path);
                    linkedList.add(edge.destination);
                    stack.push(new DirectedEdgeWithAcc(null, popped.weight + edge.weight,
                            linkedList, edge.source));
                }

                connections.forEach(con -> {
                    LinkedList linkedList = new LinkedList(popped.path);
                    linkedList.add(con.source);

                    stack.push(new DirectedEdgeWithAcc(con, popped.weight + edge.weight,
                            linkedList, edge.source));
                });
            }
        }

        if (shortestDistance.equals(Integer.MAX_VALUE)) {
            return -1;
        }

        return shortestDistance;
    }
}
