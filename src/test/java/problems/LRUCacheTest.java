package problems;

import org.junit.Assert;
import org.junit.Test;

public class LRUCacheTest {


    @Test
    public void testLruCache() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        int value = lruCache.get(1);
        lruCache.put(3, 3);
        int second = lruCache.get(2);
        lruCache.put(4, 4);
        int third = lruCache.get(1);
        int fourth = lruCache.get(3);
        int fifth = lruCache.get(4);

        Assert.assertEquals(1, value);
        Assert.assertEquals(-1, second);
        Assert.assertEquals(-1, third);
        Assert.assertEquals(3, fourth);
        Assert.assertEquals(4, fifth);
    }

    @Test
    public void testSecond() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(3, 2);

        int val = lruCache.get(3);
        int second = lruCache.get(2);
        lruCache.put(4, 3);
        int third = lruCache.get(2);
        int fourth = lruCache.get(3);
        int fifth = lruCache.get(4);
        Assert.assertEquals(2, val);
        Assert.assertEquals(1, second);
        Assert.assertEquals(1, third);
        Assert.assertEquals(-1, fourth);
        Assert.assertEquals(3, fifth);
    }

}
