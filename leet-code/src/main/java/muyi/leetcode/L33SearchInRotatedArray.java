package muyi.leetcode;

/**
 * @author: Jimu Yang
 * @date: 2019/3/13 9:39 PM
 * @descricption: want more.
 */
public class L33SearchInRotatedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return search(nums, target, 0, nums.length - 1);
    }

    public int search(int[] nums, int target, int start, int end) {
        if (start == end) return nums[start] == target ? start : -1;

        int mid = (start + end) / 2;
        int midn = nums[mid];
        System.out.println(String.format("%s - %s mid:%s midn:%s", nums[start], nums[end], mid, midn));
        if (midn == target) return mid;
        if (midn == nums[start]) {
            if (nums[start] == target) {
                return start;
            }
            if (nums[end] == target) {
                return end;
            }
            return -1;
        } else if (midn < nums[start]) {
            // mid落入小区 [6,7,0,1,2,4,5]
            if (target < midn || target > nums[end]) {
                // left
                return search(nums, target, start, mid - 1);
            } else if (target > midn && target < nums[end]) {
                // right
                return search(nums, target, mid + 1, end);
            } else if (target == nums[end]) {
                return end;
            }
        } else if (midn > nums[end]) {
            // mid落入大区 [2,4,5,6,7,0,1]
            if (target > nums[start] && target < midn) {
                // left
                return search(nums, target, start, mid - 1);
            } else if (target == nums[start]) {
                return start;
            } else {
                // right
                return search(nums, target, mid + 1, end);
            }

        } else {
            // 进入了正常区域

            if (target < midn) {
                return search(nums, target, start, mid - 1);
            } else {
                return search(nums, target, mid + 1, end);
            }
        }
        return -1;
    }
}
