package bishi;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class BiShiT {
    /**
     * hshaksdhqqkqwkasfaa,shqjqijasjkdhajkcsnidh 找出a和s之间的字符串并排序
     */
    @Test
    public void No_1(){
        String source = "hshaksdhqqkqwkasfaa,shqjqijasjkdhajkcsnidh";
        int left = -1, right = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<source.length();i++){
            if(source.charAt(i) == 'a'){
                left = i;
                right = i;
            }else if(source.charAt(i) == 's'){
                right = i;
                if(left!=-1 && left < right){
                    sb.append(source, left+1, right);
                }
            }
        }
        char[] chars = sb.toString().toCharArray();
        quickSort(chars, 0, chars.length-1);
        System.out.println(JSON.toJSONString(chars));
    }
    private void quickSort(char[] source,int begin,int end){
        if(begin >= end ){
            return;
        }
        int left = begin,right = end;
        char tmp = source[begin];
        while(left < right){
            while (left < right && source[right] > tmp ) {
                right--;
            }
            if(left < right){
                source[left++] = source[right];
            }
            while (left < right && source[left] < tmp ) {
                left++;
            }
            if(left < right){
                source[right--] = source[left];
            }
        }
        if(left != begin){
            source[left] = tmp;
        }
        quickSort(source, begin, left-1);
        quickSort(source, left+1, end);
    }

    /**
     * 100个任务分10批运行，每批必须顺序执行（第一批执行完才能执行第二批），单机器如何实现，集群模式如何实现，架构怎么设计？
     */
    @Test
    public void No_2(){
        //单机器：
        //   方式1：将任务分发包装后放入队列，然后一个线程去取，既要保证放入的顺序性，也要保证取出的顺序性，有点类似顺序消息
        //   方式2：使用condition
        //集群模式：
        //   方式1：使用队列
        //   方式2：借助消息中间件发送顺序消息
        //   方式3：在缓存里添加状态位
    }
}
