package muyi.leetcode;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 *
 * @author: Jimu Yang.
 */
public class L9PalindromeNumber {


    /**
     * without converting the integer to a string?
     */
    public boolean isPalindromeNumber(int x) {
        if (x < 0) {
            return false;
        }

        int reverseX  = new L7ReverseInteger().otherReverse(x);

        return reverseX == x;
    }
}
