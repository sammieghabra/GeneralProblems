package problems;

import java.util.ArrayDeque;
import java.util.Stack;

public class BalancedParenthesis {

    Stack<Character> stack = new Stack<>();

    /**
     * Problem: Given a string containing only the characters '(' and ')', write two functions that use stacks
     * and queues, respectively, to determine if the string is balanced. A balanced string has an equal number
     * of open and close parentheses, and every open parenthesis has a corresponding close parenthesis.
     *
     * Input: "(()())"
     * Output: True
     *
     * Input: "())("
     * Output: False
     *
     * @param parenthesis
     * @return
     */
    public boolean isBalancedStack(String parenthesis) {

        for (int i = 0; i < parenthesis.length(); ++i) {

            Character c = parenthesis.charAt(i);

            if (c.equals(')')) {

                if (stack.isEmpty()) {
                    return false;
                }

                Character top = stack.pop();

                if (top.equals(')')) {
                    return false;
                }

            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

}
