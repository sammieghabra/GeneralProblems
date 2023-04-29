package problems;

import org.junit.Assert;
import org.junit.Test;

public class BalancedParenthesisTest {

    BalancedParenthesis balancedParenthesis = new BalancedParenthesis();

    @Test
    public void testBalancedParenthesisStack() {
        Assert.assertTrue(balancedParenthesis.isBalancedStack("(()())"));
        Assert.assertFalse(balancedParenthesis.isBalancedStack("())("));
        Assert.assertTrue(balancedParenthesis.isBalancedStack("()"));
        Assert.assertTrue(balancedParenthesis.isBalancedStack("(())"));
        Assert.assertTrue(balancedParenthesis.isBalancedStack("()()(())"));
        Assert.assertTrue(balancedParenthesis.isBalancedStack("(()(()))"));
    }
}
