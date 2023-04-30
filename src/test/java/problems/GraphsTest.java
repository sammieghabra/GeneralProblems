package problems;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphsTest {

    Graphs graphs = new Graphs();

    @Test
    public void pathExistTestNoCycle() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, List.of(2, 3));
        map.put(2, List.of(4));
        map.put(3, List.of(5));
        map.put(4, List.of());
        map.put(5, List.of());

        Assert.assertTrue(graphs.pathExistsDFS(map, 1,5));
        Assert.assertTrue(graphs.pathExistsBFS(map, 1,5));
    }

    @Test
    public void pathExistTestCycle() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, List.of(2, 3));
        map.put(2, List.of(4,1));
        map.put(3, List.of(5));
        map.put(4, List.of(2));
        map.put(5, List.of());

        Assert.assertTrue(graphs.pathExistsDFS(map, 1,5));
        Assert.assertTrue(graphs.pathExistsBFS(map, 1,5));
    }

    @Test
    public void testDjikstra() {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        map.put(0, Map.of(1, 4, 7, 8));
        map.put(1, Map.of(2, 8, 7, 11));
        map.put(2, Map.of(3, 7, 8, 2, 5, 4));
        map.put(3, Map.of(4, 9, 5, 14));
        map.put(4, Map.of(5, 10));
        map.put(5, Map.of(6, 2));
        map.put(6, Map.of(7, 1, 8, 6));
        map.put(7, Map.of(8, 7));
        map.put(8, Map.of());
    }

    @Test
    public void shortestPathUnweighted_1() {
        Map<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(1, Arrays.asList(2, 4));
        graph1.put(2, Arrays.asList(3));
        graph1.put(3, Arrays.asList(1));
        graph1.put(4, Arrays.asList(5));
        graph1.put(5, Arrays.asList(6));
        graph1.put(6, Arrays.asList());
        int source1 = 1;
        int target1 = 6;
        Assert.assertEquals(3, graphs.shortestPathLength(graph1, source1, target1));
    }

    @Test
    public void shortestPathUnweighted_2() {
        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(1, Arrays.asList(2));
        graph2.put(2, Arrays.asList(3));
        graph2.put(3, Arrays.asList(4));
        graph2.put(4, Arrays.asList(5));
        graph2.put(5, Arrays.asList());
        int source2 = 1;
        int target2 = 5;
        Assert.assertEquals(4, graphs.shortestPathLength(graph2, source2, target2));
    }

    @Test
    public void shortestPathUnweighted_3() {
        Map<Integer, List<Integer>> graph3 = new HashMap<>();
        graph3.put(1, Arrays.asList(2, 3));
        graph3.put(2, Arrays.asList(4));
        graph3.put(3, Arrays.asList(5));
        graph3.put(4, Arrays.asList(6));
        graph3.put(5, Arrays.asList(6));
        graph3.put(6, Arrays.asList());
        int source3 = 1;
        int target3 = 6;
        Assert.assertEquals(3, graphs.shortestPathLength(graph3, source3, target3));
    }
}
