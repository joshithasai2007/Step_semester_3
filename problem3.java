import java.util.*;

public class problem3 {
    static boolean hasPairWithSum(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(target - num))
                return true;

            set.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println(hasPairWithSum(nums, target));

        int[] nums2 = {3, 4, 6};
        System.out.println(hasPairWithSum(nums2, 20));
    }
}