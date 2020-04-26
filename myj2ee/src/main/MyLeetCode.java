import java.util.HashMap;

/**
 * @author caichengcheng
 * date:2020-03-20
 */
public class MyLeetCode {
    public static void main(String[] args) {
        System.out.println(new MyLeetCode().myAtoi("42"));
    }
    public int myAtoi(String str) {
        str = str.trim();
        int res = 0,max=Integer.MAX_VALUE/10,min=Integer.MIN_VALUE/10;
        //true 正数 ，false 负数
        boolean tag = true;
        int i=0 ;
        if(str.charAt(0) == '-'){
            tag = false;
            i=1;
        }
        for(;i<str.length();i++){
            if((!Character.isDigit(str.charAt(i))) || res == Integer.MAX_VALUE || res == Integer.MIN_VALUE ){
                break;
            }
            Integer k = Integer.valueOf(str.substring(i,i+1));
            if(res > max || (res==max && k>=7)) res = Integer.MAX_VALUE;
            if(res < min || (res==min && k>=8)) res = Integer.MIN_VALUE;
            if(tag){
                res = res * 10 +k;
            }else{
                res = res * 10 - k;
            }
        }
        return res;
    }

    /**
     * no3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 最简单实现：使用map记录重复字符的角标+双指针
     * 缺点：内存空间
     */
    public int lengthOfLongestSubstring1(String s) {
        HashMap<Character,Integer> map = new HashMap();
        int res = 0,l=0;
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                l = Math.max(map.get(s.charAt(i))+1,l);
            }
            map.put(s.charAt(i),i);
            res = Math.max(res,i-l+1);
        }
        return res;
    }
    /**
     * no3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 使用128长度的int数组，字符对应的ASCII码范围是0-127，遍历字符串，每次取出一个字符，判断对应的ASCII码为数组角标上的值==1?
     * tag==1 则表明字符重复，则从l开始，把array[l]-- ,知道找到重复的字符，此时重复的字符及之前的字符在array对应位置上都置为0
     * tag==0 表明目前为止，子串不含重复字符，r++ 右指针后移, array[index]++ 标识该字符录入，用于判断是否重复
     */
    public int lengthOfLongestSubstring2(String s) {
        int[] array = new int[128];
        char[] chars = s.toCharArray();
        int res =0,l=0,r=0;
        while(r < chars.length ){
            if(array[chars[r]] == 0){
                array[chars[r++]]++;
            }else{
                array[chars[l++]]--;
            }
            res = Math.max(res,r-l);
        }
        return res;
    }

    /**
     * no4.给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     *请你找出这两个有序数组的中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }


}
