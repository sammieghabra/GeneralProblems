package problems;

public class Arrays {

    /**
     * Problem: Write a function rotateArray in Java that takes an array of integers and an integer k, and rotates
     * the array to the right by k positions.
     * For example, if the input array is [1,2,3,4,5] and k is 2, the output should be [4,5,1,2,3].
     * If k is greater than the length of the array, rotate it k % array.length times.
     * @param array
     * @param k
     * @return
     */
    public int[] rotateArray(int[] array, int k) {

        int rotationFactor = k;

        if (k > array.length) {
            rotationFactor = k % array.length;
        }

        int[] result = new int[array.length];

        int start = array.length - rotationFactor;
        int counter = 0;

        while (start < array.length) {
            result[counter] = array[start];
            start++;
            counter++;
        }
        start = 0;
        while (counter < array.length) {
            result[counter] = array[start];
            start++;
            counter++;
        }

        return result;
    }

    public void rotateArrayInMemory(int[] array, int k) {
        if (array.length < 2) {
            return;
        }

        if (k > array.length) {
            k = k % array.length;
        }

        reverse(array);
        reverseWithStartAndEnd(0, k - 1, array);
        reverseWithStartAndEnd(k, array.length-1, array);
    }

    private void reverse(int[] nums) {
        int last = nums.length-1;
        int i = 0;
        while (i < last) {
            int temp = nums[i];
            int tempLast = nums[last];
            nums[i] = tempLast;
            nums[last] = temp;

            last--;
            i++;
        }

    }

    private void reverseWithStartAndEnd(int start, int end, int[] nums) {

        while (start < end) {
            int temp = nums[start];
            int tempLast = nums[end];
            nums[start] = tempLast;
            nums[end] = temp;

            start++;
            end--;
        }

    }

    // primitive vs object - why use one over the other? advantages of one over the other?
    // why cant collection use primitive?

    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     *
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     *
     * @return
     */
    public int maximumSubarraySum(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int bestSeen = Integer.MIN_VALUE;

        int[] topDown = new int[nums.length];

        // For the first index, the best we can do is include it or not.
        topDown[0] = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            int previousBest = topDown[i-1];
            int current = nums[i];
            if (previousBest + current > current) {
                topDown[i] = previousBest + current;
            } else {
                topDown[i] = current;
            }
        }

        for (int i = 0; i < topDown.length; ++i) {
            if (bestSeen < topDown[i]) {
                bestSeen = topDown[i];
            }
        }

        return bestSeen;
    }
}
