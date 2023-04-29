package problems;

import org.junit.Assert;
import org.junit.Test;

public class DynamicProgrammingTest {

    DynamicProgramming dynamicProgramming = new DynamicProgramming();

    @Test
    public void testFib() {
        Assert.assertEquals(1, dynamicProgramming.fib(2));
        Assert.assertEquals(2, dynamicProgramming.fib(3));
        Assert.assertEquals(3, dynamicProgramming.fib(4));
    }
}
