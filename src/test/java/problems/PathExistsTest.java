package problems;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathExistsTest {

    PathExists pathExists = new PathExists();

    @Test
    public void pathExistTestNoCycle() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, List.of(2, 3));
        map.put(2, List.of(4));
        map.put(3, List.of(5));
        map.put(4, List.of());
        map.put(5, List.of());

        Assert.assertTrue(pathExists.pathExistsDFS(map, 1,5));
        Assert.assertTrue(pathExists.pathExistsBFS(map, 1,5));
    }

    @Test
    public void pathExistTestCycle() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, List.of(2, 3));
        map.put(2, List.of(4,1));
        map.put(3, List.of(5));
        map.put(4, List.of(2));
        map.put(5, List.of());

        Assert.assertTrue(pathExists.pathExistsDFS(map, 1,5));
        Assert.assertTrue(pathExists.pathExistsBFS(map, 1,5));
    }
}
