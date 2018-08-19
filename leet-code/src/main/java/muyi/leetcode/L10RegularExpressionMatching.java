package muyi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 简单的正则匹配实现
 *
 * @author: Jimu Yang.
 */
public class L10RegularExpressionMatching {


    /**
     * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
     * <p>
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * <p>
     * Note:
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like . or *.
     */

    /**
     * 这里将所有字符分开匹配 待优化
     */
    public boolean isMatch(String s, String p) {

        char[] source = s.toCharArray();
        List<PatternAtom> compiledPattern = this.compilePattern(p);
        int atomNum = compiledPattern.size();

        // 贪心 每个pattern atom会按顺序在source上染最大色
        // 当所有atom染色成功时，代表匹配成功
        // 当不能继续染色时 回溯
        // 当first atom都出栈时，代表匹配失败

        int currentAtom = 0;
        int start = 0;
        boolean rematch = false;

        while (currentAtom < atomNum) {
            if (currentAtom < 0) {
                return false;
            }

            if (rematch) {
                int end = compiledPattern.get(currentAtom).rematch(source);
                if (end == -1) {
                    currentAtom--;
                } else {
                    rematch = false;
                    start = end;
                    currentAtom++;
                }
            } else {
                int end = compiledPattern.get(currentAtom).match(source, start);
                if (end == -1) {
                    currentAtom--;
                    rematch = true;
                } else {
                    currentAtom++;
                    start = end;
                }
            }
        }
        return start == source.length;
    }

    /**
     * 解析pattern
     *
     * @param pattern
     * @return
     */
    public List<PatternAtom> compilePattern(String pattern) {
        char[] patternChars = pattern.toCharArray();
        // 先对pattern进行解析
        // 按优先级排序： .* | . | a* | a
        List<PatternAtom> compiledPatternAtoms = new ArrayList<>();
        char tempRoot = 0;
        for (char ch : patternChars) {
            // letters a-z
            if ((ch >= 'a' && ch <= 'z') || ch == '.') {
                if (tempRoot != 0) {
                    if (tempRoot == '.') {
                        compiledPatternAtoms.add(new Any());
                    } else {
                        compiledPatternAtoms.add(new One(tempRoot));
                    }
                }
                tempRoot = ch;
            }

            // *
            if (ch == '*') {
                if (tempRoot == 0) {
                    throw new IllegalArgumentException("Invalid pattern");
                } else if (tempRoot == '.') {
                    compiledPatternAtoms.add(new AnyMore());
                } else {
                    compiledPatternAtoms.add(new OneMore(tempRoot));
                }
                tempRoot = 0;
            }
        }
        if (tempRoot == '.') {
            compiledPatternAtoms.add(new Any());
        } else if (tempRoot != 0) {
            compiledPatternAtoms.add(new One(tempRoot));
        }
        return compiledPatternAtoms;
    }

    /**
     * 正则匹配的最小单元
     */
    interface PatternAtom {
        /**
         * atom的类型
         */
        PatternAtomType getType();

        /**
         * root 词根
         */
        char getRoot();

        /**
         * 在source上染色
         *
         * @param source source
         * @param start  未染色起始位置
         * @return 染色end位置 -1失败 最大可到length
         */
        int match(char[] source, int start);

        /**
         * 重新染色
         *
         * @param source
         * @return
         */
        int rematch(char[] source);

    }

    class AnyMore implements PatternAtom {
        private int start = -1;
        private int end = -1;

//        private boolean success = false;

        @Override
        public int match(char[] source, int start) {
            this.start = start;
            this.end = source.length;
//            this.success = true;
            return this.end;
        }

        @Override
        public int rematch(char[] source) {
            this.end = this.end - 1;
//            this.success = !(start < end);

            this.end = start > end ? -1 : end;
            return this.end;
        }

        @Override
        public PatternAtomType getType() {
            return PatternAtomType.ANYMORE;
        }

        @Override
        public char getRoot() {
            return '.';
        }

        @Override
        public String toString() {
            return ".*";
        }
    }

    class Any implements PatternAtom {

        @Override
        public int match(char[] source, int start) {
            return start + 1 > source.length ? -1 : start + 1;
        }

        @Override
        public int rematch(char[] source) {
            return -1;
        }

        @Override
        public PatternAtomType getType() {
            return PatternAtomType.ANY;
        }

        @Override
        public char getRoot() {
            return '.';
        }

        @Override
        public String toString() {
            return ".";
        }
    }

    class OneMore implements PatternAtom {
        private char root;
        private int start = -1;
        private int end = -1;

        @Override
        public int match(char[] source, int start) {
            this.start = start;
            for (int i = start; i < source.length; i++) {
                if (source[i] == root) {
                    continue;
                }
                this.end = i;
                return i;
            }
            this.end = source.length;
            return source.length;
        }

        @Override
        public int rematch(char[] source) {
            this.end = this.end - 1;
            return this.end < this.start ? -1 : this.end;
        }

        public OneMore(char root) {
            this.root = root;
        }

        @Override
        public PatternAtomType getType() {
            return PatternAtomType.ONEMORE;
        }

        @Override
        public char getRoot() {
            return this.root;
        }

        @Override
        public String toString() {
            return root + "*";
        }
    }

    class One implements PatternAtom {
        private char root;

        @Override
        public int match(char[] source, int start) {
            if (start >= source.length) {
                return -1;
            }
            return source[start] == root ? start + 1 : -1;
        }

        @Override
        public int rematch(char[] source) {
            return -1;
        }

        public One(char root) {
            this.root = root;
        }

        @Override
        public PatternAtomType getType() {
            return PatternAtomType.ONE;
        }

        @Override
        public char getRoot() {
            return this.root;
        }

        @Override
        public String toString() {
            return String.valueOf(root);
        }
    }


    enum PatternAtomType {
        /**
         * .*
         */
        ANYMORE,
        /**
         * .
         */
        ANY,
        /**
         * a*
         */
        ONEMORE,
        /**
         * a
         */
        ONE;
    }


    /**
     * 递归
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatchByDiGui(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }
    /**
     * DP解法
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatchByDP(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
