package muyi.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class L10RegularExpressionMatchingTest {

    @Test
    public void isMatch() {

        //"bbbba"
        //".*a*a"
        String pattern = ".*a*a";
        String source = "bbbba";
        assert new L10RegularExpressionMatching().isMatch(source, pattern);
    }

    @Test
    public void compilePattern() {
        String pattern = "a*.*asdf.g*.*.";
        new L10RegularExpressionMatching().compilePattern(pattern).forEach(System.out::println);
    }
}