import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 各种数据结构的时间复杂度：https://www.bigocheatsheet.com/
 * 链表
 * 206 反转链表
 * 24 链表两两反转
 * 141 判断链表是否有环
*/

/**
 * 堆栈、队列
 * No.20 括号字符串
 * No.232 stack->queue
 * No.225 queue->stack
 */

/**
 * 优先队列
 * 703 返回数据中第K大的数
 * 239 滑动窗口最大值
 */

/**
 * map和set
 * No.242 （简单）给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词 》》使用int[128]
 * No.1（简单） 找出数组中两数之和等于target 》》HashMap<num[i],i>
 * No.15（中等）三数之和 》》先排序，再遍历+左右指针，若三数大于target，右指针左移，若小于target,左指针右移，相等，则加入结果集，
 *                      并且左指针右移，右指针左移，直到指针上的值不同
 * No.18（中等）四数之和》》排序，然后target-nums[i]的值去找三数之和，有的话，就算上nums[i],加入结果集
 */

/**
 * 树、图
 * No.98 判断树是否是二叉搜索树
 * No.235,236 判断二叉树中最近公共祖先
 */


/**
 * 分治法 （递归）
 * No.50 计算x的n次方 ，一定要尝试使用非递归方式去做一次
 * No.169 找众数
 */

/**
 * 贪心
 * No.122 买卖股票
 */

/**
 * 深度优先搜索和广度优先搜索
 * No.102 对树进行广度优先遍历》》LinkList, queue.size
 * No.104 树最大深度》》使用深度优先遍历，递归，root==null? 0 : Math.max(left,right)+1 ;
 * No.111 树最小深度》》 广度优先遍历，首次为空就代表最小深度
 * No.22 生成括号
 *
 */

/**
 * 动态规划
 * No.70 爬楼梯
 * No.120 三角形最小路径的和
 * No.152 乘积最大子数列
 * No.300 最长上升子序列
 * NO.121、122、123、309、188、714 买卖股票的最佳时机
 */


/**
 * 其他面试题
 * No.70 爬楼梯
 * No.200 岛屿个数
 * No.103 二叉树锯齿形遍历
 * No.110 平衡二叉树
 * No.19 删除链表的倒数第N个节点
 * No.83 删除排序链表的重复元素
 * No.33 搜索旋转排序数组
 */
 /*
 * create by caichengcheng
 * date:2019-05-29
 */
public class LeetCode {
    static Object o = new Object();
    public static void main(String[] args) {
//        System.out.println(new LeetCode().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
//        int[][] a = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
//        List<Integer> integers = new LeetCode().spiralOrder(a);
//        System.out.println(new LeetCode().canJump(new int[]{3,2,1,0,4}));
        LeetCode leetCode = new LeetCode();
        Integer i1 = 12;
        Integer i2 = -128;
        Stack<Character> stack = new Stack();
        stack.push('a');
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        i1.intValue();
        System.out.println( leetCode.myPow2(2,-2));
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        //行数
        int x = grid.length;
        //列数
        int y = grid[0].length;
        int res = 0;
        LinkedList<Integer> queue=new LinkedList();queue.offer(1);
        //bfs
        for(int i=0;i<x;i++ ){
            for(int j=0;j<y;j++){
                if(grid[i][j] == '1'){
                    res++;
                    queue.offer(i*y+j);
                    grid[i][j] ='0';
                    while(queue.size()>0){
                        int index = queue.poll();
                        int indexi = index / y;
                        int indexj = index % y;
                        if(indexi>0 && grid[indexi-1][indexj] == '1'){
                            grid[indexi-1][indexj] = '0';
                            queue.offer((indexi-1)*y + indexj);
                        }
                        if(indexi+1<x && grid[indexi+1][indexj] == '1'){
                            grid[indexi+1][index] = '0';
                            queue.offer((indexi+1)*y + indexj);
                        }
                        if(indexj>0 && grid[indexi][indexj-1] == '1'){
                            grid[indexi][indexj-1] = '0';
                            queue.offer(indexi*y + indexj-1);
                        }
                        if(indexj +1<y && grid[indexi][indexj+1] == '1'){
                            grid[indexi][indexj+1] = '0';
                            queue.offer(indexi*y + indexj+1);
                        }
                    }
                }
            }
        }
        return res;
    }

