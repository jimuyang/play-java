package muyi.leetcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: muyi-macpro
 * @Time: 2018/3/11 下午4:47
 * @Description:
 */

/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */

import java.awt.*;

/**
 * 个人的做法：
 * 本质上是合并两个已排序的数组
 */
public class L4MedianOfTwoSortedArray {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] numAll = new int[m + n];

        int i = 0, j = 0;
        int index = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                numAll[index] = nums1[i];
                i++;
            } else {
                numAll[index] = nums2[j];
                j++;
            }
            index++;
        }

        if (i == nums1.length) {
            //将数组2的剩余部分加入numALL中
            for (int a = j; a < nums2.length; a++) {
                numAll[index] = nums2[a];
                index++;
            }
        }
        if (j == nums2.length) {
            for (int a = i; a < nums1.length; a++) {
                numAll[index] = nums1[a];
            }
        }

        double d1 = ((double) (m + n)) / 2;
        int d2 = (m + n) / 2;
        if (d1 == d2) {
            //12 / 2 = 6 取index 5，6平均数
            d1 = (double) ((numAll[d2 - 1] + numAll[d2])) / 2;
            return d1;
        } else {
            //int 13 / 2 = 6;
            return numAll[d2];
        }
    }


    public static void main(String[] args) {
        int[] intArray1 = new int[]{1, 2};
        int[] intArray2 = new int[]{3, 4};

//        double d = new L4MedianOfTwoSortedArray().findMedianSortedArrays(intArray1, intArray2);
//        System.out.println(d);

        double p = new Solution().findMedianSortedArrays(intArray1, intArray2);
        System.out.println(p);
    }


    public static class Solution {
        /**
         * https://leetcode.com/problems/median-of-two-sorted-arrays/solution/
         * <p>
         * 中位数的用处： 将一个集合分为同样长度的两个子集，其中一个子集永远大于另一个。
         * <p>
         * 此时，中位数为 左半部分的最小值 右半部分的最大值 的平均数
         * left_part          |        right_part
         * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
         * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
         * <p>
         * 那么需要： i + j = m - i + n - j; 得到 j = (m+n)/2 - i
         * A_left <=  B_right && B_left <= A_right 等价于 A[i-1] <= B[j] && B[j-1] <= A[i]
         * <p>
         * 那么需要找到一个i，使得：
         * A[i-1] <= B[j] && B[j-1] <= A[i] 其中 j = (m+n)/2-i
         */

        public double findMedianSortedArrays(int[] A, int[] B) {
            int m = A.length;
            int n = B.length;
            if (m > n) { // to ensure m<=n
                int[] temp = A;
                A = B;
                B = temp;
                int tmp = m;
                m = n;
                n = tmp;
            }
            // i的取之范围为：[0,m] , 一半长度为 halfLen 13 + 1 / 2 = 7 左边7个，右边6个
            int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
            while (iMin <= iMax) {
                //取一半
                int i = (iMin + iMax) / 2;
                //得到j
                int j = halfLen - i;
                if (i < iMax && B[j - 1] > A[i]) {
                    iMin = iMin + 1; // i is too small
                } else if (i > iMin && A[i - 1] > B[j]) {
                    iMax = iMax - 1; // i is too big
                } else { // i is perfect
                    int maxLeft = 0;
                    if (i == 0) {
                        maxLeft = B[j - 1];
                    } else if (j == 0) {
                        maxLeft = A[i - 1];
                    } else {
                        maxLeft = Math.max(A[i - 1], B[j - 1]);
                    }
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    }

                    int minRight = 0;
                    if (i == m) {
                        minRight = B[j];
                    } else if (j == n) {
                        minRight = A[i];
                    } else {
                        minRight = Math.min(B[j], A[i]);
                    }

                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }


    }
}


