import hwp.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * create by caichengcheng
 * date:2019-05-29
 */
public class LeetCode {
    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
       //"mississippi"
        //"issip"
        int i = leetCode.strStr("mississippi", "issip");

    }

    public int longestValidParentheses(String s) {
        return 0;
    }

    public int divide(int dividend, int divisor) {

        int res = 0;
        boolean a = (dividend > 0) ^ (divisor > 0);
        if(dividend > 0){
            dividend = -dividend;
        }
        if(divisor > 0){
            divisor = -divisor;
        }
        while(dividend <= divisor ){
            int tmp_res = -1;
            int tmp_divisor = divisor;
            while(dividend <= (tmp_divisor <<1)){
                if(tmp_divisor < (Integer.MIN_VALUE >>1)){
                    break;
                }
                tmp_divisor = tmp_divisor <<1;
                tmp_res  = tmp_res << 1;
            }
            dividend-= tmp_divisor;
            res +=tmp_res;
        }
        return a? res : res == Integer.MIN_VALUE? Integer.MAX_VALUE:-res ;
    }
    public int strStr(String haystack, String needle) {
        if(haystack.length() < needle.length()){
            return -1;
        }

        if("".equals(needle)){
            return 0;
        }
        int needleIndex = 0;
        for(int i = 0;i<haystack.length();i++){
            if(haystack.length() - i < needle.length() - needleIndex){
                return -1;
            }
            if(haystack.charAt(i) == needle.charAt(needleIndex)){
                if(needleIndex == needle.length()-1){
                    return i-needleIndex;
                }
                needleIndex++;
                continue;
            }else {
                if(needleIndex !=0){
                    i =i-needleIndex;
                    needleIndex = 0;
                }
            }
        }
        return -1;
    }

    public int removeElement(int[] nums, int val) {
        if(nums == null ||nums.length == 0){
            return 0;
        }
        int l = -1;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] == val){
                if(l == -1){
                    l = i;
                }
                continue;
            }
            if(l!=-1){
                nums[l++] = nums[i];
            }
        }
        return l==-1?nums.length:l;
    }

    public int removeDuplicates(int[] nums) {
        if(nums == null ||nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        int l=0,prenumber = nums[0];
        for(int i = 1;i<nums.length;i++){
            if(nums[i] == prenumber){
                continue;
            }
            prenumber = nums[i];
            nums[++l] = prenumber;
        }
        return l+1;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(k <=0 || head == null){
            return head;
        }
        int t =k;
        ListNode start = head,pn,p = head;
        while(t>1){
            if(p == null){
                return head;
            }
            p = p.next;
            t--;
        }
        if(p == null){
            return head;
        }
        pn = p.next;
        //反转
        ListNode h = reverse(start, p);
        //串接前后
        head = h;
        //递归继续
        start.next = reverseKGroup(pn,k);
        return head;
    }
    private ListNode reverse(ListNode head,ListNode end){
        ListNode cur = head,pre = null,tmp,en = end.next;
        while(cur != en){
            tmp = cur.next;
            cur.next = pre;
            pre =cur;
            cur = tmp;
        }
        return pre;
    }


    public ListNode swapPairs(ListNode head) {
        ListNode t = head,n,nn,pre = null;
        while(t!=null && t.next != null){
            n = t.next;
            nn = n.next;
            if(pre != null){
                //串接两个分段
                pre.next = n;
            }
            if(t == head){
                head = n;
            }
            n.next = t;
            pre = t;
            t.next =nn;
            t = nn;
        }
        return head;
    }

        public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        return quickMerge(lists,0,lists.length-1);
    }

    private ListNode quickMerge(ListNode[] lists , int start,int end){
        if(start ==end){
            return lists[start];
        }
        int mid = (end + start) >>1;
        ListNode l1 = quickMerge(lists, start, mid);
        ListNode l2 = quickMerge(lists, mid + 1, end);
        return mergeTwoLists(l1,l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode tmp = res;
        while(l1 != null && l2 !=null){
            if(l1.val > l2.val){
                tmp.next = l2;
                l2 = l2.next;
            }else{
                tmp.next = l1;
                l1 = l1.next;
            }
            tmp = tmp.next;
        }
        tmp.next = l1 == null ? l2 : l1;
        return res.next;

    }

    public boolean isValid(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        Stack<String> left = new Stack();
        String tmp ;
        for(int i = 0;i<s.length();i++){
            tmp = String.valueOf(s.charAt(i));
            if(tmp.equals("{") || tmp.equals("[") || tmp.equals("(")){
                left.push(tmp);
                continue;
            }
            if(tmp.equals("}") || tmp.equals("]") || tmp.equals(")")){
                if(left.size() ==0){
                    return false;
                }
                if(!getleft(left.pop()).equals(tmp)){
                    return false;
                }
            }
        }
        if(left.size() >0 ){
            return false;
        }

        return true;
    }
    private String getleft(String p){
        switch (p){
            case "{" : return "}";
            case "[" : return "]";
            case "(" : return ")";
            default:return "";
        }
    }
    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode l = head,r = head,ll = head ;
        int count = 1;
        while(r.next != null){
            if(count < n ){
                r = r.next ;
                count ++;
            }else{
                r = r.next;
                ll = l;
                l = l.next;
            }
        }
        if(l == ll){
            return ll.next;
        }

        if(l == r){
            ll.next = null;
            return head;
        }
        ll.next = l.next;
        return head;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<nums.length-3;i++){
            if(i>0 && nums[i] == nums[i-1] ){
                continue;
            }
            if(nums[i] + nums[i+1] +nums[i+2] + nums[i+3] > target){
                break;
            }
            if(nums[nums.length-1] + nums[nums.length-2] +nums[nums.length-3] + nums[nums.length-4] < target){
                break;
            }
            int t = target - nums[i];
            if(target > 0 && t<0){
                return res;
            }
            if(target < 0 && nums[i]>=0){
                return res;
            }

            if(i < nums.length -3){
                List<List<Integer>> lists = threeSum(nums, i + 1, t);
                if(lists.size() >0){
                    for(List<Integer> l: lists){
                        ArrayList<Integer> al = new ArrayList<>(l);
                        al.add(nums[i]);
                        res.add(al);
                    }
                }
            }
        }
        return res;
    }
    public List<List<Integer>> threeSum(int[] nums,int start,int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3){
            return res;
        }
