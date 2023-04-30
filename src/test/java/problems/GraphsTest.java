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

    @Test
    public void testHasCycleUndirectedGraph() {
        Map<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(0, Arrays.asList(1, 2));
        graph1.put(1, Arrays.asList(0, 2));
        graph1.put(2, Arrays.asList(0, 1, 3));
        graph1.put(3, Arrays.asList(2));
        Assert.assertTrue(graphs.hasCycleUndirectedGraph(graph1));
    }

    @Test
    public void testNoCycleUndirectedGraph() {
        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(0, Arrays.asList(1));
        graph2.put(1, Arrays.asList(0));
        graph2.put(2, Arrays.asList(3));
        graph2.put(3, Arrays.asList(2));
        Assert.assertFalse(graphs.hasCycleUndirectedGraph(graph2));
    }

    @Test
    public void anotherCycleUndirected() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList(1));
        graph.remove(1);
        Assert.assertFalse(graphs.hasCycleUndirectedGraph(graph));

        Map<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(1, Arrays.asList(2, 3));
        graph1.put(2, Arrays.asList(1, 3));
        graph1.put(3, Arrays.asList(1, 2));
        Assert.assertTrue(graphs.hasCycleUndirectedGraph(graph1));

        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(1, Arrays.asList(2, 3));
        graph2.put(2, Arrays.asList(1, 4));
        graph2.put(3, Arrays.asList(1));
        graph2.put(4, Arrays.asList(2));
        Assert.assertFalse(graphs.hasCycleUndirectedGraph(graph2));

        Map<Integer, List<Integer>> graph3 = new HashMap<>();
        graph3.put(1, Arrays.asList(2));
        graph3.put(2, Arrays.asList(1, 3, 4));
        graph3.put(3, Arrays.asList(2, 4));
        graph3.put(4, Arrays.asList(2, 3));
        Assert.assertTrue(graphs.hasCycleUndirectedGraph(graph3));

        Map<Integer, List<Integer>> graph4 = new HashMap<>();
        graph4.put(1, Arrays.asList(2));
        graph4.put(2, Arrays.asList(1, 3));
        graph4.put(3, Arrays.asList(2));
        Assert.assertFalse(graphs.hasCycleUndirectedGraph(graph4));

        Map<Integer, List<Integer>> graph5 = new HashMap<>();
        graph5.put(1, Arrays.asList(2, 4));
        graph5.put(2, Arrays.asList(1, 3, 4));
        graph5.put(3, Arrays.asList(2));
        graph5.put(4, Arrays.asList(1, 2));
        Assert.assertTrue(graphs.hasCycleUndirectedGraph(graph5));
    }

    @Test
    public void testCycleForDirectedGraph() {

        Map<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(1, Arrays.asList(2));
        graph1.put(2, Arrays.asList(3));
        graph1.put(3, Arrays.asList(4));
        graph1.put(4, Arrays.asList(1));
        Assert.assertTrue(graphs.hasCycleDirectedGraph(graph1));

        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(1, Arrays.asList(2));
        graph2.put(2, Arrays.asList(3));
        graph2.put(3, Arrays.asList(4));
        graph2.put(4, Arrays.asList());
        Assert.assertFalse(graphs.hasCycleDirectedGraph(graph2));

        Map<Integer, List<Integer>> graph3 = new HashMap<>();
        graph3.put(1, Arrays.asList(2, 3));
        graph3.put(2, Arrays.asList(3, 4));
        graph3.put(3, Arrays.asList(5));
        graph3.put(4, Arrays.asList(6));
        graph3.put(5, Arrays.asList(6));
        graph3.put(6, Arrays.asList(2));
        Assert.assertTrue(graphs.hasCycleDirectedGraph(graph3));

        Map<Integer, List<Integer>> graph4 = new HashMap<>();
        graph4.put(1, Arrays.asList(2));
        graph4.put(2, Arrays.asList(3));
        graph4.put(3, Arrays.asList(1));
        Assert.assertTrue(graphs.hasCycleDirectedGraph(graph4));

        Map<Integer, List<Integer>> graph5 = new HashMap<>();
        graph5.put(1, Arrays.asList(2));
        graph5.put(2, Arrays.asList(3));
        graph5.put(3, Arrays.asList(1));
        // Expected output: true
        Assert.assertTrue(graphs.hasCycleDirectedGraph(graph5));

        Map<Integer, List<Integer>> graph6 = new HashMap<>();
        graph6.put(1, Arrays.asList(2));
        graph6.put(2, Arrays.asList(3));
        graph6.put(3, Arrays.asList());
        // Expected output: false
        Assert.assertFalse(graphs.hasCycleDirectedGraph(graph6));

        Map<Integer, List<Integer>> graph7 = new HashMap<>();
        graph7.put(1, Arrays.asList(2));
        graph7.put(2, Arrays.asList(3));
        graph7.put(3, Arrays.asList(1));
        graph7.put(4, Arrays.asList(5));
        graph7.put(5, Arrays.asList());
        // Expected output: true
        Assert.assertTrue(graphs.hasCycleDirectedGraph(graph7));
    }

    @Test
    public void numberOfConnectedComponentsUndirectedGraph() {
        // Test case 1
        Map<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(0, Arrays.asList(1, 2));
        graph1.put(1, Arrays.asList(0));
        graph1.put(2, Arrays.asList(0));
        graph1.put(3, Arrays.asList(4));
        graph1.put(4, Arrays.asList(3));
        Assert.assertEquals(2, graphs.numberOfConnectedComponentsUndirectedGraph(graph1));

        // Test case 2
        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(0, Arrays.asList(1, 2));
        graph2.put(1, Arrays.asList(0));
        graph2.put(2, Arrays.asList(0));
        graph2.put(3, Arrays.asList(4, 5));
        graph2.put(4, Arrays.asList(3));
        graph2.put(5, Arrays.asList(3));
        Assert.assertEquals(2, graphs.numberOfConnectedComponentsUndirectedGraph(graph2));

        // Test case 3
        Map<Integer, List<Integer>> graph3 = new HashMap<>();
        graph3.put(0, Arrays.asList(1));
        graph3.put(1, Arrays.asList(0, 2));
        graph3.put(2, Arrays.asList(1));
        graph3.put(3, Arrays.asList());
        graph3.put(4, Arrays.asList(5));
        graph3.put(5, Arrays.asList(4));
        Assert.assertEquals(3, graphs.numberOfConnectedComponentsUndirectedGraph(graph3));

        // Test case 4
        Map<Integer, List<Integer>> graph4 = new HashMap<>();
        graph4.put(0, Arrays.asList(1));
        graph4.put(1, Arrays.asList(0, 2));
        graph4.put(2, Arrays.asList(1));
        graph4.put(3, Arrays.asList(4, 5));
        graph4.put(4, Arrays.asList(3, 5));
        graph4.put(5, Arrays.asList(3, 4));
        Assert.assertEquals(2, graphs.numberOfConnectedComponentsUndirectedGraph(graph4));

        // Test case 5
        Map<Integer, List<Integer>> graph5 = new HashMap<>();
        graph5.put(0, Arrays.asList(1));
        graph5.put(1, Arrays.asList(0, 2));
        graph5.put(2, Arrays.asList(1));
        graph5.put(3, Arrays.asList(4, 5, 6));
        graph5.put(4, Arrays.asList(3));
        graph5.put(5, Arrays.asList(3));
        graph5.put(6, Arrays.asList(3));
        Assert.assertEquals(2, graphs.numberOfConnectedComponentsUndirectedGraph(graph5));
    }
}
