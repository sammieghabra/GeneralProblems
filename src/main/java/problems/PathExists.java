package problems;

import java.util.*;

public class PathExists {

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

            System.out.println(temp);

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
}
