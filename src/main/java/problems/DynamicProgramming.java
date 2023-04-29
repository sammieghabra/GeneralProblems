package problems;

import java.util.ArrayList;
import java.util.List;

public class DynamicProgramming {

    int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(1);

        for (int i = 2; i <= n; ++i) {
            Integer val = res.get(i-1) + res.get(i-2);
            res.add(val);
        }
        return res.get(n);
    }

    int longestSubString(String s1, String s2) {
        int[][] matrix = new int[s1.length() + 1][s2.length() + 1];

        // as an empty string has an LCS length of 0.
        matrix[0][0] = 0;

        for (int i = 1; i <= s1.length(); ++i) {
            for (int j = 1; j <= s2.length(); ++j) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j-1], matrix[i-1][j]);
                }
            }
        }

        return matrix[s1.length()][s2.length()];
    }
}
