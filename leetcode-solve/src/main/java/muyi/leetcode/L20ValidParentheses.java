package muyi.leetcode;

import java.util.Stack;

/**
 * @author: Jimu Yang
 * @date: 2019/1/5 12:22 PM
 * @descricption: want more.
 */
public class L20ValidParentheses {

    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * <p>
     * An input string is valid if:
     * <p>
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Note that an empty string is also considered valid.
     */
    public boolean isValid(String s) {
        if (s == null) {
            return true;
        }

//        s = s.replaceAll("\\s+", "");
        if (s.length() == 0) {
            return true;
        }

//        Stack<Character> stack = new Stack<>();
        MyStack stack = new MyStack(s.length());

        for (char ch : s.toCharArray()) {
            if (isOpen(ch)) {
                stack.push(ch);
                continue;
            }

            if (isClose(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if (!isMatch(pop, ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public boolean isOpen(char ch) {
        return ch == '{' || ch == '(' || ch == '[';
    }

    public boolean isClose(char ch) {
        return ch == '}' || ch == ')' || ch == ']';
    }

    public boolean isMatch(char ch1, char ch2) {
        switch (ch1) {
            case '(':
                return ch2 == ')';
            case '[':
                return ch2 == ']';
            case '{':
                return ch2 == '}';
        }
        return false;
    }


    public class MyStack {
        char[] chars;
        int index; //空栈顶

        MyStack(int size) {
            chars = new char[size];
            index = 0;
        }

        public boolean isEmpty() {
            return index == 0;
        }

        public void push(char ch) {
            chars[index] = ch;
            index ++;
        }

        public char pop() {
            index --;
            return chars[index];
        }
    }


}
