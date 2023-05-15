package problems;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArraysTest {

    Arrays arrays;

    @Before
    public void setup() {
        arrays = new Arrays();
    }
    @Test
    public void testRotate() {
        int[] testArray = new int[]{1,2,3,4,5};
        arrays.rotateArrayInMemory(testArray, 2);

        Assert.assertTrue(testArray[0] == 4);
        Assert.assertTrue(testArray[1] == 5);
        Assert.assertTrue(testArray[2] == 1);
        Assert.assertTrue(testArray[3] == 2);
        Assert.assertTrue(testArray[4] == 3);
    }

    @Test
    public void testMaximumSubarray() {
        int[] testArray = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int maxSum = arrays.maximumSubarraySum(testArray);
        Assert.assertEquals(6, maxSum);
    }
}
