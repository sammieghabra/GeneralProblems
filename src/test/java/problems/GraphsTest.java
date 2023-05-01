package problems;

import model.DirectedEdge;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void isTreeDirectedGraph() {
        Map<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(0, Arrays.asList(1, 2));
        graph1.put(1, Arrays.asList(3));
        graph1.put(2, Arrays.asList());
        graph1.put(3, Arrays.asList());
        Assert.assertEquals(true, graphs.isTree(graph1));

        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(0, Arrays.asList(1));
        graph2.put(1, Arrays.asList(2));
        graph2.put(2, Arrays.asList(3));
        graph2.put(3, Arrays.asList());
        Assert.assertEquals(true, graphs.isTree(graph2));

        Map<Integer, List<Integer>> graph3 = new HashMap<>();
        graph3.put(0, Arrays.asList(1, 2));
        graph3.put(1, Arrays.asList(2));
        graph3.put(2, Arrays.asList());
        Assert.assertEquals(false, graphs.isTree(graph3));

        Map<Integer, List<Integer>> graph4 = new HashMap<>();
        graph4.put(0, Arrays.asList(1));
        graph4.put(1, Arrays.asList(2));
        graph4.put(2, Arrays.asList(0));
        Assert.assertEquals(false, graphs.isTree(graph4));

        Map<Integer, List<Integer>> graph5 = new HashMap<>();
        graph5.put(0, Arrays.asList(1, 2));
        graph5.put(1, Arrays.asList(3, 4));
        graph5.put(2, Arrays.asList(5, 6));
        graph5.put(3, Arrays.asList());
        graph5.put(4, Arrays.asList());
        graph5.put(5, Arrays.asList());
        graph5.put(6, Arrays.asList());
        Assert.assertEquals(true, graphs.isTree(graph5));

        Map<Integer, List<Integer>> flawedGraph = new HashMap<>();
        flawedGraph.put(0, Arrays.asList(1, 2));
        flawedGraph.put(1, Arrays.asList());
        flawedGraph.put(2, Arrays.asList(3));
        flawedGraph.put(3, Arrays.asList());
        Assert.assertEquals(true, graphs.isTree(flawedGraph));

        Map<Integer, List<Integer>> example1 = new HashMap<>();
        example1.put(0, Arrays.asList(1));
        example1.put(1, Arrays.asList(0, 2));
        example1.put(2, Arrays.asList(1, 3));
        example1.put(3, Arrays.asList(2, 4));
        example1.put(4, Arrays.asList(3, 0));
        Assert.assertEquals(false, graphs.isTree(example1));

        Map<Integer, List<Integer>> example2 = new HashMap<>();
        example2.put(0, Arrays.asList(1));
        example2.put(1, Arrays.asList(0));
        example2.put(2, Arrays.asList(3));
        example2.put(3, Arrays.asList(2));
        Assert.assertEquals(false, graphs.isTree(example2));

        Map<Integer, List<Integer>> example3 = new HashMap<>();
        example3.put(0, Arrays.asList(1, 2));
        example3.put(1, Arrays.asList(0, 3));
        example3.put(2, Arrays.asList(0, 3));
        example3.put(3, Arrays.asList(1, 2, 4));
        example3.put(4, Arrays.asList(3));
        Assert.assertEquals(false, graphs.isTree(example3));

        Map<Integer, List<Integer>> example4 = new HashMap<>();
        example4.put(0, new ArrayList<>());
        Assert.assertEquals(true, graphs.isTree(example4));

        Map<Integer, List<Integer>> example5 = new HashMap<>();
        example5.put(0, Arrays.asList(1, 2, 3));
        example5.put(1, Arrays.asList(4, 5));
        example5.put(2, Arrays.asList());
        example5.put(3, Arrays.asList(6));
        example5.put(4, Arrays.asList());
        example5.put(5, Arrays.asList());
        example5.put(6, Arrays.asList());
        Assert.assertEquals(true, graphs.isTree(example5));

        Map<Integer, List<Integer>> graph8 = new HashMap<>();
        graph8.put(1, Arrays.asList(2));
        graph8.put(2, Arrays.asList(3));
        graph8.put(3, Arrays.asList(4));
        graph8.put(4, Arrays.asList(1));
        Assert.assertEquals(false, graphs.isTree(graph8));

        Map<Integer, List<Integer>> graph9 = new HashMap<>();
        graph9.put(1, Arrays.asList(2));
        graph9.put(2, Arrays.asList());
        graph9.put(3, Arrays.asList(4));
        graph9.put(4, Arrays.asList());
        Assert.assertEquals(false, graphs.isTree(graph9));
    }

    @Test
    public void testFindLongestPathInDAG() {
        List<DirectedEdge> edges = Arrays.asList(
                new DirectedEdge(0, 1, 5),
                new DirectedEdge(0, 2, 3),
                new DirectedEdge(1, 3, 6),
                new DirectedEdge(1, 2, 2),
                new DirectedEdge(2, 4, 4),
                new DirectedEdge(2, 5, 2),
                new DirectedEdge(2, 3, 7),
                new DirectedEdge(3, 5, 1),
                new DirectedEdge(3, 4, -1),
                new DirectedEdge(4, 5, -2)
        );
        int numberOfVertices = 6;
        Assert.assertEquals(6, graphs.findLongestPathInDAG(edges, numberOfVertices));

        List<DirectedEdge> edges1 = Arrays.asList(
                new DirectedEdge(0, 1, 3),
                new DirectedEdge(0, 2, 2),
                new DirectedEdge(1, 3, 4),
                new DirectedEdge(1, 4, 1),
                new DirectedEdge(2, 3, 2),
                new DirectedEdge(2, 4, 3),
                new DirectedEdge(3, 5, 6),
                new DirectedEdge(4, 5, 5)
        );
        int numberOfVertices1 = 6;
        List<Integer> result1 = Arrays.asList(0, 1, 3, 5);

        Assert.assertEquals(result1.size(), graphs.findLongestPathInDAG(edges1, numberOfVertices1));

        List<DirectedEdge> edges2 = Arrays.asList(
                new DirectedEdge(0, 1, 1),
                new DirectedEdge(0, 2, 1),
                new DirectedEdge(1, 3, 1),
                new DirectedEdge(2, 3, 1),
                new DirectedEdge(3, 4, 1)
        );
        int numberOfVertices2 = 5;
        List<Integer> result2 = Arrays.asList(0, 1, 3, 4);
        Assert.assertEquals(result2.size(), graphs.findLongestPathInDAG(edges2, numberOfVertices2));
    }

    @Test
    public void testFindShortestDistance() {
        List<DirectedEdge> edges = Arrays.asList(
                new DirectedEdge(0, 1, 10),
                new DirectedEdge(0, 2, 5),
                new DirectedEdge(1, 3, 1),
                new DirectedEdge(1, 2, 2),
                new DirectedEdge(2, 1, 3),
                new DirectedEdge(2, 3, 9),
                new DirectedEdge(2, 4, 2),
                new DirectedEdge(3, 4, 4),
                new DirectedEdge(3, 0, 7),
                new DirectedEdge(4, 3, 6)
        );
        int numberOfVertices = 5;
        int source = 0;
        int destination = 4;
        Assert.assertEquals(7, graphs.findShortestDistance(edges, numberOfVertices, source, destination));

        List<DirectedEdge> edges1 = Arrays.asList(
                new DirectedEdge(0, 1, 4),
                new DirectedEdge(0, 2, 2),
                new DirectedEdge(1, 2, 5),
                new DirectedEdge(1, 3, 10),
                new DirectedEdge(2, 4, 3),
                new DirectedEdge(3, 5, 5),
                new DirectedEdge(4, 3, 4),
                new DirectedEdge(4, 5, 6)
        );
        int numberOfVertices1 = 6;
        int source1 = 0;
        int destination1 = 5;
        Assert.assertEquals(11, graphs.findShortestDistance(edges1, numberOfVertices1, source1, destination1));

        List<DirectedEdge> edges2 = Arrays.asList(
                new DirectedEdge(0, 1, 3),
                new DirectedEdge(0, 2, 6),
                new DirectedEdge(1, 2, 4),
                new DirectedEdge(1, 3, 4),
                new DirectedEdge(1, 4, 11),
                new DirectedEdge(2, 3, 8),
                new DirectedEdge(2, 6, 11),
                new DirectedEdge(3, 4, -4),
                new DirectedEdge(3, 5, 5),
                new DirectedEdge(3, 6, 2),
                new DirectedEdge(4, 7, 9),
                new DirectedEdge(5, 7, 1),
                new DirectedEdge(6, 7, 2)
        );
        int numberOfVertices2 = 8;
        int source2 = 0;
        int destination2 = 7;
        Assert.assertEquals(11, graphs.findShortestDistance(edges2, numberOfVertices2, source2, destination2));
    }

    @Test
    public void testWithNegative() {
        List<DirectedEdge> edges = Arrays.asList(
                new DirectedEdge(0, 1, 1),
                new DirectedEdge(1, 2, 2),
                new DirectedEdge(2, 3, 3),
                new DirectedEdge(3, 1, -7)
        );
        int numberOfVertices = 4;
        int source = 0;
        int destination = 2;
        Assert.assertEquals(1, graphs.findShortestDistance(edges, numberOfVertices, source, destination));

        List<DirectedEdge> edges1 = Arrays.asList(
                new DirectedEdge(0, 1, 4),
                new DirectedEdge(0, 2, 2),
                new DirectedEdge(1, 2, 1),
                new DirectedEdge(1, 3, 5),
                new DirectedEdge(2, 3, -3),
                new DirectedEdge(2, 4, 3),
                new DirectedEdge(3, 5, 2),
                new DirectedEdge(4, 5, -5)
        );
        int numberOfVertices1 = 6;
        int source1 = 0;
        int destination1 = 5;
        Assert.assertEquals(-2, graphs.findShortestDistance(edges1, numberOfVertices1, source1, destination1));


        List<DirectedEdge> edges2 = Arrays.asList(
                new DirectedEdge(0, 1, 2),
                new DirectedEdge(1, 2, 3),
                new DirectedEdge(1, 3, 4),
                new DirectedEdge(2, 4, -4),
                new DirectedEdge(2, 5, 5),
                new DirectedEdge(3, 5, 1),
                new DirectedEdge(4, 5, -1)
        );
        int numberOfVertices2 = 6;
        int source2 = 0;
        int destination2 = 5;
        Assert.assertEquals(0, graphs.findShortestDistance(edges2, numberOfVertices2, source2, destination2));
        List<DirectedEdge> edges3 = Arrays.asList(
                new DirectedEdge(0, 1, 2),
                new DirectedEdge(0, 2, 4),
                new DirectedEdge(1, 2, 1),
                new DirectedEdge(1, 3, 3),
                new DirectedEdge(2, 3, -6),
                new DirectedEdge(3, 4, 1)
        );
        int numberOfVertices3 = 5;
        int source3 = 0;
        int destination3 = 4;
        Assert.assertEquals(-1, graphs.findShortestDistance(edges3, numberOfVertices3, source3, destination3));
    }
}