    //no.57插入区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<int[]>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if(newInterval[0]>right){
                res.add(intervals[i]);
            }else if(false){

            }

        }
        return res.toArray(new int[0][]);
    }

    //no.56
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);

        for(int i=0;i<intervals.length;i++){
            int left = intervals[i][0] , right = intervals[i][1];
            while(i+1 < intervals.length && right>=intervals[i+1][0]){
                right = Math.max(right,intervals[i+1][1]);
                i++;
            }
            list.add(new int[]{left,right});
        }
        return list.toArray(new int[0][]);
    }

    //no.55
    public boolean canJump(int[] nums) {
        if(nums.length == 1 ){
            return nums[0] >=0;
        }
        int curLength= 0,maxLength = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 0 && maxLength <= i){
                return false;
            }
            curLength = i+nums[i];
            if(maxLength < curLength){
                maxLength = curLength;
                if(maxLength>= nums.length-1){
                    return true;
                }
            }

        }
        return false;
    }
    //no.54
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        if(matrix.length == 0){
            return result;
        }
        int z = 0,
            y = matrix[0].length-1,
            s = 0,
            x = matrix.length -1;
        int i=0,j=0;
        int k = 1;//1向右，2向下，3向左，4向上
        int num = matrix.length * matrix[0].length;
        while(true){
            if(k==1){
                if(j<y){
                    result.add(matrix[i][j]);
                    j++;
                }else if(j==y){
                    result.add(matrix[i][j]);
                    i++;
                    s++;
                    k = 2;
                }
            }else if(k==2){
                if(i<x){
                    result.add(matrix[i][j]);
                    i++;
                }else if(i==x){
                    result.add(matrix[i][j]);
                    y--;
                    j--;
                    k = 3;
                }
            }else if(k==3){
                if(j>z){
                    result.add(matrix[i][j]);
                    j--;
                }else if(j==z){
                    result.add(matrix[i][j]);
                    x--;
                    i--;
                    k=4;
                }
            }else if(k==4){
                if(i>s){
                    result.add(matrix[i][j]);
                    i--;
                }else if(i==s){
                    result.add(matrix[i][j]);
                    z++;
                    j++;
                    k=1;
                }
            }
            if(result.size() == num){
                break;
            }
        }
        return result;
    }
    //no.53
    public int maxSubArray(int[] nums) {
        int max= nums[0],tmp=nums[0];
        for(int i=1;i<nums.length;i++){
            if(tmp > 0){
                tmp+=nums[i];
            }else{
                tmp = nums[i];
            }
            max = Math.max(max,tmp);
        }
        return max;
    }
    //no.50
    public double myPow(double x, int n) {
        if(n < 0){
            x = 1/x;
            n = -n;
        }
        return fastPow(x,n);
    }
    private double fastPow(double x, long n) {
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return x;
        }
        double half = fastPow(x,n/2);
        if(n%2 == 0){
            return half * half;
        }else{
            return half * half * x;
        }
    }
    //no.50
    public double myPow2(double x, int n) {
        if(n < 0){
            x = 1/x;
            n = -n;
        }
        double res = 1;
        while(n > 0){
            if((n & 1) == 1 ){
                res *= x;
            }
            x*=x;
            n=n>>1;
        }
        return res;
    }

    //no.49
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String tmp = String.valueOf(chars);
            List<String> ms = map.get(tmp);
            if(ms!=null){
                ms.add(strs[i]);
            }else{
                ArrayList<String> strings = new ArrayList<>();
                strings.add(strs[i]);
                map.put(tmp,strings);
                result.add(strings);
            }
        }
        return result;
    }

    //no.48
    public void rotate(int[][] matrix) {
        for(int i=0;i<matrix.length;i++){
            for(int j = i;j<matrix[i].length;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length/2;j++){
                matrix[i][j] = matrix[i][j] ^ matrix[i][matrix[i].length - j-1];
                matrix[i][matrix[i].length - j-1] = matrix[i][j]^matrix[i][matrix[i].length - j-1];
                matrix[i][j] =matrix[i][j] ^matrix[i][matrix[i].length - j-1];
            }
        }
    }

    //no.47
    public List<List<Integer>> permuteUnique(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if(nums.length ==0){
            return result;
        }
        Arrays.sort(nums);
        dopermuteUnitque(nums,new boolean[nums.length],new Stack<Integer>(),result);
        return result;
    }
    private void dopermuteUnitque(int[] nums,boolean[] use,Stack<Integer> stack,ArrayList<List<Integer>>  result){
        if(stack.size() == nums.length ){
            result.add(new ArrayList<>(stack));
        }
        for(int i = 0;i<nums.length;i++){
            if(i > 0 && nums[i] == nums[i-1] && !use[i-1] ){
                continue;
            }
            stack.push(nums[i]);
            use[i] = true;
            dopermuteUnitque(nums,use,stack,result);
            stack.pop();
            use[i] = false;

        }
    }


    //no.46
    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if(nums.length ==0){
            return result;
        }
        dopermute(nums,new boolean[nums.length],new Stack<Integer>(),result);
        return result;
    }
    private void dopermute(int[] nums,boolean[] use,Stack<Integer> stack,List<List<Integer>> result){
        if(stack.size() == nums.length){
            result.add(new ArrayList<>(stack));
        }
        for(int i = 0;i<nums.length;i++){
            if(use[i]){
                continue;
            }
            stack.push(nums[i]);
            use[i] = true;
            dopermute(nums,use,stack,result);
            stack.pop();
            use[i] = false;

        }
    }

    //no.44
    public int jump(int[] nums) {
        int step=0;
        int maxIndex = 0;
        int end= 0;
        for(int i = 0;i<nums.length-1;i++){
            maxIndex = Math.max(maxIndex ,nums[i]+i);
            if(i == end){
                end = maxIndex ;
                step++;
            }
        }
        return step;
    }
    //no.43
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        int[]res = new int[num1.length() + num2.length()];
        int n1=0,n2=0;
        for(int i=num1.length() -1 ;i>=0;i--){
            n1 = num1.charAt(i) - '0';
            for(int j=num2.length()-1 ;j>=0;j--){
                n2 = num2.charAt(j) - '0';
                int k = res [j+i+1] + n1 * n2;
                res [j+i+1] = k %10  ;
                res[i +j] += k /10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i< res.length ;i++){
            if(sb.length() == 0 && 0==res[i]){
                continue;
            }
            sb.append(res[i]);
        }
        return sb.toString();

    }
    //no.42
    public int trap(int[] height) {
        int right = height.length - 1,left = 0,rightMax = 0,leftMax = 0,cap = 0;
        while(right > left){
            if(height[left] < height[right] ){
                if (height[left] > leftMax) {
                    leftMax = height[left] ;
                }else{
                    cap += leftMax - height[left];
                }
                left++;
            }else{
                if (height[right] > rightMax) {
                    rightMax = height[right];
                }else{
                    cap += rightMax - height[right];
                }
                right--;
            }
        }
        return cap;
    }
    //no.41
    public int firstMissingPositive(int[] nums) {
        //思路:第一次遍历判断数组是否包含1，
        int n = nums.length;

        // 基本情况
        int contains = 0;
        for (int i = 0; i < n; i++){
            if (nums[i] == 1) {
                contains++;
                break;
            }

        }

        if (contains == 0) {
            return 1;
        }

        // nums = [1]
        if (n == 1)
        {
            return 2;
        }

        // 用 1 替换负数，0，
        // 和大于 n 的数
        // 在转换以后，nums 只会包含
        // 正数
        for (int i = 0; i < n; i++)
        {
            if ((nums[i] <= 0) || (nums[i] > n))
            {
                nums[i] = 1;
            }
        }

        // 使用索引和数字符号作为检查器
        // 例如，如果 nums[1] 是负数表示在数组中出现了数字 `1`
        // 如果 nums[2] 是正数 表示数字 2 没有出现
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            // 如果发现了一个数字 a - 改变第 a 个元素的符号
            // 注意重复元素只需操作一次
            if (a == n) {
                nums[0] = -Math.abs(nums[0]);
            }
            else
            {
                nums[a] = - Math.abs(nums[a]);
            }
        }

        // 现在第一个正数的下标
        // 就是第一个缺失的数
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0)
            {
                return i;
            }
        }

        if (nums[0] > 0)
        {
            return n;
        }

        return n + 1;
    }

    //no.40
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates.length == 0){
            return res;
        }
        Arrays.sort(candidates);
        doCombinationSum2(0,candidates.length,target,new Stack<Integer>(),candidates,res);

        return res;
    }

    private void doCombinationSum2(int start, int end, int target, Stack<Integer> list,int[] candidates, List<List<Integer>> res) {
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=start;i<end && target -candidates[i] >=0 ;i++){
            if(i>start && candidates[i] == candidates[i-1]){
                continue;
            }

            list.push(candidates[i]);
            doCombinationSum2(i+1,end,target-candidates[i],list,candidates,res);
            list.pop();
        }
    }

    //no.39
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates.length == 0){
            return res;
        }
        doCombinationSum(0,candidates.length,target,new Stack<Integer>(),candidates,res);

        return res;
    }

    private void doCombinationSum(int start, int end, int target, Stack<Integer> list,int[] candidates, List<List<Integer>> res) {
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=start;i<end ;i++){
            if(target- candidates[i] >=0){
                continue;
            }
            list.push(candidates[i]);
            doCombinationSum(i,end,target-candidates[i],list,candidates,res);
            list.pop();
        }
    }


    //no.35
    public int searchInsert(int[] nums, int target) {
        int r = nums.length - 1,l=0,mid=0;
        if(target > nums[r]){
            return r+1;
        }else if(target < nums[l]){
            return 0;
        }

        while(l <= r){
            if(target > nums[r]){
                return r+1;
            }else if(target < nums[l]){
                return l-1;
            }
            mid = l+(r-l)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] < target && nums[mid+1] > target){
                return mid +1;
            }
            if(nums[mid] < target ){
                l = mid+1;
            }else{
                r = mid -1;
            }
        }
        return 0;
    }
    //no.34
    public int[] searchRange(int[] nums, int target) {
        int l = 0,r=nums.length-1,mid=0;
        int[] res = new int[]{-1,-1};
        while(l < r){
            mid=l+(r-l) /2;
            if(nums[mid] == target){
                int m=mid,n=mid;
                boolean lb = true ,rb = true;
                while( lb || rb){
                    if(lb){
                        if(m-1>=0 && nums[m-1] == target){
                            m--;
                        }else{
                            lb = false;
                        }
                    }
                    if(rb){
                        if(n+1 <=nums.length-1 && nums[n+1] == target){
                            n++;
                        }else{
                            rb = false;
                        }
                    }
                }
                res[0] = m;
                res[1] = n;
                return res;
            }else if(nums[mid] > target){
                r = mid-1;
            }else{
                l = mid +1;
            }
        }
        return res;
    }
    //最长无重复子串
    public int lengthOfLongestSubstringForNo3(String s) {
        char[] sc = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap();
        int res = 0,l=0,r=0;
        for(int i=0;i<sc.length;i++){
            if(map.containsKey(sc[i])){
                l = Math.max(map.get(sc[i])+1,l);
            }
                r=i;

            map.put(sc[i],i);
            res = Math.max(res,r-l+1);
        }
        return res;
    }
    //no.2 两个链表之和
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int tag = 0;
        ListNode res = new ListNode(0);
        ListNode head = res;
        while(l1!=null || l2 != null){
            int i1 = l1==null?0:l1.val;
            int i2 = l2==null?0:l2.val;
            int val = i1 + i2 + tag;
            if(val >= 10){
                tag = 1;
                val = val -10;
            }else{
                tag = 0;
            }
            res.next = new ListNode(val);
            res = res.next;

            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(tag == 1){
            res.next = new ListNode(1);
        }
        return head.next;
    }

    public int search(int[] nums, int target) {
        int l = 0,r = nums.length -1,res= -1,mid=0;
        while(l<=r){
            mid= l+(r-l) /2;
            if(nums[mid] == target){
                res= mid;
                break;
            }
            if (nums[l] > nums[mid] ) {
                //�ұ�����
                if(target <=nums[r] && target >=nums[mid]){
                    //target���Ұ벿��
                    l=  mid+1;
                }else{
                    r=mid-1;
                }
            }else{
                //�������
                if(target <=nums[mid] && target >=nums[l]){
                    //target����벿��
                    r = mid-1;
                }else{
                    l=mid+1;
                }
            }
        }
        return res;
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
        //��ת
        ListNode h = reverse(start, p);
        //����ǰ��
        head = h;
        //�ݹ����
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
                //���������ֶ�
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
        //��ż��������������ϲ����������������������ͬ���� k ��
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //�� len1 �ĳ���С�� len2���������ܱ�֤�����������ˣ�һ���� len1
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
                //˵���и��ַ��������˵�ǰԪ��
                //����start
                start = isContain+1;
                //�Ƚ���󳤶�
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

        // + - ��
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

    public int lengthOfLongestSubstring1(String s) {
        //ʹ��ASCII��Աȣ�ASCII��0-127�������ø�128���ȵ�����պö�Ӧ
        int[] freq = new int[128];
        int res=0;
        int l=0,r=-1;
        char[] c = s.toCharArray();
        while(l<s.length()){
            if(r+1<c.length && freq[c[r+1]]==0){
                freq[c[++r]]++;
            }else{
                freq[c[l++]]--;
            }
            res = Math.max(res,r-l+1);
        }
        return res;
    }
}
