class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[] { -1, -1 };
        int ans[] = { nums.length, -1 };
        int st = 0, end = nums.length - 1;
        while (st <= end) {
            int mid = (st + end) / 2;
            if (nums[mid] == target) {
                ans[0] = Math.min(ans[0], mid);
                end = mid - 1;
            } else if (nums[mid] < target)
                st = mid + 1;
            else
                end = mid - 1;
        }
        st = 0;
        end = nums.length - 1;
        while (st <= end) {
            int mid = (st + end) / 2;
            if (nums[mid] == target) {
                ans[1] = Math.max(ans[1], mid);
                st = mid + 1;
            } else if (nums[mid] < target)
                st = mid + 1;
            else
                end = mid - 1;
        }
        ans[0] = ans[0] == nums.length ? -1 : ans[0];
        return ans;
    }
}