//        Arrays.sort(nums);
        int l,r;
        for(int i = start;i<nums.length-2;i++){
            if(target < 0 && nums[i] >= 0){
                return res;
            }
            if(i >start && nums[i] == nums[i-1]){
                continue;
            }
            r = nums.length -1;
            l = i+1;
            while(l < r ){
                int sum = nums[i] + nums[l] +nums[r];
                if(sum == target){
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));

                    while (l<r && nums[l+1] == nums[l]){
                        l++;
                    }
                    while (l<r && nums[r-1] == nums[r]){
                        r--;
                    }
                    l++;
                    r--;
                }else if(sum > target){
                    r--;
                }else {
                    l++;
                }
            }

        }
        return res;
    }
    public int threeSumClosest(int[] nums, int target) {
        int res = 0;
        if(nums == null || nums.length < 3 ){
            return res;
        }
        Arrays.sort(nums);
        res = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length -2 ;i++){
            if(i>1 && nums[i]==nums[i-1]){
                continue;
            }
            int l = i+1,r = nums.length -1;
            while(l < r){
                int sum = nums[i]+nums[l] + nums[r];
                if(sum == target){
                    return 0;
                }
                if(Math.abs(sum - target) < Math.abs(res - target)){
                    res = sum - target;
                }
                if(sum > target){
                    r--;
                }else {
                    l++;
                }

            }
        }


        return res;
    }



    public String longestCommonPrefix(String[] strs) {
        String res = "";
        if(strs.length == 0){
            return res;
        }
        if(strs.length == 1){
            return strs[0];
        }
        for(int i = 0;i<strs[0].length();i++){
            char c = strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                if(c!= strs[j].charAt(i)){
                    return res;
                }
            }
            res+=c;
        }
        return res;
    }
    public int intToRoman(String s) {
        /**
         * I             1
         * V             5
         * X             10
         * L             50
         * C             100
         * D             500
         * M             1000
         *
         */
        int pre = get(s.charAt(0));
        int res =0;
        for(int i=1;i<s.length();i++){
            int cur = get(s.charAt(i));
            if(pre  < cur){
                res -= pre;
            }else{
                res += pre;
            }
            pre = cur;
        }
        res += pre;
        return res;
    }

    private int get(char s){
        switch (s){
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default:return 0;
        }
    }

    /**
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int start1 = 0,start2=0;
        int end1 = nums1.length-1 , end2 = nums2.length-1;
        int k = (nums1.length + nums2.length + 1) /2;
        int k2 = (nums1.length + nums2.length + 2) /2;
        return  (getmid(nums1,start1,end1,nums2,start2,end2,k) + getmid(nums1,start1,end1,nums2,start2,end2,k2)) * 0.5;
    }

    private double getmid(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1-start1 +1;
        int len2 = end2-start2 +1;
        if(len1 > len2) return getmid(nums2,start2,end2,nums1,start1,end1,k);
        if(len1 == 0) return nums2[start2 + k -1];
        if(k == 1) return Math.min(nums1[start1],nums2[start2]);

        int i = start1 + Math.min(k/2,len1) - 1;
        int j = start2 + Math.min(k/2,len2) - 1;
        if(nums1[i] > nums2[j]){
            return getmid(nums1,start1,end1,nums2,j+1,end2,k-(j-start2+1));
        }else{
            return getmid(nums1,i+1,end1,nums2,start2,end2,k-(i-start1+1));
        }
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int max = 0;
        int from=0,to=0;
        for(int i=0;i<s.length();i++){
            int len1 = getLength(s,i,i);
            int len2 = getLength(s,i,i +1);
            int len = Math.max(len1,len2);
            if(to - from +1 <= len){
                from = i - (len -1) / 2 ;
                to = i + len /2;
            }
        }
        return  s.substring(from,to + 1);
    }
    int getLength(String s,int left,int right){

        while(left >=0 && right< s.length() && s.charAt(left) == s.charAt(right) ){
            left --;
            right++;
        }
        return right - left -1;
    }

    public int lengthOfLongestSubstring(String s) {
        int result= 0;
        int start = 0;
        int curLength =0;

        for(int i=0;i<s.length();i++){

            int isContain = s.indexOf(s.charAt(i), start);
            if(isContain < i){
                //说明切割字符串包含了当前元素
                //重置start
                start = isContain+1;
                //比较最大长度
                curLength = i-start+1;
                if(curLength > result){
                    result = curLength;
                }
            }
            curLength++;
        }

        return curLength>result?curLength:result;
    }


    public int myAtoi(String str) {
        str = str.trim();
        if (str == null || str.length() == 0) return 0;

        // + - 号
        char firstChar = str.charAt(0);
        int sign = 1;
        int start = 0;
        long res = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }

        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            res = res * 10 + str.charAt(i) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && res > Integer.MAX_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return (int) res * sign;
    }

}
