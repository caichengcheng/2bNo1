import org.junit.Test;

import java.util.Arrays;

/**
 * @author caichengcheng
 * date:2020-03-20
 */
public class Leetcode_Array {
    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     *
     * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * 输入:
     * nums = [1, 7, 3, 6, 5, 6]
     * 输出: 3
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int total = 0;
        int res = -1;
        if(nums.length <=1){
            return -1;
        }
        int l = nums[0];
        for(int i=0;i<nums.length;i++){
            total+=nums[i];
        }
        if(total - l == 0 ){
            return 0;
        }
        for(int i=1;i<nums.length;i++){
            if(total-nums[i] == l*2 ){
                res = i;
                break;
            }
            l+=nums[i];
        }
        if(res ==-1 && total - nums[nums.length-1] == 0){
            return nums.length-1;
        }
        return res;
    }

    @Test
    public void testPivotIndex(){
//        System.out.println(new Leetcode_Array().pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
//        System.out.println(new Leetcode_Array().pivotIndex(new int[]{1, 2, 3}));
//        System.out.println(new Leetcode_Array().pivotIndex(new int[]{-1,-1,-1,0,1,1}));
//        System.out.println(new Leetcode_Array().pivotIndex(new int[]{-1,-1,0,1,1,0}));
        System.out.println(new Leetcode_Array().pivotIndex(new int[]{-1,-1,1,1,0,0}));

    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     *
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     *
     * 如果是，则返回最大元素的索引，否则返回-1。
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        int res = -1;
        if(nums.length == 0){
            return res;
        }
        if(nums.length == 1){
            return 0;
        }
        if(nums.length == 2){
            if(nums[0]>nums[1] && nums[0]>= nums[1]*2){
                return 0;
            }
            if(nums[0]<nums[1] && nums[1]>= nums[0]*2){
                return 1;
            }
            return -1;
        }
        int first = 0,sec=0;
        int max = -1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>nums[first]){

            }
        }
return 0 ;
    }

}
